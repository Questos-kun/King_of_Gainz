package year2.heiafr.ch.king_of_gainz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by samue on 04.06.2018.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "King_of_Gainz.db";

    public static final String PROFILE_KEY_ID = "_id";
    public static final String PROFILE_KEY_AGE_COLUMN = "age";
    public static final String PROFILE_KEY_SEX_COLUMN = "sex";
    public static final String PROFILE_KEY_HEIGHT_COLUMN = "height";
    public static final String PROFILE_KEY_WEIGHT_COLUMN = "weight";
    public static final String PROFILE_KEY_ACTIVITY_COLUMN = "activity";
    public static final String PROFILE_TABLE_NAME = "Profile";

    public static final String DATABASE_CREATE_PROFILE = "create table " + PROFILE_TABLE_NAME + " (" +
            PROFILE_KEY_ID + " integer primary key autoincrement, " +
            PROFILE_KEY_AGE_COLUMN + " integer not null, " +
            PROFILE_KEY_SEX_COLUMN + " text not null, " +
            PROFILE_KEY_HEIGHT_COLUMN + " integer not null, " +
            PROFILE_KEY_WEIGHT_COLUMN + " integer not null, " +
            PROFILE_KEY_ACTIVITY_COLUMN + " text not null);";

    public static final String ACTIVITY_KEY_ID = "_id";
    public static final String ACTIVITY_KEY_DATE_COLUMN = "date"; //date of the activity
    public static final String ACTIVITY_KEY_TYPE_COLUMN = "type"; //meal or workout
    public static final String ACTIVITY_KEY_NAME_COLUMN = "name"; //example: running or eggs
    public static final String ACTIVITY_KEY_QUANTITY_COLUMN = "quantity"; //example : 30minutes or 200 grams
    public static final String ACTIVITY_KEY_CALORIES_COLUMN = "calories";
    public static final String ACTIVITY_KEY_FAT_COLUMN = "fat";
    public static final String ACTIVITY_KEY_PROTEIN_COLUMN = "protein";
    public static final String ACTIVITY_KEY_CARBOHYDRATE_COLUMN = "carbohydrate";
    public static final String ACTIVITY_TABLE_NAME = "Activity";

    public static final String DATABASE_CREATE_ACTIVITY = "create table " + ACTIVITY_TABLE_NAME + " (" +
            ACTIVITY_KEY_ID + " integer primary key autoincrement, " +
            ACTIVITY_KEY_DATE_COLUMN + " text not null, " +
            ACTIVITY_KEY_TYPE_COLUMN + " text not null, " +
            ACTIVITY_KEY_NAME_COLUMN + " text not null, " +
            ACTIVITY_KEY_QUANTITY_COLUMN + " text not null, " +
            ACTIVITY_KEY_CALORIES_COLUMN + " integer not null, " +
            ACTIVITY_KEY_FAT_COLUMN + " real null, " +
            ACTIVITY_KEY_PROTEIN_COLUMN + " real null, " +
            ACTIVITY_KEY_CARBOHYDRATE_COLUMN + " real null);";

    private static SQLiteDatabase db;
    private Cursor cursor;

    public MySQLiteOpenHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_PROFILE);
        db.execSQL(DATABASE_CREATE_ACTIVITY);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                DATABASE_NAME);
        onCreate(db);
    }

    public boolean isProfileSetUp() {
        String columnName = "setUpProfile";
        String query = "SELECT count(*) as " + columnName + " FROM " + PROFILE_TABLE_NAME;
        db = getWritableDatabase();
        cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName)) > 0;
    }

    public Profile getProfile() {
        String query = "SELECT * FROM " + PROFILE_TABLE_NAME + " LIMIT 1";
        cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        Profile currentProfile = new Profile(
                cursor.getInt(cursor.getColumnIndexOrThrow(PROFILE_KEY_ID)),
                cursor.getInt(cursor.getColumnIndexOrThrow(PROFILE_KEY_AGE_COLUMN)),
                cursor.getString(cursor.getColumnIndexOrThrow(PROFILE_KEY_SEX_COLUMN)),
                cursor.getInt(cursor.getColumnIndexOrThrow(PROFILE_KEY_HEIGHT_COLUMN)),
                cursor.getInt(cursor.getColumnIndexOrThrow(PROFILE_KEY_WEIGHT_COLUMN)),
                cursor.getString(cursor.getColumnIndexOrThrow(PROFILE_KEY_ACTIVITY_COLUMN)));
        return currentProfile;
    }

    public void addProfile(int age, String sex, int height, int weight, String activity) {
        ContentValues newValues = new ContentValues();
        newValues.put(PROFILE_KEY_AGE_COLUMN, age);
        newValues.put(PROFILE_KEY_SEX_COLUMN, sex);
        newValues.put(PROFILE_KEY_HEIGHT_COLUMN, height);
        newValues.put(PROFILE_KEY_WEIGHT_COLUMN, weight);
        newValues.put(PROFILE_KEY_ACTIVITY_COLUMN, activity);
        db.insert(PROFILE_TABLE_NAME, null, newValues);
    }

    public void modifyProfile(int id, int age, String sex, int height, int weight, String activity) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(PROFILE_KEY_AGE_COLUMN, age);
        updatedValues.put(PROFILE_KEY_SEX_COLUMN, sex);
        updatedValues.put(PROFILE_KEY_HEIGHT_COLUMN, height);
        updatedValues.put(PROFILE_KEY_WEIGHT_COLUMN, weight);
        updatedValues.put(PROFILE_KEY_ACTIVITY_COLUMN, activity);
        String where = PROFILE_KEY_ID + "=" + id;
        db.update(PROFILE_TABLE_NAME, updatedValues, where, null);
    }

    public ArrayList<Activity> getActivityForDate(String date) {
        ArrayList<Activity> activitiesForTheDay = new ArrayList<>();
        return activitiesForTheDay;
    }
}

