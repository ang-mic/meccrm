package io.meccrm.domain.customer


//case class Customer(name: String, address: Address, trn: TrnRef, pfs: PfsRef, phoneNum: String)
case class Customer(id: Option[Int] = None, firstName: String, lastName: String, company: String)

case class Address(address: String, city: String, country: String, postCode: String)

//NOTE: Tax Registration Number
case class TrnRef(trn: String)

//NOTE: Public Financial Service
case class PfsRef(pfs: String)
