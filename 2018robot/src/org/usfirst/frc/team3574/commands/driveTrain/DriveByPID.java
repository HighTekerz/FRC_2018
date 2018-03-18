package org.usfirst.frc.team3574.commands.driveTrain;

import org.usfirst.frc.team3574.commands.util.L;
import org.usfirst.frc.team3574.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveByPID extends Command {

	public int _tickTargetLeft;
	public int _tickTargetRight;
	public int _tickTarget;
	public int _loopsPositioned = 0;
	
	public DriveByPID(int tickTarget) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
		_tickTarget = tickTarget;
	}
	
		public DriveByPID() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		_tickTargetRight = _tickTarget + Robot.driveTrain.getEncoderRight();
		_tickTargetLeft = _tickTarget + Robot.driveTrain.getEncoderLeft();
		Robot.driveTrain.prepareForMotionMagic();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrain.driveByPIDLoop(_tickTarget);
		Robot.driveTrain.driveByPIDLoop(5000);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		int thisNumber = 100;
		if(Robot.driveTrain.getEncoderLeft() >= _tickTargetLeft -thisNumber && Robot.driveTrain.getEncoderLeft() <= _tickTargetLeft +thisNumber 
		&& Robot.driveTrain.getEncoderRight() >= _tickTargetRight -thisNumber && Robot.driveTrain.getEncoderRight() <= _tickTargetRight +thisNumber) {
			if(_loopsPositioned >= 50) {
				return true;
			}
			else {
				_loopsPositioned++;
				return false;
			}
		} else {
			_loopsPositioned = 0;
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
    	L.ogEnd(this);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
    	L.ogInterrupt(this);
	}
}
