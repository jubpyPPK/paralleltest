package com.ppk.paralleltest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  @GetMapping("/students")
  public ResponseEntity<Object> getStudents(
      @RequestParam(name = "age", required = false) Integer age,
      @RequestParam(name = "gender", required = false) String gender) {
    List<Person2> collect2 = new ArrayList<>();
    MockData.getPeople().stream()
        .filter(person -> age == null || person.getAge().equals(age))
        .filter(
            person -> StringUtils.isEmpty(gender) || person.getGender().equalsIgnoreCase(gender))
        .forEach(person -> {
          if (person.getGender().equalsIgnoreCase("Female")) {
            collect2.addAll(this.female(person));
          }
          if (person.getGender().equalsIgnoreCase("Male")) {
            collect2.addAll(this.male(person));
          }
        });
    collect2.stream().forEach(System.out::println);
    return ResponseEntity.ok(collect2);
  }

  @GetMapping("/studentsP")
  public ResponseEntity<Object> getStudentsP(
      @RequestParam(name = "age", required = false) Integer age,
      @RequestParam(name = "gender", required = false) String gender) {
    List<Person2> collect2 = Collections.synchronizedList(new ArrayList<>());
    MockData.getPeople().parallelStream()
        .filter(person -> age == null || person.getAge().equals(age))
        .filter(
            person -> StringUtils.isEmpty(gender) || person.getGender().equalsIgnoreCase(gender))
        .forEach(person -> {
          if (person.getGender().equalsIgnoreCase("Female")) {
            collect2.addAll(this.female(person));
          }
          if (person.getGender().equalsIgnoreCase("Male")) {
            collect2.addAll(this.male(person));
          }
        });
    collect2.stream().forEach(System.out::println);
    return ResponseEntity.ok(collect2);
  }

  @GetMapping("/studentsQ")
  public ResponseEntity<Object> getStudentsQ(
      @RequestParam(name = "age", required = false) Integer age,
      @RequestParam(name = "gender", required = false) String gender) {
    List<Person2> collect2 = new ArrayList<>();
    MockData.getPeople().parallelStream()
        .filter(person -> age == null || person.getAge().equals(age))
        .filter(
            person -> StringUtils.isEmpty(gender) || person.getGender().equalsIgnoreCase(gender))
        .forEach(person -> {
          if (person.getGender().equalsIgnoreCase("Female")) {
            collect2.addAll(this.female(person));
          }
          if (person.getGender().equalsIgnoreCase("Male")) {
            collect2.addAll(this.male(person));
          }
        });
    collect2.stream().forEach(System.out::println);
    return ResponseEntity.ok(collect2);
  }

  private List<Person2> female(Person person) {
    List<Person2> person2List = new ArrayList<>();
    person2List.add(Person2.builder()
        .prefix("Miss.")
        .firstName(person.getFirstName())
        .lastName(person.getLastName())
        .age(person.getAge() + 100)
        .build());
    return person2List;
  }

  private List<Person2> male(Person person) {
    List<Person2> person2List = new ArrayList<>();
    person2List.add(Person2.builder()
        .prefix("Mr.")
        .firstName(person.getFirstName())
        .lastName(person.getLastName())
        .age(person.getAge() + 100)
        .build());
    return person2List;
  }

}
