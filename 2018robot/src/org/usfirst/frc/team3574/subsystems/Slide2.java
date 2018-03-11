package org.usfirst.frc.team3574.subsystems;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Slide2 extends Subsystem {

	public TalonSRX slideSim = new TalonSRX(RobotMap.SlideMotor); 

	DigitalInput HardStop_TheTop = new DigitalInput(RobotMap.SlideLimitSwitchTop);
	DigitalInput HardStop_TheDrop = new DigitalInput(RobotMap.SlideLimitSwitchBottom);

	

	public Slide2() {		
//		slideSim.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
		
		/* set the peak and nominal outputs, 12V means full */
//		slideSim.configNominalOutputForward(0, kTimeoutMs);
//		slideSim.configNominalOutputReverse(0, kTimeoutMs);
//		slideSim.configPeakOutputForward(1, kTimeoutMs);
//		slideSim.configPeakOutputReverse(1, kTimeoutMs);
		/*
		 * set the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
//		slideSim.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);
		
		
		/* set closed loop gains in slot0, typically kF stays zero. */
//		slideSim.config_kP(kPIDLoopIdx, 0.7, kTimeoutMs);
//		slideSim.config_kI(kPIDLoopIdx, 0.0, kTimeoutMs);
//		slideSim.config_kD(kPIDLoopIdx, 0.0, kTimeoutMs);
//		slideSim.config_kF(kPIDLoopIdx, 0.0, kTimeoutMs);

		
		
		/*
		 * lets grab the 360 degree position of the MagEncoder's absolute
		 * position, and initially set the relative sensor to match.
		 */
//		int absolutePosition = slideSim.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
//		absolutePosition &= 0xFFF;
//		if (kSensorPhase)
//			absolutePosition *= -1;
//		if (kMotorInvert)
//			absolutePosition *= -1;
		/* set the quadrature (relative) sensor to match absolute */
//		slideSim.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);

		
		
	}
	
int set;
	public void setSlidePosition(int setPoint) {
		set = setPoint;
		slideSim.set(ControlMode.Position, setPoint);
	}

	public void setSlideSpeed(double speed) {
		slideSim.set(ControlMode.PercentOutput, speed);
	}

	@Override
	protected void initDefaultCommand() {

	}

	public boolean getTopStopSwitchIsPressed() {
		return HardStop_TheTop.get();
	}

	public boolean getBottomStopSwitchIsPressed() {
		return HardStop_TheDrop.get();
	}

	public int getEncPos() {
		return slideSim.getSensorCollection().getPulseWidthPosition();
	}

//	public int filterBadNumbersWorkingTitle(int setPoint) {
//		int currentPoint = slideSim.getSensorCollection().getPulseWidthPosition();
//		if(getBottomStopSwitchIsPressed()) {
//			if(setPoint < currentPoint) {
//				return currentPoint;
//			}
//			else {
//				return setPoint;
//			}
//		}
//		else if(getTopStopSwitchIsPressed()) {
//			if(setPoint > currentPoint) {
//				return currentPoint;
//			}
//			else {
//				return setPoint;
//			}
//		}
//		else {
//			return currentPoint;
//		}
//	}

	public void log () {
		SmartDashboard.putNumber("Slide Encoder", getEncPos());
		SmartDashboard.putBoolean("Top Slide Limit Switch", HardStop_TheTop.get());
		SmartDashboard.putBoolean("Bottom Slide Limit Switch", HardStop_TheDrop.get());
		SmartDashboard.putNumber("Absolute Position", slideSim.getSensorCollection().getPulseWidthPosition()/1000000.0);
		
		SmartDashboard.putNumber("setpoint", set);
	}
}