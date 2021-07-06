package nativex.gtk.widgets.container
import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gdk.dragndrop.DragAction
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gtk.IconSize
import nativex.gtk.Orientable
import nativex.gtk.Scrollable
import nativex.gtk.SelectionData
import nativex.gtk.common.enums.ToolbarStyle
import nativex.gtk.dragndrop.DestDefaults
import nativex.gtk.selections.TargetEntry
import nativex.gtk.selections.TargetEntry.Companion.wrap
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.ToolItemGroup.Companion.wrap
import nativex.gtk.widgets.container.bin.toolitem.ToolItem
import nativex.gtk.widgets.container.bin.toolitem.ToolItem.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 24 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html">GtkToolPalette</a>
 */
class ToolPalette(
	val toolPalettePointer: CPointer<GtkToolPalette>
) : Container(toolPalettePointer.reinterpret()), Scrollable, Orientable {
	override val scrollablePointer: CPointer<GtkScrollable> by lazy {
		toolPalettePointer.reinterpret()
	}

	override val orientablePointer: CPointer<GtkOrientable> by lazy {
		toolPalettePointer.reinterpret()
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-new">
	 *     gtk_tool_palette_new</a>
	 */
	constructor() : this(gtk_tool_palette_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-exclusive">
	 *     gtk_tool_palette_get_exclusive</a>
	 */
	fun getIsExclusive(group: ToolItemGroup): Boolean =
		gtk_tool_palette_get_exclusive(
			toolPalettePointer,
			group.toolItemGroupPointer
		).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-set-exclusive">
	 *     gtk_tool_palette_set_exclusive</a>
	 */
	fun setIsExclusive(group: ToolItemGroup, isExclusive: Boolean) {
		gtk_tool_palette_set_exclusive(
			toolPalettePointer,
			group.toolItemGroupPointer,
			isExclusive.gtk
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-expand">
	 *     gtk_tool_palette_get_expand</a>
	 */
	fun getExpand(group: ToolItemGroup): Boolean =
		gtk_tool_palette_get_expand(
			toolPalettePointer,
			group.toolItemGroupPointer
		).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-set-expand">
	 *     gtk_tool_palette_set_expand</a>
	 */
	fun setExpand(group: ToolItemGroup, expand: Boolean) =
		gtk_tool_palette_set_expand(
			toolPalettePointer,
			group.toolItemGroupPointer,
			expand.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-group-position">
	 *     gtk_tool_palette_get_group_position</a>
	 */
	fun getGroupPosition(group: ToolItemGroup): Int =
		gtk_tool_palette_get_group_position(
			toolPalettePointer,
			group.toolItemGroupPointer
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-set-group-position">
	 *     gtk_tool_palette_set_group_position</a>
	 */
	fun setGroupPosition(group: ToolItemGroup, position: Int) {
		gtk_tool_palette_set_group_position(
			toolPalettePointer,
			group.toolItemGroupPointer,
			position
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-unset-icon-size">
	 *     gtk_tool_palette_unset_icon_size</a>
	 */
	fun unsetIconSize() {
		gtk_tool_palette_unset_icon_size(toolPalettePointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-unset-style">
	 *     gtk_tool_palette_unset_style</a>
	 */
	fun unsetStyle() {
		gtk_tool_palette_unset_style(toolPalettePointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-style">
	 *     gtk_tool_palette_get_style</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-set-style">
	 *     gtk_tool_palette_set_style</a>
	 */
	var style: ToolbarStyle?
		get() = ToolbarStyle.valueOf(
			gtk_tool_palette_get_style(
				toolPalettePointer
			)
		)
		set(value) =
			if (value == null)
				unsetStyle()
			else
				gtk_tool_palette_set_style(toolPalettePointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-icon-size">
	 *     gtk_tool_palette_get_icon_size</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-set-icon-size">
	 *     gtk_tool_palette_set_icon_size</a>
	 */
	var iconSize: IconSize?
		get() = IconSize.valueOf(
			gtk_tool_palette_get_icon_size(toolPalettePointer)
		)
		set(value) =
			if (value == null)
				unsetIconSize()
			else
				gtk_tool_palette_set_icon_size(
					toolPalettePointer,
					value.gtk
				)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-add-drag-dest">
	 *     gtk_tool_palette_add_drag_dest</a>
	 */
	fun addDragDest(widget: Widget, flags: DestDefaults, targets: DragTargets, actions: DragAction) {
		gtk_tool_palette_add_drag_dest(toolPalettePointer, widget.widgetPointer, flags.gtk, targets.gtk, actions.gdk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-drag-item">
	 *     gtk_tool_palette_get_drag_item</a>
	 */
	fun getDragItem(selectionData: SelectionData): Widget =
		gtk_tool_palette_get_drag_item(toolPalettePointer, selectionData.selectionDataPointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-drag-target-group">
	 *     gtk_tool_palette_get_drag_target_group</a>
	 */
	val dragTargetGroup: TargetEntry
		get() = gtk_tool_palette_get_drag_target_group()!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-drag-target-item">
	 *     gtk_tool_palette_get_drag_target_item</a>
	 */
	val dragTargetItem
		get() = gtk_tool_palette_get_drag_target_item().wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-drop-group">
	 *     gtk_tool_palette_get_drop_group</a>
	 */
	fun getPaletteGetDropGroup(x: Int, y: Int): ToolItemGroup? =
		gtk_tool_palette_get_drop_group(toolPalettePointer, x, y).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-drop-item">
	 *     gtk_tool_palette_get_drop_item</a>
	 */
	fun getDropItem(x: Int, y: Int): ToolItem? =
		gtk_tool_palette_get_drop_item(toolPalettePointer, x, y).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-set-drag-source">
	 *     gtk_tool_palette_set_drag_source</a>
	 */
	fun getDragSource(targets: DragTargets) {
		gtk_tool_palette_set_drag_source(toolPalettePointer, targets.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#GtkToolPaletteDragTargets">
	 *     GtkToolPaletteDragTargets</a>
	 */
	enum class DragTargets(
		val gtk: GtkToolPaletteDragTargets
	) {
		/** Support drag of items. */
		ITEMS(GTK_TOOL_PALETTE_DRAG_ITEMS),

		/** Support drag of groups. */
		GROUPS(GTK_TOOL_PALETTE_DRAG_GROUPS);

		companion object {
			fun valueOf(gtk: GtkToolPaletteDragTargets) =
				values().find { it.gtk == gtk }
		}
	}

}