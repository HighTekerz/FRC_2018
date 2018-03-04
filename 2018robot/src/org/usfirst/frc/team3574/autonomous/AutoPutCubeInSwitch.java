package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree;
import org.usfirst.frc.team3574.commands.groups.PutCubeInSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPutCubeInSwitch extends CommandGroup {
	
	private int _inchesAwayFromAllianceWall = 2;
	private double _movementSpeed = 0.4;
	private int _inchesToSwitchSide = 130; //VERY rough estimate, fix later
	
    public AutoPutCubeInSwitch(int degreeToTurnTo) {
//    	addSequential(new DriveByInches(degreeToTurnTo, _movementSpeed)); //For Testing Purposes
    	addSequential(new DriveByInches(_inchesAwayFromAllianceWall, _movementSpeed));
    	addSequential(new TurnToDegree(degreeToTurnTo, _movementSpeed));
    	addSequential(new DriveByInches(_inchesToSwitchSide, _movementSpeed));
    	addSequential(new TurnToDegree(-degreeToTurnTo, _movementSpeed));
    	addSequential(new PutCubeInSwitch());
    }
}
