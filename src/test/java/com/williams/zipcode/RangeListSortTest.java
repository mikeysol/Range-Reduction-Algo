package com.williams.zipcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

public class RangeListSortTest {

    @Test
    public void compareIntegers() {
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

        ranges.sort(new RangeListSort<>());

        Integer[] sortedIntRange = {21,26};
        Integer[] sortedIntRange2 = {3081,55555};
        Integer[] sortedIntRange3 = {8075,11164};
        Integer[] sortedIntRange4 = {12222,12222};
        Integer[] sortedIntRange5 = {18075,87707};
        Integer[] sortedIntRange6 = {20400,58980};
        Integer[] sortedIntRange7 = {28880,74783};
        List<Integer[]> sorted = new ArrayList<>();
        sorted.add(sortedIntRange);
        sorted.add(sortedIntRange2);
        sorted.add(sortedIntRange3);
        sorted.add(sortedIntRange4);
        sorted.add(sortedIntRange5);
        sorted.add(sortedIntRange6);
        sorted.add(sortedIntRange7);

        assertEquals(
                ranges.stream().map(Arrays::asList).collect(toList()),
                sorted.stream().map(Arrays::asList).collect(toList())
        );
    }

    @Test
    public void compareStrings() {
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

        ranges.sort(new RangeListSort<>());

        String[] sortedStringRange = {"00021","00026"};
        String[] sortedStringRange2 = {"03081", "55555"};
        String[] sortedStringRange3 = {"08075", "11164"};
        String[] sortedStringRange4 = {"12222","12222"};
        String[] sortedStringRange5 = {"18075","87707"};
        String[] sortedStringRange6 = {"20400","58980"};
        String[] sortedStringRange7 = {"28880","74783"};
        List<String[]> sorted = new ArrayList<>();
        sorted.add(sortedStringRange);
        sorted.add(sortedStringRange2);
        sorted.add(sortedStringRange3);
        sorted.add(sortedStringRange4);
        sorted.add(sortedStringRange5);
        sorted.add(sortedStringRange6);
        sorted.add(sortedStringRange7);

        assertEquals(
                ranges.stream().map(Arrays::asList).collect(toList()),
                sorted.stream().map(Arrays::asList).collect(toList())
        );
    }
}