package com.webecommerce.service;

import com.webecommerce.dto.ProductVariantDTO;
import com.webecommerce.dto.StatisticDTO;

import java.util.List;
import java.util.Map;

public interface IStatisticService {
    StatisticDTO calculateHomeAdmin();
    List<Map.Entry<Integer, Double>>  calculateMonthlyRevenue(int year);
    List<Double> calculateMonthlyRevenues(int year);


}
