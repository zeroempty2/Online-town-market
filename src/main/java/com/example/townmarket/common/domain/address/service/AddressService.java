package com.example.townmarket.common.domain.address.service;

import com.example.townmarket.common.domain.address.dto.AddressResponseDto;

public interface AddressService {

  AddressResponseDto getAddress(double x, double y, Long userId);


}
