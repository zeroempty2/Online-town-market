package com.example.townmarket.common.domain.address.service;

import com.example.townmarket.common.domain.address.dto.AddressDTO;

public interface AddressService {

  AddressDTO getXY(String query);

  String getAddress(double x, double y);

}
