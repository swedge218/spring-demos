package com.tp.dependencymanagement.core.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tp.dependencymanagement.core.abstracts.ISortAlgorithm;

@Component
public class BinarySearch {

	@Autowired
	ISortAlgorithm sortAlgorithm;
	
//	public BinarySearch(ISortAlgorithm sortAlgorithm) {
//		super();
//		this.sortAlgorithm = sortAlgorithm;
//	}
	
	public int search(int[] numbers, int number) {
		System.out.println("Sorting algorithm used: " + sortAlgorithm);
		int[] sortedNumbers = sortAlgorithm.sort(numbers);
		
		//binary search algorithm goes here.
		return 3;
	}
}
