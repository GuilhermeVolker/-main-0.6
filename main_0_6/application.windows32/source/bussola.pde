class bussola{

float rot; 
int size = height/2; //standart

void translado(){
  translate(size+size/2,size+size/2);    //2 linha, 2 coluna
}
  
void math(float heading){
  //float a = dist(0,mouseY,mouseX,mouseY);
  rot = map(heading,0,359,20,380);      //recebe curso de 0 a 360 / traduz em direção de -20 a 349 graus
  //rot = rot + (-20.0);                // deriva magnetica aproximada para SC/PR
  //println(rot);
   
}
void indicador1(PImage img1){              //fundo com rotação
  rotate(radians(rot));
  image(img1,-size/2,-size/2,size,size);
  rotate(radians(-rot));
}

void indicador2(PImage img2){
  image(img2,-size/2,-size/2,size,size);
}


void translado2(){
  translate(-(size+size/2),-(size+size/2));
  
}
}
