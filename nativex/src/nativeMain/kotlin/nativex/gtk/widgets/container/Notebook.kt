package nativex.gtk.widgets.container

import gtk.*
import kotlinx.cinterop.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.callbackSignalFlow
import nativex.gtk.*
import nativex.gtk.common.enums.DirectionType
import nativex.gtk.common.enums.PackType
import nativex.gtk.common.enums.PositionType
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 */
class Notebook internal constructor(
	internal val noteBookPointer: CPointer<GtkNotebook>
) : Container(noteBookPointer.reinterpret()) {
	var showTabs: Boolean
		get() = gtk_notebook_get_show_tabs(noteBookPointer).bool
		set(value) = gtk_notebook_set_show_tabs(noteBookPointer, value.gtk)
	var showBorder: Boolean
		get() = gtk_notebook_get_show_border(noteBookPointer).bool
		set(value) = gtk_notebook_set_show_border(noteBookPointer, value.gtk)
	var scrollable: Boolean
		get() = gtk_notebook_get_scrollable(noteBookPointer).bool
		set(value) = gtk_notebook_set_scrollable(noteBookPointer, value.gtk)
	var currentPage: Int
		get() = gtk_notebook_get_current_page(noteBookPointer)
		set(value) = gtk_notebook_set_current_page(noteBookPointer, value)
	val pageCount: Int
		get() = gtk_notebook_get_n_pages(noteBookPointer)
	var tabPos: PositionType
		set(value) = gtk_notebook_set_tab_pos(noteBookPointer, value.gtk)
		get() = PositionType.valueOf(
			gtk_notebook_get_tab_pos(
				noteBookPointer
			)
		)!!
	var groupName: String?
		get() = gtk_notebook_get_group_name(noteBookPointer)?.toKString()
		set(value) = gtk_notebook_set_group_name(noteBookPointer, value)

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val changeCurrentPageSignal: Flow<Int> by lazy {
		callbackSignalFlow(
			Signals.CHANGE_CURRENT_PAGE,
			staticChangeCurrentPageCallback
		)
	}

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val createWindowSignal: Flow<CreateWindowEvent> by lazy {
		callbackSignalFlow(
			Signals.CREATE_WINDOW,
			CreateWindowEvent.staticCallback
		)
	}

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val focusTabSignal: Flow<Any>
		get() = TODO("Figure out GtkNotebookTab")

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val moveFocusOutSignal: Flow<GtkDirectionType> by lazy {
		callbackSignalFlow(Signals.MOVE_FOCUS_OUT, staticMoveFocusOutCallback)
	}

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val pageAddedSignal: Flow<PageAddedEvent> by lazy {
		callbackSignalFlow(
			Signals.CREATE_WINDOW,
			PageAddedEvent.staticCallback
		)
	}

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val pageRemovedSignal: Flow<PageRemovedEvent> by lazy {
		callbackSignalFlow(
			Signals.CREATE_WINDOW,
			PageRemovedEvent.staticCallback
		)
	}

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val pageReorderedSignal: Flow<PageReorderedEvent> by lazy {
		callbackSignalFlow(
			Signals.CREATE_WINDOW,
			PageReorderedEvent.staticCallback
		)
	}

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val reorderTabSignal: Flow<ReorderTabEvent> by lazy {
		callbackSignalFlow(
			Signals.CREATE_WINDOW,
			ReorderTabEvent.staticCallback
		)
	}

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val selectPageSignal: Flow<Boolean> by lazy {
		callbackSignalFlow(Signals.SWITCH_PAGE, staticSelectPageCallback)
	}

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val switchPageSignal: Flow<SwitchPageEvent> by lazy {
		callbackSignalFlow(Signals.SWITCH_PAGE, SwitchPageEvent.staticCallback)
	}

	fun appendPage(
		child: Widget,
		tabLabel: Widget?
	): Int = gtk_notebook_append_page(
		noteBookPointer,
		child.widgetPointer,
		tabLabel?.widgetPointer
	)

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

	fun prependPage(
		child: Widget,
		tabLabel: Widget?
	): Int = gtk_notebook_prepend_page(
		noteBookPointer,
		child.widgetPointer,
		tabLabel?.widgetPointer
	)

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

	fun removePage(pageNum: Int) {
		gtk_notebook_remove_page(noteBookPointer, pageNum)
	}

	fun detachTab(child: Widget) {
		gtk_notebook_detach_tab(noteBookPointer, child.widgetPointer)
	}

	fun pageNumOf(child: Widget): Int =
		gtk_notebook_page_num(noteBookPointer, child.widgetPointer)

	fun nextPage() {
		gtk_notebook_next_page(noteBookPointer)
	}

	fun previousPage() {
		gtk_notebook_prev_page(noteBookPointer)
	}

	fun reorderChild(
		child: Widget,
		position: Int
	) = gtk_notebook_reorder_child(
		noteBookPointer,
		child.widgetPointer,
		position
	)

	fun popupEnable() {
		gtk_notebook_popup_enable(noteBookPointer)
	}
	fun popupDisable() {
		gtk_notebook_popup_disable(noteBookPointer)
	}

	fun getMenuLabel(child: Widget): Widget? =
		gtk_notebook_get_menu_label(
			notebook = null,
			child.widgetPointer
		).asWidgetOrNull()

	fun getNthPage(pageNum: Int): Widget? =
		gtk_notebook_get_nth_page(noteBookPointer, pageNum).asWidgetOrNull()

	fun getTabLabel(child: Widget): Widget? =
		gtk_notebook_get_tab_label(
			notebook = null,
			child.widgetPointer
		).asWidgetOrNull()

	fun setMenuLabel(child: Widget, menuLabel: Widget? = null) =
		gtk_notebook_set_menu_label(
			noteBookPointer,
			child.widgetPointer,
			menuLabel?.widgetPointer
		)

	fun setMenuLabelText(child: Widget, menuText: String) =
		gtk_notebook_set_menu_label_text(
			noteBookPointer,
			child.widgetPointer,
			menuText
		)

	fun setTabLabel(child: Widget, tabLabel: Widget? = null) =
		gtk_notebook_set_tab_label(
			noteBookPointer,
			child.widgetPointer,
			tabLabel?.widgetPointer
		)

	fun setTabLabelText(child: Widget, tabText: String) =
		gtk_notebook_set_tab_label_text(
			noteBookPointer,
			child.widgetPointer,
			tabText
		)

	fun setTabReorderable(child: Widget, reorderable: Boolean) {
		gtk_notebook_set_tab_reorderable(
			noteBookPointer,
			child.widgetPointer,
			reorderable.gtk
		)
	}

	fun setTabDetachable(child: Widget, detachable: Boolean) {
		gtk_notebook_set_tab_detachable(
			noteBookPointer,
			child.widgetPointer,
			detachable.gtk
		)
	}

	fun getMenuLabelText(child: Widget): String? =
		gtk_notebook_get_menu_label_text(
			noteBookPointer,
			child.widgetPointer
		)?.toKString()

	fun getTabLabelText(child: Widget): String? =
		gtk_notebook_get_tab_label_text(
			noteBookPointer,
			child.widgetPointer
		)?.toKString()

	fun getTabReorderable(child: Widget): Boolean =
		gtk_notebook_get_tab_reorderable(
			noteBookPointer,
			child.widgetPointer
		).bool

	fun getTabDetachable(child: Widget): Boolean =
		gtk_notebook_get_tab_detachable(
			noteBookPointer,
			child.widgetPointer
		).bool

	fun setActionWidget(widget: Widget, packType: PackType) {
		gtk_notebook_set_action_widget(
			noteBookPointer,
			widget.widgetPointer,
			packType.gtk
		)
	}

	fun getActionWidget(packType: PackType): Widget? =
		gtk_notebook_get_action_widget(
			noteBookPointer,
			packType.gtk
		).asWidgetOrNull()

	data class CreateWindowEvent(
		val page: Widget,
		val x: Int,
		val y: Int
	) {
		companion object {
			val staticCallback: GCallback =
				staticCFunction { _: gpointer,
				                  page: CPointer<GtkWidget>,
				                  x: Int,
				                  y: Int,
				                  data: gpointer? ->
					data?.asStableRef<(CreateWindowEvent) -> Unit>()?.get()
						?.invoke(CreateWindowEvent(Widget(page), x, y))
					Unit
				}.reinterpret()
		}
	}

	data class PageAddedEvent @ExperimentalUnsignedTypes constructor(
		val child: Widget,
		val pageNumber: UInt
	) {
		companion object {
			@ExperimentalUnsignedTypes
			internal val staticCallback: GCallback =
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

	data class PageRemovedEvent @ExperimentalUnsignedTypes constructor(
		val child: Widget,
		val pageNumber: UInt
	) {
		companion object {
			@ExperimentalUnsignedTypes
			internal val staticCallback: GCallback =
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

	data class PageReorderedEvent @ExperimentalUnsignedTypes constructor(
		val child: Widget,
		val pageNumber: UInt
	) {
		companion object {
			@ExperimentalUnsignedTypes
			internal val staticCallback: GCallback =
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

	data class ReorderTabEvent(
		val arg1: DirectionType,
		val arg2: Boolean
	) {
		companion object {
			internal val staticCallback: GCallback =
				staticCFunction { _: gpointer?, arg1: GtkDirectionType, arg2: gboolean, data: gpointer? ->
					data?.asStableRef<(ReorderTabEvent) -> Unit>()
						?.get()
						?.invoke(
							ReorderTabEvent(
								DirectionType.valueOf(arg1)!!,
								arg2.bool
							)
						)
					Unit
				}.reinterpret()

		}
	}

	data class SwitchPageEvent @ExperimentalUnsignedTypes constructor(
		val page: Widget,
		val pageNumber: UInt
	) {
		companion object {
			@ExperimentalUnsignedTypes
			internal val staticCallback: GCallback =
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

	companion object {
		internal val staticChangeCurrentPageCallback: GCallback =
			staticCFunction { _: gpointer?, arg1: Int, data: gpointer? ->
				data?.asStableRef<(Int) -> Unit>()
					?.get()
					?.invoke(arg1)
				Unit
			}.reinterpret()

		/**
		 * TODO Figure out [GtkNotebookTab]
		 */
		internal val staticFocusTabCallback: GCallback =
			staticCFunction { _: gpointer?, arg1: GtkNotebookTab, data: gpointer? ->
				data?.asStableRef<(GtkNotebookTab) -> Unit>()
					?.get()
					?.invoke(arg1)
				Unit
			}.reinterpret()

		internal val staticMoveFocusOutCallback: GCallback =
			staticCFunction { _: gpointer?, arg1: GtkDirectionType, data: gpointer? ->
				data?.asStableRef<(DirectionType) -> Unit>()
					?.get()
					?.invoke(DirectionType.valueOf(arg1)!!)
				Unit
			}.reinterpret()

		internal val staticSelectPageCallback: GCallback =
			staticCFunction { _: gpointer?, arg1: gboolean, data: gpointer? ->
				data?.asStableRef<(Boolean) -> Unit>()
					?.get()
					?.invoke(arg1.bool)
				Unit
			}.reinterpret()


	}

}