import java.util.Random;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
	

public class Backup implements Behavior {
	
	private MovePilot pilot;
	Backup(MovePilot p) {
		this.pilot = p;
	}
	
	public void action() {
		Random rand = new Random();
		int num = rand.nextInt(1);
		
		pilot.travel(-20, true);	
		pilot.rotate(90);
		
	}
	
	public void suppress() {}
	
	public boolean takeControl() {
		
		float[] distance = new float[1];
		EV3UltrasonicSensor ultraSensor = new EV3UltrasonicSensor(SensorPort.S2);
		SampleProvider ultraSP = ultraSensor.getDistanceMode();
		
		return (distance[0] < 0.5);
	}
		

}
