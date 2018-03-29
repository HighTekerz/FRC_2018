package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.arm.SetWristPosition;
import org.usfirst.frc.team3574.commands.claw.SetClawPosition;
import org.usfirst.frc.team3574.commands.driveTrain.DoNothing;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.DriveWithJoy;
import org.usfirst.frc.team3574.commands.slide.HoldSlidePosition;
import org.usfirst.frc.team3574.commands.slide.SetSlidePosition;
import org.usfirst.frc.team3574.commands.util.ResetPreparationInt;
import org.usfirst.frc.team3574.enums.ClawPosition;
import org.usfirst.frc.team3574.enums.WristPosition;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.subsystems.Slide;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithCube;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithoutCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDropCubeInScale extends CommandGroup {

    public AutoDropCubeInScale() {
    	//    	Move forward
//    	addSequential(new DriveByInches(2, 0.4));
    	//    	Determine when pressed against the wall
    	//No code currently ^^
    	addParallel(new HoldSlidePosition());
    	//    	Open CLAW
    	addSequential(new SetClawPosition(ClawPosition.RELEASE));
    	
    	addSequential(new DoNothing(0.5));
//    	Backup 3 inches    	
    	addSequential(new DriveByInches(Robot.driveTrain.autoBackupDistanceScale, 0.35));
//    	Move SHOULDER to carry angle
    	addSequential(new SetArmPosition(Arm.CARRY_ANGLE, new ArmSpeedSettingsWithoutCube()));
//    	Move SLIDE to Carry position
//    	addSequential(new SetSlidePosition(Slide.SLIDE_CARRY));
//		Set preparation variable back to 0
    	addSequential(new ResetPreparationInt());
//    	End Sequence and return control of robot.
    	//Return drivetrain control
//    	Shake driver controller for 1 sec
    	//Rumble 1s
    }
}
