package org.usfirst.frc.team3574.subsystems;

import org.usfirst.frc.team3574.commands.slide.ManualSlide;
import org.usfirst.frc.team3574.commands.slide.ResetEncIfAtLowestPoint;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Slide extends Subsystem {

	public TalonSRX slideSim = new TalonSRX(RobotMap.SlideMotor); 

	public static int scaleLow = 54;
	public static int scaleMed = 64;
	public static int scaleHigh = 75;
	public static int scaleHighWithCube = 85;
	public static int switchHeight = 5347;

	private static final int SPEED_TO_TOP = 87;
	private static final int TICKS_TO_TOP = 16732;
	private static final int TICKS_PER_REV = 1023;   //or 1024 or 4096
	
	private static final double P = ((SPEED_TO_TOP * TICKS_PER_REV) / TICKS_TO_TOP) * 2; //5.3192087
	private static final double I = 1;
	private static final double D = 0;
	private static final double F = 0;
	
	/**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */
	public static final int kSlotIdx = 0;

	public static final int kPIDLoopIdx = 0;

	public static final int kTimeoutMs = 100000;

	/* choose so that Talon does not report sensor out of phase */
	public static boolean kSensorPhase = false;

	/* choose based on what direction you want to be positive,
		this does not affect motor invert. */
	public static boolean kMotorInvert = false;	

	
//	I2C iTooCanSee = new I2C(Port.kOnboard, 0x13);

	public Slide() {
		slideSim.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
		
		slideSim.setSensorPhase(kSensorPhase);

		slideSim.setInverted(kMotorInvert);

		/* set the peak and nominal outputs, 12V means full */
		slideSim.configNominalOutputForward(0, kTimeoutMs);
		slideSim.configPeakOutputForward(3, kTimeoutMs);
		slideSim.configNominalOutputReverse(-0, kTimeoutMs);
		slideSim.configPeakOutputReverse(-3, kTimeoutMs);
		/*
		 * set the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		slideSim.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);
		
		/* set closed loop gains in slot0, typically kF stays zero. */
		slideSim.config_kP(kPIDLoopIdx, P, kTimeoutMs);
		slideSim.config_kI(kPIDLoopIdx, I, kTimeoutMs);
		slideSim.config_kD(kPIDLoopIdx, D, kTimeoutMs);
		slideSim.config_kF(kPIDLoopIdx, F, kTimeoutMs);
		
		/*
		 * lets grab the 360 degree position of the MagEncoder's absolute
		 * position, and initially set the relative sensor to match.
		 */
//		int absolutePosition = slideSim.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
//		absolutePosition &= 0xFFF;
//		if (kSensorPhase)
//			absolutePosition *= -1;
//		if (kMotorInvert)
//			absolutePosition *= -1;
		/* set the quadrature (relative) sensor to match absolute */
//		slideSim.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);

		
		
	}
	
	double set;
	public void setSlidePosition(double setPoint) {
		set = setPoint;
		slideSim.set(ControlMode.Position, set);
	}

	public void setSlideSpeed(double speed) {
		slideSim.set(ControlMode.PercentOutput, speed);
	}

	@Override
	protected void initDefaultCommand() {
		
	}

	public boolean getTopStopSwitchIsPressed() {
		return slideSim.getSensorCollection().isRevLimitSwitchClosed();
	}

	public boolean getBottomStopSwitchIsPressed() {
		return slideSim.getSensorCollection().isFwdLimitSwitchClosed();
	}

	public int getEncPos() {
		return slideSim.getSensorCollection().getPulseWidthPosition();
	}
	
	public void resetEnc() {
		slideSim.getSensorCollection().setPulseWidthPosition(0, 999999);
	}

	public void log () {
		SmartDashboard.putNumber("Slide Encoder", getEncPos());
		SmartDashboard.putBoolean("Top Slide Limit Switch", getTopStopSwitchIsPressed());
		SmartDashboard.putBoolean("Bottom Slide Limit Switch", getBottomStopSwitchIsPressed());
		SmartDashboard.putNumber("Absolute Position", slideSim.getSensorCollection().getPulseWidthPosition()/1000000.0);
		
		SmartDashboard.putNumber("setpoint", set);
	}
}