package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree;
import org.usfirst.frc.team3574.commands.groups.DropCubeInSwitch;
import org.usfirst.frc.team3574.enums.ShifterPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPutCubeInSwitchIsaac extends CommandGroup {
	
	private int _inchesAwayFromAllianceWall = 7;
	private double _movementSpeed = 0.4;
	private int _inchesToSwitchSide = 110; //VERY rough estimate, fix later
	private int _degreeToTurnTo = 32;
	/**
	 * 
	 * @param leftOrRight send -1 for left, 1 for right
	 */
    public AutoPutCubeInSwitchIsaac(int leftOrRight) {
//    	addSequential(new DriveByInches(degreeToTurnTo, _movementSpeed)); //For Testing Purposes
    	addSequential(new DriveByInches(_inchesAwayFromAllianceWall, _movementSpeed, ShifterPosition.LOW_GEAR));
    	addSequential(new TurnToDegree(_degreeToTurnTo * leftOrRight, _movementSpeed));
    	addSequential(new DriveByInches(_inchesToSwitchSide, _movementSpeed));
    	addSequential(new TurnToDegree(_degreeToTurnTo * -leftOrRight, _movementSpeed));
    	addSequential(new DropCubeInSwitch());
    }
}