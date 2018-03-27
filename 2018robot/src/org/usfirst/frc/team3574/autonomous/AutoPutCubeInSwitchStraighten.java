package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.arm.CalibrateArmEncStartingPosition;
import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegreeTwoPointOh;
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
	private double _movementSpeed = 0.65;
	private double _turnSpeed = 0.5;
	private double _inchesToSwitchSide = 88;//101.54801;
	private double _degreeToTurnTo = 32.125;
//	private double numberForHittingTheSwitch = 20;
	/**
	 * 
	 * @param leftOrRight send 1 for left, -1 for right
	 */
    public AutoPutCubeInSwitchStraighten(double leftOrRight) {
    	addSequential(new CalibrateArmEncStartingPosition());
    	addSequential(new DriveByInches(_inchesAwayFromAllianceWall, _movementSpeed, ShifterPosition.LOW_GEAR));

//    	addSequential(new TurnToDegree(_degreeToTurnTo * leftOrRight, _movementSpeed));
    	addSequential(new TurnToDegreeTwoPointOh(_degreeToTurnTo * leftOrRight, _turnSpeed));
    	addSequential(new DriveByInches(_inchesToSwitchSide, _movementSpeed), 5);
    	
//    	addSequential(new TurnToDegree(_degreeToTurnTo * -leftOrRight, _movementSpeed));
    	addSequential(new TurnToDegreeTwoPointOh(_degreeToTurnTo * -leftOrRight, _turnSpeed), 2);
    	
    	addSequential(new DriveByInches(_inchesAwayFromAllianceWall, _movementSpeed), 3);
    	
    	addSequential(new SetArmPosition(Arm.AUTO_SWITCH_DELIVERY, new ArmSpeedSettingsWithCube()));
    	addSequential(new DropCubeInSwitch());
    }
}