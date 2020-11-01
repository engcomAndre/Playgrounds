package com.andre.tasks.business;

import android.content.Context;

import com.andre.tasks.constants.PriorityCacheConstants;
import com.andre.tasks.constants.TaskConstants;
import com.andre.tasks.entities.APIResponse;
import com.andre.tasks.entities.FullParameters;
import com.andre.tasks.entities.HeaderEntity;
import com.andre.tasks.entities.PriorityEntity;
import com.andre.tasks.infra.SecurityPreferences;
import com.andre.tasks.infra.URLBuilder;
import com.andre.tasks.infra.operation.OperationResult;
import com.andre.tasks.repository.api.ExternalRepository;
import com.andre.tasks.repository.local.PriorityRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.*;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class PriorityBusiness extends BaseBusiness{

    private ExternalRepository mExternalRepository;
    private PriorityRepository mPriorityRepository;
    private Context mContext;

    public PriorityBusiness(Context context) {
        super(context);
        this.mExternalRepository = new ExternalRepository(context);
        this.mPriorityRepository = PriorityRepository.getInstance(context);
        this.mContext = context;
    }

    public OperationResult<Boolean> getList() {
        OperationResult<Boolean> result = new OperationResult<>();

        try {

            URLBuilder builder = new URLBuilder(TaskConstants.ENDPOINT.ROOT);
            builder.addResource(TaskConstants.ENDPOINT.PRIORITY_GET);

            AbstractMap<String,String> headerParams = super.getHeaderParams();

            FullParameters fullParameters = new FullParameters(TaskConstants.OPERATION_METHOD.GET, builder.getUrl(), (HashMap) headerParams,null);

            APIResponse response =  this.mExternalRepository.execute(fullParameters);
            String as = "";

            if(response.statusCode == TaskConstants.STATUS_CODE.SUCESS){
                Type collectionType = new TypeToken<List<PriorityEntity>>(){}.getType();
                List<PriorityEntity> list = new Gson().fromJson(response.json,collectionType);

                //SAVE DATA IN LOCAL DATABASE
                this.mPriorityRepository.clearData();
                this.mPriorityRepository.insert(list);

                //SAVE DATA IN LOCAL CACHE PERSONALIZED STORAGE
                PriorityCacheConstants.setValues(list);


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

    public List<PriorityEntity> getListLocal(){
        return  this.mPriorityRepository.getList();
    }
}
