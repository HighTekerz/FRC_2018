package org.usfirst.frc.team3574.commands.arm;

import org.usfirst.frc.team3574.enums.BrakePosition;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualArm extends Command {

	private double deadzone = .15;

	public ManualArm() {
	requires(Robot.arm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() { 
		if (Math.abs(Robot.OperatorInput.CoPilotLeftStickY()) <= deadzone) {
			Robot.arm.setBrakePosition(BrakePosition.CLOSED);
		}
		else {
			Robot.arm.setBrakePosition(BrakePosition.OPEN);
			Robot.arm.setSpeed(Robot.driveTrain.scalingSpeed(Robot.OperatorInput.CoPilotLeftStickY(), 0.5));
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
