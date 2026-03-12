package Project7_1;

public class Stop {
    final static int MAX_VEHICAL_SPEED = 90;

    String id;
    String name;
    double lat, lon;

    public Stop(String id, String name, double lat, double lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public double calculateDistanceInTime(Stop other) {
        return getDistanceFromLatLonInMeter(other)/(MAX_VEHICAL_SPEED/3.6);
    }

    private double getDistanceFromLatLonInMeter(Stop other) {
        var R = 6371; // Radius of the earth in km
        var dLat = deg2rad(other.lat - lat); // deg2rad below
        var dLon = deg2rad(other.lon - lon);
        var a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(deg2rad(lat)) * Math.cos(deg2rad(other.lat)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        var d = R * c; // Distance in km
        return d*1000;
    }

    private double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }

    // TODO: Fixa bättre hashCode och equals
    @Override
    public boolean equals(Object other) {
        if (other instanceof Stop s) {
            return s.id.equals(id);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        try {
            int code = 0;
            for (char c : id.toCharArray()) {
                code += c * 7;
            }
            return code;
        } catch (NumberFormatException e) {
            System.out.println(id);
            return 0;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
