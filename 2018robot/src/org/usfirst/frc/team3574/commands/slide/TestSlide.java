package org.usfirst.frc.team3574.commands.slide;

import org.usfirst.frc.team3574.commands.util.L;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestSlide extends Command {

    public TestSlide() {
    	requires(Robot.slide);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.slide.setSlideSpeed(0.3);
    	L.ogInit(this);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        L.ogExe(this);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	L.ogisFinished(this);
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	L.ogEnd(this);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	L.ogInterupt(this);
    }
}