package pkg.OwnPlatform;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.xml.crypto.Data;
import java.util.Date;

@Schema(name = "OwnPlatform", description = "OwnPlatform Model")
public class ownPlatformConfig {

    @Schema(required = true)
    private Long trackNumber;
    private String trackMode;
    private String startTime;
    private String endTime;
    private String status;
    private String lastSend;

    public String getLastSend() {
        return lastSend;
    }

    public void setLastSend(String lastSend) {
        this.lastSend = lastSend;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Long trackNumber) {
        this.trackNumber = trackNumber;
    }

    public String getTrackMode() {
        return trackMode;
    }

    public void setTrackMode(String trackMode) {
        this.trackMode = trackMode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
