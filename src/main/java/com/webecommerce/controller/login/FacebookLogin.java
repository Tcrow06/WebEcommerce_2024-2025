package com.webecommerce.controller.login;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.webecommerce.constant.LoginConstant;
import com.webecommerce.dto.request.people.CustomerRequest;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FacebookLogin {
    public static String getToken(String code) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(LoginConstant.FACEBOOK_LINK_GET_TOKEN);
            List<BasicNameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("client_id", LoginConstant.FACEBOOK_CLIENT_ID));
            params.add(new BasicNameValuePair("client_secret", LoginConstant.FACEBOOK_CLIENT_SECRET));
            params.add(new BasicNameValuePair("redirect_uri", LoginConstant.FACEBOOK_REDIRECT_URI));
            params.add(new BasicNameValuePair("code", code));
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            String responeString = httpClient.execute(httpPost, response -> {
                int status = response.getCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            });
            JsonObject object = new Gson().fromJson(responeString, JsonObject.class);
            return object.get("access_token").toString().replaceAll("\"", "");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public static CustomerRequest getUserInfo(final String accessToken) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(LoginConstant.FACEBOOK_LINK_GET_USER_INFO + accessToken);

            String responseString = httpClient.execute(httpGet, response -> {
                int status = response.getCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            });
            JsonObject object = new Gson().fromJson(responseString, JsonObject.class);

            CustomerRequest fbAccount = new CustomerRequest();
            fbAccount.setFbID(object.get("id").getAsString());
            fbAccount.setName(object.get("name").getAsString());
            if (object.has("email")) {
                fbAccount.setEmail(object.get("email").getAsString());
            }
            if (object.has("picture")) {
                JsonObject picObject = object.getAsJsonObject("picture")
                        .getAsJsonObject("data");
                fbAccount.setAvatar(picObject.get("url").getAsString());
            }
            return fbAccount;
        }
    }
}
