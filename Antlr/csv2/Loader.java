package csv2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Loader extends CSV2BaseListener {
  public static final String EMPTY = "";
  // Load a list of row maps that map field name to value
  List<Map<String, String>> rows = new ArrayList<>();
  // List of column names
  List<String> header = new ArrayList<>();
  // Build up a list of fields in current row
  List<String> currentRowFieldValues;

  @Override
  public void exitHdr(CSV2Parser.HdrContext ctx) {
    header.clear();
    header.addAll(currentRowFieldValues);
  }

  @Override
  public void enterRow(CSV2Parser.RowContext ctx) {
    currentRowFieldValues = new ArrayList<>();
  }

  @Override
  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.DataflowAnomalyAnalysis"})
  public void exitRow(CSV2Parser.RowContext ctx) {
    // If this is the header row, do nothing
    // if ( ctx.parent instanceof CSVParser.HdrContext ) return; OR:
    if (ctx.getParent().getRuleIndex() == CSV2Parser.RULE_hdr)
      return;
    // It's a data row
    Map<String, String> m = new LinkedHashMap<>();
    int i = 0;
    for (String v: currentRowFieldValues) {
      m.put(header.get(i), v);
      i++;
    }
    rows.add(m);
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void exitString(CSV2Parser.StringContext ctx) {
    currentRowFieldValues.add(ctx.STRING().getText());
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void exitText(CSV2Parser.TextContext ctx) {
    currentRowFieldValues.add(ctx.TEXT().getText());
  }

  @Override
  public void exitEmpty(CSV2Parser.EmptyContext ctx) {
    currentRowFieldValues.add(EMPTY);
  }
}