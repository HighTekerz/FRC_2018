package org.usfirst.frc.team3574.commands.util;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RumbleASide extends Command {

	private XboxController _xboxControllerToRumble;
	private double _rumbleValue;
	private RumbleType _rumbleSide;
	private int _timesRumbled;

	public RumbleASide(XboxController xboxControllerToRumble, double rumbleValue, String rumbleSide) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		_rumbleValue = rumbleValue;
		_xboxControllerToRumble = xboxControllerToRumble;
		if (rumbleSide == "Left") {
			_rumbleSide = RumbleType.kLeftRumble;
		}
		else {
			_rumbleSide = RumbleType.kRightRumble;
		}
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		_timesRumbled = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		_xboxControllerToRumble.setRumble(_rumbleSide, _rumbleValue);
		_timesRumbled++;
		System.out.println("Rumbling " + _timesRumbled);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (_timesRumbled == 40) {
			return true;
		}
		else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		_xboxControllerToRumble.setRumble(_rumbleSide, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
