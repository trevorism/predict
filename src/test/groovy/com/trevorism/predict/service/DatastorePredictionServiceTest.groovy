package com.trevorism.predict.service

import com.trevorism.predict.model.Prediction
import com.trevorism.predict.model.PredictionChoice
import com.trevorism.predict.model.PredictionQuestion
import com.trevorism.predict.model.PredictionResponse
import org.junit.Test

import java.time.LocalDate
import java.time.ZoneId

/**
 * @author tbrooks
 */
class DatastorePredictionServiceTest {

    PredictionService predictionService = new DatastorePredictionService()

    //5896559342387200 @Test
    void testCreate() {
        Prediction prediction = new Prediction()
        LocalDate localDate = LocalDate.parse("2019-06-25")
        Date completedDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        prediction.with{
            title = "2019 NBA Champs"
            category = "sports"
            completed = completedDate
            tags = ["nba", "basketball", "pro", "champion"]
        }

        Prediction savedPrediction = predictionService.create(prediction)

        assert savedPrediction.id
        assert savedPrediction.tags
        assert savedPrediction.completed
        assert savedPrediction.category == "sports"
        assert savedPrediction.title == "2019 NBA Champs"

    }

    @Test
    void testGet() {
        Prediction savedPrediction = predictionService.get("5896559342387200")

        assert savedPrediction.id
        assert savedPrediction.tags
        assert savedPrediction.completed
        assert savedPrediction.category == "sports"
        assert savedPrediction.title == "2019 NBA Champs"
    }

    @Test
    void testGetByTitle() {

        Prediction savedPrediction = predictionService.getByTitle("5896559342387200")
        assert !savedPrediction
        savedPrediction = predictionService.getByTitle("2019 NBA Champs")

        assert savedPrediction.id
        assert savedPrediction.tags
        assert savedPrediction.completed
        assert savedPrediction.category == "sports"
        assert savedPrediction.title == "2019 NBA Champs"
    }

    void testDelete() {
    }

    //@Test
    void testAddChoice() {
        PredictionChoice predictionChoice = new PredictionChoice()
        predictionChoice.with {
            prompt = "other"
            predictionId = "5896559342387200"
        }
        Prediction savedPrediction = predictionService.get("5896559342387200")
        savedPrediction = predictionService.addChoice(savedPrediction, predictionChoice)

        assert savedPrediction.id
        assert savedPrediction.tags
        assert savedPrediction.completed
        assert savedPrediction.category == "sports"
        assert savedPrediction.title == "2019 NBA Champs"
        assert savedPrediction.choices
    }

    //@Test
    void testAddResponse() {
        PredictionResponse response = new PredictionResponse()
        response.with {
            responseText = "rockets"
        }
        Prediction savedPrediction = predictionService.get("5896559342387200")
        savedPrediction = predictionService.addResponse(savedPrediction, response)
        assert savedPrediction.id
        assert savedPrediction.tags
        assert savedPrediction.completed
        assert savedPrediction.category == "sports"
        assert savedPrediction.title == "2019 NBA Champs"
        assert savedPrediction.responseCount

    }

    //@Test
    void testAddQuestion() {
        PredictionQuestion predictionQuestion = new PredictionQuestion()
        predictionQuestion.prompt = "Who will win the NBA Championship in 2019?"

        Prediction savedPrediction = predictionService.get("5896559342387200")
        savedPrediction = predictionService.addQuestion(savedPrediction, predictionQuestion)

        assert savedPrediction.id
        assert savedPrediction.tags
        assert savedPrediction.completed
        assert savedPrediction.category == "sports"
        assert savedPrediction.title == "2019 NBA Champs"
        assert savedPrediction.id == savedPrediction.questionId
    }
}
