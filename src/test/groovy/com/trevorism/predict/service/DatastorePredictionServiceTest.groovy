package com.trevorism.predict.service

import com.trevorism.data.Repository
import com.trevorism.predict.model.Prediction
import com.trevorism.predict.model.PredictionChoice
import com.trevorism.predict.model.PredictionQuestion
import com.trevorism.predict.model.PredictionResponse
import org.junit.Before
import org.junit.Test

import java.time.LocalDate
import java.time.ZoneId

/**
 * @author tbrooks
 */
class DatastorePredictionServiceTest {

    PredictionService predictionService
    Prediction prediction

    @Before
    void setup(){
        predictionService = new DatastorePredictionService()
        predictionService.repository = createMockRepository()
        predictionService.choiceService = createMockChoiceService()
        predictionService.questionService = createMockQuestionService()
        predictionService.responseService = createMockResponseService()

        Prediction myPrediction = createPrediction()
        prediction = predictionService.create(myPrediction)
    }

    @Test
    void testCreate() {
        assert prediction.id
        assert prediction.tags
        assert prediction.completed
        assert prediction.category == "sports"
        assert prediction.title == "2019 NBA Champs"
    }

    @Test
    void testGet() {
        Prediction savedPrediction = predictionService.get(prediction.id)

        assert savedPrediction.id == prediction.id
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

        assert savedPrediction.id == prediction.id
        assert savedPrediction.tags
        assert savedPrediction.completed
        assert savedPrediction.category == "sports"
        assert savedPrediction.title == "2019 NBA Champs"
    }

    void testDelete() {
    }

    @Test
    void testAddChoice() {
        PredictionChoice predictionChoice = new PredictionChoice()
        predictionChoice.with {
            prompt = "other"
        }
        Prediction savedPrediction = predictionService.get(prediction.id)
        savedPrediction = predictionService.addChoice(savedPrediction, predictionChoice)

        assert savedPrediction.id == prediction.id
        assert savedPrediction.tags
        assert savedPrediction.completed
        assert savedPrediction.category == "sports"
        assert savedPrediction.title == "2019 NBA Champs"
        assert savedPrediction.choices
    }

    @Test
    void testAddResponse() {
        PredictionResponse response = new PredictionResponse()
        response.with {
            responseText = "rockets"
        }
        Prediction savedPrediction = predictionService.get(prediction.id)
        savedPrediction = predictionService.addResponse(savedPrediction, response)

        assert savedPrediction.id == prediction.id
        assert savedPrediction.tags
        assert savedPrediction.completed
        assert savedPrediction.category == "sports"
        assert savedPrediction.title == "2019 NBA Champs"
        assert savedPrediction.responseCount

    }

    @Test
    void testAddQuestion() {
        PredictionQuestion predictionQuestion = new PredictionQuestion()
        predictionQuestion.prompt = "Who will win the NBA Championship in 2019?"

        Prediction savedPrediction = predictionService.get(prediction.id)
        savedPrediction = predictionService.addQuestion(savedPrediction, predictionQuestion)

        assert savedPrediction.id == prediction.id
        assert savedPrediction.tags
        assert savedPrediction.completed
        assert savedPrediction.category == "sports"
        assert savedPrediction.title == "2019 NBA Champs"
    }


    static Prediction createPrediction() {
        Prediction prediction = new Prediction()
        LocalDate localDate = LocalDate.parse("2019-06-25")
        Date completedDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
        prediction.with {
            title = "2019 NBA Champs"
            category = "sports"
            completed = completedDate
            tags = ["nba", "basketball", "pro", "champion"]
        }
        prediction
    }

    private static PredictionChoiceService createMockChoiceService() {
        return new MockPredictionChoiceService()
    }

    private static PredictionQuestionService createMockQuestionService() {
        return new MockPredictionQuestionService()
    }

    private static PredictionResponseService createMockResponseService() {
        return new MockPredictionResponseService()
    }

    private static Repository<Prediction> createMockRepository() {
        return new MockPredictionRepository()
    }
}
