package com.williams.zipcode;
/*
    author: Michael Barnes
 */

import java.util.*;
import java.util.stream.Collectors;

public class Range{

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
}
