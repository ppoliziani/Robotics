package FinalRobot;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class FL {
	
	final static float WHEEL_DIAMETER = 56;
	final static float AXEL_LENGTH = 117;
	final static double LIGHT_AVERAGE = 0.25;
	final static double PROPORTIONAL_CONSTANT = 0.6;
	
	public static void main(String[] args) {
		
		MovePilot pilot = getPilot(MotorPort.A, MotorPort.B, WHEEL_DIAMETER, AXEL_LENGTH); 
		pilot.setLinearSpeed(100);
		pilot.setAngularAcceleration(100); //Sets the acceleration at which the robot will accelerate at the start of a move and decelerate at the end of a move
		
		Thread watcher = new ColourWatcher(pilot);
		

		//for value colour sensor reads, subtract the average = result
		//multiply that result by a proportional constant, how much is will correct and turn smoothly or sharply
		//following on right side make the multiplier negative, positive on left
		
		watcher.start();
		pilot.travel(5000);
		
	}
			
	
	public static MovePilot getPilot(Port left, Port right, float diam, float offset) {
		
		
		BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(left);
		Wheel wLeft = WheeledChassis.modelWheel(mLeft, diam).offset(-offset);
		
		BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(right);
		Wheel wRight = WheeledChassis.modelWheel(mRight, diam).offset(offset);
		
		Chassis chassis = new WheeledChassis(new Wheel[] {wRight,wLeft},WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot mp = new MovePilot(chassis);
		
		return mp;
	}

}
