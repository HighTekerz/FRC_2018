package org.usfirst.frc.team3574.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Wings extends Subsystem {

	Solenoid leftSolenoid = new Solenoid(RobotMap.LeftLifterSolenoid);
	Solenoid rightSolenoid = new Solenoid(RobotMap.RightLifterSolenoid);
	Spark leftSpark = new Spark(RobotMap.LeftLifterReleaseSpark);
	Spark rightSpark = new Spark(RobotMap.RightLifterReleaseSpark);
	
	//probably just a placeholder value
	private final double sparkOpenPosition= 5;
	
    public void initDefaultCommand() {
      
    	
    	
    	
    }
    public void setLeftSolenoid(boolean pistonIsOpen) {
    
    	leftSolenoid.set(pistonIsOpen);
    }
    public void setRightSolenoid(boolean pistonIsOpen) {
    	
    	rightSolenoid.set(pistonIsOpen);
    }
    public void setLeftSparkOpen() {
    	
    	leftSpark.setPosition(sparkOpenPosition);
    }
    public void setRightSparkOpen() {
    	
    	rightSpark.setPosition(sparkOpenPosition);
    }
}


