package org.gtk.gtk

import gio.GListModel
import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import org.gtk.gio.ListModel
import org.gtk.glib.CStringPointer
import org.gtk.glib.toNullTermCStringArray
import org.gtk.gobject.KGObject

/**
 * gtk-kt
 *
 * 27 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.StringList.html">GtkStringList</a>
 */
class StringList(val stringListPointer: CPointer<GtkStringList>) :
	KGObject(stringListPointer.reinterpret()), ListModel {
	override val listModelPointer: CPointer<GListModel> by lazy { stringListPointer.reinterpret() }

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/ctor.StringList.new.html">gtk_string_list_new</a>
	 */
	constructor(strings: Array<String>) : this(gtk_string_list_new(strings.toNullTermCStringArray())!!)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StringList.append.html">gtk_string_list_append</a>
	 */
	fun append(string: String) {
		gtk_string_list_append(stringListPointer, string)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StringList.get_string.html">gtk_string_list_get_string</a>
	 */
	fun get(position: UInt): String? =
		gtk_string_list_get_string(stringListPointer, position)?.toKString()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StringList.remove.html">gtk_string_list_remove</a>
	 */
	fun remove(position: UInt) {
		gtk_string_list_remove(stringListPointer, position)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StringList.splice.html">gtk_string_list_splice</a>
	 */
	fun splice(position: UInt, nRemovals: UInt, additions: Array<String>) {
		gtk_string_list_splice(stringListPointer, position, nRemovals, additions.toNullTermCStringArray())
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StringList.take.html">gtk_string_list_take</a>
	 */
	fun take(string: CStringPointer) =
		gtk_string_list_take(stringListPointer, string)

}