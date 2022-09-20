package com.rodrigodominguez.dynamicislandwithmotionlayoutcompose.dynamicisland



import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
fun DynamicIsland() {
    var expanded by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (expanded) 1f else 0f,
        animationSpec = tween(1000)
    )

    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene1)
            .readBytes()
            .decodeToString()
    }

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
                .clip(shape = RoundedCornerShape(percent = 50))
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
                    .clip(shape = RoundedCornerShape(percent = 50))
                    .layoutId("surface")
                    .background(Color.Black)
            )
            val color = Color.fromHex("#76ff03")
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .layoutId("icCall"),
                painter = painterResource(id = R.drawable.ic_icbaselinecall),
                contentDescription = "",
                tint = color
            )

            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .layoutId("icVoice"),
                painter = painterResource(id = R.drawable.ic_icoutlinekeyboardvoice),
                contentDescription = "",
                tint = color
            )

            val fontSize = motionProperties(id = "durationLabel").value.fontSize("size")
            fontSize.let { fontSizeState = it }
            Text(text = "1:22", color = color, fontSize = fontSizeState, modifier = Modifier.layoutId("durationLabel"))
            Text(text = "Rodrigo", color = Color.White,fontSize = 16.sp, modifier = Modifier.layoutId("nameLabel"))
            Text(text = "Dominguez", color = Color.White, fontSize = 20.sp, modifier = Modifier.layoutId("lastNameLabel"))


            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .layoutId("avatar")
                    .size(64.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(2.dp, Color.White, CircleShape)   // add a border (optional)
            )
            CallIcon(modifier = Modifier.layoutId("startCall"), R.drawable.ic_icbaselinecall, Color.Green)
            CallIcon(modifier = Modifier.layoutId("endCall"), R.drawable.ic_icbaselinecallend, Color.Red)

        }

    }


}

private fun Color.Companion.fromHex(colorString: String): Color {
    return Color(android.graphics.Color.parseColor(colorString))
}


@Composable
fun CallIcon(modifier: Modifier = Modifier, icon : Int, backgroundColor: Color) {
    Box(
        modifier = modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(backgroundColor),
        Alignment.Center
    ){
        Icon(
            modifier = Modifier
                .size(28.dp)
                .layoutId("icCall"),
            painter = painterResource(id = icon),
            contentDescription = "",
            tint = Color.White
        )
    }
}
