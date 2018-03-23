package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.robot.FieldFunctions;
import org.usfirst.frc.team3574.robot.FieldFunctions.FieldElementToCheck;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousSelectorForScale extends Command {

	Command _command;
	String ourSwitchSide;
	boolean _isFinished;
	boolean _startingOnLeft;

	public AutonomousSelectorForScale(boolean startingOnLeft) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		_startingOnLeft = startingOnLeft;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		_isFinished = false;
		ourSwitchSide = FieldFunctions.getOurSide(FieldElementToCheck.SCALE);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Testing if command functions correctly
		if (ourSwitchSide.contains("Left")) {
			if (_startingOnLeft) {
				_command = new AutoPutCubeInScaleAhead(90);
			}
			else {
				_command = new AutoPutCubeInScaleAcross(90);
			}
		} //Lol hey sup
		else{
			if (_startingOnLeft) {
				_command = new AutoPutCubeInScaleAcross(-90);
			}
			else {
				_command = new AutoPutCubeInScaleAhead(-90);
			}
		}
		_command.start();
		_isFinished = true;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return _isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}