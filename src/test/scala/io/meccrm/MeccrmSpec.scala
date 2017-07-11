package io.meccrm

import org.scalatest.FlatSpec

class MeccrmSpec extends FlatSpec{
  behavior of "Meccrm"
  it should "allow user to perform CRUD operations on `Customer` resource" in {}
  it should "allow user to perform CRUD operations on `Licence` resource" in {}
  it should "allow user to perform generic search against `Customer` and `License`" in {}

  "A `Customer`" should "have `name`, `address`, `AFM`, `DOI` and telephone" in {}
  "A `License`" should "belong to a `Customer`" in {}
  "A `License`" should "have `startDate`, `endDate`, `type`" in {}
}
