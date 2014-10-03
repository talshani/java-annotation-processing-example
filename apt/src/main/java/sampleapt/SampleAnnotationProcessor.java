package sampleapt;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.util.Set;

/**
 * @author Tal Shani
 */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("sampleapt.Marker")
public class SampleAnnotationProcessor extends AbstractProcessor {
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;
    public int count = 0;
    public int total = 0;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) return false;

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Marker.class);
        total = elements.size();
        for (Element element : elements) {
            String packageOf = elementUtils.getPackageOf(element).getQualifiedName().toString();
            try {
                String elName = element.getSimpleName().toString();
                FileObject resource = filer.getResource(StandardLocation.CLASS_PATH, packageOf, elName + "Aux.txt");
                messager.printMessage(Diagnostic.Kind.NOTE, "We got the aux of " + element);
                count++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
//        count = roundEnv.getRootElements().size();


        return false;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        this.messager = processingEnv.getMessager();
        this.filer = processingEnv.getFiler();
        elementUtils = processingEnv.getElementUtils();
        super.init(processingEnv);
    }
}
