package ProjetoPOO;

public class Main {

    public static void main(String[] args) {
		Equipa e = new Equipa("F.C Porto");
		Equipa b = new Equipa("Fafe");
		//System.out.println(e.getJogadores().get(0).toString());
		e.trocaEquipa(0, b);
		System.out.println(b.getJogadores().get(b.getJogadores().size()-1).toString());

    }
}
