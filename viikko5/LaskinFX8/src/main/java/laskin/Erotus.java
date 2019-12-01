package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {
    private int arvo;

    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        arvo = Integer.parseInt(syotekentta.getText());
        sovellus.miinus(Integer.parseInt(syotekentta.getText()));
        tuloskentta.setText("" + sovellus.tulos());
        syotekentta.clear();
        nollaa.disableProperty().set(false);
        undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        sovellus.plus(arvo);
        tuloskentta.setText("" + sovellus.tulos());
        syotekentta.clear();
        undo.disableProperty().set(true);
    }
}
