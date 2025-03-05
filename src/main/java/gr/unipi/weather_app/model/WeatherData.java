package gr.unipi.weather_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "weather_data", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private int id;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "temperature", nullable = false)
    private int temperature;

    @Column(name = "humidity", nullable = false)
    private int humidity;

    @Column(name = "wind_speed", nullable = false)
    private int windSpeed;

    @Column(name = "uv_index", nullable = false)
    private int uvIndex;

    @Column(name = "weather_description", nullable = false)
    private String weather_description;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        String formatedTimestamp = timestamp.format(formatter);

        return "\uD83C\uDF0D Weather Report for " + city + '\n' +
                "\uD83C\uDF21\uFE0F Temperature: " + temperature + "°C\n" +
                "💧 Humidity: " + humidity + "%\n" +
                "💨 Wind Speed: " + windSpeed + " km/h\n" +
                "☀️ UV Index: " + uvIndex + "\n" +
                "📜 Weather Description: " + weather_description + "\n" +
                "⏳ Timestamp: " + formatedTimestamp + "\n";
    }
}
