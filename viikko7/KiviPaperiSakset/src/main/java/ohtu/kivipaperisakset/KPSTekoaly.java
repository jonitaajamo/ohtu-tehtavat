package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KPS{
    public KPSTekoaly(Tuomari tuomari, TekoalyInterface tekoaly, Scanner scanner) {
        this.asetaTuomari(tuomari);
        this.asetaTekoaly(tekoaly);
        this.asetaScanneri(scanner);
    }
}