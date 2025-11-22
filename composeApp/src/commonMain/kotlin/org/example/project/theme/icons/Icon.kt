package org.example.project.theme.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp


val CircleMoney: ImageVector
    get() {
        if (_CurrencyDollar != null) return _CurrencyDollar!!

        _CurrencyDollar = ImageVector.Builder(
            name = "CurrencyDollar",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 6f)
                verticalLineTo(18f)
                moveTo(9f, 15.1818f)
                lineTo(9.87887f, 15.841f)
                curveTo(11.0504f, 16.7197f, 12.9498f, 16.7197f, 14.1214f, 15.841f)
                curveTo(15.2929f, 14.9623f, 15.2929f, 13.5377f, 14.1214f, 12.659f)
                curveTo(13.5355f, 12.2196f, 12.7677f, 12f, 11.9999f, 12f)
                curveTo(11.275f, 12f, 10.5502f, 11.7804f, 9.99709f, 11.341f)
                curveTo(8.891f, 10.4623f, 8.891f, 9.03772f, 9.9971f, 8.15904f)
                curveTo(11.1032f, 7.28036f, 12.8965f, 7.28036f, 14.0026f, 8.15904f)
                lineTo(14.4175f, 8.48863f)
                moveTo(21f, 12f)
                curveTo(21f, 16.9706f, 16.9706f, 21f, 12f, 21f)
                curveTo(7.02944f, 21f, 3f, 16.9706f, 3f, 12f)
                curveTo(3f, 7.02944f, 7.02944f, 3f, 12f, 3f)
                curveTo(16.9706f, 3f, 21f, 7.02944f, 21f, 12f)
                close()
            }
        }.build()

        return _CurrencyDollar!!
    }

private var _CurrencyDollar: ImageVector? = null


val Drink: ImageVector
    get() {
        if (_CupHot != null) return _CupHot!!

        _CupHot = ImageVector.Builder(
            name = "CupHot",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(0.5f, 6f)
                arcToRelative(0.5f, 0.5f, 0f, false, false, -0.488f, 0.608f)
                lineToRelative(1.652f, 7.434f)
                arcTo(2.5f, 2.5f, 0f, false, false, 4.104f, 16f)
                horizontalLineToRelative(5.792f)
                arcToRelative(2.5f, 2.5f, 0f, false, false, 2.44f, -1.958f)
                lineToRelative(0.131f, -0.59f)
                arcToRelative(3f, 3f, 0f, false, false, 1.3f, -5.854f)
                lineToRelative(0.221f, -0.99f)
                arcTo(0.5f, 0.5f, 0f, false, false, 13.5f, 6f)
                close()
                moveTo(13f, 12.5f)
                arcToRelative(2f, 2f, 0f, false, true, -0.316f, -0.025f)
                lineToRelative(0.867f, -3.898f)
                arcTo(2.001f, 2.001f, 0f, false, true, 13f, 12.5f)
                moveTo(2.64f, 13.825f)
                lineTo(1.123f, 7f)
                horizontalLineToRelative(11.754f)
                lineToRelative(-1.517f, 6.825f)
                arcTo(1.5f, 1.5f, 0f, false, true, 9.896f, 15f)
                horizontalLineTo(4.104f)
                arcToRelative(1.5f, 1.5f, 0f, false, true, -1.464f, -1.175f)
            }
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveToRelative(4.4f, 0.8f)
                lineToRelative(-0.003f, 0.004f)
                lineToRelative(-0.014f, 0.019f)
                arcToRelative(4f, 4f, 0f, false, false, -0.204f, 0.31f)
                arcToRelative(2f, 2f, 0f, false, false, -0.141f, 0.267f)
                curveToRelative(-0.026f, 0.06f, -0.034f, 0.092f, -0.037f, 0.103f)
                verticalLineToRelative(0.004f)
                arcToRelative(0.6f, 0.6f, 0f, false, false, 0.091f, 0.248f)
                curveToRelative(0.075f, 0.133f, 0.178f, 0.272f, 0.308f, 0.445f)
                lineToRelative(0.01f, 0.012f)
                curveToRelative(0.118f, 0.158f, 0.26f, 0.347f, 0.37f, 0.543f)
                curveToRelative(0.112f, 0.2f, 0.22f, 0.455f, 0.22f, 0.745f)
                curveToRelative(0f, 0.188f, -0.065f, 0.368f, -0.119f, 0.494f)
                arcToRelative(3f, 3f, 0f, false, true, -0.202f, 0.388f)
                arcToRelative(5f, 5f, 0f, false, true, -0.253f, 0.382f)
                lineToRelative(-0.018f, 0.025f)
                lineToRelative(-0.005f, 0.008f)
                lineToRelative(-0.002f, 0.002f)
                arcTo(0.5f, 0.5f, 0f, false, true, 3.6f, 4.2f)
                lineToRelative(0.003f, -0.004f)
                lineToRelative(0.014f, -0.019f)
                arcToRelative(4f, 4f, 0f, false, false, 0.204f, -0.31f)
                arcToRelative(2f, 2f, 0f, false, false, 0.141f, -0.267f)
                curveToRelative(0.026f, -0.06f, 0.034f, -0.092f, 0.037f, -0.103f)
                arcToRelative(0.6f, 0.6f, 0f, false, false, -0.09f, -0.252f)
                arcTo(4f, 4f, 0f, false, false, 3.6f, 2.8f)
                lineToRelative(-0.01f, -0.012f)
                arcToRelative(5f, 5f, 0f, false, true, -0.37f, -0.543f)
                arcTo(1.53f, 1.53f, 0f, false, true, 3f, 1.5f)
                curveToRelative(0f, -0.188f, 0.065f, -0.368f, 0.119f, -0.494f)
                curveToRelative(0.059f, -0.138f, 0.134f, -0.274f, 0.202f, -0.388f)
                arcToRelative(6f, 6f, 0f, false, true, 0.253f, -0.382f)
                lineToRelative(0.025f, -0.035f)
                arcTo(0.5f, 0.5f, 0f, false, true, 4.4f, 0.8f)
                moveToRelative(3f, 0f)
                lineToRelative(-0.003f, 0.004f)
                lineToRelative(-0.014f, 0.019f)
                arcToRelative(4f, 4f, 0f, false, false, -0.204f, 0.31f)
                arcToRelative(2f, 2f, 0f, false, false, -0.141f, 0.267f)
                curveToRelative(-0.026f, 0.06f, -0.034f, 0.092f, -0.037f, 0.103f)
                verticalLineToRelative(0.004f)
                arcToRelative(0.6f, 0.6f, 0f, false, false, 0.091f, 0.248f)
                curveToRelative(0.075f, 0.133f, 0.178f, 0.272f, 0.308f, 0.445f)
                lineToRelative(0.01f, 0.012f)
                curveToRelative(0.118f, 0.158f, 0.26f, 0.347f, 0.37f, 0.543f)
                curveToRelative(0.112f, 0.2f, 0.22f, 0.455f, 0.22f, 0.745f)
                curveToRelative(0f, 0.188f, -0.065f, 0.368f, -0.119f, 0.494f)
                arcToRelative(3f, 3f, 0f, false, true, -0.202f, 0.388f)
                arcToRelative(5f, 5f, 0f, false, true, -0.253f, 0.382f)
                lineToRelative(-0.018f, 0.025f)
                lineToRelative(-0.005f, 0.008f)
                lineToRelative(-0.002f, 0.002f)
                arcTo(0.5f, 0.5f, 0f, false, true, 6.6f, 4.2f)
                lineToRelative(0.003f, -0.004f)
                lineToRelative(0.014f, -0.019f)
                arcToRelative(4f, 4f, 0f, false, false, 0.204f, -0.31f)
                arcToRelative(2f, 2f, 0f, false, false, 0.141f, -0.267f)
                curveToRelative(0.026f, -0.06f, 0.034f, -0.092f, 0.037f, -0.103f)
                arcToRelative(0.6f, 0.6f, 0f, false, false, -0.09f, -0.252f)
                arcTo(4f, 4f, 0f, false, false, 6.6f, 2.8f)
                lineToRelative(-0.01f, -0.012f)
                arcToRelative(5f, 5f, 0f, false, true, -0.37f, -0.543f)
                arcTo(1.53f, 1.53f, 0f, false, true, 6f, 1.5f)
                curveToRelative(0f, -0.188f, 0.065f, -0.368f, 0.119f, -0.494f)
                curveToRelative(0.059f, -0.138f, 0.134f, -0.274f, 0.202f, -0.388f)
                arcToRelative(6f, 6f, 0f, false, true, 0.253f, -0.382f)
                lineToRelative(0.025f, -0.035f)
                arcTo(0.5f, 0.5f, 0f, false, true, 7.4f, 0.8f)
                moveToRelative(3f, 0f)
                lineToRelative(-0.003f, 0.004f)
                lineToRelative(-0.014f, 0.019f)
                arcToRelative(4f, 4f, 0f, false, false, -0.204f, 0.31f)
                arcToRelative(2f, 2f, 0f, false, false, -0.141f, 0.267f)
                curveToRelative(-0.026f, 0.06f, -0.034f, 0.092f, -0.037f, 0.103f)
                verticalLineToRelative(0.004f)
                arcToRelative(0.6f, 0.6f, 0f, false, false, 0.091f, 0.248f)
                curveToRelative(0.075f, 0.133f, 0.178f, 0.272f, 0.308f, 0.445f)
                lineToRelative(0.01f, 0.012f)
                curveToRelative(0.118f, 0.158f, 0.26f, 0.347f, 0.37f, 0.543f)
                curveToRelative(0.112f, 0.2f, 0.22f, 0.455f, 0.22f, 0.745f)
                curveToRelative(0f, 0.188f, -0.065f, 0.368f, -0.119f, 0.494f)
                arcToRelative(3f, 3f, 0f, false, true, -0.202f, 0.388f)
                arcToRelative(5f, 5f, 0f, false, true, -0.252f, 0.382f)
                lineToRelative(-0.019f, 0.025f)
                lineToRelative(-0.005f, 0.008f)
                lineToRelative(-0.002f, 0.002f)
                arcTo(0.5f, 0.5f, 0f, false, true, 9.6f, 4.2f)
                lineToRelative(0.003f, -0.004f)
                lineToRelative(0.014f, -0.019f)
                arcToRelative(4f, 4f, 0f, false, false, 0.204f, -0.31f)
                arcToRelative(2f, 2f, 0f, false, false, 0.141f, -0.267f)
                curveToRelative(0.026f, -0.06f, 0.034f, -0.092f, 0.037f, -0.103f)
                arcToRelative(0.6f, 0.6f, 0f, false, false, -0.09f, -0.252f)
                arcTo(4f, 4f, 0f, false, false, 9.6f, 2.8f)
                lineToRelative(-0.01f, -0.012f)
                arcToRelative(5f, 5f, 0f, false, true, -0.37f, -0.543f)
                arcTo(1.53f, 1.53f, 0f, false, true, 9f, 1.5f)
                curveToRelative(0f, -0.188f, 0.065f, -0.368f, 0.119f, -0.494f)
                curveToRelative(0.059f, -0.138f, 0.134f, -0.274f, 0.202f, -0.388f)
                arcToRelative(6f, 6f, 0f, false, true, 0.253f, -0.382f)
                lineToRelative(0.025f, -0.035f)
                arcTo(0.5f, 0.5f, 0f, false, true, 10.4f, 0.8f)
            }
        }.build()

        return _CupHot!!
    }

private var _CupHot: ImageVector? = null



val Misc: ImageVector
    get() {
        if (_Package_2 != null) return _Package_2!!

        _Package_2 = ImageVector.Builder(
            name = "Package_2",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(440f, 777f)
                verticalLineToRelative(-274f)
                lineTo(200f, 364f)
                verticalLineToRelative(274f)
                close()
                moveToRelative(80f, 0f)
                lineToRelative(240f, -139f)
                verticalLineToRelative(-274f)
                lineTo(520f, 503f)
                close()
                moveToRelative(-80f, 92f)
                lineTo(160f, 708f)
                quadToRelative(-19f, -11f, -29.5f, -29f)
                reflectiveQuadTo(120f, 639f)
                verticalLineToRelative(-318f)
                quadToRelative(0f, -22f, 10.5f, -40f)
                reflectiveQuadToRelative(29.5f, -29f)
                lineToRelative(280f, -161f)
                quadToRelative(19f, -11f, 40f, -11f)
                reflectiveQuadToRelative(40f, 11f)
                lineToRelative(280f, 161f)
                quadToRelative(19f, 11f, 29.5f, 29f)
                reflectiveQuadToRelative(10.5f, 40f)
                verticalLineToRelative(318f)
                quadToRelative(0f, 22f, -10.5f, 40f)
                reflectiveQuadTo(800f, 708f)
                lineTo(520f, 869f)
                quadToRelative(-19f, 11f, -40f, 11f)
                reflectiveQuadToRelative(-40f, -11f)
                moveToRelative(200f, -528f)
                lineToRelative(77f, -44f)
                lineToRelative(-237f, -137f)
                lineToRelative(-78f, 45f)
                close()
                moveToRelative(-160f, 93f)
                lineToRelative(78f, -45f)
                lineToRelative(-237f, -137f)
                lineToRelative(-78f, 45f)
                close()
            }
        }.build()

        return _Package_2!!
    }

private var _Package_2: ImageVector? = null



val Food: ImageVector
    get() {
        if (_Food_bank != null) return _Food_bank!!

        _Food_bank = ImageVector.Builder(
            name = "Food_bank",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(400f, 720f)
                horizontalLineToRelative(40f)
                verticalLineToRelative(-160f)
                quadToRelative(25f, 0f, 42.5f, -17.5f)
                reflectiveQuadTo(500f, 500f)
                verticalLineToRelative(-120f)
                horizontalLineToRelative(-40f)
                verticalLineToRelative(120f)
                horizontalLineToRelative(-20f)
                verticalLineToRelative(-120f)
                horizontalLineToRelative(-40f)
                verticalLineToRelative(120f)
                horizontalLineToRelative(-20f)
                verticalLineToRelative(-120f)
                horizontalLineToRelative(-40f)
                verticalLineToRelative(120f)
                quadToRelative(0f, 25f, 17.5f, 42.5f)
                reflectiveQuadTo(400f, 560f)
                close()
                moveToRelative(160f, 0f)
                horizontalLineToRelative(40f)
                verticalLineToRelative(-340f)
                quadToRelative(-33f, 0f, -56.5f, 23.5f)
                reflectiveQuadTo(520f, 460f)
                verticalLineToRelative(120f)
                horizontalLineToRelative(40f)
                close()
                moveTo(160f, 840f)
                verticalLineToRelative(-480f)
                lineToRelative(320f, -240f)
                lineToRelative(320f, 240f)
                verticalLineToRelative(480f)
                close()
                moveToRelative(80f, -80f)
                horizontalLineToRelative(480f)
                verticalLineToRelative(-360f)
                lineTo(480f, 220f)
                lineTo(240f, 400f)
                close()
                moveToRelative(240f, -270f)
            }
        }.build()

        return _Food_bank!!
    }

private var _Food_bank: ImageVector? = null


private var _Fastfood: ImageVector? = null



val Add: ImageVector
    get() {
        if (_Add != null) return _Add!!

        _Add = ImageVector.Builder(
            name = "Add",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(440f, 520f)
                horizontalLineTo(200f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(-240f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(240f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(80f)
                horizontalLineTo(520f)
                verticalLineToRelative(240f)
                horizontalLineToRelative(-80f)
                close()
            }
        }.build()

        return _Add!!
    }

private var _Add: ImageVector? = null


val Person: ImageVector
    get() {
        if (_Person != null) return _Person!!

        _Person = ImageVector.Builder(
            name = "Person",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(480f, 480f)
                quadToRelative(-66f, 0f, -113f, -47f)
                reflectiveQuadToRelative(-47f, -113f)
                reflectiveQuadToRelative(47f, -113f)
                reflectiveQuadToRelative(113f, -47f)
                reflectiveQuadToRelative(113f, 47f)
                reflectiveQuadToRelative(47f, 113f)
                reflectiveQuadToRelative(-47f, 113f)
                reflectiveQuadToRelative(-113f, 47f)
                moveTo(160f, 800f)
                verticalLineToRelative(-112f)
                quadToRelative(0f, -34f, 17.5f, -62.5f)
                reflectiveQuadTo(224f, 582f)
                quadToRelative(62f, -31f, 126f, -46.5f)
                reflectiveQuadTo(480f, 520f)
                reflectiveQuadToRelative(130f, 15.5f)
                reflectiveQuadTo(736f, 582f)
                quadToRelative(29f, 15f, 46.5f, 43.5f)
                reflectiveQuadTo(800f, 688f)
                verticalLineToRelative(112f)
                close()
                moveToRelative(80f, -80f)
                horizontalLineToRelative(480f)
                verticalLineToRelative(-32f)
                quadToRelative(0f, -11f, -5.5f, -20f)
                reflectiveQuadTo(700f, 654f)
                quadToRelative(-54f, -27f, -109f, -40.5f)
                reflectiveQuadTo(480f, 600f)
                reflectiveQuadToRelative(-111f, 13.5f)
                reflectiveQuadTo(260f, 654f)
                quadToRelative(-9f, 5f, -14.5f, 14f)
                reflectiveQuadToRelative(-5.5f, 20f)
                close()
                moveToRelative(240f, -320f)
                quadToRelative(33f, 0f, 56.5f, -23.5f)
                reflectiveQuadTo(560f, 320f)
                reflectiveQuadToRelative(-23.5f, -56.5f)
                reflectiveQuadTo(480f, 240f)
                reflectiveQuadToRelative(-56.5f, 23.5f)
                reflectiveQuadTo(400f, 320f)
                reflectiveQuadToRelative(23.5f, 56.5f)
                reflectiveQuadTo(480f, 400f)
                moveToRelative(0f, 320f)
            }
        }.build()

        return _Person!!
    }

private var _Person: ImageVector? = null


val Arrow_drop_down: ImageVector
    get() {
        if (_Arrow_drop_down != null) return _Arrow_drop_down!!

        _Arrow_drop_down = ImageVector.Builder(
            name = "Arrow_drop_down",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(480f, 600f)
                lineTo(280f, 400f)
                horizontalLineToRelative(400f)
                close()
            }
        }.build()

        return _Arrow_drop_down!!
    }

private var _Arrow_drop_down: ImageVector? = null



val Money: ImageVector
    get() {
        if (_Money != null) return _Money!!

        _Money = ImageVector.Builder(
            name = "Attach_money",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(441f, 840f)
                verticalLineToRelative(-86f)
                quadToRelative(-53f, -12f, -91.5f, -46f)
                reflectiveQuadTo(293f, 612f)
                lineToRelative(74f, -30f)
                quadToRelative(15f, 48f, 44.5f, 73f)
                reflectiveQuadToRelative(77.5f, 25f)
                quadToRelative(41f, 0f, 69.5f, -18.5f)
                reflectiveQuadTo(587f, 604f)
                quadToRelative(0f, -35f, -22f, -55.5f)
                reflectiveQuadTo(463f, 502f)
                quadToRelative(-86f, -27f, -118f, -64.5f)
                reflectiveQuadTo(313f, 346f)
                quadToRelative(0f, -65f, 42f, -101f)
                reflectiveQuadToRelative(86f, -41f)
                verticalLineToRelative(-84f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(84f)
                quadToRelative(50f, 8f, 82.5f, 36.5f)
                reflectiveQuadTo(651f, 310f)
                lineToRelative(-74f, 32f)
                quadToRelative(-12f, -32f, -34f, -48f)
                reflectiveQuadToRelative(-60f, -16f)
                quadToRelative(-44f, 0f, -67f, 19.5f)
                reflectiveQuadTo(393f, 346f)
                quadToRelative(0f, 33f, 30f, 52f)
                reflectiveQuadToRelative(104f, 40f)
                quadToRelative(69f, 20f, 104.5f, 63.5f)
                reflectiveQuadTo(667f, 602f)
                quadToRelative(0f, 71f, -42f, 108f)
                reflectiveQuadToRelative(-104f, 46f)
                verticalLineToRelative(84f)
                close()
            }
        }.build()

        return _Money!!
    }

private var _Money: ImageVector? = null
val Grocery: ImageVector
    get() {
        if (_Grocery != null) return _Grocery!!

        _Grocery = ImageVector.Builder(
            name = "Grocery",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(640f, 880f)
                quadToRelative(-100f, 0f, -170f, -70f)
                reflectiveQuadToRelative(-70f, -170f)
                reflectiveQuadToRelative(70f, -170f)
                reflectiveQuadToRelative(170f, -70f)
                reflectiveQuadToRelative(170f, 70f)
                reflectiveQuadToRelative(70f, 170f)
                reflectiveQuadToRelative(-70f, 170f)
                reflectiveQuadToRelative(-170f, 70f)
                moveToRelative(0f, -80f)
                quadToRelative(66f, 0f, 113f, -47f)
                reflectiveQuadToRelative(47f, -113f)
                reflectiveQuadToRelative(-47f, -113f)
                reflectiveQuadToRelative(-113f, -47f)
                reflectiveQuadToRelative(-113f, 47f)
                reflectiveQuadToRelative(-47f, 113f)
                reflectiveQuadToRelative(47f, 113f)
                reflectiveQuadToRelative(113f, 47f)
                moveToRelative(-480f, 0f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(80f, 720f)
                verticalLineToRelative(-304f)
                quadToRelative(0f, -8f, 1.5f, -16f)
                reflectiveQuadToRelative(4.5f, -16f)
                lineToRelative(80f, -184f)
                horizontalLineToRelative(-6f)
                quadToRelative(-17f, 0f, -28.5f, -11.5f)
                reflectiveQuadTo(120f, 160f)
                verticalLineToRelative(-40f)
                quadToRelative(0f, -17f, 11.5f, -28.5f)
                reflectiveQuadTo(160f, 80f)
                horizontalLineToRelative(280f)
                quadToRelative(17f, 0f, 28.5f, 11.5f)
                reflectiveQuadTo(480f, 120f)
                verticalLineToRelative(40f)
                quadToRelative(0f, 17f, -11.5f, 28.5f)
                reflectiveQuadTo(440f, 200f)
                horizontalLineToRelative(-6f)
                lineToRelative(66f, 152f)
                quadToRelative(-19f, 10f, -36f, 21f)
                reflectiveQuadToRelative(-32f, 25f)
                lineToRelative(-84f, -198f)
                horizontalLineToRelative(-96f)
                lineToRelative(-92f, 216f)
                verticalLineToRelative(304f)
                horizontalLineToRelative(170f)
                quadToRelative(5f, 21f, 13.5f, 41.5f)
                reflectiveQuadTo(364f, 800f)
                close()
                moveToRelative(480f, -440f)
                quadToRelative(-42f, 0f, -71f, -29f)
                reflectiveQuadToRelative(-29f, -71f)
                reflectiveQuadToRelative(29f, -71f)
                reflectiveQuadToRelative(71f, -29f)
                close()
                quadToRelative(0f, -42f, 29f, -71f)
                reflectiveQuadToRelative(71f, -29f)
                reflectiveQuadToRelative(71f, 29f)
                reflectiveQuadToRelative(29f, 71f)
                close()
            }
        }.build()

        return _Grocery!!
    }

val Receipt_long: ImageVector
    get() {
        if (_Receipt_long != null) return _Receipt_long!!

        _Receipt_long = ImageVector.Builder(
            name = "Receipt_long",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(240f, 880f)
                quadToRelative(-50f, 0f, -85f, -35f)
                reflectiveQuadToRelative(-35f, -85f)
                verticalLineToRelative(-120f)
                horizontalLineToRelative(120f)
                verticalLineToRelative(-560f)
                lineToRelative(60f, 60f)
                lineToRelative(60f, -60f)
                lineToRelative(60f, 60f)
                lineToRelative(60f, -60f)
                lineToRelative(60f, 60f)
                lineToRelative(60f, -60f)
                lineToRelative(60f, 60f)
                lineToRelative(60f, -60f)
                lineToRelative(60f, 60f)
                lineToRelative(60f, -60f)
                verticalLineToRelative(680f)
                quadToRelative(0f, 50f, -35f, 85f)
                reflectiveQuadToRelative(-85f, 35f)
                close()
                moveToRelative(480f, -80f)
                quadToRelative(17f, 0f, 28.5f, -11.5f)
                reflectiveQuadTo(760f, 760f)
                verticalLineToRelative(-560f)
                horizontalLineTo(320f)
                verticalLineToRelative(440f)
                horizontalLineToRelative(360f)
                verticalLineToRelative(120f)
                quadToRelative(0f, 17f, 11.5f, 28.5f)
                reflectiveQuadTo(720f, 800f)
                moveTo(360f, 360f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(80f)
                close()
                moveToRelative(0f, 120f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(80f)
                close()
                moveToRelative(320f, -120f)
                quadToRelative(-17f, 0f, -28.5f, -11.5f)
                reflectiveQuadTo(640f, 320f)
                reflectiveQuadToRelative(11.5f, -28.5f)
                reflectiveQuadTo(680f, 280f)
                reflectiveQuadToRelative(28.5f, 11.5f)
                reflectiveQuadTo(720f, 320f)
                reflectiveQuadToRelative(-11.5f, 28.5f)
                reflectiveQuadTo(680f, 360f)
                moveToRelative(0f, 120f)
                quadToRelative(-17f, 0f, -28.5f, -11.5f)
                reflectiveQuadTo(640f, 440f)
                reflectiveQuadToRelative(11.5f, -28.5f)
                reflectiveQuadTo(680f, 400f)
                reflectiveQuadToRelative(28.5f, 11.5f)
                reflectiveQuadTo(720f, 440f)
                reflectiveQuadToRelative(-11.5f, 28.5f)
                reflectiveQuadTo(680f, 480f)
                moveTo(240f, 800f)
                horizontalLineToRelative(360f)
                verticalLineToRelative(-80f)
                horizontalLineTo(200f)
                verticalLineToRelative(40f)
                quadToRelative(0f, 17f, 11.5f, 28.5f)
                reflectiveQuadTo(240f, 800f)
                moveToRelative(-40f, 0f)
                verticalLineToRelative(-80f)
                close()
            }
        }.build()

        return _Receipt_long!!
    }

private var _Receipt_long: ImageVector? = null
private var _Grocery: ImageVector? = null