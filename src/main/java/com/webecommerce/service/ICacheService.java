package com.webecommerce.service;

public interface ICacheService {
    void setKey(String key, String value, long ttlInSeconds);
    String getKey(String key);
    long getTtlInSeconds(String key);
    void increment(String key);
    void delete(String key);
}
