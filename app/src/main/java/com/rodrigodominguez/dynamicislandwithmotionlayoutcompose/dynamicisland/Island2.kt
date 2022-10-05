package com.rodrigodominguez.dynamicislandwithmotionlayoutcompose.dynamicisland


import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.rodrigodominguez.dynamicislandwithmotionlayoutcompose.R

@OptIn(ExperimentalMotionApi::class, ExperimentalUnitApi::class)
@Preview
@Composable
fun DynamicIslandDemo2() {
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
            motionScene = getMotionSceneForDemo2(),
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
                painter = painterResource(id = R.drawable.ic_ciblyft),
                contentDescription = "",
                tint = color
            )

            val fontSize = motionProperties(id = "durationLabel").value.float("size")
            fontSize.let { fontSizeState = TextUnit(fontSize, TextUnitType.Sp) }
            Text(
                text = "3 min",
                color = color,
                fontSize = 18.sp,
                modifier = Modifier.layoutId("durationLabel")
            )
            Text(
                text = "Arrives in 3 min",
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier.layoutId("nameLabel")
            )
            Text(
                text = "Alex • 5.0 ⭐",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.layoutId("lastNameLabel")
            )
            Text(
                text = "Ford Focus • 772GTY8G",
                color = Color.LightGray,
                fontSize = 14.sp,
                modifier = Modifier.layoutId("descriptionLabel")
            )


            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .layoutId("avatar")
                    .size(44.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(1.dp, Color.White, CircleShape)   // add a border (optional)
            )
            Image(
                painter = painterResource(R.drawable.img),
                contentDescription = "avatar",
                contentScale = ContentScale.Inside,            // crop the image if it's not a square
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .layoutId("endCall")
                    .width(96.dp)
            )
            Image(
                painter = painterResource(R.drawable.lyft_icon_2x),
                contentDescription = "avatar",
                contentScale = ContentScale.Inside,            // crop the image if it's not a square
                modifier = Modifier
                    .layoutId("startCall")
                    .width(104.dp)
            )

        }

    }


}

@SuppressLint("Range")
@OptIn(ExperimentalMotionApi::class)
@Composable
fun getMotionSceneForDemo2(): MotionScene {
    return MotionScene {
        val start1 = constraintSet {
            val surface = createRefFor("surface")
            val avatar = createRefFor("avatar")
            val nameLabel = createRefFor("nameLabel")
            val lastNameLabel = createRefFor("lastNameLabel")
            val descriptionLabel = createRefFor("descriptionLabel")
            val icCall = createRefFor("icCall")
            val icVoice = createRefFor("icVoice")
            val durationLabel = createRefFor("durationLabel")
            val endCall = createRefFor("endCall")
            val startCall = createRefFor("startCall")

            constrain(surface) {
                customFloat("height", 48f)
                customFloat("width", 196f)
                customFloat("corner", 50f)
                customColor("color", Color.fromHex("#000000"))
            }
            constrain(avatar) {
                start.linkTo(parent.start, 8.dp)
                bottom.linkTo(parent.bottom)
                alpha = 0f
                scaleY = 0.5f
                scaleX = 0.5f
            }
            constrain(nameLabel) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(icCall.bottom, 16.dp)
                bottom.linkTo(lastNameLabel.top)
                alpha = 0f
                scaleY = 0.5f
                scaleX = 0.5f
            }
            constrain(lastNameLabel) {
                start.linkTo(avatar.end, 4.dp)
                top.linkTo(nameLabel.bottom)
                bottom.linkTo(parent.bottom, 24.dp)
                alpha = 0f
                scaleY = 0.5f
                scaleX = 0.5f
            }
            constrain(descriptionLabel) {
                start.linkTo(avatar.end, 4.dp)
                top.linkTo(lastNameLabel.bottom)
                bottom.linkTo(parent.bottom)
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
            constrain(endCall) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end, 16.dp)
                alpha = 0f
                scaleY = 0f
                scaleX = 0f
            }
            constrain(startCall) {
                start.linkTo(endCall.start)
                end.linkTo(endCall.end)
                top.linkTo(endCall.bottom)
                bottom.linkTo(endCall.bottom)
                alpha = 0f
                scaleY = 0f
                scaleX = 0f
            }
        }
        val end1 = constraintSet {
            val surface = createRefFor("surface")
            val avatar = createRefFor("avatar")
            val nameLabel = createRefFor("nameLabel")
            val lastNameLabel = createRefFor("lastNameLabel")
            val descriptionLabel = createRefFor("descriptionLabel")
            val icCall = createRefFor("icCall")
            val icVoice = createRefFor("icVoice")
            val durationLabel = createRefFor("durationLabel")
            val endCall = createRefFor("endCall")
            val startCall = createRefFor("startCall")

            constrain(surface) {
                customFloat("height", 168f)
                customFloat("width", 340f)
                customFloat("corner", 10f)
                customColor("color", Color.fromHex("#2C5B4B"))
            }
            constrain(avatar) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(nameLabel.bottom)
                bottom.linkTo(parent.bottom)
                alpha = 1f
                scaleY = 1f
                scaleX = 1f
            }
            constrain(nameLabel) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(icCall.bottom, 16.dp)
                alpha = 1f
                scaleY = 1f
                scaleX = 1f
            }
            constrain(lastNameLabel) {
                start.linkTo(avatar.end, 4.dp)
                top.linkTo(avatar.top)
                alpha = 1f
                scaleY = 1f
                scaleX = 1f
            }
            constrain(descriptionLabel) {
                start.linkTo(avatar.end, 4.dp)
                bottom.linkTo(avatar.bottom)
                alpha = 1f
            }
            constrain(icCall) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top, 16.dp)
            }
            constrain(icVoice) {
                end.linkTo(parent.end, 16.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                alpha = 0f
            }
            constrain(durationLabel) {
                top.linkTo(parent.top)
                end.linkTo(parent.end, 16.dp)
                bottom.linkTo(parent.bottom)
                alpha = 0f
            }
            constrain(endCall) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end, 16.dp)
                alpha = 1f
                scaleY = 1f
                scaleX = 1f
            }
            constrain(startCall) {
                start.linkTo(endCall.start)
                end.linkTo(endCall.end)
                top.linkTo(endCall.bottom)
                bottom.linkTo(endCall.bottom)
                alpha = 1f
                scaleY = 1f
                scaleX = 1f
            }
        }
        transition("default", start1, end1) {}
    }
}


private fun Color.Companion.fromHex(colorString: String): Color {
    return Color(android.graphics.Color.parseColor(colorString))
}
