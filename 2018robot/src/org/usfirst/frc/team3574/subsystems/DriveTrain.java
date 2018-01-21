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

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoy());
    }
    public void driveByTank (double leftSpeed, double rightSpeed)	
    {
    	motorLeft1.set(ControlMode.PercentOutput, leftSpeed);
    	motorLeft2.set(ControlMode.PercentOutput, leftSpeed);
    	motorRight1.set(ControlMode.PercentOutput, rightSpeed);
    	motorRight2.set(ControlMode.PercentOutput, rightSpeed);
    }    
    
     public void doNothing () 
     {
    	driveByTank(0.0, 0.0);
        
     }
    
    
}

