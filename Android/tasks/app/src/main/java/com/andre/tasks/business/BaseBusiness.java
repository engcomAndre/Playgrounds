package com.andre.tasks.business;

import android.content.Context;

import com.andre.tasks.R;
import com.andre.tasks.constants.TaskConstants;
import com.andre.tasks.infra.InternetNotAvailableException;
import com.andre.tasks.infra.SecurityPreferences;
import com.google.gson.Gson;

import java.util.AbstractMap;
import java.util.HashMap;

public class BaseBusiness {
    private Context mContext;

    public BaseBusiness(Context context) {
        this.mContext = context;
    }

    protected int handleExceptionCode(Exception e) {
        if (e instanceof InternetNotAvailableException) {
            return TaskConstants.STATUS_CODE.INTERNET_NOT_AVAILABLE;
        }
        return TaskConstants.STATUS_CODE.INTERNAL_SERVER_ERROR;
    }

    protected String handleExceptionMessage(Exception e) {
        if (e instanceof InternetNotAvailableException) {
            this.mContext.getString(R.string.INTERNET_NOT_AVAILABLE);
        }
        return this.mContext.getString(R.string.UNEXPECTED_ERROR);
    }

    protected String handleErrorMessage(String json) {
        return new Gson().fromJson(json, String.class);
    }

    protected int handleStatusCode(int value) {
        if (value == TaskConstants.STATUS_CODE.FORBIDDEN) {
            return TaskConstants.STATUS_CODE.FORBIDDEN;
        } else if (value == TaskConstants.STATUS_CODE.NOT_FOUND) {
            return TaskConstants.STATUS_CODE.NOT_FOUND;
        } else {
            return TaskConstants.STATUS_CODE.INTERNAL_SERVER_ERROR;
        }
    }


    protected AbstractMap<String,String> getHeaderParams() {
        SecurityPreferences preferences = new SecurityPreferences(this.mContext);
        AbstractMap<String,String>  headerParams = new HashMap<>();
        headerParams.put(TaskConstants.HEADER.PERSON_KEY,preferences.getStoredString(TaskConstants.HEADER.PERSON_KEY));
        headerParams.put(TaskConstants.HEADER.TOKEN_KEY,preferences.getStoredString(TaskConstants.HEADER.TOKEN_KEY));

        return headerParams;

    }
}








