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




</style>
<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__text">
                    <h4>Shop</h4>
                    <div class="breadcrumb__links">
                        <a href="<c:url value="/trang-chu"/>">Home</a>
                        <span>Shop</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Shop Section Begin -->
<section class="shop spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="shop__sidebar">
                    <div class="shop__sidebar__search">
                        <form action="#">
                            <input type="text" placeholder="Search...">
                            <button type="submit"><span class="icon_search"></span></button>
                        </form>
                    </div>
<%--                    Thử filter--%>

                    <div class="shop__sidebar__accordion">
                        <div class="accordion" id="accordionExample">
                            <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseOne">Thể loại</a>
                                </div>
                                <div id="collapseOne" class="collapse show" data-parent="#accordionExample">
                                    <div class="card-body">
                                        <div class="shop__sidebar__categories">
                                            <ul class="nice-scroll">
                                                <c:forEach var="item" items="${onemodel}">
                                                    <li><a href="javascript:void(0);" onclick="selectCategory('${item.id}')">${item.name}</a></li>

                                                    <%--<li><a href="danh-sach-san-pham?category=${item.code}">${item.name}</a></li>--%>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseTwo">Hãng</a>
                                </div>
                                <div id="collapseTwo" class="collapse show" data-parent="#accordionExample">
                                    <div class="card-body">
                                        <div class="shop__sidebar__brand">
                                            <ul>
                                                <c:forEach var="item" items="${twomodel}">
                                                    <li><a href="javascript:void(0);" onclick="selectBrand('${item}')">${item}</a></li>

                                                    <%--<li><a href="danh-sach-san-pham?brand=${item}">${item}</a></li>--%>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseThree">Lọc theo giá</a>
                                </div>
                                <div id="collapseThree" class="collapse show" data-parent="#accordionExample">
                                    <div class="card-body">
                                        <div class="shop__sidebar__price">
                                            <ul>
                                                <li><a href="#">$0.00 - $50.00</a></li>
                                                <li><a href="#">$50.00 - $100.00</a></li>
                                                <li><a href="#">$100.00 - $150.00</a></li>
                                                <li><a href="#">$150.00 - $200.00</a></li>
                                                <li><a href="#">$200.00 - $250.00</a></li>
                                                <li><a href="#">250.00+</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-heading">
                                    <a data-toggle="collapse" data-target="#collapseSix">Tags</a>
                                </div>
                                <div id="collapseSix" class="collapse show" data-parent="#accordionExample">
                                    <div class="card-body">
                                        <div class="shop__sidebar__tags">
                                            <a href="#">New</a>
                                            <a href="#">Sale</a>
                                            <a href="#">Others</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <button type="button" id="add-to-cart btn" class="primary-btn">Hủy tiêu chí</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-9">
                <div class="shop__product__option">
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="shop__product__option__left">
                                <p>Showing ${model.totalItem} results</p>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="shop__product__option__right">
                                <p>Sort by Price:</p>
                                <select>
                                    <option value="">Low To High</option>
                                    <option value="">$0 - $55</option>
                                    <option value="">$55 - $100</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <c:forEach var="item" items="${model.resultList}">


                        <div class="col-lg-4 col-md-6 col-sm-6">
                            <div class="product__item sale">
                                <div class="product__item__pic set-bg" data-setbg="<c:url value="${item.photo}"/>">
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
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                    </div>
                                    <h5>$ ${item.price}</h5>
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

                <form id="formSubmit" action="/danh-sach-san-pham" method="get">
                    <!-- Giữ giá trị của bộ lọc để gửi đi -->
                    <ul class="pagination" id="pagination"></ul>
                    <input type="hidden" name="page" id="page" value="">
                    <input type="hidden" name="maxPageItem" id="maxPageItem" value="">
                    <input type="hidden" name="category" id="category">
                    <input type="hidden" name="brand" id="brand">
                </form>

            </div>
        </div>
    </div>

    <script>

        function selectCategory(categoryCode) {

            if (categoryCode) {
                document.getElementById('category').value = categoryCode;
                sessionStorage.setItem('selectedCategory', categoryCode); // Lưu lại giá trị đã chọn
            } else {
                document.getElementById('category').value = '';
                sessionStorage.removeItem('selectedCategory'); // Xóa nếu không chọn gì
            }

            const previousBrand = sessionStorage.getItem('selectedBrand');
            if (!previousBrand) {
                document.getElementById('brand').removeAttribute('name');
            }

            updatePageInfo();
            submitFilterForm()
        }

        function selectBrand(brandName) {

            if (brandName) {
                document.getElementById('brand').value = brandName;
                sessionStorage.setItem('selectedBrand', brandName); // Lưu lại giá trị đã chọn
            } else {
                document.getElementById('brand').value = '';
                sessionStorage.removeItem('selectedBrand'); // Xóa nếu không chọn gì
            }

            const previousCategory = sessionStorage.getItem('selectedCategory');
            if (!previousCategory) {
                document.getElementById('category').removeAttribute('name');
            }

            updatePageInfo();
            submitFilterForm();
        }

        function updatePageInfo() {
            document.getElementById('page').value = currentPage;
            document.getElementById('maxPageItem').value = ${model.maxPageItem};
        }

        function submitFilterForm() {

            const storedCategory = sessionStorage.getItem('selectedCategory');
            const storedBrand = sessionStorage.getItem('selectedBrand');

            if (storedCategory) {
                document.getElementById('category').value = storedCategory;
            }
            if (storedBrand) {
                document.getElementById('brand').value = storedBrand;
            }

            document.getElementById('formSubmit').submit();
        }

        document.getElementById('reset-filter-btn').addEventListener('click', function() {
            document.getElementById('category').value = '';
            document.getElementById('brand').value = '';
            updatePageInfo();
            submitFilterForm();
        });
    </script>

    <script>
        var totalPages = ${model.totalPage};
        var currentPage = ${model.page};
        var limit = ${model.maxPageItem};



        $(function () {
            window.pagObj = $('#pagination').twbsPagination({
                totalPages: totalPages,
                visiblePages: 5,
                startPage: currentPage, // Trang bắt đầu khi load
                onPageClick: function (event, page) {
                    if (currentPage != page) {
                        $('#maxPageItem').val(limit);
                        $('#page').val(page);
                        $('#formSubmit').submit();
                    }
                },
                first: 'Trang đầu',
                prev: 'Trang trước',
                next: 'Trang tiếp',
                last: 'Trang cuối'
            });
        });
    </script>



</section>





