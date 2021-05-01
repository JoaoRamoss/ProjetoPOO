package ProjetoPOO;

import java.util.List;
import java.util.Random;

public class Lateral extends Jogador {

    private final String posicao = new String ("Lateral");
    private int cruzamento;

    private static final Random random = new Random();

    public Lateral () {
        super ("Lateral");
        this.cruzamento = random.nextInt(99-75) + 75;
    }

    public Lateral (String ID, String nome, String equipa, int velocidade, int resistencia, int destreza, int impulsao,
                  int jogoCabeca, int remate, int capPasse, boolean principal, int cruzamento) {
        super (ID, nome, equipa, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse, principal);
        this.cruzamento = cruzamento;
    }

    public Lateral (String ID, String nome, String equipa, int velocidade, int resistencia, int destreza, int impulsao,
                    int jogoCabeca, int remate, int capPasse, boolean principal, int cruzamento, List<String> equipasAnteriores) {
        super (ID, nome, equipa, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse, principal, equipasAnteriores);
        this.cruzamento = cruzamento;
    }

    public Lateral (Lateral lat) {
        super (lat);
        this.cruzamento = lat.getCruzamento();
    }

    //Obtem capacidade de cruzamento do lateral.
    public int getCruzamento () {return this.cruzamento;}

    //Set no cruzamento do Lateral.
    public void setCruzamento(int cruzamento) {this.cruzamento = cruzamento;}

    //Obtem a posição em que o jogador joga.
    public String getPosicao () {return this.posicao;}

    /**
     * Retorna o score em overall do jogador (Fórmula de cálculo varia para dar prioridade às caracteristicas mais importantes da posição em que joga).
     * @return Resultado da fórmula de calculo do overall do jogador.
     */
    public double overall () {
        return super.getVelocidade() * 0.25 + super.getResistencia() * 0.15 + super.getDestreza() * 0.15 + super.getImpulsao() * 0.05 + super.getJogoCabeca() * 0.05 +
                super.getRemate() * 0.10 + super.getCapPasse() * 0.10 + this.cruzamento * 0.15;
    }

    /**
     * Função toString da subclasse Lateral.
     * @return  String com informação sobre Lateral.
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
        sb.append("\nCapacidade de cruzamento: ").append(this.getCruzamento());
        sb.append(String.format("\nOverall: %.2f", this.overall())).append("\n\n");
        return sb.toString();
    }

    /**
     * Função equals da subclasse Lateral.
     * @param o Object a comparar
     * @return true se forem iguais, false se não forem.
     */
    @Override public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Lateral gr = (Lateral) o;
        return (this.getPosicao().equals(gr.getPosicao()) && super.getVelocidade() == gr.getVelocidade() && super.getResistencia() == gr.getResistencia()
                && super.getDestreza() == gr.getDestreza() && super.getImpulsao() == gr.getImpulsao() && super.getJogoCabeca() == gr.getJogoCabeca() &&
                super.getCapPasse() == gr.getCapPasse() && super.getNomeEquipa().equals(gr.getNomeEquipa()) &&
                super.getNome().equals(gr.getNome()) && this.getCruzamento() == gr.getCruzamento());
    }

    /**
     * Função clone da subclasse Lateral.
     * @return Novo Lateral.
     */
    @Override public Lateral clone () {return new Lateral(this);}
}
