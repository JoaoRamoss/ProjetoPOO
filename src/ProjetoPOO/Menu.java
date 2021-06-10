package ProjetoPOO;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu extends Exception{
    private static final Scanner scanner = new Scanner(System.in);

    public static void menuPrincipal() throws LinhaIncorretaException, EquipaOuJogInvalidoException {
        StoredData d = new StoredData();
        d = Parser.parse();
        StringBuilder sb = new StringBuilder("Menu principal: \n");
            sb.append("1: Consultar Equipas.\n");
            sb.append("2: Lista das Equipas.\n");
            sb.append("3: Lista de Jogadores. \n");
            sb.append("4: Lista de Jogos.\n");
            sb.append("5: Troca um jogador de equipa.\n");
            sb.append("6: Consultar Jogador.\n");
            sb.append("7: Obter resultado de um jogo.\n");
            sb.append("0: Sair.\n");
            sb.append("Escreve a opcao: ");
        while (true) {
            String res = sb.toString();
            System.out.print(res);
            int scan;
            try {
                scan = scanner.nextInt();
            }
            catch(InputMismatchException e) {
                System.out.println("Essa opção não é válida.");
                scan = -1;
            }
            scanner.nextLine();
            switch (scan) {
                case 1 -> {
                    boolean inputGood = false;
                    String resposta = "";
                    System.out.print("Que equipa pretendes consultar? ");
                    try {
                        resposta = scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        try {
                            System.out.println(d.getEquipas().get(resposta).toString());
                        } catch (NullPointerException e) {
                            System.out.println("A equipa \"" + resposta + "\" não existe.\n");
                        }
                        finally {
                            inputGood = true;
                        }
                    }
                }
                case 2 -> System.out.println(d.showEquipas());
                case 3 -> System.out.println(d.showJogadores());
                case 4 -> System.out.println(d.showJogos());
                case 5 -> d.trocaJogador();
                case 6 -> System.out.println(d.consultarJogador());
                case 7 -> System.out.println(d.resultadoJogo());
                case 0 -> System.exit(0);
            }
        }
    }
}
