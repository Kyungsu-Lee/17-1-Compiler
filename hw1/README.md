<h1>Build Script</h1>

  <i>you must install ant and use it.</i>
  
  <strong>component of ant</strong>
  
  * init  : set up
  * clean : clean all build files
  * build : compile all java files
  * [jar]   : make jar file   
  * run   : run this program
  
   <strong>ant run</strong>
   >
   > You can run this program with "ant run".
   > Running this, you can see the comment, "Enter Enter regular Expression arguments:".
   > This argument is "args" of Main Program, and you can put regular expression in here.
   > After that, the program will run.
   
<br><br>
   
<h1>Run Program</h1>


<strong>how to start</strong>
   
> In "jar" directory, there is compiler.jar. you can run the program with,
>  <code>java -jar compiler.jar 'regex'</code>
>  . you must write '' in Linux, for os to recognize regex 
><br>
><br>
> example : <code>java -jar compiler.jar '[123]'</code>
><br>
><br>
><br>
><br>
>With ant, you can run the program. <code>ant run</code>Running this, you can see the comment, 
>"Enter Enter regular Expression arguments:".
>This argument is "args" of Main Program, and you can put regular expression in here.
><br>
><br>
> example : <br>
><code>ant run</code><br>
><code>Enter Enter regular Expression arguments:</code><br>
><code>[123]</code>
><br>
><br>
><br>
><br>

<strong>how to run in program</strong>
><br>
>In a program, you can enter the string and the program checks wheather the string is matched to regular expression which you put with args. If the string is matched to pattern, the program says "accpeted", and "rejected" when not.
><br>
><br>
> example : <br>
><pre><code>ant run
>Enter Enter regular Expression arguments:
>[123]
>1         //by user
>accepted
>123       //by user
>rejected
>2         //by user
>accepted
></code></pre>
