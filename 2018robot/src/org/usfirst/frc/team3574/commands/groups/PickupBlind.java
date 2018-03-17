package org.usfirst.frc.team3574.commands.groups;

import org.usfirst.frc.team3574.commands.arm.CalibrateArmEnc;
import org.usfirst.frc.team3574.commands.arm.DownUntilClicked;
import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.arm.SetWristPosition;
import org.usfirst.frc.team3574.commands.arm.UpUntilUnclicked;
import org.usfirst.frc.team3574.commands.claw.SetClawPosition;
import org.usfirst.frc.team3574.commands.driveTrain.DoNothing;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.sensors.UntilBothSensorsAreTripped;
import org.usfirst.frc.team3574.commands.slide.SetSlidePosition;
import org.usfirst.frc.team3574.enums.ClawPosition;
import org.usfirst.frc.team3574.enums.WristPosition;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.subsystems.Slide;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithoutCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickupBlind extends CommandGroup {

    public PickupBlind() {
		addSequential(new DriveByInches(Robot.driveTrain.backupDistancePickupStart, 0.4));
		addSequential(new SetClawPosition(ClawPosition.RELEASE));
		addSequential(new SetWristPosition(WristPosition.ANGLED));
		addSequential(new SetSlidePosition(Slide.SLIDE_BOTTOM));
		addSequential(new DownUntilClicked(new ArmSpeedSettingsWithoutCube(), 1, true));
		addSequential(new UpUntilUnclicked(new ArmSpeedSettingsWithoutCube(), 1));
		addSequential(new DownUntilClicked(new ArmSpeedSettingsWithoutCube(), 1, false));
		addSequential(new CalibrateArmEnc());
		addSequential(new CompletePickup());
    }
}