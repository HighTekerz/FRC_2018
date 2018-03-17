package org.usfirst.frc.team3574.commands.slide;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.utilities.SpeedSettingsWithoutCube;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HoldSlidePosition extends Command {

    public HoldSlidePosition() {
    	requires(Robot.slide);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.slide.setSlideSpeedPercent(new SpeedSettingsWithoutCube().brakeSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
