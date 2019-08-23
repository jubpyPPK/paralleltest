package com.ppk.paralleltest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Person implements Serializable {

  private String id;
  private String firstName;
  private String lastName;
  private String email;
  private Integer age;
  private String gender;

}
