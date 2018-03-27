package org.usfirst.frc.team3574.commands.groups.pickup;

import org.usfirst.frc.team3574.commands.arm.DownUntilClicked;
import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.arm.SetWristPosition;
import org.usfirst.frc.team3574.commands.claw.SetClawPosition;
import org.usfirst.frc.team3574.commands.driveTrain.DriveWithJoy;
import org.usfirst.frc.team3574.enums.ClawPosition;
import org.usfirst.frc.team3574.enums.WristPosition;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithCube;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithoutCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OppositePickup extends CommandGroup {

    public OppositePickup() {
    	
    	
    	addParallel(new DriveWithJoy());
    	addSequential(new SetWristPosition(WristPosition.ANGLED));
    	addSequential(new DownUntilClicked(new ArmSpeedSettingsWithCube(), 1, true));
    	addSequential(new SetClawPosition(ClawPosition.RELEASE));
    	addSequential(new SetWristPosition(WristPosition.STRAIGHT));
    	addSequential(new SetArmPosition(Arm.CARRY_ANGLE, new ArmSpeedSettingsWithoutCube()), 3);
    
    	
    	
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
    }
}
