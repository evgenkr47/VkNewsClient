package com.example.vknewsclient.samples

import android.os.Handler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SideEffectTest(number: MyNumber){
    Column {
        LazyColumn {
            repeat(5){
                item {
                    Text(text = "Number: ${number.a}")
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Handler().postDelayed({
            number.a = 5
        }, 300)

        LazyColumn {
            repeat(5){
                item {
                    Text(text = "Number: ${number.a}")
                }
            }
        }
    }

}

data class MyNumber(
    var a: Int = 10
)

