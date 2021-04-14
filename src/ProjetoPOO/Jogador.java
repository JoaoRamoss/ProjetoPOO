package ProjetoPOO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jogador {

    //Variáveis de Instância.
    private String ID;
    private int velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse, elast;
    private String nomeEquipa, nome;
    private List<String> equipasAnteriores;
    private boolean principal;

    /**
     * Tipos diferentes de jog
     * ador possiveis.
     */
    enum Tipojogador {
        GuardaRedes,
        Defesa,
        Medio,
        Avancado,
        Lateral
    }
    private Tipojogador posicao;

    //Variável de classe.
    private static final Random random = new Random();

    /**
     * Construtor de jogador.
     */
    public Jogador () {
        this(Tipojogador.values()[random.nextInt(5)]);
    }

    //Construtor parametrizado
    public Jogador (Tipojogador j) {
        this.posicao = j;
        //Cada jogador tem caracteristicas diferentes, e, portanto, uma formula diferente.
        //Os limites a que o random pode chegar é alterado consoante os pontos fracos e fortes de cada jogador.
        if (j == Tipojogador.GuardaRedes) {
            this.velocidade = random.nextInt(50-25) + 25;
            this.resistencia = random.nextInt(60-45) + 45;
            this.destreza = random.nextInt(90-60) + 60;
            this.impulsao = random.nextInt(90-75) + 75;
            this.jogoCabeca = random.nextInt(30-15) + 15;
            this.remate = random.nextInt(30-20) + 20;
            this.capPasse = random.nextInt(95-80) + 80;
            this.elast = random.nextInt(99-75) + 75;
        }
        else if (j == Tipojogador.Avancado) {
            this.velocidade = random.nextInt(99-75) + 75;
            this.resistencia = random.nextInt(85-70) + 70;
            this.destreza = random.nextInt(90-65) + 65;
            this.impulsao = random.nextInt(99-70) + 70;
            this.jogoCabeca = random.nextInt(99-80) + 80;
            this.remate = random.nextInt(99-85) + 85;
            this.capPasse = random.nextInt(80-55) + 55;
        }
        else if (j == Tipojogador.Defesa) {
            this.velocidade = random.nextInt(75-50) + 50;
            this.resistencia = random.nextInt(90-75) + 75;
            this.destreza = random.nextInt(80-65) + 65;
            this.impulsao = random.nextInt(85-65) + 65;
            this.jogoCabeca = random.nextInt(95-80) + 80;
            this.remate = random.nextInt(70-45) + 45;
            this.capPasse = random.nextInt(80-65) + 65;
        }
        else if (j == Tipojogador.Medio) {
            this.velocidade = random.nextInt(75-50) + 50;
            this.resistencia = random.nextInt(90-75) + 75;
            this.destreza = random.nextInt(85-70) + 70;
            this.impulsao = random.nextInt(65-30) + 30;
            this.jogoCabeca = random.nextInt(55-35) + 35;
            this.remate = random.nextInt(90-70) + 70;
            this.capPasse = random.nextInt(99-80) + 80;
        }
        else if (j == Tipojogador.Lateral) {
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
        this.principal = random.nextBoolean();
    }

    //Construtor parametrizado.
    public Jogador (String ID, Tipojogador j, int velocidade, int resistencia, int destreza, int impulsao, int jogoCabeca, int remate, int capPasse, int elast, StringBuffer nome, StringBuffer equipa, boolean principal) {
        this.ID = ID;
        this.posicao = j;
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.impulsao = impulsao;
        this.jogoCabeca = jogoCabeca;
        this.remate = remate;
        this.capPasse = capPasse;
        if (j == Tipojogador.GuardaRedes)
            this.elast = elast;
        else
            this.elast = 0;
        this.nome = new String(nome);
        this.nomeEquipa = new String(equipa);
        this.equipasAnteriores = new ArrayList<>();
        this.principal = principal;
    }

    //Construtor parametrizado.
    public Jogador (String ID, Tipojogador j, int velocidade, int resistencia, int destreza, int impulsao, int jogoCabeca, int remate, int capPasse, int elast,
                    StringBuffer nome, StringBuffer equipa, List<String> anteriores, boolean principal) {
        this.ID = ID;
        this.posicao = j;
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.impulsao = impulsao;
        this.jogoCabeca = jogoCabeca;
        this.remate = remate;
        this.capPasse = capPasse;
        if (j == Tipojogador.GuardaRedes)
            this.elast = elast;
        else
            this.elast = 0;
        this.nome = new String(nome);
        this.nomeEquipa = new String(equipa);
        this.equipasAnteriores = new ArrayList<>();
        this.equipasAnteriores.addAll(anteriores);
        this.principal = principal;
    }

    //Construtor que recebe objeto.
    public Jogador (Jogador j) {
        this.ID = j.ID;
        this.posicao = j.posicao;
        this.velocidade = j.velocidade;
        this.resistencia = j.resistencia;
        this.destreza = j.destreza;
        this.impulsao = j.impulsao;
        this.jogoCabeca = j.jogoCabeca;
        this.remate = j.remate;
        this.capPasse = j.capPasse;
        if (j.posicao == Tipojogador.GuardaRedes)
            this.elast = j.elast;
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
     * Obtem posição do jogador.
     * @return Posição do jogador.
     */
    public Tipojogador getPosicao () {return this.posicao;}

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
     * Obtem a elasticidade do jogador.
     * @return  A elasticidade do jogador.
     */
    public int getElast () {
        return this.elast;
    }

    /**
     * Set na elasticidade do jogador (Só funciona se for um guarda-redes).
     * @param elast Valor a atribuir à elasticidade do jogador.
     */
    public void setElast (int elast) {if (this.posicao == Tipojogador.GuardaRedes) this.elast = elast;}

    /**
     * Obtem o nome da equipa do jogador.
     * @return O nome da equipa onde o jogador está de momento.
     */
    public String getNomeEquipa () {
        return this.nomeEquipa;
    }

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

    public void setPrincipal (boolean bool) {this.principal = bool;}

    public boolean isPrincipal () {return this.principal;}

    public String getID () {return this.ID;}

    /**
     * Obtem um score geral dependendo da posição em que o jogador joga.
     * @return Valor do score em "overall".
     */
    public double overall () {
        double res = switch (this.posicao) {
            case GuardaRedes -> this.velocidade * 0.05 + this.resistencia * 0.15 + this.destreza * 0.25 + this.impulsao * 0.15 + this.jogoCabeca * 0.05 + this.remate * 0.05 + this.capPasse * 0.15 + this.elast * 0.15;
            case Medio -> this.velocidade * 0.10 + this.resistencia * 0.15 + this.destreza * 0.15 + this.impulsao * 0.05 + this.jogoCabeca * 0.05 + this.remate * 0.25 + this.capPasse * 0.25;
            case Defesa -> this.velocidade * 0.10 + this.resistencia * 0.25 + this.destreza * 0.10 + this.impulsao * 0.15 + this.jogoCabeca * 0.25 + this.remate * 0.05 + this.capPasse * 0.10;
            case Lateral -> this.velocidade * 0.25 + this.resistencia * 0.15 + this.destreza * 0.15 + this.impulsao * 0.05 + this.jogoCabeca * 0.05 + this.remate * 0.15 + this.capPasse * 0.20;
            case Avancado -> this.velocidade * 0.25 + this.resistencia * 0.10 + this.destreza * 0.10 + this.impulsao * 0.15 + this.jogoCabeca * 0.15 + this.remate * 0.20 + this.capPasse * 0.05;
        };
        return res;
    }

    /**
     * Coloca as informações todas sobre o objeto em uma String.
     * @return String com as informações do jogador.
     */
    public String toString () {
        Jogador j = this.clone();
        StringBuilder sb = new StringBuilder("Jogador: ");
        sb.append(j.nome).append("\n");
        sb.append("Equipa: ").append(j.nomeEquipa).append("\n");
        if(j.equipasAnteriores.isEmpty())
            sb.append("Equipas anteriores: Sem historial noutras equipas").append("\n");
        else
            sb.append("Equipas anteriores: ").append(j.equipasAnteriores.toString()).append("\n");
        sb.append("Posição: ").append(j.posicao.toString());
        sb.append("\nResistência: ").append(j.resistencia);
        sb.append("\nVelocidade: ").append(j.velocidade);
        sb.append("\nDestreza: ").append(j.destreza);
        sb.append("\nImpulsao: ").append(j.impulsao);
        sb.append("\nJogo de cabeça: ").append(j.jogoCabeca);
        sb.append("\nRemate: ").append(j.remate);
        sb.append("\nCapacidade de passe: ").append(j.capPasse);
        if (j.posicao == Tipojogador.GuardaRedes)
            sb.append("\nElasticidade: ").append(j.elast);
        sb.append(String.format("\nOverall: %.2f", j.overall())).append("\n\n");
        return sb.toString();
    }
    /**
     * Função para testar se um jogador é igual a outro.
     * @param o Objeto fornecido à função.
     * @return true no caso de serem iguais, false no caso de não serem.
     */
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Jogador j = (Jogador) o;
        return (this.posicao == j.posicao && this.velocidade == j.velocidade && this.resistencia == j.resistencia
                && this.destreza == j.destreza && this.impulsao == j.impulsao && this.jogoCabeca == j.jogoCabeca &&
                this.capPasse == j.capPasse && this.elast == j.elast && this.nomeEquipa.toString().equals(j.nomeEquipa.toString()) &&
                this.nome.toString().equals(j.nome.toString()));
    }

    /**
     * Funçção para clonar o objeto.
     * @return Um clone do jogador.
     */
    public Jogador clone () {
        return new Jogador (this);
    }
}
