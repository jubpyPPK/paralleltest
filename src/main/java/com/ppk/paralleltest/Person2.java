package com.ppk.paralleltest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class Person2 {

  private String prefix;
  private String firstName;
  private String lastName;
  private Integer age;
}
