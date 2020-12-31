package com.kostya.mvpapp.data;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.kostya.mvpapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "table";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "user_table";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    public DbHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public int insertData(String email,String password){
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_EMAIL,email);
        cv.put(COLUMN_PASSWORD,password);

        //if id == -1, значит our data not inserted
        int id = (int) this.getWritableDatabase().insert(TABLE_NAME,null,cv);

        return id;
    }

    public List<User> getAllUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<User> allUsers = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME,null);

        Log.d("jgkerg",cursor.getCount() + " - cursor getcount");

        if ( cursor.getCount() == 0){

            cursor.close();
            return null;
        }

            while (cursor.moveToNext()){
                Log.d("jgkerg","cursor while cycle");
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));

                Log.d("jgkerg", "User " + email + " : " + password + "\\n");

                User user = new User(email,password);


                allUsers.add(user);

            }

            return allUsers;
    }
}
