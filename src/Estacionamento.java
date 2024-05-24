public class Estacionamento {

    private Veiculo[][] vagas;

    public Estacionamento(int fileiras, int colunas) {
        this.vagas = new Veiculo[fileiras][colunas];
    }

    public void adicionarNaVaga(Veiculo veiculo, int vaga) {
        int contador = 1;
        boolean parou = false;

        veiculo.verificarPlacaSudeste();

        for (int i = 0; i < vagas.length; i++) {
            for (int j = 0; j < vagas[0].length; j++) {
                if (vaga == contador) {

                    if (vagas[i][j] != null) {
                        throw new RuntimeException("Vaga já ocupada!");
                    } else {
                        vagas[i][j] = veiculo;
                        parou = true;
                        break;
                    }
                }
                contador++;
            }

            if (parou) {
                break;
            }
        }

        veiculo.setVaga(vaga);
    }

    public void removerDaVaga(int vaga, String saida) {
        int contador = 1;
        boolean parou = false;

        for (int i = 0; i < vagas.length; i++) {
            for (int j = 0; j < vagas[0].length; j++) {
                if (vaga == contador) {
                    vagas[i][j].setSaida(saida);
                    System.out.println(vagas[i][j].infoSaida());

                    vagas[i][j] = null;
                    parou = true;
                }
                contador++;
            }

            if (parou) {
                break;
            }
        }
    }

    public void buscarVeiculoPorVaga(int vaga) {
        int contador = 1;
        boolean parou = false;

        for (int i = 0; i < vagas.length; i++) {
            for (int j = 0; j < vagas[0].length; j++) {
                if (vaga == contador) {
                    System.out.println("\n" + vagas[i][j].infoEntrada());
                    parou = true;
                }
                contador++;
            }

            if (parou) {
                break;
            }
        }
    }

    public void mostrarEstacionamento() {
        String concatenador = "";

        for (int i = 0; i < vagas.length; i++) {
            concatenador += "|";

            for (int j = 0; j < vagas[0].length; j++) {
                if (vagas[i][j] == null) {
                    concatenador += " \u001B[32mVaga livre\u001B[0m ";
                } else {
                    concatenador += "\u001B[31m " + vagas[i][j].getPlaca() + "-" + vagas[i][j].getEstado() + " \u001B[0m";
                }

                concatenador += "|";
            }

            concatenador += "\n";
        }

        System.out.println(concatenador);
    }

    public void checarSeEstaVazia(int vaga) {
        int contador = 1;
        boolean parou = false;

        for (int i = 0; i < vagas.length; i++) {
            for (int j = 0; j < vagas[0].length; j++) {
                if (vaga == contador) {

                    if (vagas[i][j] == null) {
                        throw new RuntimeException("Vaga já está vazia!");
                    } else {
                        parou = true;
                        break;
                    }

                }
            }

            if (parou) {
                break;
            }
        }
    }

    public boolean checarSeEstaCheio() {
        for (int i = 0; i < vagas.length; i++) {
            for (int j = 0; j < vagas[0].length; j++) {
                if (vagas[i][j] == null) {
                    return false;
                }
            }
        }

        return true;
    }
}
