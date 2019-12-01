package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {
    private int arvo;

    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        arvo = Integer.parseInt(syotekentta.getText());
        sovellus.plus(Integer.parseInt(syotekentta.getText()));
        tuloskentta.setText("" + sovellus.tulos());
        syotekentta.clear();
        nollaa.disableProperty().set(false);
        undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        sovellus.miinus(arvo);
        tuloskentta.setText("" + sovellus.tulos());
        syotekentta.clear();
        nollaa.disableProperty().set(false);
        undo.disableProperty().set(true);
    }
}
