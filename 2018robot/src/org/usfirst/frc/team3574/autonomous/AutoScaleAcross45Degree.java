package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.arm.SetWristPosition;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree2;
import org.usfirst.frc.team3574.commands.slide.SetSlidePosition;
import org.usfirst.frc.team3574.enums.ShifterPosition;
import org.usfirst.frc.team3574.enums.WristPosition;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.subsystems.Slide;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithCube;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class AutoScaleAcross45Degree extends CommandGroup {
	
	public double movementSpeed = 0.75;
	
	/**
	 *Autonomous command to place a cube in the scale plate on the other side of the field from the robot , turning 45 degrees for the purpose
	 *
	 *@param leftOrRight should be -1 if the robot is on the left side of the field, 1 if on the right
	 **/
    public AutoScaleAcross45Degree(double leftOrRight) {
        addSequential(new DriveByInches(219, movementSpeed, ShifterPosition.LOW_GEAR));
        addSequential(new TurnToDegree2(90 * leftOrRight, movementSpeed));
        addSequential(new DriveByInches(193, movementSpeed));
        addSequential(new TurnToDegree2(-(90 * leftOrRight), movementSpeed));
		addSequential(new SetSlidePosition(Slide.SLIDE_SCALE_HIGH));
    	addSequential(new SetWristPosition(WristPosition.ANGLED));
		addSequential(new DriveByInches(37, movementSpeed));
    	addSequential(new SetArmPosition(Arm.AUTO_SCALE_DELIVERY, new ArmSpeedSettingsWithCube()));
    	addSequential(new AutoDropCubeInScale());
    }
}
