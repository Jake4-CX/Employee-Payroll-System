package lat.jack.employee.employee.Algorithms;

import lat.jack.employee.employee.Entities.Employees;

import java.util.*;

public class EmployeeSort {

    public static List<Employees> bucketSort(List<Employees> employeesList) {
        int maxId = getMaxId(employeesList);
        int bucketCount = (int) Math.sqrt(employeesList.size());

        Map<Integer, List<Employees>> buckets = new HashMap<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.put(i, new ArrayList<>());
        }

        for (Employees employee : employeesList) {
            int bucketIndex = (int) Math.floor((double) employee.getId() * bucketCount / (maxId + 1));
            buckets.get(bucketIndex).add(employee);
        }

        List<Employees> sortedEmployees = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            List<Employees> bucket = buckets.get(i);
            bucket.sort(Comparator.comparingInt(Employees::getId));
            sortedEmployees.addAll(bucket);
        }

        return sortedEmployees;
    }

    public static List<Employees> mergeSort(List<Employees> employeesList) {
        if (employeesList.size() <= 1) {
            return employeesList;
        }

        int middle = employeesList.size() / 2;
        List<Employees> left = mergeSort(employeesList.subList(0, middle));
        List<Employees> right = mergeSort(employeesList.subList(middle, employeesList.size()));

        return merge(left, right);
    }

    private static List<Employees> merge(List<Employees> left, List<Employees> right) {
        List<Employees> merged = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).getId() <= right.get(rightIndex).getId()) {
                merged.add(left.get(leftIndex));
                leftIndex++;
            } else {
                merged.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex));
            rightIndex++;
        }

        return merged;
    }

    public static List<Employees> quickSortDescending(List<Employees> employeesList) {
        if (employeesList.size() <= 1) {
            return employeesList;
        }

        int pivotIndex = employeesList.size() / 2;
        Employees pivot = employeesList.get(pivotIndex);
        List<Employees> less = new ArrayList<>();
        List<Employees> greater = new ArrayList<>();

        for (int i = 0; i < employeesList.size(); i++) {
            if (i == pivotIndex) {
                continue;
            }

            Employees employee = employeesList.get(i);
            if (employee.getId() >= pivot.getId()) {
                less.add(employee);
            } else {
                greater.add(employee);
            }
        }

        List<Employees> sorted = new ArrayList<>(quickSortDescending(less));
        sorted.add(pivot);
        sorted.addAll(quickSortDescending(greater));

        return sorted;
    }

    public static List<Employees> bubbleSortDescending(List<Employees> employeesList) {
        int n = employeesList.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (employeesList.get(j).getId() < employeesList.get(j + 1).getId()) {

                    Employees temp = employeesList.get(j);
                    employeesList.set(j, employeesList.get(j + 1));
                    employeesList.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return employeesList;
    }

    private static int getMaxId(List<Employees> employeesList) {
        int maxId = 0;
        for (Employees employee : employeesList) {
            if (employee.getId() > maxId) {
                maxId = employee.getId();
            }
        }
        return maxId;
    }
}
