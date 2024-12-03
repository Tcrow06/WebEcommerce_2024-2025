package com.webecommerce.utils;

import com.webecommerce.dto.OrderDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class GHNUtils {

    private static final String TOKEN = "8a1fc6a9-b071-11ef-b613-66a527c813e8";
    private static final String URL_PROVINCE = "https://online-gateway.ghn.vn/shiip/public-api/master-data/province";
    private static final String URL_DISTRICT = "https://online-gateway.ghn.vn/shiip/public-api/master-data/district";
    private static final String URL_WARD = "https://online-gateway.ghn.vn/shiip/public-api/master-data/ward";
    private static final String API_URL = "https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee";
    private static final String SHOP_ID = "5494399";

    private static final Map<String, Integer> provinceMap = new HashMap<>();
    private static final Map<String, Integer> districtMap = new HashMap<>();
    private static final Map<String, Integer> wardMap = new HashMap<>();

    public static double calculateShippingFee(OrderDTO orderDTO) throws Exception {

        String response = buildUrlWithParams(orderDTO);
        return parseTotalFromResponse(response);
    }

    private static Map<String, Object> getDistrictAndWardCode(String cityName, String districtName, String wardName) throws Exception {

        String provinceResponse = getAddressAPI(URL_PROVINCE);
        JSONArray provinces = new JSONObject(provinceResponse).getJSONArray("data");
        for (int i = 0; i < provinces.length(); i++) {
            JSONObject province = provinces.getJSONObject(i);
            provinceMap.put(province.getString("ProvinceName"), province.getInt("ProvinceID"));
        }
        String tmp = cityName.replace("Tỉnh ", "");
        int cityCode = provinceMap.get(tmp);

        String districtResponse = getAddressAPI(URL_DISTRICT + "?province_id=" + cityCode);
        JSONArray districts = new JSONObject(districtResponse).getJSONArray("data");
        for (int i = 0; i < districts.length(); i++) {
            JSONObject district = districts.getJSONObject(i);
            districtMap.put(district.getString("DistrictName"), district.getInt("DistrictID"));
        }
        int districtCode = districtMap.get(districtName);

        String wardResponse = getAddressAPI(URL_WARD + "?district_id=" + districtCode);
        JSONArray wards = new JSONObject(wardResponse).getJSONArray("data");
        for (int i = 0; i < wards.length(); i++) {
            JSONObject ward = wards.getJSONObject(i);
            wardMap.putIfAbsent(ward.getString("WardName"), ward.getInt("WardCode"));
        }

        Map<String, Object> result = new HashMap<>();
        result.put("to_district_id", districtMap.get(districtName));
        result.put("to_ward_code", wardMap.get(wardName));
        return result;
    }


    public static String getAddressAPI(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("token", TOKEN);
        connection.setRequestProperty("Content-Type", "application/json");

        int code = connection.getResponseCode();
        if (code == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            return response.toString();
        } else {
            throw new IOException(connection.getResponseMessage());
        }
    }

    private static String buildUrlWithParams(OrderDTO orderDTO) throws Exception {
        var result = getDistrictAndWardCode(orderDTO.getOrderInfoDTO().getAddress().getCity(),
                orderDTO.getOrderInfoDTO().getAddress().getDistrict(),
                orderDTO.getOrderInfoDTO().getAddress().getCommune());

        // Tạo body của request
        JSONObject requestBody = new JSONObject();
        requestBody.put("service_type_id", 5);
        requestBody.put("from_district_id", 1461);
        requestBody.put("from_ward_code", "21307");
        requestBody.put("to_district_id", (int) result.get("to_district_id"));
        requestBody.put("to_ward_code", result.get("to_ward_code").toString());
        requestBody.put("height", 10);
        requestBody.put("length", 30);
        requestBody.put("weight", 500);
        requestBody.put("width", 40);
        requestBody.put("insurance_value", 120000);
        requestBody.put("coupon", JSONObject.NULL);

        JSONArray itemsArray = new JSONArray();
        JSONObject item = new JSONObject();
        item.put("name", "Quần Áo");
        item.put("quantity", 1);
        item.put("height", 10);
        item.put("weight", 500);
        item.put("length", 30);
        item.put("width", 40);
        itemsArray.put(item);

        requestBody.put("items", itemsArray);


        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("token", TOKEN);
        connection.setRequestProperty("shop_id", SHOP_ID);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);  // Cho phép gửi dữ liệu qua body


        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return response.toString();
            }
        } else {
            throw new RuntimeException("Request failed with code: " + responseCode);
        }

    }


    private static double parseTotalFromResponse(String response) {
        JSONObject responseJson = new JSONObject(response);
        if (responseJson.getInt("code") == 200) {
            JSONObject data = responseJson.getJSONObject("data");
            return data.getDouble("total");
        } else {
            throw new RuntimeException("Error calculating shipping fee: " + responseJson.getString("message"));
        }
    }
}
