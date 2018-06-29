package org.usfirst.frc.team3574.commands.driveTrain;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.utilities.L;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToDegree3 extends Command {
	
	private double relativeDegreeToReach;
	private double _oGDegreeToReach;

	private double _speed;

	private double _currentAngle;
	
	private boolean turnLeft;
	private boolean isFinished = false;

	public TurnToDegree3(double targetDegree, double speed) {
		requires(Robot.driveTrain);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		
		_speed = speed;
		_oGDegreeToReach = targetDegree;
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		isFinished = false;
		L.ogInit(this);

		relativeDegreeToReach = _oGDegreeToReach + _currentAngle;
			
		_speed = -Math.abs(_speed);

		
		_currentAngle = Robot.driveTrain.getYaw();
		relativeDegreeToReach = _oGDegreeToReach + _currentAngle;
		
//		checkAndSetDirectionToTurn(_oGDegreeToReach);
//		Robot.driveTrain.driveByArcade(0, _speed);

	}
	

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {			
		// TODO: fix this so that the current angle is being populated
	
		_currentAngle = Robot.driveTrain.getYaw();

		SmartDashboard.putNumber("Degree to Reach", relativeDegreeToReach);
		L.og("angle " + _currentAngle);
		}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
	
		if ( (relativeDegreeToReach - 1.5) > _currentAngle) {
			System.out.println("TurnToDegree Is NOT Finished at: " + _currentAngle);
			Robot.driveTrain.driveByArcade(0, _speed);
	
			return false;
		} else if( (relativeDegreeToReach + 1.5) < _currentAngle){
			
			System.out.println("TurnToDegree Is REVERSING at: " + _currentAngle);
			Robot.driveTrain.driveByArcade(0, -_speed);
			
			return false;
			
		} else {
			Robot.driveTrain.driveByArcade(0, 0);
			System.out.println("TurnToDegree Is Finished at: " + _currentAngle);
			
			return true;

		}
	}


	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
    	L.ogInterrupt(this);
	}
	
	protected void checkAndSetDirectionToTurn(double targetDegree) {
		relativeDegreeToReach = _oGDegreeToReach + _currentAngle;
	
		if (relativeDegreeToReach > _currentAngle) {
			_speed = -Math.abs(_speed);
			turnLeft = true;
		}
		else if(relativeDegreeToReach < _currentAngle) {
			turnLeft = false;
		}
		else {
			L.og("If this runs, the robot has exploded... Or the programmers dont know what they are doing..."
					+ "\n Probably the former though...");
		}
	}
}