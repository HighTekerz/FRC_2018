package org.usfirst.frc.team3574.commands.arm;

import org.usfirst.frc.team3574.commands.util.UntilBothSensorsAreTripped;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickupPowerCube extends CommandGroup {

    public PickupPowerCube() {
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
    	addSequential(new UntilBothSensorsAreTripped());
    	addSequential(new SetClawPosition(true));
    	addSequential(new SetWristParallel(true));
    	addSequential(new SetCobraPosition(Arm.DeadCobra));
    	addSequential(new SetClawPosition(false));
    	addParallel(new SetCobraPosition(Arm.AggressiveCobra));
    	addSequential(new SetWristParallel(false));
    }
}
