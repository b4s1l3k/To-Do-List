package Models

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

object PrivateExecutionContext {
  val executor = Executors.newFixedThreadPool(16)
  implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(executor)

}
