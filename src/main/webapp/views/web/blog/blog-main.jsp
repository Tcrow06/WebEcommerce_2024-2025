<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


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
                            <span>Blog</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <div class="container-fluid d-flex justify-content-center align-items-center" style="height: 100vh; max-height: 400px; background-color:#f0f1f2;">
            <div class="card bg-transparent" style="width: 75rem; border: none;">
                <div class="row">
                    <div class="col-5 d-flex justify-content-center align-items-center">
                        <img src="<c:url value='/static/img/blog/blog-main.jpg'/>" class="card-img-top" style="border-radius: 24px; alt="...">
                    </div>
                    <div class="col-7 d-flex justify-content-center align-items-center">
                        <div class="card-body">
                            <p class = "fw-bold text-info">Wish you have a good day !</p>
                            <h4 class="card-title " style="font-size: 2rem; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3); font-family: 'Times New Roman', Times, serif;">
                                Chào mừng bạn đến với Blog Thời Trang của chúng tôi!
                            </h4>
                            <p class="card-text" style="font-size: 1.2rem; line-height: 1.6; color: black; font-weight: 500; font-family: 'Times New Roman', Times, serif;">
                                Khám phá những xu hướng mới, mẹo phối đồ sáng tạo và bí quyết chăm sóc trang phục, giúp bạn luôn tự tin và nổi bật.
                                <span style="color: #de9197; font-weight: bold;">Cùng chúng tôi tạo nên phong cách thời trang độc đáo</span>,
                                phản ánh cá tính riêng của bạn!
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Hình tam giác màu trắng bên dưới -->
        <div class="triangle " style="width: 0;
            height: 0;
            border-right: 100vw solid transparent;
            border-top: 200px solid #f0f1f2;
            position: relative;
            left: 0;"></div>
    <section class="blog-list">
        <div class="container">
            <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                <h3 class="mt-5 fs-2 py-3 mb-5 ms-3 text-danger-emphasis">Góc tư vấn mặc đẹp</h3>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <a href="<c:url value='/views/web/blog/blog-1.jsp'/>">
                        <div class="card" style="width: 24rem; border: none;">
                            <div class="row">
                                <img src="<c:url value='/static/img/blog/blog-m-1.png'/>" class="card-img-top px-2"
                                    alt="anh-minh-hoa"
                                    style="height: 18rem; width: 90%; object-fit: cover; border-radius: 24px; display: block; margin: 0 auto;">
                            </div>
                            <div class="card-body" style="font-family: 'Times New Roman', Times, serif;">
                                <div class="row">
                                    <div class="col-7">
                                        <h4 class="text-danger-emphasis fs-6 mb-2"
                                            style="font-family: 'Poppins', sans-serif;">Góc
                                            tư vấn mặc đẹp</h4>
                                    </div>
                                    <div class="col-5">
                                        <p class="text-body-tertiary fs-6 mb-2">11/09/2024</p>
                                    </div>
                                </div>
                                <h4 class="card-text mb-2">Chọn Quần Áo Tặng Cô Giáo Nhân Ngày 20/11: Bí Quyết Tạo Ấn
                                    Tượng
                                    Sâu Sắc</h4>
                                <p class="text-muted">Ngày Nhà Giáo Việt Nam 20/11 không chỉ là dịp để tri ân thầy cô,
                                    mà
                                    còn là cơ hội để
                                    chúng ta bày tỏ lòng biết ơn qua những món quà ý nghĩa...</p>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-md-4">
                    <a href="<c:url value='/views/web/blog/blog-2.jsp'/>">
                        <div class="card" style="width: 24rem; border: none;">
                            <div class="row">
                                <img src="<c:url value='/static/img/blog/blog-m-2.jpg'/>" class="card-img-top px-2"
                                    alt="anh-minh-hoa"
                                    style="height: 18rem; width: 90%; object-fit: cover; border-radius: 24px; display: block; margin: 0 auto;">
                            </div>
                            <div class="card-body" style="font-family: 'Times New Roman', Times, serif;">
                                <div class="row">
                                    <div class="col-7">
                                        <h4 class="text-danger-emphasis fs-6 mb-2"
                                            style="font-family: 'Poppins', sans-serif;">Góc
                                            tư vấn làm đẹp</h4>
                                    </div>
                                    <div class="col-4">
                                        <p class="text-body-tertiary fs-6 mb-2">11/09/2024</p>
                                    </div>
                                    <div class="col-1">

                                    </div>
                                </div>
                                <h4 class="card-text mb-2">Để Tôi Chỉ Cho Cách Mặc Sơ Mi Đúng Cách: Tự Tin Và Lịch Lãm</h4>
                                <p class="text-muted">Sơ mi là một trong những món đồ không thể thiếu trong tủ đồ của mỗi
                                    người, đặc biệt là đối với những người yêu thích sự lịch sự và trang trọng...</p>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-md-4">
                    <a href="<c:url value='/views/web/blog/blog-3.jsp'/>">
                        <div class="card" style="width: 24rem; border: none;">
                            <div class="row">
                                <img src="<c:url value='/static/img/blog/blog-m-3.jpg'/>" class="card-img-top px-2"
                                    alt="anh-minh-hoa"
                                    style="height: 18rem; width: 90%; object-fit: cover; border-radius: 24px; display: block; margin: 0 auto;">
                            </div>
                            <div class="card-body" style="font-family: 'Times New Roman', Times, serif;">
                                <div class="row">
                                    <div class="col-7">
                                        <h4 class="text-danger-emphasis fs-6 mb-2"
                                            style="font-family: 'Poppins', sans-serif;">Góc
                                            tư vấn làm đẹp</h4>
                                    </div>
                                    <div class="col-4">
                                        <p class="text-body-tertiary fs-6 mb-2">11/09/2024</p>
                                    </div>
                                    <div class="col-1">

                                    </div>
                                </div>
                                <h4 class="card-text mb-2">Phối Màu Quần Áo: Nghệ Thuật Tạo Dựng Phong Cách Cá Nhân</h4>
                                <p class="text-muted">Phối màu quần áo không chỉ đơn giản là việc chọn ra những món đồ phù
                                    hợp với nhau mà còn là một nghệ thuật giúp bạn thể hiện cá tính và...</p>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <!--  -->
            <div class="row">
                <div class="col-md-4">
                    <a href="<c:url value='/views/web/blog/blog-4.jsp'/>">
                        <div class="card" style="width: 24rem; border: none;">
                            <div class="row">
                                <img src="<c:url value='/static/img/blog/blog-m-4.jpg'/>" class="card-img-top px-2"
                                    alt="anh-minh-hoa"
                                    style="height: 18rem; width: 90%; object-fit: cover; border-radius: 24px; display: block; margin: 0 auto;">
                            </div>
                            <div class="card-body" style="font-family: 'Times New Roman', Times, serif;">
                                <div class="row">
                                    <div class="col-7">
                                        <h4 class="text-danger-emphasis fs-6 mb-2"
                                            style="font-family: 'Poppins', sans-serif;">Góc
                                            tư vấn làm đẹp</h4>
                                    </div>
                                    <div class="col-4">
                                        <p class="text-body-tertiary fs-6 mb-2">11/09/2024</p>
                                    </div>
                                    <div class="col-1">

                                    </div>
                                </div>
                                <h4 class="card-text mb-2">Thời Trang Thể Thao: Sự Kết Hợp Hoàn Hảo Giữa Phong Cách và Tiện
                                    Ích</h4>
                                <p class="text-muted">Thời trang thể thao đã không còn chỉ đơn thuần là những bộ trang phục
                                    dành riêng cho việc luyện tập. Nó đã trở thành một phần không thể thiếu...</p>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-md-4">
                    <a href="<c:url value='/views/web/blog/blog-5.jsp'/>">
                        <div class="card" style="width: 24rem; border: none;">
                            <div class="row">
                                <img src="<c:url value='/static/img/blog/blog-m-5.jpg'/>" class="card-img-top px-2"
                                    alt="anh-minh-hoa"
                                    style="height: 18rem; width: 90%; object-fit: cover; border-radius: 24px; display: block; margin: 0 auto;">
                            </div>
                            <div class="card-body" style="font-family: 'Times New Roman', Times, serif;">
                                <div class="row">
                                    <div class="col-7">
                                        <h4 class="text-danger-emphasis fs-6 mb-2"
                                            style="font-family: 'Poppins', sans-serif;">Góc
                                            tư vấn làm đẹp</h4>
                                    </div>
                                    <div class="col-4">
                                        <p class="text-body-tertiary fs-6 mb-2">11/09/2024</p>
                                    </div>
                                    <div class="col-1">

                                    </div>
                                </div>
                                <h4 class="card-text mb-2">Sản Xuất Thời Trang Bảo Vệ Môi Trường: Xu Hướng Bền Vững Cho
                                    Tương Lai</h4>
                                <p class="text-muted">Trong bối cảnh biến đổi khí hậu và ô nhiễm môi trường ngày càng gia
                                    tăng, ngành công nghiệp thời trang đang đối mặt với một thách thức lớn...</p>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-md-4">
                    <a href="<c:url value='/views/web/blog/blog-6.jsp'/>">
                        <div class="card" style="width: 24rem; border: none;">
                            <div class="row">
                                <img src="<c:url value='/static/img/blog/blog-m-6.jpg'/>" class="card-img-top px-2"
                                    alt="anh-minh-hoa"
                                    style="height: 18rem; width: 90%; object-fit: cover; border-radius: 24px; display: block; margin: 0 auto;">
                            </div>
                            <div class="card-body" style="font-family: 'Times New Roman', Times, serif;">
                                <div class="row">
                                    <div class="col-7">
                                        <h4 class="text-danger-emphasis fs-6 mb-2"
                                            style="font-family: 'Poppins', sans-serif;">Góc
                                            tư vấn làm đẹp</h4>
                                    </div>
                                    <div class="col-4">
                                        <p class="text-body-tertiary fs-6 mb-2">11/09/2024</p>
                                    </div>
                                    <div class="col-1">

                                    </div>
                                </div>
                                <h4 class="card-text mb-2">Phối đồ nơi công sở: Tạo phong cách chuyên nghiệp và lịch sự</h4>
                                <p class="text-muted">Công sở không chỉ là nơi làm việc mà còn là nơi thể hiện phong cách cá
                                    nhân thông qua trang phục. Việc lựa chọn trang phục phù hợp sẽ giúp bạn tự tin...</p>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </section>
</body>

</html>