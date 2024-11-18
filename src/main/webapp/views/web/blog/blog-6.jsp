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
                Phối đồ nơi công sở: Tạo phong cách chuyên nghiệp và lịch sự</h2>
        </div>
        <!-- giới thiệu -->
        <div class="my-5">
            <p>Công sở không chỉ là nơi làm việc mà còn là nơi thể hiện phong cách cá nhân thông qua trang phục. Việc
                lựa chọn trang phục phù hợp sẽ giúp bạn tự tin, chuyên nghiệp và ghi điểm trong mắt đồng nghiệp cũng như
                cấp trên. Dưới đây là một số gợi ý về cách phối đồ nơi công sở, vừa lịch sự lại vẫn thể hiện được phong
                cách cá nhân.</p>
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
                                🌟 Trang phục cơ bản cho nam giới
                            </button>
                        </h2>
                    </a>
                </div>
                <div class="accordion-item">
                    <a href="#headingOne" style="text-decoration: none;">
                        <h2 class="accordion-header">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                🌟Trang phục cơ bản cho nữ giới
                            </button>
                        </h2>
                    </a>
                </div>
                <div class="accordion-item">
                    <a href="#headingTwo" style="text-decoration: none;">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                🌟 Lựa chọn màu sắc phù hợp
                            </button>
                        </h2>
                    </a>
                </div>
                <div class="accordion-item">
                    <a href="#headingThree" style="text-decoration: none;">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                🌟 Chú trọng đến chất liệu vải
                            </button>
                        </h2>
                    </a>
                </div>
                <div class="accordion-item">
                    <a href="#headingFour" style="text-decoration: none;">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                🌟 Thêm phụ kiện nhẹ nhàng
                            </button>
                        </h2>
                    </a>
                </div>
                <div class="accordion-item">
                    <a href="#headingFive" style="text-decoration: none;">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                🌟 Phong cách cá nhân trong công sở
                            </button>
                        </h2>
                    </a>
                </div>
                <div class="accordion-item">
                    <a href="#headingSix" style="text-decoration: none;">
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
            <h4 id="headingZero">1. Trang phục cơ bản cho nam giới</h4>
            <h5>- Áo sơ mi:</h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-6-1.webp'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Trang phục cổ điển, thường có cổ và cúc, phù hợp cho cả môi
                            trường công sở và dạo phố, mang đến vẻ lịch sự và thanh lịch.</p>
                    </div>
                </div>
            </div>
            <p> Đây là item không thể thiếu trong tủ đồ của các chàng trai công sở. Bạn có thể chọn áo sơ mi
                trắng cổ điển hoặc sơ mi có họa tiết nhẹ nhàng. Sơ mi cổ điển kết hợp với một chiếc áo vest hoặc blazer
                sẽ mang đến vẻ ngoài lịch sự, trang trọng.</p>
            <h5>- Quần âu:</h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-6-2.webp'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Quần dài được cắt may tinh tế, thường làm từ vải mềm, thích
                            hợp cho các dịp trang trọng và môi trường công sở, tạo vẻ ngoài chỉn chu và lịch sự..</p>
                    </div>
                </div>
            </div>
            <p> Quần âu là sự lựa chọn hoàn hảo để kết hợp với sơ mi và áo vest. Chọn quần có màu sắc trung tính như
                đen, xám hoặc xanh navy giúp bạn dễ dàng phối hợp với các items khác.

            </p>
            <h5>- Giày da:</h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-6-3.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Giày làm từ chất liệu da, thường mang lại vẻ sang trọng và bền
                            bỉ, thích hợp cho các dịp trang trọng và môi trường công sở.</p>
                    </div>
                </div>
            </div>
            <p> Giày da chính là điểm nhấn cho bộ trang phục công sở. Những đôi giày màu đen hoặc nâu sẽ rất phù hợp với
                không gian làm việc chuyên nghiệp.</p>
            <h5>- Đồng hồ:</h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-6-4.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Phụ kiện đeo tay giúp theo dõi thời gian, đồng thời là món đồ
                            thời trang thể hiện phong cách và sự tinh tế.</p>
                    </div>
                </div>
            </div>
            <p> Một chiếc đồng hồ đơn giản nhưng sang trọng sẽ giúp bạn thể hiện sự tinh tế trong phong cách.</p>
        </div>
        <div class="mt-5">
            <h4 id="headingOne">2. Trang phục cơ bản cho nữ giới</h4>
            <h5>- Áo sơ mi và chân váy:</h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-6-5.webp'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Bộ trang phục kết hợp giữa sự thanh lịch của áo sơ mi và sự nữ
                            tính của chân váy, phù hợp cho cả môi trường công sở và các dịp trang trọng.</p>
                    </div>
                </div>
            </div>
            <p> Một chiếc áo sơ mi kết hợp với chân váy là sự lựa chọn phổ biến và phù hợp cho môi trường công sở. Chân
                váy có thể là dáng ôm hoặc dáng xòe tùy theo sở thích và thể hình của mỗi người. Để tạo sự cân đối, bạn
                có thể chọn áo sơ mi đơn sắc và kết hợp cùng các phụ kiện nhỏ nhắn như vòng cổ, vòng tay.</p>
            <h5>- Áo blazer và quần âu:</h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-6-6.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Bộ trang phục tinh tế, kết hợp giữa áo blazer lịch lãm và quần
                            âu trang nhã, tạo nên phong cách chuyên nghiệp và sang trọng, thích hợp cho môi trường công
                            sở và các sự kiện trang trọng.</p>
                    </div>
                </div>
            </div>
            <p> Blazer là một trong những trang phục thể hiện sự chuyên nghiệp nhất. Bạn có thể kết hợp với quần âu để
                tạo nên một bộ trang phục vừa lịch sự lại vẫn thể hiện được phong cách cá nhân.

            </p>
            <h5>- Váy công sở:</h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-6-7.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Trang phục thanh lịch, thiết kế đơn giản nhưng chuyên nghiệp,
                            phù hợp cho môi trường làm việc, mang đến vẻ ngoài trang nhã và dễ chịu.</p>
                    </div>
                </div>
            </div>
            <p> Váy là lựa chọn tuyệt vời cho những ngày bạn muốn tạo vẻ nữ tính nhưng vẫn đảm bảo sự thanh lịch. Váy
                xếp ly hoặc váy midi là những lựa chọn an toàn cho môi trường công sở.</p>
            <h5>- Giày cao gót hoặc giày bệt:</h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-6-8.jpeg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Giày cao gót tạo sự thanh thoát và sang trọng, trong khi giày
                            bệt mang đến sự thoải mái và dễ di chuyển, cả hai đều phù hợp với nhiều phong cách và dịp
                            khác nhau.</p>
                    </div>
                </div>
            </div>
            <p> Giày cao gót sẽ giúp bạn có một vóc dáng thanh thoát, trong khi giày bệt sẽ mang lại sự thoải mái cho
                những ngày làm việc dài.</p>
        </div>
        <!--  -->
        <div class="mt-5">
            <h4 id="headingThree">3. Lựa chọn màu sắc phù hợp</h4>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-6-9.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Chọn màu sắc trang phục sao cho hài hòa với tone da, phong
                            cách cá nhân và dịp sử dụng, giúp tôn lên vẻ đẹp và tạo sự cân đối, dễ chịu cho người mặc.
                        </p>
                    </div>
                </div>
            </div>
            <p>
                Màu sắc đóng vai trò quan trọng trong việc tạo nên ấn tượng đầu tiên. Với môi trường công sở, các màu
                sắc trung tính như trắng, đen, xám, be luôn là lựa chọn hàng đầu. Những màu sắc này mang lại vẻ ngoài
                nghiêm túc và dễ dàng phối hợp với các trang phục khác. Tuy nhiên, bạn cũng có thể chọn các màu sắc nhẹ
                nhàng như pastel hoặc những màu sắc tươi sáng để làm nổi bật cá tính mà không làm mất đi sự trang trọng.
            </p>
        </div>
        <!--  -->
        <div class="mt-5">
            <h4 id="headingThree">4. Chú trọng đến chất liệu vải</h4>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-6-10.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Lựa chọn vải phù hợp với mùa, tính chất công việc và mục đích
                            sử dụng, đảm bảo sự thoải mái, bền bỉ và tạo vẻ ngoài sang trọng.</p>
                    </div>
                </div>
            </div>
            <p>
                Chất liệu vải cũng là một yếu tố quan trọng khi chọn trang phục công sở. Các chất liệu như cotton,
                linen, wool và polyester là những lựa chọn phổ biến vì chúng mang lại cảm giác thoải mái, dễ chịu và bền
                đẹp. Hãy chọn chất liệu vải thoáng khí, không nhăn và dễ bảo quản để đảm bảo sự chuyên nghiệp trong suốt
                cả ngày làm việc.
            </p>
        </div>
        <div class="mt-5">
            <h4 id="headingFour">5. Thêm phụ kiện nhẹ nhàng</h4>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-6-11.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center"> Sử dụng phụ kiện đơn giản như đồng hồ, dây chuyền hoặc túi
                            xách nhỏ để làm điểm nhấn, tăng thêm phong cách mà không làm mất đi sự thanh thoát của trang
                            phục.</p>
                    </div>
                </div>
            </div>
            <p>Phụ kiện là điểm nhấn giúp bộ trang phục trở nên hoàn hảo hơn. Tuy nhiên, bạn cần chú ý chọn lựa những
                phụ kiện đơn giản và tinh tế để không làm mất đi vẻ trang trọng. Những chiếc túi xách da, đồng hồ thanh
                lịch, hay những chiếc khăn quàng nhỏ xinh sẽ là lựa chọn tuyệt vời để thêm phần nổi bật.
            </p>
        </div>
        <div class="mt-5">
            <h4 id="headingFive">6. Phong cách cá nhân trong công sở</h4>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-6-12.webp'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Lựa chọn trang phục và phụ kiện phản ánh cá tính, nhưng vẫn
                            giữ sự chuyên nghiệp và phù hợp với môi trường làm việc, giúp tạo ấn tượng tốt và thể hiện
                            sự tự tin.</p>
                    </div>
                </div>
            </div>
            <p>Mặc dù môi trường công sở yêu cầu sự chuyên nghiệp, nhưng điều này không có nghĩa là bạn không thể thể
                hiện phong cách cá nhân của mình. Hãy thử những trang phục có thiết kế đặc biệt hoặc màu sắc nổi bật hơn
                một chút, miễn là chúng phù hợp với văn hóa công ty và tạo ra vẻ ngoài gọn gàng, thanh lịch.
            </p>
        </div>
        <!--  -->
        <div class="mt-5">
            <h4 id="headingSix">Lời Kết</h4>
            <p>Phối đồ công sở không chỉ là việc lựa chọn trang phục mà còn là cách bạn thể hiện bản thân và tạo ấn
                tượng đầu tiên với đồng nghiệp và cấp trên. Để có phong cách hoàn hảo, bạn cần lưu ý chọn những trang
                phục phù hợp với môi trường làm việc, không quá cầu kỳ nhưng vẫn phải thể hiện được sự chuyên nghiệp. Hy
                vọng rằng những gợi ý trên sẽ giúp bạn xây dựng được bộ sưu tập trang phục công sở phù hợp, mang đến sự
                tự tin và năng lượng tích cực trong công việc.</p>
        </div>
        <!-- tin lien quan -->
        <div class="related-news mb-5" style="font-family: 'Poppins', sans-serif;">
            <h4 class="text-danger-emphasis fs-5 mb-2 fw-bold">Tin tức liên quan</h4>
            <ul>
                <li><a href="<c:url value='/views/web/blog/blog-1.jsp'/>" style=" text-decoration: none; color: #E693C7;">Chọn Quần Áo Tặng Giáo Viên Nữ Nhân Ngày 20/11: Bí Quyết Tạo Ấn Tượng Sâu Sắc</a></li>
                <li><a href="<c:url value='/views/web/blog/blog-5.jsp'/>" style=" text-decoration: none; color: #E693C7;">Sản Xuất Thời Trang Bảo Vệ Môi Trường: Xu Hướng Bền Vững Cho Tương Lai</a></li>
                <li><a href="<c:url value='/views/web/blog/blog-4.jsp'/>" style=" text-decoration: none; color: #E693C7;">  Thời Trang Thể Thao: Sự Kết Hợp Hoàn Hảo Giữa Phong Cách và Tiện Ích</a></li>
                <li><a href="<c:url value='/views/web/blog/blog-3.jsp'/>" style=" text-decoration: none; color: #E693C7;">Phối Màu Quần Áo: Nghệ Thuật Tạo Dựng Phong Cách Cá Nhân</a></li>
                <li><a href="<c:url value='/views/web/blog/blog-2.jsp'/>" style=" text-decoration: none; color: #E693C7;">Cách Mặc Sơ Mi Đúng Cách: Tự Tin Và Lịch Lãm</a></li>
            </ul>
        </div>
    </div>
</body>

</html>