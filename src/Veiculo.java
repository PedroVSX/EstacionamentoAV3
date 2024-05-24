import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Veiculo {

    private LocalTime entrada;
    private LocalTime saida;
    private String placa;
    private String estado;
    private int vaga;

    public Veiculo(String placa, String entrada) {
        this.placa = placa;
        setEntrada(entrada);
    }

    public void verificarPlacaSudeste() {
        if (verificarPlacaSP(this.placa)) {
            this.estado = "SP";
        } else if (verificarPlacaRJ(this.placa)) {
            this.estado = "RJ";
        } else if (verificarPlacaES(this.placa)) {
            this.estado = "ES";
        } else {
            this.estado = "OE";
        }
    }

    private static boolean verificarPlacaSP(String placa) {
        if (placa.length() != 7 || !placa.matches("[A-Z]{3}[0-9][A-Z][0-9]{2}")) {
            return false;
        }

        String letrasPlaca = placa.substring(0, 3);

        boolean tipo1 = letrasPlaca.compareTo("BFA") >= 0 && letrasPlaca.compareTo("GKI") <= 0;
        boolean tipo2 = letrasPlaca.compareTo("QSN") >= 0 && letrasPlaca.compareTo("QSZ") <= 0;

        return tipo1 || tipo2;
    }

    private static boolean verificarPlacaRJ(String placa) {
        if (placa.length() != 7 || !placa.matches("[A-Z]{3}[0-9][A-Z][0-9]{2}")) {
            return false;
        }

        String letrasPlaca = placa.substring(0, 3);

        boolean tipo1 = letrasPlaca.compareTo("KMF") >= 0 && letrasPlaca.compareTo("LVE") <= 0;
        boolean tipo2 = letrasPlaca.compareTo("RIO") == 0;
        boolean tipo3 = letrasPlaca.compareTo("RIP") >= 0 && letrasPlaca.compareTo("RKV") <= 0;

        return tipo1 || tipo2 || tipo3;
    }

    private static boolean verificarPlacaES(String placa) {
        if (placa.length() != 7 || !placa.matches("[A-Z]{3}[0-9][A-Z][0-9]{2}")) {
            return false;
        }

        String letrasPlaca = placa.substring(0, 3);

        boolean tipo1 = letrasPlaca.compareTo("MOX") >= 0 && letrasPlaca.compareTo("MTZ") <= 0;
        boolean tipo2 = letrasPlaca.compareTo("OCV") >= 0 && letrasPlaca.compareTo("ODT") <= 0;
        boolean tipo3 = letrasPlaca.compareTo("OVE") >= 0 && letrasPlaca.compareTo("OVF") <= 0;
        boolean tipo4 = letrasPlaca.compareTo("OVH") >= 0 && letrasPlaca.compareTo("OVL") <= 0;
        boolean tipo5 = letrasPlaca.compareTo("OYD") >= 0 && letrasPlaca.compareTo("OYK") <= 0;
        boolean tipo6 = letrasPlaca.compareTo("PPA") >= 0 && letrasPlaca.compareTo("PPZ") <= 0;
        boolean tipo7 = letrasPlaca.compareTo("QRB") >= 0 && letrasPlaca.compareTo("QRM") <= 0;
        boolean tipo8 = letrasPlaca.compareTo("RBA") >= 0 && letrasPlaca.compareTo("RBJ") <= 0;
        boolean tipo9 = letrasPlaca.compareTo("RQM") >= 0 && letrasPlaca.compareTo("RQV") <= 0;

        return tipo1 || tipo2 || tipo3 || tipo4 || tipo5 || tipo6 || tipo7 || tipo8 || tipo9;
    }

    private Duration calcularDuracao() {
        Duration duracao = Duration.between(entrada, saida);

        // Se a saída for antes da entrada, ajusta a duração para um dia anterior
        if (duracao.isNegative()) {
            duracao = duracao.plusDays(1);
        }

        return duracao;
    }

    public String getDuracaoFormatada() {
        Duration duracao = calcularDuracao();
        long horas = duracao.toHours();
        long minutos = duracao.toMinutes() % 60;
        return String.format("%d horas e %d minutos", horas, minutos);
    }

    public double getDuracao() {
        Duration duracao = calcularDuracao();
        double horas = duracao.toHours();
        double minutos = duracao.toMinutes() % 60;
        return horas + minutos / 60;
    }

    private double valorPago() {
        // Tolerância de 15 min
        // Primeiras três horas: R$ 20,00
        // Hora subsequentes: R$ 15,00

        if (getDuracao() < 0.25) {
            return 0;
        } else if (getDuracao() <= 3) {
            return 20;
        } else {
            return 20 + 15 * (Math.ceil(getDuracao()) - 3);
        }
    }

    public String getEstado() {
        return estado;
    }

    public String getPlaca() {
        return placa;
    }

    public String getEntrada() {
        return entrada.toString();
    }

    public String getSaida() {
        return saida.toString();
    }

    public int getVaga() {
        return this.vaga;
    }

    public void setEntrada(String entrada) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.entrada = LocalTime.parse(entrada, formatter);
    }

    public void setSaida(String saida) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.saida = LocalTime.parse(saida, formatter);
    }

    public void setVaga(int vaga) {
        this.vaga = vaga;
    }

    public String infoEntrada() {
        return  "\u001B[1m\u001B[34mInformações do veículo:\u001B[0m\n" +
                "\u001B[34mPlaca: " + this.placa + "\n" +
                "Estado: " + this.estado + "\n" +
                "Vaga: " + getVaga() + "\n" +
                "Entrada: " + getEntrada() + "\u001B[0m\n";
    }

    public String infoSaida() {
        return  "\u001B[1m\u001B[34mInformações do veículo:\u001B[0m\n" +
                "\u001B[34mPlaca: " + this.placa + "\n" +
                "Estado: " + this.estado + "\n" +
                "Vaga: " + getVaga() + "\n" +
                "Entrada: " + getEntrada() + "\n" +
                "Saída: " + getSaida() + "\n" +
                "Duração: " + getDuracaoFormatada() + "\n" +
                "Preço a pagar: R$" + valorPago() + "\u001B[0m";
    }
}
