package toby.spring.hellospring;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {

    public static void main(String[] args) {
        List<String> scores = Arrays.asList("z", "b", "spring", "java");
        Collections.sort(scores, Comparator.comparing(String::length));

        System.out.println(scores);
    }
}
