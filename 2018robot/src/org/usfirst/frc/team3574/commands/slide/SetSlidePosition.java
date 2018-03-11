package org.usfirst.frc.team3574.commands.slide;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.usfirst.frc.team3574.robot.Robot;
//import org.usfirst.frc.team3574.subsystems.Slide.LifterHeights;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetSlidePosition extends Command {
	
//	SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
	
	private int _setPosition;

    public SetSlidePosition(int setPosition) {
    	requires(Robot.slide);
    	
    	_setPosition = setPosition;
    }

    protected void initialize() {
    	//Measurements of pulley show that it's circumference is about 5.9in
    	Robot.slide.setSlidePosition(_setPosition);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	System.out.println(_setPosition);
    }
    
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return false;
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
