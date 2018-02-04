package org.usfirst.frc.team3574.commands.driveTrain;

import org.usfirst.frc.team3574.robot.Robot;
import org.usfirst.frc.team3574.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveByHedgehog2Distance extends Command {

	private double targetSensorReading,
	speedOfRobot;

	public DriveByHedgehog2Distance(double inchesFromObject, double speed)  {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrain);

		/*
		 * 1' = .31/closest you can go
		 * 1'4" = .40
		 * 2' = .57
		 * 3' = .86
		 * 16'8" = 4.67/farthest can go
		 * 
		 */
		System.out.println("hi");

		this.speedOfRobot = speed;

		if (inchesFromObject < 12) {

			//throw new Exception("NO, NOTHING LESS THAN 12 INCHES");
		}   else if (inchesFromObject > 200) {

			//throw new Exception("NO, NOTHING PAST 16' and 8\"");
		} else {

			//0.023 = calculated slope of line
			//0.31 = 12"/1' (closest the sensor can see to an object)
			this.targetSensorReading = ((inchesFromObject - 12) * 0.023) + 0.31;
		}




	}



	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("hi");
		Robot.driveTrain.driveByArcade(speedOfRobot, 0.0);

		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//System.out.println("drivebyhedgehog EXECUTE");
		System.out.println(((Robot.theHedgehog.getActualDistance() - 0.31)/0.023) + 12);
		System.out.println();
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		if (speedOfRobot > 0 && Robot.theHedgehog.getActualDistance() <= this.targetSensorReading) {
			Robot.driveTrain.driveByArcade(0.0, 0.0);	
			System.out.println("hey you got close to the cube");
			return true;


		} else if (speedOfRobot < 0 && Robot.theHedgehog.getActualDistance() >= this.targetSensorReading) {

			Robot.driveTrain.driveByArcade(0.0, 0.0);	
			System.out.println("why are you going backwards?");
			return true;

		}
		else {return false;}


	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
