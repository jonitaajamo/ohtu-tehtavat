package ohtu.kivipaperisakset;

// "Muistava tekoäly"

public class TekoalyParannettu implements TekoalyInterface {
  private String[] muisti;
  private int vapaaMuistiIndeksi;

  public TekoalyParannettu(int muistinKoko) {
    muisti = new String[muistinKoko];
    vapaaMuistiIndeksi = 0;
  }

  @Override
  public void asetaSiirto(String siirto) {
    if(vapaaMuistiIndeksi == muisti.length) {
      for(int i = 1; i < muisti.length; i++) {
        muisti[i-1] = muisti[i];
      }
      vapaaMuistiIndeksi--;
    }

    muisti[vapaaMuistiIndeksi] = siirto;
    vapaaMuistiIndeksi++;
  }

  @Override
  public String annaSiirto() {
    if(vapaaMuistiIndeksi == 0 || vapaaMuistiIndeksi == 1) {
      return "k";
    }

    String viimeisinSiirto = muisti[vapaaMuistiIndeksi - 1];

    int k = 0;
    int p = 0;
    int s = 0;

    for(int i = 0; i < vapaaMuistiIndeksi - 1; i++) {
      if(viimeisinSiirto.equals(muisti[i])) {
        String seuraava = muisti[i+1];

        if("k".equals(seuraava)) {
          k++;
        }
        else if("p".equals(seuraava)) {
          p++;
        }
        else {
          s++;
        }
      }
    }

    if(k > p && k > s) {
      return "p";
    }
    else if (p > k && p > s) {
      return "s";
    }
    else {
      return "k";
    }
  }
}