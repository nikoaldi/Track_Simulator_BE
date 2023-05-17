package pkg.Radar;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class RadarResource {

    @Inject
    RadarRepository radarRepository;


    @Inject
    @Channel("Ownplatform-out")
    Emitter<Radar> sendKafka;

    List<Radar> listRadar = new ArrayList<>();
    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//dd/MM/yyyy
    String sampeMana;
    //Delete Track Radar
    public void deleteAllTrack(Long[] id){
        for (int i =0; i< id.length; i++) {
            boolean deleted = radarRepository.deleteById(id[i]);
        }
    }

    // SEND RADAR TRACK

    public Response sendRadarTrack(Long[] id){
        for (int i =0; i< id.length; i++) {
            return radarRepository.find("id", id[i])
                    .singleResultOptional()
                    .map(
                            m -> {
                                if (m.getTrackMode().equals("Manual")) {
                                    Date now = new Date();
                                    String strDate = sdfDate.format(now);
                                    m.setLastSend(strDate);
                                    m.setStatus("Sended");
                                    sendKafka.send(m);
                                    return Response.ok(m).build();
                                } else {
                                    m.setStatus("Sending");
                                    sendKafka.send(m);
                                    return Response.ok(m).build();
                                }

                    })
                    .orElse(Response.status(Response.Status.BAD_REQUEST).build());

        }
        return null;
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


    // FUNGSI UPDATE RADAR TRACK
    public Response updateRadarTrack(Long id,Radar radar){
        return radarRepository
                .findByIdOptional(id)
                .map(
                        m -> {
                            m.setTrackMode(radar.getTrackMode());
                            m.setCount(radar.getCount());
                            m.setEnvironment(radar.getEnvironment());
                            m.setStartTime(radar.getStartTime());
                            m.setEndTime(radar.getEndTime());
                            m.setCourse(radar.getCourse());
                            m.setSpeed(radar.getSpeed());
                            m.setAltitude(radar.getAltitude());
                            m.setCourseRangeMin(radar.getCourseRangeMin());
                            m.setCourseRangeMax(radar.getCourseRangeMax());
                            m.setCourseIncrement(radar.getCourseIncrement());
                            m.setSpeedRangeMin(radar.getSpeedRangeMin());
                            m.setSpeedRangeMax(radar.getSpeedRangeMax());
                            m.setSpeedIncrement(radar.getSpeedIncrement());
                            m.setAltitudeRangeMin(radar.getAltitudeRangeMin());
                            m.setAltitudeRangeMax(radar.getAltitudeRangeMax());
                            m.setAltitudeIncrement(radar.getAltitudeIncrement());
                            m.setLatitude(radar.getLatitude());
                            m.setLongitude(radar.getLongitude());
                            m.setBearing(radar.getBearing());
                            m.setDistance(radar.getDistance());
                            m.setMode1code(radar.getMode1code());
                            m.setMode2code(radar.getMode2code());
                            m.setMode3code(radar.getMode3code());
                            m.setMode4code(radar.getMode4code());
                            m.setMode5code(radar.getMode5code());

                            return Response.ok(m).build();
                        })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }



    @Scheduled(every = "2s")
    public void onStart(){
        listRadar = (radarRepository.listAll());
        for (int i =0; i< listRadar.size(); i++) {
            if (listRadar.get(i).getStatus().equals("Sending")){

                radarRepository
                        .findByIdOptional(listRadar.get(i).getId())
                        .map(
                                m -> {

                                        Date now = new Date();
                                        String strDate = sdfDate.format(now);
                                        m.setLastSend(strDate);
                                        sendKafka.send(m);
                                        return Response.ok(m).build();

                                })
                        .orElse(Response.status(Response.Status.NOT_FOUND).build());
            }

        }
    }


}
