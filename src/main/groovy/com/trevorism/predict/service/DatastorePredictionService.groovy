package com.trevorism.predict.service

import com.trevorism.data.PingingDatastoreRepository
import com.trevorism.data.Repository
import com.trevorism.predict.model.Prediction
import com.trevorism.predict.model.PredictionChoice
import com.trevorism.predict.model.PredictionQuestion
import com.trevorism.predict.model.PredictionResponse

/**
 * @author tbrooks
 */
class DatastorePredictionService implements PredictionService {

    private Repository<Prediction> repository = new PingingDatastoreRepository<>(Prediction)
    private PredictionChoiceService choiceService = new DatastorePredictionChoiceService()
    private PredictionQuestionService questionService = new DatastorePredictionQuestionService()
    private PredictionResponseService responseService = new DatastorePredictionResponseService()

    @Override
    Prediction create(Prediction prediction) {
        validatePrediction(prediction)
        repository.create(prediction)
    }

    @Override
    Prediction get(String id) {
        repository.get(id)
    }

    @Override
    List<Prediction> list() {
        repository.list()
    }

    @Override
    Prediction getByTitle(String title) {
        repository.list().find{
            it.title == title
        }
    }

    @Override
    Prediction delete(String id) {
        questionService.delete(id)
        choiceService.getForPrediction(id).each {
            choiceService.delete(it.id)
        }
        responseService.getForPrediction(id).each {
            responseService.delete(it.id)
        }

        repository.delete(id)
    }

    @Override
    Prediction addChoice(Prediction prediction, PredictionChoice choice) {
        choice.predictionId = prediction.id
        PredictionChoice savedChoice = choiceService.create(choice)
        prediction.choices.add(savedChoice.id)
        repository.update(prediction.id, prediction)
    }

    @Override
    Prediction addResponse(Prediction prediction, PredictionResponse response) {
        response.predictionId = prediction.id
        responseService.create(response)
        prediction.responseCount++
        repository.update(prediction.id, prediction)
    }

    @Override
    Prediction addQuestion(Prediction prediction, PredictionQuestion question) {
        question.predictionId = prediction.id
        PredictionQuestion savedQuestion = questionService.create(question)
        prediction.questionId = savedQuestion.id
        repository.update(prediction.id, prediction)
    }

    private static void validatePrediction(Prediction prediction) {
        if (prediction.id) {
            try {
                Long.parseLong(prediction.id)
            } catch (Exception ignore) {
                throw new RuntimeException("Invalid id parameter. Id must be a long value")
            }
        }
    }
}
