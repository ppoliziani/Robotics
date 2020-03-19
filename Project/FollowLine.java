package FinalRobot;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class FollowLine implements Behavior {
	
    private MovePilot pilot;
    private Thread QRwatcher;
    private Thread Colourwatcher;
	FollowLine(MovePilot mp, Thread QRwatcher, Thread Colourwatcher) { 
		this.pilot = mp;
		this.QRwatcher = QRwatcher;
		this.Colourwatcher = Colourwatcher;
	}
	
	public void action() {
		pilot.forward();
		QRwatcher.start();
		Colourwatcher.start();
	}

	public void suppress() {}
	
	public boolean takeControl() {
		Connection con = new Connection();
		return (con.getConnected());
	}
	

}
