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
public class RadarResource {

    @Inject
    RadarRepository radarRepository;

    public static List<Radar> radars = new ArrayList<>();

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

    @POST
    @Path("/sendonly")
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


}
