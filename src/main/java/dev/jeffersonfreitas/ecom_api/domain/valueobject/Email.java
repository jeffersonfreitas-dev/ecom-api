package dev.jeffersonfreitas.ecom_api.domain.valueobject;

import dev.jeffersonfreitas.ecom_api.domain.exception.InvalidValueObjectException;

import java.util.regex.Pattern;

public final class Email {

    private static final String REGEX_PADRAO = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern PATTERN_PADRAO = Pattern.compile(REGEX_PADRAO);

    private final String email;
    
    public Email(String email){
        validateFormat(email);
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    private void validateFormat(String email) {
        if (email == null || email.isBlank()){
            throw new InvalidValueObjectException("O e-mail não pode ser nulo ou vazio");
        }

        boolean isValid = PATTERN_PADRAO.matcher(email).matches();
        if (!isValid){
            throw new InvalidValueObjectException("O e-mail informado não está no formato válido");
        }
    }
}
