package com.trevorism.predict.service

import com.trevorism.predict.model.Prediction
import com.trevorism.predict.model.PredictionChoice
import com.trevorism.predict.model.PredictionResponse

/**
 * @author tbrooks
 */
interface PredictionService {

    Prediction create(Prediction prediction)
    Prediction get(String id)
    Prediction getByTitle(String title)
    Prediction delete(String id)
    Prediction addChoice(Prediction prediction, PredictionChoice choice)
    Prediction addResponse(Prediction prediction, PredictionResponse response)


}