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
        jogadores.forEach(j -> j.setEquipa(""));
        this.jogadoresPrincipais = new ArrayList<>();
        this.jogadoresSuplentes = new ArrayList<>();
        getTopAvancados(jogadores);
    }


    private static List<Jogador> geraEquipa() {
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

    private double setSmallest(List<Jogador> j) {
        double smallest = 200;
        for (Jogador aux : j) {
            if (aux.overall() < smallest) smallest = aux.overall();
        }
        return smallest;
    }

    private void getTopAvancados(List<Jogador> lista) {
        List<Jogador> aux = new ArrayList<>();
        List<Jogador> res = new ArrayList<>();
        double smallest = 200;
        for (Jogador jog : lista) {
            if (jog.getPosicao() == Jogador.Tipojogador.Avancado) {
                aux.add(jog);
            }
        }

        System.out.println("Total: ");
        System.out.println(aux.toString());
        for (Jogador jogador : aux) {
            if (res.size() < 4) {
                res.add(jogador.clone());
            }
        }
        smallest = setSmallest(aux);
        for (Jogador jogador : aux) {
            for (int j = 0; j < res.size(); j++) {
                if (jogador.overall() > smallest) {
                    res.set(j, jogador.clone());
                    smallest = setSmallest(res);
                    System.out.println("smallest: " + smallest);
                }
            }
        }
        for (Jogador j : res) {
            this.jogadoresPrincipais.add(j.clone());
        }

        for (Jogador jog : aux) {
            boolean eq = false;
            for (Jogador jog2 : res) {
                if ((eq = jog.equals(jog2))) break;
            }
            if (!eq) this.jogadoresSuplentes.add(jog.clone());
        }
    }

    private static List<Jogador> getTopDefesas(List<Jogador> lista) {
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

    private static List<Jogador> getTopMedios(List<Jogador> lista) {
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

    private static List<Jogador> getTopLaterais(List<Jogador> lista) {
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

    private static List<Jogador> getGuardaRedes(List<Jogador> lista) {
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

    public List<Jogador> getPrincipais () {
        List <Jogador> list = new ArrayList<>();
        for (Jogador j : this.jogadoresPrincipais) list.add(j.clone());
        return list;
    }

}
