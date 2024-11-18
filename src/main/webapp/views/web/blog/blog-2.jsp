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
                            <a href="<c:url value=" /trang-chu" />">Home</a>
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
            <h2>Cách Mặc Sơ Mi Đúng Cách: Tự Tin Và Lịch Lãm</h2>
        </div>
        <!-- giới thiệu -->
        <div class="my-5">
            <p>Sơ mi là một trong những món đồ không thể thiếu trong tủ đồ của mỗi người, đặc biệt là đối với những
                người yêu thích sự lịch sự và trang trọng. Tuy nhiên, mặc sơ mi đúng cách không phải ai cũng biết. Hãy
                cùng tìm hiểu các bước và mẹo giúp bạn mặc sơ mi một cách hoàn hảo nhất, để không chỉ thể hiện phong
                cách mà còn tạo ấn tượng tốt với người đối diện.</p>
        </div>
        <!-- menu -->
        <div class="container my-4">
            <h4 class="text-danger-emphasis fs-5 mb-2 fw-bold" style="font-family: 'Poppins', sans-serif ;">Mục
                lục bài viết</h4>
            <div class="accordion" id="accordionExample">
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingOne">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            🌟BST đầm đẹp ấn tượng tặng cô ngày 20/11
                        </button>
                    </h2>
                    <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne"
                        data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <ul>
                                <li>🌟Đầm midi caro cổ sơ mi smocking lưng</li>
                                <li>🌟Đầm mini thun tay dài cổ V đính hàng nút</li>
                                <li>🌟Đầm midi tay phồng cổ vuông phối ren đính nơ</li>
                                <li>🌟Đầm mini 2 dây đính nơ in hoa phối ren smocking lưng</li>
                                <li>🌟Đầm ren midi in hoa tay dài phối lá cổ</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingTwo">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            🌟BST quần ống suông làm quà tặng cho cô giáo diện tới trường
                        </button>
                    </h2>
                    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
                        data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <ul>
                                <li>🌟Quần dài ống suông lưng liền phối dây thắt lưng</li>
                                <li>🌟Quần tây ống suông 2 túi nhấn li trước</li>
                                <li>🌟Quần dài ống suông lưng cao 2 túi nhấn li trước</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingThree">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            🌟BST blazer thanh lịch làm món quà ý nghĩa ngày 20/11
                        </button>
                    </h2>
                    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
                        data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <ul>
                                <li>🌟Áo blazer nhún xắn tay cách điệu</li>
                                <li>🌟Áo blazer form cơ bản tay dài 2 túi</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- nội dung -->

        <div class="mt-5">
            <h4>1. Chọn Sơ Mi Phù Hợp Với Dáng Người</h4>
            <p>Mỗi người có một hình dáng cơ thể khác nhau, và sơ mi cần được chọn sao cho phù hợp. Để có thể mặc sơ mi
                đẹp nhất, bạn cần chú ý đến những yếu tố sau:</p>
        </div>
        <div class="mt-5">
            <h4>2. Màu Sắc và Kiểu Dáng Phù Hợp</h4>
            <p>Khi chọn màu sắc và kiểu dáng, hãy ưu tiên những màu nhã nhặn và kiểu dáng thanh lịch. Những màu trung
                tính như trắng, kem, xanh navy, hoặc các tông pastel nhẹ nhàng thường dễ phối đồ và phù hợp với nhiều
                dáng người.</p>
            <h5>- Dáng người mập:</h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-2-1.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Chọn sơ mi có đường cắt ôm nhẹ, tránh các kiểu quá rộng hay
                            quá chật. Sơ mi màu tối hoặc họa tiết nhỏ tạo cảm giác thon gọn, thanh lịch.</p>
                    </div>
                </div>
            </div>
            <p> Chọn sơ mi có đường cắt ôm nhẹ, tránh các loại sơ mi quá rộng hay quá chật. Sơ mi có màu tối hoặc họa
                tiết nhỏ sẽ giúp tạo cảm giác thon gọn hơn.</p>
            <!--  -->
            <br>
            <h5>- Dáng người gầy:</h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-2-2.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Các loại sơ mi cắt rộng rãi với chất liệu vải dày giúp tạo cảm
                            giác đầy đặn. Họa tiết kẻ sọc ngang cũng là lựa chọn hoàn hảo</p>
                    </div>
                </div>
            </div>
            <p> Các loại sơ mi có đường cắt rộng rãi và chất liệu vải dày sẽ giúp tạo cảm giác đầy đặn hơn. Họa tiết kẻ
                sọc ngang cũng là lựa chọn hợp lý.</p>
            <!--  -->
            <h5>- Dáng người cao:</h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-2-3.webp'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Các loại sơ mi cổ đứng hoặc kiểu cài khuy truyền thống giúp
                            tôn dáng cao ráo, thanh thoát.</p>
                    </div>
                </div>
            </div>
            <p> Các loại sơ mi có cổ đứng hoặc kiểu cài khuy truyền thống sẽ giúp bạn tôn lên vóc dáng cao ráo, thanh
                thoát.</p>
        </div>
        <!--  -->
        <div class="mt-5">
            <h4>2. Chú Ý Đến Kích Cỡ Sơ Mi</h4>
            <p>Một trong những yếu tố quan trọng nhất khi mặc sơ mi là chọn đúng kích cỡ. Một chiếc sơ mi quá rộng sẽ
                khiến bạn trông luộm thuộm, trong khi sơ mi quá chật sẽ tạo cảm giác khó chịu và kém thanh thoát.</p>
            <h5>- Cổ áo:</h5>
            <p>Khi cài khuy, bạn có thể thử dùng một ngón tay để kiểm tra xem cổ áo có chật không. Nếu bạn cảm thấy khó
                thở hoặc không thể dễ dàng di chuyển cổ, sơ mi đó quá chật.</p>
            <h5>- Độ dài tay áo: </h5>
            <p>Tay áo của sơ mi nên dài tới cổ tay của bạn khi thả lỏng cánh tay. Nếu tay áo quá dài hoặc quá ngắn, nó
                sẽ ảnh hưởng đến sự chỉnh chu của trang phục.</p>
            <h5>- Độ dài của áo:</h5>
            <p>Khi mặc sơ mi bên ngoài quần, chiều dài của áo nên vừa đủ để bạn có thể bỏ vào quần mà không gặp khó
                khăn.Khi mặc sơ mi bên ngoài quần, chiều dài của áo nên vừa đủ để bạn có thể bỏ vào quần mà không gặp
                khó khăn.</p>
        </div>
        <!--  -->
        <div class="mt-5">
            <h4>3. Cách Cài Khuy Và Gấp Tay Áo</h4>
            <h5>- Cài khuy đúng cách: </h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-2-4.webp'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Cài đầy đủ khuy áo để giữ vẻ chỉnh chu</p>
                    </div>
                </div>
            </div>
            <p>Hãy đảm bảo rằng tất cả các khuy của sơ mi đều được cài đầy đủ. Một chiếc sơ mi không cài khuy đúng cách
                sẽ khiến bạn trông thiếu chỉnh chu.</p>
            <h5>- Gấp tay áo:</h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-2-5.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Ngày hè oi ả, hãy xắn tay áo vừa đủ để trông năng động và gọn
                            gàng.</p>
                    </div>
                </div>
            </div>
            <p> Đối với những ngày hè oi ả, bạn có thể xắn tay áo lên để tạo vẻ năng động, nhưng đừng xắn quá cao. Gấp
                tay áo sao cho vừa đủ, không quá ngắn và cũng không quá dài, tạo cảm giác gọn gàng.</p>
        </div>
        <!--  -->
        <div class="mt-5">
            <h4>4. Kết Hợp Với Phụ Kiện Phù Hợp</h4>
            <p>Sơ mi có thể rất đơn giản nếu không có phụ kiện đi kèm. Tuy nhiên, một vài phụ kiện nhỏ có thể tạo điểm
                nhấn và nâng tầm phong cách của bạn.</p>
            <h5>- Đồng hồ và thắt lưng: </h5>
            <p>Đồng hồ và thắt lưng là những phụ kiện không thể thiếu để tạo nên một phong cách lịch lãm. Hãy chọn những
                chiếc đồng hồ thanh thoát, không quá to để phù hợp với sơ mi của bạn.</p>
            <h5>- Cà vạt:</h5>
            <p> Nếu bạn đang mặc sơ mi trong môi trường công sở, đừng quên kết hợp với một chiếc cà vạt thanh lịch. Chọn
                cà vạt có màu sắc phù hợp với sơ mi và trang phục tổng thể.</p>
        </div>
        <!--  -->
        <div class="mt-5">
            <h4>5. Chọn Vải Và Màu Sắc Phù Hợp</h4>
            <h5>- Màu sắc:</h5>
            <p> Màu sắc của sơ mi nên phù hợp với hoàn cảnh và phong cách cá nhân. Những màu cơ bản như trắng,
                xanh dương, và đen luôn là lựa chọn an toàn. Tuy nhiên, bạn cũng có thể thử những màu sắc tươi sáng hơn
                nếu muốn thể hiện cá tính riêng.</p>
            <h5>- Chất liệu vải:</h5>
            <p> Vải sơ mi có nhiều loại, nhưng hai chất liệu phổ biến nhất là cotton và linen. Vải cotton
                mang lại cảm giác thoải mái, thoáng mát, trong khi vải linen thích hợp cho mùa hè vì độ thoáng khí cao.
                Nếu bạn muốn có vẻ ngoài trang trọng, sơ mi vải lụa hoặc satin là sự lựa chọn tuyệt vời.</p>
        </div><!--  -->
        <div class="mt-5">
            <h4>6. Cách Mặc Sơ Mi Khi Đi Làm Và Dự Tiệc</h4>
            <h5>- Đi làm: </h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-2-6.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Chọn sơ mi màu trung tính hoặc họa tiết đơn giản, kết hợp với
                            quần tây hoặc jeans để vừa chuyên nghiệp, vừa thoải mái.</p>
                    </div>
                </div>
            </div>
            <p>Khi mặc sơ mi đến công sở, hãy chọn những chiếc sơ mi có màu sắc trung tính hoặc họa tiết đơn
                giản. Cách phối hợp với quần tây hoặc quần jeans sẽ giúp bạn có vẻ ngoài vừa chuyên nghiệp lại vẫn thoải
                mái.</p>
            <h5>- Dự tiệc: </h5>
            <div class="d-flex justify-content-center mt-5">
                <div class="card" style="width: 32rem; border:none; ">
                    <img src="<c:url value='/static/img/blog/blog-2-7.jpg'/>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="text-muted text-center">Chọn sơ mi màu nổi hoặc vải cao cấp, kết hợp với vest lịch lãm
                            để tạo ấn tượng nổi bật.</p>
                    </div>
                </div>
            </div>
            <p> Nếu bạn tham dự một buổi tiệc hoặc sự kiện trang trọng, đừng ngần ngại thử những chiếc sơ mi có
                màu sắc nổi bật hoặc chất liệu vải cao cấp. Kết hợp với bộ vest lịch lãm sẽ giúp bạn nổi bật trong đám
                đông.</p>
        </div>
        <div class="mt-5">
            <h4>7. Chăm Sóc Sơ Mi Cẩn Thận</h4>
            <p>Cuối cùng, đừng quên chăm sóc sơ mi của bạn để luôn giữ được vẻ đẹp lâu dài. Hãy giặt sơ mi bằng tay
                hoặc sử dụng chế độ giặt nhẹ trên máy giặt. Đảm bảo là sơ mi khô tự nhiên thay vì dùng máy sấy để vải
                không bị co rút.</p>
        </div>
        <!-- tin lien quan -->
        <div class="related-news mb-5" style="font-family: 'Poppins', sans-serif;">
            <h4 class="text-danger-emphasis fs-5 mb-2 fw-bold">Tin tức liên quan</h4>
            <ul>
                <li><a href="<c:url value='/views/web/blog/blog-6.jsp'/>" style=" text-decoration: none; color: #E693C7;">Phối đồ nơi công sở: Tạo phong cách chuyên nghiệp và lịch sự</a></li>
                <li><a href="<c:url value='/views/web/blog/blog-5.jsp'/>" style=" text-decoration: none; color: #E693C7;">Sản Xuất Thời Trang Bảo Vệ Môi Trường: Xu Hướng Bền Vững Cho Tương Lai</a></li>
                <li><a href="<c:url value='/views/web/blog/blog-4.jsp'/>" style=" text-decoration: none; color: #E693C7;">  Thời Trang Thể Thao: Sự Kết Hợp Hoàn Hảo Giữa Phong Cách và Tiện Ích</a></li>
                <li><a href="<c:url value='/views/web/blog/blog-3.jsp'/>" style=" text-decoration: none; color: #E693C7;">Phối Màu Quần Áo: Nghệ Thuật Tạo Dựng Phong Cách Cá Nhân</a></li>
                <li><a href="<c:url value='/views/web/blog/blog-1.jsp'/>" style=" text-decoration: none; color: #E693C7;">Chọn Quần Áo Tặng Giáo Viên Nữ Nhân Ngày 20/11: Bí Quyết Tạo Ấn Tượng Sâu Sắc</a></li>
            </ul>
        </div>
    </div>
</body>

</html>