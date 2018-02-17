package org.usfirst.frc.team3574.subsystems;

//import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
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
	static I2C iTooCanSee = new I2C(Port.kOnboard, 0x13);
	DigitalInput proximitySensor = new DigitalInput(9);
	DigitalInput anotherProximitySensor = new DigitalInput(8);
	
	public boolean IsWithinRange(){
		return proximitySensor.get() && anotherProximitySensor.get();	
	}
	
	public SensorTest() {
		ultraHedgehog.setAutomaticMode(true);
	
	}
	public void log () {
		SmartDashboard.putNumber("UltraHedgehog thing", ultraHedgehog.getRangeInches());
        SmartDashboard.putBoolean("andanothersensor", !proximitySensor.get());
		SmartDashboard.getBoolean("Even Another Sensor Like How Many Of These Do We Need?", anotherProximitySensor.get());
		
//	  	iTooCanSee. read(0x27, 2, dataBuffer);
      	
    	//compBuffer.order(ByteOrder.BIG_ENDIAN);
    	
    	//SmartDashboard.putNumber("cX", compBuffer.getShort());
    	//SmartDashboard.putNumber("cY", compBuffer.getShort());
    	//SmartDashboard.putNumber("cZ", compBuffer.getShort());
    //	SmartDashboard.putNumber("cZ", compBuffer.getShort());

		// Create I2CBus
				// Select command register
				// Enables ALS and proximity measurement, LP oscillator
				iTooCanSee.write(0x80, (byte)0xFF);
				// Select proximity rate register
				// 1.95 proximity measurement / s
				iTooCanSee.write(0x82, (byte)0x00);
				// Select ALS register
				// Continuos conversion mode, ALS rate 2 samples / s
				iTooCanSee.write(0x84, (byte)0x9D);

				// Read 4 bytes of data
				// luminance msb, luminance lsb, proximity msb, proximity lsb
				byte[] data = new byte[4];
				iTooCanSee.read(0x85, 4, data);

				// Convert the data
				int luminance = ((data[0] & 0xFF) * 256) + (data[1] & 0xFF);
				int proximity = ((data[2] & 0xFF) * 256) + (data[3] & 0xFF);
				
				SmartDashboard.putNumber("Proximity of the Device", proximity);
				SmartDashboard.putNumber("Ambient Light Luminance In Lux", luminance);
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public void initDefaultCommand() {


		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
}

