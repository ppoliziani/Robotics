import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class WallDetection extends Thread {
	private BaseRegulatedMotor mLeft;
	private BaseRegulatedMotor mRight;
	private float[] arr;
	
	public WallDetection(BaseRegulatedMotor l, BaseRegulatedMotor r) {
		this.mLeft = l;
		this.mRight = r;
	}
	
	public void detectWall() {
		EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(SensorPort.S2);
		SampleProvider samps = sensor.getDistanceMode();
		while(true) {
			samps.fetchSample(arr, 0);
			if(arr[0] < 0.5f) {
				mLeft.stop();
				mRight.stop();
			}
		}
	}
}
