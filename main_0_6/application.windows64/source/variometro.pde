class variometro{
float dist, calc;
int size = height/2; //standart


//======================================================================
void translado(){
  translate(2*size+size/2,size+size/2);    //2 linha, 3 coluna
}

//======================================================================
void math(float alt){
  //recebe a altitude atual, compara com a altitude anterior e calcula a variação em ft/min
  
 float a = dist(alt,0,calc,0);    //diferença entre a alt antiga com a nova
 dist = map(a,-2000,2000,PI,-PI); //variação de até 2000ft/min para mais e menos
  
 //após calcular a variação, salva o dado antigo na variavel
 calc = alt;                            
}

//======================================================================
void fundoVariometro(PImage img1){
 image(img1,-size/2,-size/2,size,size);
}

//======================================================================
void indicador(PImage img2){
 rotate(dist);
 image(img2,-size/2,-size/2,size,size);
 rotate(-dist);
}


//======================================================================
void translado2(){
  translate(-(2*size+size/2),-(size+size/2));
  
}  }
