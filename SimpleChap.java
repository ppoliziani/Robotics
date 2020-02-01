import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class SimpleChap {
    public static void main(String[] args) {
        BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
        BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.B);
        mLeft.setSpeed(720);
        mRight.setSpeed(720);

        float[] level = new float[1]; // A sound sample is just one number
        NXTSoundSensor ss = new NXTSoundSensor(SensorPort.S1);
        SensorMode sound = (SensorMode) ss.getDBAMode();
        SampleProvider clap = new ClapFilter(sound , 0.6f, 1000);
        clap.fetchSample(level,0);

        if (level[0] > 0.9f) {
            while(true) {
                mLeft.forward();
                mRight.forward();
                clap.fetchSample(level, 0);
                if (level[0] > 0.9f) {
                    mLeft.rotate(90);
                }
                Delay.msDelay(835);
                mRight.forward();
            }
        }
    }
}
