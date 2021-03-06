package design.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe class <code>Work</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("PMD.ShortClassName")
public class Work {
  private Calculator workType;
  private List<String> jobs = new ArrayList<>();

  /**
   * Creates a new <code>Work</code> instance.
   *
   * @param workType a <code>Calculator</code> value
   * @param work list of tasks
   */
  public Work(Calculator workType, List<String> work) {
    this.workType = workType;
    this.jobs = work;
  }

  /**
   * Describe <code>getWorkType</code> method here.
   *
   * @return a <code>Calculator</code> value
   */
  public Calculator getWorkType() {
    return workType;
  }

  /**
   * Describe <code>setWorkType</code> method here.
   *
   * @param workType a <code>Calculator</code> value
   */
  public void setWorkType(Calculator workType) {
    this.workType = workType;
  }

  /**
   * Describe <code>getWork</code> method here.
   *
   * @return a <code>List</code> object
   */
  public List<String> getWork() {
    return jobs;
  }

  /**
   * Describe <code>getWork</code> method here.
   *
   * @param from start index of tasks.
   * @param to end index of tasks.
   * @return a <code>List</code> object
   */
  public List<String> getWork(int from, int to) {
    return jobs.subList(from, to);
  }

  /**
   * Describe <code>getWorkSize</code> method here.
   *
   * @return an <code>int</code> object
   */
  public int getWorkSize() {
    return jobs.size();
  }

  /**
   * Describe <code>setWork</code> method here.
   *
   * @param work list of tasks.
   */
  public void setWork(List<String> work) {
    this.jobs = work;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(46);
    builder.append("Work [workType=").append(workType).append(", work=").append(jobs).append(']');
    return builder.toString();
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Work)) return false;
    Work other = (Work) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$workType = this.getWorkType();
    Object other$workType = other.getWorkType();
    if (this$workType == null ? other$workType != null : !this$workType.equals(other$workType)) return false;
    Object this$jobs = this.jobs;
    Object other$jobs = other.jobs;
    if (this$jobs == null ? other$jobs != null : !this$jobs.equals(other$jobs)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof Work;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $workType = this.getWorkType();
    result = result * PRIME + ($workType == null ? 43 : $workType.hashCode());
    Object $jobs = this.jobs;
    result = result * PRIME + ($jobs == null ? 43 : $jobs.hashCode());
    return result;
  }
}
