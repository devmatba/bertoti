
# README

We see three critical differences between programming and software engineering: **time**, **scale**, and the **trade-offs** at play.  
On a software engineering project, engineers need to be more concerned with the passage of time and the eventual need for change.  
In a software engineering organization, we need to be more concerned about scale and efficiency, both for the software we produce as well as for the organization that is producing it.  
Finally, as software engineers, we are asked to make more complex decisions with higher-stakes outcomes, often based on imprecise estimates of time and growth.

---

## O que é Engenharia de Software?

Engenharia de software, na minha opinião, é basicamente a **arte e a ciência** de criar programas de computador de forma organizada e eficiente.  
Em vez de só codar e torcer para que tudo funcione, a engenharia de software envolve **planejar, projetar, testar e manter o software**.

---

## Atividade 2

### O que são Trade Offs?

Trade-offs são escolhas que fazemos quando não dá para ter tudo ao mesmo tempo. Para ganhar algo, muitas vezes precisamos abrir mão de outra coisa. Isso acontece porque recursos, como tempo, dinheiro ou energia, são limitados. O conceito de trade-off aparece em várias situações, no trabalho, nos estudos e no dia a dia.

1. **Tempo vs. Qualidade**:  
   Quando você tem pouco tempo para fazer algo, pode precisar sacrificar a qualidade. Se quiser algo bem feito, provavelmente vai levar mais tempo.

2. **Preço vs. Qualidade**:  
   Produtos melhores normalmente são mais caros. Se você quer economizar dinheiro, talvez precise escolher algo mais simples.

3. **Conforto vs. Economia**:  
   Para gastar menos, às vezes é preciso abrir mão de conforto. Se você quer mais conforto, terá que pagar mais

---

## Atividade 3

### Criar um novo exemplo de classes e realizar testes automatizados

**Arquivo: Filme.java**  
```java
package org.example;

// Classe Filme que representa um filme no catálogo
public class Filme {

    private String titulo;
    private String diretor;
    private String genero;
    private int anoLancamento;

    // Construtor para inicializar um filme
    public Filme(String titulo, String diretor, String genero, int anoLancamento) {
        this.titulo = titulo;
        this.diretor = diretor;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
    }

    // Métodos de acesso e modificação (getters e setters)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
}
```

**Arquivo: Locadora.java**  
```java
package org.example;

import java.util.ArrayList;
import java.util.List;

// Classe Locadora que gerencia o catálogo de filmes
public class Locadora {

    private List<Filme> catalogo = new ArrayList<>();

    // Método para adicionar um filme ao catálogo
    public void adicionarFilme(Filme filme) {
        catalogo.add(filme);
    }

    // Método para buscar filmes por gênero
    public List<Filme> buscarPorGenero(String genero) {
        List<Filme> filmesEncontrados = new ArrayList<>();
        for (Filme filme : catalogo) {
            if (filme.getGenero().equalsIgnoreCase(genero)) {
                filmesEncontrados.add(filme);
            }
        }
        return filmesEncontrados;
    }

    // Método para buscar filmes por diretor
    public List<Filme> buscarPorDiretor(String diretor) {
        List<Filme> filmesEncontrados = new ArrayList<>();
        for (Filme filme : catalogo) {
            if (filme.getDiretor().equalsIgnoreCase(diretor)) {
                filmesEncontrados.add(filme);
            }
        }
        return filmesEncontrados;
    }

    // Método que retorna todos os filmes do catálogo
    public List<Filme> getCatalogo() {
        return catalogo;
    }
}
```

**Arquivo: LocadoraTest.java**  
```java
package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Classe de teste para a Locadora
class LocadoraTest {

    @Test
    void testLocadora() {
        // Instância da locadora
        Locadora locadora = new Locadora();

        // Criando filmes
        Filme filme1 = new Filme("Inception", "Christopher Nolan", "Ficção Científica", 2010);
        Filme filme2 = new Filme("Interstellar", "Christopher Nolan", "Ficção Científica", 2014);
        Filme filme3 = new Filme("Dunkirk", "Christopher Nolan", "Guerra", 2017);
        Filme filme4 = new Filme("Titanic", "James Cameron", "Romance", 1997);

        // Adicionando filmes ao catálogo
        locadora.adicionarFilme(filme1);
        locadora.adicionarFilme(filme2);
        locadora.adicionarFilme(filme3);
        locadora.adicionarFilme(filme4);

        // Verificando o número total de filmes no catálogo
        assertEquals(4, locadora.getCatalogo().size());

        // Buscando filmes pelo gênero "Ficção Científica"
        List<Filme> filmesFiccao = locadora.buscarPorGenero("Ficção Científica");
        assertEquals(2, filmesFiccao.size());
        assertEquals("Inception", filmesFiccao.get(0).getTitulo());

        // Buscando filmes pelo diretor "Christopher Nolan"
        List<Filme> filmesNolan = locadora.buscarPorDiretor("Christopher Nolan");
        assertEquals(3, filmesNolan.size());
        assertEquals("Dunkirk", filmesNolan.get(2).getTitulo());
    }
}
```
