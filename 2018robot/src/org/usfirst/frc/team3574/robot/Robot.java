/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3574.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3574.autonomous.AutonomousSelectorForSide;
import org.usfirst.frc.team3574.autonomous.AutonomousSelectorForSwitch;
import org.usfirst.frc.team3574.autonomous.DriveForwardAutonomous;
import org.usfirst.frc.team3574.commands.arm.CalibrateArmEnc;
import org.usfirst.frc.team3574.commands.arm.CalibrateArmEncStartingPosition;
import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.driveTrain.DoNothing;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByPIDDistance;
import org.usfirst.frc.team3574.commands.driveTrain.ResetDriveEnc;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree2;
import org.usfirst.frc.team3574.commands.slide.ResetEncIfAtLowestPoint;
import org.usfirst.frc.team3574.commands.slide.ResetSlideEnc;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.subsystems.Claw;
import org.usfirst.frc.team3574.subsystems.DriveTrain;
import org.usfirst.frc.team3574.subsystems.ForkLifter;
import org.usfirst.frc.team3574.subsystems.Slide;
import org.usfirst.frc.team3574.subsystems.UtilitySubsystem;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithCube;
import org.usfirst.frc.team3574.utilities.L;
import org.usfirst.frc.team3574.subsystems.SensorTest;
import org.usfirst.frc.team3574.subsystems.JackWings;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	Thread m_visionThread;
	//subsystems 
	public static final DriveTrain  driveTrain = new DriveTrain();
	public static final SensorTest  sensorTest = new SensorTest();
	public static final Slide  	    slide = new Slide();
	public static final JackWings   jackWings = new JackWings();
	public static final Claw        claw = new Claw();
	public static final Arm 	    arm = new Arm();
	public static final ForkLifter  forkLifter = new ForkLifter();
	public static final UtilitySubsystem utilitySubsystem = new UtilitySubsystem();
	Command m_autonomousCommand;
	public static OI OperatorInput;
	SendableChooser<Command> autoChooserForLosers = new SendableChooser<>();
	SendableChooser<Command> startPositionChooser = new SendableChooser<>();	

	public double _matchTime;
	
	private Timer time = new Timer();
	private double lastTime;

	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//This is the one, only, and singular line that makes the camera do it's thing
		m_visionThread = new Thread(() -> {
			// Get the UsbCamera from CameraServer
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			// Set the resolution
			camera.setResolution(320, 240);

			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream
					= CameraServer.getInstance().putVideo("Rectangle", 320, 240);

			// Mats are very memory expensive. Lets reuse this Mat.
			Mat mat = new Mat();

			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {
				// Tell the CvSink to grab a frame from the camera and put it
				// in the source mat.  If there is an error notify the output.
				if (cvSink.grabFrame(mat) == 0) {
					// Send the output the error.
					outputStream.notifyError(cvSink.getError());
					// skip the rest of the current iteration
					continue;
				}
				
				// Put a rectangle on the image
				Imgproc.rectangle(mat, new Point(100, 100), new Point(200, 200),
						new Scalar(255, 255, 255), 5);
				Imgproc.line(mat, new Point(5, 5),new Point(30, 50), new Scalar(255,0,255), 5);
				// Give the output stream a new image to display
				outputStream.putFrame(mat);				
			}
		});
		m_visionThread.setDaemon(true);
		m_visionThread.start();
	
		
		OperatorInput = new OI();
		autoChooserForLosers.addObject("Do Nothing", new DoNothing());
		autoChooserForLosers.addDefault("Drive Across Line", new DriveForwardAutonomous());
		autoChooserForLosers.addObject("Cube in switch from middle", new AutonomousSelectorForSwitch()); 
		autoChooserForLosers.addObject("Start Left Side", new AutonomousSelectorForSide("Left")); 
		autoChooserForLosers.addObject("Start Right Side", new AutonomousSelectorForSide("Right")); 

		SmartDashboard.putData("Scheduler", Scheduler.getInstance());

		SmartDashboard.putData("Auto mode", autoChooserForLosers);

		SmartDashboard.putData(Scheduler.getInstance());


		SmartDashboard.putData(new ResetSlideEnc());
		SmartDashboard.putData(new CalibrateArmEnc());
		SmartDashboard.putData(new CalibrateArmEncStartingPosition());
		L.ogSD(new DriveByPIDDistance(12));
		L.ogSD(new ResetDriveEnc());
		L.ogSD("Forward 12'", new DriveByInches(140, .5));
		L.ogSD("TurnToDegreeTwoPointOh 0", new TurnToDegree2(00.0, 0.3));
		L.ogSD("TurnToDegreeTwoPointOh 90", new TurnToDegree2(90.0, 0.3));
		L.ogSD("TurnToDegreeTwoPointOh 180", new TurnToDegree2(180, 0.3));
		L.ogSD("TurnToDegreeTwoPointOh -90", new TurnToDegree2(-90.0, 0.3));
		L.ogSD("TurnToDegreeTwoPointOh -180", new TurnToDegree2(-180, 0.3));
		L.ogSD(new SetArmPosition(Arm.AUTO_SWITCH_DELIVERY, new ArmSpeedSettingsWithCube()));
		
		new ResetSlideEnc().start();

		Robot.slide.setCurrent(0.0);
		
		time.reset();
		time.start();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = autoChooserForLosers.getSelected();
		
		setPeriod(0.015);

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */


		// schedule the autonomous command (example)
		
		new CalibrateArmEncStartingPosition().start();
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		setPeriod(.020);
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		_matchTime = DriverStation.getInstance().getMatchTime();
		//		Robot.driveTrain.driveStraight(0.5, 0);

		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	ResetEncIfAtLowestPoint resetCommand = new ResetEncIfAtLowestPoint();
	
	@Override
	public void robotPeriodic() {
		if (!resetCommand.isRunning()) {
			resetCommand.start();
		}
		
		this.log();
	}

	/**
	 * Calls all log methods in subsystems, putting data on the smartdashboard and console
	 */
	public void log() {
//		SmartDashboard.putNumber("Percent Throttle", OperatorInput.getRightStickY());
//		SmartDashboard.putNumber("Percent Rotation", OperatorInput.getLeftStickX());
//		SmartDashboard.putNumber("Match Time", _matchTime);
//		SmartDashboard.putNumber("a", (OperatorInput.getDialAxis()+1)/2);
//		SmartDashboard.putString("Switch & Scale Colors", DriverStation.getInstance().getGameSpecificMessage());
//		SmartDashboard.putNumber("POV of driverXbox360Controller", OperatorInput.GetPOV(Robot.OperatorInput.driverXbox360Controller));
		
		double currentTime = time.get();
		
		L.ogSD("loop time", currentTime - lastTime);
		
		lastTime = currentTime;

		Robot.driveTrain.log();
		Robot.sensorTest.log();
		Robot.slide.log();
		Robot.arm.log();
		Robot.utilitySubsystem.log();
	}
}