import controller.BookingController;
import controller.FlightController;
import dao.FileBookingDao;
import dao.FileFlightDao;
import service.BookingService;
import service.FlightService;

import java.util.Scanner;

public class ConsoleRunner {
    public static void run() {
        BookingController bookingController = new BookingController(new BookingService(new FileBookingDao()));

        Scanner scanner = new Scanner(System.in);
        String userInput;

        String mainMenu = "Доступные команды:\n" +
                "1. Онайн-табло\n" +
                "2. Посмотреть информацию о рейсе\n" +
                "3. Поиск и бронировка рейса\n" +
                "4. Отменить бронирование\n" +
                "5. Мои рейсы\n" +
                "6. Выход\n";

        while (true) {
            System.out.print("\n");
            System.out.println(mainMenu);
            System.out.print("\n");
            System.out.print("Введите номер команды\n");

            userInput = scanner.nextLine().trim();

            if (userInput.equals("6")) {
                System.out.println("Спасибо что пользовались нашим приложением! До новых встреч");
                break;
            }

            switch (userInput) {
                case ("1"):


                    break;
                case ("2"):


                    break;
                case ("3"):


                    break;
                case ("4"):
                    System.out.println("Введите id бронирования");
                    String bookingId = scanner.nextLine().trim();
                    bookingController.deleteBooking(bookingId);
                    break;
                case ("5"):
                    System.out.println("Введите имя пасажира");
                    String firstName = scanner.nextLine().trim().toUpperCase();
                    System.out.println("Введите фамилию пасажира");
                    String lastName = scanner.nextLine().trim().toUpperCase();
                    bookingController.printAllBookingsByPassenger(firstName + " " + lastName);
                    break;
                default:
                    System.out.println("Вы ввели не валидные данные");
                    break;
            }
        }
    }
}
