package ProjetoPOO;

import javax.management.PersistentMBean;
import java.io.*;
import java.util.*;

public class StoredData implements Serializable {
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

    private void registaAvancado (Equipa equipa) throws Exception {
        String nome;
        int numeroC, velocidade, resistencia, destreza, impulsao, cabeceamento, remate, passe;
        System.out.println("Insere o nome do jogador: ");
        nome = scanner.nextLine();

        System.out.println("Insere o numero da camisola: ");
        try {
            numeroC = scanner.nextInt();
            numeroC = numeroC >= 100 ? 99 : numeroC;
            numeroC = numeroC < 0 ? 1 : numeroC;
            scanner.nextLine();
            boolean exists = equipa.getJogadores().containsKey(numeroC);
            while (exists) {
                System.out.println("Número invalido, tenta de novo: ");
                numeroC = scanner.nextInt();
                numeroC = numeroC >= 100 ? 99 : numeroC;
                numeroC = numeroC < 0 ? 1 : numeroC;
                scanner.nextLine();
                exists = equipa.getJogadores().containsKey(numeroC);
            }
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a velocidade: ");
        try {
            velocidade = scanner.nextInt();
            velocidade = velocidade >= 100 ? 99 : velocidade;
            velocidade = velocidade < 0 ? 1 : velocidade;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a resistência: ");
        try {
            resistencia = scanner.nextInt();
            resistencia = resistencia >= 100 ? 99 : resistencia;
            resistencia = resistencia < 0 ? 1 : resistencia;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a destreza: ");
        try {
            destreza = scanner.nextInt();
            destreza = destreza >= 100 ? 99 : destreza;
            destreza = destreza < 0 ? 1 : destreza;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a impulsão: ");
        try {
            impulsao = scanner.nextInt();
            impulsao = impulsao >= 100 ? 99 : impulsao;
            impulsao = impulsao < 0 ? 1 : impulsao;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capacidade de jogo de cabeça: ");
        try {
            cabeceamento = scanner.nextInt();
            cabeceamento = cabeceamento >= 100 ? 99 : cabeceamento;
            cabeceamento = cabeceamento < 0 ? 1 : cabeceamento;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capacidade de remate: ");
        try {
            remate = scanner.nextInt();
            remate = remate >= 100 ? 99 : remate;
            remate = remate < 0 ? 1 : remate;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capacidade de passe: ");
        try {
            passe = scanner.nextInt();
            passe = passe >= 100 ? 99 : passe;
            passe = passe < 0 ? 1 : passe;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        equipa.insereJogador(new Avancado(nome, numeroC, velocidade, resistencia, destreza, impulsao, cabeceamento, remate, passe));
    }

    private void registaDefesa(Equipa equipa) throws Exception {
        String nome;
        int numeroC, velocidade, resistencia, destreza, impulsao, cabeceamento, remate, passe;
        System.out.println("Insere o nome do jogador: ");
        nome = scanner.nextLine();

        System.out.println("Insere o numero da camisola: ");
        try {
            numeroC = scanner.nextInt();
            numeroC = numeroC >= 100 ? 99 : numeroC;
            numeroC = numeroC < 0 ? 1 : numeroC;
            scanner.nextLine();
            boolean exists = equipa.getJogadores().containsKey(numeroC);
            while (exists) {
                System.out.println("Número invalido, tenta de novo: ");
                numeroC = scanner.nextInt();
                numeroC = numeroC >= 100 ? 99 : numeroC;
                numeroC = numeroC < 0 ? 1 : numeroC;
                scanner.nextLine();
                exists = equipa.getJogadores().containsKey(numeroC);
            }
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a velocidade: ");
        try {
            velocidade = scanner.nextInt();
            velocidade = velocidade >= 100 ? 99 : velocidade;
            velocidade = velocidade < 0 ? 1 : velocidade;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a resistência: ");
        try {
            resistencia = scanner.nextInt();
            resistencia = resistencia >= 100 ? 99 : resistencia;
            resistencia = resistencia < 0 ? 1 : resistencia;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a destreza: ");
        try {
            destreza = scanner.nextInt();
            destreza = destreza >= 100 ? 99 : destreza;
            destreza = destreza < 0 ? 1 : destreza;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a impulsão: ");
        try {
            impulsao = scanner.nextInt();
            impulsao = impulsao >= 100 ? 99 : impulsao;
            impulsao = impulsao < 0 ? 1 : impulsao;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capacidade de jogo de cabeça: ");
        try {
            cabeceamento = scanner.nextInt();
            cabeceamento = cabeceamento >= 100 ? 99 : cabeceamento;
            cabeceamento = cabeceamento < 0 ? 1 : cabeceamento;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capacidade de remate: ");
        try {
            remate = scanner.nextInt();
            remate = remate >= 100 ? 99 : remate;
            remate = remate < 0 ? 1 : remate;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capacidade de passe: ");
        try {
            passe = scanner.nextInt();
            passe = passe >= 100 ? 99 : passe;
            passe = passe < 0 ? 1 : passe;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        equipa.insereJogador(new Defesa(nome, numeroC, velocidade, resistencia, destreza, impulsao, cabeceamento, remate, passe));
    }

    private void registaGuardaRedes (Equipa equipa) throws Exception {
        String nome;
        int numeroC, velocidade, resistencia, destreza, impulsao, cabeceamento, remate, passe, elast;
        System.out.println("Insere o nome do jogador: ");
        nome = scanner.nextLine();

        System.out.println("Insere o numero da camisola: ");
        try {
            numeroC = scanner.nextInt();
            numeroC = numeroC >= 100 ? 99 : numeroC;
            numeroC = numeroC < 0 ? 1 : numeroC;
            scanner.nextLine();
            boolean exists = equipa.getJogadores().containsKey(numeroC);
            while (exists) {
                System.out.println("Número invalido, tenta de novo: ");
                numeroC = scanner.nextInt();
                scanner.nextLine();
                exists = equipa.getJogadores().containsKey(numeroC);
            }
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a velocidade: ");
        try {
            velocidade = scanner.nextInt();
            velocidade = velocidade >= 100 ? 99 : velocidade;
            velocidade = velocidade < 0 ? 1 : velocidade;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a resistência: ");
        try {
            resistencia = scanner.nextInt();
            resistencia = resistencia >= 100 ? 99 : resistencia;
            resistencia = resistencia < 0 ? 1 : resistencia;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a destreza: ");
        try {
            destreza = scanner.nextInt();
            destreza = destreza >= 100 ? 99 : destreza;
            destreza = destreza < 0 ? 1 : destreza;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a impulsão: ");
        try {
            impulsao = scanner.nextInt();
            impulsao = impulsao >= 100 ? 99 : impulsao;
            impulsao = impulsao < 0 ? 1 : impulsao;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capacidade de jogo de cabeça: ");
        try {
            cabeceamento = scanner.nextInt();
            cabeceamento = cabeceamento >= 100 ? 99 : cabeceamento;
            cabeceamento = cabeceamento < 0 ? 1 : cabeceamento;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capacidade de remate: ");
        try {
            remate = scanner.nextInt();
            remate = remate >= 100 ? 99 : remate;
            remate = remate < 0 ? 1 : remate;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capacidade de passe: ");
        try {
            passe = scanner.nextInt();
            passe = passe >= 100 ? 99 : passe;
            passe = passe < 0 ? 1 : passe;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a elasticidade: ");
        try {
            elast = scanner.nextInt();
            elast = elast >= 100 ? 99 : elast;
            elast = elast < 0 ? 1 : elast;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        equipa.insereJogador(new GuardaRedes(nome, numeroC, velocidade, resistencia, destreza, impulsao, cabeceamento, remate, passe, elast));
    }

    private void registaMedio (Equipa equipa) throws Exception {
        String nome;
        int numeroC, velocidade, resistencia, destreza, impulsao, cabeceamento, remate, passe, recup;
        System.out.println("Insere o nome do jogador: ");
        nome = scanner.nextLine();

        System.out.println("Insere o numero da camisola: ");
        try {
            numeroC = scanner.nextInt();
            numeroC = numeroC >= 100 ? 99 : numeroC;
            numeroC = numeroC < 0 ? 1 : numeroC;
            scanner.nextLine();
            boolean exists = equipa.getJogadores().containsKey(numeroC);
            while (exists) {
                System.out.println("Número invalido, tenta de novo: ");
                numeroC = scanner.nextInt();
                numeroC = numeroC >= 100 ? 99 : numeroC;
                numeroC = numeroC < 0 ? 1 : numeroC;
                scanner.nextLine();
                exists = equipa.getJogadores().containsKey(numeroC);
            }
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a velocidade: ");
        try {
            velocidade = scanner.nextInt();
            velocidade = velocidade >= 100 ? 99 : velocidade;
            velocidade = velocidade < 0 ? 1 : velocidade;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a resistência: ");
        try {
            resistencia = scanner.nextInt();
            resistencia = resistencia >= 100 ? 99 : resistencia;
            resistencia = resistencia < 0 ? 1 : resistencia;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a destreza: ");
        try {
            destreza = scanner.nextInt();
            destreza = destreza >= 100 ? 99 : destreza;
            destreza = destreza < 0 ? 1 : destreza;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a impulsão: ");
        try {
            impulsao = scanner.nextInt();
            impulsao = impulsao >= 100 ? 99 : impulsao;
            impulsao = impulsao < 0 ? 1 : impulsao;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capacidade de jogo de cabeça: ");
        try {
            cabeceamento = scanner.nextInt();
            cabeceamento = cabeceamento >= 100 ? 99 : cabeceamento;
            cabeceamento = cabeceamento < 0 ? 1 : cabeceamento;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capacidade de remate: ");
        try {
            remate = scanner.nextInt();
            remate = remate >= 100 ? 99 : remate;
            remate = remate < 0 ? 1 : remate;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capacidade de passe: ");
        try {
            passe = scanner.nextInt();
            passe = passe >= 100 ? 99 : passe;
            passe  = passe < 0 ? 1 : passe;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capaciddade de recuperação: ");
        try {
            recup = scanner.nextInt();
            recup = recup >= 100 ? 99 : recup;
            recup = recup < 0 ? 1 : recup;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        equipa.insereJogador(new Medio(nome, numeroC, velocidade, resistencia, destreza, impulsao, cabeceamento, remate, passe, recup));
    }

    private void registaLateral (Equipa equipa) throws Exception {
        String nome;
        int numeroC, velocidade, resistencia, destreza, impulsao, cabeceamento, remate, passe, cruza;
        System.out.println("Insere o nome do jogador: ");
        nome = scanner.nextLine();

        System.out.println("Insere o numero da camisola: ");
        try {
            numeroC = scanner.nextInt();
            numeroC = numeroC >= 100 ? 99 : numeroC;
            numeroC = numeroC < 0 ? 1 : numeroC;
            scanner.nextLine();
            boolean exists = equipa.getJogadores().containsKey(numeroC);
            while (exists) {
                System.out.println("Número invalido, tenta de novo: ");
                numeroC = scanner.nextInt();
                numeroC = numeroC >= 100 ? 99 : numeroC;
                numeroC = numeroC < 0 ? 1 : numeroC;
                scanner.nextLine();
                exists = equipa.getJogadores().containsKey(numeroC);
            }
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a velocidade: ");
        try {
            velocidade = scanner.nextInt();
            velocidade = velocidade >= 100 ? 99 : velocidade;
            velocidade = velocidade < 0 ? 1 : velocidade;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a resistência: ");
        try {
            resistencia = scanner.nextInt();
            resistencia = resistencia >= 100 ? 99 : resistencia;
            resistencia = resistencia < 0 ? 1 : resistencia;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a destreza: ");
        try {
            destreza = scanner.nextInt();
            destreza = destreza >= 100 ? 99 : destreza;
            destreza = destreza < 0 ? 1 : destreza;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a impulsão: ");
        try {
            impulsao = scanner.nextInt();
            impulsao = impulsao >= 100 ? 99 : impulsao;
            impulsao = impulsao < 0 ? 1 : impulsao;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capacidade de jogo de cabeça: ");
        try {
            cabeceamento = scanner.nextInt();
            cabeceamento = cabeceamento >= 100 ? 99 : cabeceamento;
            cabeceamento = cabeceamento < 0 ? 1 : cabeceamento;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capacidade de remate: ");
        try {
            remate = scanner.nextInt();
            remate = remate >= 100 ? 99 : remate;
            remate = remate < 0 ? 1 : remate;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capacidade de passe: ");
        try {
            passe = scanner.nextInt();
            passe = passe >= 100 ? 99 : passe;
            passe = passe < 0 ? 1 : passe;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        System.out.println("Insere a capacidade de cruzamento: ");
        try {
            cruza = scanner.nextInt();
            cruza = cruza >= 100 ? 99 : cruza;
            cruza = cruza < 0 ? 1 : cruza;
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new Exception(e.toString());
        }
        equipa.insereJogador(new GuardaRedes(nome, numeroC, velocidade, resistencia, destreza, impulsao, cabeceamento, remate, passe, cruza));
    }

    void registaJogador (Equipa equipa) throws Exception {
        System.out.println("Em que posição queres que o jogador jogue?");
        System.out.println("1) Avancado\n2) Defesa\n3) Guarda-Redes\n4) Médio\n5) Lateral\n");
        int pos;
        pos = scanner.nextInt();
        scanner.nextLine();
        switch(pos) {
            case 1 -> registaAvancado(equipa);
            case 2 -> registaDefesa(equipa);
            case 3 -> registaGuardaRedes(equipa);
            case 4 -> registaMedio(equipa);
            case 5 -> registaLateral(equipa);
        }
    }

    public void registaEquipa () throws Exception {
        System.out.println("Insere o nome da equipa: ");
        String nome;
        nome = scanner.nextLine();
        try{
            while (this.equipas.containsKey(nome)) {
                System.out.println("Essa equipa não é valida, tenta novamente: ");
                nome = scanner.nextLine();
            }
        }
        catch(NullPointerException e) {
            throw new EquipaOuJogInvalidoException(e.toString());
        }
        Equipa nova = new Equipa(nome);
        System.out.println("Pretendes inserir Jogadores? \n1) Sim\n2) Não");
        int numero;
        try{
            numero = scanner.nextInt();
            scanner.nextLine();
        }
        catch(InputMismatchException e) {
            throw new EquipaOuJogInvalidoException(e.toString());
        }
        while (numero == 1) {
            registaJogador(nova);
            System.out.println("Pretendes inserir mais jogadores?\n1) Sim\n2) Não");
            try{
                numero = scanner.nextInt();
                scanner.nextLine();
            }
            catch(InputMismatchException e) {
                throw new EquipaOuJogInvalidoException(e.toString());
            }
        }
        this.equipas.put(nova.getNome(), nova);
    }

    public void simulaçãoSetup() throws Exception {
        String casa = "";
        String vis = "";
        System.out.println("Nome da equipa da casa: ");
        casa = scanner.nextLine();
        while(!this.equipas.containsKey(casa)) {
            System.out.println("Equipa inválida. tenta novamente:");
            casa = scanner.nextLine();
        }
        Equipa casaE = this.equipas.get(casa);

        System.out.println("Nome da equipa Visitante: ");
        vis = scanner.nextLine();
        while(!this.equipas.containsKey(vis)) {
            System.out.println("Equipa inválida. tenta novamente:");
            vis = scanner.nextLine();
        }
        Equipa visE = this.equipas.get(vis);
        Simulacao s = new Simulacao(casaE, visE);
        Jogo j = s.run();
        this.jogos.add(j);
    }

    public void storeData () throws IOException, FileNotFoundException {
        String outputName;
        System.out.println("Insere o nome do ficheiro de output: ");
        outputName = scanner.nextLine();
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(outputName));
        os.writeObject(this);
        os.close();
    }

    public StoredData clone () {return new StoredData(this);}

}
