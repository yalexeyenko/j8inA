package lambdaexpressions.executearroundpattern;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class FileReaderServiceTest {

    private FileReaderService service;

    @Before
    public void init() {
        this.service = new FileReaderService();
    }

    @Test
    public void shouldReadLinesFromFile() throws IOException {
        // when
        String result = service.processFile((BufferedReader br) -> br.readLine() + "\n" + br.readLine());
        // then
        assertNotNull(result);
        assertEquals(result, "line1" + "\n" + "line2");
    }
}