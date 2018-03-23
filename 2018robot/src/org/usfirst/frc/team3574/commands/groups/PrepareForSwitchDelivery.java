package org.usfirst.frc.team3574.commands.groups;

import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.arm.SetWristPosition;
import org.usfirst.frc.team3574.commands.slide.MoveSlideBasedOnTarget;
import org.usfirst.frc.team3574.commands.util.ResetPreparationInt;
import org.usfirst.frc.team3574.enums.WristPosition;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithCube;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithoutCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PrepareForSwitchDelivery extends CommandGroup {

    public PrepareForSwitchDelivery() {
//	Tilt WRIST to straight position
    	addSequential(new SetWristPosition(WristPosition.STRAIGHT));
//	Move SHOULDER to switch deliver angle
    	addSequential(new SetArmPosition(Arm.SWITCH_DELIVERY, new ArmSpeedSettingsWithCube()));
    	//    	If button pressed and slide at pos 1 already, move to pos 2
    	//    	If button pressed and slide at pos 2 already, move to pos 3
//	Move SLIDE to switch position 1
//    	addSequential(new MoveSlideBasedOnTarget(true));
    }
}