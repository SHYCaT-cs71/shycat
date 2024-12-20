package fyi.shycat.site.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.util.Objects;

@Embeddable
public class Location {

    @JsonProperty("locationType")
    private String type;

    @JsonProperty("locationName")
    private String name;

    @JsonProperty("locationAddress")
    private String address;

    @JsonProperty("locationUrl")
    private String locationUrl;

    @Embedded
    private GeoLocation geo;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationUrl() {
        return locationUrl;
    }

    public void setLocationUrl(String locationUrl) {
        this.locationUrl = locationUrl;
    }

    @JsonProperty("locationGeo")
    public GeoLocation getGeo() {
        return geo;
    }

    public void setGeo(GeoLocation geo) {
        this.geo = geo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(type, location.type) && Objects.equals(name, location.name) &&
               Objects.equals(address, location.address) &&
               Objects.equals(locationUrl, location.locationUrl) && Objects.equals(geo, location.geo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, address, locationUrl, geo);
    }

    @Embeddable
    public static class GeoLocation {
        @JsonProperty("geoType")
        private String type;
        private double latitude;
        private double longitude;

        public GeoLocation() {
        }

        public GeoLocation(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            GeoLocation that = (GeoLocation) o;
            return Double.compare(latitude, that.latitude) == 0 &&
                   Double.compare(longitude, that.longitude) == 0 && Objects.equals(type, that.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, latitude, longitude);
        }
    }

}
