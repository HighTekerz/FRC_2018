package org.usfirst.frc.team3574.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SensorTest extends Subsystem {

	DigitalInput proximitySensor = new DigitalInput(9);
	DigitalInput anotherProximitySensor = new DigitalInput(8);
	
	public boolean IsWithinRange(){
		return proximitySensor.get() && anotherProximitySensor.get();	
	}
	
//	TalonSRX FreShaVaCaDo = new TalonSRX (RobotMap.FreshAvocado);

	
	public SensorTest() {
//		ultraHedgehog.setAutomaticMode(true);
//		FreShaVaCaDo.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor1, 0, 10);
	}
	public void log () {
//		SmartDashboard.putNumber("UltraHedgehog thing", ultraHedgehog.getRangeInches());
	}
	public void initDefaultCommand() {
	}
}