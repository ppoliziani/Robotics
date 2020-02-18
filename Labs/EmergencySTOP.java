import lejos.hardware.Button;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;


public class EmergencySTOP implements Behavior {
	
	private MovePilot pilot;
	EmergencySTOP(MovePilot p) {
		this.pilot = p;
	}
	
	public void action() {
		pilot.stop();
	}
	
	public void suppress() {}
	
	public boolean takeControl() {
		return (Button.ESCAPE.isDown());
	}

}
