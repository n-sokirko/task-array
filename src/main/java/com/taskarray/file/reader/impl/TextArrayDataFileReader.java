package com.taskarray.file.reader.impl;

import com.taskarray.file.reader.ArrayDataFileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class TextArrayDataFileReader implements ArrayDataFileReader {

  @Override
  public List<String> readLines(String relativeFilePath) throws IOException {
    Path path = Paths.get(relativeFilePath);
    return Files.readAllLines(path, StandardCharsets.UTF_8);
  }
}
