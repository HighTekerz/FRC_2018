package org.usfirst.frc.team3574.commands.slide;

import org.usfirst.frc.team3574.subsystems.Slide;
import org.usfirst.frc.team3574.subsystems.UtilitySubsystem;
import org.usfirst.frc.team3574.utilities.L;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveSlideBasedOnTarget extends Command {

	Command _command;
	private boolean _preppingForSwitch;

	public MoveSlideBasedOnTarget(boolean preppingForSwitch) {
		_preppingForSwitch = preppingForSwitch;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		L.ogInit(this);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (_preppingForSwitch) {
			if(UtilitySubsystem.armPositionPlacementForDropoff < 2) {
				UtilitySubsystem.armPositionPlacementForDropoff++;
			}
			_command = new SetSlidePosition(Slide.SLIDE_SWITCH_DELIVERY +((UtilitySubsystem.armPositionPlacementForDropoff - 1) * Slide.SLIDE_HIGHER_INCREMENT_SWITCH));
		}
		else {
			if(UtilitySubsystem.armPositionPlacementForDropoff < 5) {
				UtilitySubsystem.armPositionPlacementForDropoff++;
			}
//			else {
//				UtilitySubsystem.armPositionPlacementForDropoff = 1;
//			}
			_command = new SetSlidePosition(Slide.SLIDE_SCALE_LOW + ((UtilitySubsystem.armPositionPlacementForDropoff - 1) * Slide.SLIDE_HIGHER_INCREMENT_SCALE));
		}
	}
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		L.ogEnd(this);
		_command.start();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		L.ogInterrupt(this);
	}
}
