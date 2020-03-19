package FinalRobot;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class ObjectDetection2 implements Behavior {

	private static int AVOID_THIS_DISTANCE = 15; // subject to change.
	
	private EV3UltrasonicSensor ultraSensor = new EV3UltrasonicSensor(SensorPort.S4);
	private SampleProvider sp = ultraSensor.getDistanceMode();
	private float[] distance = new float[1];
	
	private MovePilot pilot;
	public ObjectDetection2(MovePilot mp) {
		this.pilot = mp;
	}

	public void action() {
		pilot.stop();
	}
	
	public void suppress() {
	}


	public boolean takeControl() {
	
		sp.fetchSample(distance, 0);
		return (distance[0] < AVOID_THIS_DISTANCE);
	}
}