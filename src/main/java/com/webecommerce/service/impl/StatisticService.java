package com.webecommerce.service.impl;

import com.webecommerce.dao.order.IOrderDAO;
import com.webecommerce.dao.people.ICustomerDAO;
import com.webecommerce.dto.ProductDTO;
import com.webecommerce.dto.StatisticDTO;
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



    @Override
    public StatisticDTO calculateHomeAdmin(int year) {
        StatisticDTO statisticDTO = new StatisticDTO();
        List<Map.Entry<ProductDTO, Integer>> list = productService.findBestSellerProduct(5);
        Double revenue = orderDAO.calculateTotalRevenue();
        int totalProducts = productService.totalProducts();
        statisticDTO.setTotalProducts(totalProducts);
        statisticDTO.setRevenue(revenue);
        statisticDTO.setProductDTOList(list);
        statisticDTO.setTotalOrdersToday(orderDAO.totalOrdersToday());
        statisticDTO.setTotalOrders(orderDAO.totalOrders());
        statisticDTO.setTotalCustomers(customerDAO.totalCustiomers());
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
        List<Double> list = new ArrayList<>(Collections.nCopies(12, 0.0)); // Khởi tạo danh sách 12 phần tử giá trị 0.0

        for (Object[] result : results) {
            Integer month = (Integer) result[0];       // Tháng (1-12)
            Double totalRevenue = (Double) result[1]; // Doanh thu của tháng
            list.set(month - 1, totalRevenue);        // Đặt giá trị doanh thu vào vị trí tương ứng (tháng - 1)
        }
        return list;
    }
}
