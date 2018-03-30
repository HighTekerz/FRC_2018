package org.usfirst.frc.team3574.commands.arm;

import org.usfirst.frc.team3574.enums.BrakePosition;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithCube;
import org.usfirst.frc.team3574.utilities.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Arm10DegreesForward extends Command {
	ArmSpeedSettingsWithCube armSpeedWithCube = new ArmSpeedSettingsWithCube();
	
	double startDegree;
	double targetDegree;
	double offsetDegree = 10;
	boolean isFinished;
	double currentAngle;
	
	
    public Arm10DegreesForward() {
    	requires(Robot.arm);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isFinished = false;
    	
    	startDegree = Robot.arm.getAngleOfArm();
    	targetDegree = startDegree - offsetDegree;
    	
    	Robot.arm.setBrakePosition(BrakePosition.OPEN);
    	L.ogInit(this);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.arm.getAngleOfArm();
    	
    	if(targetDegree < currentAngle) {
    		Robot.arm.setSpeed(armSpeedWithCube.slowedSpeedDown);
    		isFinished = false;
    	} else {
    		isFinished = true;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
		System.out.println("SetArm Hit Target");
		Robot.arm.setBrakePosition(BrakePosition.CLOSED);
		Robot.arm.setSpeed(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		System.out.println("SetArm Interrupted");
		Robot.arm.setBrakePosition(BrakePosition.CLOSED);
		Robot.arm.setSpeed(0.0);
    	L.ogInterrupt(this);
    }
}
