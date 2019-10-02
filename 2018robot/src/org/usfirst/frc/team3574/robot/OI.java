/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3574.robot;


import org.usfirst.frc.team3574.commands.arm.Arm10DegreesForward;
import org.usfirst.frc.team3574.commands.arm.CalibrateArmEncStartingPosition;
import org.usfirst.frc.team3574.commands.arm.SetWristPosition;
import org.usfirst.frc.team3574.commands.claw.SetClawPosition;
import org.usfirst.frc.team3574.commands.driveTrain.ShiftGear;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree;
import org.usfirst.frc.team3574.commands.groups.DropCubeInScale;
import org.usfirst.frc.team3574.commands.groups.MoveToStartingPosition;
import org.usfirst.frc.team3574.commands.groups.DropForExchange;
import org.usfirst.frc.team3574.commands.groups.pickup.CalibratePickup;
import org.usfirst.frc.team3574.commands.groups.pickup.GoToPositionPickup;
import org.usfirst.frc.team3574.commands.groups.pickup.BlindPickup;
import org.usfirst.frc.team3574.commands.slide.SetSlidePosition;
import org.usfirst.frc.team3574.commands.util.RumbleASide;
import org.usfirst.frc.team3574.enums.ClawPosition;
import org.usfirst.frc.team3574.enums.ShifterPosition;
import org.usfirst.frc.team3574.enums.WristPosition;
import org.usfirst.frc.team3574.triggers.POVDown;
import org.usfirst.frc.team3574.triggers.POVLeft;
import org.usfirst.frc.team3574.triggers.POVRight;
import org.usfirst.frc.team3574.triggers.POVUp;
import org.usfirst.frc.team3574.triggers.TriggerButton;
import edu.wpi.first.wpilibj.GenericHID.Hand;
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

	static final int A_BUTTON = 1,
			B_BUTTON = 2,
			X_BUTTON = 3,
			Y_BUTTON = 4,
			LEFT_BUMPER = 5,
			RIGHT_BUMPER = 6,
			BACK = 7,
			START = 8,
			RIGHT_THUMBSTICK_BUTTON = 9,
			LEFT_THUMBSTICK_BUTTON = 10,
			LEFT_TRIGGER = 2,	
			RIGHT_TRIGGER = 3;
	int pov = 0;

	public OI() {
		/**
		 * <<DRIVER CONTROLLER>>
		 * 
		 * Controls drivetrain and pickup
		 */

		Button calibratePickup = new 
				TriggerButton(driverXbox360Controller, LEFT_TRIGGER);
		calibratePickup.whenPressed(new CalibratePickup());

//		Button dropForExchange = new 
//				JoystickButton(driverXbox360Controller, RIGHT_BUMPER);
//		dropForExchange.whenPressed(new DropForExchange());

		Button shiftHigh = new 
				POVUp(driverXbox360Controller, pov);
		shiftHigh.whenPressed(new ShiftGear(ShifterPosition.HIGH_GEAR));

		Button shiftLow = new 
				POVDown(driverXbox360Controller, pov);
		shiftLow.whenPressed(new ShiftGear(ShifterPosition.LOW_GEAR));
		
		Button closeThenStraighten2 = new 
				JoystickButton(driverXbox360Controller, X_BUTTON);
		closeThenStraighten2.whenPressed(new SetClawPosition(ClawPosition.GRIP));
		closeThenStraighten2.whenReleased(new SetWristPosition(WristPosition.STRAIGHT));
		
		
		
		
//		 


		//		JoystickButton pickUp = new JoystickButton(driverXbox360Controller, RIGHT_BUMPER);


		/**
		 *<<CO-PILOT CONTROLLER>>
		 *
		 *Controls all arm and slide functions except pickup
		 */

		Button angleWrist = new 
				POVDown(coPilotxbox360Controller, pov);
		angleWrist.whenPressed(new SetWristPosition(WristPosition.ANGLED));

		Button straightenWrist = new 
				POVUp(coPilotxbox360Controller, pov);
		straightenWrist.whenPressed(new SetWristPosition(WristPosition.STRAIGHT));

		Button openClaw = new 
				POVLeft(coPilotxbox360Controller, pov);
		openClaw.whenPressed(new SetClawPosition(ClawPosition.RELEASE));

		Button closeClaw = new
				POVRight(coPilotxbox360Controller, pov);
		closeClaw.whenPressed(new SetClawPosition(ClawPosition.GRIP));

		Button closeThenStraighten = new 
				JoystickButton(coPilotxbox360Controller, X_BUTTON);
		closeThenStraighten.whenPressed(new SetClawPosition(ClawPosition.GRIP));
		closeThenStraighten.whenReleased(new SetWristPosition(WristPosition.STRAIGHT));


		/**
		 * <<SMARTDASHBOARD>>
		 */

		SmartDashboard.putData(new TurnToDegree(45, .5));
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
}