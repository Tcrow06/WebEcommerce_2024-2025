package com.webecommerce.service.impl;

import com.webecommerce.constant.EnumOrderStatus;
import com.webecommerce.constant.EnumProductStatus;
import com.webecommerce.dao.discount.IBillDiscountDAO;
import com.webecommerce.dao.discount.IProductDiscountDAO;
import com.webecommerce.dao.order.IOrderDAO;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.dao.product.IProductDAO;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.StatisticDTO;
import com.webecommerce.service.IBillDiscountService;
import com.webecommerce.service.IProductService;
import com.webecommerce.service.IStatisticService;

import javax.inject.Inject;
import java.util.*;

public class StatisticService implements IStatisticService {

    @Inject
    private IOrderDAO orderDAO;
    @Inject
    private IProductService productService;

    @Inject
    private ICustomerDAO customerDAO;

    @Inject
    private IBillDiscountDAO billDiscountDAO;

    @Inject
    private IProductDiscountDAO productDiscountDAO;

    @Override
    public StatisticDTO calculateHomeAdmin() {
        StatisticDTO statisticDTO = new StatisticDTO();
        List<Map.Entry<ProductDTO, Integer>> list = productService.findBestSellerProduct(5);
        Double revenue = orderDAO.calculateTotalRevenue();
        int totalProducts = productService.totalProducts();
        statisticDTO.setTotalProducts(totalProducts);
        statisticDTO.setRevenue(revenue);
        statisticDTO.setProductDTOBestList(list);
        statisticDTO.setProductDTOLowestList(productService.findLowestSellingProducts(5));
        statisticDTO.setTotalOrdersToday(orderDAO.totalOrdersToday());
        statisticDTO.setTotalOrders(orderDAO.totalOrdersByStatus(EnumOrderStatus.PENDING));
        statisticDTO.setTotalReceivedOrders(orderDAO.totalOrdersByStatus(EnumOrderStatus.RECEIVED));
        statisticDTO.setTotalCustomers(customerDAO.totalCustomers());
        statisticDTO.setTotalProducts(productService.countByStatus(EnumProductStatus.SELLING));
        statisticDTO.setTotalDiscountBill(billDiscountDAO.countDiscountValid());
        statisticDTO.setTotalDiscountProduct(productDiscountDAO.countDiscountValid());

        return statisticDTO;
    }

    @Override
    public List<Map.Entry<Integer, Double>>  calculateMonthlyRevenue(int year) {
        List<Object[]> results = orderDAO.calculateMonthlyRevenue(year);

        // Tạo một Map để lưu dữ liệu doanh thu, khởi tạo tất cả các tháng với giá trị 0.0
        Map<Integer, Double> monthlyRevenueMap = new LinkedHashMap<>();
        for (int i = 1; i <= 12; i++) {
            monthlyRevenueMap.put(i, 0.0);
        }

        // Điền dữ liệu từ kết quả query vào map
        for (Object[] result : results) {
            Integer month = (Integer) result[0];
            Double totalRevenue = (Double) result[1];
            monthlyRevenueMap.put(month, totalRevenue);
        }

        // Chuyển Map thành List<Map.Entry<Integer, Double>>
        return new ArrayList<>(monthlyRevenueMap.entrySet());
    }

    @Override
    public List<Double> calculateMonthlyRevenues(int year) {
        List<Object[]> results = orderDAO.calculateMonthlyRevenue(year);
        List<Double> list = new ArrayList<>(Collections.nCopies(12, 0.0));
        for (Object[] result : results) {
            Integer month = (Integer) result[0];
            Double totalRevenue = (Double) result[1];
            list.set(month - 1, totalRevenue);
        }
        return list;
    }
}
