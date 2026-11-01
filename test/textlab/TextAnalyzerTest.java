package textlab;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TextAnalyzerTest {

    @Test
    void testWhitespaceCleanup() {
        TextAnalyzer analyzer = new TextAnalyzer("  Hello    world! \t Java and \n JavaScript.  ");
        assertEquals("Hello world! Java and JavaScript.", analyzer.cleanWhitespace());
    }

    @Test
    void testWholeWordSearchIgnoresSubstrings() {
        TextAnalyzer analyzer = new TextAnalyzer("Java and JavaScript are cool. java is fast.");
        assertEquals(2, analyzer.searchWordFrequency("Java"));
    }

    @Test
    void testPalindromeCheck() {
        TextAnalyzer analyzer = new TextAnalyzer("A man, a plan, a canal: Panama!");
        assertTrue(analyzer.isPalindrome());
    }

    @Test
    void testLongestWords() {
        TextAnalyzer analyzer = new TextAnalyzer("Cat elephant dog giraffe");
        List<String> longest = analyzer.getLongestWords();
        assertEquals(1, longest.size());
        assertEquals("giraffe", longest.get(0));
    }
}

