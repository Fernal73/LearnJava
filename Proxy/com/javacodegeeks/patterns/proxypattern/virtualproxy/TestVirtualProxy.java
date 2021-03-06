package com.javacodegeeks.patterns.proxypattern.virtualproxy;

import java.util.List;

public enum TestVirtualProxy {
  ;
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  public static void main(String[] args) {
    Company company = new Company(
        "ABC Company", "India", "+91-011-28458965", new ContactListProxyImpl());
    System.out.println("Company Name: " + company.getCompanyName());
    System.out.println("Company Address: " + company.getCompanyAddress());
    System.out.println("Company Contact No.: " + company.getCompanyContactNo());
    System.out.println("Requesting for contact list");
    printEmployees(company);
  }

  private static void printEmployees(Company company) {
    ContactList contactList = company.getContactList();
    printContactList(contactList);
  }

  private static void printContactList(ContactList contactList) {
    printList(contactList.getEmployeeList());
  }

  private static void printList(List<Employee> empList) {
    for (Employee emp: empList)
      System.out.println(emp);
  }
}
