package threads;
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
@SuppressWarnings({"PMD.ShortClassName", "PMD.SystemPrintln"})
public class Drop {
  // Message sent from producer
  // to consumer.
  private String message = "";
  // True if consumer should wait
  // for producer to send message,
  // false if producer should wait for
  // consumer to retrieve message.
  private boolean empty = true;
  private Object obj = new Object();

  public String take() {
    synchronized (obj) {
      // Wait until message is
      // available.
      while (empty) {
        try {
          obj.wait(100);
        } catch (InterruptedException e) {
          System.err.println(e);
        }
      }
      // Toggle status.
      empty = true;
      // Notify producer that
      // status has changed.
      obj.notifyAll();
      return message;
    }
  }

  @SuppressWarnings("checkstyle:hiddenfield")
  public void put(String message) {
    synchronized (obj) {
      // Wait until message has
      // been retrieved.
      while (!empty) {
        try {
          obj.wait(100);
        } catch (InterruptedException e) {
          System.err.println(e);
        }
      }
      // Toggle status.
      empty = false;
      // Store message.
      this.message = message;
      // Notify consumer that status
      // has changed.
      obj.notifyAll();
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Drop))
      return false;
    Drop other = (Drop)o;
    if (!other.canEqual((Object)this))
      return false;
    Object this$message = this.message;
    Object other$message = other.message;
    if (this$message == null ? other$message != null
                             : !this$message.equals(other$message))
      return false;
    if (this.empty != other.empty)
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof Drop;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $message = this.message;
    result = result * PRIME + ($message == null ? 43 : $message.hashCode());
    result = result * PRIME + (this.empty ? 79 : 97);
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "Drop(message=" + this.message + ", empty=" + this.empty + ")";
  }
}
