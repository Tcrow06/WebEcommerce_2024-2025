package com.webecommerce.service;

import com.webecommerce.dto.OrderInfoDTO;

public interface IOrderInfoService {
    OrderInfoDTO findDefaultOrderInfoByIdUser(Long idUser);

}
