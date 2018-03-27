package org.usfirst.frc.team3574.commands.groups.pickup;

import org.usfirst.frc.team3574.commands.arm.CalibrateArmEnc;
import org.usfirst.frc.team3574.commands.arm.DownUntilClicked;
import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.arm.SetWristPosition;
import org.usfirst.frc.team3574.commands.arm.UpUntilUnclicked;
import org.usfirst.frc.team3574.commands.claw.SetClawPosition;
import org.usfirst.frc.team3574.commands.driveTrain.DoNothing;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.DriveWithJoy;
import org.usfirst.frc.team3574.commands.sensors.UntilBothSensorsAreTripped;
import org.usfirst.frc.team3574.commands.slide.SetSlidePosition;
import org.usfirst.frc.team3574.commands.util.StartTimer;
import org.usfirst.frc.team3574.commands.util.StopTimer;
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
public class BlindPickup extends CommandGroup {

    public BlindPickup() {
    	addParallel(new StartTimer());
    	addParallel(new DriveWithJoy());
		addSequential(new StartPickup());
		addSequential(new CalibrateArmEnc());
		addSequential(new CompletePickup());
		addSequential(new StopTimer());
	}
}