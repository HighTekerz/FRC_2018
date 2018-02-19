package org.usfirst.frc.team3574.subsystems;

import org.usfirst.frc.team3574.commands.sensors.GetHedgehogDistance;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TheHedgehog extends Subsystem {
	AnalogInput theHedgehog = new AnalogInput(0);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new GetHedgehogDistance());
    }
    
    public double getActualDistance() {
    	return theHedgehog.getVoltage();
    }
}

