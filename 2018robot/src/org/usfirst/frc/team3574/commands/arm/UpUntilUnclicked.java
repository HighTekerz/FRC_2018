package org.usfirst.frc.team3574.commands.arm;

import org.usfirst.frc.team3574.enums.BrakePosition;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.utilities.IArmSpeedSettings;
import org.usfirst.frc.team3574.utilities.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UpUntilUnclicked extends Command {
	
	IArmSpeedSettings _iSpeedSetting;
//	private double timeout = 2;
	private double _timeout;
	
	
	public UpUntilUnclicked(IArmSpeedSettings iSpeedSetting, double timeout) {
		requires(Robot.arm); //added Friday before first districts
		_iSpeedSetting = iSpeedSetting;
		_timeout = timeout;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		L.ogInit(this);
		Robot.arm.setBrakePosition(BrakePosition.OPEN);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
			Robot.arm.setSpeed(_iSpeedSetting.calibrateSpeedUp);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
//		if (timeSinceInitialized() > timeout) {
//			return true;
//		}
		return !Robot.arm.getLimitSwitch();
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("UpUntil Clicks true at: " + Robot.arm.getEncPos());
		Robot.arm.setBrakePosition(BrakePosition.CLOSED);
		Robot.arm.setSpeed(_iSpeedSetting.brakeSpeed);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		L.ogInterrupt(this);
		Robot.arm.setBrakePosition(BrakePosition.CLOSED);
		Robot.arm.setSpeed(_iSpeedSetting.brakeSpeed);
	}
}