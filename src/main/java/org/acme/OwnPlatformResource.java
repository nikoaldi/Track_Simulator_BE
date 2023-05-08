package org.acme;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;


import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Path("/ownplatform")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OwnPlatformResource {

    @Inject
    OwnUnitGenerator coba;
    public static ownPlatformData dataOwn = null;
    public static ownPlatformConfig dataConfig = null;
    public static ownPlatformConfig cobatest = null;
    String info;
    String statusConfig;


    @GET
    public Response getAll(){
            return Response.ok(info).build();
    }

    @GET
    @Path("/config")
    public Response getConfig(){
            return Response.ok(dataConfig).build();
    }

    @GET
    @Path("/cobatest")
    public Response getcobatest(){
        return Response.ok(cobatest).build();
    }

    @GET
    @Path("/data")
    public Response getModel(){
            return Response.ok(dataOwn).build();
    }



    @POST
    @Path("/saveconfig")
    public void inputConfigOwnplatform( @RequestBody(
            description = "Save Data Config",
            required = true,
            content = @Content
                    (schema = @Schema(implementation = ownPlatformConfig.class))) ownPlatformConfig config){
       saveConfigOwnPlatform(config);
    }

    @POST
    @Path("/savedata")
    public void inputDataOwnPlatform( @RequestBody(
            description = "Save data Ownplatform",
            required = true,
            content = @Content
                    (schema = @Schema(implementation = ownPlatformData.class))) ownPlatformData data){
        saveDataOwnPlatform(data);
    }

    //Save data Ownplatform
    public Response saveDataOwnPlatform(ownPlatformData dataInput){
        if (dataOwn == null){
            dataOwn = dataInput;
            if (dataOwn.getTrackNumber() == 0){
                coba.tampungData(dataOwn);
                statusConfig = "Berhasil Ditambahkan";
                return Response.created(URI.create("/ownData/"+dataInput.getTrackNumber())).build();
            }
            statusConfig = "Gagal Ditambahkan";
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            dataOwn.setTrackNumber(dataInput.getTrackNumber());
            dataOwn.setHumidity(dataInput.getHumidity());
            dataOwn.setAirTemperature(dataInput.getAirTemperature());
            dataOwn.setBarometricPressure(dataInput.getBarometricPressure());
            dataOwn.setEnvironment(dataInput.getEnvironment());
            dataOwn.setWindSpeed(dataInput.getWindSpeed());
            dataOwn.setWindDirection(dataInput.getWindDirection());
            dataOwn.setLatitude(dataInput.getLatitude());
            dataOwn.setLongitude(dataInput.getLongitude());
            dataOwn.setAltitude(dataInput.getAltitude());
            dataOwn.setPitch(dataInput.getPitch());
            dataOwn.setAccelerationX(dataInput.getAccelerationX());
            dataOwn.setVelocityX(dataInput.getVelocityX());
            dataOwn.setSpeed(dataInput.getSpeed());
            dataOwn.setRoll(dataInput.getRoll());
            dataOwn.setAccelerationY(dataInput.getAccelerationY());
            dataOwn.setVelocityY(dataInput.getVelocityY());
            dataOwn.setHeading(dataInput.getHeading());
            dataOwn.setYaw(dataInput.getYaw());
            dataOwn.setAccelerationZ(dataInput.getAccelerationZ());
            dataOwn.setVelocityZ(dataInput.getVelocityZ());
            statusConfig = "Berhasil Diupdate";
            return Response.ok(dataOwn).build();
        }
    }

    //Save Config Ownplatform
    public Response saveConfigOwnPlatform(ownPlatformConfig dataInput) {
        if (dataConfig == null){
            dataConfig = dataInput;
            if (dataConfig.getTrackNumber() == 0){
                info = "Udah Ada Track NUmber";
                coba.tampungConfig(dataConfig);
                return Response.created(URI.create("/ownConfig/"+dataInput.getTrackNumber())).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            dataConfig.setTrackMode(dataInput.getTrackMode());
            dataConfig.setTrackNumber(dataInput.getTrackNumber());
            dataConfig.setStartTime(dataInput.getStartTime());
            dataConfig.setEndTime(dataInput.getEndTime());
            dataConfig.setStatus(dataInput.getStatus());
            return Response.ok(dataInput).build();
        }
    }



    @POST
    @Path("/saveandsend")
    public void saveAndSendOwnPlatform(String input){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        dataConfig.setLastSend(strDate);
        coba.sendMessageBroker(dataOwn);
    }

    @POST
    @Path("/sendonly")
    public void sendOwnPlatform(ownPlatformConfig input){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        dataConfig.setLastSend(strDate);
        dataConfig.setStatus(input.getStatus());
        coba.sendMessageBroker(dataOwn);
    }

    @POST
    @Path("/startstop")
    public void startAndStopOwnPlatform(ownPlatformConfig input){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        dataConfig.setLastSend(strDate);
        dataConfig.setStatus(input.getStatus());
    }

    public void updateLastSend(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        dataConfig.setLastSend(strDate);
    }

    public void updateStatus(){
        dataConfig.setStatus("Stop");
    }


//            ownPlatformModel plot1 = n ew ownPlatformModel(1.1f,11.1f,11.1f,"1.1f",11.1f,11.1f,11.1f,11.1f,11.1f,11.1f,11L,11L,11.1f,11.1f,11L,11L,11.1f,11.1f,11L,11L);
//            coba = plot1;


}
