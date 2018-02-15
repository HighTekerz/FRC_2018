package org.usfirst.frc.team3574.commands.slider;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.usfirst.frc.team3574.robot.Robot;
//import org.usfirst.frc.team3574.subsystems.Lifter.LifterHeights;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetSliderPosition extends Command {
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
	
	private int _setPosition;

//	private LifterHeights setPosition; 
    public SetSliderPosition(int setPosition) {
    	
    	_setPosition = setPosition;
     requires(Robot.slider);
    }

    protected void initialize() {
    	System.out.println("SetLifterPosition Initialized at " + dateFormat.format(new Date()));
    	Robot.slider.setLifterPosition(_setPosition);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("*elevator music*");

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("SetSliderPosition Is Finished");
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("elevator is done now. time since elevator initialized: " + timeSinceInitialized());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("The elevator has been interrupted. Uh oh.");
    }
}
