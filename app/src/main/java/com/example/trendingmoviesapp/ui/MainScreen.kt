@Composable
fun MainScreen(apiKey: String) {
    val viewModel: MovieViewModel = viewModel()
    val movies by viewModel.movies.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.getTrendingMovies(apiKey)
    }

    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(movies) { movie ->
            MovieItem(movie) {
                // Navegar a la pantalla de detalles
            }
        }
    }
}