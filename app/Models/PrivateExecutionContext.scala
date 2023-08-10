package Models

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

object PrivateExecutionContext {
  private lazy val numOfThreads = 16
  implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService {
    Executors.newFixedThreadPool(numOfThreads)
  }

}
