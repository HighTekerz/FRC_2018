package org.usfirst.frc.team3574.autonomous;

import java.lang.reflect.Field;

import org.usfirst.frc.team3574.robot.FieldFunctions;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousSelector extends Command {

	private int _step;
	private boolean _startPositionIsLeft;
	private boolean _isFinished;
	Command _command;
	
	/**
	 * Makes the robot do the correct autonomous
	 * 
	 * @param startPositionIsLeft boolean denoting whether the robot is starting in the left position. false means the robot is in the right starting position.
	 */
	public AutonomousSelector(boolean startPositionIsLeft) {
		_startPositionIsLeft = startPositionIsLeft;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		_step = 1;
		_isFinished = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (_step) {
		case 1: 
			if(FieldFunctions.getOurSide(FieldFunctions.FieldElementToCheck.OURSWITCH) == "Right") {
				if (_startPositionIsLeft) {
					_command = (new AutoPutCubeInSwitchFromOtherSide(-90));
				}
				else {
					_command = (new AutoPutCubeInSwitchAhead());
				}
			}
			else if(FieldFunctions.getOurSide(FieldFunctions.FieldElementToCheck.OURSWITCH) == "Left"){
				if (!_startPositionIsLeft) {
					_command = (new AutoPutCubeInSwitchFromOtherSide(90));
				}
				else {
					_command = (new AutoPutCubeInSwitchAhead());
				}				
			}
			_command.start();
			_step++;
			break;
		case 2:
			_isFinished = true;
			break;
		}

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
