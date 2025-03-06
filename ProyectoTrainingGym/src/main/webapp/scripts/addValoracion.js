document.addEventListener('DOMContentLoaded', function() {
    const stars = document.querySelectorAll('.star');
    stars.forEach(star => {
        star.addEventListener('click', function() {
            const rating = this.getAttribute('data-value');
            document.getElementById('rating').value = rating;
            stars.forEach((s, index) => {
                if (index < rating) {
                    s.classList.add('filled');
                } else {
                    s.classList.remove('filled');
                }
            });
        });
    });

    const ratingForm = document.getElementById('ratingForm');
    ratingForm.addEventListener('submit', function(event) {
        const filledStars = document.querySelectorAll('.star.filled').length;
        document.getElementById('rating').value = filledStars;
    });
});