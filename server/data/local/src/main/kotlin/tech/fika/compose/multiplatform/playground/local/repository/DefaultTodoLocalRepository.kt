package tech.fika.compose.multiplatform.playground.local.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.database.Database
import tech.fika.compose.multiplatform.playground.local.mappers.TodoMapper
import tech.fika.compose.multiplatform.playground.model.TodoModel
import tech.fika.compose.multiplatform.playground.repository.TodoLocalRepository

@Single
internal class DefaultTodoLocalRepository(
    private val database: Database,
) : TodoLocalRepository {
    override suspend fun getTodos(): List<TodoModel> = withContext(Dispatchers.IO) {
        database.sampleTableQueries.getAll().executeAsList().map(TodoMapper::toModel)
    }

    override suspend fun addTodo(todoModel: TodoModel) = withContext(Dispatchers.IO) {
        database.sampleTableQueries.insert(todoModel.let(TodoMapper::toTable))
    }

    override suspend fun deleteTodo(id: String) = withContext(Dispatchers.IO) {
        database.sampleTableQueries.delete(id = id)
    }

    override suspend fun editTodo(id: String, description: String) = withContext(Dispatchers.IO) {
        database.sampleTableQueries.update(description = description, id = id)
    }
}
