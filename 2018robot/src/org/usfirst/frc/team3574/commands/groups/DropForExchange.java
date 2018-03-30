package org.usfirst.frc.team3574.commands.groups;

import org.usfirst.frc.team3574.commands.arm.DownUntilClicked;
import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.arm.SetWristPosition;
import org.usfirst.frc.team3574.commands.claw.SetClawPosition;
import org.usfirst.frc.team3574.commands.driveTrain.DriveWithJoy;
import org.usfirst.frc.team3574.enums.ClawPosition;
import org.usfirst.frc.team3574.enums.WristPosition;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithCube;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithoutCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DropForExchange extends CommandGroup {

    public DropForExchange() {
    	addParallel(new DriveWithJoy());
    	addSequential(new SetWristPosition(WristPosition.ANGLED));
    	addSequential(new DownUntilClicked(new ArmSpeedSettingsWithCube(), 1, true));
    	addSequential(new SetClawPosition(ClawPosition.RELEASE));
    	addSequential(new SetWristPosition(WristPosition.STRAIGHT));
    	addSequential(new SetArmPosition(Arm.CARRY_ANGLE, new ArmSpeedSettingsWithoutCube()), 3);
    }
}
