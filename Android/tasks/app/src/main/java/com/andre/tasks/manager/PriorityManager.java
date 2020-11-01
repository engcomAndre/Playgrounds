package com.andre.tasks.manager;

import android.content.Context;
import android.os.AsyncTask;

import com.andre.tasks.business.PriorityBusiness;
import com.andre.tasks.entities.PriorityEntity;
import com.andre.tasks.infra.OperationListener;
import com.andre.tasks.infra.operation.OperationResult;

import java.util.List;

public class PriorityManager {

    private PriorityBusiness mPriorityBusiness;

    public PriorityManager(Context context){
        this.mPriorityBusiness = new PriorityBusiness(context);
    }

    public void getList(final OperationListener listener) {
        AsyncTask<Void,Void,OperationResult<Boolean>> task = new AsyncTask<Void, Void, OperationResult<Boolean>>() {
            @Override
            protected OperationResult<Boolean> doInBackground(Void... voids) {
                return mPriorityBusiness.getList();
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

    public List<PriorityEntity> getListLocal(){
        return this.mPriorityBusiness.getListLocal();
    }
}
