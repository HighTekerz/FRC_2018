package org.usfirst.frc.team3574.commands.arm;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.subsystems.WristPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetWristPosition extends Command {
	
	private WristPosition _wristPosition;
	
    public SetWristPosition(WristPosition wristPosition) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm);
    	_wristPosition = wristPosition;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("setWristPosition Initialized");
    	Robot.arm.setWristPosition(_wristPosition);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arm.setWristPosition(_wristPosition);
    	System.out.println("Wrist parallel: " + _wristPosition);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Robot.arm.setWristPosition(_wristPosition);
    	System.out.println("Wrist things returns true,");
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("setWristPosition Is Finished. time since initialized: " + timeSinceInitialized());
    	Robot.arm.setWristPosition(_wristPosition);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("uh oh! wrist has been interrupted.");
    }
}
