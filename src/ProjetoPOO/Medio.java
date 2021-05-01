package ProjetoPOO;

import java.util.List;
import java.util.Random;

public class Medio extends Jogador {

    private final String posicao = new String ("Medio");
    private int capRecuperacao;
    private static final Random random = new Random();

    public Medio () {
        super ("Medio");
        this.capRecuperacao = random.nextInt(99-75) + 75;
    }

    public Medio (String ID, String nome, String equipa, int velocidade, int resistencia, int destreza, int impulsao,
                   int jogoCabeca, int remate, int capPasse, boolean principal, int capRecuperacao) {
        super (ID, nome, equipa, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse, principal);
        this.capRecuperacao = capRecuperacao;
    }

    public Medio (String ID, String nome, String equipa, int velocidade, int resistencia, int destreza, int impulsao,
                  int jogoCabeca, int remate, int capPasse, boolean principal, int capRecuperacao, List <String> equipasAnteriores) {
        super (ID, nome, equipa, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse, principal, equipasAnteriores);
        this.capRecuperacao = capRecuperacao;
    }

    public Medio (Medio m) {
        super (m);
        this.capRecuperacao = m.getCapRecuperacao();
    }

    //Obtem a capacidade de recuperação de bola do médio.
    public int getCapRecuperacao () {return this.capRecuperacao;}

    //Set na capacidade de recuperação.
    public void setCapRecuperacao (int recup) {this.capRecuperacao = recup;}

    //Obtem posição onde jogador joga.
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
     * Função toString da subclasse Medio.
     * @return String com informação do Médio.
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
        sb.append("\nCapacidade de recuperação: ").append(this.getCapRecuperacao());
        sb.append(String.format("\nOverall: %.2f", this.overall())).append("\n\n");
        return sb.toString();
    }

    /**
     * Função equals da subclasse medio.
     * @param o Object a comparar
     * @return true se forem iguais, false se nao forem.
     */
    @Override public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Medio gr = (Medio) o;
        return (this.getPosicao().equals(gr.getPosicao()) && super.getVelocidade() == gr.getVelocidade() && super.getResistencia() == gr.getResistencia()
                && super.getDestreza() == gr.getDestreza() && super.getImpulsao() == gr.getImpulsao() && super.getJogoCabeca() == gr.getJogoCabeca() &&
                super.getCapPasse() == gr.getCapPasse() && super.getNomeEquipa().equals(gr.getNomeEquipa()) &&
                super.getNome().equals(gr.getNome()) && this.getCapRecuperacao() == gr.getCapRecuperacao());
    }

    /**
     * Função clone da subclasse Medio
     * @return novo Medio.
     */
    @Override public Medio clone () {return new Medio(this);}
}
