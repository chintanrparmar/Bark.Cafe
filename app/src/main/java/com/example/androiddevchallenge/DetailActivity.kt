/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.model.DogModel
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.yellow100
import com.example.androiddevchallenge.ui.theme.yellow300
import com.example.androiddevchallenge.ui.theme.yellow400
import com.example.androiddevchallenge.ui.theme.yellow500

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DOG_ID = "dogId"
        fun newIntent(context: Context, dogData: DogModel) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(DOG_ID, dogData)
            }
    }

    private val dogData: DogModel by lazy {
        intent?.getSerializableExtra(DOG_ID) as DogModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                DogDetailContent(dogData)
            }
        }
    }
}

@Composable
fun DogDetailContent(dogData: DogModel) {
    val typography = MaterialTheme.typography
    val context = LocalContext.current
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val imageModifier = Modifier
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.medium)

            Box(Modifier.height(300.dp)) {
                Image(
                    painter = painterResource(dogData.imageId),
                    modifier = imageModifier,
                    contentDescription = "Dog Image",
                    contentScale = ContentScale.Crop
                )
                Spacer(
                    modifier = Modifier.fillMaxSize().background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, yellow100),
                            startY = 500f
                        )
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 22.dp, start = 22.dp, end = 22.dp, top = 12.dp)
            ) {
                Text(
                    text = "Hi, I am ${dogData.title}",
                    style = typography.h4,
                    color = yellow500
                )
                Spacer(Modifier.height(1.dp))
                Text(
                    text = dogData.breed,
                    style = typography.h6,
                    color = yellow400

                )
                Spacer(Modifier.height(5.dp))

                Text(
                    text = dogData.description,
                    style = typography.body2,
                    color = yellow300
                )
                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = {
                        Toast.makeText(
                            context,
                            "Thank You for adopting!",
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = yellow500),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 100.dp)
                        .clip(CircleShape),
                ) {
                    Text(
                        text = "Adopt Me",
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme {
        DogDetailContent(DogModel(1, "Harry", "Dog", R.drawable.harry, "Desc"))
    }
}
