package com.trevorism.predict.webapi

import com.trevorism.predict.model.Card
import com.trevorism.predict.model.Prediction
import com.trevorism.predict.model.PredictionChoice
import com.trevorism.predict.model.PredictionQuestion
import com.trevorism.predict.service.DatastorePredictionChoiceService
import com.trevorism.predict.service.DatastorePredictionQuestionService
import com.trevorism.predict.service.DatastorePredictionService
import com.trevorism.predict.service.PredictionChoiceService
import com.trevorism.predict.service.PredictionQuestionService
import com.trevorism.predict.service.PredictionService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.NotFoundException
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * @author tbrooks
 */
@Api("Prediction Operations")
@Path("/card")
class CardController {

    PredictionQuestionService predictionQuestionService = new DatastorePredictionQuestionService()
    PredictionChoiceService predictionChoiceService = new DatastorePredictionChoiceService()
    PredictionService predictionService = new DatastorePredictionService()

    @ApiOperation(value = "View a Card with the {id}")
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Card get(@PathParam("id") String id) {
        Prediction prediction = predictionService.get(id)
        if(!prediction)
            throw new NotFoundException("Unable to find prediction with id: ${predictionId}")

        PredictionQuestion question = predictionQuestionService.get(prediction.questionId)
        List<PredictionChoice> choices = predictionChoiceService.getForPrediction(id)

        return new Card(id: id, answerType: prediction.answerType, title: prediction.title, category: prediction.category, question: question, choices: choices)
    }



}
