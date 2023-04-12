package com.vitorthemyth.composecrashcourse.utils

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.isUnspecified

@Composable
fun AutoResizedText(
    text : String,
    style : TextStyle = MaterialTheme.typography.body1,
    modifier : Modifier = Modifier,
    color : Color = style.color
) {
    var resizedText by remember{
        mutableStateOf(style)
    }

    var shouldDraw by remember{
        mutableStateOf(false)
    }

    var defaultFontSize = MaterialTheme.typography.body1.fontSize

    Text(
        text = text,
        color = color,
        modifier = modifier.drawWithContent {
               if (shouldDraw){
                   drawContent()
               }
        },
        softWrap = false,
        style = resizedText,
        onTextLayout = {result ->
            if (result.didOverflowWidth){
                if (style.fontSize.isUnspecified){
                    resizedText = resizedText.copy(
                        fontSize = defaultFontSize
                    )
                }
                resizedText = resizedText.copy(
                    fontSize = resizedText.fontSize * 0.95
                )
            }else{
                shouldDraw = true
            }
        }
    )
}