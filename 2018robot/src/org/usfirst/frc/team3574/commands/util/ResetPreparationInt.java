package org.usfirst.frc.team3574.commands.util;

import org.usfirst.frc.team3574.subsystems.UtilitySubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetPreparationInt extends Command {

	public ResetPreparationInt() {
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		L.ogInit(this);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		UtilitySubsystem.armPositionPlacementForDropoff = 0;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
