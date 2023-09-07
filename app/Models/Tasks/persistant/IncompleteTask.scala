package Models.Tasks.persistant

import io.swagger.annotations.{ApiModel, ApiModelProperty}

import java.time.LocalDate

@ApiModel
case class IncompleteTask(
                           login: String,
                           title: String,
                           description: String,
                           @ApiModelProperty(
                             dataType = "Date",
                             example = "yyyy-mm-dd",
                             required = true
                           )
                           dueDate: LocalDate,
                           @ApiModelProperty(
                             dataType = "string",
                             example = "Option[String]"

                           )
                           supplement: Option[String]
                         )