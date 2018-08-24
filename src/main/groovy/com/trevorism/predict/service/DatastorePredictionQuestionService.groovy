package com.trevorism.predict.service

import com.trevorism.data.PingingDatastoreRepository
import com.trevorism.data.Repository
import com.trevorism.predict.model.PredictionQuestion

/**
 * @author tbrooks
 */
class DatastorePredictionQuestionService implements PredictionQuestionService {

    private Repository<PredictionQuestion> repository = new PingingDatastoreRepository<>(PredictionQuestion)

    @Override
    PredictionQuestion create(PredictionQuestion predictionQuestion) {
        validateQuestion(predictionQuestion)
        predictionQuestion.created = new Date()
        repository.create(predictionQuestion)
    }

    @Override
    PredictionQuestion get(String id) {
        repository.get(id)
    }

    @Override
    PredictionQuestion delete(String id) {
        repository.delete(id)
    }

    private static void validateQuestion(PredictionQuestion predictionQuestion) {
        if (!predictionQuestion.prompt?.trim())
            throw new RuntimeException("Unable to create a question without a prompt")
        if (predictionQuestion.id) {
            try {
                Long.parseLong(predictionQuestion.id)
            } catch (Exception ignore) {
                throw new RuntimeException("Invalid id parameter. Id must be a long value")
            }
        }
    }

}
