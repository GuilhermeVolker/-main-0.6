class altimetro{

float dist1,dist2;
float alt, pressao;
//float[] vals;
int size = height/2; //standart

void translado(){
    translate((3*size)-(size/2),size/2);      //1 linha, 3 coluna
}

//================================================
void math(float alt){
   dist1 = map(alt,0,12000,0,24*PI);
   dist2 = map(alt,0,12000,0,12*0.6283);  //até 12mil pés
  }  


//================================================
void fundo(PImage img1){
  image(img1,-size/2,-size/2,size,size);
}


//================================================
void indicador2(PImage img3){
 rotate(dist1);
 image(img3,-size/2,-size/2,size,size); 
 
}

//================================================
void indicador1(PImage img2){
  rotate(-dist1);
  rotate(dist2);
  image(img2,-size/2,-size/2,size,size);
  rotate(-dist2);
  
  
}
void translado2(){
  translate(-((3*size)-(size/2)),-size/2);  
}
}
