package com.taskarray.factory;

import com.taskarray.entity.AbstractNumericArray;
import com.taskarray.entity.DoubleArrayEntity;
import com.taskarray.exception.InvalidArrayDataException;
import com.taskarray.validation.TokenValidator;
import java.util.List;

public final class DoubleArrayEntityFactory implements ArrayEntityFactory {

  private final TokenValidator tokenValidator;

  public DoubleArrayEntityFactory(TokenValidator tokenValidator) {
    this.tokenValidator = tokenValidator;
  }

  @Override
  public AbstractNumericArray createArrayEntity(List<String> tokens) throws InvalidArrayDataException {
    DoubleArrayEntity.Builder builder = new DoubleArrayEntity.Builder();
    for (String token : tokens) {
      if (!tokenValidator.isValid(token)) {
        throw new InvalidArrayDataException("Invalid double token: '" + token + "'");
      }
      builder.addValue(parseDoubleToken(token));
    }
    return builder.build();
  }

  private double parseDoubleToken(String token) {
    return Double.parseDouble(token);
  }
}
