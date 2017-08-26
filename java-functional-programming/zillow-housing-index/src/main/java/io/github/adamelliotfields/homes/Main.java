package io.github.adamelliotfields.homes;

import io.github.adamelliotfields.homes.model.HousingRecord;
import io.github.adamelliotfields.homes.service.HousingRecordService;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
  public static Function<Integer, String> getArgentinaPriceConverter() {
    Locale argLocale = Locale.forLanguageTag("es-AR");
    // This fluctuates and is hardcoded temporarily
    BigDecimal argPesoToUsdRate = new BigDecimal("15.48");

    Function<Integer, BigDecimal> usdToArgentinePesoConverter = (usd) -> argPesoToUsdRate.multiply(new BigDecimal(usd));

    Function<BigDecimal, String> argentineCurrencyFormatter = (price) -> {
      Currency currentCurrency = Currency.getInstance(argLocale);

      NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(argLocale);

      return String.format("%s (%s)",
        currencyFormatter.format(price),
        currentCurrency.getDisplayName()
      );
    };

    return usdToArgentinePesoConverter.andThen(argentineCurrencyFormatter);
  }

  public static Function<Integer, String> createPriceConverter(Locale locale, BigDecimal usdRate) {
    return usdPrice -> String.format("%s (%s)",
        NumberFormat.getCurrencyInstance(locale).format(usdRate.multiply(BigDecimal.valueOf(usdPrice))),
        Currency.getInstance(locale).getDisplayName()
    );
  }

  public static String getPriceConversionForRecord(HousingRecord record, Function<Integer, String> priceConverter) {
    NumberFormat usFormatter = NumberFormat.getCurrencyInstance(Locale.US);

    return String.format("%s is %s (USD) which is %s",
      record.getRegionName(),
      usFormatter.format(record.getCurrentHomeValueIndex()),
      priceConverter.apply(record.getCurrentHomeValueIndex())
    );
  }

  public static List<String> getConvertedPriceStatements(List<HousingRecord> records, Function<Integer, String> priceConverter) {
    return records.stream()
                  .sorted(Comparator.comparingInt(HousingRecord::getCurrentHomeValueIndex))
                  .map(record -> getPriceConversionForRecord(record, priceConverter))
                  .collect(Collectors.toList());
  }

  public static List<String> getStateCodesFromRecords(List<HousingRecord> records) {
    return records.stream()
                  .map(HousingRecord::getState)
                  .filter(state -> !state.isEmpty())
                  .distinct()
                  .sorted()
                  .collect(Collectors.toList());
  }

  public static void displayStateCodeMenuDeclaratively(List<String> stateCodes) {
    IntStream.rangeClosed(1, stateCodes.size())
             .mapToObj(i -> String.format("%d. %s", i, stateCodes.get(i - 1)))
             .forEach(System.out::println);
  }

  public static OptionalInt getLowestHomeValueIndexDeclaratively(List<HousingRecord> records) {
    return records.stream()
                  .mapToInt(HousingRecord::getCurrentHomeValueIndex)
                  .min();
  }

  public static Optional<HousingRecord> getHighestValueIndexHousingRecordDeclaratively(List<HousingRecord> records) {
    return records.stream()
                  .max(Comparator.comparingInt(HousingRecord::getCurrentHomeValueIndex));
  }

  public static void main(String[] args) {
    HousingRecordService service = new HousingRecordService();
    List<HousingRecord> records = service.getAllHousingRecords();

    // The first housing record is a summary of all of the United States
    HousingRecord usRecord = records.get(0);

    System.out.print("Hardcoded price converter method: ");

    Function<Integer, String> argentinaPriceConverter = getArgentinaPriceConverter();

    String priceConversion = getPriceConversionForRecord(
        usRecord,
        argentinaPriceConverter
    );

    System.out.println(priceConversion);
    System.out.print("Higher Order Function price converter");

    String closurePriceConversion = getPriceConversionForRecord(
        usRecord,
        createPriceConverter(Locale.forLanguageTag("es-AR"), new BigDecimal("15.48"))
    );

    System.out.println(closurePriceConversion);
    // Once you get the `createPriceConverter` method working, it'll work for any country!
    System.out.println("Higher Order Function showing off:");

    getConvertedPriceStatements(records, createPriceConverter(
        Locale.CANADA,
        new BigDecimal("1.35")
    )).forEach(System.out::println);
  }
}
