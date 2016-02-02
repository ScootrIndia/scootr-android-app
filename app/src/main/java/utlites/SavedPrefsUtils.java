package utlites;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import models.UserSavedDetail;


/**
 * Created by Vidit Mathur on 12-Dec-15.
 */
public class SavedPrefsUtils {

    private static final String LOG_TAG = SavedPrefsUtils.class.getSimpleName();

    private static final String userDetailFile = "userDetailFile";
    private static final String gcmKeyFile = "gcmRegKeyFile";

    Context mContext;

    public SavedPrefsUtils(Context context) {
        mContext = context;
    }

    public void saveUserDetailInSharedPrefs(UserSavedDetail userDetailToBeSaved) {

        //SharedPreferences prefs = mContext.getSharedPreferences(gcmKeyFile, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = mContext.getSharedPreferences(userDetailFile, mContext.MODE_PRIVATE).edit();
        editor.putString("userId", userDetailToBeSaved.getUserId());
        editor.putString("userName", userDetailToBeSaved.getUserName());
        editor.putString("userEmail", userDetailToBeSaved.getUserEmail());
        editor.putString("userMobile", userDetailToBeSaved.getUserMobile());
        editor.putString("userCountryCode", userDetailToBeSaved.getUserCountryCode());
        editor.putString("userDateOfBirth", userDetailToBeSaved.getUserDateOfBirth());
        editor.commit();
    }

    public UserSavedDetail getUserDetailFromSharedPrefs() {

        UserSavedDetail userSavedDetail = new UserSavedDetail((Activity) mContext);
        SharedPreferences prefs = mContext.getSharedPreferences(userDetailFile, mContext.MODE_PRIVATE);
        userSavedDetail.setUserId(prefs.getString("userId", ""));
        userSavedDetail.setUserName(prefs.getString("userName", ""));
        userSavedDetail.setUserEmail(prefs.getString("userEmail", ""));
        userSavedDetail.setUserMobile(prefs.getString("userMobile", ""));
        userSavedDetail.setUserCountryCode(prefs.getString("userCountryCode", "91"));
        userSavedDetail.setUserDateOfBirth(prefs.getString("userDateOfBirth", ""));
        return userSavedDetail;
    }

    public void saveGCMRegKeyInSharedPrefs(String regKey) {
        Log.e(LOG_TAG, "inside saveGCMRegKeyInSharedPrefs ( ) : " + regKey);

        String oldRegKey = "";
        SharedPreferences prefs = mContext.getSharedPreferences(gcmKeyFile, mContext.MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        if (restoredText != null) {
            oldRegKey = prefs.getString("RegKey", ""); //"" is the default value.
        }
        if (!regKey.isEmpty() && !regKey.equals(oldRegKey)) {
            Log.e(LOG_TAG, "inside if() : " + regKey);
            SharedPreferences.Editor editor = mContext.getSharedPreferences(gcmKeyFile, mContext.MODE_PRIVATE).edit();
            editor.putString("RegKey", regKey);
            editor.commit();
        }
    }

    public String getGCMRegKeyFromSharedPrefs() {

        String oldRegKey = "";
        SharedPreferences prefs = mContext.getSharedPreferences(gcmKeyFile, mContext.MODE_PRIVATE);
        oldRegKey = prefs.getString("RegKey", ""); //"" is the default value.

        Log.e(LOG_TAG, "Inside getGCMRegKeyFromSharedPrefs() " + oldRegKey);

        return oldRegKey;
    }


}
