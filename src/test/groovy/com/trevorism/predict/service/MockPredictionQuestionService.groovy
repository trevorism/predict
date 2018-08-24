package com.trevorism.predict.service

import com.trevorism.predict.model.PredictionQuestion

/**
 * @author tbrooks
 */
class MockPredictionQuestionService implements PredictionQuestionService{

    List<PredictionQuestion> questions = []
    static long id = 1

    @Override
    PredictionQuestion create(PredictionQuestion predictionQuestion) {
        predictionQuestion.id = String.valueOf(id++)
        questions.add(predictionQuestion)
        return predictionQuestion
    }

    @Override
    PredictionQuestion get(String id) {
        return questions.find{
            it.id == id
        }
    }

    @Override
    PredictionQuestion delete(String id) {
        PredictionQuestion question = get(id)
        questions.remove(question)
        return question
    }
}
