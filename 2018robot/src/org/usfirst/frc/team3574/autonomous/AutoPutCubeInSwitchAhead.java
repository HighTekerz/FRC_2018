package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.groups.PutCubeInSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPutCubeInSwitchAhead extends CommandGroup {
	
	/**
	 *	Autonomous command to place a cube in the switch plate directly ahead of the Robot
	 **/
	
	public AutoPutCubeInSwitchAhead() {
		System.out.println("Put Cube Ahead");
    	addSequential(new DriveByInches(107, 0.75));
    	addSequential(new PutCubeInSwitch());
    }
}