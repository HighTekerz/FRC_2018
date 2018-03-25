package org.usfirst.frc.team3574.commands.driveTrain;

import org.usfirst.frc.team3574.commands.util.L;
import org.usfirst.frc.team3574.enums.ShifterPosition;
import org.usfirst.frc.team3574.robot.Robot;
//import org.usfirst.frc.team3574.subsystems.DriveTrain;
import org.usfirst.frc.team3574.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveByInches extends Command {
	
	private double _ticksToTravel;
	private double _speed;
	private ShifterPosition _shifterPosition;
	private boolean _isShifting;
	
	private double _finalTickTargetLeft;
	private double _finalTickTargetRight;
	
	private double targetAngleToKeep;
	
	private double timeout = 3;
	//TODO: turn this ^^ into a calculation
	
	double startLoc;
	
    public DriveByInches(double inchesToTravel, double speed, ShifterPosition ShifterPosition) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
//    	4096 is the number of ticks per revolution
    	if (inchesToTravel < 0)
    	{
    		_speed = -speed;
    	}
    	else
    	{
    		_speed = speed;
    	}
    	inchesToTravel = Math.abs(inchesToTravel);
//    	217.2995489 is our ticks per inch
	
    	_ticksToTravel = inchesToTravel * 217.2995489;
    	_shifterPosition = ShifterPosition;
    	_isShifting = true;
    	requires(Robot.driveTrain);
    }

    public DriveByInches(double inchesToTravel, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
//    	4096 is the number of ticks per revolution
    	if (inchesToTravel < 0)
    	{
    		_speed = -speed;
    	}
    	else
    	{
    		_speed = speed;
    	}
    	inchesToTravel = Math.abs(inchesToTravel);
//    	217.2995489 is our ticks per inch
	
    	_ticksToTravel = inchesToTravel * DriveTrain.ticksToInch;
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
   
    protected void initialize() {
    	targetAngleToKeep = Robot.driveTrain.getYaw();
    	L.ogInit(this);
    	
    	startLoc = Robot.driveTrain.getEncoderLeft();
    	
    	System.out.println("DriveByInches speed: " + _speed);
    	if (_speed < 0) {
    		_finalTickTargetLeft = Robot.driveTrain.getEncoderLeft() + _ticksToTravel;
    		_finalTickTargetRight = Robot.driveTrain.getEncoderRight() + _ticksToTravel;
    	}
    	else {
    		_finalTickTargetLeft = Robot.driveTrain.getEncoderLeft() - _ticksToTravel;
    		_finalTickTargetRight = Robot.driveTrain.getEncoderRight() - _ticksToTravel;
    	}
    	
    	Timer timer = new Timer();
    	if (_isShifting) {
    		timer.start();
    		Robot.driveTrain.ShiftGear(_shifterPosition);
    		while(timer.get() < 0.05) {
    		}
    	}
    	
//    	System.out.printf("_finalTickTargetLeft: %f  \t Robot.driveTrain.getEncoderLeft() : %d\n", _finalTickTargetLeft,  Robot.driveTrain.getEncoderLeft());
    	SmartDashboard.putNumber("Final Tick Target Right", _finalTickTargetRight);
    	SmartDashboard.putNumber("Final Tick Target Left", _finalTickTargetLeft);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	L.og("distance travelled: " + (Robot.driveTrain.getEncoderLeft() - startLoc) / 217.2995489 + " ---- Time: " + timeSinceInitialized());
    	
    	Robot.driveTrain.driveStraightByArcade(_speed, 0, targetAngleToKeep);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (_speed < 0 && Robot.driveTrain.getEncoderLeft() >= _finalTickTargetLeft &&
    			Robot.driveTrain.getEncoderRight() >= _finalTickTargetRight) {
//    		System.out.println("Speed is positive DriveByInches Returns True at " + Robot.driveTrain.getEncoderLeft() + " Right = " + Robot.driveTrain.getEncoderRight() + " Tick Targets: " + _finalTickTargetLeft + " & " + _finalTickTargetRight);
    		return true;
    	}
    	else if (_speed > 0 && Robot.driveTrain.getEncoderLeft() <= _finalTickTargetLeft &&
    			Robot.driveTrain.getEncoderRight() <= _finalTickTargetRight) {
//    		System.out.println("Speed is negative DriveByInches Returns True at " + Robot.driveTrain.getEncoderLeft() + " Right = " + Robot.driveTrain.getEncoderRight() + " Tick Targets: " + _finalTickTargetLeft + " & " + _finalTickTargetRight);
    		return true;
    	}
    	else {
//        	if(timeSinceInitialized() >= timeout) {
//        		return true;	
//        	}
    		System.out.println("3 DriveByInches Returns False at " + Robot.driveTrain.getEncoderLeft() + " Right = " + Robot.driveTrain.getEncoderRight() + " Tick Targets: " + _finalTickTargetLeft + " & " + _finalTickTargetRight);
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
    	Robot.driveTrain.driveByArcade(0, 0);
    }
}