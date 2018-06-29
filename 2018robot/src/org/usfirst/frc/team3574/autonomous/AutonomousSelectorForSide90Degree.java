package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.robot.FieldFunctions;
import org.usfirst.frc.team3574.robot.FieldFunctions.FieldElementToCheck;
import org.usfirst.frc.team3574.utilities.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousSelectorForSide90Degree extends Command {

	Command _command;
	String ourSwitchSide;
	String ourScaleSide;
	boolean _isFinished;
	double left = 1.10;
	double right = -1;
	String _startSide;

	/**
	 * Command to determine the side "our side" of the switch and scale are, then in order, check the scale then switch. It selects the command that will place the cube in the first one it sees as ours. if none, then drives forward. this command is built for the robot to turn 90 degrees and drive into the scale.
	 * 
	 * @param startSide Which side of the field we put the robot. "Left" or "Right"
	 */
	public AutonomousSelectorForSide90Degree(String startSide) {
		_startSide = startSide;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		_command = null;
		_isFinished = false;
		ourSwitchSide = FieldFunctions.getOurSide(FieldElementToCheck.OURSWITCH);
		ourScaleSide = FieldFunctions.getOurSide(FieldElementToCheck.SCALE);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (_startSide.contains("Right")){
			if(ourScaleSide.contains("Right")) {
				L.og("Scale on our side (right) Auto");
				_command = new AutonomousSideScale90Degree(1);
			}
			else if(ourSwitchSide.contains("Right")){
				L.og("Switch on our side (right) Auto");
				_command = new AutonomousSideSwitch(1);
			}    		
			else {
				_command = new DriveForwardAutonomous();
			}
		}
		else if (_startSide.contains("Left")) {
			if(ourScaleSide.contains("Left")) {
				L.og("Scale on our side (left) Auto");
				_command = new AutonomousSideScale90Degree(-1);
			}
			else if(ourSwitchSide.contains("Left")){
				L.og("Switch on our side (left) Auto");
				_command = new AutonomousSideSwitch(-1);
			}
			else {
				_command = new DriveForwardAutonomous();
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