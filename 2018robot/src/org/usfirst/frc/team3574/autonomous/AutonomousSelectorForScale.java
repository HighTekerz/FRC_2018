package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.robot.FieldFunctions;
import org.usfirst.frc.team3574.robot.FieldFunctions.FieldElementToCheck;
import org.usfirst.frc.team3574.utilities.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousSelectorForScale extends Command {

	Command _command;
	String ourScaleSide;
	boolean _isFinished;
	boolean _startingOnLeft;

	public AutonomousSelectorForScale(boolean startingOnLeft) {
		_startingOnLeft = startingOnLeft;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		_isFinished = false;
		ourScaleSide = FieldFunctions.getOurSide(FieldElementToCheck.SCALE);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (ourScaleSide.contains("Left")) {
			if (_startingOnLeft) {
				L.og("Going Left to Left");
				_command = new AutonomousSideScale45Degree(1);
			}
			else {
				L.og("Going Right to Left");
				_command = new AutoScaleAcross45Degree(1);
			}
		} //Lol hey sup
		else{
			if (_startingOnLeft) {
				L.og("Going Left to Right");
				_command = new AutoScaleAcross45Degree(-1);
			}
			else {
				L.og("Going Right to Right");
				_command = new AutonomousSideScale45Degree(-1);
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