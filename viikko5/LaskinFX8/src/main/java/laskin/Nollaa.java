package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
    private String arvo;
    private String tulos;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        arvo = syotekentta.getText();
        tulos = tuloskentta.getText();
        tuloskentta.clear();
        tuloskentta.setText("0");
        syotekentta.clear();
        sovellus.nollaa();
        nollaa.disableProperty().set(true);
    }

    @Override
    public void peru() {
        tuloskentta.setText(tulos);
        sovellus.plus(Integer.parseInt(tulos));
        undo.disableProperty().set(true);
    }
}
