<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="sidebar" id="sidebar">
    <div class="sidebar-inner slimscroll">
        <div id="sidebar-menu" class="sidebar-menu">
            <ul>
                <li>
                    <a href="/chu-cua-hang"><img src="/static/admin/assets/img/icons/dashboard.svg" alt="img"><span> Trang chủ</span> </a>
                </li>
                <li class="submenu">
                    <a href="javascript:void(0);"><img src="/static/admin/assets/img/icons/product.svg" alt="img"><span> Sản phẩm</span> <span class="menu-arrow"></span></a>
                    <ul>
                        <li><a href="/chu-cua-hang/danh-sach-san-pham">Danh sách sản phẩm</a></li>
                        <li><a href="/chu-cua-hang/them-san-pham">Thêm sản phẩm</a></li>
                        <li><a href="/chu-cua-hang/them-chung-loai">Thêm loại sản phẩm</a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="javascript:void(0);"><img src="/static/admin/assets/img/icons/product.svg" alt="img"><span> Marketing</span> <span class="menu-arrow"></span></a>
                    <ul>
                        <li><a href="/chu-cua-hang/giam-gia-cho-san-pham">Giảm giá cho sản phẩm</a></li>
                        <li><a href="/chu-cua-hang/giam-gia-cho-don-hang">Giảm giá cho đơn hàng</a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="javascript:void(0);"><img src="/static/admin/assets/img/icons/sales1.svg" alt="img"><span>Đơn hàng</span> <span class="menu-arrow"></span></a>
                    <ul>
                        <li><a href="<c:url value='/chu-cua-hang/danh-sach-don-hang'/> ">Danh sách đơn hàng</a></li>
                        <li><a href="<c:url value='/chu-cua-hang/xac-nhan-don-hang'/> ">Xác nhận đơn hàng</a></li>
                        <li><a href="<c:url value='/chu-cua-hang/danh-sach-tra'/>">Đơn hoàn trả</a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="javascript:void(0);"><img src="/static/admin/assets/img/icons/quotation1.svg" alt="img"><span> Tin nhắn</span> <span class="menu-arrow"></span></a>
                    <ul>
                        <li><a href="quotationList.html">Tin nhắn</a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="javascript:void(0);"><img src="/static/admin/assets/img/icons/time.svg" alt="img"><span>Báo cáo</span> <span class="menu-arrow"></span></a>
                    <ul>
                        <li><a href="purchaseorderreport.html">Báo cáo doanh thu</a></li>
                        <li><a href="inventoryreport.html">Đánh giá khách hàng</a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="javascript:void(0);"><img src="/static/admin/assets/img/icons/users1.svg" alt="img"><span> Người dùng</span> <span class="menu-arrow"></span></a>
                    <ul>
                        <li><a href="<c:url value='/chu-cua-hang/quan-ly-nguoi-dung'/>">Danh sách người dùng</a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="javascript:void(0);"><img src="/static/admin/assets/img/icons/settings.svg" alt="img"><span>Cài đặt</span> <span class="menu-arrow"></span></a>
                    <ul>
                        <li><a href="generalsettings.html">General Settings</a></li>
                        <li><a href="emailsettings.html">Email Settings</a></li>
                        <li><a href="paymentsettings.html">Payment Settings</a></li>
                        <li><a href="currencysettings.html">Currency Settings</a></li>
                        <li><a href="grouppermissions.html">Group Permissions</a></li>
                        <li><a href="taxrates.html">Tax Rates</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>