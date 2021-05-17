package ProjetoPOO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Jogador {

    //Variáveis de Instância.
    private int velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse;
    private int numeroCamisola;
    private String nomeEquipa, nome;
    private List<String> equipasAnteriores;

    //Variável de classe.
    private static final Random random = new Random();

    /**
     * @brief construtor vazio de jogador.
     * Retorna um jogador cujas características são todas aleatórias.
     */
    public Jogador () {new Avancado();}

    //Construtor parametrizado
    public Jogador (String posicao) {

        //Cada jogador tem caracteristicas diferentes, e, portanto, uma formula diferente.
        //Os limites a que o random pode chegar é alterado consoante os pontos fracos e fortes de cada jogador.
        if (posicao.equals("GuardaRedes")) {
            this.velocidade = random.nextInt(50-25) + 25;
            this.resistencia = random.nextInt(60-45) + 45;
            this.destreza = random.nextInt(90-60) + 60;
            this.impulsao = random.nextInt(90-75) + 75;
            this.jogoCabeca = random.nextInt(30-15) + 15;
            this.remate = random.nextInt(30-20) + 20;
            this.capPasse = random.nextInt(95-80) + 80;
        }
        else if (posicao.equals("Avancado")) {
            this.velocidade = random.nextInt(99-75) + 75;
            this.resistencia = random.nextInt(85-70) + 70;
            this.destreza = random.nextInt(90-65) + 65;
            this.impulsao = random.nextInt(99-70) + 70;
            this.jogoCabeca = random.nextInt(99-80) + 80;
            this.remate = random.nextInt(99-85) + 85;
            this.capPasse = random.nextInt(80-55) + 55;
        }
        else if (posicao.equals("Defesa")) {
            this.velocidade = random.nextInt(75-50) + 50;
            this.resistencia = random.nextInt(90-75) + 75;
            this.destreza = random.nextInt(80-65) + 65;
            this.impulsao = random.nextInt(85-65) + 65;
            this.jogoCabeca = random.nextInt(95-80) + 80;
            this.remate = random.nextInt(70-45) + 45;
            this.capPasse = random.nextInt(80-65) + 65;
        }
        else if (posicao.equals("Medio")) {
            this.velocidade = random.nextInt(75-50) + 50;
            this.resistencia = random.nextInt(90-75) + 75;
            this.destreza = random.nextInt(85-70) + 70;
            this.impulsao = random.nextInt(65-30) + 30;
            this.jogoCabeca = random.nextInt(55-35) + 35;
            this.remate = random.nextInt(90-70) + 70;
            this.capPasse = random.nextInt(99-80) + 80;
        }
        else if (posicao.equals("Lateral")) {
            this.velocidade = random.nextInt(99-80) + 80;
            this.resistencia = random.nextInt(80-65) + 65;
            this.destreza = random.nextInt(70-55) + 55;
            this.impulsao = random.nextInt(65-45) + 45;
            this.jogoCabeca = random.nextInt(70-50) + 50;
            this.remate = random.nextInt(80-65) + 65;
            this.capPasse = random.nextInt(95-75) + 75;
        }
        this.nome = "";
        this.nomeEquipa = "";
        this.equipasAnteriores = new ArrayList<>();
    }

    //Construtor parametrizado.
    public Jogador (String nome, int numeroCamisola, int velocidade, int resistencia, int destreza, int impulsao, int jogoCabeca,
                    int remate, int capPasse) {
        this.numeroCamisola = numeroCamisola;
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.impulsao = impulsao;
        this.jogoCabeca = jogoCabeca;
        this.remate = remate;
        this.capPasse = capPasse;
        this.nomeEquipa = "";
        this.nome = nome;
        this.equipasAnteriores = new ArrayList<>();
    }

    public Jogador (String nome, int numeroCamisola, int velocidade, int resistencia, int destreza, int impulsao, int jogoCabeca,
                    int remate, int capPasse, List<String> anteriores) {
        this.numeroCamisola = numeroCamisola;
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.impulsao = impulsao;
        this.jogoCabeca = jogoCabeca;
        this.remate = remate;
        this.capPasse = capPasse;
        this.nome = nome;
        this.nomeEquipa = "";
        this.equipasAnteriores = new ArrayList<>();
        this.equipasAnteriores.addAll(anteriores);
    }


    //Construtor que recebe objeto.
    public Jogador (Jogador j) {
        this.numeroCamisola = j.numeroCamisola;
        this.velocidade = j.velocidade;
        this.resistencia = j.resistencia;
        this.destreza = j.destreza;
        this.impulsao = j.impulsao;
        this.jogoCabeca = j.jogoCabeca;
        this.remate = j.remate;
        this.capPasse = j.capPasse;
        this.nome = j.nome;
        this.nomeEquipa = j.nomeEquipa;
        this.equipasAnteriores = new ArrayList<>(j.equipasAnteriores.size());
        this.equipasAnteriores.addAll(j.equipasAnteriores);
    }


    /**
     * Muda a equipa do jogador.
     * @param equipaNova Nova equipa do jogador.
     */
    public void mudaEquipa (String equipaNova) {
        this.equipasAnteriores.add(this.nomeEquipa);
        this.nomeEquipa = equipaNova;
    }

    /**
     * Obtem a velocidade de um jogador.
     * @return A pontuação da velocidade atribuída ao jogador.
     */
    public int getVelocidade () { return this.velocidade;}


    /**
     * Set na velocidade do jogador.
     * @param vel Velocidade a ser atribuida ao jogador.
     */
    public void setVelocidade (int vel) {this.velocidade = vel;}

    /**
     * Obtem a pontuação da resistênia do jogador.
     * @return A pontuação da resistência do jogador.
     */
    public int getResistencia() { return this.resistencia;}

    /**
     * Set na resistência do jogador.
     * @param res Resistênia a ser atribuida ao jogador.
     */
    public void setResistencia (int res) {this.resistencia = res;}

    /**
     * Obtem a pontuação da destreza do jogador.
     * @return A pontuação da resistência do jogador.
     */
    public int getDestreza () {
        return this.destreza;
    }

    /**
     * Set na destreza do jogador.
     * @param des Valor da destreza a atribuir ao jogador.
     */
    public void setDestreza (int des) {this.destreza = des;}

    /**
     * Obtem a pontuação da impulsão do jogador.
     * @return A pontuação da impulsão do jogador.
     */
    public int getImpulsao () {return this.impulsao;}

    /**
     * Set na impulsão do jogador.
     * @param imp Valor a atribuir na impulsão do jogador.
     */
    public void setImpulsao (int imp) {this.impulsao = imp;}

    /**
     * Obtem a pontuação do jogo de cabeça do jogador.
     * @return A pontuação do jogo de cabeça do jogador.
     */
    public int getJogoCabeca () {return this.jogoCabeca; }

    /**
     * Set no jogo de cabeça do jogador.
     * @param jc Valor a atribuir ao jogo de cabeça do jogador.
     */
    public void setJogoCabeca (int jc) {this.jogoCabeca = jc;}

    /**
     * Obtem a pontuação do remate do jogador.
     * @return  A pontuação do remate do jogador.
     */
    public int getRemate () {
        return this.remate;
    }

    /**
     * Set no remate do jogador.
     * @param rem Valor a atribuir ao remate do jogador.
     */
    public void setRemate (int rem) {this.remate = rem;}

    /**
     * Obtem a capacidade de passe do jogador.
     * @return A pontuação da capacidade de passe do jogador.
     */
    public int getCapPasse () {
        return this.capPasse;
    }

    /**
     * Set na capacidade de passe do jogador.
     * @param capP Valor a atribuir à capacidade de passe do jogador.
     */
    public void setCapPasse (int capP) {this.capPasse = capP;}

    /**
     * Obtem o nome da equipa do jogador.
     * @return O nome da equipa onde o jogador está de momento.
     */
    public String getNomeEquipa () {
        return this.nomeEquipa;
    }

    public void setNomeEquipa (String nome) {this.nomeEquipa = nome;}

    public List<String> getEquipasAnteriores () {return this.equipasAnteriores;}

    /**
     * Dá set na quipa do jogador
     * @param equipa nova equipa onde o jogador vai ser colocado.
     */
    public void setEquipa (String equipa) {
        this.nomeEquipa = equipa;
    }

    /**
     * Obtem o nome do jogador.
     * @return O nome do jogador.
     */
    public String getNome () {
        return this.nome;
    }

    /**
     * Set no nome do jogador.
     * @param nome Nome do jogador.
     */
    public void setNome (String nome) {
        this.nome = nome;
    }


    /**
     *
     * @return
     */
    public int getNumeroCamisola() {
        return numeroCamisola;
    }

    public void setNumeroCamisola(int numeroCamisola) {
        this.numeroCamisola = numeroCamisola;
    }

    /**
     * @brief Função toString da classe Jogador.
     * @return String com a informação sobre o jogador.
     */
    public abstract double overall();

    public abstract String toString ();

    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Jogador gr = (Jogador) o;
        return (this.getNumeroCamisola() == gr.getNumeroCamisola() && this.getVelocidade() == gr.getVelocidade() && this.getResistencia() == gr.getResistencia()
                && this.getDestreza() == gr.getDestreza() && this.getImpulsao() == gr.getImpulsao() && this.getJogoCabeca() == gr.getJogoCabeca() &&
                this.getCapPasse() == gr.getCapPasse() && this.getNomeEquipa().equals(gr.getNomeEquipa()) &&
                this.getNome().equals(gr.getNome()));
    }

    public abstract Jogador clone ();
}
