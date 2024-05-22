import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime entrada = LocalTime.parse("12:00", formatter);

        Veiculo carro = new Veiculo("ABC1F34", entrada);

        carro.verificarPlacaSudeste();

        System.out.println(carro.getEstado());

    }
}
