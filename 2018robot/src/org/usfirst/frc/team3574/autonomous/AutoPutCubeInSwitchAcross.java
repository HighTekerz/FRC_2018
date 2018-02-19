package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree;
import org.usfirst.frc.team3574.commands.groups.PutCubeInSwitch;
import org.usfirst.frc.team3574.robot.FieldFunctions;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPutCubeInSwitchAcross extends CommandGroup {
	/**
	 *Autonomous command to place a cube in the switch plate on the other side of the field from the robot
	 *
	 *@param degreeToTurnTo should be 90 if the robot is on the left side of the field, -90 if on the right
	 **/
    public AutoPutCubeInSwitchAcross(double degreeToTurnTo) {
    	System.out.println("putCube in switch");
    	addSequential(new DriveByInches(43, 0.4));
    	addSequential(new TurnToDegree(degreeToTurnTo, 0.4)); 
    	addSequential(new DriveByInches(87, 0.4)); 
    	addSequential(new TurnToDegree(-degreeToTurnTo, 0.4));
    	addSequential(new DriveByInches(64, 0.4));
    	addSequential(new PutCubeInSwitch());
    }
}
