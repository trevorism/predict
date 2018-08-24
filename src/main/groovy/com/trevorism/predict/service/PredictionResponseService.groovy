package com.trevorism.predict.service

import com.trevorism.predict.model.PredictionQuestion
import com.trevorism.predict.model.PredictionResponse

/**
 * @author tbrooks
 */
interface PredictionResponseService {

    PredictionResponse create(PredictionQuestion question, PredictionResponse predictionResponse)
    PredictionResponse get(String id)
    List<PredictionResponse> getForQuestion(PredictionQuestion question)
    PredictionResponse delete(String id)
}
