package org.usfirst.frc.team3574.commands.groups;

import org.usfirst.frc.team3574.commands.arm.SetCobraPosition;
import org.usfirst.frc.team3574.commands.claw.SetClawOpen;
import org.usfirst.frc.team3574.commands.Slide.SetSlidePosition;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.subsystems.Slide;


import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PutCubeInSwitch extends CommandGroup {

    public PutCubeInSwitch() {
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
    	addParallel(new SetCobraPosition(Arm.DepressedCobra));
    	addSequential(new SetSlidePosition(Slide.switchHeight));
    	addSequential(new SetClawOpen(false));
    	addSequential(new SetCobraPosition(Arm.AttentiveCobra));
    	addSequential(new SetClawOpen(true));
    }
}
