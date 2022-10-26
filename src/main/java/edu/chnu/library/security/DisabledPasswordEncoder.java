package edu.chnu.library.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 02.10.2022 21:14
 * @class DisabledPasswordEncoder
 */
public class DisabledPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
    }
}
