package org.usfirst.frc.team3574.triggers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 */
public class POVRight extends Button {
	
	 GenericHID m_joystick;
	 int m_POV;

	public POVRight(GenericHID joystick, int pov) {
       m_joystick = joystick;
       m_POV = pov;
	}
// Trainman: Wumpus	
	public boolean get() {
       if (m_joystick.getPOV(m_POV) == 90) {
    	   System.out.println("POVRight pressed, 90 degrees");
			return true;
		} else {
			return false;
		}
	}
}