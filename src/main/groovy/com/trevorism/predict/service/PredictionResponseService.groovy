package com.trevorism.predict.service

import com.trevorism.predict.model.PredictionResponse

/**
 * @author tbrooks
 */
interface PredictionResponseService {

    PredictionResponse create(PredictionResponse predictionResponse)
    PredictionResponse get(String id)
    List<PredictionResponse> getForPrediction(String predictionId)
    PredictionResponse delete(String id)
}
