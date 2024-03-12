package com.example.usta_app.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.usta_app.core.designsystem.R

/**
 * A wrapper around [AsyncImage] which determines the colorFilter based on the theme
 */
@Composable
fun DynamicAsyncImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter = painterResource(R.drawable.core_designsystem_ic_placeholder_default),
) {
//    val iconTint = LocalTintTheme.current.iconTint
//    var isLoading by remember { mutableStateOf(true) }
//    var isError by remember { mutableStateOf(false) }
//    val imageLoader = rememberAsyncImagePainter(
//        model = imageUrl,
//        onState = { state ->
//            isLoading = state is Loading
//            isError = state is Error
//        },
//    )
//    val isLocalInspection = LocalInspectionMode.current
//    Box(
//        modifier = modifier,
//        contentAlignment = Alignment.Center,
//    ) {
//        if (isLoading && !isLocalInspection) {
//            // Display a progress bar while loading
//            CircularProgressIndicator(
//                modifier = Modifier
//                    .align(Alignment.Center)
//                    .size(80.dp),
//                color = MaterialTheme.colorScheme.tertiary,
//            )
//        }
//        Image(
//            contentScale = ContentScale.Crop,
//            painter = if (isError.not() && !isLocalInspection) imageLoader else placeholder,
//            contentDescription = contentDescription,
//            colorFilter = if (iconTint != Unspecified) ColorFilter.tint(iconTint) else null,
//        )
//    }
}
