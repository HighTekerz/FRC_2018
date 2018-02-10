package org.usfirst.frc.team3574.commands.lifter;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.Lifter.LifterHeights;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetLifterPosition extends Command {

	private LifterHeights setPosition; 
    public void SetLifterPosition(LifterHeights setPosition) {
    	
    	this.setPosition = setPosition;
     requires(Robot.lifter);
    }

    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.lifter.setToSETMotorValues(setPosition);
    
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
