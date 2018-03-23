package org.usfirst.frc.team3574.commands.groups;

import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.claw.SetClawPosition;
import org.usfirst.frc.team3574.commands.driveTrain.DoNothing;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.slide.SetSlidePosition;
import org.usfirst.frc.team3574.enums.ClawPosition;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.subsystems.Slide;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CompletePickup extends CommandGroup {

    public CompletePickup() {
		addSequential(new DriveByInches(-Robot.driveTrain.backupDistancePickupStart, 0.3), .25);
		addSequential(new SetClawPosition(ClawPosition.GRIP));
		addSequential(new DoNothing(.2));
		addSequential(new DriveByInches(Robot.driveTrain.backupDistancePickupEnd, 0.3));
//		addSequential(new SetSlidePosition(Slide.SLIDE_CARRY));
		addSequential(new SetArmPosition(Arm.CARRY_ANGLE, new ArmSpeedSettingsWithCube()));
    }
}
