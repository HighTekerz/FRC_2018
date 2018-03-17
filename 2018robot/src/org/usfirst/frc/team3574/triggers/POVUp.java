package org.usfirst.frc.team3574.triggers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 */
public class POVUp extends Button {
	
	 GenericHID m_joystick;
	 int m_POV;

	public POVUp(GenericHID joystick, int pov) {
       m_joystick = joystick;
       m_POV = pov;
	}
	
	public boolean get() {
       
//		L.og("			POV VALUE: " + m_joystick.getPOV(m_POV));
		
       if (m_joystick.getPOV(m_POV) == 0) {
//       	L.og("LOW true");
			return true;
		} else {
//			L.og("LOW false");
			return false;
		}
	}
}
