package org.usfirst.frc.team3574.commands.arm;

import org.usfirst.frc.team3574.commands.util.L;
import org.usfirst.frc.team3574.enums.BrakePosition;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.utilities.IArmSpeedSettings;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DownUntilClicked extends Command {

	IArmSpeedSettings _iSpeedSetting;
//	private double timeout = 2;
	private double _timeout;
	private boolean _isFirstTime;
	
	
	public DownUntilClicked(IArmSpeedSettings iSpeedSetting, double timeout, boolean isFirstTime) {
		requires(Robot.arm); //added Friday before first districts
		_iSpeedSetting = iSpeedSetting;
		_timeout = timeout;
		_isFirstTime = isFirstTime;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		L.ogInit(this);
		Robot.arm.setBrakePosition(BrakePosition.OPEN);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(timeSinceInitialized() < 0.4 && _isFirstTime) {
			Robot.arm.setSpeed(_iSpeedSetting.calibrateSpeed);
		}
		else {
			Robot.arm.setSpeed(_iSpeedSetting.calibrateSlowSpeed);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
//		if (timeSinceInitialized() > timeout) {
//			return true;
//		}
		return Robot.arm.getLimitSwitch();
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("Clicks False at: " + Robot.arm.getEncPos());
		Robot.arm.setBrakePosition(BrakePosition.CLOSED);
		Robot.arm.setSpeed(_iSpeedSetting.brakeSpeed);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.arm.setBrakePosition(BrakePosition.CLOSED);
		Robot.arm.setSpeed(_iSpeedSetting.brakeSpeed);
	}
}