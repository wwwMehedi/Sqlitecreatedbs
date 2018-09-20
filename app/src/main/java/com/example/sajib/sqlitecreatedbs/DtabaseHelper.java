package com.example.sajib.sqlitecreatedbs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DtabaseHelper extends SQLiteOpenHelper {
    private static final String database_Name = "Student.db";
    private static final String table_NAme = "student_deatils";
    private static final String Id = "_id";
    private static final String Name = "name";
    private static final String Age = "age";
    private static final String Gender = "gender";
    private static final int version = 15;
    private Context context;
    private static final String CREATE_TABLE = "CREATE TABLE " + table_NAme + "(" + Id + " INTEGER PRIMARY KEY AUTOINCREMENT," + Name + " VARCHAR(255)," + Age + " VARCHAR(255)," + Gender + " VARCHAR(15));";
    private static final String Drop_TAble = "DROP TABLE IF EXISTS " + table_NAme;
    private static final String SelectAll = "SELECT * FROM  " + table_NAme;

    public DtabaseHelper(Context context) {
        super(context, database_Name, null, 15);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Toast.makeText(context, "oncreted is called", Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);

        } catch (Exception e) {
            Toast.makeText(context, "Exception" + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Toast.makeText(context, "onUpgrade is calledc", Toast.LENGTH_SHORT).show();
            db.execSQL(Drop_TAble);
            onCreate(db);
        } catch (Exception e) {
            Toast.makeText(context, "Exception" + e, Toast.LENGTH_SHORT).show();
        }
    }

    public long InsertData(String name, String age, String gender) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Name, name);
        contentValues.put(Age, age);
        contentValues.put(Gender, gender);
        long rowId = sqLiteDatabase.insert(table_NAme, null, contentValues);
        return rowId;
    }

    public Cursor displayAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SelectAll, null);
        return cursor;
    }

    public boolean upDateData(String id, String name, String age, String gender) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Id, id);
        contentValues.put(Name, name);
        contentValues.put(Age, age);
        contentValues.put(Gender, gender);
        sqLiteDatabase.update(table_NAme, contentValues, Id + " = ?", new String[]{id});
        return true;
    }

    public int deleteData(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       return sqLiteDatabase.delete(table_NAme, Id + " =? ", new String[]{id});

    }

}
