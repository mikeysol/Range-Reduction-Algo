package com.williams.zipcode;

import java.util.*;
import java.util.stream.Collectors;

public class Range{

    public List<Integer[]> reduce(List<Integer[]> ranges){
        ranges = ranges.stream()
                .filter(range -> range.length == 2 && Arrays.stream(range).noneMatch(Objects::isNull))
                .sorted(new RangeListSort<>())
                .collect(Collectors.toList());
        ArrayList<Integer[]> reducedList = new ArrayList<>();
        if(ranges.size() == 0) return ranges;
        Integer[] prev = ranges.get(0);

        for(int i = 1; i<ranges.size(); i++){
            if(ranges.get(i)[0] - prev[1] <= 1){
                if(ranges.get(i)[1].compareTo(prev[1]) > 0){
                    prev[1] = ranges.get(i)[1];
                }
            }else{
                reducedList.add(prev);
                prev = ranges.get(i);
            }
        }
        reducedList.add(prev);
        return reducedList;
    }

    public <T extends Comparable<T>> List<T[]> reduceGenericIterative(List<T[]> ranges){
        ranges = ranges.stream()
                .filter(range -> range.length == 2 && Arrays.stream(range).noneMatch(Objects::isNull))
                .sorted(new RangeListSort<>())
                .collect(Collectors.toList());
        ArrayList<T[]> reducedList = new ArrayList<>();
        if(ranges.size() == 0) return ranges;
        T[] prev = ranges.get(0);

        for(int i = 1; i<ranges.size(); i++){
            if(ranges.get(i)[0].compareTo(prev[1]) <= 0){
                if(ranges.get(i)[1].compareTo(prev[1]) > 0){
                    prev[1] = ranges.get(i)[1];
                }
            }else{
                reducedList.add(prev);
                prev = ranges.get(i);
            }
        }
        reducedList.add(prev);
        return reducedList;
    }

    public <T extends Comparable<T>> List<T[]> reduceGenericRecursive(List<T[]> ranges){
        ranges = ranges.stream()
                .filter(range -> range.length == 2 && Arrays.stream(range).noneMatch(Objects::isNull))
                .sorted(new RangeListSort<>())
                .collect(Collectors.toList());
        ArrayList<T[]> reducedList = new ArrayList<>();
        Iterator<T[]> it = ranges.iterator();
        if(!it.hasNext()) return ranges;
        T[] prev = it.next();

        return reduceRecHelper(it,prev,reducedList);
    }

    private <T extends Comparable<T>> List<T[]> reduceRecHelper(Iterator<T[]> it, T[] prev, List<T[]> reducedList){
        if(it.hasNext()){
            T[] current = it.next();
            if(current[0].compareTo(prev[1]) <= 0){
                if(current[1].compareTo(prev[1]) > 0) {
                    prev[1] = current[1];
                }
                return reduceRecHelper(it,prev,reducedList);
            }else{
                reducedList.add(prev);
                prev = current;
                return reduceRecHelper(it,prev,reducedList);
            }
        }
        reducedList.add(prev);
        return reducedList;
    }


    public <T> void print(Collection<T[]> collect){
        for(T[] tarr: collect){
            System.out.println();
            for(T t: tarr){
                System.out.print(t+" ");
            }
        }
        System.out.print("\n");
    }
    public static void main(String[] args){
        Range zipCodeInts = new Range();
        Integer[] zipIntRange = {8075,11164};
        Integer[] zipIntRange2 = {18075,87707};
        Integer[] zipIntRange3 = {20400,58980};
        Integer[] zipIntRange4 = {55555,3081};
        Integer[] zipIntRange5 = {28880,74783};
        Integer[] zipIntRange6 = {21,26};
        Integer[] zipIntRange7 = {12222,12222};
        Integer[] zipIntRange8 = {27,29};
        Integer[] zipIntRange9 = {21,21};
        Integer[] zipIntRange10 = {12222,12222};
        Integer[] zipIntRange11 = {0,80};
        Integer[] zipIntRange12 = {82,83};
        Integer[] zipIntRange13 = {84,85};
        List<Integer[]> ranges = new ArrayList<>();
        ranges.add(zipIntRange);
        ranges.add(zipIntRange2);
        ranges.add(zipIntRange3);
        ranges.add(zipIntRange4);
        ranges.add(zipIntRange5);
        ranges.add(zipIntRange6);
        ranges.add(zipIntRange7);
        ranges.add(zipIntRange8);
        ranges.add(zipIntRange9);
        ranges.add(zipIntRange10);
        ranges.add(zipIntRange11);
        ranges.add(zipIntRange12);
        ranges.add(zipIntRange13);

        System.out.println("\nSorted List of Integer Zipcodes:");
        ranges.sort(new RangeListSort<>());
        zipCodeInts.print(ranges);

        System.out.println("\nReduced List of Integer Zipcodes:");
        ranges = zipCodeInts.reduce(ranges);
        zipCodeInts.print(ranges);


    }
}
