package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.FindOurSide;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceCubeInSwitchFromMiddle extends CommandGroup {

    public PlaceCubeInSwitchFromMiddle() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
//    	TODO: build these commands
    	addSequential(new FindOurSide());
//    	addSequential(new TurnToFaceOurSide());
    	addSequential(new DriveByInches(12, 0.4));
//    	addSequential(new DropACubeOntoSwitch());
    }
}
