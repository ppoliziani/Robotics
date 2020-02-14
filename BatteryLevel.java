import lejos.hardware.Battery;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;


public class BatteryLevel implements Behavior {
	
	public boolean takeControl() {
			
		Battery bat = new Battery();
		float charge = bat.getBatteryCurrent();
			
		return (charge < 2);
	}
	
	public void action() {
		
		while (takeControl()) {
			LCD.drawString("BATTERY LOW", 2, 2);
			Delay.msDelay(500);
			LCD.clear();
			Delay.msDelay(500);
			Sound.beep();
		}
		
	}
	
	public void suppress() {}
	
}
