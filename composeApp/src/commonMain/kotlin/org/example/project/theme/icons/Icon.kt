package org.example.project.theme.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp


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