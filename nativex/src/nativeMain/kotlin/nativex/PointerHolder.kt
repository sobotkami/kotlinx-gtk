package nativex

import kotlinx.cinterop.CPointed
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
@Deprecated("externalize pointer", ReplaceWith("CPointer<T>","kotlinx.cinterop.CPointer"))
class PointerHolder<T : CPointed>
@Deprecated("externalize pointer", ReplaceWith("ptr"))
constructor(internal val ptr: CPointer<T>)