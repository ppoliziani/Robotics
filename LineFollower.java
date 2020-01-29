import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class LineFollower {

	public static void main(String[] args) {
		BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor ( MotorPort . A );
		BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor ( MotorPort . B );
		
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S1);
		SampleProvider sp = cs.getRedMode();
		float[] samples = new float[1];
		
		float MAX_LIGHT = 0f;
		float MIN_LIGHT = 1f;
		float LIGHT_AVERAGE;
		
		while (Button.ENTER.isUp()) {
			sp.fetchSample(samples, 0);
			LCD.drawString(String.valueOf(samples[0]), 1, 1);
			if (samples[0] > MAX_LIGHT) {
				MAX_LIGHT = samples[0];
			} else if(samples[0] < MIN_LIGHT) {
				MIN_LIGHT = samples[0];
			}
		}
		LIGHT_AVERAGE = (MAX_LIGHT + MIN_LIGHT) / 2;
		
		while (true) {
			LCD.drawString(""+LIGHT_AVERAGE, 0, 0);
			sp.fetchSample(samples, 0);
			if (samples[0] > LIGHT_AVERAGE) {
				mLeft.forward();
				mRight.stop();
			} else {
				mLeft.stop();
				mRight.forward();
			}
		}
				
	}

}
