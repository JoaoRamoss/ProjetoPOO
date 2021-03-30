package ProjetoPOO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Equipa {
    private static List<Jogador> jogadores;
    private List<Jogador> jogadoresPrincipais, jogadoresSuplentes;
    private String nome;

    public Equipa() {
        jogadores = geraEquipa();
        jogadores.forEach(j -> j.setEquipa(this.nome));
    }


    private List<Jogador> geraEquipa() {
        List<Jogador> j = new ArrayList<>();
        j.add(new Jogador(Jogador.Tipojogador.GuardaRedes));
        for (int i = 0; i < 2; i++) {
            j.add(new Jogador(Jogador.Tipojogador.Defesa));
            j.add(new Jogador(Jogador.Tipojogador.Lateral));
        }
        for (int i = 0; i < 3; i++) {
            j.add(new Jogador(Jogador.Tipojogador.Medio));
            j.add(new Jogador(Jogador.Tipojogador.Avancado));
        }
        for (int i = 0; i < 12; i++) {
            j.add(new Jogador());
        }
        return j;
    }

    private List<Jogador> getTopAvancados(List<Jogador> lista) {
        List<Jogador> aux = new ArrayList<>();
        List<Jogador> res = new ArrayList<>();
        int max = 0;
        for (Jogador jog : lista) {
            if (jog.getPosicao() == Jogador.Tipojogador.Avancado) {
                aux.add(jog);
            }
        }
        for (int i = 0; i < aux.size() - 1; i++)
            if (res.size() <= 4)
                res.add(aux.get(i));

        for (int k = 0; k < aux.size() - 1; k++) {
            for (int j = 0; j < res.size() - 1; j++) {
                if (aux.get(k).overall() > res.get(j).overall())
                    res.set(j, aux.get(k));
            }
        }
        return res;
    }

    private List<Jogador> getTopDefesas(List<Jogador> lista) {
        List<Jogador> aux = new ArrayList<>();
        List<Jogador> res = new ArrayList<>();
        int max = 0;
        for (Jogador jog : lista) {
            if (jog.getPosicao() == Jogador.Tipojogador.Defesa) {
                aux.add(jog);
            }
        }
        for (int i = 0; i < aux.size() - 1; i++)
            if (res.size() <= 4)
                res.add(aux.get(i));

        for (int k = 0; k < aux.size() - 1; k++) {
            for (int j = 0; j < res.size() - 1; j++) {
                if (aux.get(k).overall() > res.get(j).overall())
                    res.set(j, aux.get(k));
            }
        }
        return res;
    }

    private List<Jogador> getTopMedios(List<Jogador> lista) {
        List<Jogador> aux = new ArrayList<>();
        List<Jogador> res = new ArrayList<>();
        int max = 0;
        for (Jogador jog : lista) {
            if (jog.getPosicao() == Jogador.Tipojogador.Medio) {
                aux.add(jog);
            }
        }
        for (int i = 0; i < aux.size() - 1; i++)
            if (res.size() <= 4)
                res.add(aux.get(i));

        for (int k = 0; k < aux.size() - 1; k++) {
            for (int j = 0; j < res.size() - 1; j++) {
                if (aux.get(k).overall() > res.get(j).overall())
                    res.set(j, aux.get(k));
            }
        }
        return res;
    }

    private List<Jogador> getTopLaterais(List<Jogador> lista) {
        List<Jogador> aux = new ArrayList<>();
        List<Jogador> res = new ArrayList<>();
        int max = 0;
        for (Jogador jog : lista) {
            if (jog.getPosicao() == Jogador.Tipojogador.Lateral) {
                aux.add(jog);
            }
        }
        for (int i = 0; i < aux.size() - 1; i++)
            if (res.size() <= 4)
                res.add(aux.get(i));

        for (int k = 0; k < aux.size() - 1; k++) {
            for (int j = 0; j < res.size() - 1; j++) {
                if (aux.get(k).overall() > res.get(j).overall())
                    res.set(j, aux.get(k));
            }
        }
        return res;
    }

    private List<Jogador> getGuardaRedes(List<Jogador> lista) {
        List<Jogador> aux = new ArrayList<>();
        List<Jogador> res = new ArrayList<>();
        int max = 0;
        for (Jogador jog : lista) {
            if (jog.getPosicao() == Jogador.Tipojogador.GuardaRedes) {
                aux.add(jog);
            }
        }
        for (int i = 0; i < aux.size() - 1; i++)
            if (res.size() <= 4)
                res.add(aux.get(i));

        for (int k = 0; k < aux.size() - 1; k++) {
            for (int j = 0; j < res.size() - 1; j++) {
                if (aux.get(k).overall() > res.get(j).overall())
                    res.set(j, aux.get(k));
            }
        }
        return res;
    }

}
