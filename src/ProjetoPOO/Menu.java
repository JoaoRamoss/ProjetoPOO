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
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(inputFile));
            StoredData sd = (StoredData) is.readObject();
            is.close();
            System.out.println("Dados do ficheiro carregados com sucesso!\n");
            return sd;
        }
        catch(FileNotFoundException e) {
            throw new FileNotFoundException("Não existe nenhum ficheiro chamado \"" + inputFile + "\"!");
        }
    }

    private static void consultaEquipa(StoredData d) {
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
        }
    }

    public static void menuPrincipal() throws Exception {
        StoredData d = new StoredData();
        d = Parser.parse();
        StringBuilder sb = new StringBuilder("Menu principal: \n");
            sb.append("1: Definições de equipas\n");
            sb.append("2: Lista de Jogadores. \n");
            sb.append("3: Lista de Jogos.\n");
            sb.append("4: Troca um jogador de equipa.\n");
            sb.append("5: Consultar Jogador.\n");
            sb.append("6: Obter resultado de um jogo.\n");
            sb.append("7: Guardar Informação em ficheiro.\n");
            sb.append("8: Ler informação de um ficheiro.\n");
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
                    System.out.println("1) Consulta Equipa\n2) Lista de Equipas\n3) Registar uma Equipa\n");
                    int ans = 0;
                    boolean pass = false;
                    while (!pass) {
                        try {
                            pass = true;
                            ans = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            pass = false;
                            System.out.println("Input tem de ser um número inteiro!\n");
                        }
                    }
                    switch(ans) {
                        case 1 -> consultaEquipa(d);
                        case 2 -> System.out.println(d.showEquipas());
                        case 3 -> d.registaEquipa();
                    }
                }
                case 2 -> System.out.println(d.showJogadores());
                case 3 -> System.out.println(d.showJogos());
                case 4 -> d.trocaJogador();
                case 5 -> System.out.println(d.consultarJogador());
                case 6 -> System.out.println(d.resultadoJogo());
                case 7 -> d.storeData();
                case 8 -> d = loadData();
                case 0 -> System.exit(0);
            }
        }
    }
}
