(function ($, window) {
    var Starrr;
    Starrr = (function () {

        var starNumber

        function Starrr($el) {
            this.$el = $el;
            this.init();
        }

        Starrr.prototype.init = function () {
            this.createStars();
            this.syncRating();
            this.attachEvents();
        };

        // Tạo 5 sao
        Starrr.prototype.createStars = function () {
            for (let i = 0; i < 5; i++) {
                this.$el.append("<span class='glyphicon glyphicon-star-empty'>★</span>");
            }
        };

        // Cập nhật các sao dựa trên rating
        Starrr.prototype.syncRating = function (rating = 0) {
            this.$el.children('span').each(function (index) {
                $(this).toggleClass('text-warning', index < rating); // Thêm class "text-warning" cho các sao đã được chọn
            });
        };

        // Gắn sự kiện vào các sao
        Starrr.prototype.attachEvents = function () {
            var _this = this;
            var isClicked = false; // Biến để kiểm tra xem sao đã được click hay chưa

            // Sự kiện di chuột (hover) - luôn hiển thị hiệu ứng khi hover vào sao
            this.$el.on('mouseover', 'span', function () {
                if (!isClicked) { // Chỉ áp dụng hiệu ứng hover khi chưa click
                    const rating = $(this).index() + 1;
                    _this.syncRating(rating);
                }
            });

            // Khi di chuột ra khỏi các sao
            this.$el.on('mouseout', function () {
                if (!isClicked) { // Chỉ áp dụng hiệu ứng khi chưa click
                    _this.syncRating();
                }
            });

            // Sự kiện click vào sao
            this.$el.on('click', 'span', function () {
                const rating = $(this).index() + 1;
                starNumber = rating
                isClicked = true; // Đánh dấu là đã click
                _this.syncRating(rating); // Cập nhật sao đã chọn
                $('#count').text(rating); // Hiển thị số sao đã chọn
                $('.alert').removeClass('d-none').show().delay(2000).fadeOut(); // Hiển thị thông báo
            });
        };

        Starrr.prototype.getStarNumber = function() {
            return starNumber; // Trả về giá trị của starNumber
        };

        return Starrr;
    })();

    // Gắn plugin starrr cho các phần tử
    $.fn.starrr = function () {
        return this.each(function () {
            new Starrr($(this));
        });
    };

    $.fn.starrr = function () {
        return this.each(function () {
            new Starrr($(this));
        });
    };
})(jQuery, window);

// // Khởi tạo xếp hạng sao khi trang tải
// $(function () {
//     $('#stars').starrr();
// });