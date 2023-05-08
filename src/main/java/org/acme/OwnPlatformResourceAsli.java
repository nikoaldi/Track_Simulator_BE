package org.acme;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/ownplatform1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OwnPlatformResourceAsli {

    @Inject
    OwnUnitGenerator coba;


    public static List<ownPlatformConfig> ownConfig = new ArrayList<>();
    public static List<ownPlatformData> ownData = new ArrayList<>();

    public static ownPlatformData dataOwn;
    public static ownPlatformConfig dataConfig;



    @GET
    public Response getAll(){
            return Response.ok(ownConfig).build();
    }

    @GET
    @Path("/config")
    public Response getConfig(){
        if (ownConfig.size() == 0){
            return Response.ok(ownConfig).build();
        } else {
            return Response.ok(ownConfig.get(0)).build();
        }
    }

    @GET
    @Path("/data")
    public Response getModel(){
        if (ownData.size() == 0){
            return Response.ok(ownData).build();
        } else {
            return Response.ok(ownData.get(0)).build();
        }
    }

    @POST
    @Path("/saveconfig")
    public Response inputConfig( @RequestBody(
            description = "Plot to create",
            required = true,
            content = @Content
                    (schema = @Schema(implementation = ownPlatformConfig.class))) ownPlatformConfig config){

        if (ownConfig.isEmpty()){
            ownConfig.add(config);
            dataConfig = config;
            if (ownConfig.contains(config)){
                coba.tampungConfig(ownConfig.get(0));
                return Response.created(URI.create("/ownConfig/"+config.getTrackNumber())).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return ownConfig
                    .stream().findFirst()
                    .map(
                            m -> {
                                m.setTrackMode(config.getTrackMode());
                                m.setTrackNumber(config.getTrackNumber());
                                m.setStartTime(config.getStartTime());
                                m.setEndTime(config.getEndTime());
                                return Response.ok(m).build();
                            })
                    .orElse(Response.status(Response.Status.NOT_FOUND).build());
        }
    }

    @POST
    @Path("/savedata")
    public Response saveOwnPlatform( @RequestBody(
            description = "Plot to create",
            required = true,
            content = @Content
                    (schema = @Schema(implementation = ownPlatformData.class))) ownPlatformData data){

        if (ownData.isEmpty()){
            ownData.add(data);
            if (ownData.contains(data)){
                coba.tampungData(ownData.get(0));
                return Response.created(URI.create("/ownData/"+data.getTrackNumber())).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return ownData
                    .stream().findFirst()
                    .map(
                            m -> {
                                m.setTrackNumber(data.getTrackNumber());
                                m.setHumidity(data.getHumidity());
                                m.setAirTemperature(data.getAirTemperature());
                                m.setBarometricPressure(data.getBarometricPressure());
                                m.setEnvironment(data.getEnvironment());
                                m.setWindSpeed(data.getWindSpeed());
                                m.setWindDirection(data.getWindDirection());
                                m.setLatitude(data.getLatitude());
                                m.setLongitude(data.getLongitude());
                                m.setAltitude(data.getAltitude());
                                m.setPitch(data.getPitch());
                                m.setAccelerationX(data.getAccelerationX());
                                m.setVelocityX(data.getVelocityX());
                                m.setSpeed(data.getSpeed());
                                m.setRoll(data.getRoll());
                                m.setAccelerationY(data.getAccelerationY());
                                m.setVelocityY(data.getVelocityY());
                                m.setHeading(data.getHeading());
                                m.setYaw(data.getYaw());
                                m.setAccelerationZ(data.getAccelerationZ());
                                m.setVelocityZ(data.getVelocityZ());
                                return Response.ok(m).build();
                            })
                    .orElse(Response.status(Response.Status.NOT_FOUND).build());
        }
    }

    @POST
    @Path("/savesend")
    public Response sendOwnPlatform( @RequestBody(
            description = "Plot to create",
            required = true,
            content = @Content
                    (schema = @Schema(implementation = ownPlatformData.class))) ownPlatformData data){

        if (ownData.isEmpty()){
            ownData.add(data);
            if (ownData.contains(data)){
                coba.tampungData(ownData.get(0));
                return Response.created(URI.create("/ownData/"+data.getTrackNumber())).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return ownData
                    .stream().findFirst()
                    .map(
                            m -> {
                                m.setTrackNumber(data.getTrackNumber());
                                m.setHumidity(data.getHumidity());
                                m.setAirTemperature(data.getAirTemperature());
                                m.setBarometricPressure(data.getBarometricPressure());
                                m.setEnvironment(data.getEnvironment());
                                m.setWindSpeed(data.getWindSpeed());
                                m.setWindDirection(data.getWindDirection());
                                m.setLatitude(data.getLatitude());
                                m.setLongitude(data.getLongitude());
                                m.setAltitude(data.getAltitude());
                                m.setPitch(data.getPitch());
                                m.setAccelerationX(data.getAccelerationX());
                                m.setVelocityX(data.getVelocityX());
                                m.setSpeed(data.getSpeed());
                                m.setRoll(data.getRoll());
                                m.setAccelerationY(data.getAccelerationY());
                                m.setVelocityY(data.getVelocityY());
                                m.setHeading(data.getHeading());
                                m.setYaw(data.getYaw());
                                m.setAccelerationZ(data.getAccelerationZ());
                                m.setVelocityZ(data.getVelocityZ());
                                return Response.ok(m).build();
                            })
                    .orElse(Response.status(Response.Status.NOT_FOUND).build());
        }
    }

//            ownPlatformModel plot1 = new ownPlatformModel(1.1f,11.1f,11.1f,"1.1f",11.1f,11.1f,11.1f,11.1f,11.1f,11.1f,11L,11L,11.1f,11.1f,11L,11L,11.1f,11.1f,11L,11L);
//            coba = plot1;


}
