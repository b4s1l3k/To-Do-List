package Models.Tasks.persistant

import io.swagger.annotations.{ApiModel, ApiModelProperty}

import java.time.LocalDate

@ApiModel
final case class DoneTask(
                       login: String,
                       @ApiModelProperty(
                         example = "none"
                       )
                       id: Option[Int],
                       title: String,
                       description: String,
                       @ApiModelProperty(
                         dataType = "Date",
                         example = "yyyy-mm-dd",
                         required = true
                       )
                       dueDate: LocalDate,
                       supplement: Option[String],
                       @ApiModelProperty(
                         dataType = "boolean",
                         example = "true",
                         required = true
                       )
                       status: Boolean
                     )

