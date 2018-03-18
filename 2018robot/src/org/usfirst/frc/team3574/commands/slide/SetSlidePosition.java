package org.usfirst.frc.team3574.commands.slide;

import org.usfirst.frc.team3574.robot.Robot;
//import org.usfirst.frc.team3574.subsystems.Slide.LifterHeights;
import org.usfirst.frc.team3574.subsystems.Slide;

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
    	
//    	System.out.println("Set Slide COnstructor");
//    	System.out.println(Robot.slide.getEncPos());
//    	System.out.println(setPositionInInches);
    	
    	_targetPositionInInches = setPositionInInches;
    	
//    	System.out.println(Robot.slide.getEncPos());
//    	System.out.println(_targetPosition);
    }

    protected void initialize() {
//    	System.out.println("Set Slide Initialize");
//    	System.out.println("encoder position: " + Robot.slide.getEncPos());
//    	System.out.println("setPositionInches: " + _targetPositionInInches);
    	
    	isFinished = false;
    	
//    	_targetPosition = Robot.slide.getEncPos() - (_targetPositionInInches * Slide.TicksPerInch); //sets position above 0
    	_targetPosition = Slide.SLIDE_ZERO_POINT - (_targetPositionInInches * Slide.TicksPerInch);
    	
//    	System.out.println("SetPosition: " +_targetPosition);
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
//    	System.out.println(Robot.slide.getEncPos());
		return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.slide.setCurrent(0.0);	
    }
}