package com.firebug.scootr;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.firebug.scootr.Views.CustomEditText;
import com.firebug.scootr.Views.MyTextView;
import com.firebug.scootr.utility.Constant;
import com.firebug.scootr.utility.DialogMessage;
import com.firebug.scootr.utility.Utility;
import com.rey.material.app.Dialog;
import com.rey.material.widget.ProgressView;
import com.rey.material.widget.TextView;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by PremSai on 1/30/2016.
 */
public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    Context appContext;
    Dialog.Builder builder = null;
    public android.app.Dialog dialog;
    Typeface tf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();


        ((MyTextView) findViewById(R.id.btn_sign_in)).setOnClickListener(this);
    }


    private void init() {

        appContext = this;
        tf = Typeface.createFromAsset(getAssets(), "fonts/NEXA LIGHT.OTF");
        ((MyTextView) findViewById(R.id.btn_sign_in)).setTypeface(tf);
        ((MyTextView) findViewById(R.id.btn_sign_up)).setTypeface(tf);
        ((MyTextView) findViewById(R.id.btn_sign_in)).setOnClickListener(this);
        ((MyTextView) findViewById(R.id.btn_sign_up)).setOnClickListener(this);
    }


    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_sign_in:
                String email = ((CustomEditText) findViewById(R.id.email)).getText().toString().trim();
                String password = ((CustomEditText) findViewById(R.id.password)).getText().toString().trim();
                if (email.isEmpty())
                    showErrorMsg("Please enter a valid email address");
                else if (password.isEmpty())
                    showErrorMsg("Please enter the password");
                else if (!emailValidator(email))
                    showErrorMsg(" Invalid email address");
                else {
                    if (Utility.isConnectingToInternet(appContext))
                        new SignIn().execute(email, password);
                    else
                        showErrorMsg("Please check your internet connection");
                }
                break;
        }
    }


    public void showErrorMsg(String msg) {

        DialogMessage.showPopupMessage(msg, appContext);
    }


    public class SignIn extends AsyncTask<String, String, String> {
        int code;
        String email;
        android.app.Dialog prgDialog;

        @Override
        protected void onPreExecute() {

            ((ProgressView)findViewById(R.id.progress_pv_circular_inout)).start();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {


            String result = null;
            String url = null;

            url = Constant.BASE_URL_RIDE + "sign_in?";


            try {
                ArrayList<NameValuePair>params1 = new ArrayList<NameValuePair>();
                params1.add(new BasicNameValuePair("email",params[0]));
                params1.add(new BasicNameValuePair("password",params[1]));
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost request = new HttpPost(url.replace("?",""));
                request.setEntity(new UrlEncodedFormEntity(params1));
                HttpResponse httpResponse = httpClient.execute(request);
                code = httpResponse.getStatusLine().getStatusCode();

                InputStream is = httpResponse.getEntity().getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }

                is.close();
                result = sb.toString();
                Log.d("value of response is :",""+result);
                //get all headers
                Header[] headers = httpResponse.getAllHeaders();
                for (Header header : headers) {
                    System.out.println("Key : " + header.getName()
                            + " ,Value : " + header.getValue());
                    if(header.getName().equalsIgnoreCase("Access-Token"))
                    {
                        Log.d("key ",header.getValue());
                        Utility.setSharedPreference(appContext, Constant.Access_Token, header.getValue().toString());
                    }
                    if(header.getName().equalsIgnoreCase("Client"))
                    {
                        Log.d("key ",header.getValue());
                        Utility.setSharedPreference(appContext, Constant.Client, header.getValue().toString());
                    }
                    if(header.getName().equalsIgnoreCase("Expiry"))
                    {
                        Log.d("key ",header.getValue());
                        Utility.setSharedPreference(appContext, Constant.Expiry, header.getValue().toString());
                    }
                    if(header.getName().equalsIgnoreCase("Token-Type"))
                    {
                        Log.d("key ",header.getValue());
                        Utility.setSharedPreference(appContext, Constant.Token_Type, header.getValue().toString());
                    }
                    if(header.getName().equalsIgnoreCase("Uid"))
                    {
                        Log.d("key ",header.getValue());
                        Utility.setSharedPreference(appContext, Constant.Uid, header.getValue().toString());
                    }

                }

            }catch (Exception e ){

            }


      /*      RestClient client = new RestClient(url);
            client.AddParam("email", params[0]);
            client.AddParam("password", params[1]);
            email=params[0];
            try {

                client.ExecutePost();
                result = client.getResponse();
                code = client.getResponseCode();

                Log.e("code......", code + "");
                Log.e("result......", result + "");

            } catch (Exception e) {
                e.printStackTrace();
            }*/
            return result;
        }


        @Override
        protected void onPostExecute(String s) {

//            ((ProgressView)findViewById(R.id.progress_pv_circular_inout)).stop();

//            prgDialog.hide();

//            if (prgDialog!=null)
//                prgDialog.hide();

            if (s!=null) {
                try {


                    JSONObject json = new JSONObject(s);

                /*
                                  {
                  "data": {
                    "id": 1,
                    "provider": "email",
                    "uid": "vaibhavsolanki444@gmail.com",
                    "name": "vaibhav solanki",
                    "nickname": null,
                    "image": null,
                    "email": "vaibhavsolanki444@gmail.com",
                    "mobile_number": "9770069896",
                    "city": "indore"
                  }
                }
                */

                    if (code == 401) {

                        ((ProgressView)findViewById(R.id.progress_pv_circular_inout)).stop();
                        showErrorMsg(json.getJSONArray("errors").get(0).toString());

                    } else if(code==200){

                        JSONObject data = json.getJSONObject("data");

                        Utility.setSharedPreference(appContext, Constant.USER_ID, data.getString("id"));
                        Utility.setSharedPreference(appContext, Constant.USER_PROVIDER, data.getString("provider"));
                        Utility.setSharedPreference(appContext, Constant.USER_UID, data.getString("uid"));
                        Utility.setSharedPreference(appContext, Constant.USER_NAME, data.getString("name"));
                        Utility.setSharedPreference(appContext, Constant.USER_MOBILE, data.getString("mobile_number"));
                        Utility.setSharedPreference(appContext, Constant.USER_EMAIL, data.getString("email"));
                        Utility.setSharedPreference(appContext, Constant.USER_EMAIL, data.getString("city"));

                        Utility.setSharedPreference(appContext, Constant.is_login, "yes");

                                Intent  intent = new Intent(new Intent(appContext, NavigationHome.class));
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

                    }else{

                        ((ProgressView)findViewById(R.id.progress_pv_circular_inout)).stop();
                        showErrorMsg("Error "+code+ " please try again!");

                    }


                } catch (Exception e) {
                    e.printStackTrace();

                    ((ProgressView)findViewById(R.id.progress_pv_circular_inout)).stop();
                    DialogMessage.showPopupMessage("Please check your internet connection", appContext);
//                    Toast.makeText(appContext, "Please check your internet connection", Toast.LENGTH_LONG).show();
                }
            } else {

                ((ProgressView)findViewById(R.id.progress_pv_circular_inout)).stop();
                DialogMessage.showPopupMessage("Please check your internet connection", appContext);
//                Toast.makeText(appContext, "Please check your internet connection", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(s);
        }

    }
}


