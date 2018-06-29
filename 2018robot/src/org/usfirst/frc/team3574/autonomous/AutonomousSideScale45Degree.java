package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByInchesNoStop;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree2;
import org.usfirst.frc.team3574.commands.groups.DropCubeInScale;
import org.usfirst.frc.team3574.commands.groups.DropCubeInSwitch;
import org.usfirst.frc.team3574.commands.slide.SetSlidePosition;
import org.usfirst.frc.team3574.enums.ShifterPosition;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.subsystems.Slide;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousSideScale45Degree extends CommandGroup {

	private double movementSpeed = 0.65;

	/**
	 *Autonomous command to place a cube in the scale plate directly ahead of the robot, turning 45 degrees for the purpose
	 *
	 *@param leftOrRight should be 1 if the robot is on the left side of the field, -1 if on the right
	 **/
	public AutonomousSideScale45Degree(double leftOrRight) {
		addSequential(new DriveByInchesNoStop(10, .75, ShifterPosition.LOW_GEAR));
		addSequential(new DriveByInchesNoStop(219, 1, ShifterPosition.LOW_GEAR));
		addSequential(new DriveByInches(10, .75, ShifterPosition.LOW_GEAR));
		addSequential(new TurnToDegree2(44 * leftOrRight, 0.37));
		addSequential(new SetSlidePosition(Slide.SLIDE_SCALE_HIGH));
		addSequential(new DriveByInches(22, movementSpeed));
    	addSequential(new SetArmPosition(Arm.AUTO_SCALE_DELIVERY, new ArmSpeedSettingsWithCube()));
    	addSequential(new AutoDropCubeInScale());
	}
}