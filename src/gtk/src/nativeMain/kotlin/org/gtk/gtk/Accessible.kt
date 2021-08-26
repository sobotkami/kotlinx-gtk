package org.gtk.gtk

import gtk.GtkAccessible
import gtk.GtkAccessibleRole
import gtk.GtkAccessibleRole.*
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 *
 * 28 / 07 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/iface.Accessible.html">GtkAccessible</a>
 */
interface Accessible {
	val accessiblePointer: CPointer<GtkAccessible>

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/enum.AccessibleRole.html">GtkAccessibleRole</a>
	 */
	enum class Role(val gtk: GtkAccessibleRole) {
		/**
		 * An element with important, and usually time-sensitive, information.
		 */
		ALERT(GTK_ACCESSIBLE_ROLE_ALERT),

		/**
		 * A type of dialog that contains an alert message.
		 */
		ALERT_DIALOG(GTK_ACCESSIBLE_ROLE_ALERT_DIALOG),

		/**
		 * Unused.
		 */
		BANNER(GTK_ACCESSIBLE_ROLE_BANNER),

		/**
		 * An input element that allows for user-triggered actions when clicked or pressed.
		 */
		BUTTON(GTK_ACCESSIBLE_ROLE_BUTTON),

		/**
		 * Unused.
		 */
		CAPTION(GTK_ACCESSIBLE_ROLE_CAPTION),

		/**
		 * Unused.
		 */
		CELL(GTK_ACCESSIBLE_ROLE_CELL),

		/**
		 * A checkable input element that has three possible values: true, false, or mixed
		 */
		CHECKBOX(GTK_ACCESSIBLE_ROLE_CHECKBOX),

		/**
		 * A header in a columned list.
		 */
		COLUMN_HEADER(GTK_ACCESSIBLE_ROLE_COLUMN_HEADER),

		/**
		 * An input that controls another element, such as a list or a grid, that can dynamically pop up to help the user set the value of the input.
		 */
		COMBO_BOX(GTK_ACCESSIBLE_ROLE_COMBO_BOX),

		/**
		 * Abstract role.
		 */
		COMMAND(GTK_ACCESSIBLE_ROLE_COMMAND),

		/**
		 * Abstract role.
		 */
		COMPOSITE(GTK_ACCESSIBLE_ROLE_COMPOSITE),

		/**
		 * A dialog is a window that is designed to interrupt the current processing of an application in order to prompt the user to enter information or require a response.
		 */
		DIALOG(GTK_ACCESSIBLE_ROLE_DIALOG),

		/**
		 * Unused.
		 */
		DOCUMENT(GTK_ACCESSIBLE_ROLE_DOCUMENT),

		/**
		 * Unused.
		 */
		FEED(GTK_ACCESSIBLE_ROLE_FEED),

		/**
		 * Unused.
		 */
		FORM(GTK_ACCESSIBLE_ROLE_FORM),

		/**
		 * Unused.
		 */
		GENERIC(GTK_ACCESSIBLE_ROLE_GENERIC),

		/**
		 * A grid of items.
		 */
		GRID(GTK_ACCESSIBLE_ROLE_GRID),

		/**
		 * An item in a grid or tree grid.
		 */
		GRID_CELL(GTK_ACCESSIBLE_ROLE_GRID_CELL),

		/**
		 * An element that groups multiple widgets. GTK uses this role for various containers, like GtkBox, GtkViewport, and GtkHeaderBar.
		 */
		GROUP(GTK_ACCESSIBLE_ROLE_GROUP),

		/**
		 * Unused.
		 */
		HEADING(GTK_ACCESSIBLE_ROLE_HEADING),

		/**
		 * An image.
		 */
		IMG(GTK_ACCESSIBLE_ROLE_IMG),

		/**
		 * Abstract role.
		 */
		INPUT(GTK_ACCESSIBLE_ROLE_INPUT),

		/**
		 * A visible name or caption for a user interface component.
		 */
		LABEL(GTK_ACCESSIBLE_ROLE_LABEL),

		/**
		 * Abstract role.
		 */
		LANDMARK(GTK_ACCESSIBLE_ROLE_LANDMARK),

		/**
		 * Unused.
		 */
		LEGEND(GTK_ACCESSIBLE_ROLE_LEGEND),

		/**
		 * A clickable link.
		 */
		LINK(GTK_ACCESSIBLE_ROLE_LINK),

		/**
		 * A list of items.
		 */
		LIST(GTK_ACCESSIBLE_ROLE_LIST),

		/**
		 * Unused.
		 */
		LIST_BOX(GTK_ACCESSIBLE_ROLE_LIST_BOX),

		/**
		 * An item in a list.
		 */
		LIST_ITEM(GTK_ACCESSIBLE_ROLE_LIST_ITEM),

		/**
		 * Unused.
		 */
		LOG(GTK_ACCESSIBLE_ROLE_LOG),

		/**
		 * Unused.
		 */
		MAIN(GTK_ACCESSIBLE_ROLE_MAIN),

		/**
		 * Unused.
		 */
		MARQUEE(GTK_ACCESSIBLE_ROLE_MARQUEE),

		/**
		 * Unused.
		 */
		MATH(GTK_ACCESSIBLE_ROLE_MATH),

		/**
		 * A menu.
		 */
		MENU(GTK_ACCESSIBLE_ROLE_MENU),

		/**
		 * A menubar.
		 */
		MENU_BAR(GTK_ACCESSIBLE_ROLE_MENU_BAR),

		/**
		 * An item in a menu.
		 */
		MENU_ITEM(GTK_ACCESSIBLE_ROLE_MENU_ITEM),

		/**
		 * A check item in a menu.
		 */
		MENU_ITEM_CHECKBOX(GTK_ACCESSIBLE_ROLE_MENU_ITEM_CHECKBOX),

		/**
		 * A radio item in a menu.
		 */
		MENU_ITEM_RADIO(GTK_ACCESSIBLE_ROLE_MENU_ITEM_RADIO),

		/**
		 * An element that represents a value within a known range.
		 */
		METER(GTK_ACCESSIBLE_ROLE_METER),

		/**
		 * Unused.
		 */
		NAVIGATION(GTK_ACCESSIBLE_ROLE_NAVIGATION),

		/**
		 * An element that is not represented to accessibility technologies.
		 */
		NONE(GTK_ACCESSIBLE_ROLE_NONE),

		/**
		 * Unused.
		 */
		NOTE(GTK_ACCESSIBLE_ROLE_NOTE),

		/**
		 * Unused.
		 */
		OPTION(GTK_ACCESSIBLE_ROLE_OPTION),

		/**
		 * An element that is not represented to accessibility technologies.
		 */
		PRESENTATION(GTK_ACCESSIBLE_ROLE_PRESENTATION),

		/**
		 * An element that displays the progress status for tasks that take a long time.
		 */
		PROGRESS_BAR(GTK_ACCESSIBLE_ROLE_PROGRESS_BAR),

		/**
		 * A checkable input in a group of radio roles, only one of which can be checked at a time.
		 */
		RADIO(GTK_ACCESSIBLE_ROLE_RADIO),

		/**
		 * Unused.
		 */
		RADIO_GROUP(GTK_ACCESSIBLE_ROLE_RADIO_GROUP),

		/**
		 * Abstract role.
		 */
		RANGE(GTK_ACCESSIBLE_ROLE_RANGE),

		/**
		 * Unused.
		 */
		REGION(GTK_ACCESSIBLE_ROLE_REGION),

		/**
		 * A row in a columned list.
		 */
		ROW(GTK_ACCESSIBLE_ROLE_ROW),

		/**
		 * Unused.
		 */
		ROW_GROUP(GTK_ACCESSIBLE_ROLE_ROW_GROUP),

		/**
		 * Unused.
		 */
		ROW_HEADER(GTK_ACCESSIBLE_ROLE_ROW_HEADER),

		/**
		 * A graphical object that controls the scrolling of content within a viewing area, regardless of whether the content is fully displayed within the viewing area.
		 */
		SCROLLBAR(GTK_ACCESSIBLE_ROLE_SCROLLBAR),

		/**
		 * Unused.
		 */
		SEARCH(GTK_ACCESSIBLE_ROLE_SEARCH),

		/**
		 * A type of textbox intended for specifying search criteria.
		 */
		SEARCH_BOX(GTK_ACCESSIBLE_ROLE_SEARCH_BOX),

		/**
		 * Abstract role.
		 */
		SECTION(GTK_ACCESSIBLE_ROLE_SECTION),

		/**
		 * Abstract role.
		 */
		SECTION_HEAD(GTK_ACCESSIBLE_ROLE_SECTION_HEAD),

		/**
		 * Abstract role.
		 */
		SELECT(GTK_ACCESSIBLE_ROLE_SELECT),

		/**
		 * A divider that separates and distinguishes sections of content or groups of menuitems.
		 */
		SEPARATOR(GTK_ACCESSIBLE_ROLE_SEPARATOR),

		/**
		 * A user input where the user selects a value from within a given range.
		 */
		SLIDER(GTK_ACCESSIBLE_ROLE_SLIDER),

		/**
		 * A form of range that expects the user to select from among discrete choices.
		 */
		SPIN_BUTTON(GTK_ACCESSIBLE_ROLE_SPIN_BUTTON),

		/**
		 * Unused.
		 */
		STATUS(GTK_ACCESSIBLE_ROLE_STATUS),

		/**
		 * Abstract role.
		 */
		STRUCTURE(GTK_ACCESSIBLE_ROLE_STRUCTURE),

		/**
		 * A type of checkbox that represents on/off values, as opposed to checked/unchecked values.
		 */
		SWITCH(GTK_ACCESSIBLE_ROLE_SWITCH),

		/**
		 * An item in a list of tab used for switching pages.
		 */
		TAB(GTK_ACCESSIBLE_ROLE_TAB),

		/**
		 * Unused.
		 */
		TABLE(GTK_ACCESSIBLE_ROLE_TABLE),

		/**
		 * A list of tabs for switching pages.
		 */
		TAB_LIST(GTK_ACCESSIBLE_ROLE_TAB_LIST),

		/**
		 * A page in a notebook or stack.
		 */
		TAB_PANEL(GTK_ACCESSIBLE_ROLE_TAB_PANEL),

		/**
		 * A type of input that allows free-form text as its value.
		 */
		TEXT_BOX(GTK_ACCESSIBLE_ROLE_TEXT_BOX),

		/**
		 * Unused.
		 */
		TIME(GTK_ACCESSIBLE_ROLE_TIME),

		/**
		 * Unused.
		 */
		TIMER(GTK_ACCESSIBLE_ROLE_TIMER),

		/**
		 * Unused.
		 */
		TOOLBAR(GTK_ACCESSIBLE_ROLE_TOOLBAR),

		/**
		 * Unused.
		 */
		TOOLTIP(GTK_ACCESSIBLE_ROLE_TOOLTIP),

		/**
		 * Unused.
		 */
		TREE(GTK_ACCESSIBLE_ROLE_TREE),

		/**
		 * A treeview-like, columned list.
		 */
		TREE_GRID(GTK_ACCESSIBLE_ROLE_TREE_GRID),

		/**
		 * Unused.
		 */
		TREE_ITEM(GTK_ACCESSIBLE_ROLE_TREE_ITEM),

		/**
		 * An interactive component of a graphical user interface. This is the role that GTK uses by default for widgets.
		 */
		WIDGET(GTK_ACCESSIBLE_ROLE_WIDGET),

		/**
		 * An application window.
		 */
		WINDOW(GTK_ACCESSIBLE_ROLE_WINDOW);

		companion object {
			fun valueOf(gtk: GtkAccessibleRole) = values().find { it.gtk == gtk }
		}
	}
}