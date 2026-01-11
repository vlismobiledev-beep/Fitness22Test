package com.vlis.fitness22test.ui.icons/*
The MIT License (MIT)

Copyright (c) 2019-2024 The Bootstrap Authors

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

*/
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val BootstrapFire: ImageVector
    get() {
        if (_BootstrapFire != null) return _BootstrapFire!!
        
        _BootstrapFire = ImageVector.Builder(
            name = "fire",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(8f, 16f)
                curveToRelative(3.314f, 0f, 6f, -2f, 6f, -5.5f)
                curveToRelative(0f, -1.5f, -0.5f, -4f, -2.5f, -6f)
                curveToRelative(0.25f, 1.5f, -1.25f, 2f, -1.25f, 2f)
                curveTo(11f, 4f, 9f, 0.5f, 6f, 0f)
                curveToRelative(0.357f, 2f, 0.5f, 4f, -2f, 6f)
                curveToRelative(-1.25f, 1f, -2f, 2.729f, -2f, 4.5f)
                curveTo(2f, 14f, 4.686f, 16f, 8f, 16f)
                moveToRelative(0f, -1f)
                curveToRelative(-1.657f, 0f, -3f, -1f, -3f, -2.75f)
                curveToRelative(0f, -0.75f, 0.25f, -2f, 1.25f, -3f)
                curveTo(6.125f, 10f, 7f, 10.5f, 7f, 10.5f)
                curveToRelative(-0.375f, -1.25f, 0.5f, -3.25f, 2f, -3.5f)
                curveToRelative(-0.179f, 1f, -0.25f, 2f, 1f, 3f)
                curveToRelative(0.625f, 0.5f, 1f, 1.364f, 1f, 2.25f)
                curveTo(11f, 14f, 9.657f, 15f, 8f, 15f)
            }
        }.build()
        
        return _BootstrapFire!!
    }

private var _BootstrapFire: ImageVector? = null

