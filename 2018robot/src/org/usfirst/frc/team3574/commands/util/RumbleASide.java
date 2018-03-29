package org.usfirst.frc.team3574.commands.util;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;

import org.usfirst.frc.team3574.utilities.L;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RumbleASide extends Command {

	private XboxController _xboxControllerToRumble;
	private double _rumbleValue;
	private RumbleType _rumbleType;
	private int _timesRumbled;

	/***
	 * Rumbles a joystick
	 * 
	 * @param xboxControllerToRumble self explanatory
	 * @param rumbleValue How hard to rumble, between 0.0 and 1.0
	 * @param rumbleType Which rumble mode to use. "Hard" makes firm, sequential rumbles
	 */
	public RumbleASide(XboxController xboxControllerToRumble, double rumbleValue, String rumbleType) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		_rumbleValue = rumbleValue;
		_xboxControllerToRumble = xboxControllerToRumble;
		if (rumbleType == "Hard") {
			_rumbleType = RumbleType.kRightRumble;
		}
		else {
			_rumbleType = RumbleType.kLeftRumble;
		}
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		L.ogInit(this);
		_timesRumbled = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		_xboxControllerToRumble.setRumble(_rumbleType, _rumbleValue);
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
		L.ogEnd(this);
		_xboxControllerToRumble.setRumble(_rumbleType, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		L.ogInterrupt(this);
	}
}
