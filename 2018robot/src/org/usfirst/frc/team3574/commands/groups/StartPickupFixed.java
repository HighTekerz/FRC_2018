package org.usfirst.frc.team3574.commands.groups;

import org.usfirst.frc.team3574.commands.arm.DownUntilClicked;
import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.arm.SetWristPosition;
import org.usfirst.frc.team3574.commands.arm.UpUntilUnclicked;
import org.usfirst.frc.team3574.commands.claw.SetClawPosition;
import org.usfirst.frc.team3574.commands.sensors.UntilBothSensorsAreTripped;
import org.usfirst.frc.team3574.commands.slide.SetSlidePosition;
import org.usfirst.frc.team3574.enums.ClawPosition;
import org.usfirst.frc.team3574.enums.WristPosition;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.subsystems.Slide;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithoutCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StartPickupFixed extends CommandGroup {

    public StartPickupFixed() {
		addSequential(new UntilBothSensorsAreTripped());
		addSequential(new SetClawPosition(ClawPosition.RELEASE));
		addSequential(new SetWristPosition(WristPosition.ANGLED));
		addSequential(new SetSlidePosition(Slide.SLIDE_BOTTOM));
		addSequential(new SetArmPosition(Arm.CUBE_PICKUP, new ArmSpeedSettingsWithoutCube(), 2.0));
		addSequential(new CompletePickup());
    }
}
