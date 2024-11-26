<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
    /* Ẩn toàn bộ chat container khi trang tải */
    #chat-container {
        display: none;
    }

    /* Chat box mặc định ẩn */
    #chat-box {
        display: none;
        position: fixed;
        bottom: 20px;
        right: 20px;
        width: 300px;
        height: 400px;
        border: 1px solid #ccc;
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        z-index: 1000;
    }

    /* Style cho header của chat box */
    .chat-box-header {
        background-color: #007bff;
        color: white;
        padding: 10px;
        text-align: center;
    }

    /* Style cho button đóng chat box */
    .close-chat {
        background-color: transparent;
        border: none;
        color: white;
        font-size: 20px;
        cursor: pointer;
    }

    /* Style cho nội dung chat */
    .chat-box-content {
        height: 300px;
        padding: 10px;
        overflow-y: auto;
        background-color: #f9f9f9;
    }

    /* Style cho input chat */
    .chat-box-input {
        display: flex;
        padding: 10px;
        background-color: #f1f1f1;
    }

    .chat-box-input input {
        flex: 1;
        padding: 5px;
        margin-right: 10px;
    }

    .send-chat {
        padding: 5px 10px;
        background-color: #007bff;
        color: white;
        border: none;
        cursor: pointer;
    }

    /* Style cho chat button (nút mở chat) */
    .chat-button {
        position: fixed;
        bottom: 20px;
        right: 20px;
        background-color: #007bff;
        color: white;
        border: none;
        padding: 15px;
        cursor: pointer;
        border-radius: 50%;
    }

    .chat-button img {
        width: 30px;
        height: 30px;
    }
</style>
<div class="header">

    <div class="header-left active">
        <a href="/chu-cua-hang" class="logo">
            <img src="../static/admin/assets/img/logo.png" alt="">
        </a>
        <a href="index.html" class="logo-small">
            <img src="../static/admin/assets/img/logo-small.png" alt="">
        </a>
        <a id="toggle_btn" href="javascript:void(0);">
        </a>
    </div>

    <a id="mobile_btn" class="mobile_btn" href="#sidebar">
          <span class="bar-icon">
            <span></span>
            <span></span>
            <span></span>
          </span>
    </a>

    <ul class="nav user-menu">

        <li class="nav-item">
            <div class="top-nav-search">
                <a href="javascript:void(0);" class="responsive-search">
                    <i class="fa fa-search"></i>
                </a>
                <form action="#">
                    <div class="searchinputs">
                        <input type="text" placeholder="Search Here ...">
                        <div class="search-addon">
                            <span><img src="/static/admin/assets/img/icons/closes.svg" alt="img"></span>
                        </div>
                    </div>
                    <a class="btn" id="searchdiv"><img src="/static/admin/assets/img/icons/search.svg" alt="img"></a>
                </form>
            </div>
        </li>


        <li class="nav-item dropdown has-arrow flag-nav">
            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="javascript:void(0);" role="button">
                <img src="/static/admin/assets/img/flags/us1.png" alt="" height="20">
            </a>
            <div class="dropdown-menu dropdown-menu-right">
                <a href="javascript:void(0);" class="dropdown-item">
                    <img src="/static/admin/assets/img/flags/us.png" alt="" height="16"> English
                </a>
                <a href="javascript:void(0);" class="dropdown-item">
                    <img src="/static/admin/assets/img/flags/fr.png" alt="" height="16"> French
                </a>
                <a href="javascript:void(0);" class="dropdown-item">
                    <img src="/static/admin/assets/img/flags/es.png" alt="" height="16"> Spanish
                </a>
                <a href="javascript:void(0);" class="dropdown-item">
                    <img src="/static/admin/assets/img/flags/de.png" alt="" height="16"> German
                </a>
            </div>
        </li>


        <li class="nav-item dropdown">
            <a href="javascript:void(0);" class="dropdown-toggle nav-link" data-bs-toggle="dropdown">
                <img src="/static/admin/assets/img/icons/notification-bing.svg" alt="img"> <span class="badge rounded-pill">4</span>
            </a>
            <div class="dropdown-menu notifications">
                <div class="topnav-dropdown-header">
                    <span class="notification-title">Notifications</span>
                    <a href="javascript:void(0)" class="clear-noti"> Clear All </a>
                </div>
                <div class="noti-content">
                    <ul class="notification-list">
                        <li class="notification-message">
                            <a href=/static/admin/"activities.html">
                                <div class="media d-flex">
                        <span class="avatar flex-shrink-0">
                          <img alt="" src="../static/admin/assets/img/profiles/avatar-02.jpg">
                        </span>
                                    <div class="media-body flex-grow-1">
                                        <p class="noti-details"><span class="noti-title">John Doe</span> added new task <span class="noti-title">Patient appointment booking</span></p>
                                        <p class="noti-time"><span class="notification-time">4 mins ago</span></p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="notification-message">
                            <a href="/static/admin/activities.html">
                                <div class="media d-flex">
                        <span class="avatar flex-shrink-0">
                          <img alt="" src="/static/admin/assets/img/profiles/avatar-03.jpg">
                        </span>
                                    <div class="media-body flex-grow-1">
                                        <p class="noti-details"><span class="noti-title">Tarah Shropshire</span> changed the task name <span class="noti-title">Appointment booking with payment gateway</span></p>
                                        <p class="noti-time"><span class="notification-time">6 mins ago</span></p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="notification-message">
                            <a href="/static/admin/activities.html">
                                <div class="media d-flex">
                        <span class="avatar flex-shrink-0">
                          <img alt="" src="/static/admin/assets/img/profiles/avatar-06.jpg">
                        </span>
                                    <div class="media-body flex-grow-1">
                                        <p class="noti-details"><span class="noti-title">Misty Tison</span> added <span class="noti-title">Domenic Houston</span> and <span class="noti-title">Claire Mapes</span> to project <span class="noti-title">Doctor available module</span></p>
                                        <p class="noti-time"><span class="notification-time">8 mins ago</span></p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="notification-message">
                            <a href="activities.html">
                                <div class="media d-flex">
                        <span class="avatar flex-shrink-0">
                          <img alt="" src="/static/admin/assets/img/profiles/avatar-17.jpg">
                        </span>
                                    <div class="media-body flex-grow-1">
                                        <p class="noti-details"><span class="noti-title">Rolland Webber</span> completed task <span class="noti-title">Patient and Doctor video conferencing</span></p>
                                        <p class="noti-time"><span class="notification-time">12 mins ago</span></p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="notification-message">
                            <a href="activities.html">
                                <div class="media d-flex">
                        <span class="avatar flex-shrink-0">
                          <img alt="" src="/static/admin/assets/img/profiles/avatar-13.jpg">
                        </span>
                                    <div class="media-body flex-grow-1">
                                        <p class="noti-details"><span class="noti-title">Bernardo Galaviz</span> added new task <span class="noti-title">Private chat module</span></p>
                                        <p class="noti-time"><span class="notification-time">2 days ago</span></p>
                                    </div>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="topnav-dropdown-footer">
                    <a href="activities.html">View all Notifications</a>
                </div>
            </div>
        </li>

        <li class="nav-item dropdown has-arrow main-drop">
            <a href="javascript:void(0);" class="dropdown-toggle nav-link userset" data-bs-toggle="dropdown">
              <span class="user-img"><img src="/static/admin/assets/img/profiles/avator1.jpg" alt="">
                <span class="status online"></span></span>
            </a>
            <div class="dropdown-menu menu-drop-user">
                <div class="profilename">
                    <div class="profileset">
                  <span class="user-img"><img src="/static/admin/assets/img/profiles/avator1.jpg" alt="">
                    <span class="status online"></span></span>
                        <div class="profilesets">
                            <h6>John Doe</h6>
                            <h5>Admin</h5>
                        </div>
                    </div>
                    <hr class="m-0">
                    <a class="dropdown-item" href="<c:url value="/chu-cua-hang/quan-ly-trang-ca-nhan"/>"> <i class="me-2" data-feather="user"></i> My Profile</a>
                    <a class="dropdown-item" href="generalsettings.html"><i class="me-2" data-feather="settings"></i>Settings</a>
                    <hr class="m-0">
                    <a class="dropdown-item logout pb-0" href="signin.html"><img src="/static/admin/assets/img/icons/log-out.svg" class="me-2" alt="img">Logout</a>
                </div>
            </div>
        </li>
    </ul>


    <div class="dropdown mobile-user-menu">
        <a href="javascript:void(0);" class="nav-link dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
        <div class="dropdown-menu dropdown-menu-right">
            <a class="dropdown-item" href="profile.html">My Profile</a>
            <a class="dropdown-item" href="generalsettings.html">Settings</a>
            <a class="dropdown-item" href="signin.html">Logout</a>
        </div>
    </div>

    <div id="chat-container">
        <button id="chat-button" class="chat-button" size="0">
            <img src="<c:url value='/static/img/icon/chat.webp' />" alt="Chat" width="0" height="0">
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
        document.getElementById('chatRoomInput').addEventListener('keydown', checkEnter);
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
        function checkEnter(event) {
            if (event.key === 'Enter') {
                event.preventDefault(); // Ngăn chặn hành động mặc định của Enter (tạo dòng mới)
                sendMessage(); // Gọi hàm gửi tin nhắn
            }
        }

        // Gửi tin nhắn
        sendChatRoomBtn.addEventListener("click", sendMessage);

        // Hàm gửi tin nhắn
        function sendMessage() {
            const message = chatRoomInput.value.trim();
            if (message) {
                chatRoomSocket.send(message);
                chatRoomInput.value = ""; // Xóa ô nhập sau khi gửi
            }
        }
    </script>


</div>