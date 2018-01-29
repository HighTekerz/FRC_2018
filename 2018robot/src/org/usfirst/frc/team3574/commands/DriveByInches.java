package org.usfirst.frc.team3574.commands;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveByInches extends Command {
	
	private double _ticksToTravel;
	private double _speed;
	
	private double _finalTickTargetLeft;
	private double _finalTickTargetRight;
	
	
    public DriveByInches(double inchesToTravel, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
//    	4096 is the number of ticks per revolution
//    	217.2995489 if our ticks per inch
    	if (inchesToTravel < 0)
    	{
    		_speed = -speed;
    	}
    	else
    	{
    		_speed = speed;
    	}
    	inchesToTravel = Math.abs(inchesToTravel);
    			
    	_ticksToTravel = inchesToTravel * 217.2995489;
    	
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	
    	if (_speed > 0) {
    		_finalTickTargetLeft = Robot.driveTrain.getEncoderLeft() + _ticksToTravel;
    		_finalTickTargetRight = Robot.driveTrain.getEncoderRight() + _ticksToTravel;
    	}
    	else {
    		_finalTickTargetLeft = Robot.driveTrain.getEncoderLeft() - _ticksToTravel;
    		_finalTickTargetRight = Robot.driveTrain.getEncoderRight() - _ticksToTravel;
    	}
    	
    	System.out.printf("_finalTickTargetLeft: %f  \t Robot.driveTrain.getEncoderLeft() : %d\n", _finalTickTargetLeft,  Robot.driveTrain.getEncoderLeft());
    	SmartDashboard.putNumber("Final Tick Target Right", _finalTickTargetRight);
    	SmartDashboard.putNumber("Final Tick Target Left", _finalTickTargetLeft);
    	System.out.println("DriveByInches initialize");

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.driveByArcade(_speed, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	System.out.println("DriveByInches isFinished");
    	
    	if (_speed > 0 &&Robot.driveTrain.getEncoderLeft() >= _finalTickTargetLeft &&
    			Robot.driveTrain.getEncoderRight() >= _finalTickTargetRight)
    	
    	{
    		System.out.println("DriveByInches Returns True");
    		return true;
    	}
    	else if (_speed < 0 && Robot.driveTrain.getEncoderLeft() <= _finalTickTargetLeft 
    			&& Robot.driveTrain.getEncoderRight() <= _finalTickTargetRight) {
    		return true;
    	}
    	else {
    		System.out.println("DriveByInches Returns False");
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.driveByArcade(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
