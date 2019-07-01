class horizonte{
float dist;
float a,b,c,d,e,f,g,h;
int size = height/2;
float x,y,x1,y1;
float mov, rot;

//============================================================
void math(float pitch,float roll){
 
         x = map(pitch,-90,90,0,height);
         y = map(roll,-90,90,0,height);  
         x1 = map(pitch,-90,90,-PI/2,PI/2);
         y1 = map(roll,-90,90,-PI/2,PI/2);
}    
//============================================================

void fundo(){
    background(0);
    translate(size+size/2,size/2);
  //calcula as distancias para referenciar no programa
  //distancias no eixo Y
  a = dist(x, 0, x, y);                //distancia em relação ao topo
  b = dist(0, height/2, 0, y);         //distancia em relação a metade da tela
  c= dist(x, height, x, y);            //distancia em relação ao botton
  d= dist(x, height-height/4, x, y);   //distancia em relação ao quarto inferior da tela
  e = dist(x, height/4, x, y);         //distancia em relação ao quarto superior da tela
 
  //distancias no eixo X
  g = dist(0,y,x,y);                   //distancia em relação ao canto esquerdo
  f = dist(width,y,x,y);               //distancia em relação ao canto direito


           
  
  rotate(rot);
  
  //chao
  if(a<=height/2){
    strokeWeight(0);
    fill(#834022);
    arc(0,0,size,size,0,PI);                //chao
    rect(0,-b/2,size,b);
    fill(#2D20F5);
    arc(0,-b,size,size,PI,2*PI);          //ceu
  } 
  
  //ceu
  if(a>height/2){
    strokeWeight(0);
    fill(#834022);
    arc(0,0,size,size,0,PI);          //chao  - arc(0,0+b,size,size,0,PI); 
    fill(#2D20F5);
    arc(0,0,size,size,PI,2*PI);       //ceu
    rect(0,b/2,size,b);
  }
  rotate(-rot);
  strokeWeight(0);
}
//=======================================================

void img(PImage img){
 image(img, -size/2,-size/2, 350,350); 
}

//=======================================================

void rotacao(float roll){ 
   rot = map(roll, -90, 90, -PI/2, PI/2);
}

//=============================================
void marcacao(PImage marca, float pitch){
  
  rotate(rot);
  mov = map(pitch,-90,90,0,size);      //data input
  image(marca,-size/2,-size  + mov, size, 350);
  rotate(-rot);
  
  stroke(255);
  strokeWeight(100);
  noFill();
  arc(0,0,size+100,size+100,0,2*PI);      //arco externo preto
}

//=================================================
void translate2(){
 translate(-(size+size/2),-(size/2)); 
  }
}
