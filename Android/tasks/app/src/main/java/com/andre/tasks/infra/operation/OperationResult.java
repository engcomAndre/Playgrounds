package com.andre.tasks.infra.operation;

public class OperationResult<T> {
    public static final int NO_ERROR = -1;
    private int mError = NO_ERROR;
    public String mErrorMessage = "";
    private T anonimousType;


    public int getError(){
        return mError;
    }

    public String getErrorMessage(){
        return mErrorMessage;
    }

    public void setError(int error,String errorMessage) {
        this.mError = error;
        this.mErrorMessage = errorMessage;
    }

    public T getResult(){
        return anonimousType;
    }

    public void setResult(T anonimousType){
        this.anonimousType = anonimousType;

    }
}
