package linhVu.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class User implements Validator {

    private String firstName;

    private  String lastName;
    private Integer age;

    private String phoneNumber;
    private String email;

    public User() {
    }

    public User(String firstName, String lastName, Integer age, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String number = user.getPhoneNumber();
        ValidationUtils.rejectIfEmpty(errors,"phoneNumber","number.empty");
        if (number.length()>11 || number.length()<10){
            errors.rejectValue("phoneNumber","number.length");
        }
        if(!number.startsWith("0")){
            errors.rejectValue("phoneNumber","number.startsWith");
        }
        if(!number.matches("(^$|[0-9]*$)")){
            errors.rejectValue("phoneNumber", "number.matches");
        }

        String email = user.getEmail();
        ValidationUtils.rejectIfEmpty(errors,"email","email.empty");
        if(!email.matches("^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")){
            errors.rejectValue("email", "email.matches");
        }
        //email validation


        //firstName validation
        String firstName = user.getFirstName();
        ValidationUtils.rejectIfEmpty(errors,"firstName","firstName.empty");
        if (firstName.length()<5 || firstName.length()>45){
            errors.rejectValue("firstName","firstName.length");
        }

        //lastName validation
        String lastName = user.getLastName();
        ValidationUtils.rejectIfEmpty(errors,"lastName","lastName.empty");
        if (lastName.length()<5 || lastName.length()>45){
            errors.rejectValue("lastName","lastName.length");
        }

        //age validation
        Integer age = user.getAge();
        ValidationUtils.rejectIfEmpty(errors,"age","age.empty");
        if (age<18){
            errors.rejectValue("age","age.young");
        }

    }
}
