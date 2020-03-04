package com.example.mytask.data;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

import static com.example.mytask.database.DatabaseContract.COLUMN_ID3;
import static com.example.mytask.database.DatabaseContract.COLUMN_age;
import static com.example.mytask.database.DatabaseContract.COLUMN_cell;
import static com.example.mytask.database.DatabaseContract.COLUMN_city;
import static com.example.mytask.database.DatabaseContract.COLUMN_country;
import static com.example.mytask.database.DatabaseContract.COLUMN_dob;
import static com.example.mytask.database.DatabaseContract.COLUMN_email;
import static com.example.mytask.database.DatabaseContract.COLUMN_first;
import static com.example.mytask.database.DatabaseContract.COLUMN_gender;
import static com.example.mytask.database.DatabaseContract.COLUMN_last;
import static com.example.mytask.database.DatabaseContract.COLUMN_phone;
import static com.example.mytask.database.DatabaseContract.COLUMN_registration_date;
import static com.example.mytask.database.DatabaseContract.COLUMN_state;
import static com.example.mytask.database.DatabaseContract.COLUMN_title;

public class Candidate_details {

    @SerializedName(COLUMN_ID3)
    private String Id;
    @SerializedName(COLUMN_title)
    private String title;
    @SerializedName(COLUMN_first)
    private String first_name;
    @SerializedName(COLUMN_last)
    private String last_name;
    @SerializedName(COLUMN_email)
    private String email;
    @SerializedName(COLUMN_city)
    private String city;
    @SerializedName(COLUMN_state)
    private String state;
    @SerializedName(COLUMN_country)
    private String country;
    @SerializedName(COLUMN_dob)
    private String dob;
    @SerializedName(COLUMN_age)
    private String age;
    @SerializedName(COLUMN_phone)
    private String phone;
    @SerializedName(COLUMN_cell)
    private String cell;
    @SerializedName(COLUMN_registration_date)
    private String registration_date;
    @SerializedName(COLUMN_gender)
    private String gender;
    private byte[] image;
    private String image_url;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Candidate_details{" +
                "Id='" + Id + '\'' +
                ", title='" + title + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", dob='" + dob + '\'' +
                ", age='" + age + '\'' +
                ", phone='" + phone + '\'' +
                ", cell='" + cell + '\'' +
                ", registration_date='" + registration_date + '\'' +
                ", gender='" + gender + '\'' +
                ", image=" + Arrays.toString(image) +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
