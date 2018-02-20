package org.usfirst.frc.team3574.commands.arm;

import org.usfirst.frc.team3574.commands.claw.SetClawPosition;
import org.usfirst.frc.team3574.commands.sensors.UntilBothSensorsAreTripped;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.subsystems.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickupCube extends CommandGroup {

    public PickupCube() {
    	addSequential(new UntilBothSensorsAreTripped());
    	addParallel(new SetClawPosition(ClawPosition.OPEN));
    	addSequential(new SetWristParallel(false));
    	addSequential(new SetCobraPosition(Arm.DeadCobra));
    	addSequential(new SetClawPosition(ClawPosition.CLOSED));
    	addParallel(new SetCobraPosition(Arm.AttentiveCobra));
    	addSequential(new SetWristParallel(true));
    }
}
