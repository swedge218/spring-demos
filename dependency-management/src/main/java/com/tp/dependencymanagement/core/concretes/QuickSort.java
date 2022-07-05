package com.tp.dependencymanagement.core.concretes;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.tp.dependencymanagement.core.abstracts.ISortAlgorithm;

@Component
@Primary
public class QuickSort implements ISortAlgorithm {

	public int[] sort(int[] numbers) {
		//quick sort algorithm goes here
		
		return numbers;  //sorted numbers
	}
}
