package textlab;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalyzer {
    private final String originalText;

    public TextAnalyzer(String originalText) {
        this.originalText = originalText == null ? "" : originalText;
    }

    public String getOriginalText() { return originalText; }

    public int getCharacterCountWithWhitespace() {
        return originalText.length();
    }

    public int getCharacterCountWithoutWhitespace() {
        return originalText.replaceAll("\\s+", "").length();
    }

    public int getWordCount() {
        if (originalText.isBlank()) return 0;
        String[] tokens = originalText.trim().split("\\s+");
        int count = 0;
        for (String token : tokens) {
            if (token.matches(".*[a-zA-Z0-9].*")) count++;
        }
        return count;
    }

    public int getSentenceCount() {
        if (originalText.isBlank()) return 0;
        String[] sentences = originalText.split("[.!?]+");
        int count = 0;
        for (String s : sentences) {
            if (!s.isBlank()) count++;
        }
        return Math.max(count, 1);
    }

    public String cleanWhitespace() {
        return originalText.trim().replaceAll("\\s+", " ");
    }

    public int searchWordFrequency(String targetWord) {
        if (targetWord == null || targetWord.isBlank() || originalText.isBlank()) return 0;
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(targetWord) + "\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(originalText);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public String replaceWord(String targetWord, String replacement) {
        if (targetWord == null || targetWord.isBlank()) return originalText;
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(targetWord) + "\\b", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(originalText).replaceAll(replacement);
    }

    public boolean isPalindrome() {
        String normalized = originalText.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        if (normalized.isEmpty()) return false;
        String reversed = new StringBuilder(normalized).reverse().toString();
        return normalized.equals(reversed);
    }

    public List<String> getLongestWords() {
        if (originalText.isBlank()) return Collections.emptyList();
        String cleaned = originalText.replaceAll("[^a-zA-Z0-9 ]", "");
        String[] tokens = cleaned.split("\\s+");

        Set<String> uniqueWords = new HashSet<>();
        int maxLength = 0;

        for (String token : tokens) {
            if (token.isEmpty()) continue;
            if (token.length() > maxLength) {
                maxLength = token.length();
                uniqueWords.clear();
                uniqueWords.add(token);
            } else if (token.length() == maxLength) {
                uniqueWords.add(token);
            }
        }
        return new ArrayList<>(uniqueWords);
    }
}

