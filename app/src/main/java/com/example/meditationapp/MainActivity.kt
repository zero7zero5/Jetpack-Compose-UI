package com.example.meditationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meditationapp.ui.theme.MeditationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationAppTheme {
                HomeScreen()
            }
        }
    }
}
data class FeatureItem(var title:String,var color:Color,var icon:ImageVector)

@Composable
fun HomeScreen(){
    var featureItems = arrayOf(
        FeatureItem("Sleep Meditation", Color(0xFF54e1b7),Icons.Default.CheckCircle),
        FeatureItem("Tips For Sleeping", Color(0xFF8e97fe),Icons.Default.PlayArrow),
        FeatureItem("Night Island", Color(0xFFf8747f),Icons.Default.AddCircle),
        FeatureItem("Calming Sound", Color(0xFFefbd28),Icons.Default.CheckCircle),
        FeatureItem("Sleep Meditation", Color(0xFFfaa27c),Icons.Default.Favorite))
    var genres = arrayOf("Sweet Sleep","Insomnia","Relax","Stress","Happy")

    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF05164c))){
        Column {
            Greeting()
            ChipSection(genres)
            CurrentGenre()
            FeatureSection(featureItems)
        }
        BottomNavigation()

    }
}

@Composable
fun Greeting(name:String = "Alex!"){
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)) {
        Column {
            Text(text = "Hi $name", color = Color.White, style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(vertical = 5.dp) )
            Text(text = "We wish you have a good day", color = Color(0xFF737b99) )
        }
        Icon(imageVector = Icons.Default.Search, contentDescription = "search", tint = Color.White, modifier = Modifier.size(35.dp))
    }
}

@Composable
fun ChipSection(genre:Array<String>){
    var currentGenre by remember {
        mutableStateOf(0)
    }
    LazyRow(modifier = Modifier.padding(vertical = 10.dp)) {
        items(genre.size){
            Box(modifier = Modifier
                .clickable { currentGenre = it }
                .padding(horizontal = 10.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(if (currentGenre == it) Color(0xFF4f5df4) else Color(0xFF576894))
                .padding(15.dp)){
                Text(text =genre[it], color = Color.White)
            }
        }
    }
}

@Composable
fun CurrentGenre(){
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)
        .clip(RoundedCornerShape(15.dp))
        .background(Color(0xFFf8747e))
        .padding(20.dp)){
            Column {
                Text(text = "Daily Thought", color = Color.White, style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 5.dp))
                Text(text = "Meditation   3-10 minutes", color = Color.White, style = MaterialTheme.typography.bodyMedium)
            }
            Box(contentAlignment = Alignment.Center, modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
                .background(Color(0xFF4f5cf5))
                .clickable { println("Play Button Clicked") }
                ){
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play Arrow", tint = Color.White, modifier = Modifier.size(25.dp))
            }



    }
}

@Composable
fun FeatureSection(item:Array<FeatureItem>){
    LazyVerticalGrid(modifier = Modifier.padding(bottom = 75.dp),contentPadding = PaddingValues(10.dp), columns = GridCells.Fixed(2), verticalArrangement = Arrangement.spacedBy(20.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            items(item.size){
                Column(modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(item[it].color)
                    .padding(vertical = 20.dp, horizontal = 10.dp)){
                    Text(minLines = 2, text = item[it].title, style = MaterialTheme.typography.titleLarge, color = Color.White)
                    Row(modifier = Modifier
                        .padding(top = 30.dp, bottom = 5.dp)
                        .fillMaxWidth()
                        .padding(5.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                        Icon(imageVector = item[it].icon, contentDescription = "sample", tint = Color.White, modifier = Modifier.size(30.dp))
                        Box(modifier = Modifier
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .background(Color(0xFF4f5df4))
                            .padding(vertical = 8.dp, horizontal = 15.dp)){
                            Text(text = "Start", color = Color.White, style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }
        
    }
}

data class NavItem(var name:String,var icon:ImageVector)
val navItems = arrayOf<NavItem>(
    NavItem(icon=Icons.Default.Home, name = "Home"),
    NavItem(icon=Icons.Default.Favorite, name = "Favorite"),
    NavItem(icon=Icons.Default.Star, name = "Wishlist"),
    NavItem(icon=Icons.Default.DateRange, name = "Schedule"),
    NavItem(icon=Icons.Default.Person, name = "Account"))

@Composable
fun BottomNavigation(){
    var currentPage by remember {
        mutableStateOf(0)
    }
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .background(Color(0xFF05164c))
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        navItems.forEachIndexed {
            index, navItem -> NavItems(item = navItem, handleClick = { currentPage=index},isActive = currentPage==index)

        }
    }
}

@Composable
fun NavItems(item:NavItem,handleClick:()->Unit,isActive:Boolean=true){
    val backgroundColor = if(isActive) Color(0XFF4f5df1) else Color(0xFF05164c)
    val iconColor = if(isActive) Color.White else Color.Gray
    Column(modifier = Modifier.clickable { handleClick() }, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .padding(10.dp)

            ){
            Icon(imageVector = item.icon, contentDescription ="Home", tint = iconColor, modifier = Modifier.size(30.dp) )
        }
        Text(text = item.name, color = iconColor, style = MaterialTheme.typography.bodyMedium)
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MeditationAppTheme {
        HomeScreen()
    }
}