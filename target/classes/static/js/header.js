// header.js
export default class Header {
  constructor(containerId, fragmentUrl) {
    this.containerId = containerId;
    this.fragmentUrl = fragmentUrl;
  }

  async load() {
    try {
      const res = await fetch(this.fragmentUrl);
      if (!res.ok) throw new Error(`Error al cargar ${this.fragmentUrl}`);
      const html = await res.text();
      document.getElementById(this.containerId).innerHTML = html;
      this.init();
    } catch (err) {
      console.error(err);
    }
  }

  init() {
    const header = document.querySelector('.main-header');

    // Toggle hamburguesa
    const hamburger = header.querySelector('.hamburger');
    if (hamburger) {
      hamburger.addEventListener('click', () => {
        header.classList.toggle('nav-open');
      });
    }

    // Toggle submenús
    header.querySelectorAll('.has-dropdown > a').forEach(trigger => {
    trigger.addEventListener('click', (e) => {
      e.preventDefault();
      const parent = trigger.parentElement;

      // Cierra otros submenús abiertos
      header.querySelectorAll('.has-dropdown.open').forEach(openItem => {
        if (openItem !== parent) openItem.classList.remove('open');
      });

      // Alterna el submenú actual
      parent.classList.toggle('open');
    });
});
  }
}
