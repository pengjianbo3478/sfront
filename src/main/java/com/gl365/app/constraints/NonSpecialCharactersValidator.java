package com.gl365.app.constraints;

/**
 * @author Chen, Zhuhui
 */
public class NonSpecialCharactersValidator extends ExtensiblePatternValidator<NonSpecialCharacters> {

    public NonSpecialCharactersValidator() {
        super("^[^~`!@#$%^&<>]*$");
    }

}
