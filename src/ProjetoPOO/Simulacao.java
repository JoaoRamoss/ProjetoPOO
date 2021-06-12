package ProjetoPOO;

import java.time.LocalDate;
import java.util.*;

public class Simulacao {
    private Equipa equipaCasa, equipaFora;
    private static List<Integer> principaisCasa = new ArrayList<>(), principaisFora = new ArrayList<>();
    private static Map<Integer, Integer> subsCasa = new HashMap<>(), subsFora = new HashMap<>();
    public static Scanner scanner = new Scanner(System.in);

    public Simulacao () {
        this.equipaCasa = new Equipa();
        this.equipaFora = new Equipa();
    }

    public Simulacao (Equipa casa, Equipa fora) {
        this.equipaCasa = casa.clone();
        this.equipaFora = fora.clone();
    }

    public Simulacao (Simulacao s) {
        this.equipaCasa = s.getEquipaCasa().clone();
        this.equipaFora = s.getEquipaFora().clone();
    }

    public Equipa getEquipaCasa() {
        return this.equipaCasa.clone();
    }

    public Equipa getEquipaFora() {
        return this.equipaFora.clone();
    }

    private List<Integer> colocaPrincipaisCasa(int tatica) throws Exception {
        List<Integer> res = new ArrayList<>();
        switch (tatica) {
            case 1 -> {
                try {
                    res.add(this.equipaCasa.getTop("GuardaRedes").get(0).getNumeroCamisola());
                    List<Jogador> topDef = this.equipaCasa.getTop("Defesa");
                    for (int i = 0; i < 4; i++) {
                        res.add(topDef.get(i).getNumeroCamisola());
                    }
                    List<Jogador> topMed = this.equipaCasa.getTop("Medio");
                    for (int i = 0; i < 4; i++) {
                        res.add(topMed.get(i).getNumeroCamisola());
                    }
                    List<Jogador> topAv = this.equipaCasa.getTop("Avancado");
                    for (int i = 0; i < 2; i++) {
                        res.add(topMed.get(i).getNumeroCamisola());
                    }
                } catch (IndexOutOfBoundsException e) {
                    throw new IndexOutOfBoundsException("Não há jogadores suficientes na equipa " + this.equipaCasa.getNome() + " para usar a tática 1-4-4-2.");
                }
            }
            case 2 -> {
                try {
                    res.add(this.equipaCasa.getTop("GuardaRedes").get(0).getNumeroCamisola());
                    List<Jogador> topDef = this.equipaCasa.getTop("Defesa");
                    for (int i = 0; i < 4; i++) {
                        res.add(topDef.get(i).getNumeroCamisola());
                    }
                    List<Jogador> topMed = this.equipaCasa.getTop("Medio");
                    for (int i = 0; i < 3; i++) {
                        res.add(topMed.get(i).getNumeroCamisola());
                    }
                    List<Jogador> topAv = this.equipaCasa.getTop("Avancado");
                    for (int i = 0; i < 3; i++) {
                        res.add(topMed.get(i).getNumeroCamisola());
                    }
                } catch (IndexOutOfBoundsException e) {
                    throw new IndexOutOfBoundsException("Não há jogadores suficientes na equipa " + this.equipaCasa.getNome() + " para usar a tática 1-4-3-3.");
                }
            }
        }
        return res;
    }
    private List<Integer> colocaPrincipaisFora (int tatica) throws Exception {
        List<Integer> res = new ArrayList<>();
        switch (tatica) {
            case 1 -> {
                try {
                    res.add(this.equipaFora.getTop("GuardaRedes").get(0).getNumeroCamisola());
                    List<Jogador> topDef = this.equipaFora.getTop("Defesa");
                    for (int i = 0; i < 4; i++) {
                        res.add(topDef.get(i).getNumeroCamisola());
                    }
                    List<Jogador> topMed = this.equipaFora.getTop("Medio");
                    for (int i = 0; i < 4; i++) {
                        res.add(topMed.get(i).getNumeroCamisola());
                    }
                    List<Jogador> topAv = this.equipaFora.getTop("Avancado");
                    for (int i = 0; i < 2; i++) {
                        res.add(topMed.get(i).getNumeroCamisola());
                    }
                } catch (IndexOutOfBoundsException e) {
                    throw new IndexOutOfBoundsException("Não há jogadores suficientes na equipa " + this.equipaFora.getNome() + " para usar a tática 1-4-4-2.");
                }
            }
            case 2 -> {
                try {
                    res.add(this.equipaFora.getTop("GuardaRedes").get(0).getNumeroCamisola());
                    List<Jogador> topDef = this.equipaFora.getTop("Defesa");
                    for (int i = 0; i < 4; i++) {
                        res.add(topDef.get(i).getNumeroCamisola());
                    }
                    List<Jogador> topMed = this.equipaFora.getTop("Medio");
                    for (int i = 0; i < 3; i++) {
                        res.add(topMed.get(i).getNumeroCamisola());
                    }
                    List<Jogador> topAv = this.equipaFora.getTop("Avancado");
                    for (int i = 0; i < 3; i++) {
                        res.add(topMed.get(i).getNumeroCamisola());
                    }
                } catch (IndexOutOfBoundsException e) {
                    throw new IndexOutOfBoundsException("Não há jogadores suficientes na equipa " + this.equipaFora.getNome() + " para usar a tática 1-4-3-3.");
                }
            }
        }
        return res;
    }

    private double overallEmCampo (Equipa e, List<Integer> emCampo) {
        Equipa aux = new Equipa("Auxiliar");
        for (int i : emCampo) {
            aux.insereJogador(e.getJogadores().get(i));
        }
        return aux.overall();
    }

    private double luck () {return Math.random();}

    private boolean passaDefesa (Equipa casa, Equipa fora, boolean casaAtaca) {
        double overallCasa = overallEmCampo(casa, principaisCasa);
        double overallFora = overallEmCampo(fora, principaisFora);
        double probSucessoCasa = overallCasa;
        double probSucessoFora = overallFora;
        if (overallCasa >= overallFora) {
            probSucessoCasa += 2;
        }
        else probSucessoFora += 2;

        probSucessoCasa *= luck();
        probSucessoFora *= luck();

        if (casaAtaca && probSucessoCasa > probSucessoFora) return true;
        else if (!casaAtaca && probSucessoCasa < probSucessoFora) return true;
        else return false;
    }

    private boolean consegueMarcar (Equipa casa, Equipa fora, boolean casaAtaca) {
        Random random = new Random();
        double overallCasa = overallEmCampo(casa, principaisCasa);
        double overallFora = overallEmCampo(fora, principaisFora);
        double probSucessoCasa = overallCasa;
        double probSucessoFora = overallFora;
        if (casaAtaca) {
            Jogador avancado = new Avancado();
            for (int i : principaisCasa) {
                if (this.equipaCasa.getJogadores().get(i).getPosicao().equals("Avancado")) {
                    avancado = this.equipaCasa.getJogadores().get(i).clone();
                }
            }
            List<Jogador> redes = new ArrayList<>();
            Jogador guardaR = new GuardaRedes();
            for (int i : principaisFora) {
                if (this.equipaFora.getJogadores().get(i).getPosicao().equals("GuardaRedes")) {
                    guardaR = this.equipaFora.getJogadores().get(i);
                    break;
                }
            }
            if (guardaR.overall() * luck() > avancado.overall() * luck())
                return false;
            else return true;
        }
        else {
            Jogador avancado = new Avancado();
            for (int i : principaisFora) {
                if (this.equipaFora.getJogadores().get(i).getPosicao().equals("Avancado")) {
                    avancado = this.equipaFora.getJogadores().get(i).clone();
                }
            }

            Jogador guardaR = new GuardaRedes();
            for (int i : principaisCasa) {
                if (this.equipaCasa.getJogadores().get(i).getPosicao().equals("GuardaRedes")) {
                    guardaR = this.equipaCasa.getJogadores().get(i);
                    break;
                }
            }
            if (guardaR.overall() * luck() > avancado.overall() * luck())
                return false;
            else return true;
        }
    }

    public void substituicaoCasa (List<Integer> emCampo, String posicao) throws Exception {
        List<Jogador> subs = this.equipaCasa.getTop(posicao);
        boolean sub = false;
        for (int i = 0; i < principaisCasa.size() && !sub; i++) {
           for (int j = 0; j < subs.size() && !sub; j++) {
               if (principaisCasa.get(i) != subs.get(j).getNumeroCamisola()) {
                   subsCasa.put(principaisCasa.get(i), subs.get(j).getNumeroCamisola());
                   principaisCasa.set(i, subs.get(j).getNumeroCamisola());
                   sub = true;
               }
           }
        }

    }

    public void substituicaoFora (List<Integer> emCampo, String posicao) throws Exception {
        List<Jogador> subs = this.equipaFora.getTop(posicao);
        boolean sub = false;
        for (int i = 0; i < principaisFora.size() && !sub; i++) {
            for (int j = 0; j < subs.size() && !sub; j++) {
                if (principaisFora.get(i) != subs.get(j).getNumeroCamisola()) {
                    subsFora.put(principaisFora.get(i), subs.get(j).getNumeroCamisola());
                    principaisFora.set(i, subs.get(j).getNumeroCamisola());
                    sub = true;
                }
            }
        }
    }

    public Jogo run () throws Exception {
        Random random = new Random();
        int golosCasa = 0, golosFora = 0;
        int minutos = 0;
        boolean bolaCasa = true;
        System.out.println("Que tática é que a equipa " + this.equipaCasa.getNome() + " vai usar?\n1) 1-4-4-2\n2) 1-4-3-3\nInsere a resposta aqui: ");
        int taticaCasa;
        try {
            taticaCasa = scanner.nextInt();
            scanner.nextLine();
        }
        catch(InputMismatchException e) {
            throw new InputMismatchException("Argumento inserido tem de ser um número inteiro.");
        }
        System.out.println("Que tática é que a equipa " + this.equipaFora.getNome() + " vai usar?\n1) 1-4-4-2\n2) 1-4-3-3\nInsere a resposta aqui: ");
        int taticaFora;
        try {
            taticaFora = scanner.nextInt();
            scanner.nextLine();
        }
        catch(InputMismatchException e) {
            throw new InputMismatchException("Argumento inserido tem de ser um número inteiro.");
        }
        principaisCasa = colocaPrincipaisCasa(taticaCasa);
        principaisFora = colocaPrincipaisFora(taticaFora);
        StringBuilder sb = new StringBuilder("Inicio do jogo: minuto 0\n\tScore: ");
        sb.append("Casa ").append(golosCasa).append(" - ").append(golosFora).append(" Visitante\n");
        System.out.println(sb.toString());

        Thread.sleep(1000);
        minutos += 15;
        while (minutos < 90) {
            if (minutos == 45) {
                substituicaoCasa(principaisCasa, "Avancado");
                substituicaoFora(principaisFora, "Avancado");
            }
            if (minutos == 60) {
                substituicaoCasa(principaisCasa, "Medio");
                substituicaoFora(principaisFora, "Medio");
            }
            if (minutos == 75) {
                substituicaoFora(principaisFora, "Lateral");
                substituicaoCasa(principaisCasa, "Lateral");
            }
            System.out.println("\nMinuto: " + minutos);
            System.out.println("Score: \nCasa " + golosCasa + " - " + golosFora + " Visitante\n");
            if (bolaCasa) {
                System.out.println("A Equipa da casa está com posse de bola, é a sua vez de atacar.");
                if (passaDefesa(this.equipaCasa, this.equipaFora, true)) {
                    System.out.println("A equipa da casa conseguiu passar pela defesa da equipa visitante. Vai agora tentar marcar golo\n");

                    Thread.sleep(1000);
                    if (consegueMarcar(this.equipaCasa, this.equipaFora, true)) {
                        golosCasa++;
                        System.out.println("Golo da equipa da casa! A pontuação é agora:");
                        System.out.println("Casa " + golosCasa + " - " + golosFora + " Visitante\n");
                    } else {
                        System.out.println("Não conseguiu marcar. Perde a posse de bola.\n");
                    }
                }
                else {
                    System.out.println("Não passou pela defesa. Perde a posse de bola.\n");
                }
                bolaCasa = false;
                Thread.sleep(1000);
            }
            else {
                System.out.println("A Equipa visitante está com posse de bola, é a sua vez de atacar.");
                if (passaDefesa(this.equipaCasa, this.equipaFora, false)) {
                    System.out.println("A equipa visitante conseguiu passar pela defesa da equipa visitante. Vai agora tentar marcar golo\n");
                    Thread.sleep(1000);
                    if (consegueMarcar(this.equipaCasa, this.equipaFora, false)) {
                        golosCasa++;
                        System.out.println("Golo da equipa da casa! A pontuação é agora:");
                        System.out.println("Casa " + golosCasa + " - " + golosFora + " Visitante\n");
                    }
                    else {
                        System.out.println("Não conseguiu marcar. Perde a posse de bola.\n");
                    }
                }
                else {
                    System.out.printf("Não passou pela defesa. Perde a posse de bola.\n");
                }
                bolaCasa = true;
                Thread.sleep(1000);
            }
            Thread.sleep(2000);
            minutos += 15;
            if (minutos == 45) {
                System.out.println("\nMinuto 45: Intervalo.");
                System.out.println("Pontuação: ");
                System.out.println("Casa " + golosCasa + " - " + golosFora + " Visitante\n");
                Thread.sleep(2000);
            }
        }
        System.out.println("Jogo acabou. Resultado: ");
        System.out.println("Casa " + golosCasa + " - " + golosFora + " Visitante\n");
        return new Jogo(this.equipaCasa.getNome(), this.equipaFora.getNome(), golosCasa, golosFora, LocalDate.now(), principaisCasa, subsCasa, principaisFora, subsFora);
    }

    public Simulacao clone() {return new Simulacao(this);}
}

