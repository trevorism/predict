package com.trevorism.predict.service

import com.trevorism.predict.model.PredictionChoice
import com.trevorism.predict.model.PredictionQuestion

/**
 * @author tbrooks
 */
interface PredictionChoiceService {

    PredictionChoice create(PredictionQuestion question, PredictionChoice predictionChoice)
    PredictionChoice get(String id)
    List<PredictionChoice> getForQuestion(PredictionQuestion question)
    PredictionChoice delete(String id)
}