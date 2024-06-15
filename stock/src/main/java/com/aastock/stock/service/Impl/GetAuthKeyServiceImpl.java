package com.aastock.stock.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.aastock.stock.dto.response.AuthKeyDTO;
import com.aastock.stock.service.GetAuthKeyService;

@Service
public class GetAuthKeyServiceImpl implements GetAuthKeyService{

  @Autowired
  private RestTemplate restTemplate;

  @Value(value = "${aastock.authUrl}")
  private String authUrl;

  @Override
  public String getAuthKey(){
    AuthKeyDTO authKeyDTO = restTemplate.getForObject(authUrl, AuthKeyDTO.class);
    String token =authKeyDTO.getToken();
    return token ;
  }
}
