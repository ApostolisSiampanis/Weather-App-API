# Weather App Api

## [University of Piraeus](https://www.unipi.gr/en/home/) | [Department of Informatics](https://cs.unipi.gr/en/)
**MSc course**: Advanced Topics of Object - Oriented Programming (Java)

**Semester**: 1

**Project Completion Year**: 2025

## Description
WeatherApp is a Java-based console application that retrieves and processes weather data using the wttr.in web service. The application fetches weather information in JSON format and allows users to search for weather conditions based on city names. The retrieved data is stored in a local database for later analysis and statistical calculations.

## Features
- **Get weather data for a city:** Retrieve real-time weather information, including temperature, humidity, wind speed, UV index, and weather description.
- **Get average weather for a city:** Calculate the average weather conditions for a specific city based on previous searches stored in the database.
- **Get average weather for the last X days:** Compute and display the average weather conditions for a specified number of days.
- **Find the hottest city:** Identify the city with the highest recorded temperature in the database.
- **Find the coldest city:** Identify the city with the lowest recorded temperature in the database.
- **Find the most searched cities:** Display the cities that have been searched the most by users.

## Data Storage
The application uses a local SQLite database to store search results. Each record includes:
- City name
- Temperature (°C)
- Humidity (%)
- Wind speed (km/h)
- UV index
- Weather description
- Timestamp of the search

## API Reference
The application interacts with the wttr.in API, which provides weather data in JSON format. More details can be found in the official API documentation: [wttr.in GitHub](https://github.com/chubin/wttr.in?#readme).

## Javadocs
The Javadocs for this project are hosted on GitHub Pages. You can access them directly from the following link:

[Weather App api Javadocs](https://apostolissiampanis.github.io/Weather-App-API/)

The documentation includes detailed descriptions for all classes, methods, and fields.

## Contributors
<table>
  <tr>
    <td align="center"><a href="https://github.com/ApostolisSiampanis"><img src="https://avatars.githubusercontent.com/u/75365398?v=4" width="100px;" alt="Apostolis Siampanis"/><br /><sub><b>Apostolis Siampanis</b></sub></a><br /></td>
    <td align="center"><a href="https://github.com/dimitrisstyl7"><img src="https://avatars.githubusercontent.com/u/75742419?v=4" width="100px;" alt="Dimitris Stylianou"/><br /><sub><b>Dimitris Stylianou</b></sub></a><br /></td>
  </tr>
</table>

## Acknowledgments
This project was developed as part of the "Advanced Topics of Object - Oriented Programming (Java)" MSc course at the University of Piraeus. Contributions and feedback are always welcome!

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
