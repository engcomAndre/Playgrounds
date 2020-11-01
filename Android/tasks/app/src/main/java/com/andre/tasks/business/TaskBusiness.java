package com.andre.tasks.business;

import android.content.Context;

import com.andre.tasks.constants.DataBaseConstants;
import com.andre.tasks.constants.TaskConstants;
import com.andre.tasks.entities.APIResponse;
import com.andre.tasks.entities.FullParameters;
import com.andre.tasks.entities.PriorityEntity;
import com.andre.tasks.entities.TaskEntity;
import com.andre.tasks.infra.URLBuilder;
import com.andre.tasks.infra.operation.OperationResult;
import com.andre.tasks.repository.api.ExternalRepository;
import com.andre.tasks.repository.local.PriorityRepository;
import com.andre.tasks.utils.FormatUrlParameters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.Format;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;

public class TaskBusiness extends BaseBusiness {

    private ExternalRepository mExternalRepository;

    public TaskBusiness(Context context) {
        super(context);
        this.mExternalRepository = new ExternalRepository(context);
    }

    public OperationResult<Boolean> insert(TaskEntity entity) {
        OperationResult<Boolean> result = new OperationResult<>();

        try {

            URLBuilder builder = new URLBuilder(TaskConstants.ENDPOINT.ROOT);
            builder.addResource(TaskConstants.ENDPOINT.TASK_INSERT);

            AbstractMap<String, String> headerParams = super.getHeaderParams();

            AbstractMap<String, String> params = new HashMap<>();
            params.put(TaskConstants.API_PARAMETER.PRIORITYID, String.valueOf(entity.PriorityId));
            params.put(TaskConstants.API_PARAMETER.DUEDATE, FormatUrlParameters.formatDate(entity.DueDate));
            params.put(TaskConstants.API_PARAMETER.DESCRIPTION, entity.Description);
            params.put(TaskConstants.API_PARAMETER.COMPLETE, FormatUrlParameters.formatBoolean(entity.Complete));

            FullParameters fullParameters = new FullParameters(TaskConstants.OPERATION_METHOD.POST, builder.getUrl(), (HashMap) headerParams, (HashMap) params);

            APIResponse response = this.mExternalRepository.execute(fullParameters);
            String as = "";

            if (response.statusCode == TaskConstants.STATUS_CODE.SUCESS) {
                result.setResult(new Gson().fromJson(response.json, Boolean.class));
            } else {
                result.setError(super.handleStatusCode(response.statusCode), super.handleErrorMessage(response.json));
            }
        } catch (Exception e) {
            result.setError(super.handleExceptionCode(e), super.handleExceptionMessage(e));
        }
        return result;
    }

    public OperationResult<List<TaskEntity>> getList(int filter) {
        OperationResult<List<TaskEntity>> result = new OperationResult<>();

        try {

            URLBuilder builder = new URLBuilder(TaskConstants.ENDPOINT.ROOT);

            switch (filter) {
                case TaskConstants.TASK_FILTERS.NO_FILTER: {
                    builder.addResource(TaskConstants.ENDPOINT.TASK_GET_LIST_NO_FILTER);
                    break;
                }
                case TaskConstants.TASK_FILTERS.OVERDUE: {
                    builder.addResource(TaskConstants.ENDPOINT.TASK_GET_LIST_OVERDUE);
                    break;
                }
                case TaskConstants.TASK_FILTERS.NEXT_7_DAYS: {
                    builder.addResource(TaskConstants.ENDPOINT.TASK_GET_LIST_NEXT_7_DAYS);
                    break;
                }
            }

            AbstractMap<String, String> headerParams = super.getHeaderParams();

            FullParameters fullParameters = new FullParameters(TaskConstants.OPERATION_METHOD.GET, builder.getUrl(), (HashMap) headerParams, null);

            APIResponse response = this.mExternalRepository.execute(fullParameters);

            if (response.statusCode == TaskConstants.STATUS_CODE.SUCESS) {
                Type collectionType = new TypeToken<List<TaskEntity>>() {
                }.getType();
                List<TaskEntity> list = new Gson().fromJson(response.json, collectionType);
                result.setResult(list);
            } else {
                result.setError(super.handleStatusCode(response.statusCode), super.handleErrorMessage(response.json));
            }
        } catch (Exception e) {
            result.setError(super.handleExceptionCode(e), super.handleExceptionMessage(e));
        }
        return result;
    }

    public OperationResult<TaskEntity> get(int id) {
        OperationResult<TaskEntity> result = new OperationResult<>();

        try {

            URLBuilder builder = new URLBuilder(TaskConstants.ENDPOINT.ROOT);
            builder.addResource(TaskConstants.ENDPOINT.TASK_GET_SPECIFIC);
            builder.addResource(String.valueOf(id));

            AbstractMap<String, String> headerParams = super.getHeaderParams();

            FullParameters fullParameters = new FullParameters(TaskConstants.OPERATION_METHOD.GET, builder.getUrl(), (HashMap) headerParams, null);

            APIResponse response = this.mExternalRepository.execute(fullParameters);

            if (response.statusCode == TaskConstants.STATUS_CODE.SUCESS) {
                result.setResult(new Gson().fromJson(response.json, TaskEntity.class));
            } else {
                result.setError(super.handleStatusCode(response.statusCode), super.handleErrorMessage(response.json));
            }
        } catch (Exception e) {
            result.setError(super.handleExceptionCode(e), super.handleExceptionMessage(e));
        }
        return result;
    }

    public OperationResult<Boolean> update(TaskEntity entity) {
        OperationResult<Boolean> result = new OperationResult<>();

        try {

            URLBuilder builder = new URLBuilder(TaskConstants.ENDPOINT.ROOT);
            builder.addResource(TaskConstants.ENDPOINT.TASK_UPDATE);

            AbstractMap<String, String> headerParams = super.getHeaderParams();

            AbstractMap<String, String> params = new HashMap<>();
            params.put(TaskConstants.API_PARAMETER.ID, String.valueOf(entity.Id));
            params.put(TaskConstants.API_PARAMETER.PRIORITYID, String.valueOf(entity.PriorityId));
            params.put(TaskConstants.API_PARAMETER.DUEDATE, FormatUrlParameters.formatDate(entity.DueDate));
            params.put(TaskConstants.API_PARAMETER.DESCRIPTION, entity.Description);
            params.put(TaskConstants.API_PARAMETER.COMPLETE, FormatUrlParameters.formatBoolean(entity.Complete));

            FullParameters fullParameters = new FullParameters(TaskConstants.OPERATION_METHOD.PUT, builder.getUrl(), (HashMap) headerParams, (HashMap) params);

            APIResponse response = this.mExternalRepository.execute(fullParameters);
            String as = "";

            if (response.statusCode == TaskConstants.STATUS_CODE.SUCESS) {
                result.setResult(new Gson().fromJson(response.json, Boolean.class));
            } else {
                result.setError(super.handleStatusCode(response.statusCode), super.handleErrorMessage(response.json));
            }
        } catch (Exception e) {
            result.setError(super.handleExceptionCode(e), super.handleExceptionMessage(e));
        }
        return result;
    }

    public OperationResult<Boolean> complete(Boolean complete, int id) {
        OperationResult<Boolean> result = new OperationResult<>();

        try {

            URLBuilder builder = new URLBuilder(TaskConstants.ENDPOINT.ROOT);

            if (complete) {
                builder.addResource(TaskConstants.ENDPOINT.TASK_COMPLETE);
            } else {
                builder.addResource(TaskConstants.ENDPOINT.TASK_UNCOMPLETE);
            }

            AbstractMap<String, String> headerParams = super.getHeaderParams();

            AbstractMap<String, String> params = new HashMap<>();
            params.put(TaskConstants.API_PARAMETER.ID, String.valueOf(id));

            FullParameters fullParameters = new FullParameters(TaskConstants.OPERATION_METHOD.PUT, builder.getUrl(), (HashMap) headerParams, (HashMap) params);

            APIResponse response = this.mExternalRepository.execute(fullParameters);
            String as = "";

            if (response.statusCode == TaskConstants.STATUS_CODE.SUCESS) {
                result.setResult(new Gson().fromJson(response.json, Boolean.class));
            } else {
                result.setError(super.handleStatusCode(response.statusCode), super.handleErrorMessage(response.json));
            }
        } catch (Exception e) {
            result.setError(super.handleExceptionCode(e), super.handleExceptionMessage(e));
        }
        return result;
    }

    public OperationResult<Boolean> delete(int id) {
        OperationResult<Boolean> result = new OperationResult<>();

        try {

            URLBuilder builder = new URLBuilder(TaskConstants.ENDPOINT.ROOT);
            builder.addResource(TaskConstants.ENDPOINT.TASK_DELETE);

            AbstractMap<String, String> headerParams = super.getHeaderParams();

            AbstractMap<String, String> params = new HashMap<>();
            params.put(TaskConstants.API_PARAMETER.ID, String.valueOf(id));

            FullParameters fullParameters = new FullParameters(TaskConstants.OPERATION_METHOD.DELETE, builder.getUrl(), (HashMap) headerParams, (HashMap) params);

            APIResponse response = this.mExternalRepository.execute(fullParameters);
            String as = "";

            if (response.statusCode == TaskConstants.STATUS_CODE.SUCESS) {
                result.setResult(new Gson().fromJson(response.json, Boolean.class));
            } else {
                result.setError(super.handleStatusCode(response.statusCode), super.handleErrorMessage(response.json));
            }
        } catch (Exception e) {
            result.setError(super.handleExceptionCode(e), super.handleExceptionMessage(e));
        }
        return result;
    }


}
