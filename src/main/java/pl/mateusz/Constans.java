package pl.mateusz;

public class Constans {
    public static final String REGEX_PASSWORD = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&*()_+]).{8,})";
    public static final String REGEX_PHONE = "(?:\\d{3}-){2}\\d{3}";
    public static final String REGEX_MAIL = "^[A-Za-z0-9+_.-]+@(.+)$";

}
