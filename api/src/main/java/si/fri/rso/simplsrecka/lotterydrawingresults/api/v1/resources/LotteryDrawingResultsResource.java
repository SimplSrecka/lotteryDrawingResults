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
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
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

    @Operation(description = "Get all lottery drawing results.", summary = "Get all drawing results")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "List of lottery drawing results",
                    content = @Content(schema = @Schema(implementation = LotteryDrawingResult.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of drawing results in list")}
            ),
            @APIResponse(responseCode = "400", description = "Bad Request - The request could not be understood or was missing required parameters."),
            @APIResponse(responseCode = "401", description = "Unauthorized - Authentication failed or user doesn't have permissions for requested operation."),
            @APIResponse(responseCode = "403", description = "Forbidden - Access to the requested resource is denied."),
            @APIResponse(responseCode = "404", description = "Not Found - The specified resource was not found."),
            @APIResponse(responseCode = "500", description = "Internal Server Error - Something went wrong on the server side.")
    })
    @GET
    public Response getLotteryDrawingResults() {
        List<LotteryDrawingResult> drawingResults = lotteryDrawingResultsBean.getLotteryDrawingResults();
        return Response.status(Response.Status.OK).entity(drawingResults).build();
    }

    @Operation(description = "Get details for a specific lottery drawing result.", summary = "Get drawing result details")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Lottery drawing result details",
                    content = @Content(schema = @Schema(implementation = LotteryDrawingResult.class))
            ),
            @APIResponse(responseCode = "404", description = "Drawing result not found")
    })
    @GET
    @Path("/{ticketId}")
    public Response getLotteryDrawingResult(@Parameter(description = "Drawing Result ID.", required = true)
                                            @PathParam("ticketId") Integer ticketId,
                                            @Parameter(description = "Draw Date.")
                                            @QueryParam("drawingDate") String drawingDate){
        System.out.println(ticketId + " " + drawingDate);
        try {
            List<LotteryDrawingResult> drawingResult = lotteryDrawingResultsBean.getLotteryDrawingResult(ticketId, drawingDate);
            if (drawingResult.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("No lottery drawing for ticket").build();
            }
            return Response.status(Response.Status.OK).entity(drawingResult).build();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error fetching lottery drawings", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

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