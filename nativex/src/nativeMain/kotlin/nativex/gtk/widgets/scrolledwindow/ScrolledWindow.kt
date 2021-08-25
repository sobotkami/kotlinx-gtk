package nativex.gtk.widgets.scrolledwindow

import glib.gboolean
import glib.gpointer
import gobject.GCallback
import gtk.*
import gtk.GtkCornerType.*
import gtk.GtkPolicyType.*
import gtk.GtkPolicyType.Var
import kotlinx.cinterop.*
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gobject.SignalManager
import nativex.gobject.Signals
import nativex.gobject.addSignalCallback
import nativex.gobject.connectSignal
import nativex.gtk.Adjustment
import nativex.gtk.common.enums.DirectionType
import nativex.gtk.common.enums.PositionType
import nativex.gtk.common.enums.ScrollType
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 13 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html">GtkScrolledWindow</a>
 */
class ScrolledWindow(
	val scrolledWindowPointer: CPointer<GtkScrolledWindow>
) : Widget(scrolledWindowPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-new">
	 *     gtk_scrolled_window_new</a>
	 */
	constructor() : this(gtk_scrolled_window_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-get-vadjustment">
	 *     gtk_scrolled_window_get_vadjustment</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-set-vadjustment">
	 *     gtk_scrolled_window_set_vadjustment</a>
	 */
	var scrollbarVerticalAdjustment: Adjustment?
		get() = gtk_scrolled_window_get_vadjustment(scrolledWindowPointer)?.let {
			Adjustment(
				it
			)
		}
		set(value) = gtk_scrolled_window_set_vadjustment(
			scrolledWindowPointer,
			value?.adjustmentPointer
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-get-hadjustment">
	 *     gtk_scrolled_window_get_hadjustment</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-set-hadjustment">
	 *     gtk_scrolled_window_set_hadjustment</a>
	 */
	var scrollbarHorizontalAdjustment: Adjustment?
		get() = gtk_scrolled_window_get_hadjustment(scrolledWindowPointer)?.let {
			Adjustment(
				it
			)
		}
		set(value) = gtk_scrolled_window_set_hadjustment(
			scrolledWindowPointer,
			value?.adjustmentPointer
		)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-get-hscrollbar">
	 *     gtk_scrolled_window_get_hscrollbar</a>
	 */
	val horizontalScrollBar: Widget
		get() = Widget(gtk_scrolled_window_get_hscrollbar(scrolledWindowPointer)!!)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-get-vscrollbar">
	 *     gtk_scrolled_window_get_vscrollbar</a>
	 */
	val verticalScrollBar: Widget
		get() = Widget(gtk_scrolled_window_get_vscrollbar(scrolledWindowPointer)!!)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-get-policy">
	 *     gtk_scrolled_window_get_policy</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-set-policy">
	 *     gtk_scrolled_window_set_policy</a>
	 */
	var policy: Pair<PolicyType, PolicyType>
		get() {
			val hP = cValue<Var>()
			val vP = cValue<Var>()

			gtk_scrolled_window_get_policy(scrolledWindowPointer, hP, vP)

			return memScoped {
				PolicyType.valueOf(hP.ptr.pointed.value)!! to PolicyType.valueOf(
					vP.ptr.pointed.value
				)!!
			}
		}
		set(value) {
			gtk_scrolled_window_set_policy(
				scrolledWindowPointer,
				value.first.gtk,
				value.second.gtk
			)
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-get-placement">
	 *     gtk_scrolled_window_get_placement</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-set-placement">
	 *     gtk_scrolled_window_set_placement</a>
	 */
	var placement: CornerType
		get() = CornerType.valueOf(
			gtk_scrolled_window_get_placement(
				scrolledWindowPointer
			)
		)!!
		set(value) = gtk_scrolled_window_set_placement(
			scrolledWindowPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-unset-placement">
	 *     gtk_scrolled_window_unset_placement</a>
	 */
	fun unsetPlacement() {
		gtk_scrolled_window_unset_placement(scrolledWindowPointer)
	}


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-get-kinetic-scrolling">
	 *     gtk_scrolled_window_get_kinetic_scrolling</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-set-kinetic-scrolling">
	 *     gtk_scrolled_window_set_kinetic_scrolling</a>
	 */
	var kineticScrolling: Boolean
		get() = gtk_scrolled_window_get_kinetic_scrolling(
			scrolledWindowPointer
		)
			.bool
		set(value) = gtk_scrolled_window_set_kinetic_scrolling(
			scrolledWindowPointer,
			value.gtk
		)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-get-overlay-scrolling">
	 *     gtk_scrolled_window_get_overlay_scrolling</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-set-overlay-scrolling">
	 *     gtk_scrolled_window_set_overlay_scrolling</a>
	 */
	var overlayScrolling: Boolean
		get() = gtk_scrolled_window_get_overlay_scrolling(
			scrolledWindowPointer
		).bool
		set(value) = gtk_scrolled_window_set_overlay_scrolling(
			scrolledWindowPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-get-min-content-width">
	 *     gtk_scrolled_window_get_min_content_width</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-set-min-content-width">
	 *     gtk_scrolled_window_set_min_content_width</a>
	 */
	var minContentWidth: Int
		get() = gtk_scrolled_window_get_min_content_width(scrolledWindowPointer)
		set(value) = gtk_scrolled_window_set_min_content_width(
			scrolledWindowPointer,
			value
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-get-min-content-height">
	 *     gtk_scrolled_window_get_min_content_height</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-set-min-content-height">
	 *     gtk_scrolled_window_set_min_content_height</a>
	 */
	var minContentHeight: Int
		get() = gtk_scrolled_window_get_min_content_height(scrolledWindowPointer)
		set(value) = gtk_scrolled_window_set_min_content_height(
			scrolledWindowPointer,
			value
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-get-max-content-width">
	 *     gtk_scrolled_window_get_max_content_width</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-set-max-content-width">
	 *     gtk_scrolled_window_set_max_content_width</a>
	 */
	var maxContentWidth: Int
		get() = gtk_scrolled_window_get_max_content_width(scrolledWindowPointer)
		set(value) = gtk_scrolled_window_set_max_content_width(
			scrolledWindowPointer,
			value
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-get-max-content-height">
	 *     gtk_scrolled_window_get_max_content_height</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-set-max-content-height">
	 *     gtk_scrolled_window_set_max_content_height</a>
	 */
	var maxContentHeight: Int
		get() = gtk_scrolled_window_get_max_content_height(scrolledWindowPointer)
		set(value) = gtk_scrolled_window_set_max_content_height(
			scrolledWindowPointer,
			value
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-get-propagate-natural-width">
	 *     gtk_scrolled_window_get_propagate_natural_width</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-set-propagate-natural-width">
	 *     gtk_scrolled_window_set_propagate_natural_width</a>
	 */
	var propagateNaturalWidth: Boolean
		get() = gtk_scrolled_window_get_propagate_natural_width(
			scrolledWindowPointer
		)
			.bool
		set(value) = gtk_scrolled_window_set_propagate_natural_width(
			scrolledWindowPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-get-propagate-natural-height">
	 *     gtk_scrolled_window_get_propagate_natural_height</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#gtk-scrolled-window-set-propagate-natural-height">
	 *     gtk_scrolled_window_set_propagate_natural_height</a>
	 */
	var propagateNaturalHeight: Boolean
		get() = gtk_scrolled_window_get_propagate_natural_height(
			scrolledWindowPointer
		)
			.bool
		set(value) = gtk_scrolled_window_set_propagate_natural_height(
			scrolledWindowPointer,
			value.gtk
		)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#GtkPolicyType">GtkPolicyType</a>
	 */
	enum class PolicyType(val key: Int, val gtk: GtkPolicyType) {

		/**
		 * The scrollbar is always visible.
		 *
		 * The view size is independent of the content.
		 */
		ALWAYS(0, GTK_POLICY_ALWAYS),

		/**
		 * The scrollbar will appear and disappear as necessary.
		 *
		 * For example, when all of a [nativex.gtk.widgets.container.TreeView] can not be seen.
		 */
		AUTOMATIC(1, GTK_POLICY_AUTOMATIC),

		/**
		 * The scrollbar should never appear.
		 *
		 * In this mode the content determines the size.
		 */
		NEVER(2, GTK_POLICY_NEVER),

		/**
		 * Don't show a scrollbar, but don't force the size to follow the content.
		 *
		 * This can be used e.g. to make multiple scrolled windows share a scrollbar.
		 *
		 * @since 3.16
		 */
		EXTERNAL(3, GTK_POLICY_EXTERNAL);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			fun valueOf(gtk: GtkPolicyType) =
				values().find { it.gtk == gtk }
		}
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#GtkCornerType">GtkCornerType</a>
	 */
	enum class CornerType(val key: Int, val gtk: GtkCornerType) {

		/** Place the scrollbars on the right and bottom of the widget (default behaviour). */
		TOP_LEFT(0, GTK_CORNER_TOP_LEFT),

		/** Place the scrollbars on the top and right of the widget. */
		BOTTOM_LEFT(1, GTK_CORNER_BOTTOM_LEFT),

		/** Place the scrollbars on the left and bottom of the widget. */
		TOP_RIGHT(2, GTK_CORNER_TOP_RIGHT),

		/** Place the scrollbars on the top and left of the widget. */
		BOTTOM_RIGHT(3, GTK_CORNER_BOTTOM_RIGHT);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			fun valueOf(gtk: GtkCornerType) =
				values().find { it.gtk == gtk }
		}
	}


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#GtkScrolledWindow-edge-overshot">edge-overshot</a>
	 */
	fun addOnEdgeOvershotCallback(action: (PositionType) -> Unit) =
		addSignalCallback(Signals.EDGE_OVERSHOT, action, PositionType.staticPositionTypeCallback)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#GtkScrolledWindow-edge-reached">edge-reached</a>
	 */
	fun addOnEdgeReachedCallback(action: (PositionType) -> Unit) =
		addSignalCallback(Signals.EDGE_REACHED, action, PositionType.staticPositionTypeCallback)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#GtkScrolledWindow-move-focus-out">move-focus-out</a>
	 */
	fun addOnMoveFocusOutCallback(action: (DirectionType) -> Unit) =
		addSignalCallback(Signals.MOVE_FOCUS_OUT, action, DirectionType.staticDirectionTypeCallback)

	private var scrollChildManager: SignalManager? = null

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html#GtkScrolledWindow-scroll-child">scroll-child</a>
	 */
	fun setScrollChildCallback(action: ScrollChildFunction) {
		scrollChildManager?.disconnect()
		scrollChildManager = SignalManager(
			scrolledWindowPointer,
			scrolledWindowPointer.connectSignal(
				Signals.SCROLL_CHILD,
				callbackWrapper = StableRef.create(action).asCPointer(),
				handler = staticScrollChildFunction
			)
		)
	}

	companion object {
		private val staticScrollChildFunction: GCallback =
			staticCFunction { _: gpointer, scroll: GtkScrollType, horizontal: gboolean, data: gpointer? ->
				data?.asStableRef<ScrollChildFunction>()?.get()
					?.invoke(ScrollType.valueOf(scroll)!!, horizontal.bool).gtk
			}.reinterpret()
	}
}

typealias ScrollChildFunction = (ScrollType, @ParameterName("isHorizontal") Boolean) -> Boolean