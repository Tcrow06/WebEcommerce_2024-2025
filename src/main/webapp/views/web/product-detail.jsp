<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
    .size-options label {
        padding: 10px 15px;
        margin: 0;
        border: 1px solid #ddd;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .size-options input[type="radio"] {
        display: none;
    }

    .size-options input[type="radio"]:checked + label {
        background-color: #050303;
        color: #fff;
    }

    .size-options label:not(:last-child) {
        border-right: none;
    }
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
                                <a class="nav-link <c:if test="${status.index == 0}">active</c:if>" data-toggle="tab" href="#tabs-${status.index + 1}" role="tab" data-image="${item.imageUrl}">
                                    <div class="product__thumb__pic set-bg" data-setbg="<c:url value='${item.imageUrl}'/>">
                                    </div>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>

                <div class="col-lg-6 col-md-9">
                    <div class="tab-content">
                        <c:forEach var="item" items="${model.productVariants}" varStatus="status">
                            <div class="tab-pane <c:if test="${status.index == 0}">active</c:if>" id="tabs-${status.index + 1}" role="tabpanel">
                                <div class="product__details__pic__item">
                                    <img src="<c:url value='${item.imageUrl}'/>" alt="" id="detail-image">
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
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star-o"></i>
                            <span> - 5 Reviews</span>
                        </div>
                        <h3 id="price-product">$${model.price} <span>70.00</span></h3>
                        <p>${model.brand}.</p>
                        <div class="product__details__option">
                            <div class="product__details__option__size" id="sizeOptions">
                                <span>Size:</span>
                                <c:forEach var="size" items="${model.getSizeList()}">
                                    <label for="size_${size}">${size}
                                        <input type="radio" id="size_${size}" name="size" value="${size}">
                                    </label>
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

                        <div class="product__details__cart__option">
                            <form>
                                <div class="quantity">
                                    <div class="pro-qty">
                                        <input type="text" value="1">
                                    </div>
                                </div>

                                <button id="add-your-cart" href="#" class="primary-btn" style="margin-top: 10px">add to cart</button>
                                <input type="hidden" name="productId" value="${model.id}">
                                <input type="hidden" id="productVariantId" name="productVariantId" value="">
                            </form>

                            <div id="product-quantity" style="display: none; margin-top: 10px">
                                <p>34 products available</p>
                            </div>
                        </div>
                        <div class="product__details__btns__option">
                            <a href="#"><i class="fa fa-heart"></i> add to wishlist</a>
                            <a href="#"><i class="fa fa-exchange"></i> Add To Compare</a>
                        </div>
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
                                <a class="nav-link" data-toggle="tab" href="#tabs-6" role="tab">Customer
                                    Previews(5)</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tabs-6" role="tabpanel">
                                <div class="product__details__tab__content">
                                    <p class="note">Nam tempus turpis at metus scelerisque placerat nulla deumantos
                                        solicitud felis. Pellentesque diam dolor, elementum etos lobortis des mollis
                                        ut risus. Sedcus faucibus an sullamcorper mattis drostique des commodo
                                        pharetras loremos.</p>
                                    <div class="product__details__tab__content__item">
                                        <h5>Products Infomation</h5>
                                        <p>A Pocket PC is a handheld computer, which features many of the same
                                            capabilities as a modern PC. These handy little devices allow
                                            individuals to retrieve and store e-mail messages, create a contact
                                            file, coordinate appointments, surf the internet, exchange text messages
                                            and more. Every product that is labeled as a Pocket PC must be
                                            accompanied with specific software to operate the unit and must feature
                                            a touchscreen and touchpad.</p>
                                        <p>As is the case with any new technology product, the cost of a Pocket PC
                                            was substantial during it’s early release. For approximately $700.00,
                                            consumers could purchase one of top-of-the-line Pocket PCs in 2003.
                                            These days, customers are finding that prices have become much more
                                            reasonable now that the newness is wearing off. For approximately
                                            $350.00, a new Pocket PC can now be purchased.</p>
                                    </div>
                                    <div class="product__details__tab__content__item">
                                        <h5>Material used</h5>
                                        <p>Polyester is deemed lower quality due to its none natural quality’s. Made
                                            from synthetic materials, not natural like wool. Polyester suits become
                                            creased easily and are known for not being breathable. Polyester suits
                                            tend to have a shine to them compared to wool and cotton suits, this can
                                            make the suit look cheap. The texture of velvet is luxurious and
                                            breathable. Velvet is a great choice for dinner party jacket and can be
                                            worn all year round.</p>
                                    </div>
                                </div>
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
                <h3 class="related-title">Related Product</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-3 col-md-6 col-sm-6 col-sm-6">
                <div class="product__item">
                    <div class="product__item__pic set-bg" data-setbg="<c:url value="/static/img/product/product-1.jpg"/>">
                        <span class="label">New</span>
                        <ul class="product__hover">
                            <li><a href="#"><img src="<c:url value="/static/img/icon/heart.png"/>" alt=""></a></li>
                            <li><a href="#"><img src="<c:url value="/static/img/icon/compare.png"/>" alt=""> <span>Compare</span></a></li>
                            <li><a href="#"><img src="<c:url value="/static/img/icon/search.png"/>" alt=""></a></li>
                        </ul>
                    </div>
                    <div class="product__item__text">
                        <h6>Piqué Biker Jacket</h6>
                        <a href="#" class="add-cart">+ Add To Cart</a>
                        <div class="rating">
                            <i class="fa fa-star-o"></i>
                            <i class="fa fa-star-o"></i>
                            <i class="fa fa-star-o"></i>
                            <i class="fa fa-star-o"></i>
                            <i class="fa fa-star-o"></i>
                        </div>
                        <h5>$67.24</h5>
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
            <div class="col-lg-3 col-md-6 col-sm-6 col-sm-6">
                <div class="product__item">
                    <div class="product__item__pic set-bg" data-setbg="<c:url value="/static/img/product/product-2.jpg"/>">
                        <ul class="product__hover">
                            <li><a href="#"><img src="<c:url value="/static/img/icon/heart.png"/>" alt=""></a></li>
                            <li><a href="#"><img src="<c:url value="/static/img/icon/compare.png"/>" alt=""> <span>Compare</span></a></li>
                            <li><a href="#"><img src="<c:url value="/static/img/icon/search.png"/>" alt=""></a></li>
                        </ul>
                    </div>
                    <div class="product__item__text">
                        <h6>Piqué Biker Jacket</h6>
                        <a href="#" class="add-cart">+ Add To Cart</a>
                        <div class="rating">
                            <i class="fa fa-star-o"></i>
                            <i class="fa fa-star-o"></i>
                            <i class="fa fa-star-o"></i>
                            <i class="fa fa-star-o"></i>
                            <i class="fa fa-star-o"></i>
                        </div>
                        <h5>$67.24</h5>
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
            <div class="col-lg-3 col-md-6 col-sm-6 col-sm-6">
                <div class="product__item sale">
                    <div class="product__item__pic set-bg" data-setbg="<c:url value="/static/img/product/product-3.jpg"/>">
                        <span class="label">Sale</span>
                        <ul class="product__hover">
                            <li><a href="#"><img src="<c:url value="/static/img/icon/heart.png"/>" alt=""></a></li>
                            <li><a href="#"><img src="<c:url value="/static/img/icon/compare.png"/>" alt=""> <span>Compare</span></a></li>
                            <li><a href="#"><img src="<c:url value="/static/img/icon/search.png"/>" alt=""></a></li>
                        </ul>
                    </div>
                    <div class="product__item__text">
                        <h6>Multi-pocket Chest Bag</h6>
                        <a href="#" class="add-cart">+ Add To Cart</a>
                        <div class="rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star-o"></i>
                        </div>
                        <h5>$43.48</h5>
                        <div class="product__color__select">
                            <label for="pc-7">
                                <input type="radio" id="pc-7">
                            </label>
                            <label class="active black" for="pc-8">
                                <input type="radio" id="pc-8">
                            </label>
                            <label class="grey" for="pc-9">
                                <input type="radio" id="pc-9">
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-6 col-sm-6">
                <div class="product__item">
                    <div class="product__item__pic set-bg" data-setbg="<c:url value="/static/img/product/product-4.jpg"/>">
                        <ul class="product__hover">
                            <li><a href="#"><img src="<c:url value="/static/img/icon/heart.png"/>" alt=""></a></li>
                            <li><a href="#"><img src="<c:url value="/static/img/icon/compare.png"/>" alt=""> <span>Compare</span></a></li>
                            <li><a href="#"><img src="<c:url value="/static/img/icon/search.png"/>" alt=""></a></li>
                        </ul>
                    </div>
                    <div class="product__item__text">
                        <h6>Diagonal Textured Cap</h6>
                        <a href="#" class="add-cart">+ Add To Cart</a>
                        <div class="rating">
                            <i class="fa fa-star-o"></i>
                            <i class="fa fa-star-o"></i>
                            <i class="fa fa-star-o"></i>
                            <i class="fa fa-star-o"></i>
                            <i class="fa fa-star-o"></i>
                        </div>
                        <h5>$60.9</h5>
                        <div class="product__color__select">
                            <label for="pc-10">
                                <input type="radio" id="pc-10">
                            </label>
                            <label class="active black" for="pc-11">
                                <input type="radio" id="pc-11">
                            </label>
                            <label class="grey" for="pc-12">
                                <input type="radio" id="pc-12">
                            </label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            // Lắng nghe sự kiện click trên các tab
            $('.nav-link').on('click', function () {
                // Lấy đường dẫn hình ảnh từ thuộc tính data-image của tab được nhấp
                var imageUrl = $(this).data('image');
                // Cập nhật hình ảnh chi tiết
                $('#detail-image').attr('src', imageUrl);
            });
        });


        $(document).ready(function () {
                // Hàm gọi API
                function fetchProduct() {
                    // Lấy giá trị color và size được chọn
                    var selectedSize = $('input[name="size"]:checked').val();
                    var selectedColor = $('input[name="color"]:checked').val();

                    // Kiểm tra xem cả hai radio đều có giá trị
                    if (selectedSize && selectedColor) {
                        $.ajax({
                            url: 'api-product', // Thay đổi thành API của bạn
                            method: 'GET',
                            data: {
                                id: ${model.id},
                                color: selectedColor,
                                size: selectedSize
                            },
                            success: function (productVariant) {
                                if (productVariant.id != -1 && productVariant.quantity > 0) {
                                    $('#product-quantity p').text(productVariant.quantity + ' products available').css('color','green');
                                    $('#price-product').text("$"+productVariant.price)
                                    // Kích hoạt lại button
                                    $('#add-your-cart').prop('disabled', false).css({
                                        'opacity': '1',        // Khôi phục độ trong suốt
                                        'cursor': 'pointer'    // Khôi phục con trỏ chuột
                                    });
                                    $('#productVariantId').val(productVariant.id) // gán cho product variant id
                                } else {
                                    $('#product-quantity p').text("Product is not available!").css('color', 'red');
                                    $('#price-product').text("out of stock !")
                                    $('#add-your-cart').prop('disabled', true).css({
                                        'opacity': '0.5',      // Làm mờ button
                                        'cursor': 'not-allowed' // Đổi con trỏ chuột khi hover
                                    });
                                }
                                $('#product-quantity').show(); // Hiện thẻ này
                            },
                            error: function (error) {
                                console.error('Error fetching product:', error);
                            }
                        });
                    }
                }

                // Gọi hàm khi người dùng nhấp vào bất kỳ radio nào
                $('input[name="size"], input[name="color"]').on('change', fetchProduct);
            });
        </script>
    </section>
    <!-- Related Section End -->