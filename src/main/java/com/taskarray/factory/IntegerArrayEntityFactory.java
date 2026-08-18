package com.taskarray.factory;

import com.taskarray.entity.AbstractNumericArray;
import com.taskarray.entity.IntegerArrayEntity;
import com.taskarray.exception.InvalidArrayDataException;
import com.taskarray.validation.TokenValidator;
import java.util.List;

public final class IntegerArrayEntityFactory implements ArrayEntityFactory {

  private final TokenValidator tokenValidator;

  public IntegerArrayEntityFactory(TokenValidator tokenValidator) {
    this.tokenValidator = tokenValidator;
  }

  @Override
  public AbstractNumericArray createArrayEntity(List<String> tokens) throws InvalidArrayDataException {
    IntegerArrayEntity.Builder builder = new IntegerArrayEntity.Builder();
    for (String token : tokens) {
      if (!tokenValidator.isValid(token)) {
        throw new InvalidArrayDataException("Invalid integer token: '" + token + "'");
      }
      builder.addValue(parseIntegerToken(token));
    }
    return builder.build();
  }

  private int parseIntegerToken(String token) {
    return Integer.parseInt(token);
  }
}
