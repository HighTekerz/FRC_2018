package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree2;
import org.usfirst.frc.team3574.commands.groups.DropCubeInSwitch;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousSideSwitch extends CommandGroup {
	
	double movementSpeed = 0.65;
	double distanceToMiddleOfSwitch = 128;
	
    public AutonomousSideSwitch(double degreeToTurnTo) {
    	addSequential(new DriveByInches(distanceToMiddleOfSwitch, movementSpeed));
    	addSequential(new TurnToDegree2(degreeToTurnTo, .37));
    	addSequential(new DriveByInches(15.5, movementSpeed), 2);
    	addSequential(new SetArmPosition(Arm.AUTO_SWITCH_DELIVERY, new ArmSpeedSettingsWithCube()));
    	addSequential(new DropCubeInSwitch());
    }
}