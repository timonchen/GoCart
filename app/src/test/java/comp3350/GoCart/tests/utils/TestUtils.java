package comp3350.GoCart.tests.utils;

import java.io.File;
import java.io.IOException;
import com.google.common.io.Files;

import comp3350.GoCart.application.Main;

public class TestUtils {
    private static final File DB_SRC = new File("src/main/assets/db/database.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("temp-db", ".script");
        Files.copy(DB_SRC, target);
        Main.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        return target;
    }
}
