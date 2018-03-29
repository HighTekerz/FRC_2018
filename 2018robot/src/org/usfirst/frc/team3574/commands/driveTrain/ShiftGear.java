package org.usfirst.frc.team3574.commands.driveTrain;

import org.usfirst.frc.team3574.enums.ShifterPosition;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.utilities.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftGear extends Command {

	ShifterPosition _shifterPosition;
	
    public ShiftGear(ShifterPosition ShifterPosition) {
    	_shifterPosition = ShifterPosition;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	L.ogInit(this);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("ShiftGear executes. going to: " + _shifterPosition);
    	Robot.driveTrain.ShiftGear(_shifterPosition);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("ShiftGear ends. Time Since Initialized" + timeSinceInitialized());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	L.ogInterrupt(this);
    }
}