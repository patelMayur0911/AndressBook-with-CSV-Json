package com.AdressBookSystem;

public class PersonalDetails {
    private int IndexNumber;
    private String FirstName;
    private String LastName;
    private String Address;
    private String City;
    private String State;
    private String ZipCode;
    private String PhoneNumber;
    private String Email;



    public int getIndexNumber() {
        return IndexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        IndexNumber = indexNumber;
    }

    public PersonalDetails(String firstName, String lastName, String address, String city, String state, String zipCode, String phoneNumber, String email, int indexNumber) {
        IndexNumber = indexNumber;
        FirstName = firstName;
        LastName = lastName;
        Address = address;
        City = city;
        State = state;
        ZipCode = zipCode;
        PhoneNumber = phoneNumber;
        Email = email;

    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "{" + IndexNumber +
                ", " + FirstName + '\'' +
                ", " + LastName + '\'' +
                ", " + Address + '\'' +
                ", " + City + '\'' +
                ", " + State + '\'' +
                ", " + ZipCode + '\'' +
                ", " + PhoneNumber + '\'' +
                ", " + Email + '\'' +
                '}';
    }
}
