package com.trevorism.predict.service

import com.trevorism.predict.model.PredictionResponse

/**
 * @author tbrooks
 */
class MockPredictionResponseService implements PredictionResponseService{

    List<PredictionResponse> responses = []
    static long id = 100

    @Override
    PredictionResponse create(PredictionResponse predictionResponse) {
        predictionResponse.id = String.valueOf(id++)
        responses << predictionResponse
        return predictionResponse
    }

    @Override
    PredictionResponse get(String id) {
        return responses.find{
            it.id == id
        }
    }

    @Override
    List<PredictionResponse> getForPrediction(String predictionId) {
        responses.findAll{
            it.predictionId == predictionId
        }
    }

    @Override
    PredictionResponse delete(String id) {
        PredictionResponse response = get(id)
        responses.remove(response)
        return response
    }
}
