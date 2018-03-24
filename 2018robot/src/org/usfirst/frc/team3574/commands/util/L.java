package org.usfirst.frc.team3574.commands.util;

import java.util.HashMap;

import javax.security.auth.callback.LanguageCallback;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	public static void ogOnlyOnce(String key, double value) {
	
		
		try {
			if (value != logMap.get(key)) {
				L.og(key + "; " + value);
				logMap.put(key, value);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			logMap.put(key, value);
		}
		
		
		
	}
	private static HashMap<String, Double> logMap = new HashMap<String, Double>();
	private static HashMap<String, Double> timerLogmap = new HashMap<String, Double>();
	
	
	/**
	 * Print to the Console.
	 * @param s
	 */
	public static void og(String s) {
		System.out.println(s);
	}
	/**
	 * Print to the Console.
	 * @param d
	 */
	public static void og(double d) {
		System.out.println(d);
	}
	/**
	 * Print to the Console.		
	 * @param o
	 */
	public static void og(Object o) {
		System.out.println(o);
	}
	
	
	/**
	 * For debugging purposes, place in 'initialize' within a command. 
	 * @param c
	 */
	public static void ogCmdInit(Command c) {
		System.out.println("- INIT ------- " + c.getName());
	}
	/**
	 * For debugging purposes, place in 'initialize' within a command.
	 * @param c
	 * @param s
	 */
	public static void ogCmdInit(Command c, String s) {
		System.out.println("- INIT ------- " + c.getName() + ", s");
	}
	/**
	 * For debugging purposes, place in 'execute' within a command.
	 * @param c
	 */
	public static void ogCmdExec(Command c) {
		System.out.println("- Exec ------- " + c.getName());
	}
	/**
	 * For debugging purposes, place in 'end' within a command.
	 * @param c
	 */
	public static void ogCmdEnd(Command c) {
		System.out.println("-- END ------- " + c.getName());
	}
	/**
	 * For debugging purposes, place in 'interrupted' within a command.
	 * @param c
	 */
	public static void ogCmdInterrupted(Command c) {
		System.out.println("-- INTERRUPT - " + c.getName());
	}
	
	/**
	 * Put something on the SmartDashboard. (both integers and doubles.)
	 * @param key
	 * @param value
	 */
	public static void ogSD(String key, double value) {
		SmartDashboard.putNumber(key, value);
	}
	/**
	 * Put something on the SmartDashboard.
	 * @param key
	 * @param value
	 */
	public static void ogSD(String key, String value) {
		SmartDashboard.putString(key, value);
	}
	/**
	 * Put something on the SmartDashboard.
	 * @param key
	 * @param data
	 */
	public static void ogSD(String key, Sendable data) {
		SmartDashboard.putData(key, data);
	}
	
	/**
	 * Put something on the SmartDashboard.
	 * @param key
	 * @param data
	 */
	public static void ogSD(String key, boolean data) {
		SmartDashboard.putBoolean(key, data);
	}

}
