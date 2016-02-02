package models;

import android.app.Activity;

/**
 * Created by Arpit Sinha on 24-Nov-15.
 */
public class TripRequestDetail extends CompulsoryRegistrationDetails {

    public TripRequestDetail(Activity activity) {
        super(activity);
        TaxiType = 2;

    }

    Double sourceLati,sourceLong,destiLati,destiLong;
    String comment,userId;

    Integer TaxiType;

    public Double getSourceLati() {
        return sourceLati;
    }

    public void setSourceLati(Double sourceLati) {
        this.sourceLati = sourceLati;
    }

    public Double getSourceLong() {
        return sourceLong;
    }

    public void setSourceLong(Double sourceLong) {
        this.sourceLong = sourceLong;
    }

    public Double getDestiLati() {
        return destiLati;
    }

    public void setDestiLati(Double destiLati) {
        this.destiLati = destiLati;
    }

    public Double getDestiLong() {
        return destiLong;
    }

    public void setDestiLong(Double destiLong) {
        this.destiLong = destiLong;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTaxiType() {
        return TaxiType;
    }

    public void setTaxiType(Integer TaxiType) {
        this.TaxiType = TaxiType;
    }
}
