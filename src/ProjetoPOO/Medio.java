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

    public Medio (String nome, int numeroC, int velocidade, int resistencia, int destreza, int impulsao,
                   int jogoCabeca, int remate, int capPasse, int capRecuperacao) {
        super (nome, numeroC, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse);
        this.capRecuperacao = capRecuperacao;
    }

    public Medio (String nome, int numeroC, int velocidade, int resistencia, int destreza, int impulsao,
                  int jogoCabeca, int remate, int capPasse, int capRecuperacao, List <String> equipasAnteriores) {
        super (nome, numeroC, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse, equipasAnteriores);
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

    public static Medio parse(String input){
        String[] campos = input.split(",");
        return new Medio(campos[0], Integer.parseInt(campos[1]),
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
     * Função toString da subclasse Medio.
     * @return String com informação do Médio.
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
        sb.append("\n\tCapacidade de recuperação: ").append(this.getCapRecuperacao());
        sb.append(String.format("\n\tOverall: %.2f", this.overall())).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

    /**
     * Função equals da subclasse medio.
     * @param o Object a comparar
     * @return true se forem iguais, false se nao forem.
     */
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Medio gr = (Medio) o;
        return (super.equals(gr) && this.getPosicao().equals(gr.getPosicao()) && this.getCapRecuperacao() == gr.getCapRecuperacao());
    }

    /**
     * Função clone da subclasse Medio
     * @return novo Medio.
     */
    public Medio clone () {return new Medio(this);}
}
