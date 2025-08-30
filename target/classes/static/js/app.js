// App.js

import Header from './header.js';
import Footer from './footer.js';
import { cargarPeliculas } from './peliculas.js';
import { getUpcoming, getPlaying, getTrendingDayMovies, getTopMovies } from './api.js';
import { loadFamous } from './FamousPeople.js';

document.addEventListener('DOMContentLoaded', async () => {

  // Carga el header y footer
  const header = new Header("header", "header.html");
  const footer = new Footer("footer", "footer.html");
  await header.load();
  await footer.load();
  
  // Próximos estrenos (hero banner)
  const upcomingData = await getUpcoming();
  cargarPeliculas(
    upcomingData.results,
    'proximo-container',
    'btn-upcoming-prev',
    'btn-upcoming-next',
    1,
    196
  );

  // Cartelera
  const playingData = await getPlaying();
  cargarPeliculas(
    playingData.results,
    'cartelera-container',
    'cartelera-prev',
    'cartelera-next',
    6,
    196
  );

  // Cargar famosos populares
    loadFamous();
  });


  // Trending día
  const trendingData = await getTrendingDayMovies();
  cargarPeliculas(
    trendingData.results,
    'trending-container',
    'trending-prev',
    'trending-next',
    6,
    196
  );

  // Películas TOP
  const topMoviesData = await getTopMovies();
  cargarPeliculas(
    topMoviesData.results,
    'top-movies-container',
    'top-movies-prev',
    'top-movies-next',
    6,
    196
  );

  // Abrir detalle de película
  // Listener para obtener id de la película seleccionada
  document.body.addEventListener('click', e => {
    const item = e.target.closest('.pelicula-item');
    if (item) {
      const movieId = item.getAttribute('data-id');
      window.location.href = `film.html?id=${movieId}`;
    }
  });