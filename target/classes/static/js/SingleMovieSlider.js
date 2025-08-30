import { loadMovie } from './movieDetails.js';

export class SingleMovieSlider {
  constructor(containerId, prevBtnId, nextBtnId, movies) {
    this.container = document.getElementById(containerId);
    this.prevBtn = document.getElementById(prevBtnId);
    this.nextBtn = document.getElementById(nextBtnId);
    this.movies = movies;
    this.currentIndex = 0;

    this.render();
    this.addEventListeners();
    this.addTouchSupport(); // swipe táctil en móvil
  }

  render() {
    this.container.innerHTML = '';

    const pelicula = this.movies[this.currentIndex];

    const articulo = document.createElement('article');
    articulo.classList.add('proximo-item');

    const wrapper = document.createElement('div');
    wrapper.classList.add('proximo-wrapper');

    // Poster ocupando todo el wrapper
    const poster = document.createElement('img');
    poster.src = `https://image.tmdb.org/t/p/original${pelicula.backdrop_path}`;
    poster.alt = pelicula.title;
    poster.classList.add('poster');

    // Info
    const info = document.createElement('div');
    info.classList.add('info');

    const title = document.createElement('h3');
    title.textContent = pelicula.title;

    const btnContainer = document.createElement('div');
    btnContainer.classList.add('btn-container');

    const btnTrailer = document.createElement('button');
    btnTrailer.textContent = 'Trailer';
    btnTrailer.addEventListener('click', () => {
      window.location.href = `film.html?id=${pelicula.id}`;
    });
    btnContainer.appendChild(btnTrailer);

    info.appendChild(title);
    info.appendChild(btnContainer);

    wrapper.appendChild(poster);
    wrapper.appendChild(info);
    articulo.appendChild(wrapper);
    this.container.appendChild(articulo);
  }

  addEventListeners() {
    this.prevBtn.addEventListener('click', () => {
      this.currentIndex = (this.currentIndex - 1 + this.movies.length) % this.movies.length;
      this.render();
    });

    this.nextBtn.addEventListener('click', () => {
      this.currentIndex = (this.currentIndex + 1) % this.movies.length;
      this.render();
    });
  }

  addTouchSupport() {
    let startX = 0;
    let isDragging = false;

    this.container.addEventListener('touchstart', e => {
      startX = e.touches[0].clientX;
      isDragging = true;
    });

    this.container.addEventListener('touchend', e => {
      if (!isDragging) return;
      const endX = e.changedTouches[0].clientX;
      const deltaX = endX - startX;

      if (deltaX > 50) this.prevBtn.click();
      else if (deltaX < -50) this.nextBtn.click();

      isDragging = false;
    });
  }
}
