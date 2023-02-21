package com.example.townmarket.common.domain.address.controller;

import com.example.townmarket.common.domain.address.dto.AddressDTO;
import com.example.townmarket.common.domain.address.service.AddressServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class AddressController {

  private final AddressServiceImpl addressService;

  // 위경도를 통해 주소 반환
  @GetMapping("/search/reverse-geo")
  public String getFullAddress(AddressDTO request) {
    String result = addressService.getAddress(request.getX(), request.getY());
    log.info(request.toString());
    return result;
  }

  // 주소를 통해 위경도 반환
  @GetMapping("/search/geo")
  public AddressDTO getLonLat(@RequestParam(value = "fullAddress") String fullAddress) {
    log.info("full address : " + fullAddress);
    return addressService.getXY(fullAddress);
  }
}
