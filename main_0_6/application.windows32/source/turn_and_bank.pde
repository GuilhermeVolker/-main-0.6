class turn{
    
float rot;
int size = height/2; //standart

void translado(){
  translate(size/2,size+size/2);    //2 linha, 1 coluna
}
  
void fundo(PImage img1){
  image(img1,-size/2,-size/2,size,size);
}

void aviao(PImage img2, float roll){
  
  float rot = map(roll,-90,90,-PI/2,PI/2);    //de -90º até +90º
  
  rotate(rot);
  image(img2,-size/2,-size/2,size,size);
  rotate(-rot);
}

void bolinha(PImage img3,float roll){
  translate(-width/2,-height/2);    //translate to the original position

  float deg1 = -10;
  float deg2 = 10;
  float rad1 = radians(deg1);
  float rad2 = radians(deg2);
  
  float mov = map(roll, -90,90, rad2, rad1);         //lateral moviment
  
  translate(width/2,0);
  rotate(mov/2);
  image(img3,-size/2,175,size,size);
  rotate(-mov/2);
  translate(0,height/2);
  
}

void translado2(){
  translate(-(size/2),-(size+size/2));
  //translate(size+size/4,-size/3);
}

}
