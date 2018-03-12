package org.usfirst.frc.team3574.commands.groups;

import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.claw.SetClawPosition;
import org.usfirst.frc.team3574.commands.slideDEPRICATED.SetSlidePosition;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.subsystems.ClawPosition;
import org.usfirst.frc.team3574.subsystems.Slide;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class PutCubeInScaleLow extends CommandGroup {

    public PutCubeInScaleLow() {
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
    	
    	//command to put the power cube in the scale while the scale is at its lowest position
    	
    	requires(Robot.arm);
    	requires (Robot.slide);
    	
    	addParallel(new SetArmPosition(Arm.AggressiveCobra));
    	addSequential(new SetSlidePosition(Slide.scaleLow));
    	addSequential(new SetClawPosition(ClawPosition.OPEN));
    	addSequential(new SetArmPosition(Arm.PreparedCobra));
    	addSequential(new SetClawPosition(ClawPosition.CLOSED)); //TODO: do we really need to do this????
    	
    }
}
