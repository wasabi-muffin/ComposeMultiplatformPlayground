package tech.fika.compose.multiplatform.playground.service

import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.model.TodoModel
import tech.fika.compose.multiplatform.playground.repository.TodoLocalRepository

interface TodoService {
    suspend fun getTodos(): List<TodoModel>
    suspend fun addTodo(todoModel: TodoModel)
    suspend fun deleteTodo(id: String)
    suspend fun editTodo(id: String, description: String)
}

@Single
internal class DefaultTodoService(private val todoLocalRepository: TodoLocalRepository) : TodoService {
    override suspend fun getTodos(): List<TodoModel> {
        return todoLocalRepository.getTodos()
    }

    override suspend fun addTodo(todoModel: TodoModel) {
        return todoLocalRepository.addTodo(todoModel)
    }

    override suspend fun deleteTodo(id: String) {
        return todoLocalRepository.deleteTodo(id)
    }

    override suspend fun editTodo(id: String, description: String) {
        return todoLocalRepository.editTodo(id, description)
    }
}