package org.usfirst.frc.team3574.commands.driveTrain;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToDegree extends Command {
	
	private double _degreeToReach;
	private double _oGDegreeToReach;

	private double _speed;

	private double _currentAngle;

	public TurnToDegree(double degree, double speed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		_speed = speed;
		_oGDegreeToReach = degree;
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
		_currentAngle = Robot.driveTrain._currentAngleToPass;
		
		_degreeToReach = _oGDegreeToReach + _currentAngle;
		if (_degreeToReach < _currentAngle) {
			_speed = -Math.abs(_speed);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {			
		// TODO: fix this so that the current angle is being populated
		
		_currentAngle = Robot.driveTrain._currentAngleToPass;

		Robot.driveTrain.driveByArcade(0, _speed);

		SmartDashboard.putNumber("Degree to Reach", _degreeToReach);
		SmartDashboard.putNumber("Degree", _currentAngle);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (_currentAngle > _degreeToReach -5 && _currentAngle < _degreeToReach +5) {
			Robot.driveTrain.driveByArcade(0, 0);
			System.out.println("TurnToDegree Is Finished at: " + _currentAngle);
			return true;
		}
		else {
			System.out.println("TurnToDegree Is NOT Finished at: " + _currentAngle);
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		_degreeToReach = _oGDegreeToReach;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
