package com.cursos.andre.meusconvidados.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.provider.ContactsContract;
import android.util.Log;

import com.cursos.andre.meusconvidados.constants.DatabaseContants;
import com.cursos.andre.meusconvidados.constants.GuestConstants;
import com.cursos.andre.meusconvidados.entities.GuestCount;
import com.cursos.andre.meusconvidados.entities.GuestEntity;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

public class GuestRepository {

    private static GuestRepository INSTANCE;
    private GuestDataBaseHelper mGuestDataBaseHelper;

    private GuestRepository(Context context) {
        mGuestDataBaseHelper = new GuestDataBaseHelper(context);
    }

    public static synchronized GuestRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new GuestRepository(context);
        }
        return INSTANCE;
    }

    public Boolean insert(GuestEntity guestEntity) {
        try {
            SQLiteDatabase sqLiteDatabase = this.mGuestDataBaseHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseContants.GUEST.COLUMNS.NAME, guestEntity.getName());
            contentValues.put(DatabaseContants.GUEST.COLUMNS.PRESENCE, guestEntity.getConfirmed());
            contentValues.put(DatabaseContants.GUEST.COLUMNS.DOCUMENT, guestEntity.getDocument());

            sqLiteDatabase.insert(DatabaseContants.GUEST.TABLE_NAME, null, contentValues);

            return true;
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());

            return false;
        }
    }


    public boolean update(GuestEntity guestEntity) {
        try {
            SQLiteDatabase sqLiteDatabase = this.mGuestDataBaseHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseContants.GUEST.COLUMNS.NAME, guestEntity.getName());
            contentValues.put(DatabaseContants.GUEST.COLUMNS.PRESENCE, guestEntity.getConfirmed());
            contentValues.put(DatabaseContants.GUEST.COLUMNS.DOCUMENT, guestEntity.getDocument());

            String selection = DatabaseContants.GUEST.COLUMNS.ID + " = ?";
            String[] selectionArgs = {String.valueOf(guestEntity.getId())};

            sqLiteDatabase.update(DatabaseContants.GUEST.TABLE_NAME, contentValues, selection, selectionArgs);

            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public List<GuestEntity> getGuestsByQuery(String query) {
        List<GuestEntity> guestEntities = new ArrayList<>();

        try {
            SQLiteDatabase sqLiteDatabase = mGuestDataBaseHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);

            if (cursor != null && cursor.getCount() != 0) {
                while (cursor.moveToNext()) {
                    GuestEntity guestEntity = new GuestEntity();
                    guestEntity.setId(cursor.getInt(cursor.getColumnIndex(DatabaseContants.GUEST.COLUMNS.ID)));
                    guestEntity.setName(cursor.getString(cursor.getColumnIndex(DatabaseContants.GUEST.COLUMNS.NAME)));
                    guestEntity.setConfirmed(cursor.getInt(cursor.getColumnIndex(DatabaseContants.GUEST.COLUMNS.PRESENCE)));


                    guestEntities.add(guestEntity);
                }
            }
            if (cursor != null) {
                cursor.close();
            }

        } catch (Exception e) {
            return guestEntities;
        }
        return guestEntities;

    }

    public Boolean remove(int id) {
        try {
            SQLiteDatabase sqLiteDatabase = this.mGuestDataBaseHelper.getWritableDatabase();

            String whereClause = DatabaseContants.GUEST.COLUMNS.ID + "= ? ";
            String[] whereArgs = {String.valueOf(id)};

            sqLiteDatabase.delete(DatabaseContants.GUEST.TABLE_NAME, whereClause, whereArgs);

            return true;

        } catch (Exception e) {
            return false;
        }
    }


    public GuestEntity load(int mGuestID) {
        GuestEntity guestEntity = new GuestEntity();
        try {
            SQLiteDatabase sqLiteDatabase = mGuestDataBaseHelper.getReadableDatabase();

            String[] projection = {
                    DatabaseContants.GUEST.COLUMNS.ID,
                    DatabaseContants.GUEST.COLUMNS.NAME,
                    DatabaseContants.GUEST.COLUMNS.PRESENCE,
                    DatabaseContants.GUEST.COLUMNS.DOCUMENT,

            };

            String selection = DatabaseContants.GUEST.COLUMNS.ID + " = ?";
            String[] selectionArgs = {String.valueOf(mGuestID)};

            Cursor cursor = sqLiteDatabase.query(DatabaseContants.GUEST.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                guestEntity.setId(cursor.getInt(cursor.getColumnIndex(DatabaseContants.GUEST.COLUMNS.ID)));
                guestEntity.setName(cursor.getString(cursor.getColumnIndex(DatabaseContants.GUEST.COLUMNS.NAME)));
                guestEntity.setDocument(cursor.getString(cursor.getColumnIndex(DatabaseContants.GUEST.COLUMNS.DOCUMENT)));
                guestEntity.setConfirmed(cursor.getInt(cursor.getColumnIndex(DatabaseContants.GUEST.COLUMNS.PRESENCE)));
            }

            if (cursor != null) {
                cursor.close();
            }
            return guestEntity;

        } catch (Exception e) {
            return guestEntity;
        }
    }

    public GuestCount loadDashBoard() {

        GuestCount guestCount = new GuestCount(0, 0, 0);
        Cursor cursorPresent, cursorAbsent, cursorAllInvited;
        try {
            SQLiteDatabase sqLiteDatabase = this.mGuestDataBaseHelper.getReadableDatabase();

            String queryPresence = "select count(*) from " +
                    DatabaseContants.GUEST.TABLE_NAME + " where " +
                    DatabaseContants.GUEST.COLUMNS.PRESENCE + " = " +
                    GuestConstants.CONFIRMATION.PRESENT;

            String queryAbsent = "select count(*) from " +
                    DatabaseContants.GUEST.TABLE_NAME + " where " +
                    DatabaseContants.GUEST.COLUMNS.PRESENCE + " = " +
                    GuestConstants.CONFIRMATION.ABSENT;

            String queryAllInvited = "select count(*) from " +
                    DatabaseContants.GUEST.TABLE_NAME;

            cursorPresent = sqLiteDatabase.rawQuery(queryPresence, null);
            cursorAbsent = sqLiteDatabase.rawQuery(queryAbsent, null);
            cursorAllInvited = sqLiteDatabase.rawQuery(queryAllInvited, null);

            if (cursorPresent != null && cursorPresent.getCount() > 0) {
                cursorPresent.moveToFirst();
                guestCount.setPresentCount(cursorPresent.getInt(0));
            }

            if (cursorAbsent != null && cursorAbsent.getCount() > 0) {
                cursorAbsent.moveToFirst();
                guestCount.setAbsentCount(cursorAbsent.getInt(0));
            }

            if (cursorAllInvited != null && cursorAllInvited.getCount() > 0) {
                cursorAllInvited.moveToFirst();
                guestCount.setAllInvitedCount(cursorAllInvited.getInt(0));
            }

            if (cursorPresent != null) {
                cursorPresent.close();
            }
            if (cursorAbsent != null) {
                cursorAbsent.close();
            }
            if (cursorAllInvited != null) {
                cursorAllInvited.close();
            }
            return guestCount;

        } catch (Exception e) {
            return guestCount;
        }
    }
}












