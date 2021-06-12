package ProjetoPOO;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jogo implements Serializable {
    private String equipaCasa;
    private String equipaFora;
    private int golosCasa, golosFora;
    private LocalDate date;
    private List<Integer> jogadoresCasa;
    private List<Integer> jogadoresFora;
    Map<Integer, Integer> substituicoesCasa = new HashMap<>();
    Map<Integer, Integer> substituicoesFora = new HashMap<>();

    public Jogo (String ec, String ef, int gc, int gf, LocalDate d,  List<Integer> jc, Map<Integer, Integer> sc,  List<Integer> jf, Map<Integer, Integer> sf){
        this.equipaCasa = ec;
        this.equipaFora = ef;
        this.golosCasa = gc;
        this.golosFora = gf;
        this.date = d;
        this.jogadoresCasa = new ArrayList<>(jc);
        this.jogadoresFora = new ArrayList<>(jf);
        this.substituicoesCasa = new HashMap<>(sc);
        this.substituicoesFora = new HashMap<>(sf);
    }

    public Jogo (Jogo j) {
        this.equipaCasa = j.getEquipaCasa();
        this.equipaFora = j.getEquipaFora();
        this.golosCasa = j.getGolosCasa();
        this.golosFora = j.getGolosFora();
        this.date = j.getDate();
        this.jogadoresCasa = j.getJogadoresCasa();
        this.jogadoresFora = j.getJogadoresFora();
        this.substituicoesFora = j.getSubstitucoesFora();
        this.substituicoesCasa = j.getSubstituicoesCasa();
    }

    public Jogo() {
        this.equipaCasa = "";
        this.equipaFora = "";
        this.golosCasa = 0;
        this.golosFora = 0;
        this.date = LocalDate.now();
        this.jogadoresCasa = new ArrayList<>();
        this.jogadoresFora = new ArrayList<>();
        this.substituicoesFora = new HashMap<>();
        this.substituicoesCasa = new HashMap<>();
    }

    public static Jogo parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");
        List<Integer> jc = new ArrayList<>();
        List<Integer> jf = new ArrayList<>();
        Map<Integer, Integer> subsC = new HashMap<>();
        Map<Integer, Integer> subsF = new HashMap<>();
        for (int i = 5; i < 16; i++){
            jc.add(Integer.parseInt(campos[i]));
        }
        for (int i = 16; i < 19; i++){
            String[] sub = campos[i].split("->");
            subsC.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        for (int i = 19; i < 30; i++){
            jf.add(Integer.parseInt(campos[i]));
        }
        for (int i = 30; i < 33; i++){
            String[] sub = campos[i].split("->");
            subsF.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        return new Jogo(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                jc, subsC, jf, subsF);
    }

    public String getEquipaCasa () {return this.equipaCasa;}

    public String getEquipaFora() {
        return equipaFora;
    }

    public int getGolosCasa() {
        return golosCasa;
    }

    public int getGolosFora() {
        return golosFora;
    }

    public List<Integer> getJogadoresCasa() {
        return this.jogadoresCasa;
    }

    public List<Integer> getJogadoresFora() {
        return this.jogadoresFora;
    }

    public Map<Integer, Integer> getSubstitucoesFora() {
        return this.substituicoesFora;
    }

    public Map<Integer, Integer> getSubstituicoesCasa() {
        return this.substituicoesCasa;
    }

    public LocalDate getDate() {
        return date;
    }

    public String toString () {
        StringBuilder sb = new StringBuilder();
        sb.append("Jogo: ").append("| ").append(this.equipaCasa).append(" | vs | ").append(this.equipaFora).append(" |").append(" {\n");
        sb.append("\tData: ").append(this.date).append("\n");
        sb.append("\tScore: ").append(this.golosCasa).append("-").append(this.golosFora).append("\n");
        sb.append("\tJogadores principais casa: ");
        sb.append(this.jogadoresCasa.toString()).append("\n");
        sb.append("\tJohadores principais fora: ");
        sb.append(this.jogadoresFora.toString()).append("\n");
        sb.append("\tSubstituições da equipa da casa: [");
        for (int i : this.substituicoesCasa.keySet()) {
            sb.append(i).append("->").append(this.substituicoesCasa.get(i)).append(",");
        }
        sb.append("]\n");
        sb.append("\tSubstituições da equipa Visitante: [");
        for (int i : this.substituicoesFora.keySet()) {
            sb.append(i).append("->").append(this.substituicoesFora.get(i)).append(",");
        }
        sb.append("]\n");
        sb.append("}\n");

        return sb.toString();
    }

    public static void run () {
        int mins = 0;

    }

    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Jogo jog = (Jogo) o;
        return this.substituicoesCasa.equals(jog.getSubstituicoesCasa()) && this.substituicoesFora.equals(jog.getSubstitucoesFora()) &&
                this.golosFora == jog.getGolosFora() && this.golosCasa == jog.getGolosFora() && this.equipaCasa.equals(jog.getEquipaCasa()) &&
                this.equipaFora.equals(jog.getEquipaFora()) && this.date.equals(jog.getDate()) && this.substituicoesFora.values().equals(jog.getSubstitucoesFora().values()) &&
                this.substituicoesFora.keySet().equals(jog.getSubstitucoesFora().keySet()) && this.substituicoesCasa.values().equals(jog.getSubstituicoesCasa().values()) &&
                this.substituicoesCasa.keySet().equals(jog.getSubstituicoesCasa().keySet());
    }

    public Jogo clone () {return new Jogo(this);}
}
