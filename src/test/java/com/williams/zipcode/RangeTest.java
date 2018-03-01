package com.williams.zipcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

public class RangeTest {

    @Test
    public void reduceIntRangeRecursive() {
        Integer[] zipIntRange = {8075,11164};
        Integer[] zipIntRange2 = {18075,87707};
        Integer[] zipIntRange3 = {20400,58980};
        Integer[] zipIntRange4 = {55555,3081};
        Integer[] zipIntRange5 = {28880,74783};
        Integer[] zipIntRange6 = {21,26};
        Integer[] zipIntRange7 = {12222,12222};
        List<Integer[]> ranges = new ArrayList<>();
        ranges.add(zipIntRange);
        ranges.add(zipIntRange2);
        ranges.add(zipIntRange3);
        ranges.add(zipIntRange4);
        ranges.add(zipIntRange5);
        ranges.add(zipIntRange6);
        ranges.add(zipIntRange7);

        List<Integer[]> result = new Range().reduceGenericRecursive(ranges);

        Integer[] reducedIntRange = {21,26};
        Integer[] reducedIntRange2 = {3081,87707};
        List<Integer[]> reduced = new ArrayList<>();
        reduced.add(reducedIntRange);
        reduced.add(reducedIntRange2);


        assertEquals(
                result.stream().map(Arrays::asList).collect(toList()),
                reduced.stream().map(Arrays::asList).collect(toList())
        );

    }

    @Test
    public void reduceSequentialIntRange() {
        Integer[] zipIntRange = {0,1};
        Integer[] zipIntRange2 = {2,3};
        Integer[] zipIntRange3 = {4,4};
        Integer[] zipIntRange4 = {5,6};
        Integer[] zipIntRange5 = {6,7};
        Integer[] zipIntRange6 = {7,8};
        Integer[] zipIntRange7 = {9,9};
        List<Integer[]> ranges = new ArrayList<>();
        ranges.add(zipIntRange);
        ranges.add(zipIntRange2);
        ranges.add(zipIntRange3);
        ranges.add(zipIntRange4);
        ranges.add(zipIntRange5);
        ranges.add(zipIntRange6);
        ranges.add(zipIntRange7);

        List<Integer[]> result = new Range().reduce(ranges);

        Integer[] reducedIntRange = {0,9};
        List<Integer[]> reduced = new ArrayList<>();
        reduced.add(reducedIntRange);


        assertEquals(
                result.stream().map(Arrays::asList).collect(toList()),
                reduced.stream().map(Arrays::asList).collect(toList())
        );

    }

    @Test
    public void reduceStringRangeIterative() {
        String[] zipStringRange = {"08075","11164"};
        String[] zipStringRange2 = {"18075","87707"};
        String[] zipStringRange3 = {"20400","58980"};
        String[] zipStringRange4 = {"55555","03081"};
        String[] zipStringRange5 = {"28880","74783"};
        String[] zipStringRange6 = {"00021","00026"};
        String[] zipStringRange7 = {"12222","12222"};
        List<String[]> ranges = new ArrayList<>();
        ranges.add(zipStringRange);
        ranges.add(zipStringRange2);
        ranges.add(zipStringRange3);
        ranges.add(zipStringRange4);
        ranges.add(zipStringRange5);
        ranges.add(zipStringRange6);
        ranges.add(zipStringRange7);

        List<String[]> result = new Range().reduceGenericIterative(ranges);

        String[] sortedStringRange = {"00021","00026"};
        String[] sortedStringRange2 = {"03081", "87707"};
        List<String[]> reduced = new ArrayList<>();
        reduced.add(sortedStringRange);
        reduced.add(sortedStringRange2);


        assertEquals(
                result.stream().map(Arrays::asList).collect(toList()),
                reduced.stream().map(Arrays::asList).collect(toList())
        );

    }

    @Test
    public void reduceInteger() {
        Integer[] zipIntRange = {0,1};
        Integer[] zipIntRange2 = {2,3};
        Integer[] zipIntRange3 = {4,4};
        Integer[] zipIntRange4 = {6,6};
        Integer[] zipIntRange5 = {6,7};
        Integer[] zipIntRange6 = {7,8};
        Integer[] zipIntRange7 = {9,9};
        List<Integer[]> ranges = new ArrayList<>();
        ranges.add(zipIntRange);
        ranges.add(zipIntRange2);
        ranges.add(zipIntRange3);
        ranges.add(zipIntRange4);
        ranges.add(zipIntRange5);
        ranges.add(zipIntRange6);
        ranges.add(zipIntRange7);

        List<Integer[]> result = new Range().reduce(ranges);

        Integer[] reducedIntRange = {0,4};
        Integer[] reducedIntRange2 = {6,9};
        List<Integer[]> reduced = new ArrayList<>();
        reduced.add(reducedIntRange);
        reduced.add(reducedIntRange2);


        assertEquals(
                result.stream().map(Arrays::asList).collect(toList()),
                reduced.stream().map(Arrays::asList).collect(toList())
        );
    }

    @Test
    public void reduceIntegerEmptyArrays() {
        Integer[] zipIntRange = {null,null};
        Integer[] zipIntRange2 = {2,3};
        Integer[] zipIntRange3 = new Integer[0];

        List<Integer[]> ranges = new ArrayList<>();
        ranges.add(zipIntRange);
        ranges.add(zipIntRange2);
        ranges.add(zipIntRange3);

        List<Integer[]> result = new Range().reduce(ranges);

        Integer[] reducedIntRange = {2,3};
        List<Integer[]> reduced = new ArrayList<>();
        reduced.add(reducedIntRange);


        assertEquals(
                result.stream().map(Arrays::asList).collect(toList()),
                reduced.stream().map(Arrays::asList).collect(toList())
        );
    }

    @Test
    public void reduceWithEmptyList() {

        List<Integer[]> ranges = new ArrayList<>();

        List<Integer[]> result = new Range().reduce(ranges);

        List<Integer[]> reduced = new ArrayList<>();

        assertEquals(
                result,
                reduced
        );
    }

    @Test
    public void reduceIntegerDuplicates() {
        Integer[] zipIntRange = {0,0};
        Integer[] zipIntRange2 = {2,3};
        Integer[] zipIntRange3 = {0,0};
        Integer[] zipIntRange4 = {1,1};
        Integer[] zipIntRange5 = {8,7};
        Integer[] zipIntRange6 = {7,8};
        Integer[] zipIntRange7 = {9,9};
        List<Integer[]> ranges = new ArrayList<>();
        ranges.add(zipIntRange);
        ranges.add(zipIntRange2);
        ranges.add(zipIntRange3);
        ranges.add(zipIntRange4);
        ranges.add(zipIntRange5);
        ranges.add(zipIntRange6);
        ranges.add(zipIntRange7);

        List<Integer[]> result = new Range().reduce(ranges);

        Integer[] reducedIntRange = {0,3};
        Integer[] reducedIntRange2 = {7,9};
        List<Integer[]> reduced = new ArrayList<>();
        reduced.add(reducedIntRange);
        reduced.add(reducedIntRange2);


        assertEquals(
                result.stream().map(Arrays::asList).collect(toList()),
                reduced.stream().map(Arrays::asList).collect(toList())
        );
    }

}