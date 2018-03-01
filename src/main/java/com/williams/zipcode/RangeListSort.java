package com.williams.zipcode;

import java.util.Arrays;
import java.util.Comparator;

class RangeListSort<T extends Comparable<T>> implements Comparator<T[]> {
    @Override
    public int compare(T[] one, T[] two) {
        Arrays.sort(one);/*put lower and upper bound in order with lower appearing first in array*/
        Arrays.sort(two);
        if(one[0].compareTo(two[0]) < 0 ){//first order by lower bound
            return -1;
        }else if(one[0].compareTo(two[0]) == 0){//if lower bounds are equal then order by upper
            if(one[1].compareTo(two[1]) < 0){
                return  -1;
            }else{
                return 1;
            }
        }else{
            return 1;
        }
    }
}
