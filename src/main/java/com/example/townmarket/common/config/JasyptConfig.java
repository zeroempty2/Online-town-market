package com.example.townmarket.common.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableEncryptableProperties
public class JasyptConfig {

//  @Value("${jasypt.encryptor.password}")
//  private String encryptKey;

  @Bean("jasyptStringEncryptor")
  public StringEncryptor stringEncryptor() {
    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
//    config.setPassword(encryptKey); //암호화에 사용할 키 -> 중요
    config.setPassword(getJasyptEncryptorPassword());
    config.setAlgorithm("PBEWithMD5AndDES");
    config.setKeyObtentionIterations("1000");
    config.setPoolSize("1");
    config.setProviderName("SunJCE");
    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
    config.setStringOutputType("base64");
    encryptor.setConfig(config);
    return encryptor;
  }

  private String getJasyptEncryptorPassword() {
    try {
      ClassPathResource resource = new ClassPathResource("jasypt-encryptor-password.txt");
      return Files.readAllLines(Paths.get(resource.getURI())).stream()
          .collect(Collectors.joining(""));
    } catch (IOException e) {
      throw new RuntimeException("Not found Jasypt password file.");
    }
  }
}
