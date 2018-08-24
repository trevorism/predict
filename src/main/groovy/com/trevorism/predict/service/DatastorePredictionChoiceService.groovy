package com.trevorism.predict.service

import com.trevorism.data.FastDatastoreRepository
import com.trevorism.data.PingingDatastoreRepository
import com.trevorism.data.Repository
import com.trevorism.predict.model.Prediction
import com.trevorism.predict.model.PredictionChoice

/**
 * @author tbrooks
 */
class DatastorePredictionChoiceService implements PredictionChoiceService {

    private Repository<PredictionChoice> repository = new PingingDatastoreRepository<>(PredictionChoice)
    private Repository<PredictionChoice> fastRepo = new FastDatastoreRepository<>(PredictionChoice)

    @Override
    PredictionChoice create(PredictionChoice predictionChoice) {
        validateChoice(predictionChoice)
        predictionChoice.created = new Date()
        repository.create(predictionChoice)
    }

    @Override
    PredictionChoice get(String id) {
        repository.get(id)
    }

    @Override
    List<PredictionChoice> getForPrediction(String predictionId) {
        repository.list().findAll{
            it.predictionId == predictionId
        }
    }

    @Override
    PredictionChoice delete(String id) {
        repository.delete(id)
    }

    private static void validateChoice(PredictionChoice predictionChoice) {
        if (!predictionChoice.prompt?.trim())
            throw new RuntimeException("Unable to create a choice without a prompt")
        if (predictionChoice.id) {
            try {
                Long.parseLong(predictionChoice.id)
            } catch (Exception ignore) {
                throw new RuntimeException("Invalid id parameter. Id must be a long value")
            }
        }
    }

}
