package si.fri.rso.simplsrecka.lotterydrawingresults.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.rso.simplsrecka.lotterydrawingresults.lib.LotteryDrawingResult;
import si.fri.rso.simplsrecka.lotterydrawingresults.services.beans.LotteryDrawingResultsBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@Log
@ApplicationScoped
@Path("/lotteryResults")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LotteryDrawingResultsResource {

    private Logger log = Logger.getLogger(LotteryDrawingResultsResource.class.getName());

    @Inject
    private LotteryDrawingResultsBean lotteryDrawingResultsBean;

    @Context
    protected UriInfo uriInfo;



    @Operation(description = "Get details for a specific lottery drawing result.", summary = "Get drawing result details")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Lottery drawing result details",
                    content = @Content(schema = @Schema(implementation = LotteryDrawingResult.class))
            ),
            @APIResponse(responseCode = "404", description = "Drawing result not found")
    })
    @GET
    @Path("/{resultId}")
    public Response getLotteryDrawingResult(@Parameter(description = "Drawing Result ID.", required = true)
                                            @PathParam("resultId") Integer resultId) {
        LotteryDrawingResult drawingResult = lotteryDrawingResultsBean.getLotteryDrawingResult(resultId);
        if (drawingResult == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(drawingResult).build();
    }

    @Operation(description = "Add a new lottery drawing result.", summary = "Add drawing result")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Drawing result successfully added"),
            @APIResponse(responseCode = "400", description = "Invalid drawing result data")
    })
    @POST
    public Response createLotteryDrawingResult(@RequestBody(description = "DTO object with lottery drawing result details.",
            required = true,
            content = @Content(schema = @Schema(implementation = LotteryDrawingResult.class)))
                                               LotteryDrawingResult drawingResult) {
        if (drawingResult == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        drawingResult = lotteryDrawingResultsBean.createLotteryDrawingResult(drawingResult);
        return Response.status(Response.Status.CREATED).entity(drawingResult).build();
    }

    @Operation(description = "Update details for a specific lottery drawing result.", summary = "Update drawing result")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Drawing result successfully updated"),
            @APIResponse(responseCode = "404", description = "Drawing result not found"),
            @APIResponse(responseCode = "400", description = "Invalid drawing result data")
    })
    @PUT
    @Path("/{resultId}")
    public Response updateLotteryDrawingResult(@Parameter(description = "Drawing Result ID.", required = true)
                                               @PathParam("resultId") Integer resultId,
                                               @RequestBody(description = "DTO object with updated drawing result details.",
                                                       required = true,
                                                       content = @Content(schema = @Schema(implementation = LotteryDrawingResult.class)))
                                               LotteryDrawingResult drawingResult) {
        if (drawingResult == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        LotteryDrawingResult updatedDrawingResult = lotteryDrawingResultsBean.updateLotteryDrawingResult(resultId, drawingResult);
        if (updatedDrawingResult == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(updatedDrawingResult).build();
    }

    @Operation(description = "Delete a specific lottery drawing result.", summary = "Delete drawing result")
    @APIResponses({
            @APIResponse(responseCode = "204", description = "Drawing result successfully deleted"),
            @APIResponse(responseCode = "404", description = "Drawing result not found")
    })
    @DELETE
    @Path("/{resultId}")
    public Response deleteLotteryDrawingResult(@Parameter(description = "Drawing Result ID.", required = true)
                                               @PathParam("resultId") Integer resultId) {
        boolean deleted = lotteryDrawingResultsBean.deleteLotteryDrawingResult(resultId);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}