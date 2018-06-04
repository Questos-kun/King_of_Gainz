package year2.heiafr.ch.king_of_gainz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by samue on 04.06.2018.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String KEY_ID = "_id";
    public static final String KEY_AGE_COLUMN = "age";
    public static final String KEY_SEX_COLUMN = "sex";
    public static final String KEY_HEIGHT_COLUMN = "height";
    public static final String KEY_WEIGHT_COLUMN = "weight";
    public static final String KEY_ACTIVITY_COLUMN = "activity";
    public static final String DATABASE_NAME = "King_of_Gainz.db";
    public static final String TABLE_NAME = "Profile";

    public static final String DATABASE_CREATE_PROFILE = "create table " + TABLE_NAME + " (" +
            KEY_ID + " integer primary key autoincrement, " +
            KEY_AGE_COLUMN + " integer not null, " +
            KEY_SEX_COLUMN + " text not null, " +
            KEY_HEIGHT_COLUMN + " integer not null, " +
            KEY_WEIGHT_COLUMN + " integer not null, " +
            KEY_ACTIVITY_COLUMN + " text not null);";

    private static SQLiteDatabase db;
    private Cursor cursor;

    public MySQLiteOpenHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_PROFILE);
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
        String query = "SELECT count(*) as AS " + columnName + " FROM " + TABLE_NAME;
        cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName)) > 0;
    }

    public Profile getProfile() {
        String query = "SELECT * FROM " + TABLE_NAME + " LIMIT 1";
        cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        Profile currentProfile = new Profile(
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_AGE_COLUMN)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_SEX_COLUMN)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_HEIGHT_COLUMN)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_WEIGHT_COLUMN)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_ACTIVITY_COLUMN)));
        return currentProfile;
    }

    public void addProfile(int age, String sex, int height, int weight, String activity) {
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_AGE_COLUMN, age);
        newValues.put(KEY_SEX_COLUMN, sex);
        newValues.put(KEY_HEIGHT_COLUMN, height);
        newValues.put(KEY_WEIGHT_COLUMN, weight);
        newValues.put(KEY_ACTIVITY_COLUMN, activity);
        db.insert(TABLE_NAME, null, newValues);
    }

    public void modifyProfile(int id, int age, String sex, int height, int weight, String activity) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(KEY_AGE_COLUMN, age);
        updatedValues.put(KEY_SEX_COLUMN, sex);
        updatedValues.put(KEY_HEIGHT_COLUMN, height);
        updatedValues.put(KEY_WEIGHT_COLUMN, weight);
        updatedValues.put(KEY_ACTIVITY_COLUMN, activity);
        String where = KEY_ID + "=" + id;
        db.update(TABLE_NAME, updatedValues, where, null);
    }

    public int getProfileId(int age, String sex, int height, int weight, String activity) {
        int id;
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                KEY_AGE_COLUMN + " = ? AND " + KEY_SEX_COLUMN + " = ? AND " +
                KEY_HEIGHT_COLUMN + " = ? AND " + KEY_HEIGHT_COLUMN + " = ? AND " +
                KEY_ACTIVITY_COLUMN + " = ?";
        cursor = db.rawQuery(query, new String[]{Integer.toString(age), sex,
                Integer.toString(height), Integer.toString(weight), activity});
        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID));
        return id;
    }
}

