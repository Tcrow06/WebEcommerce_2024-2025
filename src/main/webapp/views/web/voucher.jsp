<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
    .voucher-card {
        border: 2px solid #6c757d;
        border-radius: 10px;
        overflow: hidden;
        margin: 10px 0;
        background-color: #fff7f7;
    }

    .voucher-card .discount {
        background-color: #6c757d;
        color: #fff;
        font-size: 1.5rem;
        font-weight: bold;
        padding: 10px;
        text-align: center;
    }

    .voucher-card .voucher-content {
        padding: 15px;
    }

    .voucher-title {
        font-weight: bold;
        color: #333;
    }

    .voucher-expiration {
        color: #888;
        font-size: 0.9rem;
    }

    .voucher-code {
        background-color: #f1f1f1;
        padding: 5px 10px;
        border-radius: 5px;
        color: #6c757d;
        font-weight: bold;
        display: inline-block;
        margin-top: 10px;
        cursor: pointer;
    }

    .voucher-code:hover {
        background-color: #6c757d;
        color: #fff;
    }
</style>

<section class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__text">
                    <h4>Voucher</h4>
                    <div class="breadcrumb__links">
                        <a href="<c:url value="/trang-chu"/>">Home</a>
                        <span>Voucher</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<div class="d-flex">

    <div class="sidebar m-3">
        <div class="d-flex flex-column" style=" height:100%;">
            <nav class="nav flex-column">
                <a class="link-offset-2 m-2 link-underline link-underline-opacity-0" href="<c:url value="/thong-bao"/>" style = "color:black" >Cập Nhật Đơn Hàng</a>
                <a class="link-offset-2 m-2 link-underline link-underline-opacity-0" href="<c:url value="/phieu-giam-gia"/>" style = "color:#f53d2d" >Kho Voucher</a>
            </nav>
        </div>
    </div>
    <div class="container">
        <c:if test="${not empty discountList}">
            <div class="row">
                <c:forEach items="${discountList}" var="dc">
                    <div class="col-md-12">
                        <div class="voucher-card d-flex">
                            <div class="discount col-4">
                                    ${dc.discountPercentage}% OFF
                            </div>
                            <div class="voucher-content col-8">
                                <h5 class="voucher-title">${dc.name}</h5>
                                <span class="voucher-code" onclick="copyCode('${dc.code}')">${dc.code}</span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <c:if test="${empty discountList}">
            <p>No discounts available at this time.</p>
        </c:if>
    </div>
</div>

<script>
    function copyCode(code) {
        navigator.clipboard.writeText(code);
        alert("Đã sao chép mã: " + code);
    }
</script>

