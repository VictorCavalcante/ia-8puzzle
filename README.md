
## IA-8puzzle (IC-IA)

Esse projeto constitui numa simples implementação da solução do problema do jogo dos 8. 
Trata-se de um quebra-cabeças de 8 peças, com os números de 1 a 8 que trocam de lugar. O objetivo é arranjar as números em ordem, da esquerda para a direita, de cima a baixo.

## Detalhes

###### Implementação
Existem 3 implementações do algorítmo de busca, sendo eles:

* BreadthFirstSearch
* DepthFirstSearch
* IterativeDeepening

###### Input e Output
A entrada pode ser definida como os estados iniciais:

* INITIAL_STATE = "281043765";
* INITIAL_STATE = "567408321"; // Worst case (reversed)

O objetivo é que encontremos o estado: **"123804765"**

###### Resultados
Ao final da execução é possível ver:

* Número de transições do estado inicial ao objetivo
* Numero de estados visitados
* Custo da solução
* Número de nós removidos da fila
* Tempo de execução total

## Executando o projeto

O projeto é estruturado de forma simples sendo necessário apenas executar o módulo principal `App` contido no arquivo`App.java`.
As diferentes implementações se encontram neste arquivo, sendo possível alternar entre tipos de busca descomentando a chamada ao algoritmo desejado;

## Exemplo de execução projeto
```
>>> Breadth First Search

========================
> Movement Cost: 0
> Result:
-------
| 281 |
| 043 |
| 765 |
-------
========================
> MOVE '2' DOWN
> Movement Cost: 2
> Result:
-------
| 081 |
| 243 |
| 765 |
-------
========================
> MOVE '8' LEFT
> Movement Cost: 8
> Result:
-------
| 801 |
| 243 |
| 765 |
-------
========================
> MOVE '1' LEFT
> Movement Cost: 1
> Result:
-------
| 810 |
| 243 |
| 765 |
-------
========================
> MOVE '3' UP
> Movement Cost: 3
> Result:
-------
| 813 |
| 240 |
| 765 |
-------
========================
> MOVE '4' RIGHT
> Movement Cost: 4
> Result:
-------
| 813 |
| 204 |
| 765 |
-------
========================
> MOVE '2' RIGHT
> Movement Cost: 2
> Result:
-------
| 813 |
| 024 |
| 765 |
-------
========================
> MOVE '8' DOWN
> Movement Cost: 8
> Result:
-------
| 013 |
| 824 |
| 765 |
-------
========================
> MOVE '1' LEFT
> Movement Cost: 1
> Result:
-------
| 103 |
| 824 |
| 765 |
-------
========================
> MOVE '2' UP
> Movement Cost: 2
> Result:
-------
| 123 |
| 804 |
| 765 |
-------

================================================ RESULTS
> 9	Transitions from initial state to goal
> 499	Visited states
> 31	Solution cost
> 295	Nodes poped from the queue: 

[Elapsed time: 9 milliseconds]
========================================================
```