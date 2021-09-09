package org.gtk.gtk

import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.*
import org.gtk.gdk.Clipboard
import org.gtk.gdk.Clipboard.Companion.wrap
import org.gtk.gdk.ContentProvider
import org.gtk.gdk.ContentProvider.Companion.wrap
import org.gtk.gdk.Paintable
import org.gtk.gdk.Paintable.Companion.wrap
import org.gtk.glib.CStringPointer
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gobject.KGObject
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback
import org.gtk.gtk.TextChildAnchor.Companion.wrap
import org.gtk.gtk.TextIter.Companion.wrap
import org.gtk.gtk.TextMark.Companion.wrap
import org.gtk.gtk.TextTag.Companion.wrap
import org.gtk.gtk.TextTagTable.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 19 / 03 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.TextBuffer.html">GtkTextBuffer</a>
 */
class TextBuffer(
	val buffer: CPointer<GtkTextBuffer>
) : KGObject(buffer.reinterpret()) {

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/ctor.TextBuffer.new.html">
	 *     gtk_text_buffer_new</a>
	 */
	constructor(textTagTable: TextTagTable) : this(
		gtk_text_buffer_new(
			textTagTable.textTagTablePointer
		)!!.reinterpret()
	)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.add_mark.html">
	 *     gtk_text_buffer_add_mark</a>
	 */
	fun addMark(mark: TextMark, where: TextIter) {
		gtk_text_buffer_add_mark(buffer, mark.markPointer, where.pointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.add_selection_clipboard.html">
	 *     gtk_text_buffer_add_selection_clipboard</a>
	 */
	fun addSelectionClipboard(clipboard: Clipboard) {
		gtk_text_buffer_add_selection_clipboard(buffer, clipboard.clipboardPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.apply_tag.html">gtk_text_buffer_apply_tag</a>
	 */
	fun applyTag(tag: TextTag, start: TextIter, end: TextIter) {
		gtk_text_buffer_apply_tag(buffer, tag.textTagPointer, start.pointer, end.pointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.apply_tag_by_name.html">gtk_text_buffer_apply_tag_by_name</a>
	 */
	fun applyTagByName(name: String, start: TextIter, end: TextIter) {
		gtk_text_buffer_apply_tag_by_name(buffer, name, start.pointer, end.pointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.backspace.html">gtk_text_buffer_backspace</a>
	 */
	fun backspace(iter: TextIter, interactive: Boolean, defaultEditable: Boolean): Boolean =
		gtk_text_buffer_backspace(buffer, iter.pointer, interactive.gtk, defaultEditable.gtk).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.begin_irreversible_action.html">gtk_text_buffer_begin_irreversible_action</a>
	 */
	fun beginIrreversibleAction() {
		gtk_text_buffer_begin_irreversible_action(buffer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.begin_user_action.html">gtk_text_buffer_begin_user_action</a>
	 */
	fun beginUserAction() {
		gtk_text_buffer_begin_user_action(buffer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.copy_clipboard.html">gtk_text_buffer_copy_clipboard</a>
	 */
	fun copyClipboard(clipboard: Clipboard) {
		gtk_text_buffer_copy_clipboard(buffer, clipboard.clipboardPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.create_child_anchor.html">gtk_text_buffer_create_child_anchor</a>
	 */
	fun createChildAnchor(iter: TextIter): TextChildAnchor =
		gtk_text_buffer_create_child_anchor(buffer, iter.pointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.create_mark.html">gtk_text_buffer_create_mark</a>
	 */
	fun createMark(markName: String?, where: TextIter, leftGravity: Boolean): TextMark =
		gtk_text_buffer_create_mark(buffer, markName, where.pointer, leftGravity.gtk)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.create_tag.html">gtk_text_buffer_create_tag</a>
	 */
	fun createTag(tagName: String): TextTag =
		gtk_text_buffer_create_tag(buffer, tagName, null)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.cut_clipboard.html">gtk_text_buffer_cut_clipboard</a>
	 */
	fun cutClipboard(clipboard: Clipboard, defaultEditable: Boolean) {
		gtk_text_buffer_cut_clipboard(buffer, clipboard.clipboardPointer, defaultEditable.gtk)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.delete.html">gtk_text_buffer_delete</a>
	 */
	fun delete(start: TextIter, end: TextIter) {
		gtk_text_buffer_delete(buffer, start.pointer, end.pointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.delete_interactive.html">gtk_text_buffer_delete_interactive</a>
	 */
	fun deleteInteractive(startIter: TextIter, endIter: TextIter, defaultEditable: Boolean) =
		gtk_text_buffer_delete_interactive(buffer, startIter.pointer, endIter.pointer, defaultEditable.gtk).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.delete_mark.html">gtk_text_buffer_delete_mark</a>
	 */
	fun deleteMark(mark: TextMark) {
		gtk_text_buffer_delete_mark(buffer, mark.markPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.delete_mark_by_name.html">gtk_text_buffer_delete_mark_by_name</a>
	 */
	fun deleteMarkByName(name: String) {
		gtk_text_buffer_delete_mark_by_name(buffer, name)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.delete_selection.html">gtk_text_buffer_delete_selection</a>
	 */
	fun deleteSelection(interactive: Boolean, defaultEditable: Boolean) {
		gtk_text_buffer_delete_selection(buffer, interactive.gtk, defaultEditable.gtk)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.end_irreversible_action.html">gtk_text_buffer_end_irreversible_action</a>
	 */
	fun endIrreversibleAction() {
		gtk_text_buffer_end_irreversible_action(buffer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.end_user_action.html">gtk_text_buffer_end_user_action</a>
	 */
	fun endUserAction() {
		gtk_text_buffer_end_user_action(buffer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_bounds.html">gtk_text_buffer_get_bounds</a>
	 */
	val bounds: Pair<TextIter, TextIter>
		get() = memScoped {
			val start = cValue<GtkTextIter>()
			val end = cValue<GtkTextIter>()
			gtk_text_buffer_get_bounds(buffer, start, end)
			start.ptr.wrap() to end.ptr.wrap()
		}


	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_can_redo.html">gtk_text_buffer_get_can_redo</a>
	 */
	val canRedo: Boolean
		get() = gtk_text_buffer_get_can_redo(buffer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_can_undo.html">gtk_text_buffer_get_can_undo</a>
	 */
	val canUndo: Boolean
		get() = gtk_text_buffer_get_can_undo(buffer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_char_count.html">gtk_text_buffer_get_char_count</a>
	 */
	val charCount: Int
		get() = gtk_text_buffer_get_char_count(buffer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_enable_undo.html">gtk_text_buffer_get_enable_undo</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.set_enable_undo.html">gtk_text_buffer_set_enable_undo</a>
	 */
	var enableUndo: Boolean
		get() = gtk_text_buffer_get_enable_undo(buffer).bool
		set(value) = gtk_text_buffer_set_enable_undo(buffer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_end_iter.html">gtk_text_buffer_get_end_iter</a>
	 */
	val endIter: TextIter
		get() = memScoped {
			val iter = cValue<GtkTextIter>()
			gtk_text_buffer_get_end_iter(buffer, iter)
			iter.ptr.wrap()
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_has_selection.html">gtk_text_buffer_get_has_selection</a>
	 */
	val hasSelection: Boolean
		get() = gtk_text_buffer_get_has_selection(buffer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_insert.html">gtk_text_buffer_get_insert</a>
	 */
	val insert: TextMark
		get() = gtk_text_buffer_get_insert(buffer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_iter_at_child_anchor.html">gtk_text_buffer_get_iter_at_child_anchor</a>
	 */
	fun getIterAtChildAnchor(anchor: TextChildAnchor) =
		memScoped {
			val iter = cValue<GtkTextIter>()
			gtk_text_buffer_get_iter_at_child_anchor(buffer, iter, anchor.textChildAnchorPointer)
			iter.ptr.wrap()
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_iter_at_line.html">gtk_text_buffer_get_iter_at_line</a>
	 */
	fun getIterAtLine(lineNumber: Int) =
		memScoped {
			val iter = cValue<GtkTextIter>()
			gtk_text_buffer_get_iter_at_line(buffer, iter, lineNumber)
			iter.ptr.wrap()
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_iter_at_line_index.html">gtk_text_buffer_get_iter_at_line_index</a>
	 */
	fun getIterAtLineIndex(lineNumber: Int, byteIndex: Int) =
		memScoped {
			val iter = cValue<GtkTextIter>()
			gtk_text_buffer_get_iter_at_line_index(buffer, iter, lineNumber, byteIndex)
			iter.ptr.wrap()
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_iter_at_line_offset.html">gtk_text_buffer_get_iter_at_line_offset</a>
	 */
	fun getIterAtLineOffset(lineNumber: Int, charOffset: Int) =
		memScoped {
			val iter = cValue<GtkTextIter>()
			gtk_text_buffer_get_iter_at_line_offset(buffer, iter, lineNumber, charOffset)
			iter.ptr.wrap()
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_iter_at_mark.html">gtk_text_buffer_get_iter_at_mark</a>
	 */
	fun getIterAtMark(mark: TextMark) =
		memScoped {
			val iter = cValue<GtkTextIter>()
			gtk_text_buffer_get_iter_at_mark(buffer, iter, mark.markPointer)
			iter.ptr.wrap()
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_iter_at_offset.html">gtk_text_buffer_get_iter_at_offset</a>
	 */
	fun getIterAtOffset(charOffset: Int) =
		memScoped {
			val iter = cValue<GtkTextIter>()
			gtk_text_buffer_get_iter_at_offset(buffer, iter, charOffset)
			iter.ptr.wrap()
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_line_count.html">gtk_text_buffer_get_line_count</a>
	 */
	val lineCount: Int =
		gtk_text_buffer_get_line_count(buffer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_mark.html">gtk_text_buffer_get_mark</a>
	 */
	fun getMark(name: String): TextMark? =
		gtk_text_buffer_get_mark(buffer, name).wrap()


	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_max_undo_levels.html">gtk_text_buffer_get_max_undo_levels</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.set_max_undo_levels.html">gtk_text_buffer_set_max_undo_levels</a>
	 */
	var maxUndoLevels: UInt
		get() = gtk_text_buffer_get_max_undo_levels(buffer)
		set(value) = gtk_text_buffer_set_max_undo_levels(buffer, value)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_modified.html">gtk_text_buffer_get_modified</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.set_modified.html">gtk_text_buffer_set_modified</a>
	 */
	var modified: Boolean
		get() = gtk_text_buffer_get_modified(buffer).bool
		set(value) = gtk_text_buffer_set_modified(buffer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_selection_bound.html">gtk_text_buffer_get_selection_bound</a>
	 */
	val selectionBound: TextMark
		get() = gtk_text_buffer_get_selection_bound(buffer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_selection_bounds.html">gtk_text_buffer_get_selection_bounds</a>
	 */
	val selectionBounds: Pair<TextIter, TextIter>
		get() = memScoped {
			val start = cValue<GtkTextIter>()
			val end = cValue<GtkTextIter>()
			gtk_text_buffer_get_selection_bounds(buffer, start, end)
			start.ptr.wrap() to end.ptr.wrap()
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_selection_content.html">gtk_text_buffer_get_selection_content</a>
	 */
	val selectionContent: ContentProvider
		get() = gtk_text_buffer_get_selection_content(buffer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_slice.html">gtk_text_buffer_get_slice</a>
	 */
	fun getSlice(start: TextIter, end: TextIter, includeHiddenChars: Boolean): String =
		gtk_text_buffer_get_slice(buffer, start.pointer, end.pointer, includeHiddenChars.gtk)!!.toKString()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_start_iter.html"></a>
	 */
	val startIter
		get() = memScoped {
			val iter = cValue<GtkTextIter>()
			gtk_text_buffer_get_start_iter(buffer, iter)
			iter.ptr.wrap()
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_tag_table.html">gtk_text_buffer_get_tag_table</a>
	 */
	val tagTable: TextTagTable
		get() = gtk_text_buffer_get_tag_table(buffer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.get_text.html">gtk_text_buffer_get_text</a>
	 */
	fun getText(start: TextIter, end: TextIter, includeHiddenChars: Boolean): String =
		gtk_text_buffer_get_text(buffer, start.pointer, end.pointer, includeHiddenChars.gtk)!!.toKString()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.insert.html">gtk_text_buffer_insert</a>
	 */
	fun insert(iter: TextIter, text: String) {
		gtk_text_buffer_insert(buffer, iter.pointer, text, -1)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.insert_at_cursor.html">gtk_text_buffer_insert_at_cursor</a>
	 */
	fun insertAtCursor(text: String) {
		gtk_text_buffer_insert_at_cursor(buffer, text, -1)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.insert_child_anchor.html">gtk_text_buffer_insert_child_anchor</a>
	 */
	fun insertChildAnchor(iter: TextIter, anchor: TextChildAnchor) {
		gtk_text_buffer_insert_child_anchor(buffer, iter.pointer, anchor.textChildAnchorPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.insert_interactive.html">gtk_text_buffer_insert_interactive</a>
	 */
	fun insertInteractive(iter: TextIter, text: String, defaultEditable: Boolean): Boolean =
		gtk_text_buffer_insert_interactive(buffer, iter.pointer, text, -1, defaultEditable.gtk).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.insert_interactive_at_cursor.html">gtk_text_buffer_insert_interactive_at_cursor</a>
	 */
	fun insertInteractiveAtCursor(text: String, defaultEditable: Boolean): Boolean =
		gtk_text_buffer_insert_interactive_at_cursor(buffer, text, -1, defaultEditable.gtk).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.insert_markup.html">gtk_text_buffer_insert_markup</a>
	 */
	fun insertMarkup(iter: TextIter, markup: String) {
		gtk_text_buffer_insert_markup(buffer, iter.pointer, markup, -1)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.insert_paintable.html">gtk_text_buffer_insert_paintable</a>
	 */
	fun insertPaintable(iter: TextIter, paintable: Paintable) {
		gtk_text_buffer_insert_paintable(buffer, iter.pointer, paintable.paintablePointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.insert_range.html">gtk_text_buffer_insert_range</a>
	 */
	fun insertRange(iter: TextIter, startIter: TextIter, endIter: TextIter) {
		gtk_text_buffer_insert_range(buffer, iter.pointer, startIter.pointer, endIter.pointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.insert_range_interactive.html">gtk_text_buffer_insert_range_interactive</a>
	 */
	fun insertRangeInteractive(iter: TextIter, start: TextIter, end: TextIter, defaultEditable: Boolean): Boolean =
		gtk_text_buffer_insert_range_interactive(
			buffer,
			iter.pointer,
			start.pointer,
			end.pointer,
			defaultEditable.gtk
		).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.move_mark.html">gtk_text_buffer_move_mark</a>
	 */
	fun moveMark(mark: TextMark, where: TextIter) {
		gtk_text_buffer_move_mark(buffer, mark.markPointer, where.pointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.move_mark_by_name.html">gtk_text_buffer_move_mark_by_name</a>
	 */
	fun moveMarkByName(name: String, where: TextIter) {
		gtk_text_buffer_move_mark_by_name(buffer, name, where.pointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.paste_clipboard.html">gtk_text_buffer_paste_clipboard</a>
	 */
	fun pasteClipboard(clipboard: Clipboard, overrideLocation: TextIter, defaultEditable: Boolean) {
		gtk_text_buffer_paste_clipboard(
			buffer,
			clipboard.clipboardPointer,
			overrideLocation.pointer,
			defaultEditable.gtk
		)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.place_cursor.html">gtk_text_buffer_place_cursor</a>
	 */
	fun placeCursor(where: TextIter) {
		gtk_text_buffer_place_cursor(buffer, where.pointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.redo.html">gtk_text_buffer_redo</a>
	 */
	fun redo() {
		gtk_text_buffer_redo(buffer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.remove_all_tags.html">gtk_text_buffer_remove_all_tags</a>
	 */
	fun removeAllTags(start: TextIter, end: TextIter) {
		gtk_text_buffer_remove_all_tags(buffer, start.pointer, end.pointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.remove_selection_clipboard.html">gtk_text_buffer_remove_selection_clipboard</a>
	 */
	fun removeSelectionClipboard(clipboard: Clipboard) {
		gtk_text_buffer_remove_selection_clipboard(buffer, clipboard.clipboardPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.remove_tag.html">gtk_text_buffer_remove_tag</a>
	 */
	fun removeTag(tag: TextTag, start: TextIter, end: TextIter) {
		gtk_text_buffer_remove_tag(buffer, tag.textTagPointer, start.pointer, end.pointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.remove_tag_by_name.html">gtk_text_buffer_remove_tag_by_name</a>
	 */
	fun removeTagByName(name: String, start: TextIter, end: TextIter) {
		gtk_text_buffer_remove_tag_by_name(buffer, name, start.pointer, end.pointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.select_range.html">gtk_text_buffer_select_range</a>
	 */
	fun selectRange(ins: TextIter, bound: TextIter) {
		gtk_text_buffer_select_range(buffer, ins.pointer, bound.pointer)
	}


	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.set_text.html">gtk_text_buffer_set_text</a>
	 */
	fun setText(text: String) {
		gtk_text_buffer_set_text(buffer, text, -1)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TextBuffer.undo.html">gtk_text_buffer_undo</a>
	 */
	fun undo() {
		gtk_text_buffer_undo(buffer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextBuffer.apply-tag.html"></a>
	 */
	fun addOnApplyTagCallback(action: TextBufferApplyTagFunction) =
		addSignalCallback(Signals.APPLY_TAG, action, staticApplyTagFunction)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextBuffer.begin-user-action.html"></a>
	 */
	fun addOnBeginUserActionCallback(action: () -> Unit) =
		addSignalCallback(Signals.BEGIN_USER_ACTION, action)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextBuffer.changed.html"></a>
	 */
	fun addOnChangedCallback(action: () -> Unit) =
		addSignalCallback(Signals.CHANGED, action)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextBuffer.delete-range.html"></a>
	 */
	fun addOnDeleteRangeCallback(action: TextBufferDeleteRangeFunction) =
		addSignalCallback(Signals.DELETE_RANGE, action, staticDeleteRangeFunction)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextBuffer.end-user-action.html"></a>
	 */
	fun addOnEndUserActionCallback(action: () -> Unit) =
		addSignalCallback(Signals.END_USER_ACTION, action)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextBuffer.insert-child-anchor.html"></a>
	 */
	fun addOnInsertChildAnchorCallback(action: TextBufferInsertChildAnchorFunction) =
		addSignalCallback(Signals.INSERT_CHILD_ANCHOR, action, staticInsertChildAnchorFunction)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextBuffer.insert-paintable.html"></a>
	 */
	fun addOnInsertPaintableCallback(action: TextBufferInsertPaintableFunction) =
		addSignalCallback(Signals.INSERT_PAINTABLE, action, staticInsertPaintableFunction)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextBuffer.insert-text.html"></a>
	 */
	fun addOnInsertTextCallback(action: TextBufferInsertTextFunction) =
		addSignalCallback(Signals.INSERT_TEXT, action, staticInsertTextFunction)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextBuffer.mark-deleted.html"></a>
	 */
	fun addOnMarkDeletedCallback(action: TextBufferMarkDeletedFunction) =
		addSignalCallback(Signals.MARK_DELETED, action, staticMarkDeletedFunction)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextBuffer.mark-set.html"></a>
	 */
	fun addOnMarkSetCallback(action: TextBufferMarkSetFunction) =
		addSignalCallback(Signals.MARK_SET, action, staticMarkSetFunction)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextBuffer.modified-changed.html"></a>
	 */
	fun addOnModifiedChangedCallback(action: () -> Unit) =
		addSignalCallback(Signals.MODIFIED_CHANGED, action)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextBuffer.paste-done.html"></a>
	 */
	fun addOnPasteDoneCallback(action: TextBufferPasteDoneFunction) =
		addSignalCallback(Signals.PASTE_DONE, action, staticPasteDoneFunction)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextBuffer.redo.html"></a>
	 */
	fun addOnRedoCallback(action: () -> Unit) =
		addSignalCallback(Signals.REDO, action)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextBuffer.remove-tag.html"></a>
	 */
	fun addOnRemoveTagCallback(action: TextBufferRemoveTagFunction) =
		addSignalCallback(Signals.REMOVE_TAG, action, staticRemoveTagFunction)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TextBuffer.undo.html"></a>
	 */
	fun addOnUndoCallback(action: () -> Unit) =
		addSignalCallback(Signals.UNDO, action)

	companion object {
		private val staticApplyTagFunction: GCallback =
			staticCFunction { _: gpointer,
			                  tag: GtkTextTag_autoptr,
			                  start: GtkTextIter_autoptr,
			                  end: GtkTextIter_autoptr,
			                  data: gpointer ->
				data.asStableRef<TextBufferApplyTagFunction>().get().invoke(tag.wrap(), start.wrap(), end.wrap())
			}.reinterpret()

		private val staticDeleteRangeFunction: GCallback =
			staticCFunction { _: gpointer,
			                  start: GtkTextIter_autoptr,
			                  end: GtkTextIter_autoptr,
			                  data: gpointer ->
				data.asStableRef<TextBufferDeleteRangeFunction>().get().invoke(start.wrap(), end.wrap())
			}.reinterpret()

		private val staticInsertChildAnchorFunction: GCallback =
			staticCFunction { _: gpointer,
			                  location: GtkTextIter_autoptr,
			                  anchor: GtkTextChildAnchor_autoptr,
			                  data: gpointer ->
				data.asStableRef<TextBufferInsertChildAnchorFunction>().get().invoke(location.wrap(), anchor.wrap())
			}.reinterpret()

		private val staticInsertPaintableFunction: GCallback =
			staticCFunction { _: gpointer,
			                  location: GtkTextIter_autoptr,
			                  paintable: GdkPaintable_autoptr,
			                  data: gpointer ->
				data.asStableRef<TextBufferInsertPaintableFunction>().get().invoke(location.wrap(), paintable.wrap())
			}.reinterpret()

		private val staticInsertTextFunction: GCallback =
			staticCFunction { _: gpointer,
			                  location: GtkTextIter_autoptr,
			                  text: CStringPointer,
			                  len: Int,
			                  data: gpointer ->
				data.asStableRef<TextBufferInsertTextFunction>().get().invoke(location.wrap(), text.toKString(), len)
			}.reinterpret()

		private val staticMarkDeletedFunction: GCallback =
			staticCFunction { _: gpointer,
			                  mark: GtkTextMark_autoptr,
			                  data: gpointer ->
				data.asStableRef<TextBufferMarkDeletedFunction>().get().invoke(mark.wrap())
			}.reinterpret()

		private val staticMarkSetFunction: GCallback =
			staticCFunction { _: gpointer,
			                  location: GtkTextIter_autoptr,
			                  mark: GtkTextMark_autoptr,
			                  data: gpointer ->
				data.asStableRef<TextBufferMarkSetFunction>().get().invoke(location.wrap(), mark.wrap())
			}.reinterpret()

		private val staticPasteDoneFunction: GCallback =
			staticCFunction { _: gpointer,
			                  clipboard: GdkClipboard_autoptr,
			                  data: gpointer ->
				data.asStableRef<TextBufferPasteDoneFunction>().get().invoke(clipboard.wrap())
			}.reinterpret()

		private val staticRemoveTagFunction: GCallback =
			staticCFunction { _: gpointer,
			                  tag: GtkTextTag_autoptr,
			                  start: GtkTextIter_autoptr,
			                  end: GtkTextIter_autoptr,
			                  data: gpointer ->
				data.asStableRef<TextBufferRemoveTagFunction>().get().invoke(tag.wrap(), start.wrap(), end.wrap())
			}.reinterpret()

		inline fun CPointer<GtkTextBuffer>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkTextBuffer>.wrap() =
			TextBuffer(this)
	}
}
