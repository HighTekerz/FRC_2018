package org.usfirst.frc.team3574.triggers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 */
public class POVTopRange extends Button {

	GenericHID m_joystick;
	int m_POV;

	public POVTopRange(GenericHID joystick, int POV) {
       m_joystick = joystick;
       m_POV = POV;
	}
	
    public boolean get() {
		if (m_joystick.getPOV(m_POV) == 0 || m_joystick.getPOV(m_POV) == 45 || m_joystick.getPOV(m_POV) == 315) {
			return true;
		} else {
			return false;
		}
    }
}
