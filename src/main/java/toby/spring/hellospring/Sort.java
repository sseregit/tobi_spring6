package toby.spring.hellospring;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {

    public List<String> sortByLength(List<String> list) {
        list.sort(Comparator.comparing(String::length));
        return list;
    }

}
