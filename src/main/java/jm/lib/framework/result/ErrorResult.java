package jm.lib.framework.result;

import lombok.Data;
import org.apache.commons.collections.MapUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.io.Serializable;
import java.util.*;

/**
 * Created by jiming.liu
 * TODO: multilevel error process
 */
@Data
public class ErrorResult extends Result implements Serializable {

    private boolean hasGlobalErrors;
    private boolean hasFieldErrors;
    private List<String> globalErrors = Collections.EMPTY_LIST;
    private List<FieldMessage> fieldErrors = Collections.EMPTY_LIST;

    public ErrorResult() {
    }

    public ErrorResult(String errorMessage) {
        this.setGlobalErrors(Arrays.asList(errorMessage));
    }

    public ErrorResult(BindingResult bindingResult, Map<String, String> fieldCodeNameMap) {
        this.setRet(bindingResult.hasErrors());
        this.hasGlobalErrors = bindingResult.hasGlobalErrors();
        this.hasFieldErrors = bindingResult.hasFieldErrors();
        if(bindingResult.hasGlobalErrors()) {
            ArrayList<String> errors = new ArrayList<>();
            for (ObjectError error : bindingResult.getGlobalErrors()) {
                errors.add(error.getDefaultMessage());
            }
            this.setGlobalErrors(errors);
        }
        if(bindingResult.hasFieldErrors()) {
            List<FieldMessage> errors = new ArrayList<>();
            bindingResult.getFieldErrors();
            for (FieldError error : bindingResult.getFieldErrors()) {
                FieldMessage message = new FieldMessage(error.getField()
                        , MapUtils.getString(fieldCodeNameMap, error.getField(), error.getField())
                        , error.getRejectedValue()
                        , error.getDefaultMessage()
                );
                errors.add(message);
            }
            this.setFieldErrors(errors);
        }

    }


    public <T> ErrorResult(Set<ConstraintViolation<T>> validations, Map<String, String> fieldCodeNameMap) {
        if(CollectionUtils.isEmpty(validations)) {
            return;
        }
        List<FieldMessage> errors = new ArrayList<>();
        for (ConstraintViolation v : validations) {
            String name = v.getPropertyPath().toString();
            FieldMessage f = null;
            if(name.indexOf('.') >= 0) {
                name = name.replaceAll("\\[.*?\\]", ""); // TODO: map as well
            }
            f = new FieldMessage(name, MapUtils.getString(fieldCodeNameMap, name), v.getInvalidValue(), v.getMessage());
            errors.add(f);
        }
        this.setFieldErrors(errors);
    }


    public boolean isHasGlobalErrors() {
        return hasGlobalErrors;
    }

    public void setHasGlobalErrors(boolean hasGlobalErrors) {
        this.hasGlobalErrors = hasGlobalErrors;
        this.updateHasErrors();
    }

    public boolean isHasFieldErrors() {
        return hasFieldErrors;
    }

    public void setHasFieldErrors(boolean hasFieldErrors) {
        this.hasFieldErrors = hasFieldErrors;
        this.updateHasErrors();
    }

    public List<String> getGlobalErrors() {
        return globalErrors;
    }

    public void setGlobalErrors(List<String> globalErrors) {
        this.globalErrors = globalErrors;
        this.setHasGlobalErrors(!CollectionUtils.isEmpty(this.globalErrors));
    }

    public List getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List fieldErrors) {
        this.fieldErrors = fieldErrors;
        this.setHasFieldErrors(!CollectionUtils.isEmpty(this.fieldErrors));
    }


    private void updateHasErrors() {
        this.setRet(this.hasFieldErrors || this.hasGlobalErrors);
    }


    @Data
    public static class FieldMessage {
        private String name;
        private String displayName;
        private Object rejectedValue;
        private String message;

        public FieldMessage(){}

        public FieldMessage(String name, String displayName, Object rejectedValue, String message) {
            this.name = name;
            this.displayName = displayName;
            this.rejectedValue = rejectedValue;
            this.message = message;
        }

    }


    @Override
    public String toString() {
        return "ErrorData{" +
                "hasErrors=" + isRet() +
                ", hasGlobalErrors=" + hasGlobalErrors +
                ", hasFieldErrors=" + hasFieldErrors +
                ", globalErrors=" + globalErrors +
                ", fieldErrors=" + fieldErrors +
                '}';
    }
}
