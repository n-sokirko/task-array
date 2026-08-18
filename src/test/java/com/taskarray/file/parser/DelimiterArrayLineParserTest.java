package com.taskarray.file.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class DelimiterArrayLineParserTest {

  private static final String COMMA_SEPARATED_LINE = "1, 2, 3";
  private static final String DASH_SEPARATED_LINE = "11- 2 – 42-";
  private static final String BLANK_LINE = "   ";
  private static final List<String> EXPECTED_COMMA_TOKENS = List.of("1", "2", "3");
  private static final List<String> EXPECTED_DASH_TOKENS = List.of("11", "2", "42");

  @Test
  void shouldSplitCommaSeparatedLineIntoTokens() {
    DelimiterArrayLineParser parser = new DelimiterArrayLineParser();

    List<String> tokens = parser.parseTokens(COMMA_SEPARATED_LINE);

    assertEquals(EXPECTED_COMMA_TOKENS, tokens);
  }

  @Test
  void shouldIgnoreTrailingDashDelimiter() {
    DelimiterArrayLineParser parser = new DelimiterArrayLineParser();

    List<String> tokens = parser.parseTokens(DASH_SEPARATED_LINE);

    assertEquals(EXPECTED_DASH_TOKENS, tokens);
  }

  @Test
  void shouldReturnEmptyListForBlankLine() {
    DelimiterArrayLineParser parser = new DelimiterArrayLineParser();

    List<String> tokens = parser.parseTokens(BLANK_LINE);

    assertTrue(tokens.isEmpty());
  }
}
