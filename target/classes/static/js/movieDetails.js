import Header from './header.js';
import Footer from './footer.js';
import { getMovieDetails, getMovieCast ,getUrlEmbed } from './api.js';

export async function loadMovie(idMovie) {
    
    // Obtener los datos de la película
    const movieData = await getMovieDetails(idMovie);

    // Obtener créditos de la película
    const credits = await getMovieCast(idMovie);

    // Obtengo el Director
    const director = credits.crew.find(member => member.job === 'Director');

    // Obtengo la lista de actores
    const cast = credits.cast;

    // Obtengo los guionistas
    const writers = credits.crew
    .filter(member => member.job === 'Screenplay' || member.job === 'Writer');

    // Obtiene la URL del trailer en youTube, versión embed
    const trailerUrlEmbed = await getUrlEmbed(idMovie);

    // Actualizar elementos HTML con los datos de la película
    document.getElementById('title').textContent = movieData.title;
    document.getElementById('info').textContent = movieData.overview;
    document.getElementById('year').textContent = movieData.release_date.split('-')[0];
    document.getElementById('runtime').textContent = movieData.runtime + ' min';
    document.getElementById('rating').textContent = movieData.vote_average.toFixed(1);

    // Backdrop
    document.getElementById('backdrop').style.backgroundImage = `url(https://image.tmdb.org/t/p/w500${movieData.poster_path})`;

     // Trailer getUrlEmbed
    document.getElementById('movie-trailer').src = trailerUrlEmbed; 

    // Géneros
    const genresContainer = document.getElementById('genres');
    genresContainer.innerHTML = ''; // limpiar contenido previo

    // Añadir título Géneros
    const genres = document.createElement('strong');
    genres.textContent = 'Género: ';
    genresContainer.appendChild(genres);

    // Añadir lista de géneros
    movieData.genres.forEach(g => {
        const span = document.createElement('span');
        span.textContent = g.name + ' ';
        genresContainer.appendChild(span);
    });

    // Director
    const directorContainer = document.getElementById('director');
    directorContainer.innerHTML = ''; // limpiar contenido previo

    // Añadir título Director
    const nameDirector = document.createElement('strong');
    nameDirector.textContent = 'Director: ';
    directorContainer.appendChild(nameDirector);

    // Añadir nombre del director
    const directorName = document.createElement('span');
    directorName.textContent = director.name;
    directorContainer.appendChild(directorName);

    // Reparto
    const castContainer = document.getElementById('cast');
    castContainer.innerHTML = ''; // limpiar contenido previo

    // Añadir título Reparto
    const titleCast = document.createElement('strong');
    titleCast.textContent = 'Reparto principal: ';
    castContainer.appendChild(titleCast);

    // Añadir lista de actores principales
    cast.slice(0, 5).forEach((actor, index, array) => {
        const span = document.createElement('span');
        span.textContent = actor.name + (index < array.length - 1 ? ', ' : '');
        castContainer.appendChild(span);
    });

    // Guionistas
    const writerContainer = document.getElementById('writers');
    writerContainer.innerHTML = ''; // limpiar contenido previo

    // Añadir título Guionistas
    const titleWriters = document.createElement('strong');
    titleWriters.textContent = 'Guionistas: ';
    writerContainer.appendChild(titleWriters);

    // Añadir guinistas
    writers.forEach((writer, index, array) => {
        const span = document.createElement('span');
        span.textContent = writer.name + (index < array.length - 1 ? ', ' : '');
        writerContainer.appendChild(span);
    });

     // Carga el header y footer
      const header = new Header("header", "header.html");
      const footer = new Footer("footer", "footer.html");
      await header.load();
      await footer.load();
}
