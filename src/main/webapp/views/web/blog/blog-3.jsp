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
                Phối Màu Quần Áo: Nghệ Thuật Tạo Dựng Phong Cách Cá Nhân</h2>
        </div>
        <!-- giới thiệu -->
        <div class="my-5">
            <p>Phối màu quần áo không chỉ đơn giản là việc chọn ra những món đồ phù hợp với nhau mà còn là một nghệ
                thuật giúp bạn thể hiện cá tính và phong cách riêng. Mỗi người có thể tạo ra một bộ trang phục ấn tượng
                thông qua việc kết hợp màu sắc sao cho hài hòa, tôn lên vẻ đẹp của bản thân. Dưới đây là những nguyên
                tắc cơ bản để phối màu quần áo một cách hiệu quả.</p>
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
                                🌟Hiểu Biết Về Bảng Màu
                            </button>
                        </h2>
                    </a>
                </div>
                <div class="accordion-item">
                    <a href="#headingOne" style="text-decoration: none;">
                        <h2 class="accordion-header">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                🌟Nguyên Tắc 60-30-10
                            </button>
                        </h2>
                    </a>
                </div>
                <div class="accordion-item">
                    <a href="#headingTwo" style="text-decoration: none;">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                🌟Chọn Màu Phù Hợp Với Tông Da
                            </button>
                        </h2>
                    </a>
                </div>
                <div class="accordion-item">
                    <a href="#headingThree" style="text-decoration: none;">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                🌟Không Quên Phụ Kiện
                            </button>
                        </h2>
                    </a>
                </div>
                <div class="accordion-item">
                    <a href="#headingFour" style="text-decoration: none;">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                🌟Thử Nghiệm Với Các Cặp Màu Lạ
                            </button>
                        </h2>
                    </a>
                </div>
                <div class="accordion-item">
                    <a href="#headingFive" style="text-decoration: none;">
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
            <h4 id="headingZero">1. Hiểu Biết Về Bảng Màu</h4>
            <p>Một trong những yếu tố quan trọng nhất khi phối màu quần áo là hiểu rõ về bảng màu. Bảng màu giúp chúng
                ta phân biệt các nhóm màu và cách chúng kết hợp với nhau:</p>
            <h5>- Màu đối diện (Complementary colors):</h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-3-1.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Hai màu nằm đối diện trên bánh xe màu, tạo sự tương phản mạnh
                            và thu hút trong thiết kế.</p>
                    </div>
                </div>
            </div>
            <p> Đây là những màu nằm đối diện nhau trên bảng màu, ví dụ như đỏ và xanh lá, vàng và tím. Khi kết hợp các
                màu này, bạn sẽ tạo ra sự tương phản mạnh mẽ và nổi bật.</p>
            <!--  -->
            <br>
            <h5>- Màu tương tự (Analogous colors):</h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-3-2.png'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Các màu nằm cạnh nhau trên bánh xe màu, mang lại cảm giác hài
                            hòa và dễ chịu khi kết hợp trong thiết kế.</p>
                    </div>
                </div>
            </div>
            <p> Đây là những màu nằm gần nhau trên bảng màu, ví dụ như xanh lá, xanh dương và xanh lam. Sự kết hợp này
                mang lại cảm giác hài hòa và dễ chịu.</p>
            <!--  -->
            <h5>- Màu đơn sắc (Monochromatic colors): </h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-3-3.webp'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Các sắc thái khác nhau của cùng một màu, tạo nên sự nhất quán
                            và nhẹ nhàng trong thiết kế.</p>
                    </div>
                </div>
            </div>
            <p> Là việc sử dụng nhiều tông màu khác nhau của cùng một màu, ví dụ như sắc xanh đậm, xanh nhạt và xanh
                pastel. Cách phối này giúp tạo nên sự tinh tế và đồng nhất.</p>
        </div>
        <div class="mt-5">
            <h4 id="headingOne">2. Nguyên Tắc 60-30-10</h4>
            <p>Một trong những nguyên tắc phổ biến khi phối màu quần áo là tỷ lệ 60-30-10. Nguyên tắc này giúp tạo ra sự
                cân đối và hài hòa trong trang phục:</p>
            <h5>- 60% màu chủ đạo:</h5>
            <p> Đây là màu chính của bộ trang phục. Chẳng hạn, bạn có thể chọn một chiếc áo màu trung tính như trắng,
                đen hoặc xám.</p>
            <!--  -->
            <br>
            <h5>- 30% màu phụ:</h5>
            <p> Là màu đi kèm với màu chủ đạo, tạo điểm nhấn cho bộ đồ. Bạn có thể chọn màu sắc tươi sáng như xanh, đỏ
                hoặc vàng.</p>
            <!--  -->
            <h5>- 10% màu accent:</h5>
            <p> Màu này đóng vai trò làm nổi bật, giúp bộ trang phục trở nên thú vị hơn. Những chi tiết như phụ kiện,
                giày dép hay túi xách thường sử dụng màu sắc này.</p>
        </div>
        <!--  -->
        <div class="mt-5">
            <h4 id="headingTwo">3. Chọn Màu Phù Hợp Với Tông Da</h4>
            <p>Một yếu tố quan trọng khi phối màu quần áo là chọn màu sắc phù hợp với tông da của bạn. Các tông da chia
                thành hai nhóm chính:
            <h5>- Tông da ấm (Warm skin tone): </h5>
            <p>Những người có tông da ấm thường phù hợp với các màu sắc như đỏ, cam, vàng, nâu đất và màu hồng đào.</p>
            <h5>- Tông da lạnh (Cool skin tone):</h5>
            <p> Tông da lạnh thường kết hợp tốt với các màu sắc như xanh dương, xanh lá, tím, bạc và các sắc lạnh khác..
            </p>
        </div>
        <!--  -->
        <div class="mt-5">
            <h4 id="headingThree">4. Không Quên Phụ Kiện</h4>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-3-4.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Phụ kiện là chi tiết quan trọng giúp hoàn thiện trang phục,
                            tạo điểm nhấn và thể hiện phong cách cá nhân.</p>
                    </div>
                </div>
            </div>
            <p>Phụ kiện là yếu tố quan trọng trong việc phối màu quần áo. Đôi khi, một chiếc đồng hồ, túi xách hay đôi
                giày có thể là điểm nhấn tuyệt vời cho bộ trang phục của bạn. Hãy chọn phụ kiện sao cho phù hợp với màu
                sắc của trang phục chính, đồng thời thể hiện được cá tính và gu thẩm mỹ của bạn.</p>
        </div>
        <!--  -->
        <div class="mt-5">
            <h4 id="headingFour">5. Thử Nghiệm Với Các Cặp Màu Lạ</h4>
            <p>Đừng ngại thử nghiệm với các cặp màu không giống nhau, đôi khi chúng có thể tạo nên một sự kết hợp vô
                cùng độc đáo. Hãy thử phối các màu như đỏ và xanh lá, tím và vàng, hoặc cam và xanh dương để tạo nên
                những bộ trang phục mới mẻ và ấn tượng.</p>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-3-5.webp'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Sự khác biệt làm nên phong cách.</p>
                    </div>
                </div>
            </div>
        </div>
        <!--  -->
        <div class="mt-5">
            <h4 id="headingFive">Lời Kết</h4>
            <p>Phối màu quần áo là một nghệ thuật và một phần quan trọng trong việc tạo dựng phong cách cá nhân. Khi bạn
                hiểu được cách kết hợp màu sắc một cách hài hòa, bạn không chỉ nâng cao gu thẩm mỹ mà còn tạo ra sự tự
                tin trong mỗi bộ trang phục. Hãy thử áp dụng những nguyên tắc trên và đừng ngại khám phá những màu sắc
                mới để làm phong phú thêm tủ đồ của mình.</p>
        </div>
        <!-- tin lien quan -->
        <div class="related-news mb-5" style="font-family: 'Poppins', sans-serif;">
            <h4 class="text-danger-emphasis fs-5 mb-2 fw-bold">Tin tức liên quan</h4>
            <ul>
                <li><a href="<c:url value='/views/web/blog/blog-6.jsp'/>" style=" text-decoration: none; color: #E693C7;">Phối đồ nơi công sở: Tạo phong cách chuyên nghiệp và lịch sự</a></li>
                <li><a href="<c:url value='/views/web/blog/blog-5.jsp'/>" style=" text-decoration: none; color: #E693C7;">Sản Xuất Thời Trang Bảo Vệ Môi Trường: Xu Hướng Bền Vững Cho Tương Lai</a></li>
                <li><a href="<c:url value='/views/web/blog/blog-4.jsp'/>" style=" text-decoration: none; color: #E693C7;">Thời Trang Thể Thao: Sự Kết Hợp Hoàn Hảo Giữa Phong Cách và Tiện Ích</a></li>
                <li><a href="<c:url value='/views/web/blog/blog-1.jsp'/>" style=" text-decoration: none; color: #E693C7;">Chọn Quần Áo Tặng Giáo Viên Nữ Nhân Ngày 20/11: Bí Quyết Tạo Ấn Tượng Sâu Sắc</a></li>
                <li><a href="<c:url value='/views/web/blog/blog-2.jsp'/>" style=" text-decoration: none; color: #E693C7;">Cách Mặc Sơ Mi Đúng Cách: Tự Tin Và Lịch Lãm</a></li>
            </ul>
        </div>
    </div>
</body>

</html>