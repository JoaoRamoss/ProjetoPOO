package ProjetoPOO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Equipa {
    private Map<Integer, Jogador> jogadores;
    private String nome;

    public Equipa() {
        this.jogadores = new HashMap<>();
        this.nome = "";
    }

    public Equipa (String nome) {
        this.jogadores = new HashMap<>();
        this.nome = nome;
    }

    public Equipa (List<Jogador> jogadores, String nome) {
        this.jogadores = new HashMap<>();
        for (Jogador j : jogadores) this.jogadores.put(j.getNumeroCamisola(), j.clone());
        this.nome = nome;
    }

    public Equipa (Equipa e) {
        this.nome = e.getNome();
        this.jogadores = new HashMap<>();
        for (Jogador j : e.getJogadores().values()) {
            this.jogadores.put(j.getNumeroCamisola(), j.clone());
        }
    }

    /**
     * @brief Getter de todos os jogadores na equipa.
     * @return ArrayList com todos os jogadores.
     */
    public Map<Integer, Jogador> getJogadores () {
        Map<Integer, Jogador> aux = new HashMap<>();
        this.jogadores.forEach((k, v) -> aux.put(k, v.clone()));
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
        Jogador aux = j.clone();
        aux.setNomeEquipa(this.getNome());
        this.jogadores.put(aux.getNumeroCamisola(), aux);
    }

    /**
     * Remove um jogador da equipa.
     * @param j Jogador a ser removido da equipa.
     */
    public void removeJogador (Jogador j) {
        int numero = j.getNumeroCamisola();
        this.jogadores.remove(numero);
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


    public static Equipa parse(String input){
        String[] campos = input.split(",");
        return new Equipa(campos[0]);
    }
    /**
     * @brief Função toString da classe Equipa.
     * @return String com a informação da equipa.
     */
    public String toString () {
        List<String> list = new ArrayList<>();
        this.getJogadores().values().forEach(j -> list.add(j.getNome()));
        StringBuilder sb = new StringBuilder("Equipa: ").append(this.getNome()).append(" {\n");
        sb.append("\tJogadores: \n");
        for (String j : list)
            sb.append("\t").append(j).append("\n");
        sb.append("}\n");
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
