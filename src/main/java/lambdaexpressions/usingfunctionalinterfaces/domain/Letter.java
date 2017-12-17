package lambdaexpressions.usingfunctionalinterfaces.domain;

public class Letter {

    public static String addHeader(String text) {
        return "From Yevgeniy Alexeyenko: " + text;
    }

    public static String checkSpelling(String text) {
        return text.replaceAll("lamda", "lambda");
    }

    public static String addFooter(String text) {
        return text + " Best regards.";
    }
}
