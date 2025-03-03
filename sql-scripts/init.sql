CREATE TABLE public.weather_data (
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    city                VARCHAR(50) NOT NULL,
    temperature         INT NOT NULL,
    humidity            INT NOT NULL,
    wind_speed          INT NOT NULL,
    uv_index            INT NOT NULL,
    weather_description VARCHAR(255) NOT NULL,
    timestamp           TIMESTAMP NOT NULL
);