package com.trevorism.predict.service

import com.trevorism.data.Repository
import com.trevorism.predict.model.Prediction

/**
 * @author tbrooks
 */
class MockPredictionRepository implements Repository<Prediction> {

    List<Prediction> predictions = []
    private static int id = 10000

    @Override
    List<Prediction> list() {
        list(null)
    }

    @Override
    List<Prediction> list(String s) {
        predictions
    }

    @Override
    Prediction get(String s) {
        get(s, null)
    }

    @Override
    Prediction get(String s, String s1) {
        predictions.find{
            it.id == s
        }
    }

    @Override
    Prediction create(Prediction prediction) {
        create(prediction, null)
    }

    @Override
    Prediction create(Prediction prediction, String s) {
        prediction.id = id++
        predictions << prediction
        return prediction
    }

    @Override
    Prediction update(String s, Prediction prediction) {
        update(s, prediction, null)
    }

    @Override
    Prediction update(String s, Prediction prediction, String s1) {
        delete(s)
        predictions << prediction
        return prediction
    }

    @Override
    Prediction delete(String s) {
        delete(s, null)
    }

    @Override
    Prediction delete(String s, String s1) {
        def prediction = get(s)
        predictions.remove(prediction)
        return prediction
    }

    @Override
    void ping() {

    }
}
