package ROBOBOP;

import lejos.hardware.Battery;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.robotics.subsumption.Behavior;

public class BatterySaver implements Behavior {
	
    private final double LOW_LEVEL = 6.2;
    
    private BaseRegulatedMotor mLeft;
    private BaseRegulatedMotor mRight;
    
    public BatterySaver(BaseRegulatedMotor mLeft, BaseRegulatedMotor mRight) { 
        this.mLeft = mLeft;
        this.mRight = mRight;
        mLeft.synchronizeWith(new BaseRegulatedMotor[] {mRight});
    }
    
    public void action() {
        if (takeControl()) {
		LCD.drawString("Saver mode enabled", 0, 4);
		mLeft.synchronizeWith(new BaseRegulatedMotor[] {mRight});
		mLeft.setSpeed(20);
		mRight.setSpeed(20);
		mLeft.startSynchronization();
		mLeft.forward();
		mRight.forward();
		mLeft.endSynchronization();
        }
    }
    public void suppress() {}

    public boolean takeControl(){
        float voltage = Battery.getVoltage();
        return voltage < LOW_LEVEL;
    }
}
