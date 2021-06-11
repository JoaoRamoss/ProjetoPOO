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
        sb.append("}\n");

        return sb.toString();
    }

    public static void run () {
        int mins = 0;

    }

    private void substituicaoCasa(StoredData d, Equipa e, int numero) {
        e.removeJogador(e.getJogadores().get(numero));
        e.insereJogador(d.getEquipas().get(this.equipaCasa).getJogadores().get(this.substituicoesCasa.get(numero)));
        System.out.println("Substituição: " + numero + "-> " + this.substituicoesCasa.get(numero));
    }

    private void substituicaoFora(StoredData d, Equipa e, int numero) {
        e.removeJogador(e.getJogadores().get(numero));
        e.insereJogador(d.getEquipas().get(this.equipaFora).getJogadores().get(this.substituicoesFora.get(numero)));
        System.out.println("Substituição: " + numero + "-> " + this.substituicoesFora.get(numero));
    }

    public Jogo clone () {return new Jogo(this);}
}
