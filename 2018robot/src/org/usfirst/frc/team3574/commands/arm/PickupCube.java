package org.usfirst.frc.team3574.commands.arm;

import org.usfirst.frc.team3574.commands.claw.SetClawOpen;
import org.usfirst.frc.team3574.commands.sensors.UntilBothSensorsAreTripped;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.Arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PickupCube extends CommandGroup {

    public PickupCube() {
    	addParallel(new UntilBothSensorsAreTripped());
    	addParallel(new SetClawOpen(true));
    	addSequential(new SetWristParallel(false));
    	addSequential(new SetCobraPosition(Arm.DeadCobra));
    	addSequential(new SetClawOpen(false));
    	addParallel(new SetCobraPosition(Arm.AttentiveCobra));
    	addSequential(new SetWristParallel(true));
    }
}
