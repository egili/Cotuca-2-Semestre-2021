public class Conjunto<X>
{
private X [] elem;
private int qtd= 0;
public Conjunto (int capInicial) throws Exception
{
if  (capInicial <=0)
throw new Exception (“Capacidade invalida”);
//this.elem = new X [capInicial];
this.elem = (X []) new Object [capInicial];
}
public Conjunto ()
{
//this.elem = new X [1];
this.elem (X[]) new Object [10];
}
private void redimensioneSe (int novaCap)
    {
      //X     [] novo = new X      [novaCap];
        Object[] novo = new Object [novaCap];

        for (int i=0; i<this.qtd; i++)
            novo[i] = this.elem[i];

        this.elem = novo;
    }
// retorna a posição em this.elem onde está x,ou retorna -1 quando não encontrar x em this.elem.
private int ondeEsta (X x)
{
for (int i=0; i<this.qtd; i++)
if (x.equals (this.elem [i]))
return -1;
}
public boolean tem (X x) throws Exception
{
if (x==null)
throw new Exception (“Elemento ausente”);
return this.ondeEsta (x) != -1;
}
public void inclua (X x) throws Exception
{
if (x==null)
throw new Exception (“Elemento ausente”);

if (this.tem (x))
throw new Exception (“Elemento repetido”);
if (this.qtd == this.elem.length)
this.redimensioneSe (2*this.elem.length);
if (x instanceof Cloneable)
this.elem [this.qtd] = (X) x.clone (); //erro de compilação
else 
this.elem [this.qtd] = x;
this.qtd ++;
 }
public X getElemento (int i) throws Exception
{
if (i<0 |  | i>=this.qtd)
throw new Exception (“Elemento inválido”);

if (this.elem[i] instanceof Cloneable)
return (X) this.elem[i].clone (); //erro de compilação
else
return this.elem[i];
}
Public Conjunto <X> união (Conjunto <X> conj) throws Exception
{
If (conj == null)
throw new Exception  (“Conjunto ausente”);
Conjunto <X> ret = new Conjunto <X> (this.qtd + conj.qtd);
Conjunto <X> menor, maior;
if  (this.qtd < conj.qtd)
{
menor = this;
maior = conj;
}
else 
{
menor = conj;
maior = this;
}
ret.qtd = maior.qtd;
for (int i = 0; i<maior.qtd; i++)
ret.elem[i] = maior.elem [i];

for  (int i=0, i<menor.qtd; i++)
{
try
{
ret.inclua (menor.elem [i]);
}
Catch (Exception erro)
{} // ignoro repetidos e toco em frente
}
return ret;
}

public Conjunto <X> intersecção (Conjunto <X> conj) throws Exception
{
if (conj ==null)
throw new Exception (“Conjunto ausente”);

Conjunto <X> menor, maior;
Conjunto <X> menor, maior;
if (this.qtd<conj.qtd)
{
menor = this;
maior = conj;
} // aqui é  33min41s
else
{
menor = conj;
maior = this;
}
Conjunto <X> ret = new Conjunto <X> (menor.qtd); 
{
boolean tem = false;
try 
{
tem = maior.tem (menor.elem[i]);
}
catch (Exception erro)
{} // sei que no conjunto menor não há null
if (tem)
{
ret.elem [ret.qtd] = menor.elem[i];
ret.elem [ret.qtd] = menor.elem[i];
ret.qtd++;
}
}
return ret;
}
public Conjunto <X> menos (Conjunto <X> conj) throws Exception
{
Conjunto <X> ret = new Conjunto <X> (this.qtd);
for (int i = 0; i<this.qtd; i++)
if (!conj.tem (this.elem[i]))
{
ret.elem[ret.qtd]= this.elem[i];
ret.qtd++;
} 
return ret;
}

public boolean contem (Conjunto <X> conj) throws Exception
{
if (conj==null)
throw new Exception (“Conjunto ausente”);

for (int i=0; i<this.qtd; i++)
if (this.ondeEsta(conj.elem[i] == -1)
return false;

return  true;
}

public boolean estaContido (Conjunto <X> conj) throws Exception
{
if (conj==null)
throw new Exception (“Conjunto ausente”);

return conj.contem (this).
}

@Override
public String toString ()
{
 String ret = “{“ ;
If (this.qtd>0)
ret += this.elem [0];

for (int i =1; i<this.qtd; i++)
for (int i =1; i<this.qtd; i++)
ret += “,” +this.elem[i];

ret += “}”; 
return ret;
}

@Override
@Override
public boolean equals (Object obj)
{
if (this==obj) return true;
if (obj==null) return false;
if (this.getClass () != obj.getClass () ) return false;
Conjunto <X> conj = (Conjunto <X>) obj;
if (this.qtd != conj.qtd) return false;
// leva em conta a ordem;
// for (int i=0; i<this.qtd; i++)
// if (!this.elem[i].equals (conj.elem[i]))
// return false;

//sem levar em conta a ordem
for (int i =0; i<this.qtd; i++)
if (this.ondeEsta (conj.elem[i] == -1)
return false;

return true;
}

@Override
public int hashCode ()
{
int ret =1;
ret = 13*ret + new Integer (this.qtd).hashCode ();
for (int i=0; i<this.qtd; i++)
//if  (this.elem[i] !=null)
ret = 7*ret +this.elem[i].hashCode ();
if (ret<0) ret=-ret;

return ret;
}
}
