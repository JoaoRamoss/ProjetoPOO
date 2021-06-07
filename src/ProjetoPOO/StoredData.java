package ProjetoPOO;

import java.util.*;

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

    private static final Scanner scanner = new Scanner(System.in);

    public String showJogadores() {
        StringBuilder strB = new StringBuilder("Jogadores: {\n");
        for (Equipa j : this.getEquipas().values()) {
            for (Jogador jog : j.getJogadores().values()) {
                strB.append("\t").append(jog.getNome()).append(" (").append(jog.getNumeroCamisola()).append(")").append("\n");
            }
        }
        strB.append("}\n");
        return strB.toString();
    }

    public String showJogadoresEquipa (String nome) throws EquipaOuJogInvalidoException {
        StringBuilder strB = new StringBuilder("Jogadores: {\n");
        try {
            for (Jogador j : this.getEquipas().get(nome).getJogadores().values()) {
                strB.append("\t").append(j.getNome()).append(" (").append(j.getNumeroCamisola()).append(")").append("\n");
            }
            strB.append("}\n");
        } catch (NullPointerException e) {
            throw new EquipaOuJogInvalidoException("Equipa \"" + nome + "\" não é uma equipa válida");
        }
        return strB.toString();
    }

        public void trocaJogador () throws EquipaOuJogInvalidoException {

        String antiga = "", nova = "";
        Equipa equipaAntiga, equipaNova;
        StringBuilder sb = new StringBuilder();
        System.out.print("A que equipa pertence o jogador que quer trocar? ");
        try {
            antiga = scanner.nextLine();
        }
        catch(InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        finally {
            equipaAntiga = this.getEquipas().get(antiga);
        }
        if (equipaAntiga == null) throw new EquipaOuJogInvalidoException("Equipa \"" + antiga + "\" não é uma equipa válida.");
        System.out.print("Para que equipa deseja trocar o jogador? ");
        try {
            nova = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        finally {
            equipaNova = this.getEquipas().get(nova);
            System.out.println("Eis a lista de jogadores pertencentes à equipa " + antiga + ": ");
            System.out.println(this.showJogadoresEquipa(antiga));
        }
        if (equipaNova == null) throw new EquipaOuJogInvalidoException("Equipa \"" + nova + "\" não é uma equipa válida.");
        System.out.print("Qual é o número do jogador que pretende trocar? ");
        int numero = scanner.nextInt();
        Map<String, Equipa> updatedEquipas = trocaEquipas(antiga, nova,  this, this.getEquipas().get(antiga).getJogadores().get(numero));
        this.setEquipas(updatedEquipas);
        scanner.nextLine();
        System.out.println("Jogador trocado com sucesso.\n");
    }

    public String showJogos () {
        StringBuilder strB = new StringBuilder("Jogos: {\n");
        for (Jogo j : this.getJogos())
            strB.append("\t").append(j.getEquipaCasa()).append(" vs ").append(j.getEquipaFora()).append("\n");
        strB.append("}\n");
        return strB.toString();
    }

    public String showEquipas () {
        StringBuilder strB = new StringBuilder("Equipas: {\n");
        for (String s : this.getEquipas().keySet())
            strB.append("\t").append(s).append("\n");
        strB.append("}\n");
        return strB.toString();
    }

    public String consultarJogador() throws EquipaOuJogInvalidoException{
        String equipa = "";
        String res = "";
        System.out.println("A que equipa pertence o jogador? ");
        try {
            equipa = scanner.nextLine();
            System.out.println(showJogadoresEquipa(equipa));
        } catch (NullPointerException e) {
            throw new EquipaOuJogInvalidoException("Equipa Inválida.");
        }
        System.out.println("Insere o numero do jogador: ");
        int numero;
        try {
            numero = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new EquipaOuJogInvalidoException("Argumento inserido tem de ser um número inteiro.");
        }
        try {
            res = this.equipas.get(equipa).getJogadores().get(numero).clone().toString();
        }
        catch(NullPointerException e) {
            throw new EquipaOuJogInvalidoException("Número de jogador inválido.");
        }
        scanner.nextLine();
        return res;
    }

    public StoredData clone () {return new StoredData(this);}

}
