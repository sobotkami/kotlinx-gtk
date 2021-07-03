import nativex.gtk.common.enums.Orientation
import nativex.gtk.dsl.box
import nativex.gtk.dsl.frame
import nativex.gtk.widgets.container.bin.windows.Window
import nativex.gtk.widgets.entry.Entry
import nativex.gtk.widgets.entry.Entry.InputHints.*
import nativex.gtk.widgets.entry.Entry.InputHints.Companion.and

/**
 * Test an [Entry] purpose
 */
internal fun Window.entryPurposeTest() =
	box(Orientation.VERTICAL, 10) {
		frame("Free form") {
			Entry().apply {
				purpose = Entry.InputPurpose.FREE_FORM
			}.also {
				add(it)
			}
		}
		frame("Alpha") {
			Entry().apply {
				purpose = Entry.InputPurpose.ALPHA
			}.also {
				add(it)
			}
		}
		frame("Digits") {
			Entry().apply {
				purpose = Entry.InputPurpose.DIGITS
			}.also {
				add(it)
			}
		}
		frame("Number") {
			Entry().apply {
				purpose = Entry.InputPurpose.NUMBER
			}.also {
				add(it)
			}
		}
		frame("Phone") {
			Entry().apply {
				purpose = Entry.InputPurpose.PHONE
			}.also {
				add(it)
			}
		}
		frame("URL") {
			Entry().apply {
				purpose = Entry.InputPurpose.URL
			}.also {
				add(it)
			}
		}
		frame("Email") {
			Entry().apply {
				purpose = Entry.InputPurpose.FREE_FORM
			}.also {
				add(it)
			}
		}
		frame("Name") {
			Entry().apply {
				purpose = Entry.InputPurpose.NAME
			}.also {
				add(it)
			}
		}
		frame("Password") {
			Entry().apply {
				purpose = Entry.InputPurpose.PASSWORD
			}.also {
				add(it)
			}
		}
		frame("Pin") {
			Entry().apply {
				purpose = Entry.InputPurpose.PIN
			}.also {
				add(it)
			}
		}
		frame("Terminal") {
			Entry().apply {
				purpose = Entry.InputPurpose.TERMINAL
			}.also {
				add(it)
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
				add(it)
			}
		}
		frame("No Spellcheck") {
			Entry().apply {
				setInputHint(NO_SPELLCHECK)
			}.also {
				add(it)
			}
		}
		frame("Word completion") {
			Entry().apply {
				setInputHint(WORD_COMPLETION)
			}.also {
				add(it)
			}
		}
		frame("Lowercase") {
			Entry().apply {
				setInputHint(LOWERCASE)
			}.also {
				add(it)
			}
		}
		frame("Uppercase chars") {
			Entry().apply {
				setInputHint(UPPERCASE_CHARS)
			}.also {
				add(it)
			}
		}
		frame("Uppercase words") {
			Entry().apply {
				setInputHint(UPPERCASE_WORDS)
			}.also {
				add(it)
			}
		}
		frame("Uppercase sentence") {
			Entry().apply {
				setInputHint(UPPERCASE_SENTENCES)
			}.also {
				add(it)
			}
		}
		frame("Inhibit OSK") {
			Entry().apply {
				setInputHint(INHIBIT_OSK)
			}.also {
				add(it)
			}
		}
		frame("Vertical Writing") {
			Entry().apply {
				setInputHint(VERTICAL_WRITING)
			}.also {
				add(it)
			}
		}
		frame("Emoji") {
			Entry().apply {
				setInputHint(EMOJI)
			}.also {
				add(it)
			}
		}
		frame("No emoji") {
			Entry().apply {
				setInputHint(NO_EMOJI)
			}.also {
				add(it)
			}
		}

		frame("Emoji & Spellcheck & Uppercase sentences") {
			Entry().apply {
				setInputHints(EMOJI and SPELLCHECK and UPPERCASE_SENTENCES)
			}.also {
				add(it)
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
			add(it)
		}
	}
}