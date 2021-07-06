package nativex.gdk

import gtk.*
import gtk.GdkFilterReturn.*
import gtk.GdkFullscreenMode.GDK_FULLSCREEN_ON_ALL_MONITORS
import gtk.GdkFullscreenMode.GDK_FULLSCREEN_ON_CURRENT_MONITOR
import gtk.GdkModifierIntent.*
import gtk.GdkWindowEdge.*
import gtk.GdkWindowType.*
import gtk.GdkWindowTypeHint.*
import gtk.GdkWindowWindowClass.GDK_INPUT_ONLY
import gtk.GdkWindowWindowClass.GDK_INPUT_OUTPUT
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gobject.KObject

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class Window(
	 val windowPointer: CPointer<GdkWindow>
) : KObject(windowPointer.reinterpret()) {


	enum class Type(val key: Int,  val gdk: GdkWindowType) {
		ROOT(0, GDK_WINDOW_ROOT),
		TOPLEVEL(1, GDK_WINDOW_TOPLEVEL),
		CHILD(2, GDK_WINDOW_CHILD),
		TEMP(3, GDK_WINDOW_TEMP),
		FOREIGN(4, GDK_WINDOW_FOREIGN),
		OFFSCREEN(5, GDK_WINDOW_OFFSCREEN),
		SUBSURFACE(6, GDK_WINDOW_SUBSURFACE);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			 fun valueOf(gdk: GdkWindowType) = values().find { it.gdk == gdk }
		}
	}

	enum class WindowClass(val key: Int,  val gdk: GdkWindowWindowClass) {
		OUTPUT(0, GDK_INPUT_OUTPUT),
		ONLY(1, GDK_INPUT_ONLY);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			 fun valueOf(gdk: GdkWindowWindowClass) = values().find { it.gdk == gdk }
		}
	}

	enum class Hints(val key: Int,  val gdk: GdkWindowHints) {
		POS(0, GDK_HINT_POS),
		MIN_SIZE(1, GDK_HINT_MIN_SIZE),
		MAX_SIZE(2, GDK_HINT_MAX_SIZE),
		BASE_SIZE(3, GDK_HINT_BASE_SIZE),
		ASPECT(4, GDK_HINT_ASPECT),
		RESIZE_INC(5, GDK_HINT_RESIZE_INC),
		WIN_GRAVITY(6, GDK_HINT_WIN_GRAVITY),
		USER_POS(7, GDK_HINT_USER_POS),
		USER_SIZE(8, GDK_HINT_USER_SIZE);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			 fun valueOf(gdk: GdkWindowHints) = values().find { it.gdk == gdk }
		}
	}

	enum class Gravity(val key: Int,  val gdk: GdkGravity) {
		NORTH_WEST(0, GDK_GRAVITY_NORTH_WEST),
		NORTH(1, GDK_GRAVITY_NORTH),
		NORTH_EAST(2, GDK_GRAVITY_NORTH_EAST),
		WEST(3, GDK_GRAVITY_WEST),
		CENTER(4, GDK_GRAVITY_CENTER),
		EAST(5, GDK_GRAVITY_EAST),
		SOUTH_WEST(6, GDK_GRAVITY_SOUTH_WEST),
		SOUTH(7, GDK_GRAVITY_SOUTH),
		SOUTH_EAST(8, GDK_GRAVITY_SOUTH_EAST),
		STATIC(9, GDK_GRAVITY_STATIC);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			 fun valueOf(gdk: GdkGravity) = values().find { it.gdk == gdk }
		}
	}

	enum class AnchorHints(val key: Int,  val gdk: GdkAnchorHints) {
		FLIP_X(0, GDK_ANCHOR_FLIP_X),
		FLIP_Y(1, GDK_ANCHOR_FLIP_Y),
		SLIDE_X(2, GDK_ANCHOR_SLIDE_X),
		SLIDE_Y(3, GDK_ANCHOR_SLIDE_Y),
		RESIZE_X(4, GDK_ANCHOR_RESIZE_X),
		RESIZE_Y(5, GDK_ANCHOR_RESIZE_Y),
		FLIP(6, GDK_ANCHOR_FLIP),
		SLIDE(7, GDK_ANCHOR_SLIDE),
		RESIZE(8, GDK_ANCHOR_RESIZE);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			 fun valueOf(gdk: GdkAnchorHints) = values().find { it.gdk == gdk }
		}
	}

	enum class Edge(val key: Int,  val gdk: GdkWindowEdge) {
		NORTH_WEST(0, GDK_WINDOW_EDGE_NORTH_WEST),
		NORTH(1, GDK_WINDOW_EDGE_NORTH),
		NORTH_EAST(2, GDK_WINDOW_EDGE_NORTH_EAST),
		WEST(3, GDK_WINDOW_EDGE_WEST),
		EAST(4, GDK_WINDOW_EDGE_EAST),
		SOUTH_WEST(5, GDK_WINDOW_EDGE_SOUTH_WEST),
		SOUTH(6, GDK_WINDOW_EDGE_SOUTH),
		SOUTH_EAST(7, GDK_WINDOW_EDGE_SOUTH_EAST);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			 fun valueOf(gdk: GdkWindowEdge) = values().find { it.gdk == gdk }
		}
	}

	enum class TypeHint(val key: Int,  val gdk: GdkWindowTypeHint) {
		NORMAL(0, GDK_WINDOW_TYPE_HINT_NORMAL),
		DIALOG(1, GDK_WINDOW_TYPE_HINT_DIALOG),
		MENU(2, GDK_WINDOW_TYPE_HINT_MENU),
		TOOLBAR(3, GDK_WINDOW_TYPE_HINT_TOOLBAR),
		SPLASHSCREEN(4, GDK_WINDOW_TYPE_HINT_SPLASHSCREEN),
		UTILITY(5, GDK_WINDOW_TYPE_HINT_UTILITY),
		DOCK(6, GDK_WINDOW_TYPE_HINT_DOCK),
		DESKTOP(7, GDK_WINDOW_TYPE_HINT_DESKTOP),
		DROPDOWN_MENU(8, GDK_WINDOW_TYPE_HINT_DROPDOWN_MENU),
		POPUP_MENU(9, GDK_WINDOW_TYPE_HINT_POPUP_MENU),
		TOOLTIP(10, GDK_WINDOW_TYPE_HINT_TOOLTIP),
		NOTIFICATION(11, GDK_WINDOW_TYPE_HINT_NOTIFICATION),
		COMBO(12, GDK_WINDOW_TYPE_HINT_COMBO),
		DND(13, GDK_WINDOW_TYPE_HINT_DND); // Dungeons & Dragons enum :D

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			 fun valueOf(gdk: GdkWindowTypeHint) = values().find { it.gdk == gdk }
		}
	}

	enum class AttributesType(val key: Int,  val gdk: GdkWindowAttributesType) {
		TITLE(0, GDK_WA_TITLE),
		X(1, GDK_WA_X),
		Y(2, GDK_WA_Y),
		CURSOR(3, GDK_WA_CURSOR),
		VISUAL(4, GDK_WA_VISUAL),
		WMCLASS(5, GDK_WA_WMCLASS),
		NOREDIR(6, GDK_WA_NOREDIR),
		TYPE_HINT(7, GDK_WA_TYPE_HINT);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			 fun valueOf(gdk: GdkWindowAttributesType) = values().find { it.gdk == gdk }
		}
	}

	enum class FullscreenMode(val key: Int,  val gdk: GdkFullscreenMode) {
		ON_CURRENT_MONITOR(0, GDK_FULLSCREEN_ON_CURRENT_MONITOR),
		ON_ALL_MONITORS(1, GDK_FULLSCREEN_ON_ALL_MONITORS);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			 fun valueOf(gdk: GdkFullscreenMode) = values().find { it.gdk == gdk }
		}
	}

	enum class FilterReturn(val key: Int,  val gdk: GdkFilterReturn) {
		CONTINUE(0, GDK_FILTER_CONTINUE),
		TRANSLATE(1, GDK_FILTER_TRANSLATE),
		REMOVE(2, GDK_FILTER_REMOVE);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			 fun valueOf(gdk: GdkFilterReturn) = values().find { it.gdk == gdk }
		}
	}

	enum class ModifierType(val key: Int,  val gdk: GdkModifierType) {
		SHIFT_MASK(0, GDK_SHIFT_MASK),
		LOCK_MASK(1, GDK_LOCK_MASK),
		CONTROL_MASK(2, GDK_CONTROL_MASK),
		MOD1_MASK(3, GDK_MOD1_MASK),
		MOD2_MASK(4, GDK_MOD2_MASK),
		MOD3_MASK(5, GDK_MOD3_MASK),
		MOD4_MASK(6, GDK_MOD4_MASK),
		MOD5_MASK(7, GDK_MOD5_MASK),
		BUTTON1_MASK(8, GDK_BUTTON1_MASK),
		BUTTON2_MASK(9, GDK_BUTTON2_MASK),
		BUTTON3_MASK(10, GDK_BUTTON3_MASK),
		BUTTON4_MASK(11, GDK_BUTTON4_MASK),
		BUTTON5_MASK(12, GDK_BUTTON5_MASK),

		//MODIFIER_RESERVED_13_MASK,
		//MODIFIER_RESERVED_14_MASK,
		//MODIFIER_RESERVED_15_MASK,
		//MODIFIER_RESERVED_16_MASK,
		//MODIFIER_RESERVED_17_MASK,
		//MODIFIER_RESERVED_18_MASK,
		//MODIFIER_RESERVED_19_MASK,
		//MODIFIER_RESERVED_20_MASK,
		//MODIFIER_RESERVED_21_MASK,
		//MODIFIER_RESERVED_22_MASK,
		//MODIFIER_RESERVED_23_MASK,
		//MODIFIER_RESERVED_24_MASK,
		//MODIFIER_RESERVED_25_MASK,
		SUPER_MASK(26, GDK_SUPER_MASK),
		HYPER_MASK(27, GDK_HYPER_MASK),
		META_MASK(28, GDK_META_MASK),

		//MODIFIER_RESERVED_29_MASK,
		RELEASE_MASK(30, GDK_RELEASE_MASK),
		MODIFIER_MASK(31, GDK_MODIFIER_MASK);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			 fun valueOf(gdk: GdkModifierType) = values().find { it.gdk == gdk }
		}
	}

	enum class ModifierIntent(val key: Int,  val gdk: GdkModifierIntent) {
		PRIMARY_ACCELERATOR(0, GDK_MODIFIER_INTENT_PRIMARY_ACCELERATOR),
		CONTEXT_MENU(1, GDK_MODIFIER_INTENT_CONTEXT_MENU),
		EXTEND_SELECTION(2, GDK_MODIFIER_INTENT_EXTEND_SELECTION),
		MODIFY_SELECTION(3, GDK_MODIFIER_INTENT_MODIFY_SELECTION),
		NO_TEXT_INPUT(4, GDK_MODIFIER_INTENT_NO_TEXT_INPUT),
		SHIFT_GROUP(5, GDK_MODIFIER_INTENT_SHIFT_GROUP),
		DEFAULT_MOD_MASK(6, GDK_MODIFIER_INTENT_DEFAULT_MOD_MASK);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			 fun valueOf(gdk: GdkModifierIntent) = values().find { it.gdk == gdk }
		}
	}

	enum class WMDecoration(val key: Int,  val gdk: GdkWMDecoration) {
		ALL(0, GDK_DECOR_ALL),
		BORDER(1, GDK_DECOR_BORDER),
		RESIZEH(2, GDK_DECOR_RESIZEH),
		TITLE(3, GDK_DECOR_TITLE),
		MENU(4, GDK_DECOR_MENU),
		MINIMIZE(5, GDK_DECOR_MINIMIZE),
		MAXIMIZE(6, GDK_DECOR_MAXIMIZE);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			 fun valueOf(gdk: GdkWMDecoration) = values().find { it.gdk == gdk }
		}
	}

	enum class WMFunction(val key: Int,  val gdk: GdkWMFunction) {
		ALL(0, GDK_FUNC_ALL),
		RESIZE(1, GDK_FUNC_RESIZE),
		MOVE(2, GDK_FUNC_MOVE),
		MINIMIZE(3, GDK_FUNC_MINIMIZE),
		MAXIMIZE(4, GDK_FUNC_MAXIMIZE),
		CLOSE(5, GDK_FUNC_CLOSE);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			 fun valueOf(gdk: GdkWMFunction) = values().find { it.gdk == gdk }
		}
	}


	companion object {
		 inline fun CPointer<GdkWindow>?.wrap() =
			this?.let { Window(it) }

		 inline fun CPointer<GdkWindow>.wrap() =
			Window(this)
	}
}