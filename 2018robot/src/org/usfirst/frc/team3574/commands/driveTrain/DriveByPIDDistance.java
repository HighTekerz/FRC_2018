package org.usfirst.frc.team3574.commands.driveTrain;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.DriveTrain;
import org.usfirst.frc.team3574.utilities.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveByPIDDistance extends Command {

	public double _tickTarget;


	public DriveByPIDDistance(double distanceToDrive) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
		_tickTarget = distanceToDrive * DriveTrain.ticksToInch ;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.thisIsAPIDSetupLoopIThink();
		Robot.driveTrain.setPIDDistance((int)_tickTarget);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
//		if( (Mat) {
//			
//		} else {
			return false;
//		}
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
