package com.example.ownerlaundry.component.view

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ownerlaundry.*
import com.example.ownerlaundry.navigation.Screens

@Composable
fun ViewMenuItem(
    id_menu: String,
    index: String,
    title_Menu: String,
    is_packet: Boolean,
    is_dryer: Boolean,
    navController: NavController,
    onClick: (String) -> Unit
) {
    val context = LocalContext.current

    var expandedState by remember { mutableStateOf(false) }

    Card(shape = RoundedCornerShape(20.dp),
        modifier = Modifier.clickable {
            expandedState = !expandedState
            onClick.invoke(index)
            MENU_ID = id_menu
            MENU_TITLE = title_Menu
            MENU_PACKET = is_packet
            MENU_DRYER = is_dryer
            MENU_EDIT = true
            PAGE_SCREEN = Screens.AddEditMenuPrice.route
            navController.navigate(route = Screens.AddEditMenuPrice.route)
//            Toast.makeText(context, "Menu Edit $MENU_EDIT", Toast.LENGTH_SHORT).show()
        }
    ) {
        Column(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)
            .fillMaxWidth()) {

            ConstraintLayout(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {

                val (NumberTitle, TitleMenu, ClassMachine) = createRefs()

                Text(
                    color = MaterialTheme.colors.primary,
                    text = title_Menu,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(NumberTitle)
                        {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = if(is_packet) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
                    modifier = Modifier.wrapContentHeight()
                    .constrainAs(TitleMenu)
                    {
                        top.linkTo(NumberTitle.bottom, 8.dp)
                        start.linkTo(parent.start, 4.dp)
                    }
                ) {
                    Text(
                        color = if(is_packet) MaterialTheme.colors.surface else Color.LightGray,
                        text = if(is_packet) "Packet Menu" else "Not Packet Menu",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    )
                }
                Text(
                    text = if(is_dryer) "Menu Dryer" else "Not Menu Dryer",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = if(is_dryer) Color.Red else MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(start = 8.dp)
                        .constrainAs(ClassMachine)
                        {
                            top.linkTo(TitleMenu.bottom, 8.dp)
                            start.linkTo(parent.start, 4.dp)
                        }
                )
            }
        }
    }
}