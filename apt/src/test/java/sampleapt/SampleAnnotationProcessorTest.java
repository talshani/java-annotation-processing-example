package sampleapt;

import com.google.common.collect.Lists;
import com.google.testing.compile.JavaFileObjects;
import org.junit.Test;

import javax.tools.JavaFileObject;
import java.util.List;

import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;
import static org.truth0.Truth.ASSERT;

public class SampleAnnotationProcessorTest {

    @Test
    public void testProcess() throws Exception {

        List<JavaFileObject> sources = Lists.newArrayList(
                JavaFileObjects.forResource("sampleapt/set1/JavaCode.java")
//                ,JavaFileObjects.forResource("sampleapt/set1/JavaCodeAux.txt")
        );

        ASSERT.about(javaSources())
                .that(sources)
                .compilesWithoutError();

        SampleAnnotationProcessor processor = new SampleAnnotationProcessor();
        ASSERT.about(javaSources())
                .that(sources)
                .processedWith(processor)
                .compilesWithoutError()
//                .and().generatesFiles(generatedFile)
        ;
        ASSERT.that(processor.count).is(processor.total);
    }
}