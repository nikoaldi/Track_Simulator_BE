package pkg.Radar;

import io.quarkus.scheduler.Scheduled;
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
public class RadarResource {

    @Inject
    RadarRepository radarRepository;



    int count;
    int test = 0;

    public Radar dataRadar;
    public static List<Radar> radars = new ArrayList<>();
    public static List<Radar> radars1 = new ArrayList<>();

    public static List<Long> listId = new ArrayList<>();


    @GET
    @Path("/test")
    public List<Radar> tampilCount(){
        return radars1;
    }

    @GET
    @Path("/id")
    public List<Long> tampilListId(){
        return listId;
    }

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

    @POST
    @Path("/single")
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
    public Response create(
            @RequestBody(
                    description = "Radar to create",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Radar.class))
            )
            Radar radar){
        radarRepository.persist(radar);
        if (radarRepository.isPersistent(radar)){
            return Response.created(URI.create("/radars/"+radar.getId())).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }


    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
    @POST
    @Path("/multimanual")
    @Operation(
            operationId = "saveMultiManual",
            summary = "Save multiple manual data radar",
            description = "Save new Multiple Manual Data Radar to add inside the list"
    )
    @APIResponse(
            responseCode = "201",
            description = "Data Multiple Radar Created",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Transactional
    public Response saveMultiManual(
            @RequestBody(
                    description = "Multiple Radar to create",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Radar.class))
            )
            Radar radar){

        insertMultiManual(radar);

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("/multiauto")
    @Operation(
            operationId = "saveMultiAuto",
            summary = "Save multiple auto data radar",
            description = "Save new Multiple Auto Data Radar to add inside the list"
    )
    @APIResponse(
            responseCode = "201",
            description = "Data Multiple Radar Created",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Transactional
    public Response saveMultiAuto(
            @RequestBody(
                    description = "Multiple Radar to create",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Radar.class))
            )
            Radar radar){

        insertMultiAuto(radar);

        return Response.status(Response.Status.BAD_REQUEST).build();
    }


    @POST
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
    @Path("/inputdeleteid")
    @Transactional
    public Response inputIdDelete(Radar radar){
        boolean input = listId.add(radar.getId());
        return input ? Response.noContent().build() : Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Operation(
            operationId = "deleteTrackRadar",
            summary = "Delete Track Radar Message",
            description = "Delete  Track Radar Message from  database"
    )
    @APIResponse(
            responseCode = "201",
            description = "Radar Track Deleted",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Transactional
    public Response deleteTrackRadar(String radar){
        try {
            for (int i =0; i < listId.size(); i++){
                boolean deleted = radarRepository.deleteById(listId.get(i));
                if (deleted){
                    listId.remove(listId.get(i));
                }
            }
            if (listId.isEmpty()){
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }



    public void insertMultiManual(Radar radar){
        radarRepository.persist(radar);
    }

    public void insertMultiAuto(Radar radar){
        radar.setAltitudeRangeMax(20);
        radar.setAltitudeRangeMin(20);
        radarRepository.persist(radar);
    }






}
