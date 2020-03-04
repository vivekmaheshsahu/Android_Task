package com.example.mytask.database;

import android.os.Environment;

public class DatabaseContract {

    public static final String DATABASE_NAME = "task.sr";
    public static final int DATABASE_VERSION = 10;
    public static final String DB_LOCATION = Environment.getExternalStorageDirectory() + "/TASK";
    public static final String TEXT_TYPE = " TEXT";
    public static final String BLOB_TYPE = " BLOB";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";

    public static final String TABLE_NAME1 = "imagedetails";
    public static final String COLUMN_ID1 = "imageId";
    public static final String COLUMN_IMAGE1 = "image";

    public static final String TABLE_NAME2 = "Info";
    public static final String COLUMN_ID2 = "Id";
    public static final String COLUMN_seed = "seed";
    public static final String COLUMN_result2 = "result";
    public static final String COLUMN_page = "page";
    public static final String COLUMN_version = "version";

    public static final String TABLE_NAME3 = "Candidate_details";
    public static final String COLUMN_ID3 = "Id";
    public static final String COLUMN_title = "title";
    public static final String COLUMN_first = "first_name";
    public static final String COLUMN_last = "last_name";
    public static final String COLUMN_email = "email";
    public static final String COLUMN_city = "city";
    public static final String COLUMN_state = "states";
    public static final String COLUMN_country = "country";
    public static final String COLUMN_dob = "dob";
    public static final String COLUMN_age = "age";
    public static final String COLUMN_phone = "phone";
    public static final String COLUMN_cell = "cell";
    public static final String COLUMN_registration_date = "registration_date";
    public static final String COLUMN_gender = "gender";
    public static final String COLUMN_pic = "picture_url";
    public static final String COLUMN_status = "status";

    public static final class ImageDetails {

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME1 +
                "(" +
                COLUMN_ID1 + INTEGER_TYPE + COMMA_SEP +
                COLUMN_IMAGE1 + BLOB_TYPE + ")";

        public static final String CREATE_TABLE2 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME2 +
                "(" +
                COLUMN_ID2 + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                COLUMN_seed + TEXT_TYPE + COMMA_SEP +
                COLUMN_result2 + INTEGER_TYPE + COMMA_SEP +
                COLUMN_page + INTEGER_TYPE + COMMA_SEP +
                COLUMN_version + INTEGER_TYPE +
                ")";

        public static final String CREATE_TABLE3 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME3 +
                "(" +
                COLUMN_ID3 + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                COLUMN_title + TEXT_TYPE + COMMA_SEP +
                COLUMN_first + TEXT_TYPE + COMMA_SEP +
                COLUMN_last + TEXT_TYPE + COMMA_SEP +
                COLUMN_gender + TEXT_TYPE + COMMA_SEP +
                COLUMN_city + TEXT_TYPE + COMMA_SEP +
                COLUMN_state + TEXT_TYPE + COMMA_SEP +
                COLUMN_country + TEXT_TYPE + COMMA_SEP +
                COLUMN_dob + TEXT_TYPE + COMMA_SEP +
                COLUMN_age + TEXT_TYPE + COMMA_SEP +
                COLUMN_phone + INTEGER_TYPE + COMMA_SEP +
                COLUMN_cell + TEXT_TYPE + COMMA_SEP +
                COLUMN_registration_date + TEXT_TYPE + COMMA_SEP +
                COLUMN_pic + TEXT_TYPE + COMMA_SEP +
                COLUMN_email + TEXT_TYPE + COMMA_SEP +
                COLUMN_status + TEXT_TYPE + COMMA_SEP +
                COLUMN_IMAGE1 + BLOB_TYPE +
                ")";
    }
}
