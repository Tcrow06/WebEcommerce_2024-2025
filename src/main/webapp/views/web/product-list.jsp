<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.List" %>
<%
    List<String> listNames = (List<String>) request.getAttribute("listNames");
%>

<%
    List<String> randomProductNames = (List<String>) request.getAttribute("randomProductNames");
    if (randomProductNames != null && !randomProductNames.isEmpty()) {
%>
<ul>
    <c:forEach var="productName" items="${randomProductNames}">
        <li class="dropdown-item">${productName}</li>
    </c:forEach>
</ul>
<% } %>

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
<%--                        <c:url value='/danh-sach-san-pham'/>--%>
                        <form id="form-search">
                            <input type="text" placeholder="Search..." id="search-product" class="search-product">
                            <ul id="suggestions" class="suggestions-dropdown" style="display: none;"></ul>
                            <button type="button" id ="btnSearch"><span class="icon_search"></span></button>
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
                                                <li><a href="javascript:void(0);" onclick="selectPriceRange(0, 100000)">0.00 - 100.000</a></li>
                                                <li><a href="javascript:void(0);" onclick="selectPriceRange(100000, 150000)">100.000 - 150.000</a></li>
                                                <li><a href="javascript:void(0);" onclick="selectPriceRange(150000, 200000)">150.000 - 200.000</a></li>
                                                <li><a href="javascript:void(0);" onclick="selectPriceRange(200000, 500000)">200.000 - 500.000</a></li>
                                                <li><a href="javascript:void(0);" onclick="selectPriceRange(500000, (Number.MAX_SAFE_INTEGER))">500.000+</a></li>
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
                                            <a href="javascript:void(0);" onclick="selectTag('new')">Hàng mới</a>
                                            <a href="javascript:void(0);" onclick="selectTag('sale')">Giảm giá</a>
                                            <a href="javascript:void(0);" onclick="selectTag('other')">Khác</a>
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
                                <p>
                                    <c:choose>
                                        <c:when test="${model.totalItem == 0}">
                                            Không sản phẩm phù hợp
                                        </c:when>
                                        <c:otherwise>
                                            Có ${model.totalItem} trùng khớp với kết quả
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="shop__product__option__right">
                                <p>Sắp xếp theo giá:</p>
                                <select onchange="selectSort(this)">
                                    <option value="none">Mặc định</option>
                                    <option value="desc">Giảm dần</option>
                                    <option value="asc">Tăng dần</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <c:forEach var="item" items="${model.resultList}">
                        <div class="col-lg-4 col-md-6 col-sm-6">
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

                <form id="formSubmit" action="/danh-sach-san-pham" method="get">
                    <!-- Giữ giá trị của bộ lọc để gửi đi -->
                    <ul class="pagination" id="pagination"></ul>
                    <input type="hidden" name="page" id="page" value="">
                    <input type="hidden" name="maxPageItem" id="maxPageItem" value="">
                    <input type="hidden" name="category" id="category">
                    <input type="hidden" name="brand" id="brand">
                    <input type="hidden" name="minPrice" id="minPrice">
                    <input type="hidden" name="maxPrice" id="maxPrice">
                    <input type="hidden" name="tag" id="tag">
                    <input type="hidden" name="sort" id="sort">
                </form>

            </div>
        </div>
    </div>

    <script>
        document.getElementById("add-to-cart btn").addEventListener("click", function() {
            window.location.href = "/danh-sach-san-pham?page=1&maxPageItem=9";
        });
    </script>

    <script>
        window.addEventListener('DOMContentLoaded', function () {
            const urlParams = new URLSearchParams(window.location.search);

            // Thu thập tất cả các tham số có giá trị rỗng
            const emptyKeys = [];
            urlParams.forEach(function(value, key) {
                if (value.trim() === '') {
                    emptyKeys.push(key); // Thêm vào danh sách các khóa rỗng
                }
            });

            // Xóa tất cả các khóa rỗng sau khi đã duyệt xong
            emptyKeys.forEach(function(key) {
                urlParams.delete(key);
            });

            // Cập nhật lại URL mà không có các tham số rỗng
            const newUrl = window.location.pathname + '?' + urlParams.toString();
            window.history.replaceState(null, '', newUrl); // Cập nhật URL mà không reload trang
        });
        window.addEventListener('DOMContentLoaded', function () {
            const currentUrl = window.location.pathname + window.location.search;

            // Kiểm tra nếu URL là /danh-sach-san-pham?page=1&maxPageItem=9
            if (currentUrl === '/danh-sach-san-pham?page=1&maxPageItem=9') {
                sessionStorage.clear(); // Xóa toàn bộ sessionStorage
            }
        });

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

            const previousMin = sessionStorage.getItem('selectedMinPrice');
            if (!previousMin) {
                document.getElementById('minPrice').removeAttribute('name');
            }
            const previousMax = sessionStorage.getItem('selectedMaxPrice');
            if (!previousMax) {
                document.getElementById('maxPrice').removeAttribute('name');
            }

            const previousTag = sessionStorage.getItem('selectedTag');
            if (!previousTag) {
                document.getElementById('tag').removeAttribute('name');
            }

            const previousSort = sessionStorage.getItem('selectedSort');
            if (!previousSort) {
                document.getElementById('sort').removeAttribute('name');
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

            const previousMin = sessionStorage.getItem('selectedMinPrice');
            if (!previousMin) {
                document.getElementById('minPrice').removeAttribute('name');
            }
            const previousMax = sessionStorage.getItem('selectedMaxPrice');
            if (!previousMax) {
                document.getElementById('maxPrice').removeAttribute('name');
            }

            const previousTag = sessionStorage.getItem('selectedTag');
            if (!previousTag) {
                document.getElementById('tag').removeAttribute('name');
            }

            const previousSort = sessionStorage.getItem('selectedSort');
            if (!previousSort) {
                document.getElementById('sort').removeAttribute('name');
            }

            updatePageInfo();
            submitFilterForm();
        }

        function selectPriceRange(minPrice, maxPrice) {
            if (minPrice >=0 && maxPrice >=0) {
                document.getElementById('minPrice').value = minPrice;
                sessionStorage.setItem('selectedMinPrice', minPrice);
                document.getElementById('maxPrice').value = maxPrice;
                sessionStorage.setItem('selectedMaxPrice', maxPrice);
            } else {
                document.getElementById('maxPrice').value = '';
                sessionStorage.removeItem('selectedMaxPrice');
                document.getElementById('minPrice').value = '';
                sessionStorage.removeItem('selectedMinPrice'); // Xóa nếu không chọn gì
            }

            const previousBrand = sessionStorage.getItem('selectedBrand');
            if (!previousBrand) {
                document.getElementById('brand').removeAttribute('name');
            }

            const previousCategory = sessionStorage.getItem('selectedCategory');
            if (!previousCategory) {
                document.getElementById('category').removeAttribute('name');
            }

            const previousTag = sessionStorage.getItem('selectedTag');
            if (!previousTag) {
                document.getElementById('tag').removeAttribute('name');
            }

            const previousSort = sessionStorage.getItem('selectedSort');
            if (!previousSort) {
                document.getElementById('sort').removeAttribute('name');
            }

            updatePageInfo();
            submitFilterForm();
        }

        function selectTag(tagName) {
            if (tagName) {
                document.getElementById('tag').value = tagName;
                sessionStorage.setItem('selectedTag', tagName); // Store selected tag
            } else {
                document.getElementById('tag').value = '';
                sessionStorage.removeItem('selectedTag'); // Remove if no tag is selected
            }

            const previousCategory = sessionStorage.getItem('selectedCategory');
            if (!previousCategory) {
                document.getElementById('category').removeAttribute('name');
            }

            const previousBrand = sessionStorage.getItem('selectedBrand');
            if (!previousBrand) {
                document.getElementById('brand').removeAttribute('name');
            }

            const previousMin = sessionStorage.getItem('selectedMinPrice');
            if (!previousMin) {
                document.getElementById('minPrice').removeAttribute('name');
            }
            const previousMax = sessionStorage.getItem('selectedMaxPrice');
            if (!previousMax) {
                document.getElementById('maxPrice').removeAttribute('name');
            }

            const previousSort = sessionStorage.getItem('selectedSort');
            if (!previousSort) {
                document.getElementById('sort').removeAttribute('name');
            }

            updatePageInfo();
            submitFilterForm();
        }

        function selectSort(sortBy) {
            var sortByValue = sortBy.value;

            if (sortByValue) {
                document.getElementById('sort').value = sortByValue;
                sessionStorage.setItem('selectedSort', sortByValue); // Store selected tag
            } else {
                document.getElementById('sort').value = '';
                sessionStorage.removeItem('selectedSort'); // Remove if no tag is selected
            }

            const previousCategory = sessionStorage.getItem('selectedCategory');
            if (!previousCategory) {
                document.getElementById('category').removeAttribute('name');
            }

            const previousBrand = sessionStorage.getItem('selectedBrand');
            if (!previousBrand) {
                document.getElementById('brand').removeAttribute('name');
            }

            const previousMin = sessionStorage.getItem('selectedMinPrice');
            if (!previousMin) {
                document.getElementById('minPrice').removeAttribute('name');
            }

            const previousMax = sessionStorage.getItem('selectedMaxPrice');
            if (!previousMax) {
                document.getElementById('maxPrice').removeAttribute('name');
            }

            const previousTag = sessionStorage.getItem('selectedTag');
            if (!previousTag) {
                document.getElementById('tag').removeAttribute('name');
            }

            updatePageInfo();
            submitFilterForm();
        }

        function updatePageInfo() {
            document.getElementById('page').value = currentPage;
            document.getElementById('maxPageItem').value = ${model.maxPageItem};
        }

        function submitFilterForm() {

            document.getElementById('page').value = 1;

            const storedCategory = sessionStorage.getItem('selectedCategory');
            const storedBrand = sessionStorage.getItem('selectedBrand');
            const storedMinPrice = sessionStorage.getItem('selectedMinPrice');
            const storedMaxPrice = sessionStorage.getItem('selectedMaxPrice');
            const storedTag = sessionStorage.getItem('selectedTag');
            const storedSort = sessionStorage.getItem('selectedSort');

            if (storedCategory) {
                document.getElementById('category').value = storedCategory;
            }
            if (storedBrand) {
                document.getElementById('brand').value = storedBrand;
            }
            if (storedMinPrice) {
                document.getElementById('minPrice').value = storedMinPrice;
            }
            if (storedMaxPrice) {
                document.getElementById('maxPrice').value = storedMaxPrice;
            }
            if (storedTag) {
                document.getElementById('tag').value = storedTag;
            }
            if(storedSort) {
                document.getElementById('sort').value = storedSort;
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

        window.addEventListener('DOMContentLoaded', function () {
            const urlParams = new URLSearchParams(window.location.search);

            // Gán giá trị từ URL vào các input ẩn trong form
            document.getElementById('category').value = urlParams.get('category') || '';
            document.getElementById('brand').value = urlParams.get('brand') || '';
            document.getElementById('minPrice').value = urlParams.get('minPrice') || '';
            document.getElementById('maxPrice').value = urlParams.get('maxPrice') || '';
            document.getElementById('tag').value = urlParams.get('tag') || '';
            document.getElementById('sort').value = urlParams.get('sort') || '';
        });
        var totalPages = ${model.totalPage};
        var currentPage = ${model.page};
        var limit = ${model.maxPageItem};

        $(function () {
            window.pagObj = $('#pagination').twbsPagination({
                totalPages: totalPages,
                visiblePages: 5,
                startPage: currentPage,
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


<script>
    let listNameProduct = <%= new Gson().toJson(listNames) %>;

    let searchInput = document.getElementById('search-product');

    let suggestions = document.getElementById('suggestions');

    function normalizeString(str) {
        return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase();
    }

    searchInput.addEventListener('input', function () {
        const searchTerm = normalizeString(searchInput.value.trim());
        suggestions.innerHTML = '';
        if(searchTerm.length > 0){
            let matches = listNameProduct.filter(name => normalizeString(name).startsWith(searchTerm)).slice(0, 4);
            if(matches.length > 0){
                suggestions.style.display = 'block';
                matches.forEach(math => {
                    const li = document.createElement('li');
                    li.classList.add('dropdown-item');
                    li.textContent = math;
                    li.addEventListener('click', function (){
                        searchInput.value = this.textContent;
                        suggestions.style.display = 'none';
                    });
                    suggestions.appendChild(li);
                })
            }else {
                suggestions.style.display = 'block';
                    const li = document.createElement('li');
                    li.classList.add('dropdown-item');
                    li.textContent = "không có kết quả";
                    suggestions.appendChild(li);
            }
        }else {
            suggestions.style.display = 'none';

        }
    });

    searchInput.addEventListener('keydown', function (event) {
        if (event.key === 'Enter') {
            event.preventDefault();
            let name = document.getElementById('search-product').value;
            window.location.href = '/danh-sach-san-pham?page=1&maxPageItem=9&ten=' + encodeURIComponent(name);
        }
    });

</script>
<script>
    $('#btnSearch').click(function(e) {
        e.preventDefault();
        let name = document.getElementById('search-product').value;
        window.location.href = '/danh-sach-san-pham?page=1&maxPageItem=9&ten=' + encodeURIComponent(name);

    });

</script>



