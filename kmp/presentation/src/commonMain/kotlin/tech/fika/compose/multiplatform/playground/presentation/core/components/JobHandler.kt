package tech.fika.compose.multiplatform.playground.presentation.core.components

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.uuid.Uuid

interface JobHandler : CoroutineScope {
    fun launch(
        key: Any? = null,
        value: suspend CoroutineScope.() -> Unit,
    ): Job

    fun cancel(key: Any)

    fun cancelAll()

    companion object {
        fun default(
            coroutineContext: CoroutineContext = Dispatchers.Default + Job(),
        ): JobHandler = DefaultJobHandler(coroutineContext = coroutineContext)
    }
}

class DefaultJobHandler(
    override val coroutineContext: CoroutineContext = Dispatchers.Default + Job(),
) : JobHandler {
    private val jobMap: MutableMap<Any, Job> = mutableMapOf()

    override fun launch(
        key: Any?,
        value: suspend CoroutineScope.() -> Unit,
    ): Job {
        val id = key ?: Uuid.random().toString()
        cancel(id)
        return launch(block = value).apply {
            jobMap[id] = this
        }
    }

    override fun cancel(key: Any) {
        jobMap.remove(key)?.cancel()
    }

    override fun cancelAll() = jobMap.keys.forEach(::cancel)
}
