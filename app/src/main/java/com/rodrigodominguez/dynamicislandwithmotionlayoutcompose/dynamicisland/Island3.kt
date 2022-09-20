package com.rodrigodominguez.dynamicislandwithmotionlayoutcompose.dynamicisland


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.rodrigodominguez.dynamicislandwithmotionlayoutcompose.R

@OptIn(ExperimentalMotionApi::class)
@Preview
@Composable
fun DynamicIslandDemo3() {
    var expanded by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (expanded) 1f else 0f,
        animationSpec = tween(1000)
    )

    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene3)
            .readBytes()
            .decodeToString()
    }

    var heightState by remember { mutableStateOf(48.dp) }
    var widthState by remember { mutableStateOf(226.dp) }
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
            motionScene = MotionScene(
                motionScene
            ),
            progress = progress
        ) {
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
                    .background(Color.Black)
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
                    .layoutId("arrow"),
                painter = painterResource(id = R.drawable.ic_icbaselineflight),
                contentDescription = "",
                tint = Color.Yellow
            )

            val fontSize = motionProperties(id = "nameLabel").value.fontSize("size")
            fontSize.let { fontSizeState = it }
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
                color = Color.fromHex("#C750B7"),
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

