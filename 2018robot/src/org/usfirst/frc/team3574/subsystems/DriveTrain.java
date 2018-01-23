package org.usfirst.frc.team3574.subsystems;

import org.usfirst.frc.team3574.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3574.commands.driveTrain.*;

/**
 *
 */
public class DriveTrain extends Subsystem {
	TalonSRX motorLeft1 = new TalonSRX(RobotMap.DriveTrainLeftTalon1);
	TalonSRX motorLeft2 = new TalonSRX(RobotMap.DriveTrainLeftTalon2);
	TalonSRX motorRight1 = new TalonSRX(RobotMap.DriveTrainRightTalon1);
	TalonSRX motorRight2 = new TalonSRX(RobotMap.DriveTrainRightTalon2);
	
	public DriveTrain() {
		// TODO Auto-generated constructor stub
		motorLeft1.set(ControlMode.PercentOutput, 0.0);
		motorLeft2.set(ControlMode.PercentOutput,  0.0);
		motorRight1.set(ControlMode.PercentOutput,  0.0);
		motorRight2.set(ControlMode.PercentOutput,  0.0);


	}
 public int getEncoderLeft()
 {
	 return motorLeft1.getSensorCollection().getPulseWidthPosition();
	 
 }
 
 public int getEncoderRight()
 {
	 return motorRight1.getSensorCollection().getPulseWidthPosition();
 }
 
 // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoy());
    }
    
    //Drives the robot using seperate inputs for the left and right side motors.
    //Inputs are percentages of maximum motor output.
    public void driveByTank (double leftSpeed, double rightSpeed)	
    {
    	motorLeft1.set(ControlMode.PercentOutput, leftSpeed);
    	motorLeft2.set(ControlMode.PercentOutput, leftSpeed);
    	
    	motorRight1.set(ControlMode.PercentOutput, rightSpeed);
    	motorRight2.set(ControlMode.PercentOutput, rightSpeed);
    }    
    
    //Controls speed and direction of the robot.
    // -1 = full reverse; 1 = full forward
    public void driveByArcade (double percentPowerOutput, double percentRotationOutput)
    {
    	motorLeft1.set(ControlMode.PercentOutput,-percentPowerOutput - percentRotationOutput);
    	motorLeft2.set(ControlMode.PercentOutput,-percentPowerOutput - percentRotationOutput);
    	
    	motorRight1.set(ControlMode.PercentOutput, percentPowerOutput - percentRotationOutput);
    	motorRight2.set(ControlMode.PercentOutput, percentPowerOutput - percentRotationOutput);
    }
    
     public void doNothing () 
     {
    	driveByTank(0.0, 0.0);
        
     }
    
    
}

