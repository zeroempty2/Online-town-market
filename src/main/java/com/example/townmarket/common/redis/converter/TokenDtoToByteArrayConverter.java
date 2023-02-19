package com.example.townmarket.common.redis.converter;
import com.example.townmarket.common.redis.dto.TokenDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

@Component
@WritingConverter
@RequiredArgsConstructor
public class TokenDtoToByteArrayConverter implements Converter<TokenDto, byte[]> {

  private final ObjectMapper objectMapper;

  @Override
  public byte[] convert(TokenDto source) {
    // TokenDto 객체를 직렬화하여 바이트 배열로 변환하는 코드
    try {
      String json = objectMapper.writeValueAsString(source);
      return json.getBytes();
    } catch (Exception e) {
      throw new RuntimeException("error converting");
    }
  }


  public TokenDto convertTokenDto(byte[] bytes){
    if (bytes != null) {
      Jackson2JsonRedisSerializer<TokenDto> serializer = new Jackson2JsonRedisSerializer<>(TokenDto.class);
      return serializer.deserialize(bytes);
    }
    return null;
  }
}
