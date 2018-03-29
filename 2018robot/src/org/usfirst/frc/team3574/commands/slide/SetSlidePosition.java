package org.usfirst.frc.team3574.commands.slide;

import org.usfirst.frc.team3574.robot.Robot;
//import org.usfirst.frc.team3574.subsystems.Slide.LifterHeights;
import org.usfirst.frc.team3574.subsystems.Slide;
import org.usfirst.frc.team3574.utilities.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetSlidePosition extends Command {

	private double _targetPosition;
	private double _targetPositionInInches;
	public double error;
	private double allowableError = 88;
	private double slowPoint = 2096;

	private boolean isFinished;

	public SetSlidePosition(double setPositionInInches) {
		requires(Robot.slide);
		_targetPositionInInches = setPositionInInches;
	}

	protected void initialize() {
		L.ogInit(this);
		isFinished = false;    	
		_targetPosition = Slide.SLIDE_ZERO_POINT - (_targetPositionInInches * Slide.TicksPerInch);    	
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		error = Robot.slide.getEncPos() - _targetPosition;

		if(Math.abs(error) < allowableError) {
			Robot.slide.setCurrent(Robot.slide.brakeSpeed);
			System.out.println("Slide Running At: Brake Speed. error: " + error);
			isFinished = true;
		} 
		else if(Math.abs(error) < slowPoint) {
			setSpeedDirection(1);
		} 
		else {
			setSpeedDirection(2);
		}
	}

	private void setSpeedDirection(int step) { 
		double xSpeed;
		boolean DriveUp;
		if(error < 0) {
			DriveUp = false;
		}
		else {
			DriveUp = true;
		}

		if(step == 1) {  //Going to Slow Speed
			if (DriveUp) {
				System.out.println("Slide Running At: Slowed Speed UP. error: " + error);
				xSpeed = Robot.slide.slowedSpeed;
			}
			else {
				System.out.println("Slide Running At: Slowed Speed DOWN. error: " + error);
				xSpeed = Robot.slide.downslowedSpeed;
			}
		}
		else {
			if (DriveUp) {
				System.out.println("Slide Running At: Max Speed UP. error: " + error);
				xSpeed = Robot.slide.maxSpeed;
			}
			else {
				System.out.println("Running Max Speed DOWN. error: " + error);
				xSpeed = Robot.slide.downmaxSpeed;
			}

		}

		Robot.slide.setSlideSpeedPercent(xSpeed);
		//    	Robot.slide.setCurrent(xSpeed);
	}


	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.slide.getTopStopSwitchIsPressed())
		{
			return true;
		}
		return isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		L.ogEnd(this);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		L.ogInterrupt(this);
		Robot.slide.setCurrent(0.0);	
	}
}