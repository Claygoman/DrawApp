package com.clayman.firstdrawapp

import android.graphics.Paint.Cap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BottomPanel(
    onClick: (Color) -> Unit,
    onLineWidthChange: (Float) -> Unit,
    onBackClick: () -> Unit,
    onCapClick: (StrokeCap) -> Unit,
    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ColorList{color ->
            onClick(color)
        }
        CustomSlider{lineWidth ->
            onLineWidthChange(lineWidth)
        }
        ButtonPanel({
            onBackClick()
        }) {cap ->
            onCapClick(cap)
        }
        Spacer(modifier = Modifier.height(5.dp))
    }
}

@Composable
fun ColorList(onClick: (Color) -> Unit) {
    val colors = listOf(
        Color.White,
        Color.Black,
        Color.Red,
        Color.Yellow,
        Color.Green,
        Color.Blue,
        Color.Cyan,
    )

    LazyRow(
        modifier = Modifier.padding(10.dp)
    ) {
        items(colors) { color ->
            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable {
                        onClick(color)
                    }
                    .size(40.dp)
                    .background(color, CircleShape)
            )
        }
    }
}

@Composable
fun CustomSlider(onChange: (Float) -> Unit){
    var position by remember {
        mutableStateOf(0.05f)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Line width: ${(position * 100).toInt()}")
        Slider(
            value = position,
            onValueChange = {
                val tempPos = if (it > 0) it else 0.01f
                position = tempPos
                onChange(tempPos * 100)
            }
        )
    }

}

@Composable
fun ButtonPanel(onClick: () -> Unit, onCapClick: (StrokeCap) -> Unit){
    Row(Modifier.fillMaxWidth()){
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White),
                onClick = {
                    onClick()
                }) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White),
                onClick = {
                    onCapClick(StrokeCap.Round)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.cap_1),
                    contentDescription = null
                )
            }
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White),
                onClick = {
                    onCapClick(StrokeCap.Square)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.cap_2),
                    contentDescription = null
                )
            }
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White),
                onClick = {
                    onCapClick(StrokeCap.Butt)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.cap_3),
                    contentDescription = null
                )
            }
        }
    }
}

