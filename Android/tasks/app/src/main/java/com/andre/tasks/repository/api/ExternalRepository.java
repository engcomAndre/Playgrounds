package com.andre.tasks.repository.api;

import android.content.Context;

import com.andre.tasks.constants.TaskConstants;
import com.andre.tasks.entities.APIResponse;
import com.andre.tasks.entities.FullParameters;
import com.andre.tasks.infra.InternetNotAvailableException;
import com.andre.tasks.utils.NetworkUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;


public class ExternalRepository {
    private Context mContext;

    public ExternalRepository(Context context) {
        this.mContext = context;
    }

    public APIResponse execute(FullParameters parameters) throws InternetNotAvailableException {
        if(!NetworkUtils.isConnectionAvailable(this.mContext)){
            throw new InternetNotAvailableException();
        }
        APIResponse response;
        InputStream inputStream;
        HttpURLConnection conn;

        try {
            URL url;
            if (parameters.method == TaskConstants.OPERATION_METHOD.GET) {
                url = new URL(parameters.url + getQuery(parameters.parameters, parameters.method));

            } else {
                url = new URL(parameters.url);
            }
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(100000);
            conn.setConnectTimeout(150000);
            conn.setRequestMethod(parameters.method);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(false);

            if (parameters.headerParameters != null) {
                Iterator it = parameters.headerParameters.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    conn.setRequestProperty(pair.getKey().toString(), pair.getValue().toString());
                    it.remove();
                }
            }


            if(parameters.method != TaskConstants.OPERATION_METHOD.GET){
                String query = getQuery(parameters.parameters,parameters.method);
                byte[] postDataBytes = query.getBytes("UTF-8");
                int postDataBytesLenght = postDataBytes.length;

                conn.setRequestProperty("Content-Length",Integer.toString(postDataBytesLenght));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);
            }

            conn.connect();

            if (conn.getResponseCode() == TaskConstants.STATUS_CODE.SUCESS) {
                inputStream = conn.getInputStream();
                response = new APIResponse(getStringFromInputStream(inputStream), conn.getResponseCode());
            }
            else {
                inputStream = conn.getErrorStream();
                response = new APIResponse(getStringFromInputStream(inputStream), conn.getResponseCode());
            }
            inputStream.close();
            conn.disconnect();

        } catch (Exception e) {
            response = new APIResponse("", TaskConstants.STATUS_CODE.NOT_FOUND);
        }
        return response;
    }

    private String getStringFromInputStream(InputStream inputStream) {
        if(inputStream == null) {
            return "";
        }
        BufferedReader br;
        StringBuilder builder = new StringBuilder();

        String line;
        try{
            br = new BufferedReader(new InputStreamReader(inputStream));
            while((line = br.readLine())!= null){
                builder.append(line);
            }
            br.close();
        }catch (Exception e){
            return "";
        }

        return builder.toString();

    }

    private String getQuery(AbstractMap<String, String> parameters, String method) throws UnsupportedEncodingException {
        if (parameters == null) {
            return "";
        }
        boolean first = true;
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> e : parameters.entrySet()) {
            if (first) {
                if (method == TaskConstants.OPERATION_METHOD.GET) {
                    builder.append("?");
                }
                first = false;
            } else {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(e.getKey(), "UTF-8"));
            builder.append("=");
            builder.append(URLEncoder.encode(e.getValue(), "UTF-8"));
        }
        return builder.toString();
    }
}
