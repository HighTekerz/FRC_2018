package org.usfirst.frc.team3574.commands.lifter;

import org.usfirst.frc.team3574.robot.Robot;
//import org.usfirst.frc.team3574.subsystems.Lifter.LifterHeights;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetLifterPosition extends Command {
	private int _setPosition;

//	private LifterHeights setPosition; 
    public SetLifterPosition(int setPosition) {
    	
    	_setPosition = setPosition;
     requires(Robot.lifter);
    }

    protected void initialize() {
    	System.out.println("SetLifterPosition Initialized");
    	Robot.lifter.setLifterPosition(_setPosition);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("SetLifterPosition Is Finished");
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
