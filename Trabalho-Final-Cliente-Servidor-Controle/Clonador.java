import java.lang.reflect.*;

public class Clonador <X>
{
public x clone (X x)
{
//assim obtemos a classe de instancia no objeto x,que, conforme sabemos , � a classe X, // e a armazenamos no objeto chamado classe
Class <?> class = x.getClass ();
//null porque chamaremos um m�todo sem par�metros
Class <?> [] tpsParmsForms = null;
// assim obtemos o m�todo chamado clone, sem par�metros, da Class <?> armazenada no //objeto classe (sabemos que a classe � a classe X)
Method m�todo = null
try
{
M�todo = classe.getMethod (�clone�, tpsParmsForms);
}
catch (NoSuchMethodException erro)
{}
// null porque chamaremos um m�todo sem par�metros
Object [] parmsReais = null;.
// assim chamamos o m�todo armazenado no objeto chamado m�todo, fazendo com que //o objeto chamado x seja para ele o objeto chamante (o this) e fazendo com que n�o //receba par�metros reais (por isso o vetor nulo chamado parmsReais � fornecido; o //resultado da chamada, que certamente � da classe X, mas que est� sendo retornado como Object , tem seu tipo mudado para X e ent�o, � atribu�do ao objeto chamado ret
X ret = null;
try
{
ret = (X) m�todo.invoke (x.parmsReais);
}
catch (InvocationTargetException erro)
{} // 
catch (IllegalAccessException erro)
 {}
// ret = (X) x.clone ();
return ret;
}
}
