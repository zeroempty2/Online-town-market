package com.example.townmarket.common.domain.address;

import com.example.townmarket.common.domain.address.dto.AddressDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/user/api")
@RequiredArgsConstructor
@Slf4j
public class ApiController {

  private final AddressService addressService;

  @GetMapping("/search")
  public ModelAndView address() {
    return new ModelAndView("addressButton");
  }


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

//    // deprecated
//    @PutMapping("/getRestaurantData")
//    public void getRestaurantData(GetRestaurantRequest request, HttpServletResponse response){
//        try {
//            String bounds = String.format("%s;%s;%f;%f",
//                    request.getX(),
//                    request.getY(),
//                    Double.parseDouble(request.getX())+0.0241399,
//                    Double.parseDouble(request.getY())+0.0193742);
//            log.info("bounds : "+bounds);
//            request.setBounds(bounds);
//            restaurantService.getRestaurantData(request);
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
