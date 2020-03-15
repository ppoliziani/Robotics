package FinalRobot;

import lejos.hardware.Button;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.Sound;

public class RoboBop {
	
	public static Arbitrator ab;
	
	final static float WHEEL_DIAMETER = 56;
	final static float AXEL_LENGTH = 117;
	
	public static void main(String[] args) {
		
		MovePilot mp = getPilot(MotorPort.A, MotorPort.B, WHEEL_DIAMETER, AXEL_LENGTH); 
		mp.setLinearSpeed(50);
		
		Thread watcher = new QRThread(mp);
		
		Behavior connection = new Connection();
		Behavior followline = new FollowLine(mp, watcher);
		Behavior straight = new St(mp, watcher);
		Behavior bs = new BatterySaver();
		// Behaviour order: {connection, followline, interpretQR, batterylevel}
		
		ab = new Arbitrator(new Behavior[] {connection, followline}); 
		ab.go();
	
	}
	
	public Arbitrator getArbitrator() {
		return ab;
	}
	
	
	public static MovePilot getPilot(Port left, Port right, float diam, float offset) {
				
		BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(left);
		Wheel wLeft = WheeledChassis.modelWheel(mLeft, diam).offset(-offset);
		
		BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(right);
		Wheel wRight = WheeledChassis.modelWheel(mRight, diam).offset(offset);
		
		Chassis chassis = new WheeledChassis(new Wheel[] {wRight,wLeft},WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot mp = new MovePilot(chassis);
		
		return new MovePilot(chassis);
	}

}