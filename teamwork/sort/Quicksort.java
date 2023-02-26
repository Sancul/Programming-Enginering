package com.tests.assignments.teamwork.sort;

import java.util.Comparator;

public class Quicksort<T> {
    private final Comparator<T> comparator;

    public Quicksort(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void quicksort(T[] array, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivotIndex = partition(array, startIndex, endIndex);
            quicksort(array, startIndex, pivotIndex);
            quicksort(array, pivotIndex + 1, endIndex);
        }
    }

    private int partition(T[] array, int startIndex, int endIndex) {
        int pivotIndex = (startIndex + endIndex) / 2;
        T pivotValue = array[pivotIndex];
        startIndex--;
        endIndex++;

        while (true) {
            do startIndex++; while (comparator.compare(array[startIndex], pivotValue) < 0);

            do endIndex--; while (comparator.compare(array[endIndex], pivotValue) > 0);

            if (startIndex >= endIndex) return endIndex;

            T temp = array[startIndex];
            array[startIndex] = array[endIndex];
            array[endIndex] = temp;
        }
    }
}