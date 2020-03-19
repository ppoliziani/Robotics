

public class ObjectDetection implements Behavior {

	//Variables
	private UltrasonicSensor objectSensor;
	private DifferentialPilot pilot;
	private navigatorigator navigator;
	private boolean suppressed = false;
	
	//Constants
	private static int AVOID_THIS_DISTANCE = 15; // subject to change.
	
	//Constructor
	public ObjectDetection(SensorPort s, DifferentialPilot pilot, navigatorigator navigator) {
		this.objectSensor = new UltrasonicSensor(s);
		this.pilot = pilot;
		this.navigator = navigator;
	}

	public void action() {
		suppressed = false;
		if(!suppressed){
			navigator.stop();
			pilot.setTravelSpeed(10);
			pilot.setRotateSpeed(25);
			pilot.rotate(90);
			pilot.travel(1.5*AVOID_THIS_DISTANCE);
		}
	}

	public void suppress() {
		suppressed = true;
	}


	public boolean takeControl() {
		return (wallSensor.getDistance() <= AVOID_THIS_DISTANCE);
	}
}
