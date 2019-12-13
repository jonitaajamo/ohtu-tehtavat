package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPS {
    public Scanner scanner;
    public Tuomari tuomari;
    public TekoalyInterface tekoaly;

    public void pelaa() {
        String ekanSiirto;
        String tokanSiirto;

        do {
            System.out.print("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();
            tokanSiirto = tekoaly.annaSiirto();
            System.out.println("Tietokone valitsi: " + tokanSiirto);
            tekoaly.asetaSiirto(ekanSiirto);
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
        } while(onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto));

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    public void asetaTuomari(Tuomari tuomari) {
        this.tuomari = tuomari;
    }

    public void asetaTekoaly(TekoalyInterface tekoaly) {
        this.tekoaly = tekoaly;
    }

    public void asetaScanneri(Scanner scanner) {
        this.scanner = scanner;
    }
}
