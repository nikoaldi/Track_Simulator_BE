package pkg.Radar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
@Entity
@Schema(name = "Plot", description = "Plot Model")
public class Radar {

    @Id
    @GeneratedValue
    private Long id;
    @Schema(required = true)

    private String status;
    private String lastSend;
    private String count;
    private String time;

    private String trackMode;
    private String environment;
    public double courseRangeMin;
    public double courseRangeMax;
    public double courseIncrement;
    private float latitude;
    private float bearing;
    private int mode1code;
    private int mode2code;



    private String startTime;
    public double speedRangeMin;
    public double speedRangeMax;
    public double speedIncrement;
    private float longitude;
    private float distance;
    private int mode3code;
    private int mode4code;


    private String endTime;
    public double altitudeRangeMin;
    public double altitudeRangeMax;
    public double altitudeIncrement;
    private float altitude;
    private int mode5code;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastSend() {
        return lastSend;
    }

    public void setLastSend(String lastSend) {
        this.lastSend = lastSend;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTrackMode() {
        return trackMode;
    }

    public void setTrackMode(String trackMode) {
        this.trackMode = trackMode;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public double getCourseRangeMin() {
        return courseRangeMin;
    }

    public void setCourseRangeMin(double courseRangeMin) {
        this.courseRangeMin = courseRangeMin;
    }

    public double getCourseRangeMax() {
        return courseRangeMax;
    }

    public void setCourseRangeMax(double courseRangeMax) {
        this.courseRangeMax = courseRangeMax;
    }

    public double getCourseIncrement() {
        return courseIncrement;
    }

    public void setCourseIncrement(double courseIncrement) {
        this.courseIncrement = courseIncrement;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getBearing() {
        return bearing;
    }

    public void setBearing(float bearing) {
        this.bearing = bearing;
    }

    public int getMode1code() {
        return mode1code;
    }

    public void setMode1code(int mode1code) {
        this.mode1code = mode1code;
    }

    public int getMode2code() {
        return mode2code;
    }

    public void setMode2code(int mode2code) {
        this.mode2code = mode2code;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public double getSpeedRangeMin() {
        return speedRangeMin;
    }

    public void setSpeedRangeMin(double speedRangeMin) {
        this.speedRangeMin = speedRangeMin;
    }

    public double getSpeedRangeMax() {
        return speedRangeMax;
    }

    public void setSpeedRangeMax(double speedRangeMax) {
        this.speedRangeMax = speedRangeMax;
    }

    public double getSpeedIncrement() {
        return speedIncrement;
    }

    public void setSpeedIncrement(double speedIncrement) {
        this.speedIncrement = speedIncrement;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getMode3code() {
        return mode3code;
    }

    public void setMode3code(int mode3code) {
        this.mode3code = mode3code;
    }

    public int getMode4code() {
        return mode4code;
    }

    public void setMode4code(int mode4code) {
        this.mode4code = mode4code;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getAltitudeRangeMin() {
        return altitudeRangeMin;
    }

    public void setAltitudeRangeMin(double altitudeRangeMin) {
        this.altitudeRangeMin = altitudeRangeMin;
    }

    public double getAltitudeRangeMax() {
        return altitudeRangeMax;
    }

    public void setAltitudeRangeMax(double altitudeRangeMax) {
        this.altitudeRangeMax = altitudeRangeMax;
    }

    public double getAltitudeIncrement() {
        return altitudeIncrement;
    }

    public void setAltitudeIncrement(double altitudeIncrement) {
        this.altitudeIncrement = altitudeIncrement;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public int getMode5code() {
        return mode5code;
    }

    public void setMode5code(int mode5code) {
        this.mode5code = mode5code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
