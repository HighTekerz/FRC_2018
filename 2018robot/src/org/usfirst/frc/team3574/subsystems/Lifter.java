package org.usfirst.frc.team3574.subsystems;

import java.lang.invoke.SwitchPoint;

import org.usfirst.frc.team3574.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lifter extends Subsystem {

	TalonSRX liftSim = new TalonSRX(RobotMap.LiftMotor); 


	public enum LifterHeights {
		//not the actual values
		SCALE_LOW (3574), SCALE_MED (5743), SCALE_HIGH (7435), SWITCH_LOW (4357), SWITCH_HIGH (5347);
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

	public void setToSETMotorValues(LifterHeights setPosition) {
		switch (setPosition) {
		case SCALE_LOW:
			
			setLifterPosition(LifterHeights.SCALE_LOW.numVal);			
			break;

		case SCALE_MED:
			
			setLifterPosition(LifterHeights.SCALE_MED.numVal);
			break;
			
		case SCALE_HIGH:
			
			setLifterPosition(LifterHeights.SCALE_HIGH.numVal);
			break;

		case SWITCH_LOW:
			setLifterPosition(LifterHeights.SWITCH_LOW.numVal);			
			break;

		case SWITCH_HIGH:
			
			setLifterPosition(LifterHeights.SWITCH_HIGH.numVal);			
			break;


		default:
			break;

		}
	
	}

	private void setLifterPosition(int setPoint) {
		liftSim.set(ControlMode.Position, setPoint );
	}
	
	
	
	
	
	
	
}

