<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>
    <section class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__text">
                        <h4>Shop</h4>
                        <div class="breadcrumb__links">
                            <a href="<c:url value="/trang-chu" />">Home</a>
                            <span>Shop</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <div class="container-sm mt-5" style="max-width: 1000px; font-family: 'Poppins', sans-serif;">
        <!-- tiêu đề -->
        <div class="text-center">
            <h2>
                Thời Trang Thể Thao: Sự Kết Hợp Hoàn Hảo Giữa Phong Cách và Tiện Ích</h2>
        </div>
        <!-- giới thiệu -->
        <div class="my-5">
            <p>Thời trang thể thao đã không còn chỉ đơn thuần là những bộ trang phục dành riêng cho việc luyện tập. Nó
                đã trở thành một phần không thể thiếu trong đời sống hàng ngày, mang đến sự thoải mái và phong cách cho
                người mặc. Với sự phát triển mạnh mẽ của ngành công nghiệp thời trang, những bộ đồ thể thao giờ đây
                không chỉ phục vụ cho các hoạt động vận động mà còn được thiết kế để phù hợp với nhiều hoàn cảnh khác
                nhau, từ phòng gym cho đến các buổi đi chơi, dạo phố.</p>
        </div>
        <!-- menu -->
        <div class="container my-4">
            <h4 class="text-danger-emphasis fs-5 mb-2 fw-bold" style="font-family: 'Poppins', sans-serif ;">Mục
                lục bài viết</h4>
            <div class="accordion" id="accordionExample">
                <div class="accordion-item">
                    <a href="#headingZero" style="text-decoration: none;">
                        <h2 class="accordion-header">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                🌟Phong Cách Tự Do và Phóng Khoáng
                            </button>
                        </h2>
                    </a>
                </div>
                <div class="accordion-item">
                    <a href="#headingOne" style="text-decoration: none;">
                        <h2 class="accordion-header">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                🌟Kết Hợp Thời Trang Thể Thao Với Phong Cách Hàng Ngày
                            </button>
                        </h2>
                    </a>
                </div>
                <div class="accordion-item">
                    <a href="#headingTwo" style="text-decoration: none;">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                🌟Sự Bùng Nổ Của Các Thương Hiệu Thời Trang Thể Thao
                            </button>
                        </h2>
                    </a>
                </div>
                <div class="accordion-item">
                    <a href="#headingThree" style="text-decoration: none;">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                🌟Thời Trang Thể Thao Cho Mọi Lứa Tuổi
                            </button>
                        </h2>
                    </a>
                </div>
                <div class="accordion-item">
                    <a href="#headingFour" style="text-decoration: none;">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                🌟Lời Kết
                            </button>
                        </h2>
                    </a>
                </div>
            </div>
        </div>


        <!-- nội dung -->

        <div class="mt-5">
            <h4 id="headingZero">1. Phong Cách Tự Do và Phóng Khoáng</h4>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-4-1.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Phong cách thoải mái, linh hoạt với trang phục rộng rãi và
                            phối đồ ngẫu hứng, tạo nét năng động và cá tính.</p>
                    </div>
                </div>
            </div>
            <p> Một trong những điểm mạnh của thời trang thể thao là tính linh hoạt và thoải mái mà nó mang lại. Những
                bộ đồ thể thao hiện đại như áo phông, quần jogger, áo hoodie, và giày thể thao không chỉ giúp bạn vận
                động dễ dàng mà còn tạo ra một phong cách cá nhân đầy năng động. Các thương hiệu nổi tiếng như Nike,
                Adidas, Puma, và Under Armour đã không ngừng cải tiến thiết kế, tạo ra các bộ sưu tập thời trang thể
                thao đầy sáng tạo và bắt mắt, từ những màu sắc tươi sáng cho đến những họa tiết độc đáo.</p>
        </div>
        <div class="mt-5">
            <h4 id="headingOne">2. Sự Kết Hợp Giữa Công Nghệ và Thời Trang</h4>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-4-2.png'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Xu hướng tích hợp công nghệ vào thiết kế thời trang, tạo nên
                            trang phục thông minh, tiện ích và hiện đại.</p>
                    </div>
                </div>
            </div>
            <p>Không chỉ đơn giản là những bộ đồ thấm hút mồ hôi, thời trang thể thao hiện nay còn được tích hợp công
                nghệ tiên tiến. Các chất liệu như vải thun Spandex, polyester và công nghệ Dry-Fit giúp cơ thể luôn khô
                ráo, thoải mái trong suốt quá trình vận động. Ngoài ra, các thiết kế thời trang thể thao còn chú trọng
                đến yếu tố bảo vệ cơ thể, như giày thể thao có đệm êm ái giúp bảo vệ chân, áo khoác gió chống nước để
                bảo vệ cơ thể trong mọi điều kiện thời tiết.</p>
        </div>
        <!--  -->
        <div class="mt-5">
            <h4 id="headingTwo">3. Kết Hợp Thời Trang Thể Thao Với Phong Cách Hàng Ngày</h4>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-4-3.jpeg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Sự pha trộn giữa trang phục thể thao và trang phục thường
                            ngày, mang đến vẻ năng động, thoải mái và phù hợp cho cả hoạt động lẫn dạo phố.</p>
                    </div>
                </div>
            </div>
            <p>Với sự phát triển của xu hướng athleisure, thời trang thể thao đã không còn bị giới hạn trong các phòng
                tập thể dục hay sân vận động. Bạn có thể dễ dàng phối đồ thể thao với các trang phục thông thường để tạo
                nên một bộ trang phục hoàn hảo cho cả những dịp đi chơi, đi làm hoặc thậm chí đi dự tiệc. Một chiếc áo
                phông thể thao có thể kết hợp cùng quần jean hay chân váy, trong khi một đôi giày thể thao thoải mái có
                thể là sự lựa chọn tuyệt vời cho một ngày dạo phố.</p>
        </div>
        <!--  -->
        <div class="mt-5">
            <h4 id="headingThree">4. Sự Bùng Nổ Của Các Thương Hiệu Thời Trang Thể Thao</h4>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-4-4.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Các thương hiệu thời trang thể thao nổi tiếng.</p>
                    </div>
                </div>
            </div>
            <p>Các thương hiệu thời trang thể thao không chỉ giới hạn trong việc cung cấp đồ cho các vận động viên mà
                còn hướng đến người tiêu dùng đại chúng. Các chiến dịch quảng cáo nổi bật, những ngôi sao thể thao hay
                influencer tham gia vào các chiến dịch marketing, giúp thời trang thể thao không chỉ được yêu thích bởi
                những người đam mê thể thao mà còn trở thành một xu hướng thời trang chủ đạo.

                Các thương hiệu như Nike, Adidas, và Puma đã tạo ra không chỉ những sản phẩm chất lượng mà còn là những
                biểu tượng về phong cách sống khỏe mạnh và năng động. Thậm chí, có những bộ sưu tập hợp tác giữa các
                thương hiệu thể thao và các nhà thiết kế nổi tiếng, tạo ra những sản phẩm thời trang thể thao cao cấp.
            </p>
        </div>
        <!--  -->
        <div class="mt-5">
            <h4 id="headingFour">5. Thời Trang Thể Thao Cho Mọi Lứa Tuổi</h4>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-4-5.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Sự khác biệt làm nên phong cách.</p>
                    </div>
                </div>
            </div>
            <p>Một điểm đặc biệt nữa của thời trang thể thao là nó phù hợp với mọi lứa tuổi. Từ trẻ em cho đến người
                lớn tuổi, tất cả đều có thể tìm thấy những bộ đồ thể thao phù hợp với phong cách và nhu cầu của mình.
                Những bộ đồ đơn giản, thoải mái cho trẻ em đến những bộ trang phục thể thao thanh lịch và hiện đại cho
                người trưởng thành, tất cả đều mang lại cảm giác tự do, thoải mái mà vẫn thời trang.</p>
        </div>
        <!--  -->
        <div class="mt-5">
            <h4 id="headingFive">Lời Kết</h4>
            <p>Thời trang thể thao là sự kết hợp tuyệt vời giữa phong cách và tiện ích. Nó không chỉ phục vụ cho các
                hoạt động thể chất mà còn giúp người mặc thể hiện cá tính, phong cách sống năng động và hiện đại. Bất kể
                bạn là ai, bạn đều có thể tìm thấy cho mình những bộ đồ thể thao phù hợp, từ những bộ đồ dành cho phòng
                gym, chạy bộ đến những bộ trang phục thoải mái cho mọi hoạt động ngoài trời. Hãy để thời trang thể thao
                trở thành một phần không thể thiếu trong tủ đồ của bạn, mang đến sự tự tin và phong cách suốt cả ngày
                dài.</p>
        </div>
        <!-- tin lien quan -->
        <div class="related-news mb-5" style="font-family: 'Poppins', sans-serif;">
            <h4 class="text-danger-emphasis fs-5 mb-2 fw-bold">Tin tức liên quan</h4>
            <ul>
                <li><a href="<c:url value='/views/web/blog/blog-6.jsp'/>" style=" text-decoration: none; color: #E693C7;">Phối đồ nơi công sở: Tạo phong cách chuyên nghiệp và lịch sự</a></li>
                <li><a href="<c:url value='/views/web/blog/blog-5.jsp'/>" style=" text-decoration: none; color: #E693C7;">Sản Xuất Thời Trang Bảo Vệ Môi Trường: Xu Hướng Bền Vững Cho Tương Lai</a></li>
                <li><a href="<c:url value='/views/web/blog/blog-1.jsp'/>" style=" text-decoration: none; color: #E693C7;">Chọn Quần Áo Tặng Giáo Viên Nữ Nhân Ngày 20/11: Bí Quyết Tạo Ấn Tượng Sâu Sắc</a></li>
                <li><a href="<c:url value='/views/web/blog/blog-3.jsp'/>" style=" text-decoration: none; color: #E693C7;">Phối Màu Quần Áo: Nghệ Thuật Tạo Dựng Phong Cách Cá Nhân</a></li>
                <li><a href="<c:url value='/views/web/blog/blog-2.jsp'/>" style=" text-decoration: none; color: #E693C7;">Cách Mặc Sơ Mi Đúng Cách: Tự Tin Và Lịch Lãm</a></li>
            </ul>
        </div>
    </div>
</body>

</html>