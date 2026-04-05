package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


val avatarUrl: String? = null
val userBio: String? = "Desarrollador Android en formación."
val followerCount: String? = "1500"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserProfileScreen()
                }
            }
        }
    }
}

@Composable
fun UserProfileScreen() {



    val bioDisplay = userBio ?: "Biografía no disponible"

    // 2. FILTRO DE SEGURIDAD (Reto Extra)
    // Intentamos convertir a Int; si falla (null o texto) asignamos 0
    val parsedCount = followerCount?.toIntOrNull() ?: 0
    // Si el número es negativo, lo forzamos a 0
    val safeFollowers = if (parsedCount < 0) 0 else parsedCount


    val statusColor = when {
        safeFollowers == 0 -> Color.Gray
        safeFollowers > 1000 -> Color(0xFFFFD700) // Dorado
        safeFollowers > 100 -> Color.Blue
        else -> Color.DarkGray
    }

    // --- DISEÑO DE LA INTERFAZ ---
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icono con color dinámico según nulidad [cite: 93-97]
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = if (avatarUrl == null) Color.LightGray else Color.Green
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Biografía segura
        Text(
            text = bioDisplay,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Seguidores con el filtro de seguridad aplicado
        Text(
            text = "Seguidores: $safeFollowers",
            color = statusColor,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}