package gr.unipi.weather_app_api.repositories;

import gr.unipi.weather_app_api.models.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long> {
}
