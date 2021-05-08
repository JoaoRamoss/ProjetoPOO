package ProjetoPOO;

import java.util.List;

public class Avancado extends Jogador {
    private final String posicao = new String ("Avancado");

    //Construtor vazio.
    public Avancado () {
        super ("Avancado");
    }

    //Construtor parametrizado.
    public Avancado (String equipa, String nome, int numeroCamisola, int velocidade, int resistencia, int destreza, int impulsao,
                     int jogoCabeca, int remate, int capPasse) {
        super (equipa, nome, numeroCamisola, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse);
    }

    //Construtor parametrizado (Inclui equipas anteriores).
    public Avancado (String equipa, String nome, int numeroCamisola, int velocidade, int resistencia, int destreza, int impulsao,
                     int jogoCabeca, int remate, int capPasse, List<String> equipasAnteriores) {
        super (equipa, nome, numeroCamisola, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse,equipasAnteriores);
    }

    //Construtor parametrizado (Recebe outro Avancado).
    public Avancado (Avancado a) {
        super(a);
    }

    /**
     *
     * @return Posição atual do jogador.
     */
    public String getPosicao () {return this.posicao;}

    /**
     * Retorna o score em overall do jogador (Fórmula de cálculo varia para dar prioridade às caracteristicas mais importantes da posição em que joga).
     * @return Resultado da fórmula de calculo do overall do jogador.
     */
    public double overall () {
        return super.getVelocidade() * 0.25 + super.getResistencia() * 0.10 + super.getDestreza() * 0.10 + super.getImpulsao() * 0.15 + super.getJogoCabeca() * 0.15 +
                super.getRemate() * 0.20 + super.getCapPasse() * 0.05;
    }

    /**
     * Função toString da subclasse Avancado
     * @return String com informação sobre avancado.
     */
    @Override public String toString () {
        StringBuilder sb = new StringBuilder("Jogador: ");
        sb.append(super.getNome()).append("\n");
        sb.append("Equipa: ").append(super.getNomeEquipa()).append("\n");
        sb.append("Nome: ").append(super.getNome()).append("\n");
        sb.append("Numero da camisola: ").append(super.getNumeroCamisola()).append("\n");
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
     * Função equals da sublcasse Avancado
     * @param o Object a comparar
     * @return True no caso de serem iguais, false no caso de nao serem.
     */
    @Override public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Avancado gr = (Avancado) o;
        return (super.getNumeroCamisola() == gr.getNumeroCamisola() && this.getPosicao().equals(gr.getPosicao()) && super.getVelocidade() == gr.getVelocidade() && super.getResistencia() == gr.getResistencia()
                && super.getDestreza() == gr.getDestreza() && super.getImpulsao() == gr.getImpulsao() && super.getJogoCabeca() == gr.getJogoCabeca() &&
                super.getCapPasse() == gr.getCapPasse() && super.getNomeEquipa().equals(gr.getNomeEquipa()) &&
                super.getNome().equals(gr.getNome()));
    }

    /**
     * Função clone da subclasse Avancado.
     * @return Novo avancado.
     */
    @Override public Avancado clone () {return new Avancado(this);}
}
