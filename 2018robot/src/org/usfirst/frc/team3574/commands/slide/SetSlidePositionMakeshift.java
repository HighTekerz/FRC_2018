package org.usfirst.frc.team3574.commands.slide;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.usfirst.frc.team3574.robot.Robot;
//import org.usfirst.frc.team3574.subsystems.Slide.LifterHeights;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetSlidePositionMakeshift extends Command {
	
	private double _setPosition;
	public double error;
	private double allowableError = 88;
	private double slowPoint = 4096;
	
	private double maxSpeed = 5.0;
	private double slowedSpeed = 0.5;
	private double brakeSpeed = 0.0;
	
	private int slideDirectionUp = 1;
	private int slideDirectionDown = -1;
	
	private boolean isFinished;
	
//	@Deprecated
    public SetSlidePositionMakeshift(double setPositionInInches) {
    	requires(Robot.slide);
    	_setPosition = setPositionInInches * Robot.slide.TicksPerInch;
    	
    }

    protected void initialize() {
    	isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	error = Robot.slide.getEncPos() - _setPosition;
    	
    	if(Math.abs(error) < allowableError) {
    		Robot.slide.setCurrent(brakeSpeed);
    		System.out.println("Running At: Brake Speed. error: " + error);
    		isFinished = true;
    	} 
    	else if(Math.abs(error) < slowPoint) {
    		Robot.slide.setCurrent(slowedSpeed * checkDirection());
    		System.out.println("Running At: Slowed Speed. error: " + error);
    	} 
    	else {
        	Robot.slide.setCurrent(maxSpeed * checkDirection());
    		System.out.println("Running At: Max Speed. error: " + error);
    	}
    }
    
    private int checkDirection() {
    	if(error > 0) {
			return slideDirectionDown;
		} else {
			return slideDirectionUp;
		}
    }
    
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println(Robot.slide.getEncPos());
		return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
