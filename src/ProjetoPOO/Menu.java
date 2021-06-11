package ProjetoPOO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu extends Exception {
    private static StoredData d = new StoredData();
    private static final Scanner scanner = new Scanner(System.in);

    public static StoredData loadData() throws IOException, ClassNotFoundException {
        String inputFile;
        System.out.println("Insere o nome do ficheiro de input: ");
        inputFile = scanner.nextLine();
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(inputFile));
            StoredData sd = (StoredData) is.readObject();
            is.close();
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

    public static void carregaFicheiro () throws LinhaIncorretaException, IOException, ClassNotFoundException {
        System.out.println("A partir de que ficheiro pretende carregar a informação?");
        System.out.println("1) logs.txt\n2) Outro");
        int res;
        try {
            res = scanner.nextInt();
            scanner.nextLine();
        }
        catch(InputMismatchException e) {
            throw new InputMismatchException("Argumento inserido não é compatível.");
        }
        while (res != 1 && res != 2) {
            System.out.println("Argumento inserido apenas pode ser \"1\" ou \"2\". Tenta novamente: ");
            try {
                res = scanner.nextInt();
                scanner.nextLine();
            }
            catch(InputMismatchException e) {
                throw new InputMismatchException("Argumento inserido não é compatível.");
            }
        }
        switch(res) {
            case 1 -> d = Parser.parse();
            case 2 -> d = loadData();
        }
        System.out.println("Informação carregada com sucesso!\n");
    }

    public static void menuJogos () {
        System.out.printf("1) Lista de Histórico de jogos.\n2) Consultar um jogo do histórico.\nEscreve aqui a tua opção: ");
        int jogo;
        try {
            jogo = scanner.nextInt();
            scanner.nextLine();
        }
        catch (InputMismatchException e) {
            throw new InputMismatchException("Argumento introduzido tem de ser um inteiro.");
        }
        while (jogo < 1 || jogo > 2) {
            System.out.println("Número introduzido está fora de alcance. Tenta novamente:");
            try {
                jogo = scanner.nextInt();
                scanner.nextLine();
            }
            catch (InputMismatchException e) {
                throw new InputMismatchException("Argumento introduzido tem de ser um inteiro.");
            }
        }
        switch(jogo) {
            case 1 -> System.out.println(d.showJogos());
            case 2 -> {
                String casa, fora;
                boolean found = false;
                System.out.println("Insere o nome da equipa da casa:");
                casa = scanner.nextLine();
                System.out.printf("Insere o nome da equipa visitante: ");
                fora = scanner.nextLine();
                for (Jogo j : d.getJogos()) {
                    if (j.getEquipaFora().equals(fora) && j.getEquipaCasa().equals(casa)) {
                        System.out.println(j.toString());
                        found = true;
                        break;
                    }
                }
                if (!found) System.out.println("Não ocorreu nenhum jogo entre essas duas equipas.\n");
            }
        }
    }
    public static void menuPrincipal() throws Exception {
        StringBuilder sb = new StringBuilder("Menu principal: \n");
            sb.append("1: Carregar informação a partir de ficheiro.\n");
            sb.append("2: Definições de equipas\n");
            sb.append("3: Lista de Jogadores. \n");
            sb.append("4: Jogos.\n");
            sb.append("5: Troca um jogador de equipa.\n");
            sb.append("6: Consultar Jogador.\n");
            sb.append("7: Guardar Informação em ficheiro.\n");
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
                case 1 -> carregaFicheiro();
                case 2 -> {
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
                case 3 -> System.out.println(d.showJogadores());
                case 4 -> menuJogos();
                case 5 -> d.trocaJogador();
                case 6 -> System.out.println(d.consultarJogador());
                case 7 -> d.storeData();
                case 0 -> System.exit(0);
            }
        }
    }
}
