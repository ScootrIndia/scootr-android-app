package location;

import android.content.Context;
import android.location.Location;
import android.util.Log;

public class LocationProvider {

	private static final String TAG = LocationProvider.class.getSimpleName();

	public static Location getLocation(Context context) {
		Log.e(TAG, "Inside setOpMap()");
		GetLocation gps = new GetLocation(context);

		Log.e(TAG, "Inside ()");
		Location curLocation = null;
		// check if GPS enabled
		if (gps.canGetLocation()) {
			Log.e(TAG, "Inside getLocation(), can get location true ");

			curLocation = gps.getLocation();

		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settingsLog.e(TAG,
			// "Inside setOpMap() ");
			Log.e(TAG, "Inside setOpMap() can get location false");
			gps.showSettingsAlert();

		}

		return curLocation;
	}
	
}
