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

    public Lateral (String nome, int numeroC, int velocidade, int resistencia, int destreza, int impulsao,
                  int jogoCabeca, int remate, int capPasse, int cruzamento) {
        super (nome, numeroC, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse);
        this.cruzamento = cruzamento;
    }

    public Lateral (String nome, int numeroC, int velocidade, int resistencia, int destreza, int impulsao,
                    int jogoCabeca, int remate, int capPasse, int cruzamento, List<String> equipasAnteriores) {
        super (nome, numeroC, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse, equipasAnteriores);
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

    public static Lateral parse(String input){
        String[] campos = input.split(",");
        return new Lateral(campos[0], Integer.parseInt(campos[1]),
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
     * Função toString da subclasse Lateral.
     * @return  String com informação sobre Lateral.
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
        sb.append("\n\tCapacidade de cruzamento: ").append(this.getCruzamento());
        sb.append(String.format("\n\tOverall: %.2f", this.overall())).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

    /**
     * Função equals da subclasse Lateral.
     * @param o Object a comparar
     * @return true se forem iguais, false se não forem.
     */
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Lateral gr = (Lateral) o;
        return (super.equals(gr) && this.getPosicao().equals(gr.getPosicao()) && this.getCruzamento() == gr.getCruzamento());
    }

    /**
     * Função clone da subclasse Lateral.
     * @return Novo Lateral.
     */
    public Lateral clone () {return new Lateral(this);}
}
