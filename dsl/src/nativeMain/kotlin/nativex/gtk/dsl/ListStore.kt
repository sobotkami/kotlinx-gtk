package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gobject.KGType
import nativex.gobject.KGValue
import nativex.gtk.ListStore
import nativex.gtk.TreeModel.TreeIter


/**
 * @see setValue
 */
inline operator fun ListStore.set(iterator: TreeIter, indexToValue: Pair<Int, KGValue>): Unit =
	setValue(iterator, indexToValue.first, indexToValue.second)

/**
 * @see append
 */
inline operator fun ListStore.plusAssign(iterator: TreeIter) =
	append(iterator)

/**
 * @see remove
 */
inline operator fun ListStore.minusAssign(iterator: TreeIter) =
	remove(iterator)

/**
 * Quick way to create a list store and utilize it
 */
@GtkDsl
inline fun listStore(vararg fields: KGType, builder: ListStore.() -> Unit): ListStore =
	ListStore(*fields).apply(builder)