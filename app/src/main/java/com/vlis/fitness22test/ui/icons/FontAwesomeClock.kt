package com.vlis.fitness22test.ui.icons/*
Font Awesome Free License
-------------------------

Font Awesome Free is free, open source, and GPL friendly. You can use it for
commercial projects, open source projects, or really almost whatever you want.
Full Font Awesome Free license: https://fontawesome.com/license/free.

# Icons: CC BY 4.0 License (https://creativecommons.org/licenses/by/4.0/)
In the Font Awesome Free download, the CC BY 4.0 license applies to all icons
packaged as SVG and JS file types.

# Fonts: SIL OFL 1.1 License (https://scripts.sil.org/OFL)
In the Font Awesome Free download, the SIL OFL license applies to all icons
packaged as web and desktop font files.

# Code: MIT License (https://opensource.org/licenses/MIT)
In the Font Awesome Free download, the MIT license applies to all non-font and
non-icon files.

# Attribution
Attribution is required by MIT, SIL OFL, and CC BY licenses. Downloaded Font
Awesome Free files already contain embedded comments with sufficient
attribution, so you shouldn't need to do anything additional when using these
files normally.

We've kept attribution comments terse, so we ask that you do not actively work
to remove them from files, especially code. They're a great way for folks to
learn about Font Awesome.

# Brand Icons
All brand icons are trademarks of their respective owners. The use of these
trademarks does not indicate endorsement of the trademark holder by Font
Awesome, nor vice versa. **Please do not use brand logos for any purpose except
to represent the company, product, or service to which they refer.**

*/
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val FontAwesomeClock: ImageVector
    get() {
        if (_FontAwesomeClock != null) return _FontAwesomeClock!!
        
        _FontAwesomeClock = ImageVector.Builder(
            name = "clock",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(256f, 8f)
                curveTo(119f, 8f, 8f, 119f, 8f, 256f)
                reflectiveCurveTo(119f, 504f, 256f, 504f)
                reflectiveCurveTo(504f, 393f, 504f, 256f)
                reflectiveCurveTo(393f, 8f, 256f, 8f)
                close()
                moveToRelative(92.49f, 313f)
                horizontalLineToRelative(0f)
                lineToRelative(-20f, 25f)
                arcToRelative(16f, 16f, 0f, false, true, -22.49f, 2.5f)
                horizontalLineToRelative(0f)
                lineToRelative(-67f, -49.72f)
                arcToRelative(40f, 40f, 0f, false, true, -15f, -31.23f)
                verticalLineTo(112f)
                arcToRelative(16f, 16f, 0f, false, true, 16f, -16f)
                horizontalLineToRelative(32f)
                arcToRelative(16f, 16f, 0f, false, true, 16f, 16f)
                verticalLineTo(256f)
                lineToRelative(58f, 42.5f)
                arcTo(16f, 16f, 0f, false, true, 348.49f, 321f)
                close()
            }
        }.build()
        
        return _FontAwesomeClock!!
    }

private var _FontAwesomeClock: ImageVector? = null

