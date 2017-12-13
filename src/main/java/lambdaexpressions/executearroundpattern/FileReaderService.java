package lambdaexpressions.executearroundpattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class FileReaderService {

    public String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("F:/WS/IdeaWorkspace/j8inA/src/main/resources/data.txt"))) {
            return p.process(br);
        }
    }
}