package helpers;

import org.springframework.validation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationHelper {

    public static Map<String, ArrayList<String>> validate(Object obj, Validator validator) {
        DataBinder binder = new DataBinder(obj);
        binder.setValidator(validator);
        binder.validate();
        List<ObjectError> errorsList = binder.getBindingResult().getAllErrors();

        Map<String, ArrayList<String>> result = new HashMap<>();
        for (ObjectError e : errorsList) {
            String field = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            if (!result.containsKey(field)) {
                result.put(field, new ArrayList<>());
            }
            result.get(field).add(message);
        }
        return result;
    }
}
