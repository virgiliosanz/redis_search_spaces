package com.redis.om.skeleton.models;

import org.springframework.data.annotation.Id;
import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Document
public class Customer {
  // Id Field, also indexed
  @Id
  @Indexed
  private String id;

  @Searchable @NonNull
  private String name;

  @NonNull
  private String address;

  @NonNull
  private String phone;

  @Indexed @NonNull
  private String job;

  @Indexed @NonNull
  private CreditCard creditCard;
}