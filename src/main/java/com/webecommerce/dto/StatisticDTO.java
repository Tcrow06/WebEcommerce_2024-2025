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
    private List<Map.Entry<ProductDTO, Integer>> productDTOBestList = new ArrayList<>();
    private double revenue;
    private int totalCustomers;
    private int totalOrders;
    private int totalProducts;
    private int totalOrdersToday;
    private List<Integer> thisYear;
    private int totalReceivedOrders;

    private int totalDiscountProduct;
    private int totalDiscountBill;


    private List<Map.Entry<ProductDTO, Integer>> productDTOLowestList = new ArrayList<>();

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


    public List<Map.Entry<String, Double>> getRevenueListByMonthInYear() {
        return revenueListByMonthInYear;
    }

    public void setRevenueListByMonthInYear(List<Map.Entry<String, Double>> revenueListByMonthInYear) {
        this.revenueListByMonthInYear = revenueListByMonthInYear;
    }

    public List<Map.Entry<ProductDTO, Integer>> getProductDTOBestList() {
        return productDTOBestList;
    }

    public void setProductDTOBestList(List<Map.Entry<ProductDTO, Integer>> productDTOBestList) {
        this.productDTOBestList = productDTOBestList;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
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

    public int getTotalReceivedOrders() {
        return totalReceivedOrders;
    }

    public void setTotalReceivedOrders(int totalReceivedOrders) {
        this.totalReceivedOrders = totalReceivedOrders;
    }

    public List<Map.Entry<ProductDTO, Integer>> getProductDTOLowestList() {
        return productDTOLowestList;
    }

    public void setProductDTOLowestList(List<Map.Entry<ProductDTO, Integer>> productDTOLowestList) {
        this.productDTOLowestList = productDTOLowestList;
    }

    public int getTotalDiscountProduct() {
        return totalDiscountProduct;
    }

    public void setTotalDiscountProduct(int totalDiscountProduct) {
        this.totalDiscountProduct = totalDiscountProduct;
    }

    public int getTotalDiscountBill() {
        return totalDiscountBill;
    }

    public void setTotalDiscountBill(int totalDiscountBill) {
        this.totalDiscountBill = totalDiscountBill;
    }
}
