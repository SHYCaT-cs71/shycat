package fyi.shycat.site.web_scraping;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocalistLocationJson {

    @JsonProperty("@type")
    private String type;

    private String name;

    private String address;

    @JsonProperty("url")
    private String locationUrl;

    private LocalistLocationJson.GeoLocation geo;

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

    public LocalistLocationJson.GeoLocation getGeo() {
        return geo;
    }

    public void setGeo(LocalistLocationJson.GeoLocation geo) {
        this.geo = geo;
    }

    public static class GeoLocation {
        @JsonProperty("@type")
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
    }
}
