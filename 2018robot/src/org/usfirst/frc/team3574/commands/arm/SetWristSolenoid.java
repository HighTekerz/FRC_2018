package org.usfirst.frc.team3574.commands.arm;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetWristSolenoid extends Command {

    public SetWristSolenoid() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.setWristParallelToTheRestOfTheArmDeviceItIsAttachedTo(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arm.setWristParallelToTheRestOfTheArmDeviceItIsAttachedTo(false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Robot.arm.setWristParallelToTheRestOfTheArmDeviceItIsAttachedTo(false);
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.setWristParallelToTheRestOfTheArmDeviceItIsAttachedTo(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
