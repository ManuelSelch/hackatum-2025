package org.example.project.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import org.example.project.theme.AppColors.LocalCustomBrushes
import kotlin.math.abs
import kotlin.math.roundToInt

// Conversion from OKLCH to sRGB Color
fun hsl(h: Float, s: Float, l: Float): Color {
    val c = (1 - abs(2 * l - 1)) * s
    val x = c * (1 - abs((h / 60) % 2 - 1))
    val m = l - c / 2

    val (r1, g1, b1) = when {
        h < 60 -> Triple(c, x, 0f)
        h < 120 -> Triple(x, c, 0f)
        h < 180 -> Triple(0f, c, x)
        h < 240 -> Triple(0f, x, c)
        h < 300 -> Triple(x, 0f, c)
        else -> Triple(c, 0f, x)
    }

    val r = ((r1 + m) * 255).roundToInt()
    val g = ((g1 + m) * 255).roundToInt()
    val b = ((b1 + m) * 255).roundToInt()

    return Color(r, g, b)
}
object AppColors {
    val bgDark = hsl(218f, 1.0f, 0.92f)
    val bg = hsl(218f, 1.0f, 0.97f)
    val bgLight = hsl(218f, 1.0f, 1.0f)
    val text = hsl(231f, 1.0f, 0.08f)
    val textmuted = hsl(218f, 0.42f, 0.32f)
    val highlight = hsl(218f, 1.0f, 1.0f)
    val border = hsl(218f, 0.32f, 0.54f)
    val bordermuted = hsl(218f, 0.46f, 0.66f)
    val primary = hsl(218f, 0.52f, 0.32f)
    val secondary = hsl(43f, 1.0f, 0.15f)
    val danger = hsl(8f, 0.32f, 0.41f)
    val warning = hsl(51f, 0.46f, 0.3f)
    val success = hsl(150f, 0.37f, 0.33f)
    val info = hsl(217f, 0.34f, 0.43f)

    val DarkbgDark = hsl(231f, 0.98f, 0.03f)
    val Darkbg = hsl(222f, 0.80f, 0.06f)
    val DarkbgLight = hsl(218f, 0.56f, 0.10f)
    val Darktext = hsl(218f, 1.0f, 1.0f)
    val DarktextMuted = hsl(218f, 0.60f, 0.74f)
    val Darkhighlight = hsl(218f, 0.33f, 0.43f)
    val Darkborder = hsl(218f, 0.42f, 0.32f)
    val DarkborderMuted = hsl(218f, 0.60f, 0.21f)
    val Darkprimary = hsl(218f, 0.78f, 0.75f)
    val Darksecondary = hsl(39f, 0.55f, 0.61f)
    val Darkdanger = hsl(9f, 0.42f, 0.65f)
    val Darkwarning = hsl(51f, 0.29f, 0.53f)
    val Darksuccess = hsl(148f, 0.27f, 0.56f)
    val Darkinfo = hsl(217f, 0.46f, 0.66f)

    val DarkColors = darkColorScheme(
        background = Darkbg,
        surface = DarkbgLight,
        primary = Darkprimary,
        secondary = Darksecondary,
        error = Darkdanger,
        onBackground = Darktext,
        onPrimary = DarkbgDark,
        onSecondary = DarkbgLight,
        onError = DarkbgLight,
        tertiary = Darksuccess,
        outline = Darkborder,
        outlineVariant = DarkborderMuted,
        surfaceContainerHighest = Darkhighlight
    )
    val LightColors = lightColorScheme(
        background = bg,
        surface = bgLight,
        primary = primary,
        secondary = secondary,
        error = danger,
        onBackground = text,
        onPrimary = bgDark,
        onSecondary = bgLight,
        onError = bgLight,
        tertiary = success,
        outline = border,
        outlineVariant = bordermuted,
        surfaceContainerHighest = highlight


    )


    data class CustomBrushes(
        val gradient: Brush,
        val gradientHover: Brush,
    )

    val LocalCustomBrushes = staticCompositionLocalOf<CustomBrushes> {
        error("No brushes provided")
    }


    @Composable
    fun AppTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit,
    ) {
        val colors = if (darkTheme) DarkColors else LightColors

        MaterialTheme(
            colorScheme = colors
        ) {
            // NOW CustomBrushes sees the corrected colorScheme
            val brushes = CustomBrushes(
                gradient = Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.surface
                    )
                ),
                gradientHover = Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.surface
                    )
                )
            )

            CompositionLocalProvider(LocalCustomBrushes provides brushes) {
                content()
            }
        }
    }
}
object AppTheme {
    val brushes: AppColors.CustomBrushes
        @Composable get() = LocalCustomBrushes.current
}


