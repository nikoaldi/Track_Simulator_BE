package pkg.OwnPlatform;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

//@Path("/coba")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class OwnUnitGenerator {

    @Inject
    @Channel("Ownplatform-out")
    Emitter<ownPlatformData> sendToKafka;

    @Inject
    OwnPlatformResource ownPlatformResource;


    public static ownPlatformConfig ownPlatformConfigList;
    public static ownPlatformData ownPlatformDataList;

    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//dd/MM/yyyy
    Date now = new Date();
    String strDate = sdfDate.format(now);

    String endTime;

    String sampeMana;





//
//    @GET
//    public Response getAll(){
//        return Response.ok(strDate + " --- " + endTime).build();
//    }
//
//    @GET
//    @Path("/data")
//    public Response data(){
//        return Response.ok(ownPlatformDataList).build();
//    }
//
//    @GET
//    @Path("/sampemana")
//    public Response sampemana(){
//        return Response.ok(sampeMana).build();
//    }
//    @GET
//    @Path("/config")
//    public Response config(){
//        return Response.ok(ownPlatformConfigList).build();
//    }

    public ownPlatformConfig tampungConfig(ownPlatformConfig config){
        ownPlatformConfigList = config;
        endTime = ownPlatformConfigList.getEndTime();
        return config;
    }
    public ownPlatformData tampungData(ownPlatformData data){
        ownPlatformDataList = data;
//        ownData.add(data);
        return data;
    }


    public ownPlatformData sendMessageBroker(ownPlatformData data){
        sendToKafka.send(data);
        return data;
    }

    String second = "1s";

    @Scheduled(every = "2s")
    public void onStart() {
        if (ownPlatformConfigList != null) {
            sampeMana = "1";
            if (ownPlatformConfigList.getStatus().equals("Start")){
                sampeMana = "2";
                SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//dd/MM/yyyy
                Date now = new Date();
                String strDate = sdfDate.format(now);

                LocalDateTime waktusekarang = LocalDateTime.parse(strDate);
                LocalDateTime startTime = LocalDateTime.parse(ownPlatformConfigList.getStartTime());
                LocalDateTime endTime = LocalDateTime.parse(ownPlatformConfigList.getEndTime());

                String informasi;

                if (waktusekarang.isAfter(startTime) || waktusekarang.equals(startTime)){
                    sampeMana = "3";
                    if (waktusekarang.isBefore(endTime) || waktusekarang.equals(startTime)){
                        sampeMana = "4";
                        if (ownPlatformConfigList.getTrackMode().equals("Automatic")){
                            sampeMana = "5";
                            sendToKafka.send(ownPlatformDataList);
                            ownPlatformResource.updateLastSend();
                        }else {

                        }
                    } else {
                        ownPlatformResource.updateStatus();
                    }
                }
            }
        }

    }






}
