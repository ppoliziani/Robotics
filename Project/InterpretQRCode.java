import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

import static lejos.hardware.Sound.playTone;

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
                while(!takeControl()) {
                    int toneLeft = Integer.parseInt(stringArr[1]);
                    int durationLeft = Integer.parseInt(stringArr[2]);
                    pilot.rotate(-90);
                    pilot.travel(500);
                    playTone(toneLeft, durationLeft);
                }

                break;
            case "RIGHT":
                while(!takeControl()) {
                    int toneRight = Integer.parseInt(stringArr[1]);
                    int durationRight = Integer.parseInt(stringArr[2]);
                    pilot.rotate(90);
                    pilot.travel(500);
                    playTone(toneRight, durationRight);
                }
                break;  
                case "FORWARD":
                    while(!takeControl()) {
                        int toneRight = Integer.parseInt(stringArr[1]);
                        int durationRight = Integer.parseInt(stringArr[2]);
                        pilot.travel(500);
                        playTone(toneRight, durationRight);
                    }
                    break;
        }
    }

    public void suppress() {}

    public boolean takeControl() {
        return false; // boolean expression for the camera detecting a QR Code.
    }
    public static void main(String[] args) { }
}
