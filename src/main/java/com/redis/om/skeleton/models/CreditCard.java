package com.redis.om.skeleton.models;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data 
@RequiredArgsConstructor(staticName = "of") 
public class CreditCard {

  @NonNull
  @Searchable(nostem = true)
  private String provider;

  @NonNull
//  @Searchable(nostem = true)
  private String number;

  @NonNull
  @Indexed
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  private Date expiration;

  @NonNull
  private Integer securityCode ;
}
