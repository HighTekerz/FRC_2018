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
	public static int ShifterSolenoid0 = 0;
	public static int ShifterSolenoid1 = 1;
	public static int ClawSolenoid = 2;
	public static int WristSolenoid = 3;
	public static int BrakeSolenoid = 4;
	public static int LeftLifterSolenoid = 5;
	public static int RightLifterSolenoid = 6;
	
		//TalonSRX
	public static int DriveTrainLeftTalon1 = 2; //2 on Frenzy
	public static int DriveTrainLeftTalon2 = 4; //4 on Frenzy
	public static int ArmMotor = 5; //5 on Frenzy
	public static int SlideMotor = 6; //6 on Frenzy
	public static int DriveTrainRightTalon1 = 1; //1 on Frenzy
	public static int DriveTrainRightTalon2 = 3; //3 on Frenzy
		
		//Sparks
	public static int LeftLifterReleaseSpark = 0;
	public static int RightLifterReleaseSpark = 1;
	
		//SensorsS
	public static int FloorCubeSensorLeft = 0;
	public static int FloorCubeSensorRight = 1;
	public static int ClawCubeSensorLeft = 2;
	public static int ClawCubeSensorRight = 3;
	public static int ArmLimitSwitch = 4;
	
}