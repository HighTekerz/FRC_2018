package org.usfirst.frc.team3574.commands.arm;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetClawPosition extends Command {

	private boolean _setClawOpen;
	
    public SetClawPosition(boolean setClawOpen) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	_setClawOpen = setClawOpen;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.setClawOpen(_setClawOpen);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arm.setClawOpen(_setClawOpen);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Robot.arm.setClawOpen(_setClawOpen);
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.setClawOpen(_setClawOpen);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
