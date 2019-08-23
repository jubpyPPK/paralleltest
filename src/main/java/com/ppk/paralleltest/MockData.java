package com.ppk.paralleltest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MockData {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static List<Person> getPeople() {
    try {
      InputStream inputStream = Resources.getResource("people.json").openStream();
      return objectMapper.readValue(inputStream, new TypeReference<List<Person>>() {
      });
    } catch (IOException e) {
      e.printStackTrace();
      return Lists.newArrayList();
    }

  }

}