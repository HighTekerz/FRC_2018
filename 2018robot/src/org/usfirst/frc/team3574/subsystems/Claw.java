package org.usfirst.frc.team3574.subsystems;

import org.usfirst.frc.team3574.enums.ClawPosition;
import org.usfirst.frc.team3574.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Claw extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	Solenoid clawSolenoid = new Solenoid(RobotMap.ClawSolenoid);

	

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	public void setClawPosition(ClawPosition position) {
		switch(position) {
		case GRIP:
			clawSolenoid.set(false);
			break;
		case RELEASE:
			clawSolenoid.set(true);
			break;
		}
		
	}
}

