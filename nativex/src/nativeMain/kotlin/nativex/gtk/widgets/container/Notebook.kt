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
import nativex.gobject.Signals.PAGE_REORDERED
import nativex.gobject.connectSignal
import nativex.gtk.WidgetPointer
import nativex.gtk.asWidgetOrNull
import nativex.gtk.common.enums.DirectionType
import nativex.gtk.common.enums.PackType
import nativex.gtk.common.enums.PositionType
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html">GtkNotebook</a>
 */
class Notebook(
	 val noteBookPointer: CPointer<GtkNotebook>
) : Container(noteBookPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-new">gtk_notebook_new</a>
	 */
	constructor() : this(gtk_notebook_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-get-show-tabs">gtk_notebook_get_show_tabs</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-set-show-tabs">gtk_notebook_set_show_tabs</a>
	 */
	var showTabs: Boolean
		get() = gtk_notebook_get_show_tabs(noteBookPointer).bool
		set(value) = gtk_notebook_set_show_tabs(noteBookPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-get-show-border">gtk_notebook_get_show_border</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-set-show-border">gtk_notebook_set_show_border</a>
	 */
	var showBorder: Boolean
		get() = gtk_notebook_get_show_border(noteBookPointer).bool
		set(value) = gtk_notebook_set_show_border(noteBookPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-get-scrollable">gtk_notebook_get_scrollable</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-set-scrollable">gtk_notebook_set_scrollable</a>
	 */
	var scrollable: Boolean
		get() = gtk_notebook_get_scrollable(noteBookPointer).bool
		set(value) = gtk_notebook_set_scrollable(noteBookPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-get-current-page">gtk_notebook_get_current_page</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-set-current-page">gtk_notebook_set_current_page</a>
	 */
	var currentPage: Int
		get() = gtk_notebook_get_current_page(noteBookPointer)
		set(value) = gtk_notebook_set_current_page(noteBookPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-get-n-pages">gtk_notebook_get_n_pages</a>
	 */
	val pageCount: Int
		get() = gtk_notebook_get_n_pages(noteBookPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-get-tab-pos">gtk_notebook_get_tab_pos</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-set-tab-pos">gtk_notebook_set_tab_pos</a>
	 */
	var tabPos: PositionType
		set(value) = gtk_notebook_set_tab_pos(noteBookPointer, value.gtk)
		get() = PositionType.valueOf(
			gtk_notebook_get_tab_pos(
				noteBookPointer
			)
		)!!

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-get-group-name">gtk_notebook_get_group_name</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-set-group-name">gtk_notebook_set_group_name</a>
	 */
	var groupName: String?
		get() = gtk_notebook_get_group_name(noteBookPointer)?.toKString()
		set(value) = gtk_notebook_set_group_name(noteBookPointer, value)


	/**
	 * [SignalManager] for [setChangeCurrentPageCallback]
	 */
	private var changeCurrentPageManager: SignalManager? = null

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#GtkNotebook-change-current-page">change-current-page</a>
	 */
	fun setChangeCurrentPageCallback(createWindow: ChangeCurrentPageFunction) {
		changeCurrentPageManager?.disconnect()
		changeCurrentPageManager = SignalManager(
			noteBookPointer,
			noteBookPointer.connectSignal(
				Signals.CHANGE_CURRENT_PAGE,
				callbackWrapper = StableRef.create(createWindow).asCPointer(),
				handler = staticChangeCurrentPageFunction
			)
		)
	}

	/**
	 * [SignalManager] for [setOnCreateWindowCallback]
	 */
	private var createWindowManager: SignalManager? = null

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#GtkNotebook-create-window">create-window</a>
	 */
	fun setOnCreateWindowCallback(createWindow: CreateWindowFunction) {
		createWindowManager?.disconnect()
		createWindowManager = SignalManager(
			noteBookPointer,
			noteBookPointer.connectSignal(
				Signals.CREATE_WINDOW,
				callbackWrapper = StableRef.create(createWindow).asCPointer(),
				handler = staticCreateWindowFunction
			)
		)
	}

	/**
	 * [SignalManager] for [setFocusTabCallback]
	 */
	private var focusTabManager: SignalManager? = null

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#GtkNotebook-focus-tab">focus-tab</a>
	 */
	fun setFocusTabCallback(action: FocusTabFunction) {
		focusTabManager?.disconnect()
		focusTabManager = SignalManager(
			noteBookPointer,
			noteBookPointer.connectSignal(
				signal = Signals.FOCUS_TAB,
				callbackWrapper = StableRef.create(action).asCPointer(),
				handler = staticFocusTabFunction
			)
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#GtkNotebook-move-focus-out">move-focus-out</a>
	 */
	@ExperimentalCoroutinesApi
	val moveFocusOutSignal: Flow<GtkDirectionType> by signalFlow(Signals.MOVE_FOCUS_OUT, DirectionType.staticDirectionTypeCallback)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#GtkNotebook-page-added">create-window</a>
	 */
	@ExperimentalCoroutinesApi
	val pageAddedSignal: Flow<PageAddedEvent> by signalFlow(Signals.PAGE_ADDED, PageAddedEvent.staticCallback)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#GtkNotebook-page-removed">create-window</a>
	 */
	@ExperimentalCoroutinesApi
	val pageRemovedSignal: Flow<PageRemovedEvent> by signalFlow(Signals.PAGE_REMOVED, PageRemovedEvent.staticCallback)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#GtkNotebook-page-reordered">create-window</a>
	 */
	@ExperimentalCoroutinesApi
	val pageReorderedSignal: Flow<PageReorderedEvent> by signalFlow(PAGE_REORDERED, PageReorderedEvent.staticCallback)

	/**
	 * [SignalManager] for [setReorderTabCallback]
	 */
	private var reorderTabManager: SignalManager? = null

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#GtkNotebook-reorder-tab">reorder-tab</a>
	 */
	fun setReorderTabCallback(action: ReorderTabFunction) {
		reorderTabManager?.disconnect()
		reorderTabManager = SignalManager(
			noteBookPointer,
			noteBookPointer.connectSignal(
				signal = Signals.REORDER_TAB,
				callbackWrapper = StableRef.create(action).asCPointer(),
				handler = staticReorderTabFunction
			)
		)
	}

	/**
	 * [SignalManager] for [setSelectPageCallback]
	 */
	private var selectPageManager: SignalManager? = null

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#GtkNotebook-select-page">select-page</a>
	 */
	fun setSelectPageCallback(action: SelectPageFunction) {
		selectPageManager?.disconnect()
		selectPageManager = SignalManager(
			noteBookPointer,
			noteBookPointer.connectSignal(
				signal = Signals.SELECT_PAGE,
				callbackWrapper = StableRef.create(action).asCPointer(),
				handler = staticSelectPageFunction
			)
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#GtkNotebook-switch-page">switch-page</a>
	 */
	@ExperimentalCoroutinesApi
	val switchPageSignal: Flow<SwitchPageEvent> by signalFlow(Signals.SWITCH_PAGE, SwitchPageEvent.staticCallback)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-append-page">gtk_notebook_append_page</a>
	 */
	fun appendPage(
		child: Widget,
		tabLabel: Widget?
	): Int = gtk_notebook_append_page(
		noteBookPointer,
		child.widgetPointer,
		tabLabel?.widgetPointer
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-append-page-menu">gtk_notebook_append_page_menu</a>
	 */
	fun appendPageMenu(
		child: Widget,
		tabLabel: Widget?,
		menuLabel: Widget?
	): Int = gtk_notebook_append_page_menu(
		noteBookPointer,
		child.widgetPointer,
		tabLabel?.widgetPointer,
		menuLabel?.widgetPointer
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-prepend-page">gtk_notebook_prepend_page</a>
	 */
	fun prependPage(
		child: Widget,
		tabLabel: Widget?
	): Int = gtk_notebook_prepend_page(
		noteBookPointer,
		child.widgetPointer,
		tabLabel?.widgetPointer
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-prepend-page-menu">gtk_notebook_prepend_page_menu</a>
	 */
	fun prependPageMenu(
		child: Widget,
		tabLabel: Widget?,
		menuLabel: Widget?
	): Int = gtk_notebook_prepend_page_menu(
		noteBookPointer,
		child.widgetPointer,
		tabLabel?.widgetPointer,
		menuLabel?.widgetPointer
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-insert-page">gtk_notebook_insert_page</a>
	 */
	fun insertPage(
		child: Widget,
		tabLabel: Widget?,
		position: Int
	): Int = gtk_notebook_insert_page(
		noteBookPointer,
		child.widgetPointer,
		tabLabel?.widgetPointer,
		position
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-insert-page-menu">gtk_notebook_insert_page_menu</a>
	 */
	fun insertPageMenu(
		child: Widget,
		tabLabel: Widget?,
		menuLabel: Widget?,
		position: Int
	): Int = gtk_notebook_insert_page_menu(
		noteBookPointer,
		child.widgetPointer,
		tabLabel?.widgetPointer,
		menuLabel?.widgetPointer,
		position
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-remove-page">gtk_notebook_remove_page</a>
	 */
	fun removePage(pageNum: Int) {
		gtk_notebook_remove_page(noteBookPointer, pageNum)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-detach-tab">gtk_notebook_detach_tab</a>
	 */
	fun detachTab(child: Widget) {
		gtk_notebook_detach_tab(noteBookPointer, child.widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-page-num">gtk_notebook_page_num</a>
	 */
	fun pageNumOf(child: Widget): Int =
		gtk_notebook_page_num(noteBookPointer, child.widgetPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-next-page">gtk_notebook_next_page</a>
	 */
	fun nextPage() {
		gtk_notebook_next_page(noteBookPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-prev-page">gtk_notebook_prev_page</a>
	 */
	fun previousPage() {
		gtk_notebook_prev_page(noteBookPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-reorder-child">gtk_notebook_reorder_child</a>
	 */
	fun reorderChild(
		child: Widget,
		position: Int
	) = gtk_notebook_reorder_child(
		noteBookPointer,
		child.widgetPointer,
		position
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-popup-enable">gtk_notebook_popup_enable</a>
	 */
	fun popupEnable() {
		gtk_notebook_popup_enable(noteBookPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-popup-disable">gtk_notebook_popup_disable</a>
	 */
	fun popupDisable() {
		gtk_notebook_popup_disable(noteBookPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-get-menu-label">gtk_notebook_get_menu_label</a>
	 */
	fun getMenuLabel(child: Widget): Widget? =
		gtk_notebook_get_menu_label(
			notebook = null,
			child.widgetPointer
		).asWidgetOrNull()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-get-nth-page">gtk_notebook_get_nth_page</a>
	 */
	fun getNthPage(pageNum: Int): Widget? =
		gtk_notebook_get_nth_page(noteBookPointer, pageNum).asWidgetOrNull()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-get-tab-label">gtk_notebook_get_tab_label</a>
	 */
	fun getTabLabel(child: Widget): Widget? =
		gtk_notebook_get_tab_label(
			notebook = null,
			child.widgetPointer
		).asWidgetOrNull()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-set-menu-label">gtk_notebook_set_menu_label</a>
	 */
	fun setMenuLabel(child: Widget, menuLabel: Widget? = null) =
		gtk_notebook_set_menu_label(
			noteBookPointer,
			child.widgetPointer,
			menuLabel?.widgetPointer
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-set-menu-label-text">gtk_notebook_set_menu_label_text</a>
	 */
	fun setMenuLabelText(child: Widget, menuText: String) =
		gtk_notebook_set_menu_label_text(
			noteBookPointer,
			child.widgetPointer,
			menuText
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-set-tab-label">gtk_notebook_set_tab_label</a>
	 */
	fun setTabLabel(child: Widget, tabLabel: Widget? = null) =
		gtk_notebook_set_tab_label(
			noteBookPointer,
			child.widgetPointer,
			tabLabel?.widgetPointer
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-set-tab-label-text">gtk_notebook_set_tab_label_text</a>
	 */
	fun setTabLabelText(child: Widget, tabText: String) =
		gtk_notebook_set_tab_label_text(
			noteBookPointer,
			child.widgetPointer,
			tabText
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-set-tab-reorderable">gtk_notebook_set_tab_reorderable</a>
	 */
	fun setTabReorderable(child: Widget, reorderable: Boolean) {
		gtk_notebook_set_tab_reorderable(
			noteBookPointer,
			child.widgetPointer,
			reorderable.gtk
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-set-tab-detachable">gtk_notebook_set_tab_detachable</a>
	 */
	fun setTabDetachable(child: Widget, detachable: Boolean) {
		gtk_notebook_set_tab_detachable(
			noteBookPointer,
			child.widgetPointer,
			detachable.gtk
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-get-menu-label-text">gtk_notebook_get_menu_label_text</a>
	 */
	fun getMenuLabelText(child: Widget): String? =
		gtk_notebook_get_menu_label_text(
			noteBookPointer,
			child.widgetPointer
		)?.toKString()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-get-tab-label-text">gtk_notebook_get_tab_label_text</a>
	 */
	fun getTabLabelText(child: Widget): String? =
		gtk_notebook_get_tab_label_text(
			noteBookPointer,
			child.widgetPointer
		)?.toKString()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-get-tab-reorderable">gtk_notebook_get_tab_reorderable</a>
	 */
	fun getTabReorderable(child: Widget): Boolean =
		gtk_notebook_get_tab_reorderable(
			noteBookPointer,
			child.widgetPointer
		).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-get-tab-detachable">gtk_notebook_get_tab_detachable</a>
	 */
	fun getTabDetachable(child: Widget): Boolean =
		gtk_notebook_get_tab_detachable(
			noteBookPointer,
			child.widgetPointer
		).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-set-action-widget">gtk_notebook_set_action_widget</a>
	 */
	fun setActionWidget(widget: Widget, packType: PackType) {
		gtk_notebook_set_action_widget(
			noteBookPointer,
			widget.widgetPointer,
			packType.gtk
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#gtk-notebook-get-action-widget">gtk_notebook_get_action_widget</a>
	 */
	fun getActionWidget(packType: PackType): Widget? =
		gtk_notebook_get_action_widget(
			noteBookPointer,
			packType.gtk
		).asWidgetOrNull()

	/**
	 * Data returned by "page-reordered" signal via [pageAddedSignal]
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#GtkNotebook-page-added">create-window</a>
	 */
	data class PageAddedEvent constructor(
		val child: Widget,
		val pageNumber: UInt
	) {
		companion object {

			 val staticCallback: GCallback =
				staticCFunction { _: gpointer?, child: WidgetPointer, pageNum: UInt, data: gpointer? ->
					data?.asStableRef<(PageAddedEvent) -> Unit>()
						?.get()
						?.invoke(
							PageAddedEvent(
								Widget(child),
								pageNum
							)
						)
					Unit
				}.reinterpret()

		}
	}

	/**
	 * Data returned by "page-reordered" signal via [pageRemovedSignal]
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#GtkNotebook-page-removed">create-window</a>
	 */
	data class PageRemovedEvent constructor(
		val child: Widget,
		val pageNumber: UInt
	) {
		companion object {

			 val staticCallback: GCallback =
				staticCFunction { _: gpointer?, widget: WidgetPointer, pageNum: UInt, data: gpointer? ->
					data?.asStableRef<(PageRemovedEvent) -> Unit>()
						?.get()
						?.invoke(
							PageRemovedEvent(
								Widget(widget),
								pageNum
							)
						)
					Unit
				}.reinterpret()

		}
	}

	/**
	 * Data returned by "page-reordered" signal via [pageReorderedSignal]
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#GtkNotebook-page-reordered">create-window</a>
	 */
	data class PageReorderedEvent constructor(
		val child: Widget,
		val pageNumber: UInt
	) {
		companion object {

			 val staticCallback: GCallback =
				staticCFunction { _: gpointer?, child: WidgetPointer, pageNum: UInt, data: gpointer? ->
					data?.asStableRef<(PageReorderedEvent) -> Unit>()
						?.get()
						?.invoke(
							PageReorderedEvent(
								Widget(child),
								pageNum
							)
						)
					Unit
				}.reinterpret()

		}
	}

	/**
	 * Data returned by "switch-page" signal via [switchPageSignal]
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkNotebook.html#GtkNotebook-switch-page">switch-page</a>
	 */
	data class SwitchPageEvent constructor(
		val page: Widget,
		val pageNumber: UInt
	) {
		companion object {

			 val staticCallback: GCallback =
				staticCFunction { _: gpointer?, page: WidgetPointer, pageNum: UInt, data: gpointer? ->
					data?.asStableRef<(SwitchPageEvent) -> Unit>()
						?.get()
						?.invoke(
							SwitchPageEvent(
								Widget(page),
								pageNum
							)
						)
					Unit
				}.reinterpret()
		}
	}

	enum class Tab(val key: Int,  val gtk: GtkNotebookTab) {
		FIRST(0, GtkNotebookTab.GTK_NOTEBOOK_TAB_FIRST),
		LAST(1, GtkNotebookTab.GTK_NOTEBOOK_TAB_LAST);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			 fun valueOf(gtk: GtkNotebookTab) = values().find { it.gtk == gtk }
		}
	}

	companion object {

		 val staticChangeCurrentPageFunction: GCallback =
			staticCFunction { _: gpointer?, arg1: Int, data: gpointer? ->
				data?.asStableRef<ChangeCurrentPageFunction>()
					?.get()
					?.invoke(arg1).gtk
			}.reinterpret()

		 val staticCreateWindowFunction: GCallback =
			staticCFunction { _: gpointer,
			                  page: CPointer<GtkWidget>,
			                  x: Int,
			                  y: Int,
			                  data: gpointer? ->
				data?.asStableRef<CreateWindowFunction>()?.get()
					?.invoke(Widget(page), x, y)?.widgetPointer
			}.reinterpret()

		 val staticFocusTabFunction: GCallback =
			staticCFunction { _: gpointer?, arg1: GtkNotebookTab, data: gpointer? ->
				data?.asStableRef<FocusTabFunction>()
					?.get()
					?.invoke(Tab.valueOf(arg1)!!).gtk
			}.reinterpret()

		 val staticReorderTabFunction: GCallback =
			staticCFunction { _: gpointer?, arg1: GtkDirectionType, arg2: gboolean, data: gpointer? ->
				data?.asStableRef<ReorderTabFunction>()
					?.get()
					?.invoke(DirectionType.valueOf(arg1)!!, arg2.bool).gtk
			}.reinterpret()

		 val staticSelectPageFunction: GCallback =
			staticCFunction { _: gpointer?, arg2: gboolean, data: gpointer? ->
				data?.asStableRef<SelectPageFunction>()
					?.get()
					?.invoke(arg2.bool).gtk
			}.reinterpret()

	}
}

typealias ChangeCurrentPageFunction = (
	@ParameterName("arg1")
	Int
) -> Boolean

typealias CreateWindowFunction = (@ParameterName("page") Widget, @ParameterName("x") Int, @ParameterName("y") Int) -> Notebook

typealias FocusTabFunction = (Notebook.Tab) -> Boolean

typealias ReorderTabFunction = (DirectionType, Boolean) -> Boolean

typealias SelectPageFunction = (Boolean) -> Boolean