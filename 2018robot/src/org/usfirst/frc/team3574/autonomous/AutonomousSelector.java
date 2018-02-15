package org.usfirst.frc.team3574.autonomous;

import java.lang.reflect.Field;

import org.usfirst.frc.team3574.robot.FieldFunctions;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousSelector extends Command {

	private int step = 0;

	public AutonomousSelector() {

	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (step) {
		case 1: 
			if(FieldFunctions.getOurSide(FieldFunctions.FieldElementToCheck.OURSWITCH) == "Right") {
					
			}
			else {
				
			}
			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
