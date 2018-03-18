package org.usfirst.frc.team3574.commands.driveTrain;

import org.usfirst.frc.team3574.commands.util.L;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EmmaRacingMode extends Command {

    public EmmaRacingMode() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	L.ogInit(this);
    	Robot.driveTrain.driveByArcadeWithModifiers(Robot.OperatorInput.getRightTrigger()-Robot.OperatorInput.getLeftTrigger(), Robot.OperatorInput.getRightStickX(), (Robot.OperatorInput.getDialAxis()+1)/2);
	
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
