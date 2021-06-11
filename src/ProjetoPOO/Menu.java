package ProjetoPOO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu extends Exception{
    private static final Scanner scanner = new Scanner(System.in);

    public static StoredData loadData() throws IOException, ClassNotFoundException {
        String inputFile;
        System.out.println("Insere o nome do ficheiro de input: ");
        inputFile = scanner.nextLine();
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(inputFile));
        StoredData sd = (StoredData) is.readObject();
        is.close();
        return sd;
    }

    public static void menuPrincipal() throws Exception {
        StoredData d = new StoredData();
        d = Parser.parse();
        StringBuilder sb = new StringBuilder("Menu principal: \n");
            sb.append("1: Registar uma equipa\n");
            sb.append("2: Consultar Equipas.\n");
            sb.append("3: Lista das Equipas.\n");
            sb.append("4: Lista de Jogadores. \n");
            sb.append("5: Lista de Jogos.\n");
            sb.append("6: Troca um jogador de equipa.\n");
            sb.append("7: Consultar Jogador.\n");
            sb.append("8: Obter resultado de um jogo.\n");
            sb.append("9: Guardar Informação em ficheiro.\n");
            sb.append("10: Ler informação de um ficheiro.\n");
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
                case 1 -> d.registaEquipa();
                case 2 -> {
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
                case 3 -> System.out.println(d.showEquipas());
                case 4 -> System.out.println(d.showJogadores());
                case 5 -> System.out.println(d.showJogos());
                case 6 -> d.trocaJogador();
                case 7 -> System.out.println(d.consultarJogador());
                case 8 -> System.out.println(d.resultadoJogo());
                case 9 -> d.storeData();
                case 10 -> d = loadData();
                case 0 -> System.exit(0);
            }
        }
    }
}
