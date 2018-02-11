package org.usfirst.frc.team3574.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
	Solenoid Claw = new Solenoid(RobotMap.ClawSolenoid);
	Solenoid Wrist = new Solenoid(RobotMap.WristSolenoid);
	TalonSRX ArmMotor = new TalonSRX(RobotMap.ArmMotor);

	public static final int AttentiveCobra = 1;
	public static final int AggressiveCobra = 2;
	public static final int DepressedCobra = 3;
	public static final int DeadCobra = 4;
	public static final int armMotor = RobotMap.ArmMotor; 
	//remember, this one is lowercaseUppercase. UppercaseUppercase is the object.
	public static final int slotIdx = RobotMap.ArmMotor;
	//these are not the correct constant values, just placeholders.
	public final double kP = 1.0;
	public final double kI = 1.0;
	public final double kD = 1.0;
	public final int timeoutMs = 50;
	public boolean armDoneMoving = false;

	
//	DigitalInput leftCubeSensor = new DigitalInput(1);	
//	DigitalInput rightCubeSensor = new DigitalInput(2);

	public Arm() {
		ArmMotor.config_kP(slotIdx, kP, timeoutMs);
		ArmMotor.config_kI(slotIdx, kI, timeoutMs);
		ArmMotor.config_kD(slotIdx, kD, timeoutMs);

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand( MySpecialCommand());
	}

	public void setClawOpen(boolean Value) {
		Claw.set(Value);
	}

	public void setWristParallelToTheRestOfTheArmDeviceItIsAttachedTo(boolean Value) {
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
			System.out.println("Invalid arm position. Please fix your code.");
			break;
		} 
	}

	private void assumeThePosition(int cobraPosition){
		ArmMotor.set(ControlMode.Position, cobraPosition);
	}

	public boolean areBothSensorsTripped() {
//		return leftCubeSensor.get() && rightCubeSensor.get();
		return false;
	}

	/*
	 * Elbow
	 */


	/*
	 * 
	 */
}

