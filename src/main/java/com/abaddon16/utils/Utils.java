package com.abaddon16.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Utils {

    public static final ClassLoader resources = Utils.class.getClassLoader();

    public static List<String> readInLines(String fileName) {
        List<String> strings = new ArrayList<>();
        String filePath = getResourceFilePath(fileName);
        try(Stream<String> s = Files.lines(Path.of(filePath).toAbsolutePath())){
            s.forEach(string->{
                if(!string.isEmpty()) strings.add(string);
            });
        }
        catch(IOException e){
            System.out.println("This went poorly");
        }
        return strings;
    }

    public static String getResourceFilePath(String fileName){
        String filePath = null;
        try{
            URL inputUrl = resources.getResource(fileName);
            if(inputUrl!=null) {
                filePath = new File(inputUrl.toURI()).getAbsolutePath();
            }
            else{
                throw new URISyntaxException("", "The URL is invalid");
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public static <T extends Comparable<T>> List<T> quickSort(List<T> list)
    {
        if (list == null || list.isEmpty()) return list;

        List<T> left = new ArrayList<>();
        List<T> right = new ArrayList<>();
        T pivot = list.get(0);
        T j;
        for (int i=1; i<list.size(); i++)
        {
            j=list.get(i);
            if (j.compareTo(pivot)<0) left.add(j);
            else right.add(j);
        }
        left=quickSort(left);
        right=quickSort(right);
        left.add(pivot);
        left.addAll(right);

        return left;
    }

}
