import lejos.hardware.Battery;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.subsumption.Behavior;

class SaverMode implements Behavior {
    private final float LOW_LEVEL;
    private EV3LargeRegulatedMotor mLeft;
    private EV3LargeRegulatedMotor mRight;
    
    public SaverMode(EV3LargeRegulatedMotor left, EV3LargeRegulatedMotor right) { 
        this.left = mLeft;
        this.right = mRight;
        mLeft.synchronizeWith(new BaseRegulatedMotor[] {mRight});
    }
    
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
}
