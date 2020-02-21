import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class InterpretQRCode implements Behavior {

    private MovePilot pilot;
    private String QRString;
    public InterpretQRCode(MovePilot p, String q) {
        this.pilot = p;
        this.QRString = q;
    }

    public void action() {
        String[] stringArr = QRString.split(" ");
        switch(stringArr[0]) {
            case "LEFT":
                int toneLeft = Integer.parseInt(stringArr[1]);
                int durationLeft = Integer.parseInt(stringArr[2]);
                pilot.rotate(-90);
                //while(!QRCodeDetected)
                    //pilot.travel(500);
                    //playTone(toneLeft, durationLeft)

                break;
            case "RIGHT":
                int toneRight = Integer.parseInt(stringArr[1]);
                int durationRight = Integer.parseInt(stringArr[2]);
                pilot.rotate(90);
                //while(!QRCodeDetected)
                    //pilot.travel(500);
                    //playTone(toneLeft, durationRight)
                break;
        }
    }

    public void suppress() {}

    public boolean takeControl() {
        return false; // behaviours need to be decided first.
    }
    public static void main(String[] args) {
        BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
        BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.B);
    }
}
