package org.usfirst.frc.team3574.subsystems;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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

	byte[] dataBuffer = new byte[6];
	ByteBuffer compBuffer = ByteBuffer.wrap(dataBuffer);
	Ultrasonic ultraHedgehog = new Ultrasonic(0, 1, Unit.kInches);
	I2C iTooCanSee = new I2C(Port.kOnboard, 0x13);

	public SensorTest() {
		ultraHedgehog.setAutomaticMode(true);
	
	}
	public void log () {
		SmartDashboard.putNumber("UltraHedgehog thing", ultraHedgehog.getRangeInches());

	  	iTooCanSee.read(0x03, 6, dataBuffer);
      	
    	compBuffer.order(ByteOrder.BIG_ENDIAN);
    	
    	SmartDashboard.putNumber("cX", compBuffer.getShort());
    	SmartDashboard.putNumber("cY", compBuffer.getShort());
    	SmartDashboard.putNumber("cZ", compBuffer.getShort());

	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.


	public void initDefaultCommand() {


		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
}

