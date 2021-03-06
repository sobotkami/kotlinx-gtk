package nativex.glib

import kotlinx.cinterop.CPointed
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.interpretCPointer

fun <T : CPointed> CPointer<*>.reinterpretOrNull(): CPointer<T>? = interpretCPointer(this.rawValue)