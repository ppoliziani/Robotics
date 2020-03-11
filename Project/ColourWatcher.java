package FinalRobot;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;

public class ColourWatcher extends Thread {
	
	private MovePilot pilot;
	private double LIGHT_AVERAGE = 0.25;
	
	public ColourWatcher(MovePilot mp) {
		this.pilot = mp;		
	}
	
	public void run() {
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S1);
		SampleProvider sp = cs.getRedMode();
		float[] samples = new float[1];
		
		while (true) {
			sp.fetchSample(samples, 0);
			double value = (samples[0] - LIGHT_AVERAGE);
			LCD.drawString("" + value, 3, 3);
			if (value > 0 ) {
				//pilot.arcForward(-500);
				LCD.drawString("more", 2, 2);
			} else if (value < 0) {
				//pilot.arcForward(500);
				LCD.drawString("less", 2, 2);
			}
		}
	}
}
