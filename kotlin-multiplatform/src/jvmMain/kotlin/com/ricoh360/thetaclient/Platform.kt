/*
 * JVM stub implementation - Desktop not supported
 * This exists only to allow theta-client to compile for JVM target
 * so that projects using theta-client can have jvm() target
 */
package com.ricoh360.thetaclient

import java.lang.ref.WeakReference as JavaWeakReference
import java.util.UUID

/**
 * describe platform
 */
actual class Platform actual constructor() {
    actual val platform: String = "JVM (stub - not supported)"
}

/**
 * JVM stub - not functional on desktop
 */
actual typealias FrameSource = ByteArray

/**
 * JVM stub - not functional on desktop
 */
actual fun frameFrom(packet: Pair<ByteArray, Int>): FrameSource {
    throw UnsupportedOperationException("Theta client is not supported on JVM/Desktop. Use Android or iOS targets.")
}

actual fun randomUUID(): String {
    return UUID.randomUUID().toString()
}

actual fun currentTimeMillis(): Long {
    return System.currentTimeMillis()
}

actual typealias WeakReference<T> = JavaWeakReference<T>
