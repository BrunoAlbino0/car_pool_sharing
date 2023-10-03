package car_pool_sharing_client;

import car_pool_sharing_client.Models.Car;
import car_pool_sharing_client.Models.Dates_Wrapper;
import car_pool_sharing_client.Models.EngineType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class CarPoolApp {

    private boolean in_execution;
    private final String baseUrl = "http://localhost:8080/";
    private final String endpoint_add_car = "/car";
    private final String endpoint_get_all_cars = "/car";
    private final String endpoint_get_car_details = "/car_details";
    private final String endpoint_remove_car = "/inactivatecar";
    private final String endpoint_available_cars = "/availablecars";

    public void ShowMenu(){
        System.out.println("          Car Pool Managmente Tool        \n");

        System.out.println("(1) Add a new car");
        System.out.println("(2) List All Cars");
        System.out.println("(3) Consult car details");
        System.out.println("(4) Remove Car");
        System.out.println("(5) Reserve Car");
        System.out.println("(6) Consult Reserve History");
        System.out.println("(0) Exit");
    }

    public void HandleMenu() {

        Scanner inputScanner = new Scanner(System.in);

        ShowMenu();
        while (in_execution) {
            System.out.print(">");
            int user_input = inputScanner.nextInt();

            switch (user_input) {
                case 0: //Exit
                    in_execution = false;
                    break;
                case 1: //Add car
                    AddCar();
                    break;
                case 2: //List cars
                    AvailableCars();
                    break;
                case 3: // Consult car details
                    //System.out.println("Consult car details selected");
                    GetCarDetails();
                    break;
                case 4: // Remove car
                    //System.out.println("Remove car selected");
                    RemoveCar();
                    break;
                case 5: // Reserve car
                    System.out.println("Reserve a car selected");
                    ReserveCar();
                    break;
                case 6: //Consult reserve history
                    System.out.println("Consult reserve history selected");
                    ReserveHistory();
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

    public void AddCar(){

        Scanner inputScanner = new Scanner(System.in);

        String brand;
        String model;
        Integer seats;
        String licensePlate;
        EngineType engineType;
        Integer currentAutonomy;

        //Validar inputs
        System.out.println("Car:");
        System.out.println("Brand?");
        System.out.print(">");
        brand = inputScanner.nextLine();

        System.out.println("Model?");
        System.out.print(">");
        model = inputScanner.nextLine();

        System.out.println("Seats?");
        System.out.print(">");
        seats = inputScanner.nextInt();

        // Consume the newline character
        inputScanner.nextLine();

        System.out.println("license Plate?");
        System.out.print(">");
        licensePlate = inputScanner.nextLine();

        System.out.println("Engine Type?     Options(COMBUSTION, ELECTRIC, HYBRID)");
        System.out.print(">");
        engineType = EngineType.valueOf(inputScanner.nextLine());

        System.out.println("Current Autonomy? ");
        System.out.print(">");
        currentAutonomy = inputScanner.nextInt();

        Car car = new Car(null, brand, model, seats, licensePlate, engineType, currentAutonomy, false);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Car> requestEntity = new HttpEntity<>(car, headers);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + endpoint_add_car);

        ResponseEntity<String> response = restTemplate.postForEntity(builder.toUriString(), requestEntity, String.class);

        // Handle the response as needed
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            System.out.println("API Response: " + responseBody);
        } else {
            System.err.println("API request failed with status code: " + response.getStatusCodeValue());
        }
    }
    public void GetCarDetails(){
        ListAllCars();

        System.out.println("ID of car to see details?");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);

        int id = scanner.nextInt();

        if (id > 0 ){

            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(baseUrl + endpoint_get_car_details)
                    .queryParam("id", id);

            // Make the GET request and retrieve the response
            ResponseEntity<Car> response = restTemplate.getForEntity(builder.toUriString(), Car.class);

            // Handle the response as needed
            if (response.getStatusCode().is2xxSuccessful()) {
                Car responseBody = response.getBody();
                System.out.println("Cars Details: \n "+ responseBody.getDetails());
            } else {
                System.err.println("API request failed with status code: " + response.getStatusCodeValue());
            }
        }else {
            System.out.println("Invalid ID!");
        }
    }

    public void RemoveCar(){
        ListAllCars();

        System.out.println("ID of car to Remove?");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);

        int id = scanner.nextInt();

        if (id > 0 ){
            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(baseUrl + endpoint_remove_car)
                    .queryParam("id", id);

            // Make the GET request and retrieve the response
            ResponseEntity<Car> response = restTemplate.getForEntity(builder.toUriString(), Car.class);

            // Handle the response as needed
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Car Removed");
            } else {
                System.err.println("API request failed with status code: " + response.getStatusCodeValue());
            }
        }else {
            System.out.println("Invalid ID!");
        }
    }

    public void AvailableCars(){
        Scanner scanner = new Scanner(System.in);

        String pickupDate = null;
        String dropOffDate = null;
        System.out.println("Pickup date: (format: MM/dd/yyyy HH:mm)");
        System.out.print(">");
        pickupDate = scanner.nextLine();

        System.out.println("Drop off date: (format: MM/dd/yyyy HH:mm)");
        System.out.print(">");
        dropOffDate = scanner.nextLine();

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + endpoint_available_cars);

        Dates_Wrapper dates_wrapper = new Dates_Wrapper(pickupDate, dropOffDate);

        // Make the GET request and retrieve the response
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);

        // Handle the response as needed
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            //Car[] responseBody = response.getBody();
            System.out.println("Recebido: " + responseBody);
        } else {
            System.err.println("API request failed with status code: " + response.getStatusCodeValue());
        }

    }

    public void ReserveCar(){

    }

    public void ReserveHistory(){

    }

    public void ListAllCars(){

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + endpoint_get_all_cars);

        // Make the GET request and retrieve the response
        ResponseEntity<Car[]> response = restTemplate.getForEntity(builder.toUriString(), Car[].class);

        // Handle the response as needed
        if (response.getStatusCode().is2xxSuccessful()) {
            Car[] responseBody = response.getBody();
            System.out.println("Cars in the system:");
            for (Car car : responseBody){
                System.out.println(car.toString());
            }

        } else {
            System.err.println("API request failed with status code: " + response.getStatusCodeValue());
        }
    }
}
