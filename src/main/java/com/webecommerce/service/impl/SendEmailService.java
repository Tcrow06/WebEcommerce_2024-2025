package com.webecommerce.service.impl;

import com.webecommerce.configuration.VNPayConfig;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.dto.OrderDTO;
import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.service.ISendEmailService;
import com.webecommerce.utils.EmailUtils;

import javax.inject.Inject;

public class SendEmailService implements ISendEmailService {

    @Inject
    private ICustomerDAO customerDAO;

    @Override
    public void sendEmail(OrderDTO orderDTO, Long userId) {
        String recipientEmail = customerDAO.findById(userId).getEmail();

        String subject = "Hóa đơn mua hàng của bạn";
        String content = buildEmailContent(orderDTO);

        EmailUtils.sendEmail(recipientEmail, subject, content);
    }

    // Hàm xây dựng nội dung HTML cho email
    private String buildEmailContent(OrderDTO orderDTO) {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<style>");
        html.append("table { width: 100%; border-collapse: collapse; }");
        html.append("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
        html.append("th { background-color: #f2f2f2; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");

        html.append("<h2>Hóa đơn mua hàng</h2>");
        html.append("<p>Cảm ơn bạn đã mua sắm tại ElevenShop. Dưới đây là thông tin hóa đơn của bạn:</p>");

        // Thông tin tổng quan hóa đơn
        html.append("<p><strong>Mã hóa đơn:</strong> ").append(VNPayConfig.getRandomNumber(8)).append("</p>");
        html.append("<p><strong>Tổng tiền:</strong> ").append(orderDTO.getTotal()).append(" VND</p>");
        html.append("<p><strong>Giảm giá:</strong> ").append(orderDTO.getMaximumDiscount()).append(" VND</p>");
        html.append("<p><strong>Phí vận chuyển:</strong> ").append(orderDTO.getShippingFee()).append(" VND</p>");
        html.append("<p><strong>Trạng thái:</strong> ").append("Đang trong quá trình xác nhận").append("</p>");

        // Bảng danh sách sản phẩm
        html.append("<h3>Danh sách sản phẩm:</h3>");
        html.append("<table>");
        html.append("<thead>");
        html.append("<tr>");
        html.append("<th>Tên sản phẩm</th>");
        html.append("<th>Số lượng</th>");
        html.append("<th>Đơn giá</th>");
        html.append("<th>Tổng</th>");
        html.append("</tr>");
        html.append("</thead>");
        html.append("<tbody>");
        for (OrderDetailDTO detail : orderDTO.getOrderDetails()) {
            html.append("<tr>");
            html.append("<td>").append(detail.getProductVariant().getName()).append("</td>");
            html.append("<td>").append(detail.getQuantity()).append("</td>");
            html.append("<td>").append(detail.getProductVariant().getPrice()).append(" VND</td>");
            html.append("<td>").append(detail.getQuantity() * detail.getProductVariant().getPrice()).append(" VND</td>");
            html.append("</tr>");
        }
        html.append("</tbody>");
        html.append("</table>");

        html.append("<p>Nếu bạn có bất kỳ câu hỏi nào, vui lòng liên hệ với chúng tôi qua email hoặc hotline.</p>");
        html.append("<p>Trân trọng,</p>");
        html.append("<p>Đội ngũ hỗ trợ khách hàng</p>");

        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }
}
