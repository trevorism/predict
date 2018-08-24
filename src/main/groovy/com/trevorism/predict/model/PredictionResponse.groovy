package com.trevorism.predict.model

import io.swagger.annotations.ApiModelProperty

/**
 * @author tbrooks
 */
abstract class PredictionResponse {

    @ApiModelProperty(value = "An unique id, use a number for this field", dataType = "string")
    String id
    @ApiModelProperty(value = "The id of the prediction associated with this response", dataType = "string")
    String predictionId
    @ApiModelProperty(value = "Date the question was created", dataType = "date")
    Date created

    abstract def getResponse()
}
