package org.usfirst.frc.team3574.commands.arm;

import org.usfirst.frc.team3574.commands.util.L;
import org.usfirst.frc.team3574.enums.BrakePosition;
import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.Arm;
import org.usfirst.frc.team3574.subsystems.Slide;
import org.usfirst.frc.team3574.utilities.IArmSpeedSettings;
import org.usfirst.frc.team3574.utilities.ArmSpeedSettingsWithCube;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetArmPosition extends Command {
	Timer time = new Timer();
	private boolean log = false;
	private double enc = 0;

	IArmSpeedSettings _ISpeedSetting;

	private double _tickTarget;
	private double _targetPositionInDegrees;
	public double error;
	private double allowableError = 2 * Arm.TICKS_PER_DEGREE;
	private double slowPoint = 7 * Arm.TICKS_PER_DEGREE;

	private double _timeout;

	private double motorStop = 0.0;

	private boolean isFinished = false;

	private boolean isNotFirstTime = false;

	/**
	 * Command to move the Arm to different locations
	 * 
	 * @param degreesTarget Denotes . Pull your number from the arm subsystem (e.g. Robot.arm.AggressiveCobra)
	 */
	public SetArmPosition(double degreesTarget, IArmSpeedSettings ISpeedSetting) {
		requires(Robot.arm);
		_targetPositionInDegrees = degreesTarget;
		_ISpeedSetting = ISpeedSetting;
		_timeout = 20;
	}

	public SetArmPosition(double degreesTarget, IArmSpeedSettings ISpeedSetting, double timeout) {
		requires(Robot.arm);
		_targetPositionInDegrees = degreesTarget;
		_ISpeedSetting = ISpeedSetting;
		_timeout = timeout;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		L.ogInit(this);

		time.reset();
		time.start();

		Robot.arm.setSpeed(_ISpeedSetting.brakeSpeed);
		Robot.arm.setBrakePosition(BrakePosition.OPEN);
		_tickTarget = Arm.ARM_MOTOR_ZERO_POINT - (_targetPositionInDegrees * Arm.TICKS_PER_DEGREE);
		isFinished = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.arm.getEncPos() > enc - 2 && Robot.arm.getEncPos() < enc + 2) { 
			log = false;
		} else {
			log = true;
			enc = Robot.arm.getEncPos();
		}
		if(log) { 
			System.out.println("Tick Target: " + _tickTarget + " -- Encoder Value " + Robot.arm.getEncPos()); 

			error = Robot.arm.getEncPos() - _tickTarget;

			if(Math.abs(error) < allowableError) {
				isFinished = true;
				System.out.println("isFinished = true. error = " + error);
			} 
			else if(Math.abs(error) < slowPoint) {
				checkDirection(1);
			} 
			else {        	
				checkDirection(2);
			}
		}
	}


	double xSpeed;
	boolean DriveUp;
	private void checkDirection(int step) { 
		if(error > 0) {
			DriveUp = true;
		}
		else {
			DriveUp = false;
		}

		if(step == 1) {  //Going to Slow Speed
			if (DriveUp) {
				if(log)System.out.println("Running Slowed Speed UP");
				xSpeed = _ISpeedSetting.slowedSpeed;
			}
			else {
				if(log)System.out.println("Running Slowed Speed DOWN");
				xSpeed = _ISpeedSetting.slowedSpeedDown;
			}
		}
		else {
			if (DriveUp) {
				if(log)System.out.println("Running Max Speed UP");
				xSpeed = _ISpeedSetting.maxSpeed;
			}
			else {
				if(log)System.out.println("Running Max Speed DOWN");
				xSpeed = _ISpeedSetting.maxSpeedDown;
			}

		}
		
		L.og("Setting arm speed to: " + xSpeed);
		Robot.arm.setSpeed(xSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (this.timeSinceInitialized() >= _timeout) {
			return true;
		}
		return isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("SetArm Hit Target");
		Robot.arm.setBrakePosition(BrakePosition.CLOSED);
		Robot.arm.setSpeed(motorStop);
//				if (!isNotFirstTime)
//				{
//					isNotFirstTime = true;
//					this.start();
//				}
//				else {
//					isNotFirstTime = false;
//				}
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		L.ogInterrupt(this);
		Robot.arm.setBrakePosition(BrakePosition.CLOSED);
		Robot.arm.setSpeed(motorStop);
	}
}
