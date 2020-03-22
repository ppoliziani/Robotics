package ROBOBOP;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class RoboBop {
	
	public static Arbitrator ab;
	
	public static void main(String[] args) {
		
		BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
		BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.B);
		
		Thread QRwatcher = new QRThread(mLeft, mRight);
		Thread Colourwatcher = new ColourWatcher(mLeft, mRight);		
		
		Behavior connection = new Connection();
		Behavior followline = new FollowLine(mLeft, mRight, QRwatcher, Colourwatcher);
		Behavior battery = new BatterySaver(mLeft, mRight);
		
		ab = new Arbitrator(new Behavior[] {connection, followline, battery}); 
		ab.go();
	
	}	

}