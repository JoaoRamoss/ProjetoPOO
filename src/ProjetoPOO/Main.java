package ProjetoPOO;

public class Main {

    public static void main(String[] args) {
	Jogador j = new Jogador(Jogador.Tipojogador.Avancado);
	j.setNome("Marega");
	j.setEquipa("C.S Maritimo");
	j.mudaEquipa("Vitoria S.C");
	j.mudaEquipa("F.C Porto");

	//NOTA: METER LIMITE MINIMO NO RANDOM PARA NAO APARECER JOGADORES COM PONTUAÇÕES DEMASIADO BAIXAS
	Jogador r = new Jogador(Jogador.Tipojogador.Defesa);
	r.setNome("Diogo Leite");
	r.setEquipa("F.C Porto");
	System.out.println(j.toString());
	System.out.println("\n\n");
	System.out.println(r.toString());
    }
}
