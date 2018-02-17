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
	private boolean _targetElementIsSwitch;
	Command _command;

	/**
	 * Makes the robot do the correct autonomous
	 * 
	 * @param startPositionIsLeft boolean denoting whether the robot is starting in the left position. false means the robot is in the right starting position.
	 * @param targetElementIsSwitch boolean denoting whether the robot is aimed at the switch. False means the robot is aiming for the scale.
	 */
	public AutonomousSelector(boolean startPositionIsLeft, boolean targetElementIsSwitch) {
		_startPositionIsLeft = startPositionIsLeft;
		_targetElementIsSwitch = targetElementIsSwitch;
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
			if(_targetElementIsSwitch) {
				pickASwitchAuto(FieldFunctions.getOurSide(FieldFunctions.FieldElementToCheck.OURSWITCH) == "Left");
			}
			else {
				pickAScaleAuto(FieldFunctions.getOurSide(FieldFunctions.FieldElementToCheck.OURSWITCH) == "Left");
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

	/**
	 * Method to give _command a value when we are aiming for the switch
	 * 
	 * @param TargetSideIsLeft Boolean denoting if we are trying to put the cube in the left side
	 */
	private void pickASwitchAuto(boolean TargetSideIsLeft) {
		if (_startPositionIsLeft == TargetSideIsLeft) {
			_command = (new AutoPutCubeInSwitchAhead());
		}
		else {
			if(_startPositionIsLeft) {
				_command = (new AutoPutCubeInSwitchAcross(-90));
			}
			else {
				_command = (new AutoPutCubeInSwitchAcross(90));
			}
		}
	}

	/**
	 * Method to give _command a value when we are aiming for the scale
	 * 
	 * @param TargetSideIsLeft Boolean denoting if we are trying to put the cube in the left side
	 */
	private void pickAScaleAuto(boolean TargetSideIsLeft) {
		if (_startPositionIsLeft == TargetSideIsLeft) {
			if (_startPositionIsLeft) {
				_command = (new AutoPutCubeInScaleAhead(90));
			}
			else {
				_command = (new AutoPutCubeInScaleAhead(-90));
			}
		}
		else {
			if(_startPositionIsLeft) {
				_command = (new AutoPutCubeInScaleAcross(90));
			}
			else {
				_command = (new AutoPutCubeInScaleAcross(-90));
			}
		}
	}
}