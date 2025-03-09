package gr.unipi.weather_app.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class for setting up a {@link WebClient} bean.
 * <p>
 * This class is responsible for creating a {@link WebClient} instance
 * that will be used to make HTTP requests to the weather API.
 * The base URL for the API is injected from the application's properties.
 */
@Configuration
public class WebClientConfig {

    /**
     * The base URL for the weather API, injected from application properties.
     */
    @Value("${weather.api.base-url}")
    private String baseUrl;

    /**
     * Creates and configures a {@link WebClient} bean.
     * <p>
     * This WebClient instance is built using the provided base URL
     * and can be used throughout the application for making
     * asynchronous HTTP requests.
     *
     * @return a configured {@link WebClient} instance
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

}
