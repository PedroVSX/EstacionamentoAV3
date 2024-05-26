import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        System.out.print("\u001B[32mAntes de começar, por favor digite as dimensões do estacionamento.\n" +
                "Fileiras: ");
        int fil = Integer.parseInt(input.readLine());
        System.out.print("\u001B[32mColunas: ");
        int col = Integer.parseInt(input.readLine());

        Estacionamento estacionamento = new Estacionamento(fil, col);
        System.out.println();

        while (true) {
            System.out.print("""
                    \u001B[1m\u001B[32mBem-vindo(a) ao Estacionamento do Sudeste!\u001B[0m
                    \u001B[32mO que você deseja fazer?\u001B[0m
                    \u001B[36m[1] - Adicionar veículo
                    [2] - Remover veículo
                    [3] - Visualizar estacionamento
                    [4] - Verificar vaga
                    [0] - Fechar\u001B[0m
                    \u001B[32mSua escolha: \u001B[0m""");

            byte escolha = Byte.parseByte(input.readLine());
            System.out.println();

            if (escolha == 0) {
                System.out.println("\u001B[1m\u001B[32m\nAté a próxima!\u001B[0m");
                break;

            } else if (escolha == 1) {

                if (estacionamento.checarSeEstaCheio()) {
                    System.out.println("\u001B[1m\u001B[31mEstacionamento cheio!\u001B[0m\n");

                } else {
                    // Adicionar veículo
                    System.out.println("\u001B[1m\u001B[32mVamos adicionar um veículo!\u001B[0m\n");
                    estacionamento.mostrarEstacionamento();
                    System.out.println("\u001B[36mDigite as seguintes informações sobre o veículo: \u001B[0m\n");

                    // Cria o veículo
                    System.out.print("\u001B[36mPlaca: ");
                    String placa = input.readLine().toUpperCase();

                    System.out.print("\u001B[36mHora de entrada: ");
                    String entrada = input.readLine();

                    Veiculo veiculo = new Veiculo(placa, entrada);

                    System.out.print("\u001B[36mVaga: ");
                    int vaga;

                    boolean sucesso = true;
                    while (sucesso) {
                        try {
                            vaga = Integer.parseInt(input.readLine());
                            estacionamento.adicionarNaVaga(veiculo, vaga);

                            System.out.println("\u001B[1m\u001B[32m\nVeículo adicionado com sucesso!\u001B[0m\n");
                            sucesso = false;

                        } catch (RuntimeException e) {
                            System.out.print("\u001B[1m\u001B[31mVaga ocupada!\u001B[0m\n" +
                                    "\u001B[36mDigite outra vaga: ");
                        }
                    }
                }

            } else if (escolha == 2) {

                if (estacionamento.checarSeEstaVazio()) {
                    System.out.println("\u001B[1m\u001B[31mEstacionamento vazio!\u001B[0m\n");

                } else {
                    // Remover veículo
                    System.out.println("\u001B[1m\u001B[32mVamos remover um veículo!\u001B[0m\n");
                    estacionamento.mostrarEstacionamento();
                    System.out.println("\u001B[36mDigite as seguintes informações sobre o veículo: \u001B[0m\n");


                    System.out.print("\u001B[36mVaga: ");
                    int vaga;

                    boolean sucesso = true;
                    while (sucesso) {
                        try {
                            vaga = Integer.parseInt(input.readLine());
                            estacionamento.checarSeVagaEstaVazia(vaga); // Se estiver vazia dispara exceção.

                            System.out.print("\u001B[36mHora de saída: \u001B[0m");
                            String saida = input.readLine();

                            System.out.println();
                            estacionamento.removerDaVaga(vaga, saida);

                            System.out.println("\u001B[1m\u001B[32m\nVeículo removido com sucesso!\u001B[0m\n");
                            sucesso = false;

                        } catch (RuntimeException e) { // Tratamento da exceção
                            System.out.print("\u001B[1m\u001B[31mVaga vazia!\u001B[0m\n" +
                                    "\u001B[36mDigite outra vaga: \u001B[0m");
                        }

                        // Permite o usuário sair quando quiser
                        System.out.print("\u001B[32mDeseja continuar procurando? (SIM) ou (NAO)\u001B[0m\n" +
                                "\u001B[32mR: \u001B[0m");

                        String r = input.readLine().toUpperCase();

                        switch (r) {
                            case "NÃO", "NAO", "NO", "N", "NOP", "NOPE" -> {
                                System.out.println("\u001B[1m\u001B[32mBusca encerrada!\u001B[0m\n");
                                sucesso = false;
                            }
                        }
                    }
                }

            } else if (escolha == 3) {
                // Visualizar estacionamento
                System.out.println("""
                        \u001B[1m\u001B[32mMostrando o estacionamento...\u001B[0m
                        \u001B[36mLegenda:\u001B[0m
                        \u001B[36mRJ - Rio de Janeiro\u001B[0m
                        \u001B[36mSP - São Paulo\u001B[0m
                        \u001B[36mES - Espírito Santo\u001B[0m
                        \u001B[36mOE - Outro estado\u001B[0m
                        """);

                estacionamento.mostrarEstacionamento();
            } else if (escolha == 4) {
                // Olhar veículo pela vaga
                System.out.print("\u001B[1m\u001B[32mVamos verificar uma vaga!\u001B[0m\n" +
                        "\u001B[36mDigite a vaga: \u001B[0m");

                int vaga;

                boolean sucesso = true;
                while (sucesso) {
                    try {
                        vaga = Integer.parseInt(input.readLine());
                        estacionamento.checarSeVagaEstaVazia(vaga); // Se estiver vazia dispara exceção.

                        estacionamento.buscarVeiculoPorVaga(vaga);
                    } catch (RuntimeException e) { // Tratamento de exceção
                        System.out.println("\u001B[1m\u001B[31mVaga vazia!\u001B[0m\n");
                    }

                    // Permite o usuário sair quando quiser
                    System.out.print("\u001B[32mDeseja continuar procurando? (SIM) ou (NAO)\u001B[0m\n" +
                            "\u001B[32mR: \u001B[0m");

                    String r = input.readLine().toUpperCase();

                    switch (r) {
                        case "NÃO", "NAO", "NO", "N", "NOP", "NOPE" -> {
                            System.out.println("\u001B[1m\u001B[32mBusca encerrada!\u001B[0m\n");
                            sucesso = false;
                        }
                    }
                }
            }
        }
    }
}
