package ProjetoPOO;

import javax.print.event.PrintJobAdapter;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Equipa {
    private List<Jogador> jogadores;
    private List<Jogador> jogadoresPrincipais, jogadoresSuplentes;
    private String nome;

    public Equipa() {
        this.jogadores = new ArrayList<>();
        this.jogadoresPrincipais = new ArrayList<>();
        this.jogadoresSuplentes = new ArrayList<>();
        this.nome = "";
    }

    public Equipa (String nome) {
        this.jogadores = new ArrayList<>();
        this.jogadoresPrincipais = new ArrayList<>();
        this.jogadoresSuplentes = new ArrayList<>();
        this.nome = nome;
    }

    public Equipa (List<Jogador> jogadores, List<Jogador> jogadoresPrincipais, List<Jogador> jogadoresSuplentes, String nome) {
        this.jogadores = new ArrayList<>();
        for (Jogador j : jogadores) this.jogadores.add(j.clone());

        this.jogadoresPrincipais = new ArrayList<>();
        for (Jogador j : jogadoresPrincipais) this.jogadoresPrincipais.add(j.clone());

        this.jogadoresSuplentes = new ArrayList<>();
        for (Jogador j : jogadoresSuplentes) this.jogadoresSuplentes.add(j.clone());

        this.nome = nome;
    }

    public Equipa (Equipa e) {
        this.jogadores = new ArrayList<>();
        this.jogadores.addAll(e.jogadores);

        this.jogadoresSuplentes = new ArrayList<>();
        this.jogadoresSuplentes.addAll(e.jogadoresSuplentes);

        this.jogadoresPrincipais = new ArrayList<>();
        this.jogadoresPrincipais.addAll(e.jogadoresPrincipais);
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
     * @brief Obtem todos os jogadores principais.
     * @return ArrayList com todos os jogadores principais.
     */
    public List<Jogador> getJogadoresPrincipais () {
        List<Jogador> aux = new ArrayList<>();
        this.jogadoresPrincipais.forEach(j -> aux.add(j.clone()));
        return aux;
    }

    /**
     * @brief Obtem todos os jogadores suplentes.
     * @return ArrayList de todos os jogadores suplentes.
     */
    public List<Jogador> getJogadoresSuplentes () {
        List<Jogador> aux = new ArrayList<>();
        this.jogadoresSuplentes.forEach(j -> aux.add(j.clone()));
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
    public void addJogador (Jogador j) {
        if (j.isPrincipal()) this.jogadoresPrincipais.add(j.clone());
        else this.jogadoresPrincipais.add(j.clone());

        this.jogadores.add(j.clone());
    }

    /**
     * Remove um jogador da equipa.
     * @param j Jogador a ser removido da equipa.
     */
    public void removeJogador (Jogador j) {
        String id = j.getID();
        this.jogadores.removeIf(k -> (k.getID().equals(id)));
        if (j.isPrincipal()) this.jogadoresPrincipais.removeIf(k -> k.getID().equals(id));
        else this.jogadoresSuplentes.removeIf(k -> k.getID().equals(id));
    }

    /**
     * Troca um jogador de equipa.
     * @param nova Nova equipa onde o jogador irá ser inserido.
     * @param j Jogador a ser inserido na nova equipa.
     */
    public void trocaEquipa (Equipa nova, Jogador j) {
        j.mudaEquipa(nova.getNome());
        nova.addJogador(j.clone());
        this.removeJogador(j);
    }

    
    public String toString () {
        List<String> list = new ArrayList<>();
        this.getJogadores().forEach(j -> list.add(j.getNome()));
        StringBuilder sb = new StringBuilder("Equipa: {\n");
        sb.append("\tNome jogadores: ");
        sb.append(list.toString()).append("\n");
        return sb.toString();
    }
    /**
     * Função clone da classe.
     * @return Clone do objeto inserido (neste caso equipa).
     */
    public Equipa clone () {return new Equipa(this);}
}
