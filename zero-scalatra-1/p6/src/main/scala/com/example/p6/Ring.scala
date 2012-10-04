package com.example.p6;

trait Ring
{

  type Self <: Ring;

  def +(x:Self): Self;
  def unary_- : Self;
  def unary_+ : this.type = this;

  def *(y: Self): Self;

}

case class Z3(v:Int) extends Ring
{

  type Self = Z3;

  def +(x:Z3): Z3 = Z3((_v+x._v)%3) ;
  def unary_- : Z3 = Z3(3 - _v);

  def *(x:Z3) = Z3((_v*x._v)%3);

  private val _v = v % 3;
}


