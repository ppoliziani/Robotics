import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;


public class Dark implements Behavior {
	
	private MovePilot pilot;
	Dark(MovePilot p) {
		this.pilot = p;
	}
	
	public void action() {
		pilot.setLinearSpeed(5);
	}
	
	public void suppress() {}
	
	public boolean takeControl() {
		
		float[] samples = new float[1];
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S1);
		SampleProvider sp = cs.getRedMode();
		
		double speed = pilot.getLinearSpeed();
		return ((speed > 10) & (samples[0] < 0.3));
	}

}
