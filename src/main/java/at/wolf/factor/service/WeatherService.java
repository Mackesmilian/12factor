package at.wolf.factor.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.Log4J2LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final InternalLogger log = Log4J2LoggerFactory.getInstance(WeatherService.class);
    @Value("${weather.service.url}")
    private String baseUrl;
    private WebClient client;

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value ->
                    log.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }

    public String whatToPack() {
        client = WebClient.builder()
                .baseUrl(baseUrl)
                .filter(logRequest())
                .build();
        String graz = getWeatherGraz();
        int avgTempGraz = getAvgTemp(graz);
        log.info("Temp Graz: {}", avgTempGraz);
        String vienna = getWeatherVienna();
        int avgTempVienna = getAvgTemp(vienna);
        log.info("Temp Vienna: {}", avgTempVienna);
        return avgTempGraz < avgTempVienna ?
                "It's gonna be warmer by " + (avgTempVienna - avgTempGraz) + " degrees"
                : "It's gonna be colder by " + (avgTempGraz - avgTempVienna) + " degrees";
    }

    private int getAvgTemp(String graz) {
        int totalTemp = 0;
        int amountOfTemp = 0;
        JsonArray array = JsonParser.parseString(graz).getAsJsonObject().getAsJsonArray(
                "dataseries");
        for (JsonElement element : array) {
            totalTemp += element.getAsJsonObject().get("temp2m").getAsInt();
            amountOfTemp++;
        }
        return totalTemp / amountOfTemp;
    }

    private String getWeatherGraz() {
        return client.get()
                .uri(uriBuilder -> uriBuilder.queryParam("lon", "15.43")
                        .queryParam("lat", "47.07")
                        .queryParam("product", "civil")
                        .queryParam("output", "json")
                        .build())
                .retrieve()
                .bodyToMono(String.class).block();
    }

    private String getWeatherVienna() {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("").queryParam("lon", "16.37")
                        .queryParam("lat", "48.21")
                        .queryParam("product", "civil")
                        .queryParam("output", "json")
                        .build())
                .retrieve()
                .bodyToMono(String.class).block();
    }

}
