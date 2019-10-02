package org.usfirst.frc.team3574.commands.arm;

import org.usfirst.frc.team3574.enums.BrakePosition;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithCube;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithoutCube;
import org.usfirst.frc.team3574.utilities.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualArm extends Command {

	private double deadzone = .2;
	ArmSpeedSettingsWithoutCube armSpeedSettingsWithoutCube = new ArmSpeedSettingsWithoutCube();

	public ManualArm() {
		requires(Robot.arm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		L.ogInit(this);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() { 
		if(Math.abs(Robot.OperatorInput.CoPilotRightStickY()) > deadzone) {
			
			
			if(Robot.arm.getEncPos() < -1750 && Robot.OperatorInput.CoPilotRightStickY() < 0 ) {
				
				Robot.arm.setBrakePosition(BrakePosition.CLOSED);
				return;
			}
			
			if(Robot.arm.getEncPos() > -550 && Robot.OperatorInput.CoPilotRightStickY() > 0) {
				
				Robot.arm.setBrakePosition(BrakePosition.CLOSED);
				return;
			}
			
//			if(Robot.arm.getEncPos() < -4096 && (Robot.OperatorInput.CoPilotRightStickY() > 0 || Robot.OperatorInput.CoPilotRightStickY() < 0)) {
//				
//			}
//			
//			if(Robot.arm.getEncPos() < -1500 && Robot.OperatorInput.CoPilotRightStickY() < 0) {
//				
//				Robot.arm.setSpeed(Robot.driveTrain.scalingSpeed(-Robot.OperatorInput.CoPilotRightStickY(), 0.90));
//				return;
//			}
//
//			if((Robot.OperatorInput.CoPilotRightStickY() < 0.0 &&
//					Robot.arm.getAngleOfArm() < Robot.arm.CARRY_ANGLE) || Robot.OperatorInput.CoPilotLeftBumper()) {
			Robot.arm.setBrakePosition(BrakePosition.OPEN);
			Robot.arm.setSpeed(Robot.driveTrain.scalingSpeed(-Robot.OperatorInput.CoPilotRightStickY(), 0.75) + 0.2);
//			} 
//			else if((Robot.OperatorInput.CoPilotRightStickY() > 0.0 &&
//					Robot.arm.getAngleOfArm() > Robot.arm.SWITCH_DELIVERY) || Robot.OperatorInput.CoPilotLeftBumper()) {
//				Robot.arm.setBrakePosition(BrakePosition.OPEN);
//				Robot.arm.setSpeed(Robot.driveTrain.scalingSpeed(-Robot.OperatorInput.CoPilotRightStickY(), 0.75) + 0.2);
//			} 
//			else {
//				Robot.arm.setBrakePosition(BrakePosition.CLOSED);
////				Robot.arm.setSpeed(armSpeedSettingsWoutCube.brakeSpeed);
//				Robot.arm.setSpeed(0.0);
//			}

		} 
		else {
			Robot.arm.setBrakePosition(BrakePosition.CLOSED);
//			Robot.arm.setSpeed(armSpeedSettingsWoutCube.brakeSpeed);
			Robot.arm.setSpeed(0.0);
			
		}

		//		if((Robot.arm.getEncPos() / Robot.arm.TICKS_PER_DEGREE) < Robot.arm.STARTING_POSITION &&
		//				(Robot.arm.getEncPos() / Robot.arm.TICKS_PER_DEGREE) > Robot.arm.CARRY_ANGLE) {
		//
		//			Robot.arm.setBrakePosition(BrakePosition.OPEN);
		//			Robot.arm.setSpeed(Robot.driveTrain.scalingSpeed(-Robot.OperatorInput.CoPilotRightStickY(), 0.75) + 0.2);
		//
		//
		//		} else if (Robot.OperatorInput.CoPilotLeftBumper()) {
		//
		//			Robot.arm.setBrakePosition(BrakePosition.OPEN);
		//			Robot.arm.setSpeed(Robot.driveTrain.scalingSpeed(-Robot.OperatorInput.CoPilotRightStickY(), 0.75) + 0.2);
		//
		//		} else {
		//			if (Math.abs(Robot.OperatorInput.CoPilotRightStickY()) <= deadzone) {
		//
		//				Robot.arm.setBrakePosition(BrakePosition.CLOSED);
		//				//			Robot.arm.setSpeed(0.2);
		//			}
		//		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
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