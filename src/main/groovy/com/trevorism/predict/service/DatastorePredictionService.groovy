package com.trevorism.predict.service

import com.trevorism.predict.model.Prediction
import com.trevorism.predict.model.PredictionChoice
import com.trevorism.predict.model.PredictionResponse

/**
 * @author tbrooks
 */
class DatastorePredictionService implements PredictionService {

    @Override
    Prediction create(Prediction prediction) {
        return null
    }

    @Override
    Prediction get(String id) {
        return null
    }

    @Override
    Prediction getByTitle(String title) {
        return null
    }

    @Override
    Prediction delete(String id) {
        return null
    }

    @Override
    Prediction addChoice(Prediction prediction, PredictionChoice choice) {
        return null
    }

    @Override
    Prediction addResponse(Prediction prediction, PredictionResponse response) {
        return null
    }
}
