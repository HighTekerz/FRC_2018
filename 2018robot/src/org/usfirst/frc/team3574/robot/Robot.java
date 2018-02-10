/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3574.robot;

import org.usfirst.frc.team3574.autonomous.DriveForwardAutonomous;
import org.usfirst.frc.team3574.commands.RumbleASide;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.subsystems.DriveTrain;
import org.usfirst.frc.team3574.subsystems.SensorTest;
import org.usfirst.frc.team3574.subsystems.TheHedgehog;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
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

	public static final DriveTrain driveTrain = new DriveTrain();
	public static OI OperatorInput;
	public static final TheHedgehog theHedgehog = new TheHedgehog();
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	public static final SensorTest sensorTest = new SensorTest();
	public static final Arm arm = new Arm();
	
	
	public double _matchTime;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		OperatorInput = new OI();
		m_chooser.addDefault("Default Auto", new DriveForwardAutonomous());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		SmartDashboard.putData(Scheduler.getInstance());
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
		this.log();
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
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		// TODO: make it able to change through shuffle board
		m_autonomousCommand= new DriveForwardAutonomous();

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
		this.log();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		this.log();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		this.log();

	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		_matchTime = DriverStation.getInstance().getMatchTime();
		this.log();
//		Robot.driveTrain.driveStraight(0.5, 0);

		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

		this.log();
	}


	public void log() {
		SmartDashboard.putNumber("Percent Throttle", OperatorInput.getRightStickY());
		SmartDashboard.putNumber("Percent Rotation", OperatorInput.getLeftStickX());
		SmartDashboard.putNumber("Match Time", _matchTime);
		SmartDashboard.putNumber("a", (OperatorInput.getDialAxis()+1)/2);
		SmartDashboard.putString("Switch & Scale Colors", DriverStation.getInstance().getGameSpecificMessage());
		SmartDashboard.putNumber("POV of xbox", OperatorInput.GetPOV(Robot.OperatorInput.xbox));
		
		Robot.driveTrain.log();
		Robot.sensorTest.log();

	}
}
