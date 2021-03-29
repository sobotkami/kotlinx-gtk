package nativex

import kotlinx.cinterop.CPointed
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.interpretCPointer

public fun <T : CPointed> CPointer<*>.reinterpretOrNull(): CPointer<T>? = interpretCPointer(this.rawValue)