package org.usfirst.frc.team3574.commands.slide;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetSlideEnc extends Command {

    public ResetSlideEnc() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.slide.resetEnc();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		Robot.slide.resetEnc();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		Robot.slide.resetEnc();
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.slide.resetEnc();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		Robot.slide.resetEnc();
    }
}