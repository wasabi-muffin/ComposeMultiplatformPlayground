package tech.fika.compose.multiplatform.playground.controller

import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import org.koin.core.annotation.Single
import tech.fika.compose.multiplatform.playground.model.TodoModel
import tech.fika.compose.multiplatform.playground.service.TodoService

@Single
internal class TodoController(private val todoService: TodoService) : Controller {
    override fun Routing.routes() = route("/todo") {
        get {
            val todos = todoService.getTodos()
            call.respond(HttpStatusCode.OK, todos)
        }
        post {
            val todo = call.receive<TodoModel>()
            todoService.addTodo(todo)
            call.respond(HttpStatusCode.Created)
        }
        delete("/{id}") {
            val id = call.parameters["id"]
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
            } else {
                todoService.deleteTodo(id = id)
                call.respond(HttpStatusCode.OK)
            }
        }
        put {
            val todo = call.receive<TodoModel>()
            todoService.editTodo(id = todo.id, description = todo.description)
            call.respond(HttpStatusCode.OK)
        }
    }
}
