package org.usfirst.frc.team3574.commands.groups;

import org.usfirst.frc.team3574.commands.arm.SetCobraPosition;
import org.usfirst.frc.team3574.commands.arm.SetWristParallel;
import org.usfirst.frc.team3574.commands.lifter.SetLifterPosition;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SetFullPCubeAssemblyPositions extends CommandGroup {

    public SetFullPCubeAssemblyPositions(int cobraPosition, int liftPosition, boolean wristPosition) {
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
    	addSequential(new SetCobraPosition(cobraPosition));
    	//addParallel(new SetLifterPosition(Lifter.LifterHeights.SCALE_MED)); original version in case this screws up
    	addSequential(new SetLifterPosition(liftPosition));
    	addSequential(new SetWristParallel(wristPosition));
    	//for some mysterious reason this command group occasionally will get stuck in the initialize part of SetCobraPosition and does not continue to the next command
    }
}
