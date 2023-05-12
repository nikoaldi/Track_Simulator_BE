package pkg.Radar;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@ApplicationScoped
public class RadarResource {

    @Inject
    RadarRepository radarRepository;


    //Delete Track Radar
    public void deleteAllTrack(Long[] id){
        for (int i =0; i< id.length; i++) {
            boolean deleted = radarRepository.deleteById(id[i]);
        }
    }

    // SEND RADAR TRACK
    public void sendRadarTrack(Long[] id){
        for (int i =0; i< id.length; i++) {
            radarRepository
                    .findByIdOptional(id[i])
                    .map(
                            m -> {
                                if (m.getTrackMode().equals("Manual")) {
                                    m.setStatus("Sended");
                                    return Response.ok(m).build();
                                } else {
                                    m.setStatus("Sending");
                                    return Response.ok(m).build();
                                }
                            })
                    .orElse(Response.status(Response.Status.NOT_FOUND).build());
        }
    }

    // FUNGSI SAVE SINGLE RADAR TRACK
    public Response insertSingleRadarTrack(Radar radar){
        radarRepository.persist(radar);
        if (radarRepository.isPersistent(radar)){
            return Response.created(URI.create("/radars/"+radar.getId())).build();
        } else  {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    // FUNGSI SAVE MULTI RADAR TRACK
    public Response insertMultiRadarTrack(Radar radar){
        if (radar.getTrackMode().equals("Automatic")){
            radar.setEnvironment("Air");
            radar.setStartTime("-");
            radar.setEndTime("-");
            radar.setCourse(10);
            radar.setSpeed(20);
            radar.setAltitude(30);
            radar.setCourseRangeMin(1);
            radar.setCourseRangeMax(2);
            radar.setSpeedRangeMin(3);
            radar.setSpeedRangeMax(4);
            radar.setAltitudeRangeMin(5);
            radar.setAltitudeRangeMax(6);
            radar.setCourseIncrement(7);
            radar.setSpeedIncrement(8);
            radar.setAltitudeIncrement(9);
            radar.setLatitude(10);
            radar.setLongitude(11);
            radar.setBearing(12);
            radar.setDistance(13);
            radar.setMode1code(14);
            radar.setMode2code(15);
            radar.setMode3code(16);
            radar.setMode4code(17);
            radar.setMode5code(18);

            radarRepository.persist(radar);
            if (radarRepository.isPersistent(radar)){
                return Response.created(URI.create("/radars/"+radar.getId())).build();
            } else  {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

//            // Generate Random Course
//            double randomCourse = new Random().nextDouble();
//            radar.setCourse(radar.getCourseRangeMin() + (randomCourse * (radar.getCourseRangeMax() - radar.getCourseRangeMin())));
//
//            // Generate Random speed
//            double randomSpeed = new Random().nextDouble();
//            radar.setCourse(radar.getSpeedRangeMin() + (randomSpeed * (radar.getSpeedRangeMax() - radar.getSpeedRangeMin())));

        } else {
            return Response.ok(insertSingleRadarTrack(radar)).build();
        }
    }
}
