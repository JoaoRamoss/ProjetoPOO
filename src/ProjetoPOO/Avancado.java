package ProjetoPOO;

import java.util.List;

public class Avancado extends Jogador {
    private final String posicao = new String ("Avancado");

    public Avancado () {
        super ("Avancado");
    }

    public Avancado (String ID, String nome, String equipa, int velocidade, int resistencia, int destreza, int impulsao,
                     int jogoCabeca, int remate, int capPasse, boolean principal) {
        super (ID, nome, equipa, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse, principal);
    }

    public Avancado (String ID, String nome, String equipa, int velocidade, int resistencia, int destreza, int impulsao,
                     int jogoCabeca, int remate, int capPasse, boolean principal, List<String> equipasAnteriores) {
        super (ID, nome, equipa, velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse, principal, equipasAnteriores);
    }

    public Avancado (Avancado a) {
        super(a);
    }

    public String getPosicao () {return this.posicao;}

    public double overall () {
        return super.getVelocidade() * 0.25 + super.getResistencia() * 0.10 + super.getDestreza() * 0.10 + super.getImpulsao() * 0.15 + super.getJogoCabeca() * 0.15 +
                super.getRemate() * 0.20 + super.getCapPasse() * 0.05;
    }

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

    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Avancado gr = (Avancado) o;
        return (this.getPosicao().equals(gr.getPosicao()) && super.getVelocidade() == gr.getVelocidade() && super.getResistencia() == gr.getResistencia()
                && super.getDestreza() == gr.getDestreza() && super.getImpulsao() == gr.getImpulsao() && super.getJogoCabeca() == gr.getJogoCabeca() &&
                super.getCapPasse() == gr.getCapPasse() && this.getNomeEquipa().equals(gr.getNomeEquipa()) &&
                super.getNome().equals(gr.getNome()));
    }

    @Override public Avancado clone () {return new Avancado(this);}
}
