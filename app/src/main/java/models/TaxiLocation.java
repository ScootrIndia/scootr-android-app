package models;

/**
 * Created by Arpit Sinha on 27-Nov-15.
 */
public class TaxiLocation {

    Integer TaxiType, driverId;
    Double latitude, longitude;

    public TaxiLocation() {

    }

    public TaxiLocation(int TaxiType, int driverId, Double latitude, Double longitude) {
        this.TaxiType = TaxiType;
        this.driverId = driverId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getTaxiType() {
        return TaxiType;
    }

    public void setTaxiType(int TaxiType) {
        this.TaxiType = TaxiType;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
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

    public static boolean areTaxisEqual(TaxiLocation Taxi1, TaxiLocation Taxi2) {
        if(Taxi1.TaxiType.equals(Taxi2.TaxiType)&&
                Taxi1.driverId.equals(Taxi2.driverId)&&
                Taxi1.latitude.equals(Taxi2.latitude)&&
                Taxi1.longitude.equals(Taxi2.longitude)) {
            return true;
        }
        return true;
    }

}
