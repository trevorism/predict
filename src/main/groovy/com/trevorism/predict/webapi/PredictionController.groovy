package com.trevorism.predict.webapi

import com.trevorism.predict.model.Prediction
import com.trevorism.predict.model.PredictionChoice
import com.trevorism.predict.model.PredictionQuestion
import com.trevorism.predict.model.PredictionResponse
import com.trevorism.predict.service.DatastorePredictionService
import com.trevorism.predict.service.PredictionService
import com.trevorism.secure.Secure
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.NotFoundException
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * @author tbrooks
 */
@Api("Prediction Operations")
@Path("/prediction")
class PredictionController {

    private PredictionService predictionService = new DatastorePredictionService()

    @ApiOperation(value = "Create a new Prediction **Secure")
    @POST
    @Secure
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Prediction create(Prediction prediction) {
        predictionService.create(prediction)
    }

    @ApiOperation(value = "View all Predictions")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    List<Prediction> list() {
        predictionService.list()
    }

    @ApiOperation(value = "View a Prediction with the {id}")
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Prediction get(@PathParam("id") String id) {
        predictionService.get(id)
    }

    @ApiOperation(value = "Delete a Prediction with the {id} **Secure")
    @DELETE
    @Secure
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Prediction delete(@PathParam("id") String id) {
        predictionService.delete(id)
    }

    @ApiOperation(value = "Create a new Prediction Question **Secure")
    @POST
    @Secure
    @Path("{id}/question")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Prediction addQuestion(@PathParam("id") String predictionId, PredictionQuestion question) {
        Prediction prediction = get(predictionId)
        if(!prediction)
            throw new NotFoundException("Unable to find prediction with id: ${predictionId}")

        predictionService.addQuestion(prediction, question)
    }

    @ApiOperation(value = "Create a new Prediction Choice **Secure")
    @POST
    @Secure
    @Path("{id}/choice")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Prediction addChoice(@PathParam("id") String predictionId, PredictionChoice choice) {
        Prediction prediction = get(predictionId)
        if(!prediction)
            throw new NotFoundException("Unable to find prediction with id: ${predictionId}")
        predictionService.addChoice(prediction, choice)
    }

    @ApiOperation(value = "Create a new Prediction Response")
    @POST
    @Path("{id}/response")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Prediction addResponse(@PathParam("id") String predictionId, PredictionResponse response) {
        Prediction prediction = get(predictionId)
        if(!prediction)
            throw new NotFoundException("Unable to find prediction with id: ${predictionId}")
        predictionService.addResponse(prediction, response)
    }
}
