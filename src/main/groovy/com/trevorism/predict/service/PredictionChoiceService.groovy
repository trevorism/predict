package com.trevorism.predict.service

import com.trevorism.predict.model.PredictionChoice

/**
 * @author tbrooks
 */
interface PredictionChoiceService {

    PredictionChoice create(PredictionChoice predictionChoice)
    PredictionChoice get(String id)
    List<PredictionChoice> getForPrediction(String predictionId)
    PredictionChoice delete(String id)
}