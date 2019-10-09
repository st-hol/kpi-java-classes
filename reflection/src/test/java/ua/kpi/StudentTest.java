package ua.kpi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

public class StudentTest {
  @Test
  void testReflectionAccess(){
    Student student = createStudent();

    assertNotNull( student.toString());

    Class clazz = student.getClass();
    System.out.println("Class name" + clazz.getSimpleName());
    System.out.println("Fields \n" + Arrays.asList(clazz.getDeclaredFields())
      .stream().map(field -> Modifier.toString( field.getModifiers())
                           +" "+field.getType().getSimpleName() + " " + field.getName())
                           .collect(Collectors.joining("\n")));
  }

  @Test
  void should_print_all_methods(){
    Student student = createStudent();

    Arrays.asList(student.getClass().getDeclaredMethods())
        .stream()
        .map(method -> Modifier.toString( method.getModifiers() )
            + " " + method.getReturnType().getSimpleName() + " "
            +method.getName() + "(" + getMethodParameters( method ) +")" )
        .forEach(System.out::println);

  }

  @Test
  @SneakyThrows
  void should_change_first_name() {
    Student student = createStudent();

    Field firstNameField = student.getClass().getDeclaredField("firstName");
    firstNameField.setAccessible(true);
    firstNameField.set(student,"NewOne");
    assertEquals("NewOne", student.getFirstName());

  }

  @Test
  @SneakyThrows
  void should_create_new_instance(){
    Class clazz = Class.forName("ua.kpi.Student");
    Student student = (Student) clazz.newInstance();
    System.out.println(student);

  }

  @Test
  @SneakyThrows
  void should_call_method(){
    Student student = createStudent();
    Class clazz = student.getClass();
    Method addToAge = clazz.getMethod("addToAge", int.class);
    addToAge.invoke(student,2);
    assertEquals(22,student.getAge());

  }

  @Test
  @SneakyThrows
  void sould_print_file_content(){
    InputStream resourceAsStream = Student.class.getResourceAsStream("/hello.txt");
    System.out.println( IOUtils.toString(resourceAsStream));
  }

  @Test
  @SneakyThrows
  void should_call_method_with_annotation(){
    Student student = createStudent();
    for(Method method: student.getClass().getMethods()){
      if( method.isAnnotationPresent(CallMe.class) ){
        method.invoke(student);
      }
    }
    assertEquals(21,student.getAge());

  }


  private String getMethodParameters(Method method) {
    return Arrays.asList( method.getParameters() )
        .stream()
        .map( param -> param.getType() + " " + param.getName())
        .collect(Collectors.joining(","));
  }



  private Student createStudent() {
    return Student.builder()
          .firstName("One")
          .lastName("Second")
          .group("TI-71")
          .age(20)
          .rating(100500.).build();
  }

}
