// api.js

let BASE_URL;

if (window.location.hostname.includes("localhost") || window.location.hostname === "127.0.0.1") {
    BASE_URL = "http://localhost:8080";
} else {
    BASE_URL = "https://peliculasonlinehd-production.up.railway.app";
}

const UPCOMING_URL = `${BASE_URL}/peliculas/upcoming`;
const PLAYING_URL = `${BASE_URL}/peliculas/playing`;
const TRENDING_DAY_URL = `${BASE_URL}/peliculas/trendingDayMovies`;
const TOP_MOVIES_URL = `${BASE_URL}/peliculas/topMovies`;
const MOVIE_DETAILS_URL = `${BASE_URL}/peliculas/details`;
const MOVIE_CAST_URL = `${BASE_URL}/famous/credits`;
const MOVIE_TRAILER_URL = `${BASE_URL}/trailer`;




/** Obtiene los próximos estrenos */
export async function getUpcoming() {
  const res = await fetch(UPCOMING_URL);
  return res.json();
}

/** Obtiene películas en cartelera */
export async function getPlaying() {
  const res = await fetch(PLAYING_URL);
  return res.json();
}

/** Obtiene películas trending del día */
export async function getTrendingDayMovies() {
  const res = await fetch(TRENDING_DAY_URL);
  return res.json();
}

/** Obtiene top películas */
export async function getTopMovies() {
  const res = await fetch(TOP_MOVIES_URL);
  return res.json();
}

// Obtiene detalles de una película específica
export async function getMovieDetails(id) {
  const res = await fetch(`${MOVIE_DETAILS_URL }/${id}`);
  return res.json();
}

// Obtiene el reparto de una película
export async function getMovieCast(id) {
  const res = await fetch(`${MOVIE_CAST_URL}/${id}`);
  return res.json();
}

// Obtiene url embed del trailer
export async function getUrlEmbed(id){
  const res = await fetch(`${MOVIE_TRAILER_URL}/${id}`);
  return res.text();
}