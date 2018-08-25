package com.trevorism.predict.webapi

import com.trevorism.predict.model.Prediction
import com.trevorism.predict.model.PredictionChoice
import com.trevorism.predict.model.PredictionQuestion
import com.trevorism.predict.model.PredictionResponse
import com.trevorism.predict.service.DatastorePredictionServiceTest
import com.trevorism.predict.service.PredictionService
import org.junit.Test

import javax.ws.rs.NotFoundException

/**
 * @author tbrooks
 */
class PredictionControllerTest {


    @Test
    void testAddQuestion() {
        PredictionController predictionController = new PredictionController()
        Prediction sample = DatastorePredictionServiceTest.createPrediction()
        sample.id = "5"
        predictionController.predictionService = [get:{id -> id ? sample : null}, addQuestion: {prediction, question -> prediction?.questionId = prediction?.id; return prediction}] as PredictionService

        Prediction added = predictionController.addQuestion("5", new PredictionQuestion())
        assert added.questionId == "5"

    }

    @Test(expected = NotFoundException)
    void testAddQuestionWithUnfoundPrediction() {
        PredictionController predictionController = new PredictionController()
        Prediction sample = DatastorePredictionServiceTest.createPrediction()
        sample.id = "5"
        predictionController.predictionService = [get:{id -> id ? sample : null}, addQuestion: {prediction, question -> prediction?.questionId = prediction?.id; return prediction}] as PredictionService

        Prediction added = predictionController.addQuestion(null, new PredictionQuestion())
        assert added.questionId == "5"

    }

    @Test
    void testAddChoice() {
        PredictionController predictionController = new PredictionController()
        Prediction sample = DatastorePredictionServiceTest.createPrediction()
        sample.id = "5"
        predictionController.predictionService = [get:{id -> id ? sample : null}, addChoice: {prediction, choice -> prediction?.choices << choice; return prediction}] as PredictionService

        Prediction added = predictionController.addChoice("5", new PredictionChoice())
        assert added.choices
    }

    @Test
    void testAddResponse() {
        PredictionController predictionController = new PredictionController()
        Prediction sample = DatastorePredictionServiceTest.createPrediction()
        sample.id = "5"
        predictionController.predictionService = [get:{id -> id ? sample : null}, addResponse: {prediction, response -> prediction?.responseCount++; return prediction}] as PredictionService

        Prediction added = predictionController.addResponse("5", new PredictionResponse())
        assert added.responseCount == 1
    }
}
