
  
   Illustrate bug in scala-compiler or in scala macroparadies plugin.
   
   sbt test:compile
  
  You will received message:

  required: (c: scala.reflect.macros.Context)(ch: c.Expr[x.Channel[A]]): c.Expr[c.universe.Block]
  found   : (c: scala.reflect.macros.Context)(ch: c.Expr[x.Channel[A]]): c.Expr[c.universe.Block]


