// peliculas.js
import { initSlider } from './slider.js';
import { SingleMovieSlider } from './SingleMovieSlider.js';

/**
 * Renderiza un array de películas dentro de un contenedor dado
 * @param {Array} peliculas - Array de objetos de películas
 * @param {HTMLElement} contenedor - Elemento donde se renderizan las películas
 */

function renderPeliculas(peliculas, contenedor) {
  contenedor.innerHTML = ''; // Limpiar contenido previo

  peliculas.forEach(pelicula => {
    const articulo = document.createElement('article');
    articulo.classList.add('pelicula-item');
    articulo.setAttribute('data-id', pelicula.id);
    articulo.setAttribute('role', 'listitem');
    articulo.setAttribute('aria-label', pelicula.title);
    articulo.setAttribute('itemscope', '');
    articulo.setAttribute('itemtype', 'https://schema.org/PeliculaObject');

    // Imagen de portada
    const img = document.createElement('img');
    img.src = 'https://image.tmdb.org/t/p/w300' + pelicula.poster_path;
    img.alt = pelicula.title;
    img.classList.add('thumbnail');
    img.setAttribute('itemprop', 'thumbnailUrl');

    // Contenedor de puntuación
    const vote = document.createElement('div');
    vote.classList.add('vote');
    vote.setAttribute('itemprop', 'voteAverage');
    const star = document.createElement('span');
    star.textContent = '★';
    star.style.color = 'gold';
    const score = document.createElement('span');
    score.textContent = pelicula.vote_average !== undefined ? pelicula.vote_average.toFixed(1) : 'N/A';
    vote.appendChild(star);
    vote.appendChild(score);

    // Título
    const title = document.createElement('h3');
    title.classList.add('pelicula-title');
    title.setAttribute('itemprop', 'name');
    title.textContent = pelicula.title;

    // Año de lanzamiento
    const releaseYear = document.createElement('span');
    releaseYear.classList.add('pelicula-release-year');
    releaseYear.setAttribute('itemprop', 'datePublished');
    releaseYear.textContent = pelicula.release_date ? new Date(pelicula.release_date).getFullYear() : 'N/A';

    // Construir artículo
    articulo.appendChild(img);
    articulo.appendChild(vote);
    articulo.appendChild(title);
    articulo.appendChild(releaseYear);
    //articulo.appendChild(botonesContainer);

    // Agregar artículo al contenedor
    contenedor.appendChild(articulo);
  });
}

/**
 * Función genérica que renderiza películas desde un array de datos
 * @param {Array} peliculas - Array de objetos de películas
 * @param {string} contenedorId - ID del contenedor donde se agregan
 * @param {string} prevBtnId - ID del botón de scroll anterior
 * @param {string} nextBtnId - ID del botón de scroll siguiente
 * @param {number} elementosVisibles - Número de películas visibles en escritorio
 * @param {number} anchoElemento - Ancho de cada película + gap
 */
export function cargarPeliculas(peliculas, contenedorId, prevBtnId, nextBtnId, elementosVisibles, anchoElemento) {
  const contenedor = document.getElementById(contenedorId);

  // Si es hero banner, usar SingleMovieSlider
  if (contenedorId === 'proximo-container') {
    new SingleMovieSlider(contenedorId, prevBtnId, nextBtnId, peliculas);
    return;
  }

  // Para sliders de lista normal
  renderPeliculas(peliculas, contenedor);

  // Inicializar slider tipo lista
  initSlider(contenedorId, prevBtnId, nextBtnId, elementosVisibles, anchoElemento);
}


  /**
   * Función opcional para formatear duración en horas y minutos
   * @param {number} minutos
   * @returns {string} Formato "Xh Ym" o "Ym"
   */
  export function formatDuration(minutos) {
    const h = Math.floor(minutos / 60);
    const m = minutos % 60;
    return h > 0 ? `${h}h ${m}m` : `${m}m`;
  }
