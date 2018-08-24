package com.trevorism.predict.service

import com.trevorism.predict.model.PredictionQuestion

/**
 * @author tbrooks
 */
interface PredictionQuestionService {

    PredictionQuestion create(PredictionQuestion predictionQuestion)
    PredictionQuestion get(String id)
    PredictionQuestion delete(String id)
}