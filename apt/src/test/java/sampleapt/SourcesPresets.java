package sampleapt;

import com.google.common.collect.Lists;
import com.google.testing.compile.JavaFileObjects;

import javax.tools.JavaFileObject;
import java.util.List;

/**
 * @author Tal Shani
 */
public class SourcesPresets {
    public static List<JavaFileObject> set1() {
        return Lists.newArrayList(
                JavaFileObjects.forResource("com/xrispi/templategen/presets/set1/JavaCode.java")
        );
    }
    public static List<JavaFileObject> set2() {
        return Lists.newArrayList(
                JavaFileObjects.forResource("com/xrispi/templategen/presets/set2/Object.java"),
                JavaFileObjects.forResource("com/xrispi/templategen/presets/set2/JavaCode.java")
        );
    }
}
