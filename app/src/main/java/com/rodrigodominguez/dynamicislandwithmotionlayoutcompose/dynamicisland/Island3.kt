package com.rodrigodominguez.dynamicislandwithmotionlayoutcompose.dynamicisland


import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.rodrigodominguez.dynamicislandwithmotionlayoutcompose.R

@OptIn(ExperimentalMotionApi::class, ExperimentalUnitApi::class)
@Preview
@Composable
fun DynamicIslandDemo3() {
    var expanded by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (expanded) 1f else 0f,
        animationSpec = tween(1000)
    )
    var colorBackgroundState by remember { mutableStateOf(Color.Black) }
    var heightState by remember { mutableStateOf(48.dp) }
    var widthState by remember { mutableStateOf(196.dp) }
    var percentCornerState by remember {
        mutableStateOf(50)
    }
    var fontSizeState by remember {
        mutableStateOf(18.sp)
    }
    var colorTextState by remember {
        mutableStateOf(Color.White)
    }
    Box(
        modifier = Modifier
            .padding(16.dp), Alignment.TopCenter
    ) {
        MotionLayout(
            modifier = Modifier
                .width(widthState)
                .height(heightState)
                .clip(shape = RoundedCornerShape(percent = percentCornerState))
                .clickable { expanded = !expanded },
            motionScene = getMotionSceneForDemo3(),
            progress = progress
        ) {
            val colorBackground = motionProperties(id = "surface").value.color("color")
            colorBackground.let { colorBackgroundState = it }

            val newHeight = motionProperties(id = "surface").value.float("height")
            newHeight.let { heightState = it.dp }

            val newWidth = motionProperties(id = "surface").value.float("width")
            newWidth.let { widthState = it.dp }

            val percentCorner = motionProperties(id = "surface").value.int("corner")
            percentCorner.let { percentCornerState = it }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(percent = percentCorner))
                    .layoutId("surface")
                    .background(colorBackgroundState)
            )
            val color = Color.fromHex("#EA0B8C")
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .layoutId("icCall"),
                painter = painterResource(id = R.drawable.ic_icbaselineflightland),
                contentDescription = "",
                tint = Color.Green
            )

            Icon(
                modifier = Modifier
                    .width(68.dp)
                    .layoutId("arrow")
                    .rotate(90f),
                painter = painterResource(id = R.drawable.ic_icbaselineflight),
                contentDescription = "",
                tint = Color.Yellow
            )

            val fontSize = motionProperties(id = "nameLabel").value.float("size")
            fontSize.let { fontSizeState = TextUnit(fontSize, TextUnitType.Sp) }
            val colorText = motionProperties(id = "nameLabel").value.color("color")
            colorText.let { colorTextState = it }
            Text(
                text = "FL228",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.layoutId("code")
            )
            Text(
                text = "in 48m",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.layoutId("durationLabel")
            )
            Text(
                text = "JFK",
                color = colorTextState,
                fontSize = fontSizeState,
                modifier = Modifier.layoutId("nameLabel")
            )
            Text(
                text = "SFO",
                color = Color.fromHex("#B6D8B0"),
                fontSize = fontSizeState,
                modifier = Modifier.layoutId("nameStartLabel")
            )
            Text(
                text = "On Time",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.layoutId("lastNameLabel")
            )
            Text(
                text = "Landing ",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.layoutId("descriptionLabel")
            )
            CallIconFlight(
                modifier = Modifier.layoutId("bagInfo"),
                R.drawable.ic_mdibagsuitcase,
                Color.fromHex("#c1a263")
            )
        }

    }


}

private fun Color.Companion.fromHex(colorString: String): Color {
    return Color(android.graphics.Color.parseColor(colorString))
}


@Composable
fun CallIconFlight(modifier: Modifier = Modifier, icon: Int, backgroundColor: Color) {
    Row(
        modifier = modifier
            .width(60.dp)
            .height(24.dp)
            .padding(end = 8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(22.dp)
                .layoutId("icCall"),
            painter = painterResource(id = icon),
            contentDescription = "",
            tint = Color.Black
        )
        Text(text = "2")
    }
}

@SuppressLint("Range")
@OptIn(ExperimentalMotionApi::class)
@Composable
fun getMotionSceneForDemo3(): MotionScene {
    return MotionScene {
        val start1 = constraintSet {
            val surface = createRefFor("surface")
            val avatar = createRefFor("avatar")
            val nameLabel = createRefFor("nameLabel")
            val nameStartLabel = createRefFor("nameStartLabel")
            val lastNameLabel = createRefFor("lastNameLabel")
            val descriptionLabel = createRefFor("descriptionLabel")
            val icCall = createRefFor("icCall")
            val code = createRefFor("code")
            val icVoice = createRefFor("icVoice")
            val durationLabel = createRefFor("durationLabel")
            val bagInfo = createRefFor("bagInfo")
            val arrow = createRefFor("arrow")

            constrain(surface) {
                customFloat("height", 48f)
                customFloat("width", 196f)
                customFloat("corner", 50f)
                customColor("color", Color.fromHex("#000000"))
            }
            constrain(avatar) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                alpha = 0f
                scaleY = 0.5f
                scaleX = 0.5f
            }
            constrain(nameLabel) {
                start.linkTo(icCall.end)
                top.linkTo(icCall.top)
                bottom.linkTo(icCall.bottom)
                alpha = 1f
                scaleY = 0.5f
                scaleX = 0.5f
                customFloat("size", 34f)
                customColor("color", Color.fromHex("#ffffff"))
            }
            constrain(nameStartLabel) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(parent.top, 24.dp)
                bottom.linkTo(parent.bottom, 24.dp)
                alpha = 0f
            }
            constrain(lastNameLabel) {
                start.linkTo(parent.start, 16.dp)
                bottom.linkTo(descriptionLabel.top, 4.dp)
                alpha = 0f
                scaleY = 0.5f
                scaleX = 0.5f
            }
            constrain(descriptionLabel) {
                start.linkTo(parent.start, 4.dp)
                bottom.linkTo(parent.bottom, 8.dp)
                alpha = 0f
            }
            constrain(icCall) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            constrain(icVoice) {
                end.linkTo(parent.end, 16.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            constrain(durationLabel) {
                end.linkTo(parent.end, 16.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            constrain(code) {
                top.linkTo(parent.top, 16.dp)
                start.linkTo(parent.start, 16.dp)
                alpha = 0f
            }
            constrain(bagInfo) {
                bottom.linkTo(parent.bottom, 24.dp)
                end.linkTo(parent.end, 16.dp)
                alpha = 0f
                scaleY = 0f
                scaleX = 0f
            }
            constrain(arrow) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                alpha = 0f
                scaleY = 0f
                scaleX = 0f
            }
        }
        val end1 = constraintSet {
            val surface = createRefFor("surface")
            val avatar = createRefFor("avatar")
            val nameLabel = createRefFor("nameLabel")
            val nameStartLabel = createRefFor("nameStartLabel")
            val lastNameLabel = createRefFor("lastNameLabel")
            val descriptionLabel = createRefFor("descriptionLabel")
            val icCall = createRefFor("icCall")
            val code = createRefFor("code")
            val icVoice = createRefFor("icVoice")
            val durationLabel = createRefFor("durationLabel")
            val bagInfo = createRefFor("bagInfo")
            val arrow = createRefFor("arrow")

            constrain(surface) {
                customFloat("height", 188f)
                customFloat("width", 340f)
                customFloat("corner", 10f)
                customColor("color", Color.fromHex("#800000"))
            }
            constrain(avatar) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                alpha = 1f
                scaleY = 1f
                scaleX = 1f
            }
            constrain(nameLabel) {
                end.linkTo(parent.end, 24.dp)
                top.linkTo(parent.top, 24.dp)
                bottom.linkTo(parent.bottom, 24.dp)
                alpha = 1f
                scaleY = 1f
                scaleX = 1f
                customFloat("size", 48f)
                customColor("color", Color.fromHex("#50C7C1"))
            }
            constrain(lastNameLabel) {
                start.linkTo(parent.start, 16.dp)
                bottom.linkTo(descriptionLabel.top, 4.dp)
                alpha = 1f
                scaleY = 1f
                scaleX = 1f
            }
            constrain(nameStartLabel) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(parent.top, 24.dp)
                bottom.linkTo(parent.bottom, 24.dp)
                alpha = 1f
            }
            constrain(descriptionLabel) {
                start.linkTo(parent.start, 16.dp)
                bottom.linkTo(parent.bottom, 16.dp)
                alpha = 1f
            }
            constrain(icCall) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top, 16.dp)
                alpha = 0f
                scaleX = 0f
                scaleY = 0f
            }
            constrain(icVoice) {
                end.linkTo(parent.end, 16.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                alpha = 0f
            }
            constrain(durationLabel) {
                top.linkTo(descriptionLabel.top)
                bottom.linkTo(descriptionLabel.bottom)
                start.linkTo(descriptionLabel.end)
            }
            constrain(code) {
                top.linkTo(parent.top, 16.dp)
                start.linkTo(parent.start, 16.dp)
                alpha = 1f
            }
            constrain(bagInfo) {
                bottom.linkTo(parent.bottom, 24.dp)
                end.linkTo(parent.end, 16.dp)
                alpha = 1f
                scaleY = 1f
                scaleX = 1f
            }
            constrain(arrow) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                alpha = 1f
                scaleY = 1f
                scaleX = 1f
            }
        }
        transition("default", start1, end1) {}
    }
}


