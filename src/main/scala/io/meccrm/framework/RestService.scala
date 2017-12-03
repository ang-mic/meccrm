package io.meccrm.framework


trait RestService {
  protected def getAll[E <: Entity]: Seq[E]

  protected def getById[E <: Entity](id: Int): E
}
