document.addEventListener("DOMContentLoaded", function() {
    var swiper = new Swiper(".mySwiper", {
        slidesPerView: 1,
        spaceBetween: 10,
        grabCursor: true,
        pagination: {
            el: ".swiper-pagination",
            clickable: true,
        }
    });
});

document.addEventListener("DOMContentLoaded", function() {
    var swiper = new Swiper(".muSwiper", {
        slidesPerView: 3,
        spaceBetween: 10,
        grabCursor: true,
        pagination: {
            el: ".swiper-pagination",
            clickable: true,
        }
    });
});