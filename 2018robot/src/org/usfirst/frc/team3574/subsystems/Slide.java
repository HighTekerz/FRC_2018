package org.usfirst.frc.team3574.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Lifter extends Subsystem {

	TalonSRX liftSim = new TalonSRX(RobotMap.LiftMotor); 
	final int slotIdx = 0;
	final int timeoutMs = 50;
	final double kP = 1.0;
	final double kI = 1.0;
	final double kD = 1.0;

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

	/* choose so that Talon does not report sensor out of phase */
	public static boolean kSensorPhase = false;

	/* choose based on what direction you want to be positive,
		this does not affect motor invert. */
	public static boolean kMotorInvert = false;

	public static int scaleLow = 54;
	public static int scaleMed = 64;
	public static int scaleHigh = 75;
	public static int scaleHighWithCube = 85;
	public static int switchHeight = 5347;

	public Lifter() {
		liftSim.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);

		liftSim.setSensorPhase(kSensorPhase);


		liftSim.setInverted(kMotorInvert);

		/* set the peak and nominal outputs, 12V means full */
		liftSim.configNominalOutputForward(0, kTimeoutMs);
		liftSim.configNominalOutputReverse(0, kTimeoutMs);
		liftSim.configPeakOutputForward(1, kTimeoutMs);
		liftSim.configPeakOutputReverse(-1, kTimeoutMs);
		/*
		 * set the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		liftSim.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);

		/* set closed loop gains in slot0, typically kF stays zero. */
		liftSim.config_kF(kPIDLoopIdx, 0.0, kTimeoutMs);
		liftSim.config_kP(kPIDLoopIdx, 0.7, kTimeoutMs);
		liftSim.config_kI(kPIDLoopIdx, 0.0, kTimeoutMs);
		liftSim.config_kD(kPIDLoopIdx, 0., kTimeoutMs);

		/*
		 * lets grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match.
		 */
		int absolutePosition = liftSim.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
		absolutePosition &= 0xFFF;
		if (kSensorPhase)
			absolutePosition *= -1;
		if (kMotorInvert)
			absolutePosition *= -1;
		/* set the quadrature (relative) sensor to match absolute */
		liftSim.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);
		
	}

	public void setLifterPosition(int setPoint) {
		liftSim.set(ControlMode.Position, setPoint);
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void log () {
		
		SmartDashboard.putNumber("Absolute Position", liftSim.getSensorCollection().getPulseWidthPosition()/1000000.0
			);
	}
}

