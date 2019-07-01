int cont = 0;
int print=0;


class grafico{
int size = 350;

void graf(){
    
  //translate(3*350,300);
  fill(255);
  stroke(255);
  //rect(220,0,40,40);
  
  textSize(14);
  fill(255);
  text(" Gráficos ",195,4);  //size/2+20
  
  //float distX = dist(1125,mouseY,mouseX,mouseY);
  //float distY = dist(mouseX,300,mouseX,mouseY);
  
  float distX = dist(1100,mouseY,mouseX,mouseY);
  float distY = dist(mouseX,525,mouseX,mouseY);
  
  if(distX <= 40 && distY <= 20){       //se o mouse esta dentro da figura
  if((cont % 2) == 1){  
   translate(-(2*size+size/2),-(size+size/2));           //se mouse foi clicado
   fill(255);
   rect(0,0,1000,600);
   graph(2000);
   print = 1;
   }
   
   if((cont % 2) == 0){ 
     print =0;
   }
   
   }
   if(print == 1){
   translate(-(2*size+size/2),-(size+size/2)); 
   fill(255);
   //rect(width/2-50,height/2,1000,600);
   rect(0,0,1000,600);
   graph(2000);
    }
    
      }

void mouseClicked(){
  cont += 1;
}


int x=0;
void graph(float alt){
  float p = map(alt,0,200,0,3500);
  point(x,width/2+p);
  x ++;
}
  
}
