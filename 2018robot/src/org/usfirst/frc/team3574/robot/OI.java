/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3574.robot;

import org.usfirst.frc.team3574.commands.DriveByInches;
import org.usfirst.frc.team3574.commands.PowerSomeMotors;
import org.usfirst.frc.team3574.commands.TurnToDegree;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	XboxController xbox = new XboxController(0);
	XboxController testBox = new XboxController(1);


	//	Joystick xbox = new Joystick(0);
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


	public OI() {
		Button driveOneRotationForeward = new JoystickButton(xbox, A_BUTTON);
			driveOneRotationForeward.whenPressed(new DriveByInches(3 * Math.PI, -0.5));
		Button driveOneRotationBackward = new JoystickButton(xbox, Y_BUTTON);
			driveOneRotationBackward.whenPressed(new DriveByInches(3 * Math.PI, 0.5));
		Button turnNinetyDegreesRight = new JoystickButton(xbox, X_BUTTON);
			turnNinetyDegreesRight.whenPressed(new TurnToDegree(90, 0.4));
		Button turnNinetyDegreesLeft = new JoystickButton(xbox, B_BUTTON);
			turnNinetyDegreesLeft.whenPressed(new TurnToDegree(-90, 0.4));
		Button turnFifteenRight = new JoystickButton(xbox, RIGHT_BUMPER);
			turnFifteenRight.whenPressed(new TurnToDegree(-10, 0.4));
		Button turnFifteenLeft = new JoystickButton(xbox, LEFT_BUMPER);
			turnFifteenLeft.whenPressed(new TurnToDegree(10, 0.4));
			
			
	}



	public double getLeftStickY ()
	{
		return xbox.getY(Hand.kLeft);
	}
	public double getRightStickY()
	{
		return -xbox.getY(Hand.kRight);
	}

	public double getLeftStickX()
	{
		return xbox.getX(Hand.kLeft);
	}
	public double getRightStickX()
	{
		return xbox.getX(Hand.kRight);
	}
}