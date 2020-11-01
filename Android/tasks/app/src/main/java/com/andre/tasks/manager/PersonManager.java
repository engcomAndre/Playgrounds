package com.andre.tasks.manager;

import android.content.Context;
import android.content.OperationApplicationException;
import android.media.audiofx.Visualizer;
import android.os.AsyncTask;

import com.andre.tasks.business.PersonBusiness;
import com.andre.tasks.infra.OperationListener;
import com.andre.tasks.infra.operation.OperationResult;

public class PersonManager {
    private PersonBusiness mPersonBusiness;
    public PersonManager(Context context) {
        this.mPersonBusiness = new PersonBusiness(context);
    }

    public void create(final String name, final String email , final String password, final OperationListener listener){
        AsyncTask<Void,Void,OperationResult<Boolean>> task = new AsyncTask<Void, Void, OperationResult<Boolean>>() {
            @Override
            protected OperationResult<Boolean> doInBackground(Void... voids) {
                return mPersonBusiness.create(name,email,password);
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

    public void login(final String email, final String password, final OperationListener listener) {
        AsyncTask<Void,Void,OperationResult<Boolean>> task = new AsyncTask<Void, Void, OperationResult<Boolean>>() {
            @Override
            protected OperationResult<Boolean> doInBackground(Void... voids) {
                return mPersonBusiness.login(email,password);
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
