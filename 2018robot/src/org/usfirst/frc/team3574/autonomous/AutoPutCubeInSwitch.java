package org.usfirst.frc.team3574.autonomous;

import org.usfirst.frc.team3574.commands.driveTrain.DriveByInches;
import org.usfirst.frc.team3574.commands.driveTrain.TurnToDegree;
import org.usfirst.frc.team3574.commands.groups.PutPCubeInSwitch;
import org.usfirst.frc.team3574.robot.FieldFunctions;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * attempt of questionable quality to create code for putting a power cube into the switch during autonomous
 * feel free to destroy if it's unneeded or unsalvageable
 * yes unsalvageable is spelled correctly this program is just weird
 */
public class AutoPutCubeInSwitch extends CommandGroup {

    public AutoPutCubeInSwitch(double degreeToTurnTo) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	   
    	   
//    	   if (new FieldFunctions.getOurSide(FieldFunctions.FieldElementToCheck.OURSWITCH) == "Left") {
//    		   addSequential(new TurnToDegree(90, 0.5));
//    	   }
    	
    	//why is that code there this wasn't something i remember writing where did it come from
    	//did someone else make something with this name already and this just hijacked it???
    	
    	
//    	addSequential(new DriveByInches(12, 0.4));
//    	addSequential(new DropACubeOntoSwitch());
    	addSequential(new DriveByInches(12, 0.4)); //placeholder values for driving across auto line
    	addSequential(new TurnToDegree(degreeToTurnTo, 0.4)); 
    	//the degreeToTurnTo will be received from whatever is calling the getOurSide methods
    	addSequential(new DriveByInches(12, 0.4)); //placeholder values again, distance to reach end of switch
    	//note: maybe the distances can vary as well to allow different starting positions?
    	addSequential(new TurnToDegree(-degreeToTurnTo, 0.4)); //turns back towards switch
    	addSequential(new PutPCubeInSwitch());
    	
    }
}
