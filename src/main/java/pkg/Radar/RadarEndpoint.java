package pkg.Radar;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.net.URI;
import java.util.*;

@Path("/radar")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RadarEndpoint {

    @Inject
    RadarRepository radarRepository;

    @Inject
    RadarResource radarResource;

    int count;
    public static List<Radar> radars = new ArrayList<>();


    @GET
    @Path("/count")
    public int count(){
        return count;
    }



    // GET ALL DATA RADAR
    @GET
    @Operation(
            operationId = "getAll",
            summary = "get All Plot",
            description = "method to get all Plot"
    )
    @APIResponse(
            responseCode = "200",
            description = "Operation Complited",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getAll(){
        radars = radarRepository.listAll();
        return Response.ok(radars).build();
    }


    // GET DATA RADAR BY ID
    @GET
    @Operation(
            operationId = "getById",
            summary = "get Plot By id",
            description = "method to get plot by id"
    )
    @APIResponse(
            responseCode = "200",
            description = "Operation Complited",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Path("{id}")
    public Response getById(@PathParam("id") Long id){
        return radarRepository.findByIdOptional(id)
                .map(radar -> Response.ok(radar).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }


    // DELETE ALL RADAR TRACK
    @DELETE
    @Operation(
            operationId = "DeletePlot",
            summary = "Delete  Plot",
            description = "Delete Plot from  inside the list"
    )
    @APIResponse(
            responseCode = "201",
            description = "Plot Deleted",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Path("/deleteall")
    @Transactional
    public void inputIdDelete(Long[] id){
        radarResource.deleteAllTrack(id);
    }

    // POST SEND TRACK
    @POST
    @Operation(
            operationId = "sendTrackRadar",
            summary = "Send Radar Track",
            description = "Sending Radar Track to Kafka"
    )
    @APIResponse(
            responseCode = "201",
            description = "Radar track sended",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Path("/sendtrack")
    @Transactional
    public void sendRadarTrack(Long[] id){
        radarResource.sendRadarTrack(id);
    }

    // SAVE RADA TRACK
    @POST
    @Operation(
            operationId = "inputDataRadar",
            summary = "create new Data Radar",
            description = "Create a new Data Radar to add inside the list"
    )
    @APIResponse(
            responseCode = "201",
            description = "Data Radar Created",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Transactional
    public Response inputRadarTrack(
            @RequestBody(
                    description = "Radar to create",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Radar.class))
            )
            Radar radar) {
            if (radar.getTrackInput().equals("single")){
                return Response.ok(radarResource.insertSingleRadarTrack(radar)).build();
            } else {
                return Response.ok(radarResource.insertMultiRadarTrack(radar)).build();
            }
    }
















 

}
