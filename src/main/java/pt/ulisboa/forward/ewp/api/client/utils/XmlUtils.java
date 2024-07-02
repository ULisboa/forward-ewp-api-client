package pt.ulisboa.forward.ewp.api.client.utils;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
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
  public static JAXBContext createJAXBContext(String... packageNames) throws JAXBException {
    Set<Class<?>> classes = new HashSet<>();
    for (String packageName : packageNames) {
      classes.addAll(findAllClassesUsingClassLoader(packageName));
    }
    classes = classes.stream().filter(c -> !c.isInterface()).collect(Collectors.toSet());
    return JAXBContext.newInstance(classes.toArray(new Class[0]));
  }

  private static Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
    try {
      return ClassPath.from(ClassLoader.getSystemClassLoader()).getAllClasses().stream()
          .filter(clazz -> clazz.getPackageName().startsWith(packageName))
          .map(ClassInfo::load)
          .collect(Collectors.toSet());
    } catch (IOException e) {
      return new HashSet<>();
    }
  }
}
