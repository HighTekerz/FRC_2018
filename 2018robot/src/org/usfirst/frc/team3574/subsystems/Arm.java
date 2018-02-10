package org.usfirst.frc.team3574.subsystems;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

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
	
	public Arm() {
		
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
		switch (cobraForm) {
		
		case (1):
			 assumeThePosition();
			break;
		
		case (2):
			 assumeThePosition();
			break;
		
		case (3):
			 assumeThePosition();
			break;
		
		case (4):
			 assumeThePosition();
			break;
		} 
	}
	
	public void assumeThePosition(){
	}
}

