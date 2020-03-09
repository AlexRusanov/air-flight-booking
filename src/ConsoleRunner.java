import controller.BookingController;
import controller.FlightController;
import dao.FileBookingDao;
import dao.FileFlightDao;
import model.Flight;
import service.BookingService;
import service.FlightService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ConsoleRunner {
    public static void run() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
                        System.out.println("Введите дату в формате 2020-05-04");
                        LocalDate date = LocalDate.parse(scanner.nextLine().trim().toUpperCase(), formatter);
                        System.out.println("Введите количество необходимых билетов");
                        int ticketsCount = Integer.parseInt(scanner.nextLine().trim());
                        Optional<List<Flight>> flightList = flightController.printFlightByParams(destination, date, ticketsCount);
                        System.out.print("\n");
                        System.out.println("Выберите порядковый номер рейса или введите 0 для возврата в главное меню");
                        userInput = scanner.nextLine().trim();

                        if (Objects.equals(userInput, "0")) {
                            break;
                        }

                        int flightIndex = Integer.parseInt(userInput) - 1;

                        List<String> passengers = new ArrayList<>();

                        for (int i = 0; i < ticketsCount; i++) {
                            System.out.println("Введите имя пасажира");
                            String fullName = scanner.nextLine().trim().toUpperCase();
                            System.out.println("Введите фамилию пасажира");
                            fullName += scanner.nextLine().trim().toUpperCase();
                            passengers.add(fullName);
                        }

                        bookingController.createBooking(destination,
                                flightList.get().get(flightIndex).getDepartureTime(),
                                flightList.get().get(flightIndex).getFlightId(),
                                passengers);
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
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }


    public static void main(String[] args) {
        run();
    }
}
