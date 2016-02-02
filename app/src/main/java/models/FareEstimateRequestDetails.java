package models;

import android.app.Activity;

/**
 * Created by Arpit Sinha on 04-Dec-15.
 */
public class FareEstimateRequestDetails extends CompulsoryRegistrationDetails {

    public FareEstimateRequestDetails(Activity activity) {
        super(activity);
    }

    Double sourceLat, sourceLng, destLat, destLng;
    String userId;

    public Double getSourceLat() {
        return sourceLat;
    }

    public void setSourceLat(Double sourceLat) {
        this.sourceLat = sourceLat;
    }

    public Double getSourceLng() {
        return sourceLng;
    }

    public void setSourceLng(Double sourceLng) {
        this.sourceLng = sourceLng;
    }

    public Double getDestLat() {
        return destLat;
    }

    public void setDestLat(Double destLat) {
        this.destLat = destLat;
    }

    public Double getDestLng() {
        return destLng;
    }

    public void setDestLng(Double destLng) {
        this.destLng = destLng;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
