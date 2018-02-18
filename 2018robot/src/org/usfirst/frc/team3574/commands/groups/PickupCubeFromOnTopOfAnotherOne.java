package org.usfirst.frc.team3574.commands.groups;

import org.usfirst.frc.team3574.commands.arm.SetCobraPosition;
import org.usfirst.frc.team3574.commands.arm.SetWristParallel;
import org.usfirst.frc.team3574.commands.claw.SetClawClosed;
import org.usfirst.frc.team3574.commands.util.UntilBothSensorsAreTripped;
import org.usfirst.frc.team3574.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickupCubeFromOnTopOfAnotherOne extends CommandGroup {

    public PickupCubeFromOnTopOfAnotherOne() {
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
    	
    	//what am i even doing
    	// <|O_O|> Polygonman Wishes you well
    	//literally just a variant of the other pickup command group but for cubes that are on top of other cubes.
    	
    	addSequential(new UntilBothSensorsAreTripped());
    	addParallel(new SetClawClosed(true));
    	addSequential(new SetWristParallel(false));
    	addSequential(new SetCobraPosition(Arm.DepressedCobra));
    	addSequential(new SetClawClosed(false));
    	addParallel(new SetCobraPosition(Arm.AttentiveCobra));
    	addSequential(new SetWristParallel(true));
    }
}
