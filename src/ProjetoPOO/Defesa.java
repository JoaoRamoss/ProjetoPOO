package ProjetoPOO;

import java.util.List;

public class Defesa extends Jogador {
    private final String posicao = new String ("Defesa");

    //Construtor vazio.
    public Defesa () {
        super ("Defesa");
    }

    //Construtor parametrizado.
    public Defesa (String ID, String nome, String equipa, int velocidade, int resistencia, int destreza, int impulsao,
                     int jogoCabeca, int remate, int capPasse, boolean principal) {
        super (ID, nome, equipa, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse, principal);
    }

    //Construtor parametrizado (Inclui equipas anteriores).
    public Defesa (String ID, String nome, String equipa, int velocidade, int resistencia, int destreza, int impulsao,
                   int jogoCabeca, int remate, int capPasse, boolean principal, List<String> equipasAnteriores) {
        super (ID, nome, equipa, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse, principal, equipasAnteriores);
    }

    //Construtor parametrizado (Recebe outro defesa).
    public Defesa (Defesa d) {
        super (d);
    }

    /**
     * Obtem posição em que o jogador joga
     * @return A posição do jogador.
     */
    public String getPosicao () {return this.posicao;}

    /**
     * Retorna o score em overall do jogador (Fórmula de cálculo varia para dar prioridade às caracteristicas mais importantes da posição em que joga).
     * @return Resultado da fórmula de calculo do overall do jogador.
     */
    public double overall () {
        return super.getVelocidade() * 0.10 + super.getResistencia() * 0.25 + super.getDestreza() * 0.10 + super.getImpulsao() * 0.15 +
                super.getJogoCabeca() * 0.25 + super.getRemate() * 0.05 + super.getCapPasse() * 0.10;
    }

    /**
     * Função toString da subclasse Defesa
     * @return String com informação sobre o Defesa.
     */
    @Override public String toString () {
        StringBuilder sb = new StringBuilder("Jogador: ");
        sb.append(super.getNome()).append("\n");
        sb.append("Equipa: ").append(super.getNomeEquipa()).append("\n");
        if(super.getEquipasAnteriores().isEmpty())
            sb.append("Equipas anteriores: Sem historial noutras equipas").append("\n");
        else
            sb.append("Equipas anteriores: ").append(super.getEquipasAnteriores().toString()).append("\n");
        sb.append("Posição: ").append(this.getPosicao());
        sb.append("\nResistência: ").append(super.getResistencia());
        sb.append("\nVelocidade: ").append(super.getVelocidade());
        sb.append("\nDestreza: ").append(super.getDestreza());
        sb.append("\nImpulsao: ").append(super.getImpulsao());
        sb.append("\nJogo de cabeça: ").append(super.getJogoCabeca());
        sb.append("\nRemate: ").append(super.getRemate());
        sb.append("\nCapacidade de passe: ").append(super.getCapPasse());
        sb.append(String.format("\nOverall: %.2f", this.overall())).append("\n\n");
        return sb.toString();
    }

    /**
     * Função equals da subclasse Defesa
     * @param o Object a comparar
     * @return  True se forem iguais, false se não forem.
     */
    @Override public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Defesa gr = (Defesa) o;
        return (this.getPosicao().equals(gr.getPosicao()) && super.getVelocidade() == gr.getVelocidade() && super.getResistencia() == gr.getResistencia()
                && super.getDestreza() == gr.getDestreza() && super.getImpulsao() == gr.getImpulsao() && super.getJogoCabeca() == gr.getJogoCabeca() &&
                super.getCapPasse() == gr.getCapPasse() && this.getNomeEquipa().equals(gr.getNomeEquipa()) &&
                super.getNome().equals(gr.getNome()));
    }

    /**
     * Função clone da subclasse Defesa
     * @return Novo Defesa.
     */
    @Override public Defesa clone () {return new Defesa (this);}
}
