package com.example.mobileappfurniture.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileappfurniture.R

@Composable
fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onCubeClick: () -> Unit = {},
    onAddToCartClick: () -> Unit = {},
) {
    var selectedColorIndex by remember { mutableIntStateOf(0) }
    var quantity by remember { mutableIntStateOf(1) }
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFEAEAEA)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            ProductHeader(
                onBackClick = onBackClick,
                onFavoriteClick = onFavoriteClick,
                onShareClick = onShareClick,
                onCubeClick = onCubeClick
            )

            Product360View()

            Spacer(modifier = Modifier.height(12.dp))

            ProductInfoCard(
                selectedColorIndex = selectedColorIndex,
                onColorSelected = { selectedColorIndex = it },
                quantity = quantity,
                onQuantityChange = { quantity = it },
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { selectedTabIndex = it },
                onAddToCartClick = onAddToCartClick
            )
        }
    }
}

@Composable
private fun ProductHeader(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onCubeClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "09:41",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF104035)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(Color(0xFF104035), shape = RoundedCornerShape(2.dp))
                )
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .background(Color(0xFF104035), shape = CircleShape)
                )
                Box(
                    modifier = Modifier
                        .width(20.dp)
                        .height(10.dp)
                        .border(1.dp, Color(0xFF104035), RoundedCornerShape(2.dp))
                        .padding(1.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF104035), RoundedCornerShape(1.dp))
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HeaderIconButton(
                iconRes = R.drawable.ic_back_chevron,
                contentDescription = "Back",
                onClick = onBackClick
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                HeaderIconButton(
                    iconRes = R.drawable.ic_heart_outline,
                    contentDescription = "Favorite",
                    onClick = onFavoriteClick
                )
                HeaderIconButton(
                    iconRes = R.drawable.ic_send_share,
                    contentDescription = "Share",
                    onClick = onShareClick
                )
                HeaderIconButton(
                    iconRes = R.drawable.ic_cube_3d,
                    contentDescription = "3D View",
                    onClick = onCubeClick
                )
            }
        }
    }
}

@Composable
private fun HeaderIconButton(
    iconRes: Int,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .shadow(2.dp, CircleShape)
            .background(Color.White, CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = contentDescription,
            tint = Color(0xFF104035),
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun Product360View(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.blue_white_chair),
            contentDescription = stringResource(id = R.string.modern_chair_title),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(230.dp)
                .fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                drawOval(
                    color = Color(0xFF333333),
                    style = Stroke(width = 1.5.dp.toPx())
                )
            }

            Box(
                modifier = Modifier
                    .offset(y = 30.dp)
                    .size(28.dp)
                    .shadow(2.dp, CircleShape)
                    .background(Color.White, CircleShape)
                    .border(1.dp, Color(0xFFE0E0E0), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_double_arrow),
                    contentDescription = "Rotate 360",
                    tint = Color(0xFF104035),
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(38.dp))

        Text(
            text = stringResource(id = R.string.degree_360),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF666666)
        )
    }
}

@Composable
private fun ProductInfoCard(
    selectedColorIndex: Int,
    onColorSelected: (Int) -> Unit,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    onAddToCartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.modern_chair_title),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF104035)
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        ) {
                            append(stringResource(id = R.string.modern_chair_price))
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF888888)
                            )
                        ) {
                            append(stringResource(id = R.string.modern_chair_price_cents))
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star_filled),
                    contentDescription = "Rating",
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = stringResource(id = R.string.rating_text),
                    fontSize = 13.sp,
                    color = Color(0xFF8E8E93),
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.color_label),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF104035)
                    )

                    ColorOptionSelector(
                        selectedIndex = selectedColorIndex,
                        onColorSelected = onColorSelected
                    )
                }

                QuantitySelector(
                    quantity = quantity,
                    onQuantityChange = onQuantityChange
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            TabSelector(
                selectedIndex = selectedTabIndex,
                onTabSelected = onTabSelected
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = stringResource(id = R.string.about_product),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF104035)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.product_description))
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF104035)
                        )
                    ) {
                        append(stringResource(id = R.string.read_more))
                    }
                },
                fontSize = 12.sp,
                color = Color(0xFF888888),
                lineHeight = 18.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(24.dp))

            AddToCartButton(onClick = onAddToCartClick)

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .width(134.dp)
                    .height(5.dp)
                    .background(Color(0xFFD0D0D0), CircleShape)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
private fun ColorOptionSelector(
    selectedIndex: Int,
    onColorSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = listOf(
        Color(0xFFE2E2E2),
        Color(0xFF8A8E93),
        Color(0xFFEB3B3B)
    )

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        colors.forEachIndexed { index, color ->
            val isSelected = index == selectedIndex
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .then(
                        if (isSelected) {
                            Modifier
                                .border(1.5.dp, Color(0xFFB0B0B0), CircleShape)
                                .padding(2.dp)
                        } else {
                            Modifier
                        }
                    )
                    .background(color, CircleShape)
                    .clickable { onColorSelected(index) }
            )
        }
    }
}

@Composable
private fun QuantitySelector(
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(32.dp)
            .border(1.dp, Color(0xFF333333), RoundedCornerShape(16.dp))
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "—",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.clickable {
                if (quantity > 1) onQuantityChange(quantity - 1)
            }
        )

        Text(
            text = quantity.toString(),
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "+",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.clickable {
                onQuantityChange(quantity + 1)
            }
        )
    }
}

@Composable
private fun TabSelector(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = listOf(
        stringResource(id = R.string.tab_overview),
        stringResource(id = R.string.tab_material),
        stringResource(id = R.string.tab_review)
    )

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        tabs.forEachIndexed { index, tabText ->
            val isSelected = index == selectedIndex
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (isSelected) Color(0xFF0D0D0D) else Color(0xFFE5E5E5)
                    )
                    .clickable { onTabSelected(index) }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tabText,
                    fontSize = 12.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) Color.White else Color(0xFF333333)
                )
            }
        }
    }
}

@Composable
private fun AddToCartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .shadow(4.dp, RoundedCornerShape(25.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF0F6851),
                        Color(0xFF34D197)
                    )
                ),
                shape = RoundedCornerShape(25.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.add_to_cart_full),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailScreenPreview() {
    MaterialTheme {
        ProductDetailScreen()
    }
}
