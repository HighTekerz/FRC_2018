package org.usfirst.frc.team3574.subsystems;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.concurrent.TimeUnit;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import org.omg.CORBA.SetOverrideType;
import org.usfirst.frc.team3574.commands.driveTrain.DriveWithJoy;
import org.usfirst.frc.team3574.commands.util.L;
import org.usfirst.frc.team3574.enums.ShifterPosition;
import org.usfirst.frc.team3574.motionProfile.MotionProfileRight;
import org.usfirst.frc.team3574.robot.Constants;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.RobotMap;
import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	TalonSRX motorLeft1 = new TalonSRX(RobotMap.DriveTrainLeftTalon1);
	TalonSRX motorLeft2 = new TalonSRX(RobotMap.DriveTrainLeftTalon2);
	TalonSRX motorRight1 = new TalonSRX(RobotMap.DriveTrainRightTalon1);
	TalonSRX motorRight2 = new TalonSRX(RobotMap.DriveTrainRightTalon2);

	DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.ShifterSolenoid0, RobotMap.ShifterSolenoid1);

	PigeonIMU pid_geon = new PigeonIMU(motorLeft2);

	DigitalInput leftFrontCubeSensor = new DigitalInput(RobotMap.FloorCubeSensorLeft);
	DigitalInput rightFrontCubeSensor = new DigitalInput(RobotMap.FloorCubeSensorRight);

	MotionProfileRight mPRIGHT = new MotionProfileRight(motorRight1);
	MotionProfileRight mPLeft = new MotionProfileRight(motorLeft1);
	StringBuilder _sb = new StringBuilder();

	public double backupDistancePickupStart = -0.25;
	public double backupDistancePickupEnd = -3;
	public double backupDistanceSwitch = -3;
	public double backupDistanceScale = -12;
	public static final double ticksToInch = 217.2995489;

	double targetPos = 0;

	Timer t = new Timer();
	double lastT = 0;
	double currentT = 0; 
	/**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */
	public static final int kSlotIdx = 0;

	/*
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/*
	 * set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
	public static final int kTimeoutMs = 10;

	private static int _loops = 0;
	private static int _timesInMotionMagic = 0;

	double kPgain = 0.04; /* percent throttle per degree of error */
	double kDgain = 0.0004; /* percent throttle per angular velocity dps */
	double kMaxCorrectionRatio = 0.30; /* cap corrective turning throttle to 30 percent of forward throttle */

	//	public double _currentAngle;

	public static boolean runningPancake;
	public String pancakeMAC = "00-80-2F-17-D7-A3";

	public DriveTrain() {
		// TODO Auto-generated constructor stub
		motorLeft1.set(ControlMode.PercentOutput, 0.0);
		motorLeft2.set(ControlMode.Follower,  0.0);
		motorRight1.set(ControlMode.PercentOutput,  0.0);
		motorRight2.set(ControlMode.Follower,  0.0);
		motorLeft1.setNeutralMode(NeutralMode.Brake);
		motorRight1.setNeutralMode(NeutralMode.Brake);
		motorRight2.setNeutralMode(NeutralMode.Brake);
		motorLeft2.setNeutralMode(NeutralMode.Brake);



		//		runningPancake = false;
		//		InetAddress ip;
		//		try {
		//
		//			ip = InetAddress.getLocalHost();
		//			System.out.println("Current IP address : " + ip.getHostAddress());
		//
		//			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
		//
		//			byte[] mac = network.getHardwareAddress();
		//
		//			System.out.print("Current MAC address : ");
		//
		//			StringBuilder sb = new StringBuilder();
		//			for (int i = 0; i < mac.length; i++) {
		//				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
		//			}
		//			System.out.println(sb.toString());
		//			if (sb.toString().contains(pancakeMAC)) {
		//				System.out.println("Hi Pancake!");
		//				runningPancake = true;
		//				System.out.println("Inverting motors, please wait");
		// 				motorLeft1.setInverted(true);
		//				motorLeft2.setInverted(true);
		//				motorRight1.setInverted(true);
		//				motorRight2.setInverted(true);
		//			}
		//
		//		} catch (Exception e) {
		//
		//			e.printStackTrace();
		//
		//		}


		if (runningPancake) {
		}

		t.reset();
		t.start();

	}
	public void setPIDDistance(int ticks) {
		motorLeft1.set(ControlMode.Position,  getEncoderLeft() + ticks);
		motorRight1.set(ControlMode.Position, getEncoderRight() - ticks);
	}
//	public boolean isPIDDone() { 
//		if(motorLeft1.getClosedLoopError(arg0)) {
//			
//		}
//			
//		
//		return false;
//	}
	public int getEncoderLeft() {
		return motorLeft1.getSensorCollection().getPulseWidthPosition();
	}

	public int getEncoderRight() {
		//		Value reversed for clarity
		return -motorRight1.getSensorCollection().getPulseWidthPosition();
	}
	public void resetEncoders() {
		motorLeft1.getSensorCollection().setPulseWidthPosition(0, 1000);
		motorRight1.getSensorCollection().setPulseWidthPosition(0, 1000);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoy());
	}

	/** 
	 * Drives the robot using seperate inputs for the left and right side motors.
	 * Inputs are percentages of maximum motor output.
	 * 
	 * @param leftSpeed Speed of the left wheels
	 * @param rightSpeed Speed of the right wheels
	 */
	public void driveByTank (double leftSpeed, double rightSpeed)	
	{
		motorLeft1.set(ControlMode.PercentOutput, leftSpeed);
		motorLeft2.set(ControlMode.PercentOutput, leftSpeed);

		motorRight1.set(ControlMode.PercentOutput, rightSpeed);
		motorRight2.set(ControlMode.PercentOutput, rightSpeed);
	}    

	public void driveByEncoders (int setpoint, MotionProfileStatus _status) {

		motorLeft1.getMotionProfileStatus(_status);

		if (_status.hasUnderrun)

			motorLeft1.clearMotionProfileHasUnderrun(setpoint);

		motorLeft1.set(ControlMode.MotionProfile, setpoint);
	}

	/**
	 * Driving mechanism with throttle and turn
	 * Modified version that makes input values smaller to allow for more precise user control
	 * 
	 * @param percentThrottle Denotes how fast you want to go foreward/backwards
	 * @param percentRotationOutput Denotes how fast you want to turn (+ is left, - is right)
	 * @param scalingValue Slows the change in motor speed near maximum and minimum speed. Raise this value to make it slower
	 */
	public void driveByArcadeWithModifiers (double percentThrottle, double percentRotationOutput, double scalingValue )
	{
		percentThrottle = valueAfterDeadzoned(percentThrottle);
		percentRotationOutput = valueAfterDeadzoned(percentRotationOutput);

		percentThrottle = scalingSpeed(percentThrottle, scalingValue);
		percentRotationOutput = scalingSpeed(percentRotationOutput, scalingValue);

		SmartDashboard.putNumber("ACTUAL Percent Throttle", percentThrottle);
		SmartDashboard.putNumber("ACTUAL Percent Rotation", percentRotationOutput);

		this.driveByArcade(-percentThrottle, percentRotationOutput);
	}

	/**
	 * Driving mechanism with throttle and turn
	 * 
	 * @param percentThrottle Denotes how fast you want to go foreward/backwards
	 * @param percentRotationOutput Denotes how fast you want to turn (+ is left, - is right)
	 */
	public void driveByArcade (double percentThrottle, double percentRotationOutput) {

		motorLeft1.set(ControlMode.PercentOutput, -percentThrottle - percentRotationOutput);
		motorLeft2.set(ControlMode.PercentOutput, -percentThrottle - percentRotationOutput);

		motorRight1.set(ControlMode.PercentOutput, (-percentThrottle + percentRotationOutput) * -1.0);
		motorRight2.set(ControlMode.PercentOutput, (-percentThrottle + percentRotationOutput) * -1.0);		
	}

	/**
	 * Autonomous driving mechanism that attempts to keep the same angle
	 * 
	 * @param percentThrottle Denotes how fast you want to go foreward/backwards
	 * @param percentRotationOutput Denotes how fast you want to turn (+ is left, - is right)
	 * @param targetAngle Angle to stay at while driving. note that this will not return the robot to it's original line of motion if it is bumped
	 */
	public void driveStraightByArcade (double percentThrottle, double percentRotationOutput, double targetAngle) {

		percentThrottle = valueAfterDeadzoned(percentThrottle);
		percentRotationOutput = valueAfterDeadzoned(percentRotationOutput);

		if (percentRotationOutput == 0) {
			percentRotationOutput -= calculateDriveStraightTurnThrottle(percentThrottle, targetAngle);
		}
		driveByArcade(percentThrottle, percentRotationOutput);
	}

	public double scalingSpeed (double joystickValue, double scalingCutoff) {
		/**		TODO: Find better scaling system
				Here's a simple algorithm to add sensitivity adjustment to your joystick:

				x' = a * x^3 + (1-a) * x

				x is a joystick output ranging from -1 to +1

				x' is the sensitivity-adjusted output (also will be -1 to +1)

				"a" is a variable ranging from 0 to +1

				When a=0, you get x' = x

				When a=1, you get x' = x^3 which gives very fine control of small outputs

				When a is between 0 and 1, you get something in between.

				joystickValue is "x"

				below is "a", wait, no.

				below is "x^3"
		 */
		double joystickValueToTheThird = Math.pow(joystickValue, 3);

		//		x'   = a             * x^3                     +  (1-a)             * x
		return scalingCutoff * joystickValueToTheThird + ((1-scalingCutoff) * joystickValue);
	}

	public void doNothing () 
	{
		driveByTank(0.0, 0.0);

	}

	private double valueAfterDeadzoned (double currentValue) {
		//		This is the deadzone. Change to change how sensitive the robot is.
		double deadzone = 0.2;
		if (Math.abs(currentValue) < deadzone)
		{
			return 0;
		}
		else
		{
			return currentValue;
		}
	}

	public double calculateDriveStraightTurnThrottle(double forwardThrottle, double targetAngle) {
		//    	/* some temps for Pigeon API */
		//		PigeonIMU.FusionStatus fusionStatus = new PigeonIMU.FusionStatus();
		//		double [] xyz_dps = new double [3];
		//
		//		//		/* grab some input data from Pigeon */
		//		pid_geon.getFusedHeading(fusionStatus);
		//		double currentAngle = fusionStatus.heading;
		//		double currentAngularRate = xyz_dps[2];

		double turnThrottle = (targetAngle - getYaw()) * kPgain - (getYawRate()) * kDgain;

		/* the max correction is the forward throttle times a scaler,
		 * This can be done a number of ways but basically only apply small turning correction when we are moving slow
		 * and larger correction the faster we move.  Otherwise you may need stiffer pgain at higher velocities. */
		double maxThrottle = getMaxCorrection(forwardThrottle, kMaxCorrectionRatio);
		//		System.out.println("Before Cap " + turnThrottle);
		turnThrottle = cap(turnThrottle, maxThrottle);
		//		System.out.println("After Cap " + turnThrottle);
		return turnThrottle;
	}

	public double getYaw() {
		PigeonIMU.FusionStatus fusionStatus = new PigeonIMU.FusionStatus();
		return (pid_geon.getFusedHeading(fusionStatus) /*% 360*/);
	}

	public double getYawRate() {
		double [] xyz_dps = new double [3];
		pid_geon.getRawGyro(xyz_dps);

		return xyz_dps[2];
	}




	private double cap(double value, double peak) {
		if (value < -peak)
			return -peak;
		if (value > +peak)
			return +peak;
		return value;
	}

	private double getMaxCorrection(double forwardThrot, double scalor) {
		/* make it positive */
		if(forwardThrot < 0) {forwardThrot = -forwardThrot;}
		/* max correction is the current forward throttle scaled down */
		forwardThrot *= scalor;
		/* ensure caller is allowed at least 10% throttle,
		 * regardless of forward throttle */
		if(forwardThrot < 0.10)
			return 0.10;
		return forwardThrot;
	}

	public boolean areBothFrontSensorsTripped() {
		return (!leftFrontCubeSensor.get() && !rightFrontCubeSensor.get());
	}

	/**
	 * Command to shift gears on the drivetrain
	 * 
	 * @param lowOrHigh send 1 for low gear, 2 for high. 0 for off.
	 */
	public void ShiftGear(ShifterPosition lowOrHigh) {
		switch (lowOrHigh) {
		case OFF:
			shifter.set(DoubleSolenoid.Value.kOff);
		case LOW_GEAR:
			shifter.set(DoubleSolenoid.Value.kForward);
			break;
		case HIGH_GEAR:
			shifter.set(DoubleSolenoid.Value.kReverse);
			break;
		}
	}



	public void prepareForMotionMagic() {

		/* first choose the sensor */
		motorLeft1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx,  kTimeoutMs);
		motorLeft1.setSensorPhase(false);
		motorLeft1.setInverted(false);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		motorLeft1.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10,  kTimeoutMs);
		motorLeft1.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10,  kTimeoutMs);

		/* set the peak and nominal outputs */
		motorLeft1.configNominalOutputForward(0,  kTimeoutMs);
		motorLeft1.configNominalOutputReverse(0,  kTimeoutMs);
		motorLeft1.configPeakOutputForward(1,  kTimeoutMs);
		motorLeft1.configPeakOutputReverse(-1,  kTimeoutMs);

		/* set closed loop gains in slot0 - see documentation */
		motorLeft1.selectProfileSlot( kSlotIdx,  kPIDLoopIdx);
		motorLeft1.config_kF(0, 0.0,  kTimeoutMs);
		motorLeft1.config_kP(0, 0.8,  kTimeoutMs);
		motorLeft1.config_kI(0, 0.00001,  kTimeoutMs);
		motorLeft1.config_kD(0, 0.0,  kTimeoutMs);
		/* set acceleration and vcruise velocity - see documentation */
		motorLeft1.configMotionCruiseVelocity(15000,  kTimeoutMs);
		motorLeft1.configMotionAcceleration(6000,  kTimeoutMs);
		/* zero the sensor */
		motorLeft1.setSelectedSensorPosition(0,  kPIDLoopIdx,  kTimeoutMs);

		motorRight1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,  kPIDLoopIdx,  kTimeoutMs);
		motorRight1.setSensorPhase(false);
		motorRight1.setInverted(false);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		motorRight1.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10,  kTimeoutMs);
		motorRight1.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10,  kTimeoutMs);

		/* set the peak and nominal outputs */
		motorRight1.configNominalOutputForward(0,  kTimeoutMs);
		motorRight1.configNominalOutputReverse(0,  kTimeoutMs);
		motorRight1.configPeakOutputForward(1,  kTimeoutMs);
		motorRight1.configPeakOutputReverse(-1,  kTimeoutMs);

		/* set closed loop gains in slot0 - see documentation */
		motorRight1.selectProfileSlot( kSlotIdx,  kPIDLoopIdx);
		motorRight1.config_kF(0, 0.0,  kTimeoutMs);
		motorRight1.config_kP(0, 0.8,  kTimeoutMs);
		motorRight1.config_kI(0, 0.00001,  kTimeoutMs);
		motorRight1.config_kD(0, 0.0,  kTimeoutMs);
		/* set acceleration and vcruise velocity - see documentation */
		motorRight1.configMotionCruiseVelocity(15000,  kTimeoutMs);
		motorRight1.configMotionAcceleration(6000,  kTimeoutMs);
		/* zero the sensor */
		motorRight1.setSelectedSensorPosition(0,  kPIDLoopIdx,  kTimeoutMs);

		motorLeft2.follow(motorLeft1);
		motorRight2.follow(motorRight1);

		SmartDashboard.putNumber("setPoint", 1);

	}


	public void driveByPIDLoop(double valueToLockOn) {
		/* calculate the percent motor output */
		double motorOutput = motorLeft1.getMotorOutputPercent();

		/* prepare line to print */
		//		_sb.append("\tOut%:");
		//		_sb.append(motorOutput);
		//		_sb.append("\tVel:");
		//		_sb.append(motorLeft1.getSelectedSensorVelocity( kPIDLoopIdx));

		/* Motion Magic - 4096 ticks/rev * 10 Rotations in either direction */
		targetPos = valueToLockOn;
		motorLeft1.set(ControlMode.Position, targetPos);
		motorRight1.set(ControlMode.Position, -targetPos);

		/* append more signals to print when in speed mode. */
		//		_sb.append("\terr:");
		//		_sb.append(motorLeft1.getClosedLoopError( kPIDLoopIdx));
		//		_sb.append("\ttrg:");
		//		_sb.append(targetPos);
		//		} else {
		/* Percent voltage mode */
		//			_talon.set(ControlMode.PercentOutput, leftYstick);
		//		}

		/* instrumentation */
		Process(motorLeft1, _sb, valueToLockOn);
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (Exception e) {
		}
	}

	public static void Process(TalonSRX tal, StringBuilder sb, double target)
	{
		/* smart dash plots */
		SmartDashboard.putNumber("SensorVel", tal.getSelectedSensorVelocity(kPIDLoopIdx));
		SmartDashboard.putNumber("SensorPos", tal.getSelectedSensorPosition(kPIDLoopIdx));
		SmartDashboard.putNumber("MotorOutputPercent", tal.getMotorOutputPercent());
		SmartDashboard.putNumber("ClosedLoopError", tal.getClosedLoopError(kPIDLoopIdx));
		SmartDashboard.putNumber("ClosedLoopTarget", target);

		/* check if we are motion-magic-ing */
		if (tal.getControlMode() == ControlMode.MotionMagic) {
			++_timesInMotionMagic;
		} else {
			_timesInMotionMagic = 0;
		}
		if (_timesInMotionMagic > 10) {
			/* print the Active Trajectory Point Motion Magic is servoing towards */
			SmartDashboard.putNumber("ActTrajVelocity", tal.getActiveTrajectoryVelocity());
			SmartDashboard.putNumber("ActTrajPosition", tal.getActiveTrajectoryPosition());
			SmartDashboard.putNumber("ActTrajHeading", tal.getActiveTrajectoryHeading());
		}
		/* periodically print to console */
		if (++_loops >= 10) {
			_loops = 0;
			System.out.println(sb.toString());
		}
		/* clear line cache */
		sb.setLength(0);
	}

	public void MPInit() {
		mPLeft.startMotionProfile();
		mPRIGHT.startMotionProfile();

	}

	public void MPExec() {
		SetValueMotionProfile setOutputLeft = mPLeft.getSetValue();
		SetValueMotionProfile setOutputRight = mPRIGHT.getSetValue();
		motorLeft1.set(ControlMode.MotionProfile, setOutputLeft.value);
		motorRight1.set(ControlMode.MotionProfile, setOutputRight.value);
	}

	public void MPEnd() {
		mPLeft.reset();
		mPRIGHT.reset();
	}

	
	public void thisIsAPIDSetupLoopIThink() {
		configureDistancePID(motorLeft1);
		
		configureDistancePID(motorRight1);
		
	
	}
	
	private void configureDistancePID(TalonSRX _talon) {


		_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx,
				Constants.kTimeoutMs);

		/* choose to ensure sensor is positive when output is positive */
//		_talon.setSensorPhase(Constants.kSensorPhase);

		/* choose based on what direction you want forward/positive to be.
		 * This does not affect sensor phase. */ 
		_talon.setInverted(Constants.kMotorInvert);

		/* set the peak and nominal outputs, 12V means full */
		_talon.configNominalOutputForward(0, Constants.kTimeoutMs);
		_talon.configNominalOutputReverse(0, Constants.kTimeoutMs);
		_talon.configPeakOutputForward(1, Constants.kTimeoutMs);
		_talon.configPeakOutputReverse(-1, Constants.kTimeoutMs);
		/*
		 * set the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		_talon.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

		/* set closed loop gains in slot0, typically kF stays zero. */
		_talon.config_kF(Constants.kPIDLoopIdx, 0.0, Constants.kTimeoutMs);
		_talon.config_kP(Constants.kPIDLoopIdx, 5.0, Constants.kTimeoutMs);
		_talon.config_kI(Constants.kPIDLoopIdx, 0.0, Constants.kTimeoutMs);
		_talon.config_kD(Constants.kPIDLoopIdx, 0.0, Constants.kTimeoutMs);
		
//		/*
//		 * lets grab the 360 degree position of the MagEncoder's absolute
//		 * position, and intitally set the relative sensor to match.
//		 */
//		int absolutePosition = _talon.getSensorCollection().getPulseWidthPosition();
//		/* mask out overflows, keep bottom 12 bits */
//		absolutePosition &= 0xFFF;
//		if (Constants.kSensorPhase)
//			absolutePosition *= -1;
//		if (Constants.kMotorInvert)
//			absolutePosition *= -1;
//		/* set the quadrature (relative) sensor to match absolute */
//		_talon.setSelectedSensorPosition(absolutePosition, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
	}

	public void log() {
		//		PigeonIMU.GeneralStatus genStatus = new PigeonIMU.GeneralStatus();
		//		PigeonIMU.FusionStatus fusionStatus = new PigeonIMU.FusionStatus();
		//		double [] xyz_dps = new double [3];
		//		double[] accelerometer = new double [3];
		//		double [] _6dquaternion = new double [4];
		/* grab some input data from Pigeon and gamepad*/
		//		pid_geon.getGeneralStatus(genStatus);
		//		pid_geon.getRawGyro(xyz_dps);
		//		pid_geon.getFusedHeading(fusionStatus);
		//		pid_geon.getAccelerometerAngles(accelerometer);
		//		pid_geon.get6dQuaternion(_6dquaternion);
		//		double currentAngle = fusionStatus.heading;
		//		_currentAngle = currentAngle;
		//		boolean angleIsGood = (pid_geon.getState() == PigeonIMU.PigeonState.Ready) ? true : false;
		//		SmartDashboard.putNumberArray("_6dQuaternion", _6dquaternion);
		SmartDashboard.putNumber("Angle", getYaw());
//		SmartDashboard.putNumber("Yaw Rate", getYawRate());

//		SmartDashboard.putNumber("Encoder Right", this.getEncoderRight());
//		SmartDashboard.putNumber("Encoder Left", this.getEncoderLeft());
		
//		L.ogSD("Left Drive Error", this.motorLeft1.getClosedLoopError(kPIDLoopIdx));
//		L.ogSD("Right Drive Error", this.motorRight1.getClosedLoopError(kPIDLoopIdx));
//		L.ogSD("Left Drive Target", this.motorLeft1.getClosedLoopTarget(kPIDLoopIdx));
//		L.ogSD("Right Drive Target", this.motorRight1.getClosedLoopTarget(kPIDLoopIdx));

		
		
//		L.og("Left mode " + this.motorLeft1.getControlMode());
//		L.og("Right Mode" + this.motorRight1.getControlMode());
		
//		SmartDashboard.putNumber("Motor Left 1 Voltage", motorLeft1.getMotorOutputVoltage());
//		SmartDashboard.putNumber("Motor Left 2 Voltage", motorLeft2.getMotorOutputVoltage());
//		SmartDashboard.putNumber("Motor Right 1 Voltage", motorRight1.getMotorOutputVoltage());
//		SmartDashboard.putNumber("Motor Right 2 Voltage", motorRight2.getMotorOutputVoltage());

//		SmartDashboard.putBoolean("Left Floor Cube Sensor", leftFrontCubeSensor.get());
//		SmartDashboard.putBoolean("Right Floor Cube Sensor", rightFrontCubeSensor.get());

		//		L.og("LEFT enc: " + getEncoderLeft());
		//		L.og("RIGHT enc: " + getEncoderRight());

		currentT = t.get();

		//		System.out.println("{" + motorLeft1.getSensorCollection().getQuadraturePosition() + ",\t" + 
		//				motorLeft1.getSensorCollection().getQuadratureVelocity() + ",\t"  + (int)((currentT - lastT) * 1000 + 5) + "},\t" +
		//				"{" + motorRight1.getSensorCollection().getQuadraturePosition() + ",\t" + 
		//				motorRight1.getSensorCollection().getQuadratureVelocity() + ",\t"  + (int)((currentT - lastT) * 1000 + 5) + "}," );

		lastT = currentT;
	}



}