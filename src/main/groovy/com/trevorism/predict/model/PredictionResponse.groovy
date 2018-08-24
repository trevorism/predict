package com.trevorism.predict.model

import io.swagger.annotations.ApiModelProperty

/**
 * @author tbrooks
 */
class PredictionResponse {

    @ApiModelProperty(value = "An unique id, use a number for this field", dataType = "string")
    String id
    @ApiModelProperty(value = "The response text", dataType = "string")
    String responseText
    @ApiModelProperty(value = "The id of the prediction associated with this response", dataType = "string")
    String predictionId
    @ApiModelProperty(value = "Date the response was created", dataType = "date")
    Date created
}
