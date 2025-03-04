package gr.unipi.weather_app_api.repository;

import gr.unipi.weather_app_api.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long> {
}
