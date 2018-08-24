package com.trevorism.predict.service

import com.trevorism.data.FastDatastoreRepository
import com.trevorism.data.PingingDatastoreRepository
import com.trevorism.data.Repository
import com.trevorism.predict.model.Prediction
import com.trevorism.predict.model.PredictionResponse

/**
 * @author tbrooks
 */
class DatastorePredictionResponseService implements PredictionResponseService {

    private Repository<PredictionResponse> repository = new PingingDatastoreRepository<>(PredictionResponse)
    private Repository<PredictionResponse> fastRepo = new FastDatastoreRepository<>(PredictionResponse)

    @Override
    PredictionResponse create(PredictionResponse predictionResponse) {
        validateResponse(predictionResponse)
        predictionResponse.created = new Date()
        repository.create(predictionResponse)
    }

    @Override
    PredictionResponse get(String id) {
        repository.get(id)
    }

    @Override
    List<PredictionResponse> getForPrediction(String predictionId) {
        repository.list().findAll{
            it.predictionId == predictionId
        }
    }

    @Override
    PredictionResponse delete(String id) {
        repository.delete(id)
    }

    private static void validateResponse(PredictionResponse predictionResponse) {
        if (!predictionResponse.responseText)
            throw new RuntimeException("Unable to create a response with no value")
        if (predictionResponse.id) {
            try {
                Long.parseLong(predictionResponse.id)
            } catch (Exception ignore) {
                throw new RuntimeException("Invalid id parameter. Id must be a long value")
            }
        }
    }
}
