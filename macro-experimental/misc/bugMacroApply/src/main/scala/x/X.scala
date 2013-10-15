package x

import language.experimental.macros
import scala.reflect.macros._
import scala.reflect.api._

class Y
{
  def apply(x:Int): Unit = macro X.fImpl
}

object X
{
 
  def xProvider = macro xProviderImpl

  def xProviderImpl(c:Context) =
  {
   import c.universe._
/*
   val rtree = q"""
   {
    class H {
       def apply(x:Int): Unit = macro X.fImpl
    }
    new H { }
   }
   """
*/
   import Flag._
   val rtree = {
Block(List(ClassDef(Modifiers(), newTypeName("H"), List(), Template(List(Select(Ident(newTermName("scala")), newTypeName("AnyRef"))), ValDef(Modifiers(PRIVATE), nme.WILDCARD, TypeTree(), EmptyTree), List(DefDef(Modifiers(), nme.CONSTRUCTOR, List(), List(List()), TypeTree(), Block(List(Apply(Select(Super(This(tpnme.EMPTY), tpnme.EMPTY), nme.CONSTRUCTOR), List())), Literal(Constant(())))), DefDef(Modifiers(MACRO), newTermName("apply"), List(), List(List(ValDef(Modifiers(PARAM), newTermName("x"), Ident(newTypeName("Int")), EmptyTree))), Ident(newTypeName("Unit")), Select(Ident(newTermName("X")), newTermName("fImpl"))))))), Block(List(ClassDef(Modifiers(FINAL, nme.EMPTY), newTypeName("$anon"), List(), Template(List(Ident(newTypeName("H"))), ValDef(Modifiers(PRIVATE), nme.WILDCARD, TypeTree(), EmptyTree), List(DefDef(Modifiers(), nme.CONSTRUCTOR, List(), List(List()), TypeTree(), Block(List(Apply(Select(Super(This(tpnme.EMPTY), tpnme.EMPTY), nme.CONSTRUCTOR), List())), Literal(Constant(())))), EmptyTree)))), Apply(Select(New(Ident(newTypeName("$anon"))), nme.CONSTRUCTOR), List())))
}
   c.Expr[Any](rtree)
  }

  def fImpl(c:Context)(x: c.Expr[Int]) =
  {
   import c.universe._
   reify{ () }
  }
     

}
