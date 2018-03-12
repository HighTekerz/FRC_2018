package org.usfirst.frc.team3574.commands.slide;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SlideByInches extends Command {

	private double _TicksToSlide;
	
	public static final double TicksPerRevolution = 4096;
	public static final double InchesPerRevolution = 5.875;
	public static final double TicksPerInch = TicksPerRevolution / InchesPerRevolution;
	
    public SlideByInches(double inchesToSlide) {
        requires(Robot.slide);
    	_TicksToSlide = inchesToSlide * TicksPerInch;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.slide.setSlidePosition(_TicksToSlide);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	Robot.slide.setSlidePosition(_TicksToSlide);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
