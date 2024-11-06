@Composable
fun ApiKeyScreen(onApiKeySaved: (String) -> Unit) {
    var apiKey by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Ingrese su API Key")
        TextField(
            value = apiKey,
            onValueChange = { apiKey = it },
            label = { Text("API Key") }
        )
        Button(onClick = {
            // Guardar la API Key localmente
            onApiKeySaved(apiKey)
        }) {
            Text("Guardar")
        }
    }
}