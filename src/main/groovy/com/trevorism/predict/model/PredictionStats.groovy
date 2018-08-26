package com.trevorism.predict.model

/**
 * @author tbrooks
 */
class PredictionStats {
    Map<String, Integer> aggregates = [:]
    PredictionChoice selectedChoice

    static PredictionStats fromAllResponses(List<PredictionChoice> predictionChoices, List<PredictionResponse> predictionResponses) {
        PredictionStats stats = new PredictionStats()
        predictionChoices.each {
            stats.aggregates.put(it.prompt, 0)
        }
        predictionResponses.each {
            Integer count = stats.aggregates.get(it.responseText)
            if(count != null){
                stats.aggregates.put(it.responseText, count + 1)
            }
        }

        return stats
    }
}
