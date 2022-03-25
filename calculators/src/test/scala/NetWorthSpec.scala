import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class NetWorthSpec extends AnyFlatSpec with Matchers {
  "A NetWorth Calculator" should "return 500" in {
    NetWorth.calculate(1000, 500) should be (500)
  }

  "A NetWorth Calculator" should "return 5000" in {
    NetWorth.calculate(10000, 5000) should be (5000)
  }
}
