# INF011 Editor de Texto  
*Atividade prática sobre o Abstract Factory*<br/>

Objetivo: implementar um Abstract Factory com dois produtos:<br/>
  1) SyntaxHighlighter.<br/>
  2) Builder.<br/>
  O objetivo é criar um sistema visual onde você possa abrir um arquivo .cpp ou .java.<br/>
  A depender de qual arquivo você abra ele utilizará uma fábrica que disponibiliza um syntaxhighlighter e builder apropriado para a linguagem em questão.<br/>
  
# Implementando um plugin:
  Em /src/inf011/plugin/builders: Adicionar a implementação de IBuilder com o metodo build, para compilar o arquivo escolhido na apliação.<br/> 
  Em /src/inf011/plugin/factorys: Adicionar a implementação de ILangFactory retornando um RSyntaxTextArea, com as configurações desejadas e a implementação do IBuilder.<br/>
  Em /data/Plugins.xml: Adicionar o nome da factory e extenção dentro da tag plugin.<br/>
