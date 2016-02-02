package models;

import android.app.Activity;

/**
 * Created by Vidit Mathur on 12-Dec-15.
 */
public class UserSavedDetail extends CompulsoryRegistrationDetails {

    //"userMobile":"1234567890","message":"Login Successfully","response":"true","userName":"Test","userEmail":"qwe@asd.com","userId":"9"
    String userMobile, userName, userEmail, userId, userCountryCode, userDateOfBirth;

    public UserSavedDetail(Activity activity) {
        super(activity);
        userCountryCode = "91";
    }

    public String getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public void setUserDateOfBirth(String userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCountryCode() {
        return userCountryCode;
    }

    public void setUserCountryCode(String userCountryCode) {
        this.userCountryCode = userCountryCode;
    }
}
