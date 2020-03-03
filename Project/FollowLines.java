import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

class FollowLines implements Behavior {
    private SampleProvider redSensor;
    private float[] samples;
    private float MAX_LIGHT;
    private float MIN_LIGHT;
    private MovePilot pilot;

    public FollowLines(MovePilot p) {
         EV3ColorSensor red = new EV3ColorSensor(SensorPort.S1);
         this.redSensor = red.getRedMode();
         this.samples = new float[1];
         this.MAX_LIGHT = 0.0f;
         this.MIN_LIGHT = 1.0f;
         this.pilot = p;
        pilot.setLinearSpeed(10);
    }

    public boolean takeControl() {
        return false; // boolean expression for the camera detecting a QR Code.
    }
    public void action() {
        while (!takeControl()) {
            redSensor.fetchSample(samples, 0);
            if (samples[0] > MAX_LIGHT) MAX_LIGHT = samples[0];
            if (samples[0] < MIN_LIGHT) MIN_LIGHT = samples[0];
            float AVERAGE = (MAX_LIGHT + MIN_LIGHT) / 2;
            if (samples[0] > AVERAGE) pilot.rotate(45);
            else pilot.rotate(-45);
            pilot.travel(400);
        }
    }
    public void suppress(){}
}
