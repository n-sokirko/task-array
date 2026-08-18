package com.taskarray.file.reader;

import java.io.IOException;
import java.util.List;

public interface ArrayDataFileReader {

  List<String> readLines(String relativeFilePath) throws IOException;
}
