import java.lang.reflect.*;

public class Clonador <X>
{
public x clone (X x)
{
//assim obtemos a classe de instancia no objeto x,que, conforme sabemos , é a classe X, // e a armazenamos no objeto chamado classe
Class <?> class = x.getClass ();
//null porque chamaremos um método sem parâmetros
Class <?> [] tpsParmsForms = null;
// assim obtemos o método chamado clone, sem parâmetros, da Class <?> armazenada no //objeto classe (sabemos que a classe é a classe X)
Method método = null
try
{
Método = classe.getMethod (“clone”, tpsParmsForms);
}
catch (NoSuchMethodException erro)
{}
// null porque chamaremos um método sem parâmetros
Object [] parmsReais = null;.
// assim chamamos o método armazenado no objeto chamado método, fazendo com que //o objeto chamado x seja para ele o objeto chamante (o this) e fazendo com que não //receba parâmetros reais (por isso o vetor nulo chamado parmsReais é fornecido; o //resultado da chamada, que certamente é da classe X, mas que está sendo retornado como Object , tem seu tipo mudado para X e então, é atribuído ao objeto chamado ret
X ret = null;
try
{
ret = (X) método.invoke (x.parmsReais);
}
catch (InvocationTargetException erro)
{} // 
catch (IllegalAccessException erro)
 {}
// ret = (X) x.clone ();
return ret;
}
}
