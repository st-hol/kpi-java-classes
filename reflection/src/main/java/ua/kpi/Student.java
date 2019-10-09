package ua.kpi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Student {
  private String firstName;
  private String lastName;
  private String group;
  private int age;
  private double rating;

  @CallMe
  public void incrementAge(){
    age++;
  }

  public void addToAge(int delta){
    age+=delta;
  }

}
