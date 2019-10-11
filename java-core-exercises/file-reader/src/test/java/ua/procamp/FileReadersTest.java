package ua.procamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

import lombok.SneakyThrows;

@RunWith(JUnit4.class)
public class FileReadersTest {

    @Test
    @SneakyThrows
    public void testReadWholeFileOnEmptyFile() {
        String fileContent = FileReaders.readWholeFile("empty.txt");

        assertEquals("", fileContent);

    }

    @Test
    @SneakyThrows
    public void testReadWholeFileOnFileWithEmptyLines() {
        String fileContent = FileReaders.readWholeFile("lines.txt");

        assertEquals("Hey!\n" +
                "\n" +
                "What's up?\n" +
                "\n" +
                "Hi!", fileContent);
    }

    @Test
    @SneakyThrows
    public void testReadWholeFile() {
        String fileContent = FileReaders.readWholeFile("simple.txt");

        assertEquals("Hello!\n" + "It's a test file.", fileContent);
    }
}
