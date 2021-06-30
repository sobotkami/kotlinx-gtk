# List of GTK Widgets that fit the "Display" category

Guide

| Color | Meaning             |
| ----- | ------------------- |
| 🟩    | Complete            |
| 🟨    | Partially complete  |
| 🟥    | Incomplete          |
| 🟫    | N/A                 |

| Kotlin Name         | Functions | Signals   | Documented    | DSL |
| ------------------- | --------- | --------- | ------------- | --- |
| Label         | Wrapped   |           |               |
| AccelLabel    | Wrapped   |           |
| Image         | Wrapped   |           |
| TextView      | Wrapped   |           |
| TreeView      | -         |           |
| IconView      | -         |           |
| ProgressBar   | Wrapped   | N/A       | Partial       |
| LevelBar      | Wrapped   |           |
| Spinner       | Wrapped   |           |
| Menu          | Wrapped   |           |
| Calendar      | -         | -         |
| Separator     | Wrapped   | N/A       |
| DrawingArea   | Wrapped   | N/A       |
| GLArea        | -         |           |
| InfoBar       | Wrapped*  | Wrapped   |

## Legend:
- Functions:
	- Wrapped : GTK Functions are completely encapsulated
- Signals:
	- N/A : Has no signals
- Documented:
	- Partial : Documentation only contains kotlin doc or 
	            only links to gtk docs
	  
### InfoBar Wrapped*
Because passing a vararg is impossible,
some functions have been ignored or emulated
