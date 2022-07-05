package com.tp.dependencymanagement.core.concretes;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.tp.dependencymanagement.core.abstracts.ISortAlgorithm;

@Component
public class BubbleSort implements ISortAlgorithm {
	public int[] sort(int[] n) {
		//bubble sort algorithm goes here
		return n; //sorted array
	}
}
