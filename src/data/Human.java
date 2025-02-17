package data;
import utility.*;
import java.time.LocalDate;

public class Human implements Validate{
    private long age; //Значение поля должно быть больше 0
    private long height; //Значение поля должно быть больше 0
    private LocalDate birthday;

    public Human(long age, long height, LocalDate birthday) {
        this.age = age;
        this.height = height;
        this.birthday = birthday;
    }
    @Override
    public boolean validate() {
        if (age <= 0) return false;
        return height > 0;
    }
    @Override
    public String toString() {
        return age + ";" + height + ";" + birthday;
    }

    public long getAge() {
        return age;
    }

    public long getHeight() {
        return height;
    }
}
