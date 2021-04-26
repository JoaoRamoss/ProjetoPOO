package ProjetoPOO;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Equipa teste = new Equipa("Porto");
        Equipa second = new Equipa("Belenenses");
        Jogador j = new Jogador("RDFEFSGFWRG_fsfewSEFGWEGWg", "Júlio César", "Porto", Jogador.Tipojogador.Lateral,10, 10, 10, 10, 10, 10, 10, 10, true);
        System.out.println(j.toString());
        teste.addJogador(j);
        teste.trocaEquipa(second, j);
        System.out.println(j.toString());
    }
}
