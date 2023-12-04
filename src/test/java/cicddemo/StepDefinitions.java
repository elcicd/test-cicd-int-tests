package stationdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {
    
    private static final String STATION_DEMO_HOST = "STATION_DEMO_HOST";
    private static final String SDLC_ENV = "SDLC_ENV";
    
    private HttpURLConnection connection;

    private ObjectMapper objectMapper = new ObjectMapper();
    
    private int httpStatus;
    private String httpResponse;

    @Given("the stationdemo app returns http code 200")
    public void the_stationdemo_app_returns_http_code_200() throws IOException, MalformedURLException {
        String stationDemoHost = "stationdemo:8080";
        String env = System.getenv("SDLC_ENV");
        
        String path = "/stationdemo";
        if (env) {
            path = path + "-" + env;
        }
        else {
            stationDemoHost = "localhost:8080";
        }
        
        
        path = (env != null) ? path + "-" + env : path;
        
        String httpUrl = "http://" + stationDemoHost + path + "/stations";
        URL url = new URL(httpUrl);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        httpStatus = connection.getResponseCode();
        
        assertEquals(200, httpStatus);
    }

    @When("we call the stations url")
    public void we_call_the_stations_url () throws IOException {        
        StringBuilder stringBuilder = new StringBuilder();
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line + "\n");
        }
        
        bufferedReader.close();
        httpResponse = stringBuilder.toString();
    }

    @Then("we get a json list of stations")
    public void we_get_a_json_list_of_stations() throws JsonProcessingException {
        Stations stations = objectMapper.readValue(httpResponse, Stations.class);
        
        assertTrue(stations.getStations().size() > 0);
    }
}
