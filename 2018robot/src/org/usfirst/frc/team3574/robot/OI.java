/*----------------------------------------------------------------------------*/

/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3574.robot;


import org.usfirst.frc.team3574.commands.arm.CalibrateArmEncStartingPosition;
import org.usfirst.frc.team3574.commands.arm.CalibrateArmEncStartingPositionYadda;
import org.usfirst.frc.team3574.commands.arm.HoldArmPosition;
import org.usfirst.frc.team3574.commands.arm.ManualArm;
import org.usfirst.frc.team3574.commands.arm.OpenBrake;
import org.usfirst.frc.team3574.commands.arm.SetArmPosition;
import org.usfirst.frc.team3574.commands.arm.SetWristPosition;
import org.usfirst.frc.team3574.commands.claw.SetClawPosition;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.ShiftGear;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree;
import org.usfirst.frc.team3574.commands.groups.CalibratePickup;
import org.usfirst.frc.team3574.commands.groups.DropCubeInScale;
import org.usfirst.frc.team3574.commands.groups.DropCubeInSwitch;
import org.usfirst.frc.team3574.commands.groups.MoveToStartingPosition;
import org.usfirst.frc.team3574.commands.groups.PickupBlind;
import org.usfirst.frc.team3574.commands.groups.PrepareForScaleDelivery;
import org.usfirst.frc.team3574.commands.groups.StartPickup;
import org.usfirst.frc.team3574.commands.groups.PrepareForSwitchDelivery;
import org.usfirst.frc.team3574.commands.slide.HoldSlidePosition;
import org.usfirst.frc.team3574.commands.slide.ManualSlide;
import org.usfirst.frc.team3574.commands.slide.SetSlidePosition;
import org.usfirst.frc.team3574.enums.ClawPosition;
import org.usfirst.frc.team3574.enums.ShifterPosition;
import org.usfirst.frc.team3574.enums.WristPosition;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.subsystems.Slide;
import org.usfirst.frc.team3574.triggers.POVBottomRange;
import org.usfirst.frc.team3574.triggers.POVDown;
import org.usfirst.frc.team3574.triggers.POVLeft;
import org.usfirst.frc.team3574.triggers.POVRight;
import org.usfirst.frc.team3574.triggers.POVTopRange;
import org.usfirst.frc.team3574.triggers.POVUp;
import org.usfirst.frc.team3574.triggers.TriggerButton;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithCube;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController; 
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	XboxController driverXbox360Controller = new XboxController(0);
	XboxController coPilotxbox360Controller = new XboxController(1);
//	Joystick garrettDriver = new Joystick(4);
	//	Joystick logitechAttack = new Joystick(3);
	
	static final int A_BUTTON = 1;
	static final int B_BUTTON = 2;
	static final int X_BUTTON = 3;
	static final int Y_BUTTON = 4;
	static final int LEFT_BUMPER = 5;
	static final int RIGHT_BUMPER = 6;
	static final int BACK = 7;
	static final int START = 8;
	static final int RIGHT_THUMBSTICK_BUTTON = 9;
	static final int LEFT_THUMBSTICK_BUTTON = 10;

	
	static final int LEFT_STICK_X = 0;
	static final int LEFT_STICK_Y = 1;
	static final int LEFT_TRIGGER = 2;
	static final int RIGHT_TRIGGER = 3;
	int pov = 0;

	public OI() {
	SmartDashboard.putData(new TurnToDegree(45, .5));
		
		
		/**
		 * <<DRIVER CONTROLLER>> (david)
		 */
		Button calibratePickup = new TriggerButton(driverXbox360Controller, LEFT_TRIGGER);
		calibratePickup.whenPressed(new CalibratePickup());
		
		Button pickupNoCal = new TriggerButton(driverXbox360Controller, RIGHT_TRIGGER);
		pickupNoCal.whenPressed(new StartPickup());
		
		Button pickupBlind = new JoystickButton(driverXbox360Controller, LEFT_BUMPER);
		pickupBlind.whenPressed(new PickupBlind());
		
		Button shiftHigh = new POVUp(driverXbox360Controller, pov);
		shiftHigh.whenPressed(new ShiftGear(ShifterPosition.HIGH_GEAR));
		
		Button shiftLow = new POVDown(driverXbox360Controller, pov);
		shiftLow.whenPressed(new ShiftGear(ShifterPosition.LOW_GEAR));
		
		// <<CO-PILOT CONTROLLER>>
//		Button prepareForSwitch = new JoystickButton(coPilotxbox360Controller, LEFT_BUMPER);
//		prepareForSwitch.whenPressed(new SetSlidePosition(60));//PrepareForSwitchDelivery());

		Button prepareForScale = new JoystickButton(coPilotxbox360Controller, RIGHT_BUMPER);
		prepareForScale.whenPressed(new SetSlidePosition((12 * 8)));//PrepareForScaleDelivery());
		
//		Button dropOffInSwitch = new TriggerButton(coPilotxbox360Controller, LEFT_TRIGGER);
//		dropOffInSwitch.whenPressed(new DropCubeInSwitch());
		
		Button dropOffInScale = new TriggerButton(coPilotxbox360Controller, RIGHT_TRIGGER);
		dropOffInScale.whenPressed(new DropCubeInScale());
		
		Button calToStartingPosition = new JoystickButton(coPilotxbox360Controller, BACK);
		calToStartingPosition.whenPressed(new CalibrateArmEncStartingPositionYadda());

		Button readyToIntakePosition = new JoystickButton(coPilotxbox360Controller, START);
//		startingPosition.whenPressed(new MoveToStartingPosition());
		/**
		 * NEED TO PROGRAM   <-----  this worries me.  -John  :)
		 */

		Button startingPosition = new JoystickButton(coPilotxbox360Controller, Y_BUTTON);
		startingPosition.whenPressed(new MoveToStartingPosition());
		
		Button angleWrist = new POVDown(coPilotxbox360Controller, pov);
		angleWrist.whenPressed(new SetWristPosition(WristPosition.ANGLED));
		
		Button straightenWrist = new POVUp(coPilotxbox360Controller, pov);
		straightenWrist.whenPressed(new SetWristPosition(WristPosition.STRAIGHT));
		
		Button openClaw = new POVLeft(coPilotxbox360Controller, pov);
		openClaw.whenPressed(new SetClawPosition(ClawPosition.RELEASE));

		Button closeClaw = new POVRight(coPilotxbox360Controller, pov);
		closeClaw.whenPressed(new SetClawPosition(ClawPosition.GRIP));
		
//		Button manualArm = new JoystickButton(coPilotxbox360Controller, A_BUTTON);
//		manualArm.whileHeld(new ManualArm());
//		manualArm.whenReleased(new HoldArmPosition());
//		
//		Button manualSlide = new JoystickButton(coPilotxbox360Controller, B_BUTTON);
//		manualSlide.whileHeld(new ManualSlide());
//		manualSlide.whenReleased(new HoldSlidePosition());
		
	}

	public double getLeftStickY()
	{
		return driverXbox360Controller.getY(Hand.kLeft);
	}
	
	public double getRightStickY()
	{
		return driverXbox360Controller.getY(Hand.kRight);
	}

	public double getLeftStickX()
	{             
		return driverXbox360Controller.getX(Hand.kLeft);
	}
	
	public double getRightStickX()
	{
		return driverXbox360Controller.getX(Hand.kRight);
	}
	
	public double getRightTrigger()
	{
		return driverXbox360Controller.getTriggerAxis(Hand.kRight);
	}

	public double GetPOV(XboxController stickToCheck) {
		return stickToCheck.getPOV();
	}
	
	public double getLeftTrigger()
	{
		return driverXbox360Controller.getTriggerAxis(Hand.kLeft);
	}
	
	public double getDialAxis()
	{
		return 0;
	}
	
	public double CoPilotRightStickY() {
		return coPilotxbox360Controller.getY(Hand.kRight);
	}
	
	public double CoPilotLeftStickY() {
		return coPilotxbox360Controller.getY(Hand.kLeft);
	}	
	public boolean CoPilotLeftBumper() {
		return coPilotxbox360Controller.getBumper(Hand.kLeft);
	}
	
	/**
	 * Garrett Methods
	 */
//	public double garrettDriverThrottle() {
//		return 
//	}
	
	
	
}