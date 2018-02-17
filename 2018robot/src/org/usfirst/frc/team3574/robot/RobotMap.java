/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3574.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public final class RobotMap {
	public static int ShifterSolenoid = 3; //normally 0
	public static int ShifterSolenoid2 = 2; // normally 1
	public static int ClawSolenoid = 0; //2
	public static int WristSolenoid = 1; //3
	public static int BreakSolenoid = 4;
	public static int LeftLifterSolenoid = 5;
	public static int RightLifterSolenoid = 6;
	
	public static int DriveTrainLeftTalon1 = 3; //normally 1
	public static int DriveTrainLeftTalon2 = 2;
	public static int ArmMotor = 1; //normally 3
	public static int SlideMotor = 13; //normally 4
	public static int DriveTrainRightTalon1 = 4; //normally 13
	public static int DriveTrainRightTalon2 = 12;
		
	public static int LeftLifterReleaseSpark = 0;
	public static int RightLifterReleaseSpark = 1;
}