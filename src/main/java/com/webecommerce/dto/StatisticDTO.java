package com.webecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatisticDTO {
    private List<Map.Entry<String, Double>> revenueListByMonthInYear = new ArrayList<>();
    private List<Map.Entry<ProductDTO, Integer>> productDTOList = new ArrayList<>();
    private double revenue;
    private double revenueByYear;
    private int brandNumber;
    private int totalCustomers;
    private int totalOrders;
    private int totalProducts;
    private int totalOrdersToday;
    private List<Integer> thisYear;

    public StatisticDTO() {
        List<Integer> years = new ArrayList<>();
        int year = LocalDate.now().getYear(); // Lấy năm hiện tại
        for (int i = 0; i < 10; i++) {
            years.add(year - i); // Thêm các năm từ hiện tại về trước
        }
        this.thisYear = years;

    }

    public List<Integer> getThisYear() {
        return thisYear;
    }

    public void setThisYear(List<Integer> thisYear) {
        this.thisYear = thisYear;
    }

    public int getTotalOrdersToday() {
        return totalOrdersToday;
    }

    public void setTotalOrdersToday(int totalOrdersToday) {
        this.totalOrdersToday = totalOrdersToday;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public double getRevenueByYear() {
        return revenueByYear;
    }

    public void setRevenueByYear(double revenueByYear) {
        this.revenueByYear = revenueByYear;
    }

    public List<Map.Entry<String, Double>> getRevenueListByMonthInYear() {
        return revenueListByMonthInYear;
    }

    public void setRevenueListByMonthInYear(List<Map.Entry<String, Double>> revenueListByMonthInYear) {
        this.revenueListByMonthInYear = revenueListByMonthInYear;
    }

    public List<Map.Entry<ProductDTO, Integer>> getProductDTOList() {
        return productDTOList;
    }

    public void setProductDTOList(List<Map.Entry<ProductDTO, Integer>> productDTOList) {
        this.productDTOList = productDTOList;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public int getBrandNumber() {
        return brandNumber;
    }

    public void setBrandNumber(int brandNumber) {
        this.brandNumber = brandNumber;
    }


    public int getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

}
