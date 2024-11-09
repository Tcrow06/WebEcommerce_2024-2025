<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="sidebar" id="sidebar">
    <div class="sidebar-inner slimscroll">
        <div id="sidebar-menu" class="sidebar-menu">
            <ul>
                <li>
                    <a href="index.html"><img src="../static/admin/assets/img/icons/dashboard.svg" alt="img"><span> Dashboard</span> </a>
                </li>
                <li class="submenu">
                    <a href="javascript:void(0);"><img src="../static/admin/assets/img/icons/product.svg" alt="img"><span> Sản phẩm</span> <span class="menu-arrow"></span></a>
                    <ul>
                        <li><a href="/aadmin/danh-sach-san-pham">Danh sách sản phẩm</a></li>
                        <li><a href="/aadmin/them-san-pham">Thêm sản phẩm</a></li>
                        <li><a href="/aadmin/them-chung-loai">Thêm loại sản phẩm</a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="javascript:void(0);"><img src="../static/admin/assets/img/icons/product.svg" alt="img"><span> Marketing</span> <span class="menu-arrow"></span></a>
                    <ul>
                        <li><a href="/aadmin/giam-gia-cho-san-pham">Thêm giảm giá cho sản phẩm</a></li>
                        <li><a href="/aadmin/giam-gia-cho-don-hang">Thêm giảm giá cho đơn hàng</a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="javascript:void(0);"><img src="../static/admin/assets/img/icons/sales1.svg" alt="img"><span>Đơn hàng</span> <span class="menu-arrow"></span></a>
                    <ul>
                        <li><a href="<c:url value='/admin/danh-sach-don-hang'/> ">Danh sách đơn hàng</a></li>
                        <li><a href="pos.html">Xác nhận đơn hàng</a></li>
                        <li><a href="salesreturnlists.html">Đơn hoàn trả</a></li>
                        <li><a href="createsalesreturns.html">New Sales Return</a></li>
                    </ul>
                </li>
<%--                <li class="submenu">--%>
<%--                    <a href="javascript:void(0);"><img src="../static/admin/assets/img/icons/purchase1.svg" alt="img"><span> Purchase</span> <span class="menu-arrow"></span></a>--%>
<%--                    <ul>--%>
<%--                        <li><a href="purchaselist.html">Purchase List</a></li>--%>
<%--                        <li><a href="addpurchase.html">Add Purchase</a></li>--%>
<%--                        <li><a href="importpurchase.html">Import Purchase</a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
<%--                <li class="submenu">--%>
<%--                    <a href="javascript:void(0);"><img src="../static/admin/assets/img/icons/expense1.svg" alt="img"><span> Expense</span> <span class="menu-arrow"></span></a>--%>
<%--                    <ul>--%>
<%--                        <li><a href="expenselist.html">Expense List</a></li>--%>
<%--                        <li><a href="createexpense.html">Add Expense</a></li>--%>
<%--                        <li><a href="expensecategory.html">Expense Category</a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
                <li class="submenu">
                    <a href="javascript:void(0);"><img src="../static/admin/assets/img/icons/quotation1.svg" alt="img"><span> Tin nhắn</span> <span class="menu-arrow"></span></a>
                    <ul>
                        <li><a href="quotationList.html">Tin nhắn</a></li>
<%--                        <li><a href="addquotation.html">Add Quotation</a></li>--%>
                    </ul>
                </li>
<%--                <li class="submenu">--%>
<%--                    <a href="javascript:void(0);"><img src="../static/admin/assets/img/icons/transfer1.svg" alt="img"><span> Đánh giá</span> <span class="menu-arrow"></span></a>--%>
<%--                    <ul>--%>
<%--                        <li><a href="transferlist.html">Đánh giá từ khách hàng</a></li>--%>
<%--&lt;%&ndash;                        <li><a href="addtransfer.html">Add Transfer </a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <li><a href="importtransfer.html">Import Transfer </a></li>&ndash;%&gt;--%>
<%--                    </ul>--%>
<%--                </li>--%>
<%--                <li class="submenu">--%>
<%--                    <a href="javascript:void(0);"><img src="../static/admin/assets/img/icons/return1.svg" alt="img"><span> Return</span> <span class="menu-arrow"></span></a>--%>
<%--                    <ul>--%>
<%--                        <li><a href="salesreturnlist.html">Sales Return List</a></li>--%>
<%--                        <li><a href="createsalesreturn.html">Add Sales Return </a></li>--%>
<%--                        <li><a href="purchasereturnlist.html">Purchase Return List</a></li>--%>
<%--                        <li><a href="createpurchasereturn.html">Add Purchase Return </a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
<%--                <li class="submenu">--%>
<%--                    <a href="javascript:void(0);"><img src="../static/admin/assets/img/icons/users1.svg" alt="img"><span> People</span> <span class="menu-arrow"></span></a>--%>
<%--                    <ul>--%>
<%--                        <li><a href="customerlist.html">Customer List</a></li>--%>
<%--                        <li><a href="addcustomer.html">Add Customer </a></li>--%>
<%--                        <li><a href="supplierlist.html">Supplier List</a></li>--%>
<%--                        <li><a href="addsupplier.html">Add Supplier </a></li>--%>
<%--                        <li><a href="userlist.html">User List</a></li>--%>
<%--                        <li><a href="adduser.html">Add User</a></li>--%>
<%--                        <li><a href="storelist.html">Store List</a></li>--%>
<%--                        <li><a href="addstore.html">Add Store</a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
<%--                <li class="submenu">--%>
<%--                    <a href="javascript:void(0);"><img src="../static/admin/assets/img/icons/places.svg" alt="img"><span> Places</span> <span class="menu-arrow"></span></a>--%>
<%--                    <ul>--%>
<%--                        <li><a href="newcountry.html">New Country</a></li>--%>
<%--                        <li><a href="countrieslist.html">Countries list</a></li>--%>
<%--                        <li><a href="newstate.html">New State </a></li>--%>
<%--                        <li><a href="statelist.html">State list</a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
<%--                <li>--%>
<%--                    <a href="components.html"><i data-feather="layers"></i><span> Components</span> </a>--%>
<%--                </li>--%>
<%--                <li>--%>
<%--                    <a href="blankpage.html"><i data-feather="file"></i><span> Blank Page</span> </a>--%>
<%--                </li>--%>
<%--                <li class="submenu">--%>
<%--                    <a href="javascript:void(0);"><i data-feather="alert-octagon"></i> <span> Error Pages </span> <span class="menu-arrow"></span></a>--%>
<%--                    <ul>--%>
<%--                        <li><a href="error-404.html">404 Error </a></li>--%>
<%--                        <li><a href="error-500.html">500 Error </a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
<%--                <li class="submenu">--%>
<%--                    <a href="javascript:void(0);"><i data-feather="box"></i> <span>Elements </span> <span class="menu-arrow"></span></a>--%>
<%--                    <ul>--%>
<%--                        <li><a href="sweetalerts.html">Sweet Alerts</a></li>--%>
<%--                        <li><a href="tooltip.html">Tooltip</a></li>--%>
<%--                        <li><a href="popover.html">Popover</a></li>--%>
<%--                        <li><a href="ribbon.html">Ribbon</a></li>--%>
<%--                        <li><a href="clipboard.html">Clipboard</a></li>--%>
<%--                        <li><a href="drag-drop.html">Drag & Drop</a></li>--%>
<%--                        <li><a href="rangeslider.html">Range Slider</a></li>--%>
<%--                        <li><a href="rating.html">Rating</a></li>--%>
<%--                        <li><a href="toastr.html">Toastr</a></li>--%>
<%--                        <li><a href="text-editor.html">Text Editor</a></li>--%>
<%--                        <li><a href="counter.html">Counter</a></li>--%>
<%--                        <li><a href="scrollbar.html">Scrollbar</a></li>--%>
<%--                        <li><a href="spinner.html">Spinner</a></li>--%>
<%--                        <li><a href="notification.html">Notification</a></li>--%>
<%--                        <li><a href="lightbox.html">Lightbox</a></li>--%>
<%--                        <li><a href="stickynote.html">Sticky Note</a></li>--%>
<%--                        <li><a href="timeline.html">Timeline</a></li>--%>
<%--                        <li><a href="form-wizard.html">Form Wizard</a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
<%--                <li class="submenu">--%>
<%--                    <a href="javascript:void(0);"><i data-feather="bar-chart-2"></i> <span> Charts </span> <span class="menu-arrow"></span></a>--%>
<%--                    <ul>--%>
<%--                        <li><a href="chart-apex.html">Apex Charts</a></li>--%>
<%--                        <li><a href="chart-js.html">Chart Js</a></li>--%>
<%--                        <li><a href="chart-morris.html">Morris Charts</a></li>--%>
<%--                        <li><a href="chart-flot.html">Flot Charts</a></li>--%>
<%--                        <li><a href="chart-peity.html">Peity Charts</a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
<%--                <li class="submenu">--%>
<%--                    <a href="javascript:void(0);"><i data-feather="award"></i><span> Icons </span> <span class="menu-arrow"></span></a>--%>
<%--                    <ul>--%>
<%--                        <li><a href="icon-fontawesome.html">Fontawesome Icons</a></li>--%>
<%--                        <li><a href="icon-feather.html">Feather Icons</a></li>--%>
<%--                        <li><a href="icon-ionic.html">Ionic Icons</a></li>--%>
<%--                        <li><a href="icon-material.html">Material Icons</a></li>--%>
<%--                        <li><a href="icon-pe7.html">Pe7 Icons</a></li>--%>
<%--                        <li><a href="icon-simpleline.html">Simpleline Icons</a></li>--%>
<%--                        <li><a href="icon-themify.html">Themify Icons</a></li>--%>
<%--                        <li><a href="icon-weather.html">Weather Icons</a></li>--%>
<%--                        <li><a href="icon-typicon.html">Typicon Icons</a></li>--%>
<%--                        <li><a href="icon-flag.html">Flag Icons</a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
<%--                <li class="submenu">--%>
<%--                    <a href="javascript:void(0);"><i data-feather="columns"></i> <span> Forms </span> <span class="menu-arrow"></span></a>--%>
<%--                    <ul>--%>
<%--                        <li><a href="form-basic-inputs.html">Basic Inputs </a></li>--%>
<%--                        <li><a href="form-input-groups.html">Input Groups </a></li>--%>
<%--                        <li><a href="form-horizontal.html">Horizontal Form </a></li>--%>
<%--                        <li><a href="form-vertical.html"> Vertical Form </a></li>--%>
<%--                        <li><a href="form-mask.html">Form Mask </a></li>--%>
<%--                        <li><a href="form-validation.html">Form Validation </a></li>--%>
<%--                        <li><a href="form-select2.html">Form Select2 </a></li>--%>
<%--                        <li><a href="form-fileupload.html">File Upload </a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
<%--                <li class="submenu">--%>
<%--                    <a href="javascript:void(0);"><i data-feather="layout"></i> <span> Table </span> <span class="menu-arrow"></span></a>--%>
<%--                    <ul>--%>
<%--                        <li><a href="tables-basic.html">Basic Tables </a></li>--%>
<%--                        <li><a href="data-tables.html">Data Table </a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
<%--                <li class="submenu">--%>
<%--                    <a href="javascript:void(0);"><img src="../static/admin/assets/img/icons/product.svg" alt="img"><span> Application</span> <span class="menu-arrow"></span></a>--%>
<%--                    <ul>--%>
<%--                        <li><a href="chat.html">Chat</a></li>--%>
<%--                        <li><a href="calendar.html">Calendar</a></li>--%>
<%--                        <li><a href="email.html">Email</a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
                <li class="submenu">
                    <a href="javascript:void(0);"><img src="../static/admin/assets/img/icons/time.svg" alt="img"><span>Báo cáo</span> <span class="menu-arrow"></span></a>
                    <ul>
                        <li><a href="purchaseorderreport.html">Báo cáo doanh thu</a></li>
                        <li><a href="inventoryreport.html">Đánh giá khách hàng</a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="javascript:void(0);"><img src="../static/admin/assets/img/icons/users1.svg" alt="img"><span> Người dùng</span> <span class="menu-arrow"></span></a>
                    <ul>
                        <li><a href="newuser.html">Khách hàng mới</a></li>
                        <li><a href="userlists.html">Danh sách người dùng</a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="javascript:void(0);"><img src="../static/admin/assets/img/icons/settings.svg" alt="img"><span> Settings</span> <span class="menu-arrow"></span></a>
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