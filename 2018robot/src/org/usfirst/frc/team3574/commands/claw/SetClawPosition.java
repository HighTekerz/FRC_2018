package org.usfirst.frc.team3574.commands.claw;

import org.usfirst.frc.team3574.enums.ClawPosition;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.UtilitySubsystem;
import org.usfirst.frc.team3574.utilities.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetClawPosition extends Command {

	private ClawPosition pos;

	public SetClawPosition(ClawPosition position) {
		requires(Robot.claw);
		pos = position;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		L.ogInit(this);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (pos == ClawPosition.RELEASE) UtilitySubsystem.armPositionPlacementForDropoff = 0;
		Robot.claw.setClawPosition(pos);
		System.out.println("SetClawPosition executes. going to: " + pos);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
    	L.ogisFinished(this);
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("SetClawPosition ends. Time Since Initialized: " + timeSinceInitialized());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
    	L.ogInterrupt(this);
	}
}