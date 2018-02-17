package org.usfirst.frc.team3574.robot;

import edu.wpi.first.wpilibj.DriverStation;


public class FieldFunctions {

	public enum FieldElementToCheck {
		OURSWITCH, SCALE, THEIRSWITCH
	}
	public FieldFunctions(double aMeaninglessNumber) {
		
	}

	public static String getOurSide(FieldElementToCheck fieldElementToCheck) {

		String gameDataString = DriverStation.getInstance().getGameSpecificMessage();

		switch (fieldElementToCheck) {
		
		case OURSWITCH:
			if (gameDataString.charAt(1) == 'L'){
				return "Left";
			}
			else {
				return "Right";
			}
		
		case SCALE:
			if (gameDataString.charAt(2) == 'L'){
				return "Left";
			}
			else {
				return "Right";
			}

		case THEIRSWITCH: 
			if (gameDataString.charAt(3) == 'L'){
				return "Left";
			}
			else {
				return "Right";
			}
			
		default: return "No";
		
		}
		/***need to have a command or something for auto that creates a new FieldFunctions and 
		 * calls the getOurSide method on OURSWITCH to determine which side/autonomous thing to run
	***/
	}

}