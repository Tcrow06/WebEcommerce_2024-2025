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
                        <div class="product__item__pic set-bg" data-setbg="<c:url value='${item.photo}'/>">
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
                            <h5>$ ${item.price}</h5>
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

<!-- Categories Section Begin -->
<section class="categories spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="categories__text">
                    <h2>Clothings Hot <br /> <span>Shoe Collection</span> <br /> Accessories</h2>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="categories__hot__deal">
                    <img src="<c:url value="/static/img/product-sale.png"/>" alt="">
                    <div class="hot__deal__sticker">
                        <span>Sale Of</span>
                        <h5>$29.99</h5>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 offset-lg-1">
                <div class="categories__deal__countdown">
                    <span>Deal Of The Week</span>
                    <h2>Multi-pocket Chest Bag Black</h2>
                    <div class="categories__deal__countdown__timer" id="countdown">
                        <div class="cd-item">
                            <span>3</span>
                            <p>Days</p>
                        </div>
                        <div class="cd-item">
                            <span>1</span>
                            <p>Hours</p>
                        </div>
                        <div class="cd-item">
                            <span>50</span>
                            <p>Minutes</p>
                        </div>
                        <div class="cd-item">
                            <span>18</span>
                            <p>Seconds</p>
                        </div>
                    </div>
                    <a href="#" class="primary-btn">Shop now</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Categories Section End -->

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
                    <div class="blog__item__pic set-bg" data-setbg="<c:url value="/static/img/blog/blog-1.jpg"/>"></div>
                    <div class="blog__item__text">
                        <span><img src="<c:url value="/static/img/icon/calendar.png"/>" alt=""> 16 February 2020</span>
                        <h5>What Curling Irons Are The Best Ones</h5>
                        <a href="#">Read More</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6">
                <div class="blog__item">
                    <div class="blog__item__pic set-bg" data-setbg="<c:url value="/static/img/blog/blog-2.jpg"/>"></div>
                    <div class="blog__item__text">
                        <span><img src="<c:url value="/static/img/icon/calendar.png"/>" alt=""> 21 February 2020</span>
                        <h5>Eternity Bands Do Last Forever</h5>
                        <a href="#">Read More</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6">
                <div class="blog__item">
                    <div class="blog__item__pic set-bg" data-setbg="<c:url value="/static/img/blog/blog-3.jpg"/>"></div>
                    <div class="blog__item__text">
                        <span><img src="<c:url value="/static/img/icon/calendar.png"/>" alt=""> 28 February 2020</span>
                        <h5>The Health Benefits Of Sunglasses</h5>
                        <a href="#">Read More</a>
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

