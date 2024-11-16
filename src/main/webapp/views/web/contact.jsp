<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style>

</style>

<div class="map">
    <iframe src="https://www.google.com/maps/embed?pb=!1m17!1m12!1m3!1d979.6236840681074!2d106.77183026945552!3d10.849925316462642!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m2!1m1!2zMTDCsDUwJzU5LjciTiAxMDbCsDQ2JzIwLjkiRQ!5e0!3m2!1svi!2s!4v1731306323826!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
</div>

<!-- Contact Section Begin -->
<section class="contact spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-md-6">
                <div class="contact__text">
                    <div class="section-title">
                        <span>Phần thông tin</span>
                        <h2>Liên hệ với chúng tôi</h2>
                        <p style="text-align: justify;">Đội ngũ chăm sóc khách hàng luôn sẵn sàng hỗ trợ bạn mọi lúc, từ việc tư vấn chọn lựa trang phục cho đến việc giải đáp thắc mắc về các chương trình ưu đãi. Chúng tôi cam kết mang đến cho bạn trải nghiệm mua sắm tuyệt vời và dịch vụ chuyên nghiệp. Hãy liên hệ ngay hôm nay để nhận được sự trợ giúp nhanh chóng và hiệu quả!.</p>
                    </div>
                    <ul>
                        <li>
                            <h4>TP Hồ Chí Minh</h4>
                            <p>04 Việt Thắng, Thủ Đức<br />+84 946-353-123</p>
                        </li>
                        <li>
                            <h4>Hà Nội</h4>
                            <p>16 Nguyễn Trãi, Thanh Xuân<br />+84 946-353-345</p>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-6 col-md-6">
                <div class="contact__form">
                    <form action="/sendEmail" method="POST">
                        <div class="row">
                            <div class="col-lg-6">
                                <input type="text" name="name" placeholder="Tên" required>
                            </div>
                            <div class="col-lg-6">
                                <input type="text" name="email" placeholder="Email" required>
                            </div>
                            <div class="col-lg-12">
                                <textarea name="messageContent" placeholder="Nội dung" required></textarea>
                                <button type="submit" class="site-btn">Gửi lời nhắn</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>



</html>