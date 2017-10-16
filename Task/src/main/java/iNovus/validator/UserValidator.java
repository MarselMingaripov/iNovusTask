package iNovus.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import iNovus.service.UserService;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        iNovus.models.User user = (iNovus.models.User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");

        if (user.getUsername().length() < 8 || user.getUsername().length() > 32){
            errors.rejectValue("username", "Size.userForm.username");
        }

        if(userService.findByUsername(user.getUsername()) != null){
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        if (user.getPassword().length() < 8 || user.getPassword().length() > 32){
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())){
            errors.rejectValue("confirmPassword", "Diff.userForm.passwordConfirm");
        }
    }
}
