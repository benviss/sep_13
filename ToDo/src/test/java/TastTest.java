import org.junit.*;
import static org.junit.Assert.*;

public class TaskTest {

  @Test
  public void Task_isntantiatesCorrectly_true() {
    Task myTask = new Task("Mow the lawn");
    assertEquals(true, myTask instanceof Task);
  }


}
