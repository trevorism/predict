package com.trevorism.predict.service

import com.trevorism.predict.model.PredictionChoice

/**
 * @author tbrooks
 */
class MockPredictionChoiceService implements PredictionChoiceService{

    List<PredictionChoice> choices = []
    static long id = 1000

    @Override
    PredictionChoice create(PredictionChoice predictionChoice) {
        predictionChoice.id = String.valueOf(id++)
        choices << predictionChoice
        return predictionChoice
    }

    @Override
    PredictionChoice get(String id) {
        return choices.find{
            it.id == id
        }
    }

    @Override
    List<PredictionChoice> getForPrediction(String predictionId) {
        choices.findAll{
            it.predictionId == predictionId
        }
    }

    @Override
    PredictionChoice delete(String id) {
        PredictionChoice choice = get(id)
        choices.remove(choice)
        return choice
    }
}
