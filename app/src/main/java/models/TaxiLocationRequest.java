package models;

import android.app.Activity;

/**
 * Created by Arpit Sinha on 27-Nov-15.
 */
public class TaxiLocationRequest extends CompulsoryRegistrationDetails {

    /*

    unique_key
    latitude
    longitude

     */

    Double latitude, longitude;

    public TaxiLocationRequest(Activity activity) {
        super(activity);
    }

    public TaxiLocationRequest(Activity activity, Double latitude, Double longitude) {
        super(activity);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
