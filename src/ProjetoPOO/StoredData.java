package ProjetoPOO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoredData {
    private Map<String, Equipa> equipas;
    private List<Jogo> jogos;

    public StoredData () {
        this.equipas = new HashMap<>();
        this.jogos = new ArrayList<>();
    }

    public StoredData (Map<String, Equipa> equipas, List<Jogo> jogos) {
        this.equipas = new HashMap<>();
        this.jogos = new ArrayList<>();
        equipas.forEach((k, v) -> this.equipas.put(k, v.clone()));
        jogos.forEach(k -> this.jogos.add(k.clone()));
    }

    public StoredData(StoredData sd) {
        this.equipas = sd.getEquipas();
        this.jogos = sd.getJogos();
    }

    public Map<String, Equipa> getEquipas() {
        Map<String, Equipa> aux = new HashMap<>();
        this.equipas.forEach((k, v) -> aux.put(k, v.clone()));
        return aux;
    }

    public void setEquipas (Map<String, Equipa> equipas) {
        this.equipas.clear();
        equipas.forEach((k,v) -> this.equipas.put(k, v.clone()));
    }

    public static Map<String, Equipa> trocaEquipas (String antiga, String nova, StoredData d, Jogador j) {
        Map<String, Equipa> eq = new HashMap<>();
        eq = d.getEquipas();
        eq.get(antiga).trocaEquipa(eq.get(nova), j);
        return eq;
    }

    public List<Jogo> getJogos () {
        List <Jogo> aux = new ArrayList<>();
        this.jogos.forEach(j -> aux.add(j.clone()));
        return aux;
    }

    public void addEquipaData(Equipa e) {
        this.equipas.put(e.getNome(), e.clone());
    }

    public void addJogoData (Jogo jog) {
        this.jogos.add(jog.clone());
    }

    public void addEquipas (Map<String, Equipa> equipas) {
        equipas.forEach((k, v) -> this.equipas.put(k, v.clone()));
    }

    public StoredData clone () {return new StoredData(this);}

}
