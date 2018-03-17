package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree;
import org.usfirst.frc.team3574.enums.ShifterPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class AutoPutCubeInScaleAcross extends CommandGroup {
	
	public double movementSpeed = 0.5;
	
	@Deprecated
	/**
	 *Autonomous command to place a cube in the scale plate on the other side of the field from the robot
	 *
	 *@param degreeToTurnTo should be 90 if the robot is on the left side of the field, -90 if on the right
	 **/
    public AutoPutCubeInScaleAcross(double degreeToTurnTo) {
        addSequential(new DriveByInches(0, movementSpeed, ShifterPosition.LOW_GEAR)); // insert the distance needed to travel to near scale. 288 = distance between alliance station and close side of scale plate
        addSequential(new TurnToDegree(degreeToTurnTo, movementSpeed));
        addSequential(new DriveByInches(0, movementSpeed)); // insert the distance needed to travel along scale. 180 = distance between one edge of scale plate and the other
        addSequential(new TurnToDegree(-degreeToTurnTo, movementSpeed));
        addSequential(new DriveByInches(0, movementSpeed)); // insert the distance needed to have arm over scale
    }
}
