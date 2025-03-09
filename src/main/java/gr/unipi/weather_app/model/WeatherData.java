package gr.unipi.weather_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents weather data for a specific city, including temperature, humidity,
 * wind speed, UV index, weather description, and a timestamp.
 *
 * This class is mapped to the "weather_data" table in the "public" schema using JPA annotations.
 * Lombok is used to reduce boilerplate code for getters, setters, and constructors.
 */
@Entity
@Table(name = "weather_data", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {

    /**
     * The unique identifier for the weather data record.
     * It is auto-generated and cannot be modified after creation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private int id;

    /**
     * The name of the city for which the weather data is recorded.
     * It cannot be null and has a maximum length of 50 characters.
     */
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    /**
     * The temperature in degrees Celsius.
     * It cannot be null.
     */
    @Column(name = "temperature", nullable = false)
    private int temperature;

    /**
     * The humidity percentage.
     * It cannot be null.
     */
    @Column(name = "humidity", nullable = false)
    private int humidity;

    /**
     * The wind speed in kilometers per hour.
     * It cannot be null.
     */
    @Column(name = "wind_speed", nullable = false)
    private int windSpeed;

    /**
     * The UV index, representing the intensity of ultraviolet radiation.
     * It cannot be null.
     */
    @Column(name = "uv_index", nullable = false)
    private int uvIndex;

    /**
     * A brief textual description of the weather conditions.
     * It cannot be null.
     */
    @Column(name = "weather_description", nullable = false)
    private String weather_description;

    /**
     * The timestamp when the weather data was recorded.
     * It cannot be null.
     */
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    /**
     * Returns a formatted string representation of the weather data.
     *
     * @return A human-readable weather report including temperature, humidity,
     *         wind speed, UV index, weather description, and timestamp.
     */
    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        String formatedTimestamp = timestamp.format(formatter);

        return "\uD83C\uDF0D Weather Report for " + city + '\n' +
                "🌡️ Temperature: " + temperature + "°C\n" +
                "💧 Humidity: " + humidity + "%\n" +
                "💨 Wind Speed: " + windSpeed + " km/h\n" +
                "☀️ UV Index: " + uvIndex + "\n" +
                "📜 Weather Description: " + weather_description + "\n" +
                "⏳ Timestamp: " + formatedTimestamp + "\n";
    }
}
