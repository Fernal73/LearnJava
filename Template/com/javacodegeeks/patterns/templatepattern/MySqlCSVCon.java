package com.javacodegeeks.patterns.templatepattern;

@SuppressWarnings("PMD.SystemPrintln")
public class MySqlCSVCon extends ConnectionTemplate {
  @Override
  public void setDBDriver() {
    System.out.println("Setting MySQL DB drivers...");
  }

  @Override
  public void setCredentials() {
    System.out.println("Setting credentials for MySQL DB...");
  }

  @Override
  public void setData() {
    System.out.println("Setting up data from csv file....");
  }
}
