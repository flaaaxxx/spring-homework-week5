package pl.flaaaxxx.springhomeworkweek5.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.flaaaxxx.springhomeworkweek5.model.weather.WeatherDetails;
import pl.flaaaxxx.springhomeworkweek5.model.weather.WeatherInfo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Controller
public class WeatherController {

    RestTemplate restTemplate = new RestTemplate();
    private WeatherInfo[] weatherInfo;
    private WeatherDetails weatherDetails;

    public WeatherController() {
    }

    // return basic info about cities
    private WeatherInfo[] getWeatherInfo(String city) {
        weatherInfo = restTemplate.getForObject("https://www.metaweather.com/api/location/search/?query=" + city, WeatherInfo[].class);
        return weatherInfo;
    }

    @GetMapping("/show-city")
    public String showWeather(@ModelAttribute WeatherInfo weather, Model model) {

        model.addAttribute("weather", new WeatherInfo());

        getWeatherInfo(weather.getTitle());
        String city = weather.getTitle();

//        find city
        Optional<WeatherInfo> weatherCity = Arrays.asList(weatherInfo).stream().filter(w -> w.getTitle().equalsIgnoreCase(city)).findFirst();
        if (weatherCity.isPresent()) {
//            find weather details
            weatherDetails = restTemplate.getForObject("https://www.metaweather.com/api/location/" + weatherCity.get().getWoeid(), WeatherDetails.class);
            model.addAttribute("weatherDetails", weatherDetails);
            return "city";
        } else {
            model.addAttribute("weatherDetails", new WeatherDetails());
            return "city";
        }
    }
}
