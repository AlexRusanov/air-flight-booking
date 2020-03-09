import controller.BookingController;
import controller.FlightController;
import dao.FileBookingDao;
import dao.FileFlightDao;
import exceptions.FlightException;
import service.BookingService;
import service.FlightService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleRunner {
    public static void run() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        formatter = formatter.withLocale(Locale.getDefault());
        BookingController bookingController = new BookingController(new BookingService(new FileBookingDao()));
        try {
            FlightController flightController = new FlightController(new FlightService(new FileFlightDao()));

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
                        flightController.printAllFlights();
                        break;
                    case ("2"):
                        System.out.println("Введите id рейса");
                        String flightId = scanner.nextLine().trim();
                        flightController.printFlightById(flightId);
                        break;
                    case ("3"):
                        System.out.println("Введите место назначения");
                        String destination = scanner.nextLine().trim().toUpperCase();
                        System.out.println("Введите дату в формате 21-01-2020");
                        LocalDate date = LocalDate.parse(scanner.nextLine().trim().toUpperCase(), formatter);
                        System.out.println("Введите количество необходимых билетов");
                        String ticketsCount = scanner.nextLine().trim();
                        flightController.printFlightByParams(destination, date, Integer.parseInt(ticketsCount));
                        System.out.print("\n");
                        System.out.println("Выберите порядковый номер рейса");

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
        } catch (FlightException | RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }
}
