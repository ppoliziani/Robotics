package FinalRobot;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class FollowLine implements Behavior {
	
	private int AVOID_THIS_DISTANCE = 15;
	
	private EV3UltrasonicSensor ultraSensor = new EV3UltrasonicSensor(SensorPort.S4);
	private SampleProvider sp = ultraSensor.getDistanceMode();
	private float[] distance = new float[1];

	private MovePilot pilot;
    	private Thread QRwatcher;
   	private Thread Colourwatcher;
	FollowLine(MovePilot mp, Thread QRwatcher, Thread Colourwatcher) { 
		this.pilot = mp;
		this.QRwatcher = QRwatcher;
		this.Colourwatcher = Colourwatcher;
	}
	
	public void action() {
		QRwatcher.start();
		Colourwatcher.start();
		pilot.forward();
	}

	public void suppress() {}
	
	public boolean takeControl() {
		Connection con = new Connection();
		sp.fetchSample(distance, 0);
		return (con.getConnected() | distance[0] > AVOID_THIS_DISTANCE);
	}
}
