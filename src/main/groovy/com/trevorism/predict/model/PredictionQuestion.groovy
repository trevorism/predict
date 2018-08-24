package com.trevorism.predict.model

import io.swagger.annotations.ApiModelProperty

/**
 * @author tbrooks
 */
class PredictionQuestion {

    @ApiModelProperty(value = "An unique id, use a number for this field", dataType = "string")
    String id
    @ApiModelProperty(value = "A question of the prediction", dataType = "string")
    String prompt
    @ApiModelProperty(value = "Date the question was created", dataType = "date")
    Date created

}
