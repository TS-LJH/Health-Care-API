package com.example.demo.Controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Todo;


@RestController
@RequestMapping(value="/rest")
public class RestTestController {
	private final AtomicInteger counter = new AtomicInteger();
	private InfluxService service = new InfluxService();
	
	@RequestMapping("/test")
	public Todo test() {
//		service.getList();
		return new Todo(service.getList(),"테스트");
	}
	
	@PostMapping("/test")
	public Todo registryTodo(@RequestParam(value="todoTitle") String todoTitle) {
		return new Todo(counter.incrementAndGet(),todoTitle);
	}
	
	@PostMapping("/test/response")
	public ResponseEntity<Todo> postRegistyTodo(@RequestParam String todoTitle){
		return new ResponseEntity<>(new Todo(counter.incrementAndGet(),todoTitle),HttpStatus.CREATED);
	}

}