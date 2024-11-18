<!-- Header Section Begin -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
    /* Tạo viền mềm và màu đen xám cho dropdown */
    .custom-dropdown-menu {
        width: 220px; /* Tăng độ rộng của dropdown */
        padding: 0.5rem;
        background-color: #fff;
        border: 1px solid #b0b0b0; /* Viền màu đen xám */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15); /* Bóng mờ mềm */
        border-radius: 8px; /* Bo tròn các góc */
    }

    .custom-dropdown-menu .dropdown-item {
        color: #333;
        transition: background-color 0.2s, color 0.2s;
        padding: 10px 15px;
    }

    .custom-dropdown-menu .dropdown-item:hover {
        background-color: #f0f0f5;
        color: #000;
    }

    .custom-dropdown-menu .dropdown-divider {
        margin: 0.4rem 0;
    }

    .custom-dropdown-menu .dropdown-item.text-danger:hover {
        background-color: #f8d7da;
        color: #dc3545;
    }

    /* Thay đổi màu khi click vào avatar */
    #userDropdown .user-avatar {
        cursor: pointer;
        transition: box-shadow 0.3s;
    }

    #userDropdown .user-avatar:hover {
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }
    #chat-container {
        position: fixed;
        bottom: 20px;
        right: 20px;
        z-index: 1000;
    }

    .chat-button {
        background-color: transparent;
        border: none;
        border-radius: 50%;
        cursor: pointer;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }

    .chat-button img {
        display: block;
    }

    .close-chat button {
        background: transparent;
        color: #e53637;
        font-size: 20px;
    }

    .chat-box {
        position: fixed;
        bottom: 80px;
        right: 20px;
        width: 400px;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        display: flex;
        flex-direction: column;
        overflow: hidden;
    }

    .chat-box-header {
        background-color: #000000;
        color: #fff;
        padding: 10px;
        font-size: 16px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .chat-box-content {
        flex-grow: 1;
        padding: 10px;
        overflow-y: auto;
        font-size: 14px;
        max-height: 400px;
        height: 400px;
        border-bottom: 1px solid #ddd;
        box-sizing: border-box;
    }

    .chat-box-input {
        display: flex;
        border-top: 1px solid #ddd;
    }

    .chat-box-input input {
        flex-grow: 1;
        border: none;
        padding: 10px;
        font-size: 14px;
        border-right: 1px solid #ddd;
        outline: none;
    }

    .chat-box-input button {
        background-color: #f58a20;
        border: none;
        color: #fff;
        padding: 10px;
        cursor: pointer;
    }

    .d-none {
        display: none;
    }

    .message-container {
        display: flex;
        margin-bottom: 10px;
        padding: 5px;
    }

    .message-container img.avatar {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        margin-right: 10px;
    }

    .user-message {
        justify-content: flex-end;
    }

    .user-message .avatar {
        order: 1;
        margin-left: 10px;
        margin-right: 0;
    }

    .user-message p {
        background-color: #007bff;
        color: black;
        padding: 10px;
        border-radius: 15px;
        max-width: 250px;
        word-wrap: break-word;
        word-break: break-word;
    }

    .server-message {
        justify-content: flex-start;
    }

    .server-message p {
        background-color: #f1f1f1;
        color: black;
        padding: 10px;
        border-radius: 15px;
        max-width: 250px;
        word-wrap: break-word;
        word-break: break-word;
    }


    #messages {
        max-height: 700px;
        overflow-y: auto;
    }

    .message-container p {
        margin: 0;
        background-color: #f1f1f1;
        color: black;
        padding: 10px;
        border-radius: 10px;
        max-width: 200px;
        word-wrap: break-word;
        word-break: break-word;
    }

</style>
<header class="header">
    <div class="header__top">
        <div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="d-flex justify-content-between align-items-center">
                <div class="header__top__left">
                    <c:if test="${not empty user}">
                        <p>Chào mừng ${user.name} đến với Eleven Store</p>
                    </c:if>
                </div>
                <div class="header__top__right d-flex align-items-center">
                    <div class="header__top__links">
                        <c:if test="${not empty user}">
                            <form action="<c:url value='/dang-xuat'/>" method="post" class="d-inline">
                                <button class="btn-dang-xuat btn btn-link" type="submit">Đăng xuất</button>
                            </form>
                        </c:if>
                        <c:if test="${empty user}">
                            <a href="<c:url value='/dang-nhap'/>">Đăng nhập</a>
                        </c:if>
                        <a href="#">FAQs</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-3">
                <div class="header__logo">
                    <a href="<c:url value="/trang-chu"/>"><img src="<c:url value="/static/img/logo.jpg"/>" alt=""></a>
                </div>
            </div>
            <div class="col-lg-6 col-md-6">
                <nav class="header__menu mobile-menu">
                    <ul>
                        <li class="active"><a href="<c:url value="/trang-chu"/>">Home</a></li>
                        <li><a href="<c:url value="/danh-sach-san-pham?page=1&maxPageItem=9"/>">Shop</a></li>
                        <li><a href="#">Pages</a>
                            <ul class="dropdown">
                                <li><a href="<c:url value="/ve-chung-toi"/>">About Us</a></li>
                                <li><a href="<c:url value="/san-pham?action=ten-san-pham-o-day"/>">Shop Details</a></li>
                                <li><a href="<c:url value="/gio-hang"/>">Shopping Cart</a></li>
                                <li><a href="<c:url value="/thanh-toan"/>">Check Out</a></li>
                            </ul>
                        </li>
                        <li><a href="<c:url value="/blog"/>">Blog</a></li>
                        <li><a href="<c:url value="/ve-chung-toi"/>">Contacts</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3 col-md-3 d-flex justify-content-end align-items-center gap-4" >
                <div class="header__nav__option">
                    <a href="#" class="search-switch"><img src="<c:url value="/static/img/icon/search.png"/>" alt=""></a>
                </div>
                <div class="header__nav__option">
                    <a href="<c:url value="/gio-hang"/>"><img src="<c:url value="/static/img/icon/cart.png"/>" alt=""> <span>0</span></a>
                </div>

                <c:if test="${not empty user}">
                    <div class="header__menu mobile-menu">
                        <ul class="d-flex align-items-center">
                            <li>
                                <a href="#" id="userDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                                    <c:if test="${not empty user.avatar}">
                                        <img src="${user.avatar}" alt="User Profile" width="32" height="32" class="rounded-circle user-avatar">
                                    </c:if>
                                    <c:if test="${empty user.avatar}">
                                        <img src='<c:url value="/static/img/avatar/user.png"/>' alt="User Profile" width="32" height="32" class="rounded-circle user-avatar">
                                    </c:if>
                                </a>

                                <ul class="dropdown-menu custom-dropdown-menu" aria-labelledby="userDropdown">
                                    <li><a class="dropdown-item" href="<c:url value="/trang-chu/thong-tin-ca-nhan"/>">Thông tin tài khoản</a></li>
                                    <li><a class="dropdown-item" href="<c:url value="/trang-chu/don-hang"/>">Đơn hàng của tôi</a></li>
                                    <li><a class="dropdown-item" href="#">Danh sách yêu thích</a></li>
                                    <li><a class="dropdown-item" href="#">Cài đặt</a></li>
                                    <hr class="dropdown-divider">
                                    <li><a class="dropdown-item text-danger" href="#">Đăng xuất</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </c:if>

            </div>
        </div>
        <div class="canvas__open"><i class="fa fa-bars"></i></div>
    </div>

    <div id="chat-container">
        <button id="chat-button" class="chat-button">
            <img src="<c:url value='/static/img/icon/chat.webp' />" alt="Chat" width="40" height="40">
        </button>
        <div id="chat-box" class="chat-box d-none">
            <div class="chat-box-header">
                <span>Hỗ trợ trực tuyến</span>
                <button id="close-chat" class="close-chat">&times;</button>
            </div>
            <div class="chat-box-content" id="messages"></div>
            <div class="chat-box-input">
                <input type="text" id="message" placeholder="Nhập tin nhắn..." id="chat-input" onkeyup="checkEnter(event)">
                <button id="send-chat" class="send-chat" onclick="sendMessage()">Gửi</button>
            </div>
        </div>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", () => {
            const chatButton = document.getElementById("chat-button");
            const chatBox = document.getElementById("chat-box");
            const closeChat = document.getElementById("close-chat");
            const messages = document.getElementById('messages');

            function showWelcomeMessage() {
                var messageContainer = document.createElement('div');
                messageContainer.classList.add('message-container');
                var avatar = document.createElement('img');
                avatar.src = '/static/img/avatar/chatbot.png';
                avatar.classList.add('avatar');
                var messageText = document.createElement('p');
                messageText.textContent = "Nhấn chat để bắt đầu";
                messageContainer.appendChild(avatar);
                messageContainer.appendChild(messageText);
                messages.appendChild(messageContainer);
                messages.scrollTop = messages.scrollHeight;
            }

            chatButton.addEventListener("click", () => {
                chatBox.classList.toggle("d-none");
                if (messages.children.length === 0) {
                    showWelcomeMessage();
                }
            });

            closeChat.addEventListener("click", () => {
                chatBox.classList.add("d-none");
            });
        });

        var socket = new WebSocket('ws://localhost:8080/chat');

        socket.onopen = function() {
            console.log("Connected to the WebSocket server");
        };

        socket.onmessage = function(event) {
            var messages = document.getElementById('messages');
            var messageContainer = document.createElement('div');

            messageContainer.classList.add('message-container', 'server-message');


            var avatar = document.createElement('img');
            avatar.src = '/static/img/avatar/chatbot.png';
            avatar.classList.add('avatar');
            var messageText = document.createElement('p');
            messageText.textContent = event.data;
            messageContainer.appendChild(avatar);
            messageContainer.appendChild(messageText);
            messages.appendChild(messageContainer);
            messages.scrollTop = messages.scrollHeight;
        };

        socket.onclose = function() {
            console.log("Disconnected from the WebSocket server");
        };

        function sendMessage() {
            var message = document.getElementById('message').value;
            if (message != "") {
                socket.send(message);

                var messages = document.getElementById('messages');
                var messageContainer = document.createElement('div');
                messageContainer.classList.add('message-container', 'user-message'); // Thêm lớp CSS cho tin nhắn của người dùng

                var avatar = document.createElement('img');
                avatar.src = '/static/img/avatar/userChat.png'; // Bạn có thể thay đổi avatar của người dùng ở đây
                avatar.classList.add('avatar');

                var messageText = document.createElement('p');
                messageText.textContent = message;

                messageContainer.appendChild(avatar);
                messageContainer.appendChild(messageText);
                messages.appendChild(messageContainer);
                messages.scrollTop = messages.scrollHeight;

                document.getElementById('message').value = '';
            }
        }

        function checkEnter(event) {
            if (event.key === 'Enter') {
                sendMessage();
            }
        }
    </script>
</header>
<!-- Header Section End -->