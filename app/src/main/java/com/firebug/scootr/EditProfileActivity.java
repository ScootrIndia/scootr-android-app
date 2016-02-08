package com.firebug.scootr;

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
import android.widget.Button;

import com.firebug.scootr.Views.CustomEditText;
import com.firebug.scootr.Views.MyTextView;
import com.firebug.scootr.utility.Constant;
import com.firebug.scootr.utility.DialogMessage;
import com.firebug.scootr.utility.RestClient;
import com.firebug.scootr.utility.Utility;
import com.rey.material.app.Dialog;
import com.rey.material.widget.ProgressView;
import com.rey.material.widget.TextView;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by PremSai on 1/30/2016.
 */
public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {


    private Context appContext;
    Dialog.Builder builder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        appContext = this;
        Typeface tf = Typeface.createFromAsset(appContext.getAssets(), "fonts/NEXA LIGHT.OTF");
        ((TextView) findViewById(R.id.btn_sign_up_merchant)).setTypeface(tf);
        ((TextView) findViewById(R.id.btn_sign_up_merchant)).setOnClickListener(this);

        /*((MyTextView) findViewById(R.id.btn_sign_up_merchant)).setOnClickListener();*/
    }


    public void onClick(View v) {

        String fullname = ((CustomEditText) findViewById(R.id.full_name)).getText().toString().trim();
        String email = ((CustomEditText) findViewById(R.id.email)).getText().toString().trim();
        String phone = ((CustomEditText) findViewById(R.id.phone)).getText().toString().trim();
        String password = ((CustomEditText) findViewById(R.id.password)).getText().toString().trim();
        String con_pass = ((CustomEditText) findViewById(R.id.con_password)).getText().toString().trim();
        String city = ((CustomEditText) findViewById(R.id.city)).getText().toString().trim();

        if (fullname.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || con_pass.isEmpty() || city.isEmpty()) {
            showErrorMsg("Please fill all the fields");
        } else if (!emailValidator(email)) {
            showErrorMsg("Please enter a valid email address");
        } else if (phone.length() < 10) {
            showErrorMsg("Invalid Mobile Number");
        } else if (password.length() < 8) {
            showErrorMsg("Password must be 8 characters or more");
        } else if (!password.equals(con_pass)) {
            showErrorMsg("Password not match ");
        } else {
            if (Utility.isConnectingToInternet(appContext))
                new SignUp().execute(fullname, email, phone, password);
            else
                showErrorMsg("Please check your internet connection");
        }

    }

    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //
    public void showErrorMsg(String msg) {

        DialogMessage.showPopupMessage(msg, appContext);

    }


    public void conformationDialog(String msg) {

        final android.app.Dialog dd = new android.app.Dialog(appContext);

        dd.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dd.setContentView(R.layout.dialog_message);

        ((MyTextView) dd.findViewById(R.id.message)).setText(msg);
        ((Button) dd.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                startActivity(new Intent(appContext, SignInActivity.class));
//                finish();

            }
        });

        dd.show();

       /* Dialog.Builder builder = null;
        builder = new SimpleDialog.Builder(R.style.SimpleDialog){
            @Override
            public void onPositiveActionClicked(DialogFragment fragment) {

                startActivity(new Intent(appContext, com.firebug.cocoapay.Login_Activity.class));
                finish();

                super.onPositiveActionClicked(fragment);
            }

        };

        ((SimpleDialog.Builder)builder).message(msg).positiveAction("Ok");

        DialogFragment fragment = DialogFragment.newInstance(builder);
       fragment.show(getSupportFragmentManager(), null);*/

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(appContext, SignInActivity.class));
        finish();
    }


    public class SignUp extends AsyncTask<String, String, String> {
        int code;
        String email;
//        ProgressDialog prgDialog;

        @Override
        protected void onPreExecute() {
//            prgDialog=new ProgressDialog(appContext);
//            prgDialog.setMessage("Please wait...");
//            prgDialog.show();
            ((ProgressView) findViewById(R.id.progress_pv_circular_inout)).start();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String result = null;
            String url = null;


//            if(Utility.getSharedPreferences(appContext,"user_type").equals("1"))
//            url = Constant.BASE_URL3 + "?";
//            else
            url = Constant.BASE_URL_RIDE + "?";

            //  https:
//cryptic-shelf-9191.herokuapp.com/api/auth/employee?name=Trilok warke
            // &email=vaibhavsolanki444@gmail.com&
            // mobile_number=9770069896&password=ashwin@123&password_confirmation=ashwin@123

            RestClient client = new RestClient(url);

            if (Utility.getSharedPreferences(appContext, "user_type").equals("1"))
                client.AddParam("role", "merchant");
            else
                client.AddParam("role", "employee");

            client.AddParam("name", params[0]);
            client.AddParam("email", params[1]);
            client.AddParam("mobile_number", params[2]);
            client.AddParam("password", params[3]);
            client.AddParam("password_confirmation", params[3]);
            email = params[0];

            client.AddHeader("access-token", Utility.getSharedPreferences(appContext, Constant.Access_Token));
            client.AddHeader("client", Utility.getSharedPreferences(appContext, Constant.Client));
            client.AddHeader("expiry", Utility.getSharedPreferences(appContext, Constant.Expiry));
            client.AddHeader("token-type", Utility.getSharedPreferences(appContext, Constant.Token_Type));
            client.AddHeader("uid", Utility.getSharedPreferences(appContext, Constant.Uid));

            try {

                client.ExecutePut();
                result = client.getResponse();
                code = client.getResponseCode();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }


        @Override
        protected void onPostExecute(String s) {
//            if(prgDialog!=null)
//           prgDialog.hide();

            ((ProgressView) findViewById(R.id.progress_pv_circular_inout)).stop();

            if (s != null) {
                try {
                    Log.e("ssssss", "" + s);
                    JSONObject json = new JSONObject(s);

                    if (code == 403) {

                        showErrorMsg(json.getJSONObject("errors").getJSONArray("full_messages").get(0) + "");

                    } else if (code == 200) {

//                        JSONObject data = json.getJSONObject("data");
                        /*
                        Utility.setSharedPreference(appContext, Constant.USER_ID, data.getString("id"));
                        Utility.setSharedPreference(appContext, Constant.USER_PROVIDER, data.getString("provider"));
                        Utility.setSharedPreference(appContext, Constant.USER_UID, data.getString("uid"));
                        Utility.setSharedPreference(appContext, Constant.USER_NAME, data.getString("name"));
                        Utility.setSharedPreference(appContext, Constant.USER_MOBILE, data.getString("mobile_number"));

                        Utility.setSharedPreference(appContext, Constant.USER_EMAIL, data.getString("email"));*/

                        conformationDialog("Your profile updated successfully");

//                        startActivity(new Intent(appContext, Signup_Activity_Two.class));
//                        Utility.setSharedPreference(appContext, "sign", "yes");
//                        finish();

                    } else {
                        DialogMessage.showPopupMessage("Error " + code + " please try again!", appContext);
//                        Toast.makeText(appContext, "Error "+code+" please try again!", Toast.LENGTH_LONG).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    DialogMessage.showPopupMessage("Please check your internet connection", appContext);
//                    Toast.makeText(appContext, "Please check your internet connection", Toast.LENGTH_LONG).show();
                }
            } else {
                DialogMessage.showPopupMessage("Please check your internet connection", appContext);
//                Toast.makeText(appContext, "Please check your internet connection", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(s);
        }

    }

}
