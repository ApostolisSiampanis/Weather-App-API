package gr.unipi.weather_app.repository;

import gr.unipi.weather_app.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long> {

    @Query("SELECT AVG(w.temperature), AVG(w.humidity), AVG(w.windSpeed), AVG(w.uvIndex) " +
            "FROM WeatherData w WHERE w.city = :city")
    Object[] getAverageWeatherForCity(@Param("city") String city);

    @Query("SELECT AVG(w.temperature), AVG(w.humidity), AVG(w.windSpeed), AVG(w.uvIndex) " +
            "FROM WeatherData w WHERE w.timestamp >= :startDate")
    Object[] getAverageWeatherForLastDays(@Param("startDate")LocalDateTime startDate);

    @Query("SELECT w.city, MAX(w.temperature) " +
            "FROM WeatherData w")
    Object[] findHottestCity();

    @Query("SELECT w.city, MIN(w.temperature) " +
            "FROM WeatherData w")
    Object[] findColdestCity();

    @Query("SELECT w.city, COUNT(w.city) " +
            "FROM WeatherData w GROUP BY w.city ORDER BY COUNT(w.city) DESC")
    List<Object[]> findMostSearchedCities();

}
