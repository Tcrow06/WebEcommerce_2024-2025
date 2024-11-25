<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../../common/web/slider.jsp"%>

<%@ include file="../../common/web/banner.jsp"%>

<style>
    .product__item__pic {
        position: relative; /* Để chứa các thẻ con có position absolute */
    }

    .div_label_product {
        position: absolute;
        left: 10px;
        font-size: 14px;
        color: #fff;
        padding: 5px 10px;
        border-radius: 5px;
    }

    /* Nhãn Sale ở phía trên */
    .div_label_product:nth-child(1) {
        top: 10px; /* Nhãn Sale nằm ở trên cùng */
    }

    /* Nhãn New ở phía dưới */
    .div_label_product:nth-child(2) {
        top:40px; /* Nhãn New nằm ở dưới cùng */
    }

    .categories__hot__deal {
        position: relative;
    }
    .hot__deal__sticker {
        position: absolute;
        top: 0;
        right: 0;
        z-index: 10; /* Giúp cho sticker nằm trên các thành phần khác */
    }

    .discounted-price {
        text-decoration: line-through;  /* Gạch ngang */
        font-size: 0.9em;               /* Giảm kích thước font */
        color: gray;                   /* Làm mờ màu sắc */
        opacity: 0.6;                  /* Làm mờ thêm */
    }

</style>
<!-- Product Section Begin -->
<section class="product spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <ul class="filter__controls">
                    <li class="active" data-filter=".sale">Sales</li>
                    <li data-filter=".new">News</li>
                    <li data-filter=".other">Other</li>
                </ul>
            </div>
        </div>
        <div class="row product__filter">
            <c:forEach var="item" items="${results}">
                <c:set var="saleNewClass" value=" " />
                <c:if test="${item.productDiscount != null}">
                    <c:set var="saleNewClass" value="${saleNewClass} sale " />
                </c:if>
                <c:if test="${item.isNew}">
                    <c:set var="saleNewClass" value="${saleNewClass} new " />
                </c:if>
                <c:if test="${item.productDiscount == null && !item.isNew}">
                    <c:set var="saleNewClass" value="${saleNewClass} other " />
                </c:if>
                <div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix ${saleNewClass}">
                    <div class="product__item sale">


                        <div class="product__item__pic set-bg" data-setbg="<c:url value='/api-image?path=${item.photo}'/>">
                            <c:if test="${item.productDiscount != null}">
                                <div class="div_label_product"><span class="label">Sale_${item.productDiscount.discountPercentage}%</span></div>
                            </c:if>
                            <c:if test="${item.isNew}">
                                <div class="div_label_product"><span class="label">New</span></div>
                            </c:if>
                            <ul class="product__hover">
                                <li><a href="#"><img src="<c:url value='/static/img/icon/heart.png'/>" alt=""></a></li>
                                <li><a href="#"><img src="<c:url value='/static/img/icon/compare.png'/>" alt=""> <span>Compare</span></a></li>
                                <li><a href="#"><img src="<c:url value='/static/img/icon/search.png'/>" alt=""></a></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6>${item.name}</h6>
                            <a href="<c:url value='san-pham?id=${item.id}'/> " class="add-cart">View Detail</a>
                            <div class="rating">
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                            </div>
<%--                            <h5>$ ${item.price}</h5>--%>
                            <h5>${item.getDiscountedPrice()}
                                <c:if test="${item.productDiscount != null}">
                                    <span class="discounted-price">${item.price}</span>
                                </c:if>
                            </h5>
<%--                            đây nha--%>
                            <div class="product__color__select">
                                <label for="pc-1">
                                    <input type="radio" id="pc-1">
                                </label>
                                <label class="active black" for="pc-2">
                                    <input type="radio" id="pc-2">
                                </label>
                                <label class="grey" for="pc-3">
                                    <input type="radio" id="pc-3">
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
<!-- Product Section End -->


<c:forEach var="item" items="${results}">
    <c:if test="${item.productDiscount != null}">
        <c:set var="saleNewClass" value="${saleNewClass} sale " />
    </c:if>
</c:forEach>


<section class="latest spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title">
                    <span>Giảm giá đặc biệt</span>
                    <h2>Giảm giá cho từng đơn hàng</h2>
                </div>
            </div>
                <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <c:set var="isFirstActive" value="true" />  <!-- Khởi tạo biến kiểm tra item đầu tiên -->

                        <c:forEach var="item" items="${model}" varStatus="status">
                            <c:if test="${item.isOutStanding == true}">
                                <div class="carousel-item ${isFirstActive == true ? 'active' : ''}">
                                    <section class="instagram spad" style="padding: 0px">
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-lg-8">
                                                    <div class="categories__hot__deal">
                                                        <div style="padding: 50px">
                                                            <c:set var="imageIndex" value="${status.index % 3}" />
                                                            <img src="<c:url value='/static/img/instagram/ig-${imageIndex}.jpg'/>" alt="">
                                                            <div class="hot__deal__sticker">
                                                                <span>Giảm</span>
                                                                <h5>${item.discountPercentage}%</h5>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4">
                                                    <div class="instagram__text">
                                                        <span>Chỉ dành cho thành viên trên ${item.loyaltyPointsRequired} điểm !</span>
                                                        <h3>Giảm giá cho đơn hàng trên ${item.minimumInvoiceAmount} VND</h3>
                                                        <p>Giảm tối thiểu ${item.maximumAmount} VND.</p>

                                                        <span class="badge badge-${item.getBootstrapClassStatus()} rounded-pill d-inline">${item.getStatus()}</span>

                                                        <div class="instagram__text" style="padding: 0">
                                                            <span>Bắt đầu vào ngày: </span>
                                                            <h2>${item.getStringStartDate()}</h2>
                                                        </div>
                                                        <div class="instagram__text" style="padding: 0">
                                                            <span>Kết thúc vào ngày: </span>
                                                            <h2>${item.getStringEndDate()}</h2>
                                                        </div>
                                                        <p>Nhanh tay sử dụng nào !</p>
                                                        <h2>#Male_Fashion</h2>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </div>
                                <!-- Đặt biến isFirstActive thành false sau khi lần đầu tiên item được chọn -->
                                <c:set var="isFirstActive" value="false" />
                            </c:if>
                        </c:forEach>
                    </div>

                    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>

        </div>
    </div>
</section>


<section class="latest spad" style="margin-top: 100px">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title">
                    <span>Giảm giá đặc biệt</span>
                    <h2>Sản phẩm đang được đặc biệt giảm giá</h2>
                </div>
            </div>
        </div>
        <div id="carouselExampleControlss" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <c:set var="isFirstActive" value="true" />  <!-- Khởi tạo biến kiểm tra item đầu tiên -->

                <c:forEach var="item" items="${results}" varStatus="status">
                    <c:if test="${item.productDiscount != null}">
                        <c:if test="${item.productDiscount.isOutStanding == true}">
                            <div class="carousel-item ${isFirstActive == true ? 'active' : ''}">
                                <section class="categories spad">
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-lg-3">
                                                <div class="categories__text">
                                                    <h2>Clothings<br /> <span>Products are discounted</span> <br /> Accessories <br /> Shoe</h2>
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="categories__hot__deal">
                                                    <img src="<c:url value='/api-image?path=${item.photo}'/>" alt="">
                                                    <div class="hot__deal__sticker">
                                                        <span>Sale Of</span>
                                                        <h5>${item.getDiscountedPrice()}</h5>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 offset-lg-1">
                                                <div class="categories__deal__countdown">
                                                    <span>Giảm giá đặc biệt</span>
                                                    <h2>${item.name}</h2>
                                                    <h5>Giá gốc: $${item.price}</h5>
                                                    <span style="margin-top: 10px">${item.productDiscount.discountPercentage}% giảm</span>
                                                    <c:if test="${status.index == 0}">
                                                        <div class="categories__deal__countdown__timer" id="countdown">
                                                            <div class="cd-item">
                                                                <span>${item.productDiscount.getRemainingDates()}</span>
                                                                <p>Days</p>
                                                            </div>
                                                            <div class="cd-item">
                                                                <span>${item.productDiscount.getRemainingHours()}</span>
                                                                <p>Hours</p>
                                                            </div>
                                                            <div class="cd-item">
                                                                <span>${item.productDiscount.getRemainingMinutes()}</span>
                                                                <p>Minutes</p>
                                                            </div>
                                                            <div class="cd-item">
                                                                <span>${item.productDiscount.getRemainingSeconds()}</span>
                                                                <p>Seconds</p>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${status.index != 0}">
                                                        <div class="instagram__text" style="padding: 0">
                                                            <span>Kết thúc vào ngày: </span>
                                                            <h2>${item.productDiscount.getStringEndDate()}</h2>
                                                        </div>
                                                    </c:if>
                                                    <a href="/san-pham?id=${item.id}" class="primary-btn">Mua ngay</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </div>
                            <!-- Đặt biến isFirstActive thành false sau khi lần đầu tiên item được chọn -->
                            <c:set var="isFirstActive" value="false" />
                        </c:if>
                    </c:if>
                </c:forEach>
            </div>

            <a class="carousel-control-prev" href="#carouselExampleControlss" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleControlss" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>

    </div>
</section>



<!-- Instagram Section Begin -->
<section class="instagram spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div class="instagram__pic">
                    <div class="instagram__pic__item set-bg" data-setbg="<c:url value="/static/img/instagram/instagram-1.jpg"/>"></div>
                    <div class="instagram__pic__item set-bg" data-setbg="<c:url value="/static/img/instagram/instagram-2.jpg"/>"></div>
                    <div class="instagram__pic__item set-bg" data-setbg="<c:url value="/static/img/instagram/instagram-3.jpg"/>"></div>
                    <div class="instagram__pic__item set-bg" data-setbg="<c:url value="/static/img/instagram/instagram-4.jpg"/>"></div>
                    <div class="instagram__pic__item set-bg" data-setbg="<c:url value="/static/img/instagram/instagram-5.jpg"/>"></div>
                    <div class="instagram__pic__item set-bg" data-setbg="<c:url value="/static/img/instagram/instagram-6.jpg"/>"></div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="instagram__text">
                    <h2>Instagram</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                        labore et dolore magna aliqua.</p>
                    <h3>#Male_Fashion</h3>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Instagram Section End -->

<!-- Latest Blog Section Begin -->
<section class="latest spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title">
                    <span>Latest News</span>
                    <h2>Fashion New Trends</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 col-md-6 col-sm-6">
                <div class="blog__item">
                    <div class="blog__item__pic set-bg" data-setbg="<c:url value='/static/img/blog/blog-m-4.jpg'/>"></div>
                    <div class="blog__item__text">
                        <span><img src="<c:url value='/static/img/blog/blog-m-4.jpg'/>" alt="">11/09/2024</span>
                        <h5>Thời Trang Thể Thao: Sự Kết Hợp Hoàn Hảo Giữa Phong Cách và Tiện
                                                                Ích</h5>
                        <a href="<c:url value='/views/web/blog/blog-4.jsp'/>">Read More</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6">
                <div class="blog__item">
                    <div class="blog__item__pic set-bg" data-setbg="<c:url value='/static/img/blog/blog-m-2.jpg'/>"></div>
                    <div class="blog__item__text">
                        <span><img src="<c:url value='/static/img/blog/blog-m-2.jpg'/>" alt=""> 11/09/2024</span>
                        <h5>Để Tôi Chỉ Cho Cách Mặc Sơ Mi Đúng Cách: Tự Tin Và Lịch Lãm</h5>
                        <a href="<c:url value='/views/web/blog/blog-2.jsp'/>">Read More</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6">
                <div class="blog__item">
                    <div class="blog__item__pic set-bg" data-setbg="<c:url value='/static/img/blog/blog-m-3.jpg'/>"></div>
                    <div class="blog__item__text">
                        <span><img src="<c:url value='/static/img/blog/blog-m-3.jpg'/>" alt=""> 11/09/2024</span>
                        <h5>Phối Màu Quần Áo: Nghệ Thuật Tạo Dựng Phong Cách Cá Nhân</h5>
                        <a href="<c:url value='/views/web/blog/blog-3.jsp'/>">Read More</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Latest Blog Section End -->

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        $(".product__filter .mix").not(".sale").hide();
    });

</script>


<script>
    const url = new URL(window.location.href);

    const message = url.searchParams.get("message");
    const alertType = url.searchParams.get("alert");

    if (message && alertType) {
        if(message==="not_permission_access"){
            alert("Không được phép truy cập");
        }
        // Xóa các tham số từ URL
        url.searchParams.delete("message");
        url.searchParams.delete("alert");

        // Chuyển hướng đến URL mới (không có tham số)
        window.history.replaceState({}, document.title, url.pathname);

    }
</script>

