package com.weather.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.weather.model.WeatherInfo;

@RestController
@RequestMapping("/weather")
public class WeatherResource {
	
	@Autowired
	private WebClient.Builder builder;
	
	@Value("${service.uri}")
	private String uri;
	
	@Value("${service.host}")
	private String host;
	
	@Value("${service.key}")
	private String key;
	
	@RequestMapping("/{city}")
	public WeatherInfo getWeatherDetails(@PathVariable("city") String city)
	{
//		System.out.println(host);
		WeatherInfo info = builder.build().get()
							.uri(uri + "?q="+city+"&units=metric")
							.header("x-rapidapi-key", key)
							.header("x-rapidapi-host", host)
							.retrieve()
							.bodyToMono(WeatherInfo.class)
							.block();
		
//		System.out.println(info.getWeather()[0].getDescription());
		
		return null;
	}
}
