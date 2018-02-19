package org.usfirst.frc.team3574.subsystems;

//import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.usfirst.frc.team3574.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SensorTest extends Subsystem {

	Ultrasonic ultraHedgehog = new Ultrasonic(0, 1, Unit.kInches);
	I2C iTooCanSee = new I2C(Port.kOnboard, 0x13);
	DigitalInput proximitySensor = new DigitalInput(9);
	DigitalInput anotherProximitySensor = new DigitalInput(8);
	
	public boolean IsWithinRange(){
		return proximitySensor.get() && anotherProximitySensor.get();	
	}
	
//	TalonSRX FreShaVaCaDo = new TalonSRX (RobotMap.FreshAvocado);

	
	public SensorTest() {
		ultraHedgehog.setAutomaticMode(true);
//		FreShaVaCaDo.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor1, 0, 10);
	}
	public void log () {
		SmartDashboard.putNumber("UltraHedgehog thing", ultraHedgehog.getRangeInches());
        SmartDashboard.putBoolean("andanothersensor", !proximitySensor.get());
		SmartDashboard.getBoolean("Even Another Sensor Like How Many Of These Do We Need?", anotherProximitySensor.get());
		
	}
	public void initDefaultCommand() {


		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
}

