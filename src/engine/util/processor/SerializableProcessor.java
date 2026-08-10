package engine.util.processor;

import java.util.Set;

import javax.annotation.processing.*;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Types;

@SupportedAnnotationTypes("*")
public class SerializableProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		TypeElement serializable = processingEnv.getElementUtils().getTypeElement("engine.util.data.Serializable");
		Types types = processingEnv.getTypeUtils();
		
		for (Element e : roundEnv.getRootElements()) {
			if (e instanceof TypeElement type) {
				if (types.isAssignable(type.asType(), serializable.asType())) {
					System.out.println(type.getQualifiedName());
				}
			}
		}
		
		return false;
	}

}
