package org.usfirst.frc.team3574.commands.slideDEPRICATED;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SlideByInches extends Command {

	private int _TicksToSlide;
	private double _currentPosition;
	
	@Deprecated
	public SlideByInches(double inchesToSlide) {
		requires(Robot.slide);
//		_TicksToSlide = (int) (inchesToSlide * Robot.slide.TicksPerInch);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		//    	Robot.slide.setSlidePosition(_TicksToSlide);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		Robot.slide.setSlidePosition(_TicksToSlide);
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
		System.out.println("SlideByInches Tags out");
	}
}
