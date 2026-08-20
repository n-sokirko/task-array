package com.taskarray.file.parser.impl;

import com.taskarray.file.parser.ArrayLineParser;
import java.util.ArrayList;
import java.util.List;

public final class DelimiterArrayLineParser implements ArrayLineParser {

  private static final String DELIMITER_REGEX = "[\\s,;\\-–]+";

  @Override
  public List<String> parseTokens(String line) {
    List<String> tokens = new ArrayList<>();
    String[] rawParts = line.trim().split(DELIMITER_REGEX);
    for (String part : rawParts) {
      if (!part.isEmpty()) {
        tokens.add(part);
      }
    }
    return tokens;
  }
}
