package car_pool_sharing_client;

import car_pool_sharing_client.Models.*;
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

    private Scanner scanner;
    private boolean in_execution;
    private final String baseUrl = "http://localhost:8080/";
    private final String endpoint_add_car = "/car";
    private final String endpoint_get_all_cars = "/car";
    private final String endpoint_get_car_details = "/car_details";
    private final String endpoint_remove_car = "/inactivatecar";
    private final String endpoint_available_cars = "/availablecars";
    private final String endpoint_get_drivers = "/driver";
    private final String endpoint_reserve_car = "/reservation";
    private final String endpoint_add_driver = "/driver";
    private final String endpoint_reservation_history = "/reservationHistory";


    public CarPoolApp(){
        scanner = new Scanner(System.in);
    }

    public void ShowMenu(){
        System.out.println("          Car Pool Managmente Tool        \n");

        System.out.println("(1) Add a new car");
        System.out.println("(2) List All Cars");
        System.out.println("(3) Consult car details");
        System.out.println("(4) Remove Car");
        System.out.println("(5) Reserve Car");
        System.out.println("(6) Consult Reserve History");
        System.out.println("(7) Add a new driver");
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
                    String pickupDate = getDatesFromUser("Pickup date?");
                    String dropOffDate = getDatesFromUser("Dropoff date?");
                    getAvailableCars(pickupDate, dropOffDate);
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
                    //System.out.println("Reserve a car selected");
                    ReserveCar();
                    break;
                case 6: //Consult reserve history
                    System.out.println("Consult reserve history selected");
                    ReserveHistory();
                    break;
                case 7: //Add new driver
                    addDriver();
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
    public String getDatesFromUser(String userQuestion){
        Scanner scanner = new Scanner(System.in);
        String date;
        System.out.println(userQuestion + " (format: dd/MM/yyyy HH:mm)");

        System.out.print(">");
        date = scanner.nextLine();
        date= date.replace(' ', 'T');

        return date;
    }
   public void getAvailableCars(String pickupDate, String dropOffDate){
       RestTemplate restTemplate = new RestTemplate();

       UriComponentsBuilder builder = UriComponentsBuilder
               .fromHttpUrl(baseUrl + endpoint_available_cars)
               .queryParam("pickcupDate", pickupDate)
               .queryParam("drop_offDate", dropOffDate);

       // Make the GET request and retrieve the response
       ResponseEntity<Car []> response = restTemplate.getForEntity(builder.toUriString(), Car[].class);

       // Handle the response as needed
       if (response.getStatusCode().is2xxSuccessful()) {
           Car[] responseBody = response.getBody();

           assert responseBody != null;
           for (Car car : responseBody){
               System.out.println(car.getDetails());
           }

       } else {
           System.err.println("API request failed with status code: " + response.getStatusCodeValue());
       }
   }

    public void ReserveCar(){

        ReservationDTO reservationDTO= new ReservationDTO();

        System.out.println("Reserve Car");
        ListDrivers();

        reservationDTO.setDriver_id(getIntFromUser("Driver id?"));

        String pickupDate = getDatesFromUser("Pickup date?");
        String dropOffDate = getDatesFromUser("Dropoff date?");
        System.out.println("Available cars");
        getAvailableCars(pickupDate, dropOffDate);

        reservationDTO.setCar_id(getIntFromUser("Car id?"));
        reservationDTO.setPickupDate(formatDate(pickupDate));
        reservationDTO.setDropOffDate(formatDate(dropOffDate));

        //Request
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ReservationDTO> requestEntity = new HttpEntity<>(reservationDTO, headers);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + endpoint_reserve_car);

        ResponseEntity<Reservation> response = restTemplate.postForEntity(builder.toUriString(), requestEntity, Reservation.class);

        // Handle the response as needed
        if (response.getStatusCode().is2xxSuccessful()) {
            Reservation responseBody = response.getBody();
            System.out.println("API Response: " + responseBody);
        } else {
            System.err.println("API request failed with status code: " + response.getStatusCodeValue());
        }

    }

    public void ReserveHistory(){
        System.out.println("Reserve History");
        String starDate = getDatesFromUser("Start date?");
        String endDate = getDatesFromUser("End date?");

        ListAllCars();
        int car_id = getIntFromUser("Car id?");

        //Request
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + endpoint_reservation_history)
                .queryParam("StartDate", starDate)
                .queryParam("EndDate", endDate)
                .queryParam("car_id", car_id);

        // Make the GET request and retrieve the response
        ResponseEntity<Reservation []> response = restTemplate.getForEntity(builder.toUriString(), Reservation[].class);

        // Handle the response as needed
        if (response.getStatusCode().is2xxSuccessful()) {
            Reservation [] responseBody = response.getBody();

            assert responseBody != null;
            for (Reservation reservation : responseBody){
                System.out.println(reservation.toString());
            }

        } else {
            System.err.println("API request failed with status code: " + response.getStatusCodeValue());
        }

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

    public void addDriver(){

        System.out.println("Add new driver");
        Driver driver = new Driver();

        driver.setName(getStringFromUser("Driver name?"));
        driver.setContact(getStringFromUser("Driver contact?"));
        driver.setLicenseNumber(getStringFromUser("Driver licenseNumber?"));
        driver.setDriver_Id(null);

        //Request
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Driver> requestEntity = new HttpEntity<>(driver, headers);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + endpoint_add_driver);

        ResponseEntity<Driver> response = restTemplate.postForEntity(builder.toUriString(), requestEntity, Driver.class);

        // Handle the response as needed
        if (response.getStatusCode().is2xxSuccessful()) {
            Driver responseBody = response.getBody();
            System.out.println("API Response: " + responseBody);
        } else {
            System.err.println("API request failed with status code: " + response.getStatusCodeValue());
        }

    }
    public void ListDrivers(){
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + endpoint_get_drivers);

        // Make the GET request and retrieve the response
        ResponseEntity<Driver []> response = restTemplate.getForEntity(builder.toUriString(), Driver[].class);

        // Handle the response as needed
        if (response.getStatusCode().is2xxSuccessful()) {
            Driver[] responseBody = response.getBody();
            System.out.println("Drivers in the system:");
            for (Driver driver : responseBody){
                System.out.println(driver.toString());
            }
        } else {
            System.err.println("API request failed with status code: " + response.getStatusCodeValue());
        }
    }

    public String getStringFromUser(String userQuestion){

        String userInput;

        System.out.println(userQuestion);
        System.out.print(">");
        userInput = scanner.nextLine();
        return userInput;
    }

    public int getIntFromUser(String userQuestion){

        int userInput;

        System.out.println(userQuestion);
        System.out.print(">");
        userInput = scanner.nextInt();
        scanner.nextLine();
        return userInput;
    }

    public java.sql.Timestamp formatDate(String dateToFormat){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm");

        Date date = null;
        try {
            date = dateFormat.parse(dateToFormat);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return new Timestamp(date.getTime());
    }

}
