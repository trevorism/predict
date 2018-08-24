package com.trevorism.predict.model

import io.swagger.annotations.ApiModelProperty

/**
 * @author tbrooks
 */
class Prediction {

    @ApiModelProperty(value = "An unique id, use a number for this field", dataType = "string")
    String id
    @ApiModelProperty(value = "A title for the prediction container", dataType = "string")
    String title
    @ApiModelProperty(value = "A category for the prediction topic", dataType = "string")
    String category
    @ApiModelProperty(value = "Date the question will be answered definitively", dataType = "date")
    Date completed
    @ApiModelProperty(value = "Help to find the question")
    List<String> tags = []
    @ApiModelProperty(value = "The type of answer possible (true/false | date | score | etc.", dataType = "string")
    String answerType
    @ApiModelProperty(value = "The question for the prediction", dataType = "string")
    String questionId
    @ApiModelProperty(value = "The valid choices for the prediction")
    List<String> choices = []
    @ApiModelProperty(value = "The number of responses", dataType = "integer")
    int responseCount = 0
}
