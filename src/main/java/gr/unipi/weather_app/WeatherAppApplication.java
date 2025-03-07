package gr.unipi.weather_app;

import gr.unipi.weather_app.service.WeatherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class WeatherAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherAppApplication.class, args);
    }

    @Bean
    CommandLineRunner runConsole(WeatherService weatherService) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n🌤️ WEATHER APP API MENU 🌤️");
                System.out.println("1. Get weather data for a city");
                System.out.println("2. Get average weather for a city");
                System.out.println("3. Get average weather for the last X days");
                System.out.println("4. Find the hottest city");
                System.out.println("5. Find the coldest city");
                System.out.println("6. Find the most searched cities");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid number: ");
                    scanner.next();
                }
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        getWeatherData(scanner, weatherService);
                        break;
                    case 2:
                        getAverageWeatherForCity(scanner, weatherService);
                        break;
                    case 3:
                        getAverageWeatherForLastDays(scanner, weatherService);
                        break;
                    case 4:
                        System.out.println(weatherService.findHottestCity());
                        break;
                    case 5:
                        System.out.println(weatherService.findColdestCity());
                        break;
                    case 6:
                        System.out.println(weatherService.getMostSearchedCities());
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid number.");
                }

            } while (choice != 7);

        };
    }

    private void getWeatherData(Scanner scanner, WeatherService weatherService) {
        System.out.print("Enter city name: ");
        String city = scanner.nextLine().trim();

        while (!city.matches("^[a-zA-Z ]+$")) {
            System.out.print("Invalid city name! Enter again: ");
            city = scanner.nextLine().trim();
        }

        System.out.println("\nFetching weather data for " + city + "...");
        var weatherData = weatherService.getWeather(city);
        System.out.println(weatherData);
    }

    private void getAverageWeatherForCity(Scanner scanner, WeatherService weatherService) {
        System.out.print("Enter city name: ");
        String city = scanner.nextLine().trim();

        while (!city.matches("^[a-zA-Z ]+$")) {
            System.out.print("Invalid city name! Enter again: ");
            city = scanner.nextLine().trim();
        }

        System.out.println(weatherService.getAverageWeatherForCity(city));
    }

    private void getAverageWeatherForLastDays(Scanner scanner, WeatherService weatherService) {
        System.out.print("Enter number of days: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number: ");
            scanner.next();
        }
        int days = scanner.nextInt();
        scanner.nextLine();

        System.out.println(weatherService.getAverageWeatherForLastDays(days));
    }
}
