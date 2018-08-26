package com.trevorism.predict.webapi

import com.trevorism.predict.model.Card
import com.trevorism.predict.service.DatastorePredictionServiceTest
import com.trevorism.predict.service.MockPredictionChoiceService
import com.trevorism.predict.service.MockPredictionQuestionService
import com.trevorism.predict.service.PredictionService
import org.junit.Test

/**
 * @author tbrooks
 */
class CardControllerTest {

    @Test
    void testGet() {
        CardController controller = new CardController()
        controller.predictionService = [get:{id -> id ? DatastorePredictionServiceTest.createPrediction() : null}] as PredictionService

        controller.predictionChoiceService = new MockPredictionChoiceService()
        controller.predictionQuestionService = new MockPredictionQuestionService()

        Card card = controller.get("5")

        assert card.id == "5"
        assert card.category == "sports"
        assert card.title == "2019 NBA Champs"

    }
}
