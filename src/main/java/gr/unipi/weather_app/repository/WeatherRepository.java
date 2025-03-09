package gr.unipi.weather_app.repository;

import gr.unipi.weather_app.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for managing {@link WeatherData} entities.
 * <p>
 * This interface provides methods for retrieving weather-related data,
 * including average weather statistics, hottest and coldest cities, and
 * the most searched cities. It extends {@link JpaRepository}, enabling
 * basic CRUD operations and custom queries using Spring Data JPA.
 */
@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long> {

    /**
     * Retrieves the average temperature, humidity, wind speed, and UV index for a given city.
     *
     * @param city the name of the city to retrieve weather averages for.
     * @return an Object array containing the average temperature, humidity, wind speed, and UV index.
     */
    @Query("SELECT AVG(w.temperature), AVG(w.humidity), AVG(w.windSpeed), AVG(w.uvIndex) " +
            "FROM WeatherData w WHERE w.city = :city")
    Object[] getAverageWeatherForCity(@Param("city") String city);

    /**
     * Retrieves the average temperature, humidity, wind speed, and UV index
     * for weather data recorded within a given date range.
     *
     * @param startDate the start date from which weather data should be considered.
     * @return an Object array containing the average temperature, humidity, wind speed, and UV index.
     */
    @Query("SELECT AVG(w.temperature), AVG(w.humidity), AVG(w.windSpeed), AVG(w.uvIndex) " +
            "FROM WeatherData w WHERE w.timestamp >= :startDate")
    Object[] getAverageWeatherForLastDays(@Param("startDate")LocalDateTime startDate);

    /**
     * Finds the city with the highest recorded temperature.
     *
     * @return an Object array containing the city name and the highest recorded temperature.
     */
    @Query("SELECT w.city, MAX(w.temperature) " +
            "FROM WeatherData w")
    Object[] findHottestCity();

    /**
     * Finds the city with the lowest recorded temperature.
     *
     * @return an Object array containing the city name and the lowest recorded temperature.
     */
    @Query("SELECT w.city, MIN(w.temperature) " +
            "FROM WeatherData w")
    Object[] findColdestCity();

    /**
     * Retrieves a list of cities ordered by the number of times they have been searched.
     *
     * @return a list of Object arrays, where each array contains a city name and the corresponding search count.
     */
    @Query("SELECT w.city, COUNT(w.city) " +
            "FROM WeatherData w GROUP BY w.city ORDER BY COUNT(w.city) DESC")
    List<Object[]> findMostSearchedCities();

}
