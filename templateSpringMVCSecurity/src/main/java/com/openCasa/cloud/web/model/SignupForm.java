package com.openCasa.cloud.web.model;

public class SignupForm {

	private static final String NOT_BLANK_MESSAGE = "{signupForm.error.notBlank}";
	private static final String EMAIL_MESSAGE = "{signupForm.error.email}";

	// @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	// @Email(message = SignupForm.EMAIL_MESSAGE)
	private String email;

	// @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account createAccount() {
		return new Account(getEmail(), getPassword(), "ROLE_USER");
	}
}
