import java.time.Duration;
import java.time.LocalTime;

public class Veiculo {

    private LocalTime entrada;
    private LocalTime saida;
    private String placa;
    private String estado;

    public Veiculo(String placa, LocalTime entrada) {
        this.placa = placa;
        this.entrada = entrada;
    }

    public void verificarPlacaSudeste() {
        if (verificarPlacaSP(this.placa)) {
            this.estado = "São Paulo";
        } else if (verificarPlacaRJ(this.placa)) {
            this.estado = "Rio de Janeiro";
        } else if (verificarPlacaES(this.placa)) {
            this.estado = "Espírito Santo";
        } else {
            System.out.println("Placa não pertence ao Sudeste!");
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

    public Duration calcularDuracao() {
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

    public String getEstado() {
        return estado;
    }

    public String getPlaca() {
        return placa;
    }
}
