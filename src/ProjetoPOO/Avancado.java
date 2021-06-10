package ProjetoPOO;

import java.util.List;

public class Avancado extends Jogador {
    private final String posicao = new String ("Avancado");

    //Construtor vazio.
    public Avancado () {
        super ("Avancado");
    }

    //Construtor parametrizado.
    public Avancado (String nome, int numeroCamisola, int velocidade, int resistencia, int destreza, int impulsao,
                     int jogoCabeca, int remate, int capPasse) {
        super (nome, numeroCamisola, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse);
    }

    //Construtor parametrizado (Inclui equipas anteriores).
    public Avancado (String nome, int numeroCamisola, int velocidade, int resistencia, int destreza, int impulsao,
                     int jogoCabeca, int remate, int capPasse, List<String> equipasAnteriores) {
        super (nome, numeroCamisola, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse,equipasAnteriores);
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

    public static Avancado parse(String input){
        String[] campos = input.split(",");
        return new Avancado(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]));
    }

    /**
     * Função toString da subclasse Avancado
     * @return String com informação sobre avancado.
     */
    @Override public String toString () {
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
        sb.append(String.format("\n\tOverall: %.2f", this.overall())).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

    /**
     * Função equals da sublcasse Avancado
     * @param o Object a comparar
     * @return True no caso de serem iguais, false no caso de nao serem.
     */
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Avancado gr = (Avancado) o;
        return (super.equals(gr) && this.getPosicao().equals(gr.getPosicao()));
    }

    /**
     * Função clone da subclasse Avancado.
     * @return Novo avancado.
     */
    @Override public Avancado clone () {return new Avancado(this);}
}
