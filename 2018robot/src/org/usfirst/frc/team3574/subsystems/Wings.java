package org.usfirst.frc.team3574.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Wings extends Subsystem {

	Solenoid leftSolenoid = new Solenoid(RobotMap.LeftWingSolenoid);
	Solenoid rightSolenoid = new Solenoid(RobotMap.RightWingSolenoid);
	Spark leftSpark = new Spark(RobotMap.LeftWingReleaseSpark);
	Spark rightSpark = new Spark(RobotMap.RightWingReleaseSpark);
	
	private final double setPosition= 5;
	
    public void initDefaultCommand() {
      
    	
    	
    	
    }
    public void setLeftSolenoid() {
    
    	leftSolenoid.set(true);
    }
    public void setRightSolenoid() {
    	
    	rightSolenoid.set(true);
    }
    public void setLeftSparkOpen() {
    	
    	leftSpark.setPosition(setPosition);
    }
    public void setRightSparkOpen() {
    	
    	rightSpark.setPosition(setPosition);
    }
}


