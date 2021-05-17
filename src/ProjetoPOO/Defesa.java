package ProjetoPOO;

import java.util.List;

public class Defesa extends Jogador {
    private final String posicao = new String ("Defesa");

    //Construtor vazio.
    public Defesa () {
        super ("Defesa");
    }

    //Construtor parametrizado.
    public Defesa (String nome, int numeroC, int velocidade, int resistencia, int destreza, int impulsao,
                     int jogoCabeca, int remate, int capPasse) {
        super (nome, numeroC, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse);
    }

    //Construtor parametrizado (Inclui equipas anteriores).
    public Defesa (String nome, int numeroC, int velocidade, int resistencia, int destreza, int impulsao,
                   int jogoCabeca, int remate, int capPasse, List<String> equipasAnteriores) {
        super (nome, numeroC, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse, equipasAnteriores);
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


    public static Defesa parse(String input){
        String[] campos = input.split(",");
        return new Defesa(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]));
    }
    /**
     * Função toString da subclasse Defesa
     * @return String com informação sobre o Defesa.
     */
    public String toString () {
        StringBuilder sb = new StringBuilder("Jogador: ");
        sb.append(super.getNome()).append("\n");
        sb.append("Equipa: ").append(super.getNomeEquipa()).append("\n");
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
     * Função equals da subclasse Defesa
     * @param o Object a comparar
     * @return  True se forem iguais, false se não forem.
     */
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Defesa gr = (Defesa) o;
        return (super.equals(gr) && this.getPosicao().equals(gr.getPosicao()));
    }

    /**
     * Função clone da subclasse Defesa
     * @return Novo Defesa.
     */
    public Defesa clone () {return new Defesa (this);}
}
