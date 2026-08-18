package com.taskarray.file.parser;

import java.util.List;

public interface ArrayLineParser {

  List<String> parseTokens(String line);
}
