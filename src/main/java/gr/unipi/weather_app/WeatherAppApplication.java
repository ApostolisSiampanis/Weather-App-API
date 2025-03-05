package gr.unipi.weather_app;

import gr.unipi.weather_app.service.WeatherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
                System.out.println("2. Exit");
                System.out.print("Enter your choice: ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid number: ");
                    scanner.next();
                }
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter city name: ");
                        String city = scanner.nextLine().trim();

                        while (!city.matches("^[a-zA-Z ]+$")) {
                            System.out.print("Invalid city name! Enter again: ");
                            city = scanner.nextLine().trim();
                        }

                        System.out.println("\nFetching weather data for " + city + "...");
                        var weatherData = weatherService.getWeather(city);
                        System.out.println(weatherData);
                        break;

                    case 2:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid number.");
                }

            } while (choice != 2);

        };
    }
}
