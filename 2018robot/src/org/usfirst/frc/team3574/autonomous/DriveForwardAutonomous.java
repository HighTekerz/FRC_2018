package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.arm.CalibrateArmEncStartingPosition;
import org.usfirst.frc.team3574.commands.driveTrain.DoNothing;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.ShiftGear;
import org.usfirst.frc.team3574.enums.ShifterPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveForwardAutonomous extends CommandGroup {

	/**
	 * Autonomous program to drive across the auto line and stop
	 */
    public DriveForwardAutonomous() {
    	System.out.println("Drive Forwards Auto");
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
    	addSequential(new CalibrateArmEncStartingPosition());
    	addSequential(new DriveByInches(130, .4, ShifterPosition.HIGH_GEAR)); /* 120in from alliance wall to auto line
    												 - 40in long robot
    												 + 10in to make sure we get over the line */
    	addSequential(new DoNothing());
    }
}
