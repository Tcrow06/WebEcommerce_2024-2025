package com.webecommerce.service.impl;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.dao.discount.IBillDiscountDAO;
import com.webecommerce.dao.discount.IProductDiscountDAO;
import com.webecommerce.dao.order.IOrderDAO;
import com.webecommerce.dao.order.IOrderInfoDAO;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.dao.product.IProductVariantDAO;
import com.webecommerce.dto.OrderDTO;
import com.webecommerce.dto.OrderDetailDTO;
import com.webecommerce.dto.PlacedOrder.CheckOutRequestDTO;
import com.webecommerce.dto.PlacedOrder.ProductOrderDTO;
import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.dto.discount.BillDiscountDTO;
import com.webecommerce.dto.notinentity.DisplayOrderDTO;
import com.webecommerce.entity.discount.BillDiscountEntity;
import com.webecommerce.entity.discount.ProductDiscountEntity;
import com.webecommerce.entity.order.OrderDetailEntity;
import com.webecommerce.entity.order.OrderEntity;
import com.webecommerce.entity.order.OrderInfoEntity;
import com.webecommerce.entity.order.OrderStatusEntity;
import com.webecommerce.entity.people.CustomerEntity;
import com.webecommerce.entity.product.ProductVariantEntity;
import com.webecommerce.mapper.Impl.OrderInfoMapper;
import com.webecommerce.mapper.Impl.OrderMapper;
import com.webecommerce.mapper.Impl.ProductDiscountMapper;
import com.webecommerce.mapper.Impl.ProductVariantMapper;
import com.webecommerce.service.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {
    @Inject
    private IProductService productService;

    @Inject
    private IBillDiscountService billDiscountService;

    @Inject
    private IProductVariantDAO productVariantDAO;

    @Inject
    private IBillDiscountDAO billDiscountDAO;


    @Inject
    private ProductVariantService productVariantService;

    @Inject
    private IProductDiscountService productDiscountService;

    @Inject
    private ProductDiscountMapper productDiscountMapper;

    @Inject
    private ProductVariantMapper productVariantMapper;

    @Inject
    private IOrderInfoService orderInfoService;

    @Inject
    private OrderInfoMapper orderInfoMapper;

    @Inject
    private IOrderDAO orderDAO;

    @Inject
    private OrderMapper orderMapper;

    @Inject
    private IOrderInfoDAO orderInfoDAO;

    @Inject
    private IProductDiscountDAO productDiscountDAO;

    @Inject
    private ICustomerDAO customerDAO;



    @Override
    public OrderDTO findInfoCheckOut(CheckOutRequestDTO checkOutRequestDTO) {
        OrderDTO orderDTO = new OrderDTO();
        String status=null;
        StringBuilder message = new StringBuilder();
//        OrderEntity order = new OrderEntity();
//        List<OrderDetailEntity> orderDetails = new ArrayList<>();
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        BillDiscountDTO billDiscountDTO = billDiscountService.findBillDiscountByCode(checkOutRequestDTO.getBillDiscountCode());
        if(!checkOutRequestDTO.getBillDiscountCode().trim().isEmpty()){
            if(billDiscountDTO!=null){
                if(billDiscountDTO.getStartDate().isAfter(LocalDateTime.now())||billDiscountDTO.getEndDate().isBefore(LocalDateTime.now())){
                    status="error";
                    message.append(" Mã giảm giá đã hết hạn, vui lòng chọn mã giảm giá khác <br/>");
                }else{
                    orderDTO.setBillDiscount(billDiscountDTO);
                }
            }else{
                status="error";
                message.append(" Mã giảm giá không hợp lệ, vui lòng chọn mã giảm giá khác <br/>");
            }
        }

        for(ProductOrderDTO product : checkOutRequestDTO.getSelectedProductsId()){
            ProductVariantEntity productVariantEntity = productVariantDAO.findById(product.getProductVariantId());
            ProductVariantDTO productVariantDTO = productVariantMapper.toDTO(productVariantEntity);

            if(productVariantDTO.getQuantity()<product.getQuantity()){
                if(status==null){
                    status="error";
                }
                message.append(productVariantDTO.getName() + " "
                        + productVariantDTO.getColor() +" " + productVariantDTO.getSize()
                        + " chỉ còn: " + productVariantDTO.getQuantity() + " sản phẩm trong kho <br/> ");
            }

            orderDetailDTOS.add(new OrderDetailDTO(product.getQuantity(),productVariantDTO, productDiscountMapper.toDTO(productVariantEntity.getProduct().getProductDiscount())));
        }
        orderDTO.setOrderInfoDTO(orderInfoService.findDefaultOrderInfoByIdUser(checkOutRequestDTO.getIdUser()));
        orderDTO.setOrderDetails(orderDetailDTOS);
        if(!orderDTO.calculateTotal()){
            status="error";
            message.append("Mã giảm giá hóa đơn không đủ điều kiện để áp dụng <br/>");
        }
        if(status==null){
            status ="success";
        }
        orderDTO.setStatus(status);
        orderDTO.setMessage(message);
        return orderDTO;

    }
    @Override
    public OrderDTO findInfoPayment(OrderDTO orderDTO, Long idUser) {
        String status=null;
        StringBuilder message = new StringBuilder();
        try {
            if(orderDTO.getBillDiscount()!=null){
                if(billDiscountDAO.findBillDiscountByCodeAndValid(orderDTO.getBillDiscount().getCode())==null){
                    status ="warning";
                    message.append("Mã giảm giá cho hóa đơn đã hết hạn ! <br/>");
                    orderDTO.setBillDiscount(null);
                    orderDTO.calculateTotal();
                }
            }

            for(OrderDetailDTO orderDetailDTO : orderDTO.getOrderDetails()){
                ProductVariantEntity productVariantEntity = productVariantDAO.findById(orderDetailDTO.getProductVariant().getId());
                if(productVariantEntity.getProduct().getProductDiscount() !=null && orderDetailDTO.getProductDiscount()!=null){
                    if(productVariantEntity.getProduct().getProductDiscount().getEndDate().isBefore(LocalDateTime.now())){
                        orderDetailDTO.setProductDiscount(null);
                        orderDetailDTO.setProductVariant(productVariantMapper.toDTO(productVariantEntity));
                        status = "warning";
                        message.append("Mã giảm giá cho sản phẩm ")
                                .append(productVariantEntity.getProduct().getName())
                                .append(" ")
                                .append(productVariantEntity.getColor())
                                .append(" ")
                                .append(productVariantEntity.getSize())
                                .append(" đã hết hạn! <br/> ");
                    }
                }
                if(productVariantEntity.getQuantity()<orderDetailDTO.getQuantity()){
                    if(status == null ||status.equals("warning")){
                        status="waring+error";
                    }
                    message.append(productVariantEntity.getProduct().getName())
                            .append(" ")
                            .append(productVariantEntity.getColor())
                            .append(" ")
                            .append(productVariantEntity.getSize())
                            .append(" chỉ còn: ")
                            .append(productVariantEntity.getQuantity())
                            .append(" sản phẩm trong kho <br/>");

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        OrderEntity orderEntity;
        if(status == null){
            orderEntity = createOrder(orderDTO,idUser);
            orderDTO = orderMapper.toDTO(orderEntity);
            status ="success";
            message =new StringBuilder("Đặt hàng thành công");
        }else if(status.equals("warning")){
            message.append(" Vui lòng tải lại trang để xem lại hóa đơn!");
        }else{
            orderDTO = new OrderDTO();
        }
        orderDTO.setStatus(status);
        orderDTO.setMessage(message);

        return orderDTO;

    }

    @Override
    public boolean changeConfirmStatus(Long orderId) {
        return orderDAO.changeConfirmStatus(orderId);
    }

    @Transactional
    public OrderEntity createOrder(OrderDTO orderDTO, Long idUser) {
       try {
           OrderEntity orderEntity = new OrderEntity();
           orderEntity.setShippingFee(orderDTO.getShippingFee());


           if(orderDTO.getBillDiscount()!=null){
               BillDiscountEntity billDiscountEntity = billDiscountDAO.findBillDiscountByCodeAndValid(orderDTO.getBillDiscount().getCode());
               orderEntity.setBillDiscount(billDiscountEntity);
           }


           OrderInfoEntity orderInfoEntity = orderInfoMapper.toEntity(orderDTO.getOrderInfoDTO());
           orderEntity.setOrderInfo(orderInfoEntity);


           List<OrderStatusEntity> orderStatusEntities = new ArrayList<>();
           OrderStatusEntity orderStatusEntity = new OrderStatusEntity();
           orderStatusEntity.setDate(LocalDateTime.now());
           orderStatusEntity.setStatus(EnumOrderStatus.PENDING);
           orderStatusEntities.add(orderStatusEntity);
           orderStatusEntity.setOrder(orderEntity);
           orderEntity.setOrderStatuses(orderStatusEntities);


           List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
           for (OrderDetailDTO detailDTO : orderDTO.getOrderDetails()) {
               OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
               orderDetailEntity.setQuantity(detailDTO.getQuantity());

               ProductVariantEntity productVariantEntity = productVariantDAO.findById(detailDTO.getProductVariant().getId());

               productVariantEntity.setQuantity(productVariantEntity.getQuantity()-detailDTO.getQuantity());
               productVariantDAO.update(productVariantEntity);


               orderDetailEntity.setProductVariant(productVariantEntity);

               ProductDiscountEntity productDiscountEntity = productVariantEntity.getProduct().getProductDiscount();
               if (productDiscountEntity!=null&&(productDiscountEntity.getStartDate().isBefore(LocalDateTime.now()) ||
                       productDiscountEntity.getStartDate().isEqual(LocalDateTime.now()))
                       && (productDiscountEntity.getEndDate().isAfter(LocalDateTime.now()) ||
                       productDiscountEntity.getEndDate().isEqual(LocalDateTime.now()))) {
                   orderDetailEntity.setProductDiscount(productDiscountEntity);
               }
               else orderDetailEntity.setProductDiscount(null);

               orderDetailEntity.setOrder(orderEntity);
               orderDetailEntities.add(orderDetailEntity);
           }
           orderEntity.setOrderDetails(orderDetailEntities);
           CustomerEntity customer = customerDAO.findById(idUser);
           orderEntity.setCustomer(customer);

           orderDAO.insert(orderEntity);
           return orderEntity;
       }catch (Exception e){
           e.printStackTrace();
       }
       return null;
    }

    @Override
    public List<DisplayOrderDTO> getOrderDisplay(Long customerId) {
        return orderDAO.getOrderDisplay(customerId);
    }
}
