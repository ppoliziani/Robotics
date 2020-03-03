import lejos.hardware.Battery;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

class SaverMode implements Behavior {
    private float LOW_LEVEL;
    private boolean supressed = false;
    public MovePilot pilot;
    public SaverMode(MovePilot p) { this.pilot = p; }
    public void action() {
        if (takeControl()) {
            LCD.drawString("Saver mode enabled", 0, 0);
            pilot.setLinearSpeed(5);
        }
    }
    public void suppress(){}

    public boolean takeControl(){
        float voltage = Battery.getVoltage();
        return voltage < LOW_LEVEL;
    }

    public void LowBattery(float volts){
        LOW_LEVEL = volts;
    }
}
