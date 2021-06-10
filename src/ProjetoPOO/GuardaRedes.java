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
    public GuardaRedes (String nome, int numeroC, int velocidade, int resistencia, int destreza, int impulsao,
                        int jogoCabeca, int remate, int capPasse, int elasticidade) {
        super (nome, numeroC, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse);
        this.elasticidade = elasticidade;
    }

    //Construtor parametrizado.
    public GuardaRedes (String nome, int numeroC, int velocidade, int resistencia, int destreza, int impulsao,
                        int jogoCabeca, int remate, int capPasse, int elasticidade, List<String> equipasAnteriores) {
        super (nome, numeroC, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse, equipasAnteriores);
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

    public static GuardaRedes parse(String input){
        String[] campos = input.split(",");
        return new GuardaRedes(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]));
    }

    /**
     * Função toString da subclasse GuardaRedes.
     * @return String com informação sobre guardaRedes.
     */
    public String toString () {
        StringBuilder sb = new StringBuilder("Jogador: ");
        sb.append(super.getNome()).append(" {\n");
        sb.append("\tEquipa: ").append(super.getNomeEquipa()).append("\n");
        sb.append("\tNumero da camisola: ").append(super.getNumeroCamisola()).append("\n");
        if(super.getEquipasAnteriores().isEmpty())
            sb.append("\tEquipas anteriores: Sem historial noutras equipas").append("\n");
        else
            sb.append("\tEquipas anteriores: ").append(super.getEquipasAnteriores().toString()).append("\n");
        sb.append("\tPosição: ").append(this.getPosicao());
        sb.append("\n\tResistência: ").append(super.getResistencia());
        sb.append("\n\tVelocidade: ").append(super.getVelocidade());
        sb.append("\n\tDestreza: ").append(super.getDestreza());
        sb.append("\n\tImpulsao: ").append(super.getImpulsao());
        sb.append("\n\tJogo de cabeça: ").append(super.getJogoCabeca());
        sb.append("\n\tRemate: ").append(super.getRemate());
        sb.append("\n\tCapacidade de passe: ").append(super.getCapPasse());
        sb.append("\n\tElasticidade: ").append(this.getElasticidade());
        sb.append(String.format("\n\tOverall: %.2f", this.overall())).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

    /**
     * Função equals da subclasse GuardaRedes
     * @param o Object a comparar
     * @return  true se forem iguais, false se nao forem.
     */
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        GuardaRedes gr = (GuardaRedes) o;
        return (super.equals(gr) && this.getPosicao().equals(gr.getPosicao()) && this.getElasticidade() == gr.getElasticidade());
    }

    /**
     * Função clone da subclasse GuardaRedes.
     * @return Novo GuardaRedes.
     */
    public GuardaRedes clone () {return new GuardaRedes(this);}
}
