package io.meccrm.framework


trait RestService[E <: Entity] {
  def getAll: Seq[E]
  def getById(id: Int): E
  def create(entity: E): Int
  def update(id: Int, entity: E): Int
  def delete(id: Int): Boolean
}
