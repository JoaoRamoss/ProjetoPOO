package ProjetoPOO;

public class Main {

    public static void main(String[] args) {
	Jogador j = new Jogador(Jogador.Tipojogador.GuardaRedes, true);
	j.setNome("Júlio");
	j.setEquipa("Belenenses");
	j.mudaEquipa("Benfica");
	j.mudaEquipa("Porto");

	//NOTA: METER LIMITE MINIMO NO RANDOM PARA NAO APARECER JOGADORES COM PONTUAÇÕES DEMASIADO BAIXAS
	Jogador r = new Jogador(Jogador.Tipojogador.Avancado, true);
	r.setNome("Ricardo");
	r.setEquipa("Porto");
	System.out.println(j.toString());
	System.out.println("\n\n");
	System.out.println(r.toString());
    }
}
