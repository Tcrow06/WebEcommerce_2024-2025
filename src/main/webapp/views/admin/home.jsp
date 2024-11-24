<%--<jsp:useBean id="revenueChart" scope="request" type=""/>--%>
<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="content">
    <div class="row">
        <div class="col-lg-3 col-sm-6 col-12">
            <div class="dash-widget dash1">
                <div class="dash-widgetimg">
                    <span><img src="<c:url value='/static/admin/assets/img/icons/dash2.svg'/> " alt="img"></span>
                </div>
                <div class="dash-widgetcontent">
                    <h5><span class="counters" data-count="${statistic.revenue}"> ${statistic.revenue}</span> VND</h5>
                    <h6>Tổng doanh thu</h6>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-sm-6 col-12">
            <div class="dash-widget ">
                <div class="dash-widgetimg">
                    <span><img src= "<c:url value='/static/admin/assets/img/icons/dash1.svg'/> " alt="img"></span>
                </div>
                <div class="dash-widgetcontent">
                    <h5><span class="counters" data-count="${statistic.totalOrders}">${statistic.totalOrders}</span></h5>
                    <h6>Tổng số đơn hàng</h6>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-sm-6 col-12">
            <div class="dash-widget dash3">
                <div class="dash-widgetimg">
                    <span><img src="<c:url value='/static/admin/assets/img/icons/dash4.svg'/> " alt="img"></span>
                </div>
                <div class="dash-widgetcontent">
                    <h5><span class="counters" data-count="${statistic.totalReceivedOrders}">${statistic.totalReceivedOrders}</span></h5>
                    <h6>Tổng đơn hàng đã nhận</h6>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-sm-6 col-12">
            <div class="dash-widget dash2">
                <div class="dash-widgetimg">
                    <span><img src="<c:url value='/static/admin/assets/img/icons/dash3.svg'/> " alt="img"></span>
                </div>
                <div class="dash-widgetcontent">
                    <h5><span class="counters" data-count="${statistic.totalOrdersToday}">${statistic.totalOrdersToday}</span></h5>
                    <h6>Tổng số đơn hàng hôm nay</h6>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-sm-6 col-12 d-flex">
            <div class="dash-count">
                <div class="dash-counts">
                    <h4>${statistic.totalCustomers}</h4>
                    <h5>Khách hàng</h5>
                </div>
                <div class="dash-imgs">
                    <i data-feather="user-check"></i>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-sm-6 col-12 d-flex">
            <div class="dash-count das1">
                <div class="dash-counts">
                    <h4>${statistic.totalProducts}</h4>
                    <h5>Sản phẩm</h5>
                </div>
                <div class="dash-imgs">
                    <i data-feather="file"></i>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-sm-6 col-12 d-flex">
            <div class="dash-count das2">
                <div class="dash-counts">
                    <h4>${statistic.totalDiscountProduct}</h4>
                    <h5>Giảm giá sản phẩm</h5>
                </div>
                <div class="dash-imgs">
                    <i data-feather="tag"></i>
                </div>
            </div>
        </div>
        <div class="col-lg-3 col-sm-6 col-12 d-flex">
            <div class="dash-count das3">
                <div class="dash-counts">
                    <h4>${statistic.totalDiscountBill}</h4>
                    <h5>Giảm giá hóa đơn</h5>
                </div>
                <div class="dash-imgs">
                    <i data-feather="tag"></i>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-10 col-sm-12 col-12 d-flex">
            <div class="card flex-fill">
                <div class="card-header pb-0 d-flex justify-content-between align-items-center">
                    <h5 class="card-title mb-0">Doanh thu theo tháng</h5>
                    <div class="graph-sets">
                        <ul>
                            <li>
                                <span id ="revenue"></span>

                            </li>
                            <li>
<%--                                <span id ="revenue"></span>--%>
                            </li>
                        </ul>
                        <div class="dropdown">
                            <button class="btn btn-white btn-sm dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                                ${statistic.thisYear.get(0)} <img src="<c:url value='/static/admin/assets/img/icons/dropdown.svg'/>" alt="img" class="ms-2">
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                <c:forEach var="year" items="${statistic.thisYear}">
                                    <li>
                                        <a href="javascript:void(0);" class="dropdown-item">${year}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div id="s-line"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="card mb-0">
        <div class="card-body">
            <h4 class="card-title">Top 5 sản phẩm bán chạy nhất</h4>
            <div class="table-responsive dataview">
                <table class="table datatable ">
                    <thead>
                    <tr>
                        <th>STT</th>
                        <th>Mã sản phẩm</th>
                        <th>Tên sản phẩm</th>
                        <th>Hãng</th>
                        <th>Phân loại</th>
                        <th>Lượt bán</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${statistic.productDTOBestList}" var="item" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td><a href="javascript:void(0);">SP${item.key.id}</a></td>
                            <td class="productimgname">
                                <a class="product-img" href="productlist.html">
                                    <c:if test="${not empty item.key.photo}">
                                        <img src="<c:url value='/api-image?path=${item.key.photo}'/>" alt="product">
                                    </c:if>
                                </a>
                                <a href="productlist.html">${item.key.name}</a>
                            </td>
                            <td>${item.key.brand}</td>
                            <td>${item.key.category.name}</td>
                            <td>${item.value}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <c:forEach items="${revenueChart}" var="item" varStatus="status">
                        <input id="${item.key}" name="${item.key}" value="${item.value}" type="hidden">
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <div class="card mb-0">
        <div class="card-body">
            <h4 class="card-title">Các sản phẩm có lượt bán thất nhất</h4>
            <div class="table-responsive dataview">
                <table class="table datatable ">
                    <thead>
                    <tr>
                        <th>STT</th>
                        <th>Mã sản phẩm</th>
                        <th>Tên sản phẩm</th>
                        <th>Hãng</th>
                        <th>Phân loại</th>
                        <th>Lượt bán</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${statistic.productDTOLowestList}" var="item" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td><a href="javascript:void(0);">SP${item.key.id}</a></td>
                            <td class="productimgname">
                                <a class="product-img" href="productlist.html">
                                    <c:if test="${not empty item.key.photo}">
                                        <img src="<c:url value='/api-image?path=${item.key.photo}'/>" alt="product">
                                    </c:if>
                                </a>
                                <a href="productlist.html">${item.key.name}</a>
                            </td>
                            <td>${item.key.brand}</td>
                            <td>${item.key.category.name}</td>
                            <td>${item.value}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <c:forEach items="${revenueChart}" var="item" varStatus="status">
                        <input id="${item.key}" name="${item.key}" value="${item.value}" type="hidden">
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value='/static/admin/assets/js/jquery-3.6.0.min.js'/>"></script>
<%--<script src="<c:url value='/static/admin/assets/js/chart/chart-data.js'/>"></script>--%>
<script src="<c:url value='/static/admin/assets/js/chart/apexcharts.min.js'/>"></script>

<script>

    $(document).ready(function () {
        var currentYear = parseInt($("#dropdownMenuButton").text().trim());
        updateChart(currentYear);
        var monthlyRevenue = [];
        let revenue;
        function updateChart(year) {
            $.ajax({
                url: '/thong-ke',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({ year: year }),
                success: function (response) {
                    // Giả sử server trả về một mảng doanh thu theo tháng
                    if (response && response.monthlyRevenue) {
                        monthlyRevenue = response.monthlyRevenue;
                        revenue=response.revenue;
                        console.log(monthlyRevenue);
                        chart.updateSeries([{
                            name: "VND",
                            data: monthlyRevenue
                        }]);
                        document.getElementById('revenue').innerText = "Doanh thu : " + revenue + " VND";
                    }
                },
                error: function (xhr, status, error) {
                    console.error("Error fetching data:", error);
                }
            });
        }

        // Gắn sự kiện click vào các mục trong dropdown
        $(".dropdown-menu").on("click", ".dropdown-item", function () {
            var selectedYear = $(this).text().trim(); // Lấy năm đã chọn
            $("#dropdownMenuButton").html(selectedYear + ' <img src="<c:url value='/static/admin/assets/img/icons/dropdown.svg'/>" alt="img" class="ms-2">'); // Cập nhật nút dropdown
            updateChart(parseInt(selectedYear)); // Chuyển đổi năm thành int và gọi hàm cập nhật biểu đồ
        });

        // Khởi tạo biểu đồ ApexCharts
        var sline = {
            chart: {
                height: 350,
                type: 'line',
                zoom: { enabled: false },
                toolbar: { show: false }
            },
            dataLabels: { enabled: false },
            stroke: { curve: 'straight' },
            series: [{ name: "VND", data: monthlyRevenue }],
            title: { text: '', align: 'left' },
            grid: { row: { colors: ['#f1f2f3', 'transparent'], opacity: 0.5 } },
            xaxis: {
                categories: [
                    'Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6',
                    'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'
                ]
            }
        };
        var chart = new ApexCharts(document.querySelector("#s-line"), sline);
        chart.render();
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
