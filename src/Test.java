import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate appDate = LocalDate.parse(scanner.nextLine().trim(), dateFormat);
        System.out.println(appDate+"  "+LocalDate.now()+" "+appDate.isAfter(LocalDate.now()));
        System.out.println(appDate+"  "+LocalDate.now()+" "+appDate.isEqual(LocalDate.now()));

    }
}
