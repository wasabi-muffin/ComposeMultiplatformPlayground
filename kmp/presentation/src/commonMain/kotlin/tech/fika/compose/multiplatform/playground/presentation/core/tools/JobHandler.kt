package tech.fika.compose.multiplatform.playground.presentation.core.tools

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.uuid.Uuid

interface JobHandler : CoroutineScope {
    fun launch(
        key: String? = null,
        coroutineScope: CoroutineScope = this,
        block: suspend CoroutineScope.() -> Unit,
    ): Job

    fun cancel(key: String)

    fun cancelAll()
}

class DefaultJobHandler(
    override val coroutineContext: CoroutineContext = Dispatchers.Default + Job(),
) : JobHandler {
    private val jobMap: MutableMap<String, Job> = mutableMapOf()

    override fun launch(
        key: String?,
        coroutineScope: CoroutineScope,
        block: suspend CoroutineScope.() -> Unit,
    ): Job {
        val id = key ?: Uuid.random().toString()
        cancel(id)
        return coroutineScope.launch(block = block).apply {
            jobMap[id] = this
        }
    }

    override fun cancel(key: String) {
        jobMap.remove(key)?.cancel()
    }

    override fun cancelAll() = jobMap.keys.forEach(::cancel)
}