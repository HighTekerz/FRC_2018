package org.usfirst.frc.team3574.commands.lifter;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetLifterToLift extends Command {

	boolean _pistonWillExtend;
	
    public SetLifterToLift(boolean pistonWillExtend) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lifter);
    	_pistonWillExtend = pistonWillExtend;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lifter.setLeftSolenoid(_pistonWillExtend);
    	Robot.lifter.setRightSolenoid(_pistonWillExtend);
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
    }
}
