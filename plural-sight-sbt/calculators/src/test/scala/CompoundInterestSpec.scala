import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CompoundInterestSpec extends AnyFlatSpec with Matchers {
  "A Compound Interest Calculator" should "return more than 62000 in return" in {
    CompoundInterest.calculate(5000, 5, 10) should be(6.0466175E10)
  }
}
