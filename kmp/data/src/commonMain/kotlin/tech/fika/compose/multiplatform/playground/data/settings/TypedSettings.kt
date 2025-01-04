package tech.fika.compose.multiplatform.playground.data.settings

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
    internal sealed class Key<T>(val key: kotlin.String) {
        class Serializable<K>(val name: kotlin.String, val serializer: KSerializer<K>) : Key<K>(key = name)
        class String(val name: kotlin.String) : Key<kotlin.String>(key = name)
        class Int(val name: kotlin.String) : Key<kotlin.Int>(key = name)
        class Long(val name: kotlin.String) : Key<kotlin.Long>(key = name)
        class Float(val name: kotlin.String) : Key<kotlin.Float>(key = name)
        class Double(val name: kotlin.String) : Key<kotlin.Double>(key = name)
        class Boolean(val name: kotlin.String) : Key<kotlin.Boolean>(key = name)
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
        this[key] ?: throw DomainError.Local.MissingSettings()

    internal inline operator fun <reified T : Any> Settings.set(key: Key<T>, value: T) = when (key) {
        is Key.Serializable -> encodeValue(serializer = key.serializer, key = key.name, value = value)
        else -> set(key = key.key, value = value)
    }

    internal fun Settings.remove(vararg keys: Key<*>) {
        keys.forEach { key ->
            when (key) {
                is Key.Serializable<*> -> removeValue(serializer = key.serializer, key = key.key)
                else -> remove(key = key.key)
            }
        }
    }

    internal fun Settings.contains(key: Key<*>): Boolean = when (key) {
        is Key.Serializable<*> -> containsValue(serializer = key.serializer, key = key.key)
        else -> contains(key = key.key)
    }
}
