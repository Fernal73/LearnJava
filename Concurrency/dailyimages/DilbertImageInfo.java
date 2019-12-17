package dailyimages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class DilbertImageInfo extends ImageInfo {
  @Override
  public DilbertImageInfo findImage(String body) {
    this.imagePath = findProperty(body, "image");
    return this;
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  private static String findProperty(String body,
                                     String filter) {
    String search =
        "meta name=\"twitter:" + filter + "\" content=\"";
    return body.lines()
        .filter(line -> line.contains(search))
        .findFirst()
        .map(line -> line.replaceAll(".*" + search, ""))
        .map(line -> line.replaceAll("\".*", ""))
        .orElseThrow(
            ()
                -> new IllegalStateException(
                    "Could not find \"" + filter + "\""));
  }

  @Override
  public String getUrlForDate(LocalDate date) {
    return "https://dilbert.com/strip/"
        + DateTimeFormatter.ISO_DATE.format(date);
  }
}
