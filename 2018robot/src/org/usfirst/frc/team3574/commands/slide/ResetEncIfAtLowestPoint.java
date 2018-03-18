package org.usfirst.frc.team3574.commands.slide;

import org.usfirst.frc.team3574.commands.util.L;
import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetEncIfAtLowestPoint extends Command {

	private int loop  = 0;
    public ResetEncIfAtLowestPoint() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		L.ogInit(this);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (loop % 15 == 0 && Robot.slide.getBottomStopSwitchIsPressed()) {
    		Robot.slide.resetEnc();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
		L.ogEnd(this);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		L.ogInterrupt(this);
    }
}