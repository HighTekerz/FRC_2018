package org.usfirst.frc.team3574.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Arm extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Solenoid Wrist = new Solenoid(RobotMap.WristSolenoid);
	TalonSRX ArmMotor = new TalonSRX(RobotMap.ArmMotor);

	public static final int AttentiveCobra = 1000;
	public static final int AggressiveCobra = 2000;
	public static final int DepressedCobra = 3000;
	public static final int DeadCobra = 4000;

	//TODO: tune this loop
	public final double kP = 1.0;
	public final double kI = 1.0;
	public final double kD = 1.0;
	public final int timeoutMs = 50;
	public boolean armDoneMoving = false;






	public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 10;
	public static boolean kSensorPhase = false;
	public static boolean kMotorInvert = false;


	public Arm() {

		ArmMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);

		ArmMotor.setSensorPhase(kSensorPhase);

		ArmMotor.setInverted(kMotorInvert);

		/* set the peak and nominal outputs, 12V means full */
		ArmMotor.configNominalOutputForward(0, kTimeoutMs);
		ArmMotor.configNominalOutputReverse(0, kTimeoutMs);
		ArmMotor.configPeakOutputForward(1, kTimeoutMs);
		ArmMotor.configPeakOutputReverse(-1, kTimeoutMs);
		ArmMotor.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);


		ArmMotor.config_kF(kPIDLoopIdx, 0.0, kTimeoutMs);
		ArmMotor.config_kP(kPIDLoopIdx, 0.7, kTimeoutMs);
		ArmMotor.config_kI(kPIDLoopIdx, 0.0, kTimeoutMs);
		ArmMotor.config_kD(kPIDLoopIdx, 0., kTimeoutMs);

		/*
		 * lets grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match.
		 */
		int absolutePosition = ArmMotor.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
		absolutePosition &= 0xFFF;
		if (kSensorPhase)
			absolutePosition *= -1;
		if (kMotorInvert)
			absolutePosition *= -1;
		/* set the quadrature (relative) sensor to match absolute */
		ArmMotor.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);

	}



	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand( MySpecialCommand());
	}


	public void setWristParallel(boolean Value) {
		Wrist.set(Value);
	}

	public void putTheArmSomewhere(int cobraForm) {
		System.out.println("Reached putTheArmSomeWhere");
		switch (cobraForm) {

		case (AttentiveCobra):
			assumeThePosition(cobraForm);
		break;

		case (AggressiveCobra):
			assumeThePosition(cobraForm);
		break;

		case (DepressedCobra):
			assumeThePosition(cobraForm);
		break;

		case (DeadCobra):
			assumeThePosition(cobraForm);
		break;

		default:
			System.out.println("Invalid arm position. Please fix your code. correct code looks like Arm.[The cobra position you want]");
			break;
		} 
	}

	private void assumeThePosition(int cobraPosition){
		ArmMotor.set(ControlMode.Position, cobraPosition);
	}

	/*
	 * Elbow
	 */


	/*
	 * 
	 */
}

