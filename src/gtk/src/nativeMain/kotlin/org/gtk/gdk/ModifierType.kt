package org.gtk.gdk

import gtk.*

/**
 * kotlinx-gtk
 *
 * 07 / 08 / 2021
 *
 * @see <a href=""></a>
 */
enum class ModifierType(val gdk: GdkModifierType) {
	SHIFT_MASK(GDK_SHIFT_MASK),
	LOCK_MASK(GDK_LOCK_MASK),
	CONTROL_MASK(GDK_CONTROL_MASK),
	ALT_MASK(GDK_ALT_MASK),
	BUTTON1_MASK(GDK_BUTTON1_MASK),
	BUTTON2_MASK(GDK_BUTTON2_MASK),
	BUTTON3_MASK(GDK_BUTTON3_MASK),
	BUTTON4_MASK(GDK_BUTTON4_MASK),
	BUTTON5_MASK(GDK_BUTTON5_MASK),
	SUPER_MASK(GDK_SUPER_MASK),
	HYPER_MASK(GDK_HYPER_MASK),
	META_MASK(GDK_META_MASK);

	companion object {
		inline fun valueOf(gdk: GdkModifierType) = values().find { it.gdk == gdk }
	}
}