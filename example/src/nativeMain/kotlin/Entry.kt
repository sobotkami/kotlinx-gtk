
import org.gtk.dsl.gtk.box
import org.gtk.dsl.gtk.frame
import org.gtk.gtk.common.enums.Orientation
import org.gtk.gtk.widgets.entry.Entry
import org.gtk.gtk.widgets.entry.Entry.InputHints.*
import org.gtk.gtk.widgets.entry.Entry.InputHints.Companion.and
import org.gtk.gtk.widgets.windows.Window

/**
 * Test an [Entry] purpose
 */
internal fun Window.entryPurposeTest() =
	box(Orientation.VERTICAL, 10) {
		frame("Free form") {
			Entry().apply {
				purpose = Entry.InputPurpose.FREE_FORM
			}.also {
				append(it)
			}
		}
		frame("Alpha") {
			Entry().apply {
				purpose = Entry.InputPurpose.ALPHA
			}.also {
				append(it)
			}
		}
		frame("Digits") {
			Entry().apply {
				purpose = Entry.InputPurpose.DIGITS
			}.also {
				append(it)
			}
		}
		frame("Number") {
			Entry().apply {
				purpose = Entry.InputPurpose.NUMBER
			}.also {
				append(it)
			}
		}
		frame("Phone") {
			Entry().apply {
				purpose = Entry.InputPurpose.PHONE
			}.also {
				append(it)
			}
		}
		frame("URL") {
			Entry().apply {
				purpose = Entry.InputPurpose.URL
			}.also {
				append(it)
			}
		}
		frame("Email") {
			Entry().apply {
				purpose = Entry.InputPurpose.FREE_FORM
			}.also {
				append(it)
			}
		}
		frame("Name") {
			Entry().apply {
				purpose = Entry.InputPurpose.NAME
			}.also {
				append(it)
			}
		}
		frame("Password") {
			Entry().apply {
				purpose = Entry.InputPurpose.PASSWORD
			}.also {
				append(it)
			}
		}
		frame("Pin") {
			Entry().apply {
				purpose = Entry.InputPurpose.PIN
			}.also {
				append(it)
			}
		}
		frame("Terminal") {
			Entry().apply {
				purpose = Entry.InputPurpose.TERMINAL
			}.also {
				append(it)
			}
		}
	}

/**
 * Test an [Entry]s hints
 */
internal fun Window.entryHintTest() =
	box(Orientation.VERTICAL, 10) {
		frame("Spellcheck") {
			Entry().apply {
				setInputHint(SPELLCHECK)
			}.also {
				append(it)
			}
		}
		frame("No Spellcheck") {
			Entry().apply {
				setInputHint(NO_SPELLCHECK)
			}.also {
				append(it)
			}
		}
		frame("Word completion") {
			Entry().apply {
				setInputHint(WORD_COMPLETION)
			}.also {
				append(it)
			}
		}
		frame("Lowercase") {
			Entry().apply {
				setInputHint(LOWERCASE)
			}.also {
				append(it)
			}
		}
		frame("Uppercase chars") {
			Entry().apply {
				setInputHint(UPPERCASE_CHARS)
			}.also {
				append(it)
			}
		}
		frame("Uppercase words") {
			Entry().apply {
				setInputHint(UPPERCASE_WORDS)
			}.also {
				append(it)
			}
		}
		frame("Uppercase sentence") {
			Entry().apply {
				setInputHint(UPPERCASE_SENTENCES)
			}.also {
				append(it)
			}
		}
		frame("Inhibit OSK") {
			Entry().apply {
				setInputHint(INHIBIT_OSK)
			}.also {
				append(it)
			}
		}
		frame("Vertical Writing") {
			Entry().apply {
				setInputHint(VERTICAL_WRITING)
			}.also {
				append(it)
			}
		}
		frame("Emoji") {
			Entry().apply {
				setInputHint(EMOJI)
			}.also {
				append(it)
			}
		}
		frame("No emoji") {
			Entry().apply {
				setInputHint(NO_EMOJI)
			}.also {
				append(it)
			}
		}

		frame("Emoji & Spellcheck & Uppercase sentences") {
			Entry().apply {
				setInputHints(EMOJI and SPELLCHECK and UPPERCASE_SENTENCES)
			}.also {
				append(it)
			}
		}

	}

/**
 * Test an [Entry]s callbacks
 */
internal fun Window.entryCallbackTest() {
	box(Orientation.VERTICAL, 10) {
		Entry().apply {
			addOnActivateCallback {
				println("Entry: activate")
			}
			addOnBackspaceCallback {
				println("Entry: backspace")
			}
			addOnCopyClipboardCallback {
				println("Entry: copy-clipboard")
			}
			addOnCutClipboardCallback {
				println("Entry: cut-clipboard")
			}
			addOnDeleteFromCursorCallback { deleteType, count ->
				println("Entry: delete-from-cursor : $deleteType, $count")
			}
			addOnIconPressCallback { iconPosition, event ->
				println("Entry: icon-press : $iconPosition, $event")
			}
			addOnIconReleaseCallback { iconPosition, event ->
				println("Entry: icon-release : $iconPosition, $event")
			}
			addOnInsertAtCursorCallback {
				println("Entry: insert-at-cursor")
			}
			addOnInsertEmojiCallback {
				println("Entry: insert-emoji")
			}
			addOnMoveCursorCallback { movementStep, count, extendSelection ->
				println("Entry: move-cursor : $movementStep, $count, $extendSelection")
			}
			addOnPasteClipboardCallback {
				println("Entry: paste-clipboard")
			}
			addOnPopulatePopupCallback {
				println("Entry: populate-popup")
			}
			addOnPreeditChangedCallback {
				println("Entry: preedit-changed")
			}
			addOnToggleOverwriteCallback {
				println("Entry: toggle-overwrite")
			}
		}.also {
			append(it)
		}
	}
}