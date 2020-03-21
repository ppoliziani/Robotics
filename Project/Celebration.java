import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Celebration extends Behavior {
    private EV3LargeRegulatedMotor mLeft;
    private EV3LargeRegulatedMotor mRight;
    public Celebration(EV3LargeRegulatedMotor left, EV3LargeRegulatedMotor right) {
        this.mLeft = left;
        this.mRight = right;
        mLeft.setSpeed(1000);
        mRight.setSpeed(1000);
    }

    public void action() {
        mRight.forward();
        mLeft.backward();
        Delay.msDelay(5000);
        System.exit(0);
    }

    public void suppress(){}

    public boolean takeControl() {
        QRThread thread = new QRThread();
        return thread.getLast() == 999;
    }
}
