package io.meccrm.framework

import io.meccrm.framework.fixture.AppFixture
import org.scalatest.FlatSpec

class BootableSpec extends FlatSpec {

  "A `Bootable`" should "return `Sig(0)` when `boot` is successful" in new AppFixture {
    withApp {
      println("TEST :D 1")
    }
  }

  it should "return `Sig(0)` when `halt` gracefully" in new AppFixture {}
}
