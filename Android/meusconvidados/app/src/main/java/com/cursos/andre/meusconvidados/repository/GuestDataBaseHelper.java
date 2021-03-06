package com.cursos.andre.meusconvidados.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.cursos.andre.meusconvidados.constants.DatabaseContants;

public class GuestDataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MeusConvidados.db";
    private static final String SQL_CREATE_TABLE_GUEST = "create table "
            + DatabaseContants.GUEST.TABLE_NAME + "("
            + DatabaseContants.GUEST.COLUMNS.ID + " integer primary key autoincrement, "
            + DatabaseContants.GUEST.COLUMNS.NAME +" text , "
            + DatabaseContants.GUEST.COLUMNS.DOCUMENT + " text null, "
            + DatabaseContants.GUEST.COLUMNS.PRESENCE +" integer " + ");";

    private static final String DROP_TABLE_GUEST  = "DROP TABLE IF EXISTS"
            + DatabaseContants.GUEST.TABLE_NAME;


    public GuestDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_GUEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(DROP_TABLE_GUEST);
//        db.execSQL(SQL_CREATE_TABLE_GUEST);
    }
}
