package ProjetoPOO;

import java.util.Map;
import java.util.Scanner;

public class execOnData {
    private static final Scanner scanner = new Scanner(System.in);
    public static String showJogadores(StoredData d) {
        StringBuilder strB = new StringBuilder("Jogadores: {\n");
        for (Equipa j : d.getEquipas().values()) {
            for (Jogador jog : j.getJogadores().values()) {
                strB.append("\t").append(jog.getNome()).append(" (").append(jog.getNumeroCamisola()).append(")").append("\n");
            }
        }
        strB.append("}\n");
        return strB.toString();
    }

    public static String showJogadoresEquipa (StoredData d, String nome) {
        StringBuilder strB = new StringBuilder("Jogadores: {\n");
        for (Jogador j : d.getEquipas().get(nome).getJogadores().values()) {
            strB.append("\t").append(j.getNome()).append(" (").append(j.getNumeroCamisola()).append(")").append("\n");
        }
        strB.append("}\n");
        return strB.toString();
    }

    public static void trocaJogador (StoredData d) {
        StringBuilder sb = new StringBuilder();
        System.out.print("A que equipa pertence o jogador que quer trocar? ");
        String antiga = scanner.nextLine();
        Equipa equipaAntiga = d.getEquipas().get(antiga);
        System.out.print("Para que equipa deseja trocar o jogador? ");
        String nova = scanner.nextLine();
        System.out.println("Eis a lista de jogadores pertencentes à equipa " + antiga + ": ");
        System.out.println(execOnData.showJogadoresEquipa(d, antiga));
        System.out.print("Qual é o número do jogador que pretende trocar? ");
        int numero = scanner.nextInt();
        Map<String, Equipa> updatedEquipas = StoredData.trocaEquipas(antiga, nova, d, d.getEquipas().get(antiga).getJogadores().get(numero));
        d.setEquipas(updatedEquipas);
        System.out.println("Jogador trocado com sucesso.\n");
    }

    public static String showJogos (StoredData d) {
        StringBuilder strB = new StringBuilder("Jogos: {\n");
        for (Jogo j : d.getJogos())
            strB.append("\t").append(j.getEquipaCasa()).append(" vs ").append(j.getEquipaFora()).append("\n");
        strB.append("}\n");
        return strB.toString();
    }

    public static String showEquipas (StoredData d) {
        StringBuilder strB = new StringBuilder("Equipas: {\n");
        for (String s : d.getEquipas().keySet())
            strB.append("\t").append(s).append("\n");
        strB.append("}\n");
        return strB.toString();
    }
}