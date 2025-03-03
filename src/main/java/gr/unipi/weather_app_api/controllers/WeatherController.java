package gr.unipi.weather_app_api.controllers;

import lombok.RequiredArgsConstructor;
import gr.unipi.weather_app_api.models.WeatherData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gr.unipi.weather_app_api.services.WeatherService;

@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<WeatherData> getWeather(@PathVariable String city) {
        if (city == null || city.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(weatherService.getWeather(city));
    }

}
