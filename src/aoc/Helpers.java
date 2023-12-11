package aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Helpers {
    public static ArrayList<String> extractInputFromFile(String fileName) {
        ArrayList<String> result = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()) {
                result.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void printList (List<?> list){
        System.out.println("<****---------------------------------------------------------------------------------****>");
        for (Object element : list) {
            System.out.println(element);
            System.out.println("<****---------------------------------------------------------------------------------****>");
        }
    }

    public static <K, V> Set<K> getKeySubset(Map<K, V> map, K fromKey, K toKey) {
        if (map instanceof NavigableMap) {
            NavigableMap<K, V> navigableMap = (NavigableMap<K, V>) map;
            return navigableMap.subMap(fromKey, false, toKey, true).keySet();
        } else {
            throw new IllegalArgumentException("The map must be an instance of NavigableMap.");
        }
    }
}
