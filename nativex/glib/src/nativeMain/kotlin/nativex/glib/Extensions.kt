package nativex.glib

import kotlinx.cinterop.CPointed
import kotlinx.cinterop.CPointer


/**
 * Use a pointer, then the pointer will be freed afterwards
 *
 * @see [CPointer.free]
 * @return return the result from [action]
 */
inline fun <I : CPointed, O> CPointer<I>.use(action: (CPointer<I>) -> O): O = let(action).also { free() }