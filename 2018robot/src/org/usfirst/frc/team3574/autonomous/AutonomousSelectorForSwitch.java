package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree;
import org.usfirst.frc.team3574.robot.FieldFunctions;
import org.usfirst.frc.team3574.robot.FieldFunctions.FieldElementToCheck;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousSelectorForSwitch extends Command {

	Command _command;
	String ourSwitchSide;
	boolean _isFinished;
	
    public AutonomousSelectorForSwitch() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	_isFinished = false;
    	ourSwitchSide = FieldFunctions.getOurSide(FieldElementToCheck.THEIRSWITCH);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Testing if command functions correctly
    	int degree = 90;
    	if (ourSwitchSide.contains("Left")) {
    			_command = new AutoPutCubeInSwitch(-degree);
    	}
    	else{
			_command = new AutoPutCubeInSwitch(degree);
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