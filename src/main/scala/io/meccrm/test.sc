type numberSplitter = Int => Seq[Int]
//zipWithIndex
type evenMultiplier = ((Int, Int)) => Int
//sum
//modulo 11
// if 10 return 1

val splitIntToList: numberSplitter = num => {
  num.toString.split("").map(_.toInt)
}

val multiplicationFactor: evenMultiplier = numWithIndex => {
  (1 + 2 * (numWithIndex._2 % 2)) * numWithIndex._1
}

implicit class IntImprovements(num: Int) {
  def mod11 = num % 11
  def ifGreaterNineThenOne = if (num > 9) 1 else num
  def splitIntToList = num.toString.split("").map(_.toInt)
}


123456789.splitIntToList
  .zipWithIndex
  .map(multiplicationFactor)
  .sum
  .mod11
  .ifGreaterNineThenOne


trait ControlDigitFlow {
  type numberSplitter         = Int => Seq[Int]
  type evenMultiplier         = Seq[Int] => Seq[Int]
  type intAdder               = Seq[Int] => Int
  type modulo                 = Int => Int
  type capApplier             = Int => Int
  type controlDigitCalculator = Int => Int

  def integerAsList: numberSplitter
  def multiplicationFactor: evenMultiplier
  def sum: intAdder
  def modulo11: modulo
  def tenAsCap: capApplier
  def calculateControlDigit: controlDigitCalculator = {
    integerAsList
      .andThen(multiplicationFactor)
      .andThen(sum)
      .andThen(modulo11)
      .andThen(tenAsCap)
  }
}

def funB(i: Int): String
def funA(f: Int => String): Int =

