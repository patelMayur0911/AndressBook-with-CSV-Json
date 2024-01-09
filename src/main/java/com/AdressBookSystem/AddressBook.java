package com.AdressBookSystem;

import com.google.gson.Gson;
import org.jumpmind.symmetric.csv.CsvReader;
import org.jumpmind.symmetric.csv.CsvWriter;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    private List<PersonalDetails> list1 = new ArrayList<>();
    private Map<String, List<PersonalDetails>> cityPersonMap = new HashMap<>();
    private Map<String, List<PersonalDetails>> statePersonMap = new HashMap<>();

    public void addNewContact(PersonalDetails PDItems) {
        if (list1.stream().anyMatch(contact -> contact.getFirstName().equals(PDItems.getFirstName()))) {
            System.out.println("addNewContact : Duplicate found -> not added : " + PDItems.getFirstName());
        } else {
            list1.add(PDItems);
        }
    }
    private static AddressBook getAddressBook() {
        PersonalDetails p = new PersonalDetails("Dev","Patel","Olpad","Olpad","Gujrat","531620","7388103726","patelmayuraaa@gmail.com",1);
        PersonalDetails p1 = new PersonalDetails("Yash","Sharode","Udhana","Udhana","GJ","402460","1103135819","yashSharode@gmail.com",2);
        PersonalDetails p2 = new PersonalDetails("Akshay","Sushir","Udhana","Udhana","Gujrat","565022","0998701818","Akshaysushir@gmail.com",3);
        PersonalDetails p3 = new PersonalDetails("Vaibhav","Singh","Olpad","Olpad","GJ","242922","8006719208","vaibhavsingh@gmail.com",4);
        PersonalDetails p4 = new PersonalDetails("Karitk","Gohil","Olpad","Olpad","Gujrat","849135","8532437705","KartikGohil@gmail.com",5);
        PersonalDetails p5 = new PersonalDetails("Dev","Patel","Olpad","Olpad","Gujrat","595853","9046782898","patelmayuraaa@gmail.com",6);
        AddressBook B =new AddressBook(

        );
        B.addNewContact(p);
        B.addNewContact(p1);
        B.addNewContact(p2);
        B.addNewContact(p3);
        B.addNewContact(p4);
        B.addNewContact(p5);
        B.displayAddedContact();
        return B;
    }
    public void addMultiContact() {
        Scanner scanner = new Scanner(System.in);
        char userResponse;
        do {
            System.out.println("addMultiContact : Enter details for a new person:");

            System.out.print("First Name: ");
            String firstName = scanner.next();

            System.out.print("Last Name: ");
            String lastName = scanner.next();

            System.out.print("address: ");
            String address = scanner.next();

            System.out.println("city : ");
            String city = scanner.next();

            System.out.println("state : ");
            String state = scanner.next();

            System.out.println("zipCode : ");
            String zipCode = scanner.next();

            System.out.println("phoneNumber : ");
            String phoneNumber = scanner.next();

            System.out.println("email : ");
            String email = scanner.next();

            System.out.println("IndexNumber : ");
            int IndexNumber = Integer.parseInt(scanner.next());

            PersonalDetails newPerson = new PersonalDetails(firstName, lastName, address, city, state, zipCode, phoneNumber, email,IndexNumber);
            list1.add(newPerson);

            System.out.print("Do you want to add another person? (y/n): ");
            userResponse = scanner.next().charAt(0);
        } while (userResponse == 'y' || userResponse == 'Y');
    }

    public void displayAddedContact(){
        if (list1.isEmpty()){
            System.out.println("Address Book is Empty");
        }else{
            System.out.println();
//            System.out.println("\n Personal Details list :");
            for (PersonalDetails personalDetails : list1) {
                System.out.println("displayAddedContact : " + personalDetails);
            }
        }
    }

    public void editFirstName(String input, String output){
        for ( PersonalDetails contact : list1){
            if (contact.getFirstName().toLowerCase().equals(input.toLowerCase())){
                contact.setFirstName(output);
            }
        }
    }
    public void deleteContact(int index){
        list1.remove(index);
    }

    public List<PersonalDetails> searchByCity(String city) {
        return list1.stream()
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<PersonalDetails> searchByState(String state) {
        return list1.stream()
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }
    public void mapPersonsByCity() {
        cityPersonMap = list1.stream()
                .collect(Collectors.groupingBy(PersonalDetails::getCity));
    }

    public void mapPersonsByState() {
        statePersonMap = list1.stream()
                .collect(Collectors.groupingBy(PersonalDetails::getState));
    }

    public void displayPersonsByCity() {
        mapPersonsByCity();
        cityPersonMap.forEach((city, persons) -> {
            System.out.println("displayPersonsByCity : Persons in " + city + ":");
            persons.stream().map(PersonalDetails::getFirstName).forEach(System.out::println);
        });
    }
    public void displayPersonsByState() {
        mapPersonsByState();
        statePersonMap.forEach((state, persons) -> {
            System.out.println("displayPersonsByState : Persons in " + state + " State:");
            persons.stream().map(PersonalDetails::getFirstName).forEach(System.out::println);
        });
    }

    public void writeToCSVFile(String filePath) {
        try  {
            CsvWriter csvWriter = new CsvWriter(filePath);

            String[] headers = {"First Name", "Last Name", "Address", "City", "State", "Zip", "Phone", "Email","IndexNumber"};
            csvWriter.writeRecord(headers);
            for (PersonalDetails personalDetails : list1) {
                String[] data = { personalDetails.getFirstName(), personalDetails.getLastName(),
                        personalDetails.getAddress(), personalDetails.getCity(), personalDetails.getState(),
                        personalDetails.getZipCode(), personalDetails.getPhoneNumber(), personalDetails.getEmail(), String.valueOf(personalDetails.getIndexNumber())};
                csvWriter.writeRecord(data);
            }
            System.out.println("writeToCSVFile : AddressBookData created & updated successfully as CSV");
            csvWriter.close();
        } catch (IOException ex) {
            System.out.println("writeToCSVFile : Error writing to CSV file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void readFromCSVFile(String filePath) {
        try {
            CsvReader csvReader = new CsvReader(filePath);
            csvReader.readHeaders();
            AddressBook addressBookCSVReader = new AddressBook();
            while (csvReader.readRecord()) {
                String firstName = csvReader.get("First Name");
                String lastName = csvReader.get("Last Name");
                String address = csvReader.get("Address");
                String city = csvReader.get("City");
                String state = csvReader.get("State");
                String zipCode = csvReader.get("Zip");
                String phoneNumber = csvReader.get("Phone");
                String email = csvReader.get("Email");
                int IndexNumber = Integer.parseInt(csvReader.get("IndexNumber"));
                PersonalDetails personalDetails  = new PersonalDetails(firstName, lastName, address, city, state, zipCode, phoneNumber, email,IndexNumber);
                addressBookCSVReader.addNewContact(personalDetails);
            }
            csvReader.close();
            System.out.println();
            for(PersonalDetails personalDetails : addressBookCSVReader.list1){
                System.out.println("addressBookCSVReader :" + personalDetails);
            }
        } catch (IOException ex) {
            System.out.println("addressBookCSVReader : Error reading CSV file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void objectToJson (){
        Gson gson = new Gson();
        System.out.println();
        for (PersonalDetails personalDetails : list1){
            String jsonData = gson.toJson(personalDetails);
            System.out.println("objectToJson : "+jsonData);
        }
    }

    public static void main(String[] args) {
        Scanner sysInput= new Scanner(System.in);
        System.out.println("main : Welcome to AddressBook Program");
        System.out.println();
        AddressBook B = getAddressBook();

//        B.addMultiContact();

//        System.out.println("Enter First Name to edit : ");
//        String inputString = sysInput.next();
//        System.out.println("Enter Name :");
//        String editString = sysInput.next();
//
//        B.editFirstName(inputString,editString);
//        System.out.println(B);
//        B.displayAddedContact();
//
//        System.out.println();
//        System.out.println("Enter index Number to delete");
//        int toDelete = sysInput.nextInt();
//
//        B.deleteContact(toDelete-1);
//        B.displayAddedContact();
//
//        System.out.println();
//        System.out.println("Search by City : ");
//        String citySearch = sysInput.next();
//        List<PersonalDetails> personsInCity = B.searchByCity(citySearch);
//        System.out.println("Persons in "+citySearch+" :");
//        personsInCity.forEach(System.out::println);
//
//        System.out.println();
//        System.out.println("Search by State : ");
//        String stateSearch = sysInput.next();
//        List<PersonalDetails> personsInState = B.searchByState(stateSearch);
//        System.out.println("Persons in "+stateSearch+" :");
//        personsInState.forEach(System.out::println);
//
//        System.out.println();
//        System.out.println("Enter City to count  : ");
//        String citySearchForCount = sysInput.next();
//        long CountOfCity = B.list1.stream().filter(city -> city.getCity().equalsIgnoreCase(citySearchForCount)).count();
//        System.out.println("Person Count of "+citySearchForCount+" People : " +  CountOfCity);
//        B.list1.stream().filter(city -> city.getCity().equalsIgnoreCase(citySearchForCount)).forEach(System.out::println);
//
//        System.out.println();
//        System.out.println("Enter State to count : ");
//        String stateSearchForCount = sysInput.next();
//        long CountOfstate = B.list1.stream().filter(state -> state.getState().equalsIgnoreCase(stateSearchForCount)).count();
//        System.out.println("Person Count of "+stateSearchForCount+" State   : " +  CountOfstate);
//
//        System.out.println();
//        System.out.println("Directory BY City");
//        B.displayPersonsByCity();
//
//        System.out.println();
//        System.out.println("Directory By State");
//        B.displayPersonsByState();
//
//        List<PersonalDetails> sortedListByName =
//                B.list1.stream().sorted(Comparator.comparing(PersonalDetails::getFirstName))
//                        .collect(Collectors.toList());
//        System.out.println();
//        System.out.println("Sorted AddressBook by First Name: ");
//        sortedListByName.forEach(System.out::println);
//
//        List<PersonalDetails> sortedListByZip =
//                B.list1.stream().sorted(Comparator.comparing(PersonalDetails::getZipCode))
//                        .collect(Collectors.toList());
//        System.out.println();
//        System.out.println("Sorted AddressBook by Zip Code : ");
//        sortedListByZip.forEach(System.out::println);

//        B.writeToCSVFile("C:\\Users\\Patel Mayur\\PM_GH\\temp\\AddressBookData.csv");
//        B.readFromCSVFile("C:\\Users\\Patel Mayur\\PM_GH\\temp\\AddressBookData.csv");
        B.objectToJson();

        sysInput.close();
    }
}