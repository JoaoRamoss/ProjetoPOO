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

    public static Map<String, Equipa> trocaEquipas (String antiga, String nova, StoredData d, Jogador j, int number) {
        Map<String, Equipa> eq = new HashMap<>();
        eq = d.getEquipas();
        j.setNumeroCamisola(number);
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
            throw new EquipaOuJogInvalidoException("A equipa \"" + nome + "\" não é uma equipa válida");
        }
        return strB.toString();
    }

        public void trocaJogador () throws EquipaOuJogInvalidoException {
        Random random = new Random();
        String antiga = "", nova = "";
        Equipa equipaAntiga, equipaNova;
        StringBuilder sb = new StringBuilder();
        boolean passes = false;
        System.out.print("A que equipa pertence o jogador que quer trocar? ");
        Equipa temp;
        while(!passes) {
            passes = true;
            antiga = scanner.nextLine();
            if (!this.equipas.containsKey(antiga)) {
                passes = false;
                System.out.println("A equipa inserida é inválida. Tenta novamente: ");
            }
        }
        System.out.print("Para que equipa deseja trocar o jogador? ");
        passes = false;
        while(!passes) {
            passes = true;
            nova = scanner.nextLine();
            if (!this.equipas.containsKey(nova)) {
                System.out.println("Equipa inserida não é uma equipa válida. Tenta novamente: ");
                passes = false;
            }
        }
        System.out.println("Eis a lista de jogadores pertencentes à equipa " + antiga + ": ");
        System.out.println(this.showJogadoresEquipa(antiga));
        System.out.print("Qual é o número do jogador que pretende trocar? ");
        int numero = 0;
        passes = false;
        while(!passes) {
            try {
                passes = true;
                numero = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Argumento inserido tem de ser um número inteiro. Tenta novamente: ");
                passes = false;
            }
        }
        int finalNumero = numero;
        int newNumber = numero;
        if (this.equipas.get(nova).getJogadores().values().stream().anyMatch(j -> j.getNumeroCamisola() == finalNumero)) {
            boolean rep = true;
            while (rep) {
                rep = false;
                newNumber = random.nextInt(100);
                for (Jogador j : this.equipas.get(nova).getJogadores().values()) {
                    if (j.getNumeroCamisola() == newNumber) {
                        rep = true;
                        break;
                    }
                }
            }
        }

        Map<String, Equipa> updatedEquipas = trocaEquipas(antiga, nova,  this, this.getEquipas().get(antiga).getJogadores().get(numero), newNumber);
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
        boolean passed = false;
        while (!passed) {
            try {
                passed = true;
                equipa = scanner.nextLine();
                this.equipas.get(equipa).getNome(); //Verifica se a equipa existe.
            } catch (NullPointerException e) {
                System.out.println("\"" + equipa + "\" não é uma equipa válida. Por favor tente novamente: ");
                passed = false;
            }
            finally {
                if (passed) {
                    System.out.println(showJogadoresEquipa(equipa));
                    passed = true;
                }
            }
        }
        System.out.println("Insere o numero do jogador: ");
        passed = false;
        int numero = -1;
        while (!passed) {
            try {
                passed = true;
                numero = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("O input deverá ser um número inteiro. Tente novamente:");
                passed = false;
            }
            if (passed)
                try {
                    res = this.equipas.get(equipa).getJogadores().get(numero).clone().toString();
                }
                catch (NullPointerException e) {
                    System.out.println("O número introduzido não corresponde a um jogador nesta equipa. Tente novamente:");
                    passed = false;
                }
            scanner.nextLine();
        }
        return res;
    }

    public String resultadoJogo() {
        String casa = "";
        String visitante = "";
        Jogo play = null;
        System.out.println("Insere o nome da equipa da casa: ");
        boolean passes = false;
        while(!passes) {
            casa = scanner.nextLine();
            for (Jogo j : this.jogos)
                if (j.getEquipaCasa().equals(casa)) {
                    passes = true;
                    break;
                }
            if (!passes)
                System.out.println("Equipa inserida é inválida. Tenta novamente: ");
            System.out.println("Insere a equipa visitante: ");
            visitante = scanner.nextLine();
            for (Jogo j : this.jogos) {
                if (j.getEquipaCasa().equals(casa) && j.getEquipaFora().equals(visitante)) {
                    passes = true;
                    play = new Jogo(j);
                    break;
                }
                else {
                    passes = false;
                }
            }
            if (!passes) {
                System.out.println("Não existe nenhum jogo com essas equipas. Tenta inserir as duas equipas de novo: ");
            }
        }
        return play.getResultado(this);
    }

    public StoredData clone () {return new StoredData(this);}

}
