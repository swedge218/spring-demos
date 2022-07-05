package com.tp.dependencymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.tp.dependencymanagement.core.concretes.BinarySearch;
import com.tp.dependencymanagement.core.concretes.BubbleSort;

@SpringBootApplication
public class DependencyManagementApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DependencyManagementApplication.class, args);
		BinarySearch binarySearch = context.getBean(BinarySearch.class);
//		BinarySearch binarySearch = new BinarySearch(new BubbleSort());
		int searchResult = binarySearch.search(new int[]{1,3,5,2,4}, 3);
		System.out.println("Result of the search is " + searchResult);
	}

}