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
	
		//Solenoids
	public static int ShifterSolenoid = 0; //normally 0
	public static int ShifterSolenoid2 = 1; // normally 1
	public static int ClawSolenoid = 2; //normally 2
	public static int WristSolenoid = 3; //normally 3
	public static int BreakSolenoid = 4;
	public static int LeftLifterSolenoid = 5;
	public static int RightLifterSolenoid = 6;
	
		//TalonSRX
	public static int DriveTrainLeftTalon1 = 1; //normally 1
	public static int DriveTrainLeftTalon2 = 2;
	public static int ArmMotor = 3; //normally 3
	public static int SlideMotor = 4; //normally 4
	public static int DriveTrainRightTalon1 = 13; //normally 13
	public static int DriveTrainRightTalon2 = 12;
		
		//Sparks
	public static int LeftLifterReleaseSpark = 0;
	public static int RightLifterReleaseSpark = 1;
	
		//Sensors
	public static int Pigeon;//help???
	public static int ToF = 1;
	public static int IRR1 = 2;
	public static int IRR2 = 3;
	public static int Sonic1 = 4;
	public static int Sonic2 = 5;
	public static int ColorSensor = 6;
	public static int ClawLimitSwitch1 = 7;
	public static int ClawLimitSwitch2 = 8;  
}