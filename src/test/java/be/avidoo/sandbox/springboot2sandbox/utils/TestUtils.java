package be.avidoo.sandbox.springboot2sandbox.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

public class TestUtils {

    /**
     * Read a file in as String. Resource files must be in the same package (test-resources) as the Class
     *
     * @param clazz
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String readFileAsString(Class<?> clazz, String fileName) throws IOException {
        File file = ResourceUtils.getFile(clazz.getResource(fileName));

        return FileUtils.readFileToString(file);
    }
}
