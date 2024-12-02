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
        max-width: 200px;
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
        max-width: 200px;
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
                    <a href="<c:url value="/trang-chu"/>"><img src="<c:url value="/static/img/logoshop.jpg"/>" alt=""></a>
                </div>
            </div>
            <div class="col-lg-6 col-md-6">
                <nav class="header__menu mobile-menu">
                    <ul>
                        <li class="active"><a href="<c:url value="/trang-chu"/>">Trang chủ</a></li>
                        <li><a href="<c:url value="/danh-sach-san-pham?page=1&maxPageItem=9"/>">Thời trang</a></li>
                        <li><a href="<c:url value="/blog"/>">Bài viết</a></li>
                        <li><a href="<c:url value="/ve-chung-toi"/>">Liên hệ</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3 col-md-3 d-flex justify-content-end align-items-center gap-4" >
                <div class="header__nav__option">
                    <a href="#" class="search-switch"><img src="<c:url value="/static/img/icon/search.png"/>" alt=""></a>
                </div>
                <div class="header__nav__option">
                    <a href="<c:url value="/thong-bao"/>"><img src="<c:url value="/static/img/icon/bell.png"/>" alt=""> </a>
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

                                    <li><a class="dropdown-item" href="<c:url value="/thong-tin-ca-nhan"/>">Thông tin tài khoản</a></li>
                                    <li><a class="dropdown-item" href="<c:url value="/trang-chu/don-hang"/>">Đơn hàng của tôi</a></li>
                                    <li><a class="dropdown-item" href="<c:url value="/danh-sach-ma-giam-gia"/>">Kho mã giảm giá</a></li>
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

    <button id="chatRoomBtn"
            style="position: fixed; right: 20px; top: 50%; transform: translateY(-50%) rotate(-90deg); transform-origin: right center; z-index: 1001; white-space: nowrap; background-color: #f58a20; color: white; border: none; padding: 10px 20px; font-size: 14px; cursor: pointer;">
        Thảo luận
    </button>

    <div id="chatRoom"
         style="display: none; position: fixed; right: 20px; top: 15px; width: 300px; height: calc(100vh - 30px); background: #f9f9f9; border: 1px solid #ccc; z-index: 1000; padding: 10px; overflow: hidden;">
        <div id="chatRoomMessages"
             style="height: 90%; overflow-y: scroll; margin-bottom: 10px;"></div>
        <div style="display: flex; align-items: center; gap: 5px; margin: 0;">
            <input id="chatRoomInput"
                   type="text"
                   placeholder="Nhập tin nhắn..."
                   style="width: 80%; padding: 5px; margin: 0; box-sizing: border-box; vertical-align: middle;">
            <button id="sendChatRoomBtn"
                    style="width: 15%; padding: 5px; margin: 0; box-sizing: border-box; background-color: #f58a20; color: white; border: none; font-size: 14px; cursor: pointer;">
                Gửi
            </button>
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
                messageText.textContent = "Chào bạn! Bạn muốn hỏi về vấn đề gì? (ví dụ: sản phẩm, vận chuyển, hỗ trợ, một mặt hàng cụ thể, ...)";
                messageContainer.appendChild(avatar);
                messageContainer.appendChild(messageText);
                messages.appendChild(messageContainer);
                messages.scrollTop = messages.scrollHeight;
            }

            loadMessagesFromSession();

            // Mở hoặc đóng hộp chat
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

        // WebSocket kết nối tới server
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

            // Lưu tin nhắn trả về từ server vào sessionStorage
            const currentChatHistory = JSON.parse(localStorage.getItem('chatHistory')) || [];
            currentChatHistory.push({ sender: "server", message: event.data });
            localStorage.setItem('chatHistory', JSON.stringify(currentChatHistory));

        };

        socket.onclose = function() {
            console.log("Disconnected from the WebSocket server");
        };

        // Gửi tin nhắn từ người dùng
        function sendMessage() {
            var message = document.getElementById('message').value;
            if (message != "") {
                socket.send(message);

                var messages = document.getElementById('messages');
                var messageContainer = document.createElement('div');
                messageContainer.classList.add('message-container', 'user-message');

                var avatar = document.createElement('img');
                avatar.src = '/static/img/avatar/userChat.png';
                avatar.classList.add('avatar');

                var messageText = document.createElement('p');
                messageText.textContent = message;

                messageContainer.appendChild(avatar);
                messageContainer.appendChild(messageText);
                messages.appendChild(messageContainer);
                messages.scrollTop = messages.scrollHeight;

                // Lưu tin nhắn của người dùng vào sessionStorage
                const currentChatHistory = JSON.parse(localStorage.getItem('chatHistory')) || [];
                currentChatHistory.push({ sender: "user", message: message });
                localStorage.setItem('chatHistory', JSON.stringify(currentChatHistory));

                document.getElementById('message').value = '';
            }
        }

        // Gửi tin nhắn từ session vào giao diện
        function sendMessageFromSession(messageObj) {
            var messages = document.getElementById('messages');
            var messageContainer = document.createElement('div');

            // Phân biệt tin nhắn của server và người dùng
            if (messageObj.sender === "user") {
                messageContainer.classList.add('message-container', 'user-message');
                var avatar = document.createElement('img');
                avatar.src = '/static/img/avatar/userChat.png'; // Tin nhắn của người dùng
            } else {
                messageContainer.classList.add('message-container', 'server-message');
                var avatar = document.createElement('img');
                avatar.src = '/static/img/avatar/chatbot.png'; // Tin nhắn từ server
            }

            avatar.classList.add('avatar');

            var messageText = document.createElement('p');
            messageText.textContent = messageObj.message;

            messageContainer.appendChild(avatar);
            messageContainer.appendChild(messageText);
            messages.appendChild(messageContainer);
            // messages.scrollTop = messages.scrollHeight;
        }

        // Tải tin nhắn từ sessionStorage
        function loadMessagesFromSession() {
            const savedMessages = JSON.parse(localStorage.getItem('chatHistory')) || [];
            savedMessages.forEach((messageObj) => {
                sendMessageFromSession(messageObj);
            });
        }

        // Kiểm tra khi người dùng nhấn Enter
        function checkEnter(event) {
            if (event.key === 'Enter') {
                sendMessage();
            }
        }

        // Gắn sự kiện cho phím Enter trong trường nhập tin nhắn
        document.getElementById('message').addEventListener('keydown', checkEnter);






        function getCookie(name) {
            const value = "; " + document.cookie;  // Lấy tất cả cookies
            const parts = value.split("; " + name + "=");  // Tìm kiếm cookie có tên bằng name
            if (parts.length === 2) return parts.pop().split(";").shift();  // Trả về giá trị cookie nếu tìm thấy
            return null;  // Trả về null nếu không tìm thấy cookie
        }

        // Lấy token từ cookie
        const token = getCookie("token");

        document.getElementById("chatRoomBtn").addEventListener("click", () => {
            if (token) {
                const chatRoom = document.getElementById("chatRoom");
                chatRoom.style.display = chatRoom.style.display === "none" ? "block" : "none";
            } else {
                alert("Bạn cần phải có tài khoản để tham gia thảo luận!")
            }
        });

        const chatRoomMessages = document.getElementById("chatRoomMessages");
        const chatRoomInput = document.getElementById("chatRoomInput");
        const sendChatRoomBtn = document.getElementById("sendChatRoomBtn");
        document.getElementById('chatRoomInput').addEventListener('keydown', checkEnterChat);
        let wsUrl = "ws://localhost:8080/chat-room";  // URL cơ bản của WebSocket
        if (token) {
            wsUrl += `?token=` + token;  // Nếu có token, thêm nó vào URL
        }
        // Kết nối WebSocket đến endpoint chat room
        const chatRoomSocket = new WebSocket(wsUrl);

        chatRoomSocket.onmessage = (event) => {
            const message = document.createElement("p");
            message.textContent = event.data;

            if (event.data.includes("Chủ cửa hàng") || event.data.includes("Hệ thống")) {
                message.style.color = "red"; // Đổi màu chữ thành đỏ
            } else {
                message.style.color = "black"; // Màu chữ đen cho tin nhắn bình thường
            }

            chatRoomMessages.appendChild(message);
            chatRoomMessages.scrollTop = chatRoomMessages.scrollHeight; // Tự động cuộn xuống cuối
        };

        // Hàm kiểm tra phím Enter
        function checkEnterChat(event) {
            if (event.key === 'Enter') {
                event.preventDefault(); // Ngăn chặn hành động mặc định của Enter (tạo dòng mới)
                sendMessageChat(); // Gọi hàm gửi tin nhắn
            }
        }

        // Gửi tin nhắn
        sendChatRoomBtn.addEventListener("click", sendMessageChat);

        // Hàm gửi tin nhắn
        function sendMessageChat() {
            const message = chatRoomInput.value.trim();
            if (message) {
                chatRoomSocket.send(message);
                chatRoomInput.value = ""; // Xóa ô nhập sau khi gửi
            }
        }

    </script>

</header>
<!-- Header Section End -->