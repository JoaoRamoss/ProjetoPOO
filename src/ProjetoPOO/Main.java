package ProjetoPOO;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Equipa teste = new Equipa("Porto");
        Equipa second = new Equipa("Belenenses");
        StringBuffer sb = new StringBuffer("Júlio César");
        StringBuffer sbp = new StringBuffer("Porto");
        Jogador j = new Jogador("RDFEFSGFWRG_fsfewSEFGWEGWg", Jogador.Tipojogador.Avancado, 50, 50, 50, 50, 50, 50, 50, 50, sb, sbp, true);
        //System.out.println(j.toString());
        teste.addJogador(j);
        teste.trocaEquipa(second, j);
    }
}
