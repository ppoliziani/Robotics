import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class WatcherThread extends Thread {
	private BaseRegulatedMotor mL, mR;
	
	public WatcherThread(BaseRegulatedMotor _mL, BaseRegulatedMotor _mR) {
		mL = _mL;
		mR = _mR; 
	}
	
	public void run() {
		EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S2);
		SampleProvider sp = us.getDistanceMode();
		float[] samples = new float[1];
		while(true) {
			sp.fetchSample(samples, 0);
			if(samples[0] > 50) {
				mL.setSpeed(0);
				mR.setSpeed(0);
				Thread.yield();
			}
		}
	}
}
