package ProjetoPOO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jogador {

    //Variáveis de Instância.
    private int velocidade, resistencia, destreza, impulsao, jogoCabeca, remate, capPasse, elast;
    private String nomeEquipa, nome;
    private List<String> equipasAnteriores;
    private boolean principal;

    /**
     * Tipos diferentes de jogador possiveis.
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
        this(Tipojogador.values()[random.nextInt(5)], true);
    }

    //Construtor parametrizado
    public Jogador (Tipojogador j, boolean principal) {
        this.posicao = j;
        //Cada jogador tem caracteristicas diferentes, e, portanto, uma formula diferente.
        //Os limites a que o random pode chegar é alterado consoante os pontos fracos e fortes de cada jogador.
        if (j == Tipojogador.GuardaRedes) {
            this.velocidade = random.nextInt(50) + 1;
            this.resistencia = random.nextInt(60) + 1;
            this.destreza = random.nextInt(80) + 1;
            this.impulsao = random.nextInt(100) + 1;
            this.jogoCabeca = random.nextInt(30) + 1;
            this.remate = random.nextInt(30) + 1;
            this.capPasse = random.nextInt(100) + 1;
            this.elast = random.nextInt(100) + 1;
        } else if (j == Tipojogador.Avancado) {
            this.velocidade = random.nextInt(100) + 1;
            this.resistencia = random.nextInt(100) + 1;
            this.destreza = random.nextInt(100) + 1;
            this.impulsao = random.nextInt(100) + 1;
            this.jogoCabeca = random.nextInt(100) + 1;
            this.remate = random.nextInt(100) + 1;
            this.capPasse = random.nextInt(100) + 1;
        }
        else if (j == Tipojogador.Defesa) {
            this.velocidade = random.nextInt(70) + 1;
            this.resistencia = random.nextInt(70) + 1;
            this.destreza = random.nextInt(70) + 1;
            this.impulsao = random.nextInt(60) + 1;
            this.jogoCabeca = random.nextInt(70) + 1;
            this.remate = random.nextInt(70) + 1;
            this.capPasse = random.nextInt(90) + 1;
        }
        else if (j == Tipojogador.Medio) {
            this.velocidade = random.nextInt(50) + 1;
            this.resistencia = random.nextInt(65) + 1;
            this.destreza = random.nextInt(60) + 1;
            this.impulsao = random.nextInt(40) + 1;
            this.jogoCabeca = random.nextInt(60) + 1;
            this.remate = random.nextInt(60) + 1;
            this.capPasse = random.nextInt(100) + 1;
        }
        else if (j == Tipojogador.Lateral) {
            this.velocidade = random.nextInt(70) + 1;
            this.resistencia = random.nextInt(70) + 1;
            this.destreza = random.nextInt(70) + 1;
            this.impulsao = random.nextInt(65) + 1;
            this.jogoCabeca = random.nextInt(70) + 1;
            this.remate = random.nextInt(70) + 1;
            this.capPasse = random.nextInt(100) + 1;
        }
        this.nome = "";
        this.nomeEquipa = "";
        this.equipasAnteriores = new ArrayList<>();
        this.principal = principal;
    }

    //Construtor parametrizado.
    public Jogador (Tipojogador j, int velocidade, int resistencia, int destreza, int impulsao, int jogoCabeca, int remate, int capPasse, int elast, StringBuffer nome, StringBuffer equipa, boolean princ) {
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
        this.principal = princ;
    }

    //Construtor que recebe objeto.
    public Jogador (Jogador j) {
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
        this.principal = j.principal;
    }

    /**
     * Dá set na quipa do jogador
     * @param equipa nova equipa onde o jogador vai ser colocado.
     */
    public void setEquipa (String equipa) {
        this.nomeEquipa = equipa;
    }

    /**
     * Set no nome do jogador.
     * @param nome Nome do jogador.
     */
    public void setNome (String nome) {
        this.nome = nome;
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
     * Parametro que informa se o jogador é principal ou suolente.
     * @param principal Booleano que indica se é principal.
     */
    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public int getVelocidade () {
        return this.clone().velocidade;
    }

    public int getResistencia() {
        return this.clone().resistencia;
    }

    public int getDestreza () {
        return this.clone().destreza;
    }
    public int getImpulsao () {
        return this.clone().impulsao;
    }
    public int getJogoCabeca () {
        return this.clone().jogoCabeca;
    }

    public int getRemate () {
        return this.clone().remate;
    }

    public int getCapPasse () {
        return this.clone().capPasse;
    }

    public int getElast () {
        return this.clone().elast;
    }

    public String getNomeEquipa () {
        return this.clone().nomeEquipa;
    }

    public String getNome () {
        return this.clone().nome;
    }

    public boolean getPrincipal () {
        return this.clone().principal;
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
        sb.append("Equipas anteriores: ").append(j.equipasAnteriores.toString()).append("\n");
        sb.append("Posição: ").append(j.posicao.toString());
        sb.append("\nJogador principal: ").append(j.principal);
        sb.append("\nVelocidade: ").append(j.velocidade);
        sb.append("\nResistência: ").append(j.resistencia);
        sb.append("\nDestreza: ").append(j.destreza);
        sb.append("\nImpulsao: ").append(j.impulsao);
        sb.append("\nJogo de cabeça: ").append(j.jogoCabeca);
        sb.append("\nRemate: ").append(j.remate);
        sb.append("\nCapacidade de passe: ").append(j.capPasse);
        if (j.posicao == Tipojogador.GuardaRedes)
            sb.append("\nElasticidade: ").append(j.elast);
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
                this.nome.toString().equals(j.nome.toString()) && this.principal == j.principal);
    }

    /**
     * Funçção para clonar o objeto.
     * @return Um clone do jogador.
     */
    public Jogador clone () {
        return new Jogador (this);
    }
}
