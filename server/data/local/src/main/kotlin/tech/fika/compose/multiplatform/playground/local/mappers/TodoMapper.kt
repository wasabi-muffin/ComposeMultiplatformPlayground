package tech.fika.compose.multiplatform.playground.local.mappers

import tech.fika.compose.multiplatform.playground.database.TodoTable
import tech.fika.compose.multiplatform.playground.model.TodoModel

object TodoMapper {
    fun toModel(table: TodoTable): TodoModel = TodoModel(
        id = table.id,
        description = table.description
    )

    fun toTable(model: TodoModel): TodoTable = TodoTable(
        id = model.id,
        description = model.description
    )
}
