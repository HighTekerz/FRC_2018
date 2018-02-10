package org.usfirst.frc.team3574.subsystems;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Solenoid Claw = new Solenoid(RobotMap.ClawSolenoid);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /*
     * Elbow
     */
    
    
    /*
     * 
     */
}

