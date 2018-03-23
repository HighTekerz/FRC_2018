package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.arm.CalibrateArmEncStartingPosition;
import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree;
import org.usfirst.frc.team3574.commands.groups.DropCubeInSwitch;
import org.usfirst.frc.team3574.enums.ShifterPosition;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPutCubeInSwitchStraighten extends CommandGroup {
	
	private int _inchesAwayFromAllianceWall = 7;
	private double _movementSpeed = 0.2;
	private double _inchesToSwitchSide = 105.1487;
	private double _degreeToTurnTo = 44.7076;
	private double numberForHittingTheSwitch = 20;
	/**
	 * 
	 * @param leftOrRight send 1 for left, -1 for right
	 */
    public AutoPutCubeInSwitchStraighten(int leftOrRight) {
    	addSequential(new CalibrateArmEncStartingPosition());
    	addSequential(new DriveByInches(_inchesAwayFromAllianceWall, _movementSpeed, ShifterPosition.LOW_GEAR));
    	addSequential(new TurnToDegree(_degreeToTurnTo * leftOrRight, _movementSpeed));
    	addSequential(new DriveByInches(_inchesToSwitchSide, _movementSpeed));
    	addSequential(new TurnToDegree(_degreeToTurnTo * -leftOrRight, _movementSpeed));
//    	addSequential(new DriveByInches(numberForHittingTheSwitch, _movementSpeed), 1);
//    	addSequential(new SetArmPosition(Arm.AUTO_SWITCH_DELIVERY, new ArmSpeedSettingsWithCube()));
//    	addSequential(new DropCubeInSwitch());
    }
}