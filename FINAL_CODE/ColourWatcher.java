package ROBOBOP;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColourWatcher extends Thread {
	
	private double MIDDLE = 0.2;
	private int MULTIPLIER = 1400;
	
	private BaseRegulatedMotor mLeft;
	private BaseRegulatedMotor mRight;
	
	public ColourWatcher(BaseRegulatedMotor mLeft, BaseRegulatedMotor mRight) {
		this.mLeft = mLeft;
		this.mRight = mRight;
	}
	
	public void run() {
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S2);
		SampleProvider sp = cs.getRedMode();
		float[] arr = new float[1];
		
		FollowLine fl = new FollowLine();
		
		while (true) {
			sp.fetchSample(arr, 0);
			double diff = (arr[0] - MIDDLE);
			LCD.drawString("" + diff, 3, 3);
			if (diff  > 0) {
				//too far left
				LCD.drawString("left", 0, 7);
				mRight.setSpeed(fl.getSpeed());
				mLeft.setSpeed(fl.getSpeed() + (int) (diff * MULTIPLIER));
			} else if (diff < 0) {
				//too far right
				LCD.drawString("right", 0, 7);
				mLeft.setSpeed(fl.getSpeed());
				mRight.setSpeed(fl.getSpeed() + (int) (-diff * MULTIPLIER));
			}
		}
	}
}
