package kotlin

import kotlinx.cinterop.CPointed
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class PointerHolder<T : CPointed>(internal val ptr: CPointer<T>)