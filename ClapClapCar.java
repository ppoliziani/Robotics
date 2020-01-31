import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.SampleProvider;

public class ClapClapCar {
    public static void main(String[] args) {
        float []samples = new float [1]; // A sound sample is just one number
        NXTSoundSensor ss = new NXTSoundSensor(SensorPort.S1);
        SampleProvider sound = ss.getDBAMode();

        while (true) {
            sound.fetchSample(samples, 0);
            LCD.drawString(Float.toString(samples[0]),0,0);
        }
    }
}
