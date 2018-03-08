package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree;
import org.usfirst.frc.team3574.commands.groups.PutCubeInSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPutCubeInSwitchButItsNoahsAndReallyBad extends CommandGroup {
	
	private int _inchesAwayFromAllianceWall = 7;
	private double _movementSpeed = 0.4;
	private double _inchesToSwitchSide = 105.1487; //VERY rough estimate, fix later
	private double _degreeToTurnTo = 44.7076;
	/**
	 * 
	 * @param leftOrRight send -1 for left, 1 for right
	 */
    public AutoPutCubeInSwitchButItsNoahsAndReallyBad(int leftOrRight) {
//    	addSequential(new DriveByInches(degreeToTurnTo, _movementSpeed)); //For Testing Purposes
    	addSequential(new DriveByInches(_inchesAwayFromAllianceWall, _movementSpeed));
    	addSequential(new TurnToDegree(_degreeToTurnTo * leftOrRight, _movementSpeed));
    	addSequential(new DriveByInches(_inchesToSwitchSide, _movementSpeed));
    	addSequential(new TurnToDegree(_degreeToTurnTo * -leftOrRight, _movementSpeed));
    	addSequential(new DriveByInches(_inchesAwayFromAllianceWall, _movementSpeed));
    	addSequential(new PutCubeInSwitch());
    }
}