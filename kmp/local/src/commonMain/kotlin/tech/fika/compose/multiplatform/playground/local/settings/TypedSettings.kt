package tech.fika.compose.multiplatform.playground.local.settings

import com.russhwolf.settings.Settings
import com.russhwolf.settings.contains
import com.russhwolf.settings.serialization.containsValue
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import com.russhwolf.settings.serialization.removeValue
import com.russhwolf.settings.set
import kotlinx.serialization.KSerializer
import tech.fika.compose.multiplatform.playground.domain.core.DomainError

internal object TypedSettings {
    internal sealed class Key<T>(val name: kotlin.String) {
        class Serializable<K>(name: kotlin.String, val serializer: KSerializer<K>) : Key<K>(name = name)
        class String(name: kotlin.String) : Key<kotlin.String>(name = name)
        class Int(name: kotlin.String) : Key<kotlin.Int>(name = name)
        class Long(name: kotlin.String) : Key<kotlin.Long>(name = name)
        class Float(name: kotlin.String) : Key<kotlin.Float>(name = name)
        class Double(name: kotlin.String) : Key<kotlin.Double>(name = name)
        class Boolean(name: kotlin.String) : Key<kotlin.Boolean>(name = name)
    }

    internal inline operator fun <reified T : Any> Settings.get(key: Key<T>): T? = when (key) {
        is Key.Serializable -> decodeValueOrNull(serializer = key.serializer, key = key.name)
        is Key.String -> getStringOrNull(key = key.name) as? T
        is Key.Int -> getIntOrNull(key = key.name) as? T
        is Key.Float -> getLongOrNull(key = key.name) as? T
        is Key.Long -> getFloatOrNull(key = key.name) as? T
        is Key.Double -> getDoubleOrNull(key = key.name) as? T
        is Key.Boolean -> getBooleanOrNull(key = key.name) as? T
    }

    internal inline fun <reified T : Any> Settings.getOrThrow(key: Key<T>): T =
        this[key] ?: throw DomainError.Local.MissingSettings(message = "Key [${key.name}] was not found")

    internal inline operator fun <reified T : Any> Settings.set(key: Key<T>, value: T) = when (key) {
        is Key.Serializable -> encodeValue(serializer = key.serializer, key = key.name, value = value)
        else -> set(key = key.name, value = value)
    }

    internal fun Settings.remove(vararg keys: Key<*>) {
        keys.forEach { key ->
            when (key) {
                is Key.Serializable<*> -> removeValue(serializer = key.serializer, key = key.name)
                else -> remove(key = key.name)
            }
        }
    }

    internal fun Settings.contains(key: Key<*>): Boolean = when (key) {
        is Key.Serializable<*> -> containsValue(serializer = key.serializer, key = key.name)
        else -> contains(key = key.name)
    }
}
