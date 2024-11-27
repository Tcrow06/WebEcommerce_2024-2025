<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>

<div class="container-fluid d-flex justify-content-center align-items-center" style="height: 100vh; max-height: 550px; background-color:#f0f1f2; position: relative;">
    <div class="card bg-transparent" style="width: 75rem; border: none;">
        <div class="row">
            <div class="col-5 d-flex justify-content-center align-items-center">
                <img src="<c:url value='/static/img/tet.jpg'/>" class="card-img-top" alt="Tết sale promotion" style="border-radius: 24px;">
            </div>
            <div class="col-7 d-flex justify-content-center align-items-center">
                <div class="card-body">
                    <p class="fw-bold text-warning">Chào xuân 2025 !</p>
                    <h4 class="card-title text-danger"
                        style="font-size: 2rem; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3); font-family: 'Times New Roman', Times, serif;">
                        Tết hạnh phúc, <span class="text-warning" style="font-size: 60px;">Săn sale liền tay!</span>
                    </h4>
                    <p class="card-text"
                       style="font-size: 1.2rem; line-height: 1.6; color: black; font-weight: 500; font-family: 'Times New Roman', Times, serif;">
                        Khám phá những ưu đãi Tết hấp dẫn, săn sale cực hot và tận hưởng không khí mua sắm rộn ràng.
                        <span style="color: #de9197; font-weight: bold;">Mua sắm Tết chưa bao giờ dễ dàng đến
                                    thế</span>,
                        đón xuân với những món quà tuyệt vời và tiết kiệm nhất!
                    </p>
                </div>
            </div>
        </div>
    </div>

</div>
<div class="triangle mb-5" style="width: 0;
    height: 0;
    border-right: 100vw solid transparent;
    border-top: 200px solid #f0f1f2;
    position: absolute;
    bottom: 0;"></div>


<section class="list-voucher" style="margin-top: 300px; margin-bottom: 200px">
    <div class="container mt-5 mb-5">
        <h4 class="mb-4">Danh Sách Mã Giảm Giá</h4>
        <ul class="nav nav-tabs" id="discountTabs" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link active" id="all-discount-tab" data-bs-toggle="tab"
                        data-bs-target="#all-discount" type="button" role="tab" aria-controls="all-discount"
                        aria-selected="false">Tất cả</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link " id="product-discount-tab" data-bs-toggle="tab"
                        data-bs-target="#product-discount" type="button" role="tab" aria-controls="product-discount"
                        aria-selected="true">Mã giảm giá sản phẩm</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="bill-discount-tab" data-bs-toggle="tab" data-bs-target="#bill-discount"
                        type="button" role="tab" aria-controls="bill-discount" aria-selected="false">Mã giảm giá đơn
                    hàng</button>
            </li>
        </ul>

        <div class="tab-content mt-3" id="discountTabsContent">
            <div class="tab-pane fade show active" id="all-discount" role="tabpanel"
                 aria-labelledby="all-discount-tab">
                <div class="card">
                    <c:forEach items="${billDiscountList}" var="o">
                        <div class="card-body border mb-3 mx-3 mt-3">
                            <div class="row">
                                <div class="col-3">
                                    <img src="<c:url value='/static/img/voucher/bill.png'/>">
                                </div>
                                <div class="col-9">
                                    <div class="row ps-1">
                                        <h5 class="fw-bold">${o.name}</h5>
                                    </div>
                                    <div class="row">
                                        <strong>${o.code}</strong><br>
                                        <p class="text-secondary-emphasis" data-time="true">Thời hạn: ${o.startDate}
                                            - ${o.endDate}</p>
                                    </div>
                                    <c:choose>
                                        <c:when test="${currentDate >= o.startDate and currentDate <= o.endDate}">
                                            <p class="text-success fw-bold">Đang diễn ra</p>
                                        </c:when>
                                        <c:when test="${currentDate < o.startDate}">
                                            <p class="text-warning fw-bold">Sắp diễn ra</p>
                                        </c:when>
                                        <c:otherwise>
                                            <p class="text-danger fw-bold">Đã hết hạn</p>
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="row">
                                        <h6>• Giảm: ${o.discountPercentage}%</h6>
                                    </div>
                                    <div id="extraContent${o.code}" class="collapse"
                                         data-minimum-invoice-amount="${o.minimumInvoiceAmount}"
                                         data-maximum-amount="${o.maximumAmount}">
                                        <h6>• Áp dụng với đơn hàng trên: ${o.minimumInvoiceAmount} VND.</h6>
                                        <h6>• Số tiền giảm tối đa: ${o.maximumAmount} VND.</h6>
                                    </div>
                                    <div class="row">
                                        <div class="col-9">
                                            <button type="button" class="btn btn-link p-0 text-decoration-none"
                                                    data-bs-toggle="collapse" data-bs-target="#extraContent${o.code}"
                                                    aria-expanded="false" aria-controls="extraContent${o.code}"
                                                    onclick="toggleButtonText(this)">Xem chi tiết ⬎</button>
                                        </div>
                                        <div class="col-3">
                                            <button type="button" class="btn btn-dark w-100 mb-2 ms-auto" onclick="copyToClipboard(this)" data-code="${o.code}">Sao chép</button>
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <c:forEach items="${productDiscountList}" var="o">
                        <div class="card-body border mb-3 p-3 mx-3 mt-3">
                            <div class="row">
                                <div class="col-3">
                                    <img src="<c:url value='/static/img/voucher/product.png'/>">
                                </div>
                                <div class="col-9">
                                    <div class="row ps-1">
                                        <h5 class="fw-bold">${o.name}</h5>
                                    </div>
                                    <div class="row">
                                        <strong></strong><br>
                                        <p class="text-secondary-emphasis" data-time="true">Thời hạn: ${o.startDate}
                                            - ${o.endDate}</p>
                                    </div>
                                    <c:choose>
                                        <c:when test="${currentDate >= o.startDate and currentDate <= o.endDate}">
                                            <p class="text-success fw-bold">Đang diễn ra</p>
                                        </c:when>
                                        <c:when test="${currentDate < o.startDate}">
                                            <p class="text-warning fw-bold">Sắp diễn ra</p>
                                        </c:when>
                                        <c:otherwise>
                                            <p class="text-danger fw-bold">Đã hết hạn</p>
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="row mt-4">
                                        <div class="col-9">
                                            <h6>• Giảm: ${o.discountPercentage}%</h6>
                                        </div>
                                        <div class="col-3">
                                            <button type="button" class="btn btn-dark w-100 mb-2 ms-auto" onclick="copyToClipboard(this)" data-code="${o.name}">Sao chép</button>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="tab-pane fade" id="product-discount" role="tabpanel" aria-labelledby="product-discount-tab">
                <div class="card">
                    <div class = "row mt-3">
                        <div class="col-8"></div>
                        <div class = "col-4">
                            <form class="d-flex" role="search" action="/danh-sach-ma-giam-gia" method="GET">
                                <input type="hidden" name="activeTab" id="activeTab" value="product-discount">
                                <div class="row">
                                    <div class="col-7">
                                        <input type="search" class="form-control" name="productName" placeholder="Nhập tên sản phẩm..."
                                               value="${param.productName}">
                                    </div>
                                    <div class="col-5">
                                        <button type="submit" class="btn btn-outline-success" onclick="setProductTab()">Tìm kiếm</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${searchMode}">
                            <c:forEach items="${searchDiscount}" var="o">
                                <div class="card-body border mb-3 p-3 mx-3 mt-3">
                                    <div class="row">
                                        <div class="col-3">
                                            <img src="<c:url value='/static/img/voucher/product.png'/>">
                                        </div>
                                        <div class="col-9">
                                            <div class="row ps-1">
                                                <h5 class="fw-bold">${o.name}</h5>
                                            </div>
                                            <div class="row">
                                                <strong></strong><br>
                                                <p class="text-secondary-emphasis" data-time="true">Thời hạn:
                                                        ${o.startDate} - ${o.endDate}</p>
                                            </div>
                                            <c:choose>
                                                <c:when test="${currentDate >= o.startDate and currentDate <= o.endDate}">
                                                    <p class="text-success fw-bold">Đang diễn ra</p>
                                                </c:when>
                                                <c:when test="${currentDate < o.startDate}">
                                                    <p class="text-warning fw-bold">Sắp diễn ra</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <p class="text-danger fw-bold">Đã hết hạn</p>
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="row mt-4">
                                                <div class="col-9">
                                                    <h6>• Giảm: ${o.discountPercentage}%</h6>
                                                </div>
                                                <div class="col-3">
                                                    <button type="button" class="btn btn-dark w-100 mb-2 ms-auto" onclick="copyToClipboard(this)" data-code="${o.name}">Sao chép</button>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${productDiscountList}" var="o">
                                <div class="card-body border mb-3 p-3 mx-3 mt-3">
                                    <div class="row">
                                        <div class="col-3">
                                            <img src="<c:url value='/static/img/voucher/product.png'/>">
                                        </div>
                                        <div class="col-9">
                                            <div class="row ps-1">
                                                <h5 class="fw-bold">${o.name}</h5>
                                            </div>
                                            <div class="row">
                                                <strong></strong><br>
                                                <p class="text-secondary-emphasis" data-time="true">Thời hạn:
                                                        ${o.startDate} - ${o.endDate}</p>
                                            </div>
                                            <c:choose>
                                                <c:when test="${currentDate >= o.startDate and currentDate <= o.endDate}">
                                                    <p class="text-success fw-bold">Đang diễn ra</p>
                                                </c:when>
                                                <c:when test="${currentDate < o.startDate}">
                                                    <p class="text-warning fw-bold">Sắp diễn ra</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <p class="text-danger fw-bold">Đã hết hạn</p>
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="row mt-4">
                                                <div class="col-9">
                                                    <h6>• Giảm: ${o.discountPercentage}%</h6>
                                                </div>
                                                <div class="col-3">
                                                    <button type="button" class="btn btn-dark w-100 mb-2 ms-auto" onclick="copyToClipboard(this)" data-code="${o.name}">Sao chép</button>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="tab-pane fade" id="bill-discount" role="tabpanel" aria-labelledby="bill-discount-tab">
                <div class="card">
                    <c:forEach items="${billDiscountList}" var="o">
                        <div class="card-body border mb-3 mx-3 mt-3">
                            <div class="row">
                                <div class="col-3">
                                    <img src="<c:url value='/static/img/voucher/bill.png'/>">
                                </div>
                                <div class="col-9">
                                    <div class="row ps-1">
                                        <h5 class="fw-bold">${o.name}</h5>
                                    </div>
                                    <div class="row">
                                        <strong>${o.code}</strong><br>
                                        <p class="text-secondary-emphasis" data-time="true">Thời hạn: ${o.startDate}
                                            - ${o.endDate}</p>
                                    </div>
                                    <c:choose>
                                        <c:when test="${currentDate >= o.startDate and currentDate <= o.endDate}">
                                            <p class="text-success fw-bold">Đang diễn ra</p>
                                        </c:when>
                                        <c:when test="${currentDate < o.startDate}">
                                            <p class="text-warning fw-bold">Sắp diễn ra</p>
                                        </c:when>
                                        <c:otherwise>
                                            <p class="text-danger fw-bold">Đã hết hạn</p>
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="row">
                                        <h6>• Giảm: ${o.discountPercentage}%</h6>
                                    </div>
                                    <div id="extraContent${o.code}" class="collapse"
                                         data-minimum-invoice-amount="${o.minimumInvoiceAmount}"
                                         data-maximum-amount="${o.maximumAmount}">
                                        <h6>• Áp dụng với đơn hàng trên: ${o.minimumInvoiceAmount} VND.</h6>
                                        <h6>• Số tiền giảm tối đa: ${o.maximumAmount} VND.</h6>
                                    </div>
                                    <div class="row">
                                        <div class="col-9">
                                            <button type="button" class="btn btn-link p-0 text-decoration-none"
                                                    data-bs-toggle="collapse" data-bs-target="#extraContent${o.code}"
                                                    aria-expanded="false" aria-controls="extraContent${o.code}"
                                                    onclick="toggleButtonText(this)">Xem chi tiết ⬎</button>
                                        </div>
                                        <div class="col-3">
                                            <button type="button" class="btn btn-dark w-100 mb-2 ms-auto" onclick="copyToClipboard(this)" data-code="${o.code}">Sao chép</button>
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
<script>"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"</script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Lấy giá trị tab hiện tại từ hidden input và hiển thị tab đó
        const activeTab = document.querySelector("#activeTab").value;
        const activeTabButton = document.querySelector(`#${activeTab}-tab`);
        if (activeTabButton) {
            const tabInstance = new bootstrap.Tab(activeTabButton);
            tabInstance.show();
        }

        // Gán sự kiện cho các tab để cập nhật activeTab khi chuyển tab
        const tabs = document.querySelectorAll("#discountTabs button[data-bs-toggle='tab']");
        tabs.forEach(tab => {
            tab.addEventListener("shown.bs.tab", function () {
                document.querySelector("#activeTab").value = tab.id.replace("-tab", "");
            });
        });

        // Tự động kích hoạt tab sản phẩm nếu searchMode là true
        const searchMode = ${searchMode}; // Giá trị từ backend, ví dụ: true hoặc false
        if (searchMode) {
            const productTabButton = document.querySelector('#product-discount-tab');
            const productTabContent = new bootstrap.Tab(productTabButton);
            productTabContent.show();
            document.querySelectorAll('.tab-pane').forEach(tabContent => {
                tabContent.classList.remove('active', 'show');
            });
            const productTabPane = document.querySelector('#product-discount');
            productTabPane.classList.add('active', 'show');
        }

        // Xử lý tìm kiếm tự động khi input tìm kiếm rỗng
        const searchInput = document.querySelector("input[name='productName']");
        const searchForm = document.querySelector("form[action='/danh-sach-ma-giam-gia']");
        if (searchInput && searchForm) {
            searchInput.addEventListener("blur", function () {
                if (searchInput.value.trim() === "") {
                    searchForm.submit(); // Gửi form khi nội dung input rỗng
                }
            });
        }

        // Xử lý thay đổi kiểu tìm kiếm khi chọn dropdown item
        document.querySelectorAll("#searchCriteria .dropdown-item").forEach(item => {
            item.addEventListener("click", function(event) {
                event.stopPropagation(); // Ngăn chặn dropdown đóng lại ngay lập tức
                var searchCriteria = this.getAttribute("data-value");
                var searchInput = document.getElementById("searchInput");

                // Thay đổi kiểu input dựa trên lựa chọn
                if (searchCriteria === "time") {
                    searchInput.type = "date";
                    searchInput.placeholder = "Chọn ngày";
                } else if (searchCriteria === "percent") {
                    searchInput.type = "number";
                    searchInput.placeholder = "Nhập phần trăm";
                } else if (searchCriteria === "code") {
                    searchInput.type = "text";
                    searchInput.placeholder = "Nhập mã giảm giá";
                } else {
                    searchInput.type = "text";
                    searchInput.placeholder = "Nhập thông tin tìm kiếm...";
                }
                searchInput.value = "";
            });
        });

        // Xử lý sao chép mã giảm giá vào clipboard
        document.querySelectorAll("[data-code]").forEach(button => {
            button.addEventListener("click", function() {
                const codeToCopy = this.getAttribute('data-code');
                if (!codeToCopy) {
                    alert('Không có mã giảm giá để sao chép.');
                    return;
                }
                console.log("Mã giảm giá: ", codeToCopy);
                navigator.clipboard.writeText(codeToCopy).then(function() {
                    alert('Đã sao chép mã giảm giá: ' + codeToCopy);
                }).catch(function(err) {
                    alert('Không thể sao chép mã giảm giá: ' + err);
                });
            });
        });

        // Xử lý thay đổi giá trị của ô tìm kiếm theo kiểu tìm kiếm
        document.getElementById('search-type').addEventListener('change', changeSearchInput);

        // Tự động tìm kiếm khi nhấn nút tìm kiếm
        document.getElementById("searchButton1").addEventListener("click", function(event) {
            event.preventDefault(); // Ngăn không để form submit ngay lập tức
            var voucherName = document.getElementById("voucherName").value;

            // Cập nhật activeTab khi nhấn nút tìm kiếm
            document.querySelector("#activeTab").value = "product-discount"; // Hoặc giá trị tương ứng nếu cần

            // Gửi yêu cầu GET đến /gio-hang với tham số voucherName
            fetch(`/gio-hang?voucherName=${encodeURIComponent(voucherName)}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                })
                .catch(error => {
                    console.error('Lỗi khi gửi yêu cầu:', error);
                });
        });
    });
</script>
