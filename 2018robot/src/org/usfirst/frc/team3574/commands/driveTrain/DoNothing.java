package org.usfirst.frc.team3574.commands.driveTrain;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DoNothing extends Command {

	double _timeout;
	boolean isTimeout;
	
    public DoNothing() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }
    
    public DoNothing(double timeout) {
    	_timeout = timeout;
    	isTimeout = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.doNothing();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.doNothing();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Robot.driveTrain.doNothing();
    	if(isTimeout) {
    		if(timeSinceInitialized() >= _timeout) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	else {
        return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.doNothing();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.doNothing();
    }
}
