package org.usfirst.frc.team3574.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lifter extends Subsystem {

	TalonSRX liftSim = new TalonSRX(RobotMap.LiftMotor); 
	final int slotIdx = RobotMap.LiftMotor;
	final int timeoutMs = 50;
	final double kP = 1.0;
	final double kI = 1.0;
	final double kD = 1.0;
	
	//not the actual values
	public static int scaleLow = 3574;
	public static int scaleMed = 5743;
	public static int scaleHigh = 7535;
	public static int switchLow = 4357;
	public static int switchHigh = 5347;
	
	public Lifter() {
		//guessing that the "slotIdx" number is the same as the Id/Device number
		liftSim.config_kP(slotIdx, kP, timeoutMs);
		liftSim.config_kI(slotIdx, kI, timeoutMs);
		liftSim.config_kD(slotIdx, kD, timeoutMs);
		
		
			
	}
/***
	public enum LifterHeights {

		SCALE_LOW (scaleLow), SCALE_MED (scaleMed), SCALE_HIGH (scaleHigh), SWITCH_LOW (switchLow), SWITCH_HIGH (switchHigh);
		private int numVal;

		LifterHeights(int numVal) {
			this.numVal = numVal;
		}

		public int getNumVal() {
			return numVal;
		}


	}

	public void initDefaultCommand() {

	}

	public int getEncoderValue() {

		return liftSim.getSensorCollection().getPulseWidthPosition();
	}

	public void setToSETMotorValues(int setPosition) {
		 setLifterPosition(setPosition);
		switch (setPosition) {
		   
		    
		case (scaleLow):
			
			setLifterPosition(scaleLow);			
			break;

		case scaleMed:
			
			setLifterPosition(scaleMed);
			break;
			
		case scaleHigh:
			
			setLifterPosition(scaleHigh);
			break;

		case switchLow:
			setLifterPosition(switchLow);			
			break;

		case switchHigh:
			
			setLifterPosition(switchHigh);			
			break;


		default:
			break;

		}***/

	public void setLifterPosition(int setPoint) {
		liftSim.set(ControlMode.Position, setPoint);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}

