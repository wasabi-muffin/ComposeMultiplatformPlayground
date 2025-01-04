package tech.fika.compose.multiplatform.playground.repository

import tech.fika.compose.multiplatform.playground.model.TodoModel

interface TodoLocalRepository {
    suspend fun getTodos(): List<TodoModel>
    suspend fun addTodo(todoModel: TodoModel)
    suspend fun deleteTodo(id: String)
    suspend fun editTodo(id: String, description: String)
}
