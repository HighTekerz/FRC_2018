package org.usfirst.frc.team3574.commands.claw;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.ClawPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetClawPosition extends Command {
	
	private ClawPosition pos;

	public SetClawPosition(ClawPosition position) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.claw);

		this.pos = position;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.claw.setClawPosition(this.pos);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {    
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (timeSinceInitialized() > 0.10) {

			return true;
		}
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
