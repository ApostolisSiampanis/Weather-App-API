package gr.unipi.weather_app.service;

import gr.unipi.weather_app.model.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import gr.unipi.weather_app.repository.WeatherRepository;

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

    private String formatAverageWeather(String location, Object[] avgWeather) {
        if (avgWeather == null || avgWeather.length == 0) {
            return "No data available for " + location;
        }

        Object[] nestedAvgWeather = (Object[]) avgWeather[0];

        double avgTemp = nestedAvgWeather[0] != null ? ((Number) nestedAvgWeather[0]).doubleValue() : 0.0;
        double avgHumidity = nestedAvgWeather[1] != null ? ((Number) nestedAvgWeather[1]).doubleValue() : 0.0;
        double avgWindSpeed = nestedAvgWeather[2] != null ? ((Number) nestedAvgWeather[2]).doubleValue() : 0.0;
        double avgUvIndex = nestedAvgWeather[3] != null ? ((Number) nestedAvgWeather[3]).doubleValue() : 0.0;

        if (avgTemp == 0.0 && avgHumidity == 0.0 && avgWindSpeed == 0.0 && avgUvIndex == 0.0) {
            return "No data available for " + location;
        }

        return String.format(
                "%nAverage Weather for %s%nAverage Temperature: %.1f°C%nAverage Humidity: %.1f%%%nAverage Wind Speed: %.1f km/h%nAverage UV Index: %.1f",
                location, avgTemp, avgHumidity, avgWindSpeed, avgUvIndex
        );
    }

    public String getAverageWeatherForCity(String city) {
        Object[] avgWeather = repository.getAverageWeatherForCity(city);
        return formatAverageWeather(city, avgWeather);
    }

    public String getAverageWeatherForLastDays(int days) {
        LocalDateTime startDate = LocalDateTime.now().minusDays(days);
        Object[] avgWeather = repository.getAverageWeatherForLastDays(startDate);
        return formatAverageWeather("the last " + days + " days", avgWeather);
    }

    public String findHottestCity() {
        Object[] hottestCity = repository.findHottestCity();
        Object[] nestedHottestCity = (Object[]) hottestCity[0];

        if (nestedHottestCity[0] == null) {
            return "No data available for the hottest city";
        }

        return String.format(
                "%n🔥 Hottest City Recorded: %s%nHottest Temperature: %d°C",
                nestedHottestCity[0], ((Number) nestedHottestCity[1]).intValue()
        );
    }

    public String findColdestCity() {
        Object[] coldestCity = repository.findColdestCity();
        Object[] nestedColdestCity = (Object[]) coldestCity[0];

        if (nestedColdestCity[0] == null) {
            return "No data available for the coldest city";
        }

        return String.format(
                "%n❄️ Coldest City Recorded: %s%nColdest Temperature: %d°C",
                nestedColdestCity[0], ((Number) nestedColdestCity[1]).intValue()
        );
    }

    public String getMostSearchedCities() {
        List<Object[]> cities = repository.findMostSearchedCities();
        if (cities == null || cities.isEmpty()) {
            return "No data available for the most searched cities";
        }
        StringBuilder result = new StringBuilder();
        for (Object[] c : cities) {
            result.append(String.format(
                    "%n🔍 City: %s%n🔢 Searches: %d",
                    c[0], ((Number) c[1]).intValue()
            ));
        }

        return result.toString();
    }

}
