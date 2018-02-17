package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree;
import org.usfirst.frc.team3574.commands.groups.PutCubeInScaleMed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPutCubeInScaleAhead extends CommandGroup {

	private double movementSpeed = 0.5;

	/**
	 *Autonomous command to place a cube in the scale plate directly ahead of the robot
	 *
	 *@param degreeToTurnTo should be 90 if the robot is on the left side of the field, -90 if on the right
	 **/
	public AutoPutCubeInScaleAhead(double degreeToTurnTo) {
		addSequential(new DriveByInches(298, movementSpeed));  /* distance needed to travel to scale. 
		 * 312in distance from alliance wall to middle line
		 * -20in (half of 40in long robot) */
		addSequential(new TurnToDegree(degreeToTurnTo, movementSpeed));
		addSequential(new DriveByInches(0, movementSpeed));
		addSequential(new PutCubeInScaleMed());
	}
}
