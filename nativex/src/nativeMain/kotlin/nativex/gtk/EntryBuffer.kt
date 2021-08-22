package nativex.gtk
import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.*
import nativex.gobject.KGObject
import nativex.glib.CString
import nativex.gobject.SignalManager
import nativex.gobject.Signals
import nativex.gobject.connectSignal

/**
 * kotlinx-gtk
 *
 * 30 / 06 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html">GtkEntryBuffer</a>
 */
class EntryBuffer(val entryBufferPointer: CPointer<GtkEntryBuffer>) : KGObject(entryBufferPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html#gtk-entry-buffer-new">
	 *     gtk_entry_buffer_new</a>
	 */
	constructor(initialCharacters: String? = null) : this(
		gtk_entry_buffer_new(initialCharacters, initialCharacters?.length ?: -1)!!
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html#gtk-entry-buffer-get-text">
	 *     gtk_entry_buffer_get_text</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html#gtk-entry-buffer-set-text">
	 *     gtk_entry_buffer_set_text</a>
	 */
	var text: String
		get() = gtk_entry_buffer_get_text(entryBufferPointer)!!.toKString()
		set(value) = gtk_entry_buffer_set_text(entryBufferPointer, value, value.length)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html#gtk-entry-buffer-get-bytes">
	 *     gtk_entry_buffer_get_bytes</a>
	 */
	val bytes: ULong
		get() = gtk_entry_buffer_get_bytes(entryBufferPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html#gtk-entry-buffer-get-max-length">
	 *     gtk_entry_buffer_get_max_length</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html#gtk-entry-buffer-set-max-length">
	 *     gtk_entry_buffer_set_max_length</a>
	 */
	var maxLength: Int
		get() = gtk_entry_buffer_get_max_length(entryBufferPointer)
		set(value) = gtk_entry_buffer_set_max_length(entryBufferPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html#gtk-entry-buffer-insert-text">
	 *     gtk_entry_buffer_insert_text</a>
	 */
	fun insertText(position: UInt, chars: String) {
		gtk_entry_buffer_insert_text(entryBufferPointer, position, chars, chars.length)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html#gtk-entry-buffer-delete-text">
	 *     gtk_entry_buffer_delete_text</a>
	 */
	fun deleteText(position: UInt, count: Int) {
		gtk_entry_buffer_delete_text(entryBufferPointer, position, count)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html#gtk-entry-buffer-emit-deleted-text">
	 *     gtk_entry_buffer_emit_deleted_text</a>
	 */
	fun emitDeletedText(position: UInt, count: UInt) {
		gtk_entry_buffer_emit_deleted_text(entryBufferPointer, position, count)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html#gtk-entry-buffer-emit-inserted-text">
	 *     gtk_entry_buffer_emit_inserted_text</a>
	 */
	fun emitInsertedText(position: UInt, chars: String) {
		gtk_entry_buffer_emit_inserted_text(entryBufferPointer, position, chars, chars.length.toUInt())
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html#GtkEntryBuffer-deleted-text">
	 *     deleted-text</a>
	 */
	fun addDeletedTextCallback(action: DeletedTextFunction) =
		SignalManager(
			entryBufferPointer,
			entryBufferPointer.connectSignal(
				Signals.DELETED_TEXT,
				StableRef.create(action).asCPointer(),
				staticDeletedTextFunction
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html#GtkEntryBuffer-inserted-text">
	 *     inserted-text</a>
	 */
	fun addInsertedTextCallback(action: InsertedTextFunction) =
		SignalManager(
			entryBufferPointer,
			entryBufferPointer.connectSignal(
				Signals.INSERTED_TEXT,
				StableRef.create(action).asCPointer(),
				staticInsertedTextFunction
			)
		)

	companion object {
		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html#GtkEntryBuffer-deleted-text">
		 *     deleted-text</a>
		 */
		val staticDeletedTextFunction: GCallback =
			staticCFunction { _: gpointer, position: UInt, nChars: UInt, data: gpointer? ->
				data?.asStableRef<DeletedTextFunction>()?.get()?.invoke(position, nChars)
				Unit
			}.reinterpret()

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html#GtkEntryBuffer-inserted-text">
		 *     inserted-text</a>
		 */
		val staticInsertedTextFunction: GCallback by lazy {
			staticCFunction { _: gpointer, position: UInt, chars: CString, nChars: UInt, data: gpointer? ->
				data?.asStableRef<InsertedTextFunction>()?.get()?.invoke(position, chars.toKString(), nChars)
				Unit
			}.reinterpret()
		}

		fun CPointer<GtkEntryBuffer>?.wrap() =
			this?.wrap()

		fun CPointer<GtkEntryBuffer>.wrap() =
			EntryBuffer(this)
	}
}

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html#GtkEntryBuffer-deleted-text">
 *     deleted-text</a>
 */
typealias DeletedTextFunction = (
	@ParameterName("position") UInt,
	UInt
) -> Unit

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEntryBuffer.html#GtkEntryBuffer-inserted-text">
 *     inserted-text</a>
 */
typealias InsertedTextFunction = (
	@ParameterName("position") UInt,
	@ParameterName("chars") String,
	@ParameterName("nChars") UInt
) -> Unit