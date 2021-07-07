package nativex.gtk.widgets.container
import glib.gboolean
import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.signalFlow
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gobject.SignalManager
import nativex.gobject.Signals
import nativex.gobject.connectSignal
import nativex.gtk.IconSize
import nativex.gtk.common.enums.Orientation
import nativex.gtk.common.enums.ReliefStyle
import nativex.gtk.common.enums.ToolbarStyle
import nativex.gtk.widgets.container.bin.toolitem.ToolItem
import nativex.gtk.widgets.container.bin.toolitem.ToolItem.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 24 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html">GtkToolbar</a>
 */
class Toolbar(
	 val toolbarPointer: CPointer<GtkToolbar>
) : Container(toolbarPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html#gtk-toolbar-new">gtk_toolbar_new</a>
	 */
	constructor() : this(gtk_toolbar_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html#gtk-toolbar-insert">gtk_toolbar_insert</a>
	 */
	fun insert(item: ToolItem, position: Int) {
		gtk_toolbar_insert(toolbarPointer, item.toolItemPointer, position)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html#gtk-toolbar-get-item-index">gtk_toolbar_get_item_index</a>
	 */
	fun getItemIndex(item: ToolItem): Int =
		gtk_toolbar_get_item_index(toolbarPointer, item.toolItemPointer)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html#gtk-toolbar-get-n-items">gtk_toolbar_get_n_items</a>
	 */
	val itemCount: Int
		get() = gtk_toolbar_get_n_items(toolbarPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html#gtk-toolbar-get-nth-item">gtk_toolbar_get_nth_item</a>
	 */
	fun getItem(index: Int): ToolItem? =
		gtk_toolbar_get_nth_item(toolbarPointer, index).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html#gtk-toolbar-get-drop-index">gtk_toolbar_get_drop_index</a>
	 */
	fun getDropIndex(x: Int, y: Int): Int =
		gtk_toolbar_get_drop_index(toolbarPointer, x, y)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html#gtk-toolbar-set-drop-highlight-item">gtk_toolbar_set_drop_highlight_item</a>
	 */
	fun setDropHighlightItem(item: ToolItem?, index: Int) {
		gtk_toolbar_set_drop_highlight_item(
			toolbarPointer,
			item?.toolItemPointer,
			index
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html#gtk-toolbar-get-show-arrow">gtk_toolbar_get_show_arrow</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html#gtk-toolbar-set-show-arrow">gtk_toolbar_set_show_arrow</a>
	 */
	var showArrow: Boolean
		get() = gtk_toolbar_get_show_arrow(toolbarPointer).bool
		set(value) = gtk_toolbar_set_show_arrow(toolbarPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html#gtk-toolbar-unset-icon-size">gtk_toolbar_unset_icon_size</a>
	 */
	fun unsetIconSize() {
		gtk_toolbar_unset_icon_size(toolbarPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html#gtk-toolbar-get-relief-style">gtk_toolbar_get_relief_style</a>
	 */
	val reliefStyle: ReliefStyle
		get() = ReliefStyle.valueOf(gtk_toolbar_get_relief_style(toolbarPointer))!!

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html#gtk-toolbar-get-icon-size">gtk_toolbar_get_icon_size</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html#gtk-toolbar-set-icon-size">gtk_toolbar_set_icon_size</a>
	 */
	var iconSize: IconSize?
		get() = IconSize.valueOf(
			gtk_toolbar_get_icon_size(toolbarPointer)
		)
		set(value) =
			if (value == null)
				unsetIconSize()
			else
				gtk_toolbar_set_icon_size(
					toolbarPointer,
					value.gtk
				)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html#gtk-toolbar-get-style">gtk_toolbar_get_style</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html#gtk-toolbar-set-style">gtk_toolbar_set_style</a>
	 */
	var style: ToolbarStyle?
		get() = ToolbarStyle.valueOf(
			gtk_toolbar_get_style(
				toolbarPointer
			)
		)
		set(value) =
			if (value == null)
				unsetStyle()
			else
				gtk_toolbar_set_style(toolbarPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolbar.html#gtk-toolbar-unset-style">gtk_toolbar_unset_style</a>
	 */
	fun unsetStyle() {
		gtk_toolbar_unset_style(toolbarPointer)
	}

	@ExperimentalCoroutinesApi
	val orientationChangedSignal: Flow<Orientation> by signalFlow(
		Signals.ORIENTATION_CHANGED,
		staticOrientationChangedCallback
	)

	@ExperimentalCoroutinesApi
	val styleChanged: Flow<ToolbarStyle> by signalFlow(Signals.STYLE_CHANGED, staticStyleChangedCallback)

	fun addFocusHomeOrEndFunction(action: FocusHomeOrEndFunction): SignalManager =
		SignalManager(
			toolbarPointer,
			toolbarPointer.connectSignal(
				Signals.FOCUS_HOME_OR_END,
				callbackWrapper = StableRef.create(action).asCPointer(),
				handler = staticFocusHomeOrEndFunction
			)
		)

	fun addPopupContextMenuFunction(action: PopupContextMenuFunction): SignalManager =
		SignalManager(
			toolbarPointer,
			toolbarPointer.connectSignal(
				Signals.POPUP_CONTEXT_MENU,
				callbackWrapper = StableRef.create(action).asCPointer(),
				handler = staticPopupContextMenuFunction
			)
		)

	companion object {
		 val staticOrientationChangedCallback: GCallback =
			staticCFunction { _: gpointer, orientation: GtkOrientation, data: gpointer? ->
				data?.asStableRef<(Orientation) -> Unit>()?.get()?.invoke(Orientation.valueOf(orientation)!!)
				Unit
			}.reinterpret()

		 val staticStyleChangedCallback: GCallback =
			staticCFunction { _: gpointer, orientation: GtkToolbarStyle, data: gpointer? ->
				data?.asStableRef<(ToolbarStyle) -> Unit>()?.get()?.invoke(ToolbarStyle.valueOf(orientation)!!)
				Unit
			}.reinterpret()

		 val staticFocusHomeOrEndFunction: GCallback =
			staticCFunction { _: gpointer, focusHome: gboolean, data: gpointer? ->
				data?.asStableRef<FocusHomeOrEndFunction>()?.get()?.invoke(focusHome.bool).gtk
			}.reinterpret()

		 val staticPopupContextMenuFunction: GCallback =
			staticCFunction { _: gpointer, x: Int, y: Int, button: Int, data: gpointer? ->
				data?.asStableRef<PopupContextMenuFunction>()?.get()?.invoke(x, y, button).gtk
			}.reinterpret()
	}
}
typealias FocusHomeOrEndFunction = (@ParameterName("focusHome") Boolean) -> Boolean

typealias PopupContextMenuFunction = (@ParameterName("x") Int, @ParameterName("y") Int, @ParameterName("button") Int) -> Boolean
