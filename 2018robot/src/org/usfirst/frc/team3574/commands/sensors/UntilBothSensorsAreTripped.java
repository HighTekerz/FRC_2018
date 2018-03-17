package org.usfirst.frc.team3574.commands.sensors;

import org.usfirst.frc.team3574.commands.util.L;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UntilBothSensorsAreTripped extends Command {

    public UntilBothSensorsAreTripped() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println(">>>>UntilBothSensorsAreTripped Initializes");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.areBothFrontSensorsTripped();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
