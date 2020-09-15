# CrudHash-Back
Using spring : 2.3.3.RELEASE // java: 11 // swagger : 3.0.0

Disponivel em https://crudhash.herokuapp.com/swagger-ui/

Available in https://crudhash.herokuapp.com/swagger-ui/

Recebe a inserçao de uma entidade qualquer, no caso uma venda (sale), e faz o hash das informaçoes passadas e salva em uma tabela paralela afim de se conter uma integridade dos dados salvos, onde ao salvar é criado uma hash e ao editar a hash é editata.
O hash é usado para integridade onde ao consultar uma venda é trazido o resultado de sua integridade.
Possui um arquivo de log onde sao registrados insert delet e update, juntamento com datatime de tal operaçao
 
