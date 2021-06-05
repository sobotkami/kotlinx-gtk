package nativex.gtk.widgets.container

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.IconSize
import nativex.gtk.SelectionData
import nativex.gtk.bool
import nativex.gtk.common.enums.ToolbarStyle
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 24 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html"></a>
 */
class ToolPalette internal constructor(
	internal val toolPalettePointer: CPointer<GtkToolPalette>
) : Container(toolPalettePointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-new"></a>
	 */
	constructor() : this(gtk_tool_palette_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-exclusive"></a>
	 */
	fun getIsExclusive(group: ToolItemGroup): Boolean =
		gtk_tool_palette_get_exclusive(
			toolPalettePointer,
			group.toolItemGroupPointer
		).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-set-exclusive"></a>
	 */
	fun setIsExclusive(group: ToolItemGroup, isExclusive: Boolean) {
		gtk_tool_palette_set_exclusive(
			toolPalettePointer,
			group.toolItemGroupPointer,
			isExclusive.gtk
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-expand"></a>
	 */
	fun getExpand(group: ToolItemGroup): Boolean =
		gtk_tool_palette_get_expand(
			toolPalettePointer,
			group.toolItemGroupPointer
		).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-set-expand"></a>
	 */
	fun setExpand(group: ToolItemGroup, expand: Boolean) =
		gtk_tool_palette_set_expand(
			toolPalettePointer,
			group.toolItemGroupPointer,
			expand.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-group-position"></a>
	 */
	fun getGroupPosition(group: ToolItemGroup): Int =
		gtk_tool_palette_get_group_position(
			toolPalettePointer,
			group.toolItemGroupPointer
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-set-group-position"></a>
	 */
	fun setGroupPosition(group: ToolItemGroup, position: Int) {
		gtk_tool_palette_set_group_position(
			toolPalettePointer,
			group.toolItemGroupPointer,
			position
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-unset-icon-size"></a>
	 */
	fun unsetIconSize() {
		gtk_tool_palette_unset_icon_size(toolPalettePointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-unset-style"></a>
	 */
	fun unsetStyle() {
		gtk_tool_palette_unset_style(toolPalettePointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-style"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-set-style"></a>
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
				gtk_tool_palette_set_style(toolPalettePointer, value!!.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-icon-size"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-set-icon-size"></a>
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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-add-drag-dest">gtk_tool_palette_add_drag_dest</a>
	 */
	fun addDragDest() {
		TODO("gtk_tool_palette_add_drag_dest")
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-drag-item"></a>
	 */
	fun getDragItem(selectionData: SelectionData): Widget =
		gtk_tool_palette_get_drag_item(toolPalettePointer, selectionData.selectionDataPointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-drag-target-group">gtk_tool_palette_get_drag_target_group</a>
	 */
	fun getDragTargetGroup() {
		TODO("gtk_tool_palette_get_drag_target_group")
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-drag-target-item">gtk_tool_palette_get_drag_target_item</a>
	 */
	fun getDragTargetItem() {
		TODO("gtk_tool_palette_get_drag_target_item")
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-drop-group">gtk_tool_palette_get_drop_group</a>
	 */
	fun paletteGetDropGroup() {
		TODO("gtk_tool_palette_get_drop_group")
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-get-drop-item">gtk_tool_palette_get_drop_item</a>
	 */
	fun getDropItem() {
		TODO("gtk_tool_palette_get_drop_item")
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolPalette.html#gtk-tool-palette-set-drag-source">gtk_tool_palette_set_drag_source</a>
	 */
	fun getDragSource() {
		TODO("gtk_tool_palette_set_drag_source")
	}

	/**
	 * @see <a href=""></a>
	 */
	enum class DragTargets(
		val key: Int,
		internal val gtk: GtkToolPaletteDragTargets
	) {
		ITEMS(0, GTK_TOOL_PALETTE_DRAG_ITEMS),
		GROUPS(1, GTK_TOOL_PALETTE_DRAG_GROUPS);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }


			internal fun valueOf(gtk: GtkToolPaletteDragTargets) =
				values().find { it.gtk == gtk }
		}
	}

}