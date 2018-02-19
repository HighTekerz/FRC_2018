package org.usfirst.frc.team3574.commands.arm;

import org.usfirst.frc.team3574.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetCobraPosition extends Command {

	private int _cobraPosition; 
	
    /**
     * Command to move the Arm to different locations
     * 
     * @param cobraPosition Denotes which arm position you want to go to. Pull your number from the arm subsystem (e.g. Robot.arm.AggressiveCobra)
     */
	public SetCobraPosition(int cobraPosition) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.arm);
		int g = Robot.arm.AggressiveCobra;
    	_cobraPosition = cobraPosition;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("SetCobraPosition Initialized");
    	Robot.arm.putTheArmSomewhere(_cobraPosition);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("bleep boop COBRA STRIKKKKEEEEE");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("Cobra done");
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("now its really done. cobra movement completed at " + timeSinceInitialized());
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("cobra has been interrupted...");
    }
}
