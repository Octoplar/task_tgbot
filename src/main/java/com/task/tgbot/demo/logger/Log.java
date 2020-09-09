package com.task.tgbot.demo.logger;

public class Log {
    public static void debug(String message) {
        System.out.println(message);
    }
    public static void debug(String template, Object... args) {
        debug(String.format(template, args));
    }

    public static void info(String message) {
        System.out.println(message);
    }
    public static void info(String template, Object... args) {
        info(String.format(template, args));
    }

    public static void warn(String message) {
        System.out.println(message);
    }
    public static void warn(String template, Object... args) {
        warn(String.format(template, args));
    }

    public static void error(String message) {
        System.out.println(message);
    }
    public static void error(String template, Object... args) {
        error(String.format(template, args));
    }
}
