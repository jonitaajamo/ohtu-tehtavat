package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
    int viitenro;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viitenro = 4321;
        viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(viitenro);

        varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(9);
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "juusto", 4));
        when(varasto.haeTuote(3)).thenReturn(new Tuote(2, "leipä", 4));

        // sitten testattava kauppa
        k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {

        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoOikeillaTiedoilla() {

        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(viitenro), eq("12345"), eq("33333-44455"), eq(9));
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoOikeillaTiedoillaKunTuotteetSamat() {

        k.lisaaKoriin(1);
        k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(viitenro), eq("12345"), eq("33333-44455"), eq(10));
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoOikeillaTiedoillaKunTuotteetEriJaToinenLoppu() {
        k.lisaaKoriin(1);
        k.lisaaKoriin(3); // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(viitenro), eq("12345"), eq("33333-44455"), eq(5));
    }

    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot() {
        k.lisaaKoriin(1);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(viitenro), eq("12345"), eq("33333-44455"), eq(5));
    }


    @Test
    public void KauppaPyytaaUudenViitteenJokaiselleMaksulle() {
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        verify(viite, times(1)).uusi();
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(viite, times(2)).uusi();
    }

    @Test
    public void koristaPoistaminenLisaaTuotteenTakaisinVarastoon() {
        k.lisaaKoriin(1);
        varasto.haeTuote(1);
        k.poistaKorista(1);

        verify(varasto, times(1)).palautaVarastoon(any());
    }

}