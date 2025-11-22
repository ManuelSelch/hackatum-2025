import androidx.compose.material3.*

import androidx.compose.ui.graphics.Color
import kotlin.math.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme

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





    object AppColorsDark {
        val bgDark = hsl(231f, 0.98f, 0.03f)
        val bg = hsl(222f, 0.80f, 0.06f)
        val bgLight = hsl(218f, 0.56f, 0.10f)
        val text = hsl(218f, 1.0f, 1.0f)
        val textMuted = hsl(218f, 0.60f, 0.74f)
        val highlight = hsl(218f, 0.33f, 0.43f)
        val border = hsl(218f, 0.42f, 0.32f)
        val borderMuted = hsl(218f, 0.60f, 0.21f)
        val primary = hsl(218f, 0.78f, 0.75f)
        val secondary = hsl(39f, 0.55f, 0.61f)
        val danger = hsl(9f, 0.42f, 0.65f)
        val warning = hsl(51f, 0.29f, 0.53f)
        val success = hsl(148f, 0.27f, 0.56f)
        val info = hsl(217f, 0.46f, 0.66f)
    }


    val LightColors = lightColorScheme(
    background = AppColors.bg,
    surface = AppColors.bgLight,
    primary = AppColors.primary,
    secondary = AppColors.secondary,
    error = AppColors.danger,
    onBackground = AppColors.text,
    onPrimary = AppColors.bgLight,
    onSecondary = AppColors.bgLight,
    onError = AppColors.bgLight
)

val DarkColors = darkColorScheme(
    background = AppColorsDark.bg,
    surface = AppColorsDark.bgLight,
    primary = AppColorsDark.primary,
    secondary = AppColorsDark.secondary,
    error = AppColorsDark.danger,
    onBackground = AppColorsDark.text,
    onPrimary = AppColorsDark.bgLight,
    onSecondary = AppColorsDark.bgLight,
    onError = AppColorsDark.bgLight
)



@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
}

