package org.usfirst.frc.team3574.commands.arm;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetWristParallel extends Command {
	
	private boolean _setWristParallel;
	
    public SetWristParallel(boolean setWristParallel) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	_setWristParallel = setWristParallel;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("SetWristParallel Initialized");
    	Robot.arm.setWristParallelToTheRestOfTheArmDeviceItIsAttachedTo(_setWristParallel);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arm.setWristParallelToTheRestOfTheArmDeviceItIsAttachedTo(_setWristParallel);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Robot.arm.setWristParallelToTheRestOfTheArmDeviceItIsAttachedTo(_setWristParallel);
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.setWristParallelToTheRestOfTheArmDeviceItIsAttachedTo(_setWristParallel);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
