package pt.ulisboa.forward.ewp.api.client.utils;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class XmlUtils {

  private XmlUtils() {}

  /**
   * Creates a JAXBContext that is aware of every class on the given packages, as wells subpackages
   * of them.
   *
   * @param packageNames The packages, and corresponding subpackages, that are to be searched for
   *     classes to include in the JAXBContext.
   * @return A JAXBContext aware of every class on the given packages, as wells subpackages of them.
   * @throws JAXBException Thrown if the creation of JAXBContext fails for some reason.
   */
  public static JAXBContext createJAXBContext(Map<String, ClassLoader> packageNameToClassLoaderMap)
      throws JAXBException {
    Set<Class<?>> classes = new HashSet<>();
    for (Map.Entry<String, ClassLoader> entry : packageNameToClassLoaderMap.entrySet()) {
      classes.addAll(findAllClassesUsingClassLoader(entry.getValue(), entry.getKey()));
    }
    classes = classes.stream().filter(c -> !c.isInterface()).collect(Collectors.toSet());
    return JAXBContext.newInstance(classes.toArray(new Class[0]));
  }

  private static Set<Class<?>> findAllClassesUsingClassLoader(
      ClassLoader classLoader, String packageName) {
    try {
      return ClassPath.from(classLoader).getAllClasses().stream()
          .filter(clazz -> clazz.getPackageName().startsWith(packageName))
          .map(ClassInfo::load)
          .collect(Collectors.toSet());
    } catch (IOException e) {
      return new HashSet<>();
    }
  }
}
