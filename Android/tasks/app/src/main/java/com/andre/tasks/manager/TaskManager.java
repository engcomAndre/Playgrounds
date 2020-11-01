package com.andre.tasks.manager;

import android.content.Context;
import android.os.AsyncTask;

import com.andre.tasks.business.PriorityBusiness;
import com.andre.tasks.business.TaskBusiness;
import com.andre.tasks.entities.TaskEntity;
import com.andre.tasks.infra.OperationListener;
import com.andre.tasks.infra.operation.OperationResult;

import java.util.List;

public class TaskManager {

    private TaskBusiness mTaskBusiness;

    public TaskManager(Context context){
        this.mTaskBusiness = new TaskBusiness(context);
    }


    public void getList(final int filter,final OperationListener listener) {
        AsyncTask<Void,Void,OperationResult<List<TaskEntity>>> task = new AsyncTask<Void, Void, OperationResult<List<TaskEntity>>>() {
            @Override
            protected OperationResult<List<TaskEntity>> doInBackground(Void... voids) {
                return mTaskBusiness.getList(filter);
            }

            @Override
            protected void onPostExecute(OperationResult<List<TaskEntity>> result) {
                int error  = result.getError();
                if(error != OperationResult.NO_ERROR){
                    listener.onError(error,result.getErrorMessage());
                }
                else {
                    listener.onSucess(result.getResult());
                }
            }
        };
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void insert(final TaskEntity entity,final OperationListener listener) {
        AsyncTask<Void,Void,OperationResult<Boolean>> task = new AsyncTask<Void, Void, OperationResult<Boolean>>() {
            @Override
            protected OperationResult<Boolean> doInBackground(Void... voids) {
                return mTaskBusiness.insert(entity);
            }

            @Override
            protected void onPostExecute(OperationResult<Boolean> result) {
                int error  = result.getError();
                if(error != OperationResult.NO_ERROR){
                    listener.onError(error,result.getErrorMessage());
                }
                else {
                    listener.onSucess(result.getResult());
                }
            }
        };
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void get(final int id ,final OperationListener listener) {
        AsyncTask<Void,Void,OperationResult<TaskEntity>> task = new AsyncTask<Void, Void, OperationResult<TaskEntity>>() {
            @Override
            protected OperationResult<TaskEntity> doInBackground(Void... voids) {
                return mTaskBusiness.get(id);
            }

            @Override
            protected void onPostExecute(OperationResult<TaskEntity> result) {
                int error  = result.getError();
                if(error != OperationResult.NO_ERROR){
                    listener.onError(error,result.getErrorMessage());
                }
                else {
                    listener.onSucess(result.getResult());
                }
            }
        };
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void update(final TaskEntity entity,final OperationListener listener) {
        AsyncTask<Void,Void,OperationResult<Boolean>> task = new AsyncTask<Void, Void, OperationResult<Boolean>>() {
            @Override
            protected OperationResult<Boolean> doInBackground(Void... voids) {
                return mTaskBusiness.update(entity);
            }

            @Override
            protected void onPostExecute(OperationResult<Boolean> result) {
                int error  = result.getError();
                if(error != OperationResult.NO_ERROR){
                    listener.onError(error,result.getErrorMessage());
                }
                else {
                    listener.onSucess(result.getResult());
                }
            }
        };
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void complete(final int id,final Boolean complete ,final OperationListener listener) {
        AsyncTask<Void,Void,OperationResult<Boolean>> task = new AsyncTask<Void, Void, OperationResult<Boolean>>() {
            @Override
            protected OperationResult<Boolean> doInBackground(Void... voids) {
                return mTaskBusiness.complete(complete,id);
            }

            @Override
            protected void onPostExecute(OperationResult<Boolean> result) {
                int error  = result.getError();
                if(error != OperationResult.NO_ERROR){
                    listener.onError(error,result.getErrorMessage());
                }
                else {
                    listener.onSucess(result.getResult());
                }
            }
        };
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void delete(final int id,final OperationListener listener) {
        AsyncTask<Void,Void,OperationResult<Boolean>> task = new AsyncTask<Void, Void, OperationResult<Boolean>>() {
            @Override
            protected OperationResult<Boolean> doInBackground(Void... voids) {
                return mTaskBusiness.delete(id);
            }

            @Override
            protected void onPostExecute(OperationResult<Boolean> result) {
                int error  = result.getError();
                if(error != OperationResult.NO_ERROR){
                    listener.onError(error,result.getErrorMessage());
                }
                else {
                    listener.onSucess(result.getResult());
                }
            }
        };
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


}
