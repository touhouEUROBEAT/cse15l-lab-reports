import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MarkdownParseTest {
    @Test
    public void snip1() throws IOException {
        String contents= Files.readString(Path.of("/home/spaike97/Documents/UCSDw22/CSE15L/cse15l-lab-reports/misc/snip1.md"));
        List<String> expect = List.of("'google.com", "google.com", "ucsd.edu");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
    
    @Test
    public void snip2() throws IOException {
        String contents= Files.readString(Path.of("/home/spaike97/Documents/UCSDw22/CSE15L/cse15l-lab-reports/misc/snip2.md"));
        List<String> expect = List.of("a.com", "a.com(())", "example.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void snip3() throws IOException {
        String contents= Files.readString(Path.of("/home/spaike97/Documents/UCSDw22/CSE15L/cse15l-lab-reports/misc/snip3.md"));
        List<String> expect = List.of("https://ucsd-cse15l-w22.github.io/");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
}
