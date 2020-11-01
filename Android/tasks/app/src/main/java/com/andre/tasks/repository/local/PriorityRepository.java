package com.andre.tasks.repository.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.ContactsContract;
import android.support.v7.widget.ActionBarContextView;

import com.andre.tasks.constants.DataBaseConstants;
import com.andre.tasks.constants.TaskConstants;
import com.andre.tasks.entities.PriorityEntity;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

public class PriorityRepository {

    private static PriorityRepository INSTANCE;
    private TaskDataBaseHelper mTaskDataBaseHelper;

    private PriorityRepository(Context context){
        this.mTaskDataBaseHelper = new TaskDataBaseHelper(context);
    }

    public static PriorityRepository getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new PriorityRepository(context);
        }
        return INSTANCE;
    }

    public void insert(List<PriorityEntity> list){
        String sqlQuery = "insert into Priority (_id,description) values(?,?)";
        SQLiteDatabase db = this.mTaskDataBaseHelper.getReadableDatabase();
        db.beginTransaction();

        SQLiteStatement statement = db.compileStatement(sqlQuery);
        for(PriorityEntity entity : list){
            statement.bindLong(1,entity.Id);
            statement.bindString(2,entity.Description);

            statement.executeInsert();
            statement.clearBindings();
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public List<PriorityEntity> getList(){
        List<PriorityEntity> list = new ArrayList<>();
        try{
            Cursor cursor;
            SQLiteDatabase db = mTaskDataBaseHelper.getReadableDatabase();
            cursor = db.rawQuery("select * from Priority ",null);

            if(cursor != null  && cursor.getCount() > 0){
                while (cursor.moveToNext()){
                    PriorityEntity entity = new PriorityEntity();
                    entity.Id = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.PRIORITY.COLUMNS.ID));
                    entity.Description = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.PRIORITY.COLUMNS.DESCRIPTION));

                    list.add(entity);
                }
            }
            if(cursor != null){
                cursor.close();
            }

        }catch (Exception e){
            return list;
        }
        return list;
    }

    public void clearData(){
        SQLiteDatabase db = this.mTaskDataBaseHelper.getReadableDatabase();
        db.delete(DataBaseConstants.PRIORITY.TABLE_NAME,null,null);
        db.close();
    }
}
