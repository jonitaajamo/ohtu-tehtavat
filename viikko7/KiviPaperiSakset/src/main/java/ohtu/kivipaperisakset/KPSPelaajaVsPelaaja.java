package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KPS {

    public KPSPelaajaVsPelaaja(Tuomari tuomari, Scanner scanner) {
        this.asetaTuomari(tuomari);
        this.asetaScanneri(scanner);
    }

    @Override
    public void pelaa() {
        String ekanSiirto;
        String tokanSiirto;
        do {
            System.out.print("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();
            System.out.print("Toisen pelaajan siirto: ");
            tokanSiirto = scanner.nextLine();
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
        } while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto));
        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

}