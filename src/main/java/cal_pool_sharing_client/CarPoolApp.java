package cal_pool_sharing_client;
import com.fasterxml.jackson.databind.util.JSONPObject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class CarPoolApp {

    private boolean in_execution;

    public void ShowMenu(){
        System.out.println("          Car Pool Managmente Tool        \n");

        System.out.println("(1) Add a new car");
        System.out.println("(2) Consult car details");
        System.out.println("(3) Remove Car");
        System.out.println("(4) Reserve Car");
        System.out.println("(5) Consult Reserve History");
        System.out.println("(0) Exit");
    }

    public void HandleMenu() {

        Scanner inputScanner = new Scanner(System.in);

        ShowMenu();
        while (in_execution) {
            int user_input = inputScanner.nextInt();

            switch (user_input) {
                case 0: //Exit
                    in_execution = false;
                    break;
                case 1: //Add car
                    System.out.println("Add car option selected");
                    break;
                case 2: // Consult car details
                    System.out.println("Consult car details selected");
                    break;
                case 3: // Remove car
                    System.out.println("Remove car selected");
                    break;
                case 4: // Reserve car
                    System.out.println("Reserve a car selected");
                    break;
                case 5: //Consult reserve history
                    System.out.println("Consult reserve history selected");
                    break;
                default:
                    System.out.println("Invalid option selected");
            }
        }
        System.out.println("Shuting down...");
    }
    public void start(){
        in_execution = true;
        HandleMenu();
    }
    public static void main(String[] args) {
        CarPoolApp carPoolApp = new CarPoolApp();
        carPoolApp.start();

    }

    public void testrequests(){

        RestTemplate restTemplate = new RestTemplate();

        String baseUrl = "http://localhost:8080/";
        String endpointPath = "/availablecars";

        java.sql.Timestamp pickupDate = null;
        java.sql.Timestamp dropOffDate = null;


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date date = dateFormat.parse("03/10/2023 09:00");
            pickupDate = new Timestamp(date.getTime());

            date = dateFormat.parse("05/10/2023 09:00");
            dropOffDate = new Timestamp(date.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + endpointPath)
                .queryParam("pickcupDate", Timestamp.from(Instant.now()))
                .queryParam("dropOffDate", Timestamp.from(Instant.now()));
        // Make the GET request and retrieve the response
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);

        // Handle the response as needed
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            System.out.println("API Response: " + responseBody);
        } else {
            System.err.println("API request failed with status code: " + response.getStatusCodeValue());
        }
    }
}
