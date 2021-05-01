package ProjetoPOO;

import java.util.List;
import java.util.Random;

public class GuardaRedes extends Jogador {

    private final String posicao = new String ("GuardaRedes");
    private int elasticidade;

    private static final Random random = new Random();

    //Construtor vazio
    public GuardaRedes () {
        super ("GuardaRedes");
        this.elasticidade = random.nextInt(99-75) + 75;
    }

    //Construtor parametrizado.
    public GuardaRedes (String ID, String nome, String equipa, int velocidade, int resistencia, int destreza, int impulsao,
                        int jogoCabeca, int remate, int capPasse, boolean principal, int elasticidade) {
        super (ID, nome, equipa, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse, principal);
        this.elasticidade = elasticidade;
    }

    //Construtor parametrizado.
    public GuardaRedes (String ID, String nome, String equipa, int velocidade, int resistencia, int destreza, int impulsao,
                        int jogoCabeca, int remate, int capPasse, boolean principal, int elasticidade, List<String> equipasAnteriores) {
        super (ID, nome, equipa, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse, principal, equipasAnteriores);
        this.elasticidade = elasticidade;
    }

    //Construtor parametrizado.
    public GuardaRedes (GuardaRedes gr) {
        super (gr);
        this.elasticidade = gr.getElasticidade ();
    }

    //Obtem posição em que o jogador joga.
    public String getPosicao () {return this.posicao;}

    //Obtem elasticidade do guarda redes.
    public int getElasticidade() {return this.elasticidade;}

    //Set na elasticidade do guarda-redes.
    public void setElasticidade(int elast) {this.elasticidade = elast;}

    /**
     * Retorna o score em overall do jogador (Fórmula de cálculo varia para dar prioridade às caracteristicas mais importantes da posição em que joga).
     * @return Resultado da fórmula de calculo do overall do jogador.
     */
    public double overall () {
        return super.getVelocidade() * 0.05 + super.getResistencia() * 0.15 + super.getDestreza() * 0.25 + super.getImpulsao() * 0.15 + super.getJogoCabeca() * 0.05
                + super.getRemate() * 0.05 + super.getCapPasse() * 0.15 + this.getElasticidade() * 0.15;
    }

    /**
     * Função toString da subclasse GuardaRedes.
     * @return String com informação sobre guardaRedes.
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
        sb.append("\nElasticidade: ").append(this.getElasticidade());
        sb.append(String.format("\nOverall: %.2f", this.overall())).append("\n\n");
        return sb.toString();
    }

    /**
     * Função equals da subclasse GuardaRedes
     * @param o Object a comparar
     * @return  true se forem iguais, false se nao forem.
     */
    @Override public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        GuardaRedes gr = (GuardaRedes) o;
        return (this.getPosicao().equals(gr.getPosicao()) && super.getVelocidade() == gr.getVelocidade() && super.getResistencia() == gr.getResistencia()
                && super.getDestreza() == gr.getDestreza() && super.getImpulsao() == gr.getImpulsao() && super.getJogoCabeca() == gr.getJogoCabeca() &&
                super.getCapPasse() == gr.getCapPasse() && this.getElasticidade() == gr.getElasticidade() && this.getNomeEquipa().equals(gr.getNomeEquipa()) &&
                super.getNome().equals(gr.getNome()));
    }

    /**
     * Função clone da subclasse GuardaRedes.
     * @return Novo GuardaRedes.
     */
    @Override public GuardaRedes clone () {return new GuardaRedes(this);}
}
