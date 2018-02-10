package org.usfirst.frc.team3574.subsystems;

import java.lang.invoke.SwitchPoint;

import org.usfirst.frc.team3574.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

	TalonSRX liftSim = new TalonSRX(RobotMap.LiftMotor); 


	public enum LifterHeights {
		//not the actual values yet
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

	public void setToSETMotorValues(LifterHeights setValues) {
		switch (setValues) {
		case SCALE_LOW:

			break;

		case SCALE_MED:

			break;
			
		case SCALE_HIGH:

			break;

		case SWITCH_LOW:

			break;

		case SWITCH_HIGH:
			
			break;


		default:
			break;

		}
	}

}

