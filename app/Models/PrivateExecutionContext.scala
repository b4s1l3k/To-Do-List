package Models

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

object PrivateExecutionContext {
  private lazy val numOfThreads =  16
  private lazy val executor = Executors.newFixedThreadPool(numOfThreads)
  implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(executor)

}
