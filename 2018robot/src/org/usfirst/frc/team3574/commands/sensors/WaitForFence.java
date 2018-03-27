package org.usfirst.frc.team3574.commands.sensors;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.utilities.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitForFence extends Command {

	
    public WaitForFence() {
      
    }

    protected void initialize() {
    	L.ogInit(this);
    }

    protected void execute() {    	
    }

    protected boolean isFinished() {
    	return Robot.sensorTest.IsWithinRange();
    }

    protected void end() {
    	L.ogEnd(this);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	L.ogInterrupt(this);
    }
}
