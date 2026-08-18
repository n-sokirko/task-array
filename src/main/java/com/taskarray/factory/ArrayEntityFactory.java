package com.taskarray.factory;

import com.taskarray.entity.AbstractNumericArray;
import com.taskarray.exception.InvalidArrayDataException;
import java.util.List;

public interface ArrayEntityFactory {

  AbstractNumericArray createArrayEntity(List<String> tokens) throws InvalidArrayDataException;
}
