<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

    .pagination {
        display: flex;
        justify-content: center;
    }

    .pagination li {
        display: inline-block;
        margin-right: 5px;
    }

    .pagination li.active a {
        color: white !important; /* Màu chữ khi trang đang được chọn */
        background-color: black !important; /* Màu nền khi trang được chọn */
    }

    .pagination li.active {
        background-color: black !important; /* Màu nền của ô active */
    }

    .pagination li a {
        color: black !important; /* Thay đổi màu chữ thành đen */
    }

    .pagination li:hover a {
        color: black !important; /* Màu chữ khi hover */
    }

    .discounted-price {
        text-decoration: line-through;  /* Gạch ngang */
        font-size: 0.9em;               /* Giảm kích thước font */
        color: darkgray;                   /* Làm mờ màu sắc */
        opacity: 0.6;                  /* Làm mờ thêm */
    }


    .suggestions-dropdown {
        position: absolute;
        background-color: #fff;
        border: 1px solid #ddd;
        width: 100%;
        max-height: 200px;
        overflow-y: auto;
        z-index: 10;
    }
    .dropdown-item {
        padding: 5px 10px;
        cursor: pointer;
    }
    .dropdown-item:hover {
        background-color: #f0f0f0;
    }


</style>

<style>

    /* Lightbox container (ẩn mặc định) */
    #lightbox {
        display: none;
        position: fixed;
        z-index: 1000;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.8); /* Nền mờ đen */
    }

    /* Close button */
    .close {
        position: absolute;
        top: 20px;
        right: 35px;
        color: #fff;
        font-size: 30px;
        font-weight: bold;
        cursor: pointer;
    }

    /* Lightbox image */
    .lightbox-content {
        display: block;
        max-width: 90%;
        max-height: 90%;
        margin: auto;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }


    .product__details__tab__content__image {
        display: flex;
        justify-content: center;
        margin-top: 15px;
    }

    .product__details__tab__content__image img {
        max-width: 100%;
        height: auto;
        border: 1px solid #ddd;
        border-radius: 5px;
        padding: 10px;
    }

    /* CSS cho radio button khi ở trạng thái disabled */
    input[type="radio"]:disabled + label {
        opacity: 0.5;  /* Giảm độ trong suốt */
        pointer-events: none; /* Ngăn không cho người dùng tương tác */
        color: gray; /* Thay đổi màu chữ để biểu thị trạng thái không khả dụng */
    }

    .btn-check:disabled + .btn {
        opacity: 0.5; /* Giảm độ trong suốt cho button */
        cursor: not-allowed; /* Thay đổi con trỏ chuột khi hover */
    }


    .size-options label {
        padding: 10px 15px;
        margin: 0;
        border: 1px solid #ddd;
        cursor: pointer;
        transition: background-color 0.3s ease;
        font-weight: bold; /* Đặt màu chữ đậm */
    }

    .size-options input[type="radio"] {
        display: none;
    }

    .size-options input[type="radio"]:checked + label {
        background-color: #0e0d0d;
        color: #fff;
        font-weight: bold; /* Làm chữ đậm */
    }

    .size-options label:not(:last-child) {
        border-right: none;
    }

    <c:if test="${model.status == 'STOP_SELLING'}">
        #detail-image {
            display: block;
            width: 100%;
            height: auto;
        }

        .product__details__pic__item {
            position: relative; /* Làm gốc tham chiếu cho phần tử con dùng position: absolute */
        }

        .product__details__pic__item .overlay {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 150px;
            height: 150px;
            background-color: rgba(0, 0, 0, 0.7); /* Màu nền trong suốt */
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #fff; /* Màu chữ trắng */
            font-size: 16px;
            font-weight: bold;
            text-align: center;
            z-index: 1; /* Đảm bảo lớp phủ hiển thị phía trên hình ảnh */
            pointer-events: none; /* Không chặn các tương tác chuột */
        }
    </c:if>

</style>

<!-- Shop Details Section Begin -->
<section class="shop-details">
    <div class="product__details__pic">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="product__details__breadcrumb">
                        <a href="<c:url value="/trang-chu"/>">Home</a>
                        <a href="<c:url value="/san-pham"/>">Shop</a>
                        <span>Product Details</span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <ul class="nav nav-tabs" role="tablist">
                        <c:forEach var="item" items="${model.productVariants}" varStatus="status">
                            <li class="nav-item">
                                <a class="nav-link <c:if test="${status.index == 0}">active</c:if>"
                                   data-toggle="tab" href="#tabs-${status.index + 1}"
                                   role="tab"
                                   data-image="<c:url value='/api-image?path=${item.imageUrl}'/>">
                                    <div class="product__thumb__pic set-bg" data-setbg="<c:url value='/api-image?path=${item.imageUrl}'/>">
                                    </div>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>

                <div class="col-lg-6 col-md-9 img-detail-container">
                    <div class="tab-content">
                        <c:forEach var="item" items="${model.productVariants}" varStatus="status">
                            <div class="tab-pane <c:if test="${status.index == 0}">active</c:if>" id="tabs-${status.index + 1}" role="tabpanel">
                                <div class="product__details__pic__item">
                                    <img src="<c:url value='/api-image?path=${item.imageUrl}'/>" alt="" id="detail-image">
                                    <c:if test="${model.status == 'STOP_SELLING'}">
                                        <div class="overlay">Đã ngừng<br>kinh doanh</div>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="product__details__content">
        <div class="container">
            <div class="row d-flex justify-content-center">
                <div class="col-lg-8">
                    <div class="product__details__text">
                        <h4>${model.name}</h4>

                        <div class="rating">
                            <c:forEach var="i" begin="1" end="5">
                                <i class="fa ${i <= model.averageStars ? ' fa-star' : ' fa-star-o'}"></i>
                            </c:forEach>
                            <span> - ${model.countProductReview} Lượt đánh giá</span>
                        </div>
                        <h3 id="price-product">${model.getDiscountedPrice()}
                            <c:if test="${model.productDiscount != null}">
                                <span class="discounted-price">${model.price}</span>
                            </c:if></h3>
                        <p>${model.brand}.</p>
                        <div class="container mt-5 product__details__option">
                            <span>Size:</span>
                            <div class="btn-group size-options" role="group" aria-label="Size options">
                                <c:forEach var="size" items="${model.getSizeList()}">
                                    <input type="radio" class="btn-check" name="size" id="${size}" autocomplete="off" value="${size}">
                                    <label class="btn btn-outline-secondary" for="${size}">${size}</label>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="container mt-5 product__details__option">
                            <span>Color:</span>
                            <div class="btn-group size-options" role="group" aria-label="Size options">
                                <c:forEach var="color" items="${model.getColorList()}">
                                    <input type="radio" class="btn-check" name="color" id="${color}" autocomplete="off" value="${color}">
                                    <label class="btn btn-outline-secondary" for="${color}">${color}</label>
                                </c:forEach>
                            </div>
                        </div>

                        <c:if test="${model.status == 'SELLING'}">
                            <div class="product__details__cart__option">
                                <form id="add-to-cart-form">
                                    <div class="quantity">
                                        <div class="pro-qty">
                                            <input type="number" value="1" name="quantity" id="quantity">
                                        </div>
                                    </div>
                                    <button type="button" id="add-to-cart-btn" class="primary-btn" style="margin-top: 10px">add to cart</button>
                                    <input type="hidden" name="productId" value="${model.id}">
                                    <input type="hidden" id="productVariantId" name="productVariantId" value="">
                                </form>
                                <div id="product-quantity" style="display: none; margin-top: 10px">
                                    <p>34 products available</p>
                                </div>
                            </div>
                        </c:if>

                        <div class="product__details__last__option">
                            <h5><span>Guaranteed Safe Checkout</span></h5>
                            <img src="<c:url value="/static/img/shop-details/details-payment.png"/>" alt="">
                            <ul>
                                <li><span>Categories:</span>${model.category.name}</li>
                                <li><span>Tag:</span> Clothes, Skin, Body</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="product__details__tab">
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#tabs-5"
                                   role="tab">Mô tả</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tabs-6" role="tab">Đánh giá khách hàng</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tabs-7" role="tab">Bảng size</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tabs-5" role="tabpanel">
                                <div class="product__details__tab__content">
                                    ${model.description}
                                </div>
                            </div>
                            <div class="tab-pane" id="tabs-6" role="tabpanel">
                                <div class="product__details__tab__content">
                                    <div class="card-body p-4">
                                        <div class="row">
                                            <div class="col">
                                                <c:forEach var="item" items="${review}">
                                                    <div class="d-flex flex-start" style="margin-top: 20px">
                                                        <img class="rounded-circle shadow-1-strong me-3"
                                                             src="https://icons.veryicon.com/png/o/miscellaneous/standard/avatar-15.png" alt="avatar" width="65"
                                                             height="65" />
                                                        <div class="flex-grow-1 flex-shrink-1">
                                                            <div>
                                                                <div class="d-flex justify-content-between align-items-center">
                                                                    <p class="mb-1">
                                                                            ${item.nameCustomer} <span class="small"> - ${item.getDateString()}</span>
                                                                    </p>
                                                                    <div class="rating">
                                                                        <c:forEach var="i" begin="1" end="5">
                                                                            <i class="fa ${i <= item.numberOfStars ? 'fa-star' : 'fa-star-o'}"></i>
                                                                        </c:forEach>
                                                                    </div>
                                                                </div>
                                                                <p class="small mb-0">
                                                                        ${item.content}
                                                                </p>

                                                                <c:if test="${item.reviewFeedback != null}">
                                                                    <button class="btn btn-sm btn-link view-feedback-btn" data-feedback-id="${item.id}">
                                                                        Xem phản hồi
                                                                    </button>
                                                                    <div class="feedback-content" style="display: none; margin-top: 10px;">
                                                                        <strong>Phản hồi của shop:</strong>
                                                                        <p>${item.reviewFeedback.content}</p>
                                                                        <span class="small text-muted">Phản hồi vào: ${item.reviewFeedback.getDateString()}</span>
                                                                    </div>
                                                                </c:if>
                                                                <c:if test="${item.reviewFeedback == null && role == 'OWNER'}">
                                                                    <a class="reply-btn"><img src="https://icons.iconarchive.com/icons/bootstrap/bootstrap/512/Bootstrap-reply-icon.png" width="20px" height="20px"><span class="small"> reply</span></a>
                                                                </c:if>
                                                                <c:if test="${item.reviewFeedback == null && role == 'OWNER'}">
                                                                    <div class="card-footer py-3 border-0 review-feedback-section" style="background-color: #ffff; display: none">
                                                                        <div class="d-flex flex-start w-100 review-feedback-form">
                                                                            <img class="rounded-circle shadow-1-strong me-3"
                                                                                 src="<c:url value='/api-image?path=logo-shop'/>" alt="avatar" width="40"
                                                                                 height="40" />

                                                                            <input type="hidden" class="productReviewId" name="id-feedback" value="${item.id}">

                                                                            <div data-mdb-input-init class="form-outline w-100">
                                                                          <textarea class="form-control content" id="textAreaExample" rows="4"
                                                                                    style="background: #ffff;" placeholder="Nhập nội dung"></textarea>
                                                                            </div>
                                                                        </div>
                                                                        <div class="float-end mt-2 pt-1">
                                                                            <button  type="button" data-mdb-button-init data-mdb-ripple-init class="submit-feedback btn btn-dark btn-sm">Post comment</button>
                                                                        </div>
                                                                    </div>
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>


                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tabs-7" role="tabpanel">
                                <div class="product__details__tab__content">
                                    <h5>Bảng size sản phẩm</h5>
                                    <p>Vui lòng tham khảo bảng size dưới đây để chọn kích cỡ phù hợp cho bạn:</p>
                                    <div class="product__details__tab__content__image">
                                        <img src="<c:url value='/api-image?path=${model.sizeConversionTableUrl}'/>" alt="Bảng size sản phẩm thời trang" onclick="openLightbox()" />
                                    </div>
                                </div>
                            </div>

                            <!-- Lightbox -->
                            <div id="lightbox" onclick="closeLightbox()">
                                <span class="close">&times;</span>
                                <img class="lightbox-content" id="lightbox-img" src="<c:url value='/api-image?path=${model.sizeConversionTableUrl}'/>" alt="Bảng size lớn">
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Shop Details Section End -->

<!-- Related Section Begin -->
<section class="related spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h3 class="related-title">Sản phẩm liên quan</h3>
            </div>
        </div>
        <div class="row">


            <c:forEach var="item" items="${suggest}">
                <div class="col-lg-3 col-md-6 col-sm-6 ">
                    <div class="product__item sale">
                        <div class="product__item__pic set-bg" data-setbg="<c:url value='/api-image?path=${item.photo}'/>">

                            <c:if test="${item.productDiscount != null}">
                                <div class="div_label_product"><span class="label">Sale_${item.productDiscount.discountPercentage}%</span></div>
                            </c:if>
                            <c:if test="${item.isNew}">
                                <div class="div_label_product"><span class="label">New</span></div>
                            </c:if>
                            <ul class="product__hover">
                                <li><a href="#"><img src="<c:url value="/static/img/icon/heart.png"/>" alt=""></a></li>
                                <li><a href="#"><img src="<c:url value="/static/img/icon/compare.png"/>" alt=""> <span>Compare</span></a></li>
                                <li><a href="san-pham?id=${item.id}"><img src="<c:url value="/static/img/icon/search.png"/>" alt=""></a></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6>${item.name}</h6>
                                <%--                                    <a href="<c:url value="/them-gio-hang?id=${item.id}"/>" class="add-cart">View Detail</a>--%>
                            <a href="san-pham?id=${item.id}" class="add-cart">View Detail</a>

                            <div class="rating">
                                <c:forEach var="i" begin="1" end="5">
                                    <i ${i > item.averageStars ? 'style="color: #6c757d"' : ''} class="fa ${i <= item.averageStars ? ' fa-star' : ' fa-star-o'}"></i>
                                </c:forEach>
                            </div>

                            <h5>${item.getDiscountedPrice()}
                                <c:if test="${item.productDiscount != null}">
                                    <span class="discounted-price">${item.price}</span>
                                </c:if>
                            </h5>
                            <div class="product__color__select">
                                <label for="pc-4">
                                    <input type="radio" id="pc-4">
                                </label>
                                <label class="active black" for="pc-5">
                                    <input type="radio" id="pc-5">
                                </label>
                                <label class="grey" for="pc-6">
                                    <input type="radio" id="pc-6">
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>

        $(document).ready(function () {
            // Xử lý sự kiện khi nhấn nút reply
            $(".reply-btn").on("click", function () {
                // Tìm phần tử cha gần nhất chứa review và phần tử card-footer
                let parentDiv = $(this).closest(".flex-grow-1");
                let replySection = parentDiv.find(".card-footer");
                // Kiểm tra nếu phần tử replySection tồn tại và áp dụng hiệu ứng
                if (replySection.length > 0) {
                    replySection.stop(true, true).slideToggle(300); // Hiệu ứng trượt lên/xuống
                }
            });
        });



        // Mở lightbox
        function openLightbox() {
            $("#lightbox").fadeIn();
        }

        // Đóng lightbox
        function closeLightbox() {
            $("#lightbox").fadeOut();
        }


        $(document).ready(function () {
            // Lắng nghe sự kiện click trên các tab
            $('.nav-link').on('click', function () {
                // Lấy đường dẫn hình ảnh từ thuộc tính data-image của tab được nhấp
                var imageUrl = $(this).data('image');
                // Cập nhật hình ảnh chi tiết
                $('#detail-image').attr('src', imageUrl);
            });
        });
        var CHECKFECHPRODCUCT = true

        $(document).ready(function () {
            // Hàm gọi API
            function fetchProduct(sizeOrColor) {
                if (!CHECKFECHPRODCUCT) return
                $('#product-quantity p').text('');
                // Lấy giá trị color và size được chọn
                var selectedSize = $('input[name="size"]:checked').val();
                var selectedColor = $('input[name="color"]:checked').val();

                // Kiểm tra xem cả hai radio đều có giá trị
                $.ajax({
                    url: 'api-product', // Thay đổi thành API của bạn
                    method: 'GET',
                    data: {
                        id: ${model.id},
                        color: selectedColor,
                        size: selectedSize,
                        atributeName : sizeOrColor,
                    },
                    success: function (response) {
                        var colorOrSizeAvailable = response.colorOrSizeAvailable;
                        updateAvailableOptions(colorOrSizeAvailable,sizeOrColor)
                        if (selectedSize && selectedColor) {
                            var productVariant = response.productVariant;
                            console.log(productVariant)
                            if (productVariant.id !== -1 && productVariant.quantity > 0) {

                                $('#product-quantity p').text(productVariant.quantity + ' products available').css('color', 'green');
                                $('#price-product').text(productVariant.price + " VND")
                                // Kích hoạt lại button
                                $('#add-your-cart').prop('disabled', false).css({
                                    'opacity': '1',        // Khôi phục độ trong suốt
                                    'cursor': 'pointer'    // Khôi phục con trỏ chuột
                                });
                                $('#productVariantId').val(productVariant.id) // Gán cho product variant id
                            } else {
                                $('#product-quantity p').text("Product is not available!").css('color', 'red');
                                $('#price-product').text("out of stock !")
                                $('#add-your-cart').prop('disabled', true).css({
                                    'opacity': '0.5',      // Làm mờ button
                                    'cursor': 'not-allowed' // Đổi con trỏ chuột khi hover
                                });

                                CHECKFECHPRODCUCT = false
                                if (sizeOrColor == 'color') sizeOrColor = 'size'
                                else sizeOrColor = 'color'
                                $('input[name="' + sizeOrColor + '"]').each(function () {
                                    $(this).prop('checked', false); // Kích hoạt radio button
                                });
                                CHECKFECHPRODCUCT = true
                            }
                            $('#product-quantity').show(); // Hiện thẻ này
                        }
                    },
                    error: function (error) {
                        console.error('Error fetching product:', error);
                    }
                });
            }

            // Gọi hàm khi người dùng nhấp vào bất kỳ radio nào
            $('input[name="size"], input[name="color"]').on('change', function () {
                fetchProduct(this.name);
            });
        });

        function updateAvailableOptions(colorOrSizeAvailable, attributeName) {
            $('input[name="' + attributeName + '"]').each(function () {
                    $(this).prop('disabled', false); // Kích hoạt radio button
            });

            if (attributeName == 'color') attributeName = 'size'
            else attributeName = 'color'
            // Cập nhật các radio button dựa vào tên thuộc tính
            $('input[name="' + attributeName + '"]').each(function () {
                var value = $(this).val();
                console.log(value);
                if (colorOrSizeAvailable.includes(value)) {
                    $(this).prop('disabled', false); // Kích hoạt radio button
                } else {
                    $(this).prop('disabled', true); // Vô hiệu hóa radio button
                }
            });
        }

        $(document).ready(function () {
            $(".view-feedback-btn").on("click", function () {
                const feedbackContent = $(this).siblings(".feedback-content");
                feedbackContent.slideToggle(300);
            });
        });


        // Hàm xử lý sự kiện khi nhấn nút "Thêm vào giỏ hàng"
        $(document).ready(function () {
            $('#add-to-cart-btn').on('click', function (e) {
                e.preventDefault(); // Ngăn chặn submit form mặc định

                // Thu thập dữ liệu từ form
                var formData = {
                    productId: $('input[name="productId"]').val(),
                    productVariantId: $('#productVariantId').val(),
                    quantity: parseInt($('#quantity').val(), 10) // Lấy số lượng người dùng chọn và chuyển thành số nguyên
                };

                // Kiểm tra nếu productVariantId đã được chọn
                if (!formData.productVariantId) {
                    Swal.fire({
                        icon: 'warning',
                        title: 'Cảnh báo',
                        text: "Vui lòng chọn kích cỡ và màu sắc.",
                    });
                    return;
                }

                // Kiểm tra trạng thái sản phẩm có sẵn
                var productStatus = $('#product-quantity p').text();
                if (productStatus.includes("Product is not available!") || productStatus.includes("not available!")) {
                    Swal.fire({
                        icon: 'warning',
                        title: 'Cảnh báo',
                        text: "Sản phẩm không có sẵn. Không thể thêm vào giỏ hàng.",
                    });
                    return;
                }

                // Kiểm tra số lượng người dùng chọn có hợp lệ hay không
                var availableQuantity = parseInt($('#product-quantity p').text(), 10); // Lấy số lượng sản phẩm có sẵn
                if (formData.quantity > availableQuantity) {
                    Swal.fire({
                        icon: 'warning',
                        title: 'Cảnh báo',
                        text: "Số lượng sản phẩm không hợp lệ. Vui lòng chọn số lượng nhỏ hơn hoặc bằng " + availableQuantity + ".",
                    });
                    return;
                }

                // Gửi yêu cầu AJAX POST
                $.ajax({
                    url: 'them-gio-hang',
                    method: 'POST',
                    data: formData,
                    success: function (response) {
                        Swal.fire({
                            icon: 'success',
                            title: 'Thành công',
                            text: 'Sản phẩm đã được thêm vào giỏ hàng thành công!',
                        });
                    },
                    error: function (error) {
                        console.error("Lỗi khi thêm sản phẩm vào giỏ hàng:", error);
                        Swal.fire({
                            icon: 'warning',
                            title: 'Cảnh báo',
                            text: "Không thể thêm sản phẩm vào giỏ hàng.",
                        });
                    }
                });
            });
        });
    </script>

    <script>
        $(document).ready(function () {
            // Lắng nghe sự kiện click nút "Post comment"
            $(".review-feedback-section").on("click", ".submit-feedback", function () {
                // Tìm thẻ cha gần nhất chứa form tương ứng
                const parentSection = $(this).closest(".review-feedback-section");

                // Lấy dữ liệu từ các trường trong form tương ứng
                const formData = {
                    content: parentSection.find(".content").val(), // Lấy nội dung từ textarea
                    productReview: {
                        id: parentSection.find(".productReviewId").val(), // Lấy ID từ hidden input
                    },
                };

                // Kiểm tra nội dung trước khi gửi
                if (!formData.content.trim()) {

                    alert("Vui lòng nhập nội dung phản hồi.");
                    return;
                }

                // Gửi request POST tới API
                $.ajax({
                    url: "/api-review-feedback", // Địa chỉ servlet của bạn
                    type: "POST",
                    contentType: "application/json; charset=UTF-8",
                    data: JSON.stringify(formData), // Chuyển đổi dữ liệu sang JSON
                    success: function (response) {
                        // Ẩn form sau khi gửi thành công
                        parentSection.slideUp(300, function () {
                            // Xóa nội dung đã nhập
                            parentSection.find(".content").val("");
                        });
                    },
                    error: function (xhr, status, error) {
                        alert("Lỗi: " + xhr.responseText);
                    },
                });
                alert("Phản hồi thành công");
                window.location.href = '/san-pham?id=' + $('input[name="productId"]').val();

            });
        });
    </script>

</section>
<!-- Related Section End -->