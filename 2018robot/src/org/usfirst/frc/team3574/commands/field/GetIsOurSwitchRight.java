package org.usfirst.frc.team3574.commands.field;

import org.usfirst.frc.team3574.autonomous.PlaceCubeInSwitchFromMiddle;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GetIsOurSwitchRight extends Command {

	private int intToMakeItRunTwice = 0;

	public GetIsOurSwitchRight() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (intToMakeItRunTwice == 1) {
			return true;
		}
		else {
			intToMakeItRunTwice++;
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
