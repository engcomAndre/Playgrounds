package com.andre.tasks.business;

import android.content.Context;

import com.andre.tasks.constants.TaskConstants;
import com.andre.tasks.entities.APIResponse;
import com.andre.tasks.entities.FullParameters;
import com.andre.tasks.entities.HeaderEntity;
import com.andre.tasks.infra.SecurityPreferences;
import com.andre.tasks.infra.URLBuilder;
import com.andre.tasks.infra.operation.OperationResult;
import com.andre.tasks.repository.api.ExternalRepository;
import com.andre.tasks.utils.FormatUrlParameters;
import com.google.gson.Gson;

import java.util.AbstractMap;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Random;

public class PersonBusiness extends BaseBusiness {

    private ExternalRepository mExternalRepository;
    private Context mContext;

    public PersonBusiness(Context context) {
        super(context);
        this.mExternalRepository = new ExternalRepository(context);
        this.mContext = context;
    }

    public OperationResult<Boolean> create(String name, String email, String password) {
        OperationResult<Boolean> result = new OperationResult<>();

        try {

            URLBuilder builder = new URLBuilder(TaskConstants.ENDPOINT.ROOT);
            builder.addResource(TaskConstants.ENDPOINT.AUTHENTICATION_CREATE);

            AbstractMap<String, String> params = new HashMap<>();
            params.put(TaskConstants.API_PARAMETER.NAME, name);
            params.put(TaskConstants.API_PARAMETER.EMAIL, email);
            params.put(TaskConstants.API_PARAMETER.PASSWORD, password);

            params.put(TaskConstants.API_PARAMETER.RECEIVE_NEWS, FormatUrlParameters.formatBoolean(true));

            FullParameters fullParameters = new FullParameters(TaskConstants.OPERATION_METHOD.POST, builder.getUrl(), null,(HashMap) params);

            APIResponse response =  this.mExternalRepository.execute(fullParameters);

            if(response.statusCode == TaskConstants.STATUS_CODE.SUCESS){
                HeaderEntity entity = new Gson().fromJson(response.json,HeaderEntity.class);
                SecurityPreferences preferences = new SecurityPreferences(mContext);
                preferences.storeString(TaskConstants.HEADER.PERSON_KEY,entity.personKey);
                preferences.storeString(TaskConstants.HEADER.TOKEN_KEY,entity.token);
                preferences.storeString(TaskConstants.USER.NAME,entity.name);
                preferences.storeString(TaskConstants.USER.EMAIL,email);

                result.setResult(true);

            }else {
                result.setError(super.handleStatusCode(response.statusCode),super.handleErrorMessage(response.json));
            }
        }
        catch (Exception e) {
            result.setError(super.handleExceptionCode(e),super.handleExceptionMessage(e));
        }
        return result;
    }

    public OperationResult<Boolean> login(String email, String password) {
        OperationResult<Boolean> result = new OperationResult<>();

        try {

            URLBuilder builder = new URLBuilder(TaskConstants.ENDPOINT.ROOT);
            builder.addResource(TaskConstants.ENDPOINT.AUTHENTICATION_LOGIN);

            AbstractMap<String, String> params = new HashMap<>();
            params.put(TaskConstants.API_PARAMETER.EMAIL,email);
            params.put(TaskConstants.API_PARAMETER.PASSWORD, password);

            FullParameters fullParameters = new FullParameters(TaskConstants.OPERATION_METHOD.POST, builder.getUrl(), null,(HashMap) params);

            APIResponse response =  this.mExternalRepository.execute(fullParameters);

            if(response.statusCode == TaskConstants.STATUS_CODE.SUCESS){
                HeaderEntity entity = new Gson().fromJson(response.json,HeaderEntity.class);
                SecurityPreferences preferences = new SecurityPreferences(this.mContext);
                preferences.storeString(TaskConstants.HEADER.PERSON_KEY,entity.personKey);
                preferences.storeString(TaskConstants.HEADER.TOKEN_KEY,entity.token);
                preferences.storeString(TaskConstants.USER.NAME,entity.name);
                preferences.storeString(TaskConstants.USER.EMAIL,email);

                result.setResult(true);

            }else {
                result.setError(super.handleStatusCode(response.statusCode),super.handleErrorMessage(response.json));
                String a = "";
            }
        }
        catch (Exception e) {
            result.setError(super.handleExceptionCode(e),super.handleExceptionMessage(e));
        }
        return result;
    }
}
