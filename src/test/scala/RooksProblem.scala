import org.scalatest.FlatSpec

class RooksProblemSpec extends FlatSpec {

  "An empty Set" should "have size 0" in {
    RooksProblem.show(RooksProblem.findPermutations(9))
  }

}