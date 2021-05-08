package ProjetoPOO;

import java.util.ArrayList;
import java.util.List;

public class Equipa {
    private List<Jogador> jogadores;
    private String nome;

    public Equipa() {
        this.jogadores = new ArrayList<>();
        this.nome = "";
    }

    public Equipa (String nome) {
        this.jogadores = new ArrayList<>();
        this.nome = nome;
    }

    public Equipa (List<Jogador> jogadores, String nome) {
        this.jogadores = new ArrayList<>();
        for (Jogador j : jogadores) this.jogadores.add(j.clone());
        this.nome = nome;
    }

    public Equipa (Equipa e) {
        this.jogadores = new ArrayList<>();
        this.jogadores.addAll(e.jogadores);
    }

    /**
     * @brief Getter de todos os jogadores na equipa.
     * @return ArrayList com todos os jogadores.
     */
    public List<Jogador> getJogadores () {
        List<Jogador> aux = new ArrayList<>();
        this.jogadores.forEach(j -> aux.add(j.clone()));
        return aux;
    }

    /**
     * Obtem o nome da equipa.
     * @return O nome da equipa.
     */
    public String getNome () {return this.nome;}

    /**
     * Setter do nome da equipa.
     * @param nome Nome a ser colocado como nome da equipa.
     */
    public void setNome (String nome) {this.nome = nome;}

    /**
     * Adiciona um jogador na equipa.
     * @param j Jogador a ser colocado na equipa.
     */
    public void insereJogador (Jogador j) {
        this.jogadores.add(j.clone());
    }

    /**
     * Remove um jogador da equipa.
     * @param j Jogador a ser removido da equipa.
     */
    public void removeJogador (Jogador j) {
        int numero = j.getNumeroCamisola();
        this.jogadores.removeIf(k -> (k.getNumeroCamisola() == numero));
    }

    /**
     * Troca um jogador de equipa.
     * @param nova Nova equipa onde o jogador irá ser inserido.
     * @param j Jogador a ser inserido na nova equipa.
     */
    public void trocaEquipa (Equipa nova, Jogador j) {
        j.mudaEquipa(nova.getNome());
        nova.insereJogador(j.clone());
        this.removeJogador(j);
    }

    /**
     * @brief Função toString da classe Equipa.
     * @return String com a informação da equipa.
     */
    public String toString () {
        List<String> list = new ArrayList<>();
        this.getJogadores().forEach(j -> list.add(j.getNome()));
        StringBuilder sb = new StringBuilder("Equipa: {\n");
        sb.append("\tNome jogadores: ");
        sb.append(list.toString()).append("\n");
        return sb.toString();
    }

    /**
     * @brief Função equals
     * @param o Object a ser comparado
     * @return True se forem equipas iguais, false no caso de nao serem.
     */
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Equipa gr = (Equipa) o;
        return (this.nome.equals(gr.getNome()) && this.jogadores.equals(gr.getJogadores()));
    }

    /**
     * Função clone da classe.
     * @return Clone do objeto inserido (neste caso equipa).
     */
    public Equipa clone () {return new Equipa(this);}
}
