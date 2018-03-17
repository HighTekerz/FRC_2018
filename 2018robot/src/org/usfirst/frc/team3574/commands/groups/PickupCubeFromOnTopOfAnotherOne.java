package org.usfirst.frc.team3574.commands.groups;

import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.arm.SetWristPosition;
import org.usfirst.frc.team3574.commands.sensors.UntilBothSensorsAreTripped;
import org.usfirst.frc.team3574.enums.WristPosition;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.utilities.SpeedSettingsWithCube;
import org.usfirst.frc.team3574.utilities.SpeedSettingsWithoutCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickupCubeFromOnTopOfAnotherOne extends CommandGroup {

    public PickupCubeFromOnTopOfAnotherOne() {
    	addSequential(new UntilBothSensorsAreTripped());
//    	addParallel(new SetClawOpen(true));
    	addSequential(new SetWristPosition(WristPosition.ANGLED));
    	addSequential(new SetArmPosition(Arm.SWITCH_DELIVERY, new SpeedSettingsWithoutCube()));
//    	addSequential(new SetClawOpen(false));
    	addParallel(new SetArmPosition(Arm.CARRY_ANGLE, new SpeedSettingsWithCube()));
    	addSequential(new SetWristPosition(WristPosition.STRAIGHT));
    }
}