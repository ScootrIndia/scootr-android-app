package com.firebug.scootr.utility;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

public class RestClient {

	private MultipartEntity paramsMulty ;
	 private ArrayList<NameValuePair> params;
	    private ArrayList<NameValuePair> headers;

	    private String url;

	    private int responseCode;
	    private String message;

	    private String response;

	    public String getResponse() {
	        return response;
	    }

	    public String getErrorMessage() {
	        return message;
	    }

	    public int getResponseCode() {
	        return responseCode;
	    }

	    public RestClient(String url)
	    {
	        this.url = url;
	        params = new ArrayList<NameValuePair>();
	        headers = new ArrayList<NameValuePair>();
	        paramsMulty = new MultipartEntity();
	    }


	    public void AddParam(String name, String value)
	    {
	        params.add(new BasicNameValuePair(name, value));
	    }

	  public void AddMultiParamString(String name, String value)
	    {
			try {
				paramsMulty.addPart(name, new StringBody(value));
			}catch (Exception e){e.printStackTrace();}
		}
  public void AddMultiParamImage(String name, File value)
	    {

			paramsMulty.addPart(name, new FileBody(value));
	    }

	    public void AddHeader(String name, String value)
	    {
	        headers.add(new BasicNameValuePair(name, value));
	    }

	    public void ExecuteGet() throws Exception
	    {
	    	
	    	//add parameters
            String combinedParams = "";
            if(!params.isEmpty()){
                combinedParams += "?";
                for(NameValuePair p : params)
                {
                    String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(), "UTF-8");
                    if(combinedParams.length() > 1)
                    {
                        combinedParams  +=  "&" + paramString;
                    }
                    else
                    {
                        combinedParams += paramString;
                    }
                }
            }

            HttpGet request = new HttpGet(url + combinedParams);
            
            //add headers
            for(NameValuePair h : headers)
            {
                request.addHeader(h.getName(), h.getValue());
            }

            executeRequest(request, url);
	    	
	    }
	    
	    public void ExecuteDelete() throws Exception
	    {
	    	
	    	//add parameters
            String combinedParams = "";
            if(!params.isEmpty()){
                combinedParams += "?";
                for(NameValuePair p : params)
                {
                    String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(), "UTF-8");
                    if(combinedParams.length() > 1)
                    {
                        combinedParams  +=  "&" + paramString;
                    }
                    else
                    {
                        combinedParams += paramString;
                    }
                }
            }

            HttpDelete request=new HttpDelete(url + combinedParams);

            //add headers
            for(NameValuePair h : headers)
            {
                request.addHeader(h.getName(), h.getValue());
            }

            executeRequest(request, url);
	    	
	    }
	    
	    public void ExecutePost() throws Exception
	    {
	    	 HttpPost request = new HttpPost(url);



	         //add headers
	         for(NameValuePair h : headers)
	         {
	             request.addHeader(h.getName(), h.getValue());
	         }

	         if(!params.isEmpty()){
	        	 Log.d("value of params inside the RestClient", "" + params.toString());
	             request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
	         }
	         Log.d("value of request inside RestClient", "" + request);
	         executeRequest(request, url);
	    }

	public void ExecutePostMultipart() throws Exception
	{
		HttpPost request = new HttpPost(url);

		//add headers
		for(NameValuePair h : headers)
		{
			request.addHeader(h.getName(), h.getValue());
		}

		if(paramsMulty!=null){
			Log.d("value of params inside the RestClient", "" + paramsMulty.toString());
			request.setEntity(paramsMulty);
		}
		Log.d("value of request inside RestClient", "" + request);
		executeRequest(request, url);
	}

	public void ExecutePostJSON(String json) throws Exception
	{
		HttpPost request = new HttpPost(url);

		request.setEntity(new StringEntity(json));
		request.setHeader("Content-Type", "application/json");

		//add headers
		for(NameValuePair h : headers)
		{
			request.addHeader(h.getName(), h.getValue());
		}

		if(!params.isEmpty()){
			Log.d("value of params inside the RestClient", "" + params.toString());
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		}
		Log.d("value of request inside RestClient", "" + request);
		executeRequest(request, url);

	}

	    public void ExecutePut() throws Exception
	    {
	    	//add parameters
            String combinedParams = "";
            if(!params.isEmpty()){
                combinedParams += "?";
                for(NameValuePair p : params)
                {
                    String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(), "UTF-8");
                    if(combinedParams.length() > 1)
                    {
                        combinedParams  +=  "&" + paramString;
                    }
                    else
                    {
                        combinedParams += paramString;
                    }
                }
            }
            
            HttpPut request = new HttpPut(url + combinedParams);
            
            //add headers
            for(NameValuePair h : headers)
            {
                request.addHeader(h.getName(), h.getValue());
            }

            executeRequest(request, url);
	    }

	public void ExecutePutMultipart() throws Exception
	{


		HttpPut request = new HttpPut(url);

		//add headers
		for(NameValuePair h : headers)
		{
			request.addHeader(h.getName(), h.getValue());
		}

		request.setEntity(paramsMulty);

		executeRequest(request, url);


	}


	private void executeRequest(HttpUriRequest request, String url)
	    {
	        HttpClient client = new DefaultHttpClient();

	        HttpResponse httpResponse;

	        try {
	            httpResponse = client.execute(request);
	            responseCode = httpResponse.getStatusLine().getStatusCode();
	            message = httpResponse.getStatusLine().getReasonPhrase();

	            HttpEntity entity = httpResponse.getEntity();

	            if (entity != null) {

	                InputStream instream = entity.getContent();
	                response = convertStreamToString(instream);

	                // Closing the input stream will trigger connection release
	                instream.close();
	            }

	        } catch (ClientProtocolException e)  {
	            client.getConnectionManager().shutdown();
	            e.printStackTrace();
	        } catch (IOException e) {
	            client.getConnectionManager().shutdown();
	            e.printStackTrace();
	        }
	    }
	    
	    
	    

	    private static String convertStreamToString(InputStream is) {

	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        StringBuilder sb = new StringBuilder();

	        String line = null;
	        try {
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                is.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return sb.toString();
	    }

}
