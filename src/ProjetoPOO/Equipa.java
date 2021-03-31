package ProjetoPOO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Equipa {
    private List<Jogador> jogadores;
    private List<Jogador> jogadoresPrincipais, jogadoresSuplentes;
    private String nome;

    //Construtor vazio.
    public Equipa () { this(""); }

    //Construtor parametrizado.
    public Equipa(String nome) {
        this.jogadores = new ArrayList<>();
        geraEquipa();
        this.nome = nome;
        this.jogadores.forEach(j -> j.setEquipa(nome));
        this.jogadoresPrincipais = new ArrayList<>();
        this.jogadoresSuplentes = new ArrayList<>();
        getTop(Jogador.Tipojogador.Avancado, 3);
        getTop(Jogador.Tipojogador.Medio, 3);
        getTop(Jogador.Tipojogador.GuardaRedes, 1);
        getTop(Jogador.Tipojogador.Defesa, 2);
        getTop(Jogador.Tipojogador.Lateral, 2);
    }

    //Construtor de cópia.
    public Equipa (Equipa e) {
        this.jogadores = new ArrayList<>();
        this.jogadoresPrincipais = new ArrayList<>();
        this.jogadoresSuplentes = new ArrayList<>();
        this.nome = e.getNome();

        for (Jogador j : e.jogadores) {
            this.jogadores.add(j.clone());
        }
        this.jogadores.forEach(j -> j.setEquipa(e.getNome()));

        for (Jogador j : e.jogadoresPrincipais) {
            this.jogadoresPrincipais.add(j.clone());
        }
        for (Jogador j : e.jogadoresSuplentes) {
            this.jogadoresSuplentes.add(j.clone());
        }
    }

    /**
     * Getter de nome da equipa.
     * @return O nome da equipa.
     */
    public String getNome () {return this.nome;}

    public int getTotal (Jogador j) {
        return switch(j.getPosicao()) {
            case Avancado -> 3;
            case Lateral -> 2;
            case Medio -> 3;
            case Defesa -> 2;
            case GuardaRedes -> 1;
        };
    }

    /**
     * Obtem os jogadores principais.
     * @return Lista com os jogadores principais.
     */
    public List<Jogador> getPrincipais () {
        List <Jogador> list = new ArrayList<>();
        for (Jogador j : this.jogadoresPrincipais) list.add(j.clone());
        return list;
    }

    public List<Jogador> getJogadores () {
        List<Jogador> aux = new ArrayList<>();
        for (Jogador j : this.jogadores) aux.add(j.clone());
        return aux;
    }

    public List<Jogador> getSuplentes() {
        List<Jogador> list = new ArrayList<>();
        for (Jogador j : this.jogadoresSuplentes) list.add(j.clone());
        return list;
    }
    /**
     * Gera uma equipa e coloca na variavel de instancia "jogadores".
     */
    private void geraEquipa() {
        for (int i = 0; i < 2; i++) {
            this.jogadores.add(new Jogador(Jogador.Tipojogador.GuardaRedes));
        }
        for (int i = 0; i < 4; i++) {
            this.jogadores.add(new Jogador(Jogador.Tipojogador.Defesa));
            this.jogadores.add(new Jogador(Jogador.Tipojogador.Lateral));
        }
        for (int i = 0; i < 6; i++) {
            this.jogadores.add(new Jogador(Jogador.Tipojogador.Medio));
            this.jogadores.add(new Jogador(Jogador.Tipojogador.Avancado));
        }
    }

    /**
     * Obtem o índice do jogador com menor overall.
     * @param j lista de jogadores.
     * @return Índice do jogador com menor overall.
     */
    private int getSmallest(List<Jogador> j) {
        double smallest = j.get(0).overall();
        int i = 0;
        for (Jogador aux : j) {
            if (aux.overall() < smallest) {
                smallest = aux.overall();
                i++;
            }
        }
        return i;
    }

    /**
     * Generalização de getTop(Posição)
     * @param posicao Posição para a qual queremos obter o top.
     * @param total Total de jogadores principais nessa posição que é permitido ter em campo
     */
    private void getTop(Jogador.Tipojogador posicao, int total) {
        List<Jogador> aux = new ArrayList<>(); //Array auiliar.
        List<Jogador> res = new ArrayList<>(total); //Array de resultados.
        int smallest; //Índice do jogador com menor overall.

        for (Jogador jog : this.jogadores) {
            if (jog.getPosicao() == posicao) {
                aux.add(jog.clone());
            }
        }
        for (Jogador jogador : aux) {
            if (res.size() < total) {
                res.add(jogador.clone());
            }
        }
        for (int i = total; i < aux.size(); i++) {
            smallest = getSmallest(res);
            if (aux.get(i).overall() > res.get(smallest).overall()) res.set(smallest, aux.get(i).clone());
        }
        for (Jogador j : res) this.jogadoresPrincipais.add(j.clone());

        for (Jogador jog : aux) {
            boolean eq = false;
            for (Jogador jog2 : res) {
                if ((eq = jog.equals(jog2))) break;
            }
            if (!eq) this.jogadoresSuplentes.add(jog.clone());
        }
    }

    /**
     * Insere um jogador na equipa.
     * @param j Jogador a inserir na equipa.
     */
    public void insereJogador (Jogador j) {
        Jogador.Tipojogador tipo = j.getPosicao();
        this.jogadores.add(j.clone());
        this.getTop(tipo, getTotal(j));
    }

    public void trocaEquipa (int pos, Equipa e) {
        Jogador j = this.jogadores.get(pos).clone();
        j.mudaEquipa(e.getNome());
        e.insereJogador(j);
        this.jogadores.remove(pos);
        this.getTop(j.getPosicao(), getTotal(j));
    }
    /**
     * Função toString da classe Equipa.
     * @return String com o conteúdo da classe.
     */
    public String toString () {
        StringBuilder sb = new StringBuilder();
        sb.append("Jogadores Principais:\n-------------------------------------\n");
        for (Jogador j : this.getPrincipais()) sb.append(j.toString());
        sb.append("--------------------------------------\n\n");
        sb.append("Jogadores Suplentes: \n-------------------------------------\n");
        for (Jogador j : this.getSuplentes()) sb.append(j.toString());
        sb.append("--------------------------------------\n\n");
        return sb.toString();
    }

    /**
     * Função clone da classe.
     * @return Clone do objeto inserido (neste caso equipa).
     */
    public Equipa clone () {return new Equipa(this);}
}
