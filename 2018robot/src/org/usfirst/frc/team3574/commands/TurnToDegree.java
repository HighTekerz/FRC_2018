package org.usfirst.frc.team3574.commands;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToDegree extends Command {

	private double _degreeToTravel;
	private double _speed;

	public TurnToDegree(double degree, double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		_speed = speed;
		_degreeToTravel = degree;
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
			Robot.driveTrain.driveByArcade(0, _speed);
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
