package org.usfirst.frc.team3574.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FindOurSide extends Command {

	String NewMessage;
	
    public FindOurSide() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	System.out.print("FOS-init");
    	NewMessage = DriverStation.getInstance().getGameSpecificMessage();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(NewMessage);
//    	System.out.print("find our side-- exe");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	System.out.print("find our side-- is finished");
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	System.out.print("find our side-- end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
