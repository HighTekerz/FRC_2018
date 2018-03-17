package org.usfirst.frc.team3574.commands.arm;

import org.usfirst.frc.team3574.commands.util.L;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CalibrateArmEncStartingPosition extends Command {

    public CalibrateArmEncStartingPosition() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	L.ogInit(this);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arm.calibrateArmEncoderFromStarting();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
