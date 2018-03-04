package org.usfirst.frc.team3574.robot;

import edu.wpi.first.wpilibj.DriverStation;


public class FieldFunctions {

	public enum FieldElementToCheck {
		OURSWITCH, SCALE, THEIRSWITCH
	}
	
	public FieldFunctions(double aMeaninglessNumber) {
		
	}

	/**
	 * 
	 * @param fieldElementToCheck Send a value from the FieldElementToCheck Enum
	 * @return String "Left" or "Right"
	 */
	public static String getOurSide(FieldElementToCheck fieldElementToCheck) {

		String gameDataString = DriverStation.getInstance().getGameSpecificMessage();

		switch (fieldElementToCheck) {
		
		case OURSWITCH:
			if (gameDataString.charAt(0) == 'L'){
				System.out.println("<<1 Left of close switch is ours");
				return "Left";
			}
			else {
				System.out.println("2>> Right of close switch is ours");
				return "Right";
			}
		
		case SCALE:
			if (gameDataString.charAt(1) == 'L'){
				System.out.println("<<1 Left of scale is ours");
				return "Left";
			}
			else {
				System.out.println("2>> Right of scale is ours");
				return "Right";
			}

		case THEIRSWITCH: 
			if (gameDataString.charAt(2) == 'L'){
				System.out.println("<<1 Left of far switch is ours");
				return "Left";
			}
			else {
				System.out.println("2>> Right of far switch is ours");
				return "Right";
			}
			
		default: return "No";
		
		}
		
		/***need to have a command or something for auto that creates a new FieldFunctions and 
		 * calls the getOurSide method on OURSWITCH to determine which side/autonomous thing to run
		 ***/
	}

}