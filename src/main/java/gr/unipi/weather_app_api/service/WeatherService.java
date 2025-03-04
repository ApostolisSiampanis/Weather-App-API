package gr.unipi.weather_app_api.service;

import gr.unipi.weather_app_api.model.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import gr.unipi.weather_app_api.repository.WeatherRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    private final WebClient webClient;
    private final WeatherRepository repository;

    @Value("${weather.api.url-params}")
    private String urlParams; // URL extension for weather API, %s is the city name and j1 is the JSON format

    public WeatherService(WeatherRepository repository, WebClient webClient) {
        this.webClient = webClient;
        this.repository = repository;
    }

    public WeatherData getWeather(String city) {
        try {
            String url = String.format(urlParams, city);

            Map response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block(); // Blocking call to make it synchronous

            if (response == null) {
                throw new RuntimeException("No response from weather API");
            }

            Map<String, Object> currentCondition = ((List<Map<String, Object>>) response.get("current_condition")).getFirst();
            WeatherData data = new WeatherData();
            data.setCity(city);
            data.setTemperature(Integer.parseInt((String) currentCondition.get("temp_C")));
            data.setHumidity(Integer.parseInt((String) currentCondition.get("humidity")));
            data.setWindSpeed(Integer.parseInt((String) currentCondition.get("windspeedKmph")));
            data.setUvIndex(Integer.parseInt((String) currentCondition.get("uvIndex")));
            data.setWeather_description(((List<Map<String, Object>>) currentCondition.get("weatherDesc")).getFirst().get("value").toString());
            data.setTimestamp(LocalDateTime.now());

            return repository.save(data);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching weather data", e);
        }
    }

    public List<WeatherData> getAllWeatherRecords() {
        return repository.findAll();
    }

}
