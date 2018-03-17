package org.usfirst.frc.team3574.subsystems;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.RobotMap;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
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

	public double maxSpeed = -.75; //TODO: This was -9.0
	public double slowedSpeed = -.25;
	public double brakeSpeed = -0.2;
	public double downmaxSpeed = .025;
	public double downslowedSpeed = -.01;

	public static double SLIDE_ZERO_POINT;
	
	public static int SLIDE_BOTTOM = 0;
	public static int SLIDE_HIGHER_INCREMENT = 4;
	public static int SLIDE_HIGHER_INCREMENT_SCALE = 6;
	public static int SLIDE_CARRY = 0;
	public static int SLIDE_SWITCH_DELIVERY = 6;
	public static int SLIDE_SCALE_LOW = 6;
	public static int SLIDE_START = 0;
	
	public static final double TicksPerRevolution = 4096;
	public static final double InchesPerRevolution = 5.875;
	public static final double TicksPerInch = (TicksPerRevolution / InchesPerRevolution); //697

	private static final int SPEED_TO_TOP = 87;
	private static final int TICKS_TO_TOP = 16732;
	private static final int TICKS_PER_REV = 4096;   //or 1024 or 4096
	
//	private static final double P = 0.05;//((SPEED_TO_TOP * TICKS_PER_REV) / TICKS_TO_TOP) * 2; //5.3192087
//	private static final double I = 0.000000001;
//	private static final double D = 0.0;
//	private static final double F = 1.0;
//	
//	/**
//	 * Which PID slot to pull gains from. Starting 2018, you can choose from
//	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
//	 * configuration.
//	 */
//	public static final int kSlotIdx = 0;
//
//	public static final int kPIDLoopIdx = 0;
//
//	public static final int kTimeoutMs = 10;
//
//	/* choose so that Talon does not report sensor out of phase */
//	public static boolean kSensorPhase = true;
//
//	/* choose based on what direction you want to be positive,
//		this does not affect motor invert. */
//	public static boolean kMotorInvert = false;	

	

	public Slide() {
		slideSim.set(ControlMode.Current, 0.0);
		
		
		
		
//		slideSim.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
//		slideSim.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
//		
//		slideSim.setSensorPhase(kSensorPhase);
//
//		slideSim.setInverted(kMotorInvert);
//
//		/* set the peak and nominal outputs, 12V means full */
//		slideSim.configNominalOutputForward(0.0, kTimeoutMs);
//		slideSim.configNominalOutputReverse(-0.0, kTimeoutMs);
//		slideSim.configPeakOutputForward(0.75, kTimeoutMs);
//		slideSim.configPeakOutputReverse(-0.75, kTimeoutMs);
//		/*
//		 * set the allowable closed-loop error, Closed-Loop output will be
//		 * neutral within this range. See Table in Section 17.2.1 for native
//		 * units per rotation.
//		 */
//		slideSim.configAllowableClosedloopError(kSlotIdx, (int)(Math.round(1 * TicksPerInch)), kTimeoutMs);
//		slideSim.configMaxIntegralAccumulator(kSlotIdx, 100, kTimeoutMs);
//		slideSim.config
		
//		/* set closed loop gains in slot0, typically kF stays zero. */
//		slideSim.config_kP(kPIDLoopIdx, P, kTimeoutMs);
//		slideSim.config_kI(kPIDLoopIdx, I, kTimeoutMs);
//		slideSim.config_kD(kPIDLoopIdx, D, kTimeoutMs);
//		slideSim.config_kF(kPIDLoopIdx, F, kTimeoutMs);
//	
//		/*
//		 * lets grab the 360 degree position of the MagEncoder's absolute
//		 * position, and initially set the relative sensor to match.
//		 */
//		
		
	}
	
//	public void updateEncTW() {
//		int absolutePosition = slideSim.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
//		absolutePosition &= 0xFFF;
//		if (kSensorPhase)
//			absolutePosition *= -1;
//		if (kMotorInvert)
//			absolutePosition *= -1;
		/* set the quadrature (relative) sensor to match absolute */
//		int absolutePosition = 50;
//		ErrorCode plsWork = slideSim.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);
//	}
	
//	int set;
//	public void setSlidePosition(int setPoint) {
//		set = setPoint;
//		slideSim.set(ControlMode.Position, set);
//	}

	public void setSlideSpeedPercent(double speed) {
		slideSim.set(ControlMode.PercentOutput, speed);
	}
	
	public void setCurrent(double current) {
		slideSim.set(ControlMode.Current, current);
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
		SLIDE_ZERO_POINT = Robot.slide.getEncPos();
	}

	public void log () {
		SmartDashboard.putNumber("Slide Encoder", getEncPos());
		SmartDashboard.putBoolean("Top Slide Limit Switch", getTopStopSwitchIsPressed());
		SmartDashboard.putBoolean("Bottom Slide Limit Switch", getBottomStopSwitchIsPressed());
		
		SmartDashboard.putNumber("Slide Zero Point (Not Really 0)", SLIDE_ZERO_POINT);
		
//		SmartDashboard.putNumber("ACTUAL ClosedLoopError", Math.round(1 * TicksPerInch));
//		SmartDashboard.putNumber("ACTUAL ClosedLoopError INTEGER", (int)(Math.round(1 * TicksPerInch)));
//		
//		
//		SmartDashboard.putNumber("SelectedSlideSensorSite", slideSim.getSelectedSensorPosition(kPIDLoopIdx));
//		SmartDashboard.putNumber("SelectedSlide Velocity", slideSim.getSelectedSensorVelocity(kPIDLoopIdx));
//		
//		SmartDashboard.putNumber("setpoint", set);
//		SmartDashboard.putNumber("CLE", slideSim.getClosedLoopError(kPIDLoopIdx));
//		SmartDashboard.putNumber("Target", slideSim.getClosedLoopTarget(kPIDLoopIdx));
//		SmartDashboard.putNumber(" I ", slideSim.getIntegralAccumulator(kPIDLoopIdx));
		
		
	}
}