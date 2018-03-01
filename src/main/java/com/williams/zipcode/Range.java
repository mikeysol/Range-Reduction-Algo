package com.williams.zipcode;
/*
    author: Michael Barnes
 */

import java.util.*;
import java.util.stream.Collectors;

public class Range{
    /*
        There are multiple implementations of the reduce method but ultimately the reduce(List<Integer[]> ranges)
        is the most correct implementation. For one reason it is iterative and although recursion does have
        its advantages there is the issue that java does not really support tail call optimization so if the
        reduction method is called for a very large list then we risk a stack overflow.

        Another reason is reduce(List<Integer[]> ranges) is not generic and again although generic implementations
        can have their advantages there can be issues with generic methods leaving the implementation of comparison to
        the caller. In the use case for Zipcode range reduction we want to compare two ranges by value and difference.
        The compareTo() method that results in a determination of equal, greater, or lesser does not suffice the case
        when numbers are sequential and one increment apart. At that time we should join and reduce the number of Ranges
        if a range is one increment apart from another. Determining the differences needs the implementation of an
        arithmetic operation which a generic method can't provide because we don't know the Type until runtime nor which
        operation to apply.

        I kept the other implementations to have a reference to the differences and to point out that more refactoring
        could be done if we want to preserve a structure of a true Zipcode being 5 digits and can have leading 0's.
        Possibly a composite or wrapper Range object could be implemented to preserve this strict definition and have helper
        methods format the integers to strings with leading 0's.

        There's also a philosophical debate on how you could interpret a reduction such as it can only be merged and reduced
        if a bound is equal to or inclusive of a range's bounds. In other words it must strictly overlap and that definition
        would not satisfy ranges that are sequential and one increment apart. But the point of giving a range as we commonly
        understand is really to abbreviate a list of consecutive values so again reduce(List<Integer[]> ranges) is the proper
        implementation.
     */

    public List<Integer[]> reduce(List<Integer[]> ranges){
        ranges = ranges.stream()
                .filter(range -> range.length == 2 && Arrays.stream(range).noneMatch(Objects::isNull))
                .sorted(new RangeListSort<>())//sorting and removing empty ranges
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
