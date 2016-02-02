package models;

import android.app.Activity;

import com.firebug.scootr.R;


/**
 * Created by Arpit Sinha on 30-Oct-15.
 */
public class CompulsoryRegistrationDetails {

    private String unique_key;
    private String userLevel;
    private String userType;

    private String userRegId;

    private String userIMEI;



    public CompulsoryRegistrationDetails(Activity activity) {

        unique_key = activity.getResources().getString(R.string.app_unique_key_for_web_services);
        userLevel = activity.getResources().getString(R.string.user_level);
        userType = activity.getResources().getString(R.string.user_type);
        userRegId = "";

        userIMEI = "";

    }

    public String getUniqueKey() { return unique_key; }

    public String getUserLevel() { return userLevel; }

    public String getUserType() {
        return userType;
    }

    public String getUserRegId() { return userRegId; }

    public String getUserIMEI() {
        return userIMEI;
    }

    public void setUserIMEI(String userIMEI) { this.userIMEI = userIMEI; }

    public void setUserRegId(String userRegId) { this.userRegId = userRegId; }

}
