package org.gtk.pango

import pango.PangoWrapMode

/**
 * kotlinx-gtk
 *
 * 26 / 03 / 2021
 *
 */
enum class WrapMode(val pango: PangoWrapMode) {
	/**
	 * Wrap lines at word boundaries.
	 */
	WRAP_WORD(PangoWrapMode.PANGO_WRAP_WORD),

	/**
	 * Wrap lines at character boundaries.
	 */
	WRAP_CHAR(PangoWrapMode.PANGO_WRAP_CHAR),

	/**
	 * Wrap lines at word boundaries,
	 * but fall back to character boundaries if there is not enough space for a full word.
	 */
	WRAP_WORD_CHAR(PangoWrapMode.PANGO_WRAP_WORD_CHAR);

	companion object {
		fun valueOf(pango: PangoWrapMode) = values().find { it.pango == pango }
	}
}