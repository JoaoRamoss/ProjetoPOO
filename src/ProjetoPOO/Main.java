package ProjetoPOO;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Equipa teste = new Equipa("Porto");
        Equipa second = new Equipa("Belenenses");
        Avancado j = new Avancado("RDFEFSGFWRG_fsfewSEFGWEGWg", "Júlio César", "Porto",10, 10, 10, 10, 10, 10, 10, true);
        GuardaRedes gr = new GuardaRedes("JHBDFHLjknekjgfnkwjngfe_ lejfnj", "André Diogo", "Porto", 20, 20, 20, 20, 20, 20, 20, true, 100);
        teste.addJogador(j);
        teste.addJogador(gr);
        teste.trocaEquipa(second, j);
        System.out.println(j.toString());
        System.out.println(gr.toString());
    }
}
