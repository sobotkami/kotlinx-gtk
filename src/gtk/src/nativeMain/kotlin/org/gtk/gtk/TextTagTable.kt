package org.gtk.gtk

import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.*
import org.gtk.glib.bool
import org.gtk.gobject.KGObject
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback
import org.gtk.gtk.TextTag.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 19 / 03 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.TextTagTable.html">GtkTextTagTable</a>
 */
class TextTagTable(
	val textTagTablePointer: CPointer<GtkTextTagTable>
) : KGObject(textTagTablePointer.reinterpret()) {

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/ctor.TextTagTable.new.html">gtk_text_tag_table_new</a>
	 */
	constructor() : this(gtk_text_tag_table_new()!!.reinterpret())

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextTagTable.add.html">gtk_text_tag_table_add</a>
	 */
	fun add(tag: TextTag) =
		gtk_text_tag_table_add(textTagTablePointer, tag.textTagPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextTagTable.foreach.html">gtk_text_tag_table_foreach</a>
	 */
	fun forEach(action: TextTagTableForeachFunction) {
		gtk_text_tag_table_foreach(
			textTagTablePointer,
			staticTextTagTableForeachFunction,
			StableRef.create(action).asCPointer()
		)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextTagTable.get_size.html">gtk_text_tag_table_get_size</a>
	 */
	val size: Int
		get() = gtk_text_tag_table_get_size(textTagTablePointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextTagTable.lookup.html">gtk_text_tag_table_lookup</a>
	 */
	fun lookup(name: String): TextTag? =
		gtk_text_tag_table_lookup(textTagTablePointer, name).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextTagTable.remove.html">gtk_text_tag_table_remove</a>
	 */
	fun remove(tag: TextTag) {
		gtk_text_tag_table_remove(textTagTablePointer, tag.textTagPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextTagTable.tag-added.html">tag-added</a>
	 */
	fun addOnTagAddedCallback(action: (TextTag) -> Unit) =
		addSignalCallback(Signals.TAG_ADDED, action, staticTagCallback)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextTagTable.tag-changed.html">tag-changed</a>
	 */
	fun addOnTagChangedCallback(action: TextTagTableTagChangedFunction) =
		addSignalCallback(Signals.TAG_CHANGED, action, staticTagChangedCallback)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextTagTable.tag-removed.html">tag-removed</a>
	 */
	fun addOnTagRemovedCallback(action: (TextTag) -> Unit) =
		addSignalCallback(Signals.TAG_REMOVED, action, staticTagCallback)

	companion object {
		private val staticTextTagTableForeachFunction: GtkTextTagTableForeach =
			staticCFunction { tag, data ->
				data?.asStableRef<TextTagTableForeachFunction>()?.get()?.invoke(tag!!.wrap())
				Unit
			}

		private val staticTagCallback: GCallback =
			staticCFunction { _: gpointer, tag: CPointer<GtkTextTag>, data: gpointer ->
				data.asStableRef<(TextTag) -> Unit>().get().invoke(tag.wrap())
			}.reinterpret()

		private val staticTagChangedCallback: GCallback =
			staticCFunction { _: gpointer, tag: CPointer<GtkTextTag>, changed: Boolean, data: gpointer ->
				data.asStableRef<TextTagTableTagChangedFunction>().get().invoke(tag.wrap(), changed)
			}.reinterpret()
	}
}