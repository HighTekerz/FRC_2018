package org.usfirst.frc.team3574.commands.groups;

import org.usfirst.frc.team3574.commands.arm.SetCobraPosition;
import org.usfirst.frc.team3574.commands.claw.SetClawPosition;
import org.usfirst.frc.team3574.commands.slide.MoveSlideUpPastScale;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.subsystems.ClawPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PutCubeInScaleAtAnyLevel extends CommandGroup {

    public PutCubeInScaleAtAnyLevel() {
        addSequential(new MoveSlideUpPastScale());
        addSequential(new SetCobraPosition(Arm.AttentiveCobra));
        addSequential(new SetClawPosition(ClawPosition.OPEN));
    }
}
