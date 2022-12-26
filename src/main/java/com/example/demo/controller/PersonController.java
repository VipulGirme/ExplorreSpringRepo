package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;

@RestController
public class PersonController {

	@GetMapping("/person")
	public List<Person> getPerson() {
		Person p1 = new Person("Avadhut");
		List<Person> personList = new ArrayList<>();
		personList.add(p1);
		return personList;
	}
}
