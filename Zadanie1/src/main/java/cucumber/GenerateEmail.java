package cucumber;

    public class GenerateEmail {

        public static String withTimestamp() {
            String currentTs = Long.toString(System.currentTimeMillis());
            return "rogalski" + currentTs + "@gmail.com";
        }
    }

