package org.usfirst.frc.team3574.commands.util;

public class L {
	/**
	 * Put the initialization of a command on the console
	 * 
	 * @param className name of the command you want to declare as initialized
	 */
	public static void ogInit(Object className) {
		System.out.println(className + " init");
	}
	public static void ogExe(Object className) {
		System.out.println(className + " Execute");
	}
	public static void ogisFinished(Object className) {
		System.out.println(className + " IsFinished");
	}
	public static void ogInterrupt(Object className) {
		System.out.println(className + " inturupt");
	}
	public static void ogEnd(Object className) {
		System.out.println(className + " end");
	}
}
