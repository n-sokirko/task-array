# Task Array — Part I (Light)

Учебное приложение: чтение массивов из текстовых файлов, их валидация,
создание через Factory Method/Builder, и сервисы поиска min/max, суммы,
среднего (через `Optional`) и сортировки (два алгоритма).

## Структура

```
data/                     исходные данные (.txt), вне src, с частично некорректными строками
  int-arrays.txt
  double-arrays.txt
src/main/java/com/taskarray/
  entity/                 AbstractNumericArray + IntegerArrayEntity/DoubleArrayEntity (Builder)
  exception/              InvalidArrayDataException (своё исключение)
  validation/             TokenValidator + Integer/DoubleTokenValidator
  file/reader/            чтение файла (Files/Paths, Java 7+)
  file/parser/            разбиение строки на токены (регулярное выражение)
  factory/                ArrayEntityFactory, SortServiceFactory (Factory Method)
  service/statistics/     поиск min/max, сумма, среднее (Optional)
  service/sort/           BubbleSortService, SelectionSortService
  app/Application.java    точка входа
src/main/resources/log4j2.xml
src/test/java/...         тесты JUnit5 (given/when/then)
```

## Запуск в IntelliJ IDEA

1. `File -> Open...` -> выбрать папку `D:\TaskArrayLight` (папку с `pom.xml`).
2. Дождаться, пока IntelliJ подтянет зависимости Maven (Log4j2, JUnit5).
3. Открыть `src/main/java/com/taskarray/app/Application.java`, запустить `main`.
   Working directory по умолчанию — корень модуля, поэтому относительные
   пути `data/int-arrays.txt` и `data/double-arrays.txt` найдутся сами.
4. Логи пишутся в консоль и в файл `logs/app.log` (папка создаётся автоматически).

## Тесты

Правый клик на `src/test/java` -> `Run 'All Tests'`, либо `mvn test`.
