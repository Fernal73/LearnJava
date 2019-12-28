package com.javacodegeeks.patterns.commandpattern;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class EmailJob implements Job {
  private Email email;

  public void setEmail(Email email) {
    this.email = email;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public void run() {
    System.out.println("Job ID: " + Thread.currentThread().getId()
                       + " executing email jobs.");

    if (email != null) {
      email.sendEmail();
    }
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
