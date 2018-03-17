package org.usfirst.frc.team3574.triggers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 */
public class POVLeft extends Button {
	
	 GenericHID m_joystick;
	 int m_POV;

	public POVLeft(GenericHID joystick, int pov) {
       m_joystick = joystick;
       m_POV = pov;
	}
	
	public boolean get() {
       if (m_joystick.getPOV(m_POV) == 270) {
    	   System.out.println("POVLeft pressed, 180 degrees");
			return true;
		} else {
			return false;
		}
	}
}
