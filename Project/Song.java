import lejos.hardware.Sound;
import lejos.utility.Delay;

class Song {
    public static void main(String[] args) {
        int[][] freq = {{660, 100, 150}, {660, 100, 300},{660, 100, 300},
                        {510, 100, 100}, {660, 100, 300}, {770, 100, 550},
                        {380, 100, 575}};
        for (int i = 0; i < freq.length; i++) {
            Sound.playTone(freq[i][0], freq[i][1]);
            //Delay.msDelay(freq[i][2]);
        }
    }
}

