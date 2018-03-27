package org.usfirst.frc.team3574.commands.groups.pickup;

import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.arm.SetWristPosition;
import org.usfirst.frc.team3574.commands.claw.SetClawPosition;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.DriveWithJoy;
import org.usfirst.frc.team3574.commands.sensors.UntilBothSensorsAreTripped;
import org.usfirst.frc.team3574.enums.ClawPosition;
import org.usfirst.frc.team3574.enums.WristPosition;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithoutCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GoToPositionPickup extends CommandGroup {

    public GoToPositionPickup() {
    	
    	addParallel(new DriveWithJoy());
		addSequential(new UntilBothSensorsAreTripped());
		addSequential(new DriveByInches(Robot.driveTrain.backupDistancePickupStart, 0.2));
    	addSequential(new SetClawPosition(ClawPosition.RELEASE));
		addSequential(new SetWristPosition(WristPosition.ANGLED));
//		addSequential(new SetSlidePosition(Slide.SLIDE_BOTTOM));
		addSequential(new SetArmPosition(Arm.CUBE_PICKUP, new ArmSpeedSettingsWithoutCube(), 2));
		addSequential(new CompletePickup());    }
}
