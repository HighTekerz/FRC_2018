/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3574.robot;


import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.DriveByPID;
import org.usfirst.frc.team3574.commands.driveTrain.MakeMotionProflileGo;
import org.usfirst.frc.team3574.commands.driveTrain.ShiftGear;
import org.usfirst.frc.team3574.commands.groups.PutCubeInSwitch;
import org.usfirst.frc.team3574.commands.slide.NewSlidePositionDown;
import org.usfirst.frc.team3574.commands.slide.NewSlidePositionUp;
import org.usfirst.frc.team3574.commands.slide.SetSlidePosition;
import org.usfirst.frc.team3574.commands.slide.SlideByInches;
import org.usfirst.frc.team3574.commands.slide.TestSlide;
import org.usfirst.frc.team3574.commands.slide.TestSlide2;
import org.usfirst.frc.team3574.triggers.POVBottomRange;
import org.usfirst.frc.team3574.triggers.POVTopRange;

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
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by its isFinished method.
	// button.whenReleased(new ExampleCommand());

	XboxController driverXbox360Controller = new XboxController(0);
	XboxController coPilotxbox360Controller = new XboxController(1);
	//	Joystick logitechAttack = new Joystick(3);

	//	Joystick driverXbox360Controller = new Joystick(0);
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

	int pov = 0;

	public OI() {

		Button button1 = new JoystickButton(driverXbox360Controller, A_BUTTON);
		button1.whenPressed(new SetSlidePosition(-100000));
		Button button2 = new JoystickButton(driverXbox360Controller, B_BUTTON);
		button2.whenPressed(new SetSlidePosition(100000));
		Button button3 = new JoystickButton(driverXbox360Controller, X_BUTTON);
		button3.whenPressed(new TestSlide());
		Button button4 = new JoystickButton(driverXbox360Controller, Y_BUTTON);
		button4.whenPressed(new TestSlide2());
		
		Button shiftHighGear = new POVTopRange(driverXbox360Controller, pov);
		shiftHighGear.whenPressed(new ShiftGear());
		
		SmartDashboard.putData(new SlideByInches(2));
		
//		SmartDashboard.putData(new DriveByPID(20000));
//		
//		SmartDashboard.putData(new PutCubeInSwitch());
//		SmartDashboard.putData(new MakeMotionProflileGo());
	}

	public double getLeftStickY ()
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
		return coPilotxbox360Controller.getRawAxis(5);
	}
	
	
	
	
	
	
	
	
}