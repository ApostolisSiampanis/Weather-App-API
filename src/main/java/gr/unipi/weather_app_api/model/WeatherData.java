package gr.unipi.weather_app_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private Long id;

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
}
