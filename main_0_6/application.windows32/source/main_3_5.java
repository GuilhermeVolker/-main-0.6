import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class main_3_5 extends PApplet {

altimetro altimetro;
bussola bussola;
variometro variometro;
turn turn;
horizonte horizonte;
grafico grafico;


PImage fundoAltimetro,indicadorMaior,indicadorMenor;
PImage fundoBussola, ponteiroBussola;
PImage fundoVariometro,ponteiroVariometro;
PImage Turn_coordinator,Aviaozinho,bolinha;
PImage horizonteartificial, imgFundo, marcacao;

//recebe data

String[] str;
float [] ano        = new float  [100000];
float [] mes        = new float  [100000];
float [] dia        = new float  [100000];
float [] hora       = new float  [100000];
float [] minuto     = new float  [100000];
float [] segundo    = new float  [100000];
float [] pressao    = new float  [100000];
float [] alt        = new float  [100000];
float [] pitch      = new float  [100000];
float [] roll       = new float  [100000];
float [] curso      = new float  [100000];
float [] velocidade = new float  [100000];
float [] alt2       = new float  [100000];  //altitude gps em Metros
double[] lat        = new double [100000];
double[] lng        = new double [100000];
int   [] sat        = new int    [100000];
int size = 350;
//int fps = 10;      //ajusta os quadros por segundo!

//tamanho da tela
//int width = 1200;
//int height = 700;



  public void setup(){
  //fullScreen();
  
  surface.setResizable(true);
  background(0);
  
  frameRate(30);      //ajustar para a velocidade de gravação do arduino
  rectMode(CENTER);
  
  //altimetro
  fundoAltimetro = loadImage("imagens/fundoAltimetro.png");
  indicadorMaior = loadImage("imagens/indicadorMaior.png");
  indicadorMenor= loadImage("imagens/indicadorMenor.png");
  //bussola
  fundoBussola = loadImage("imagens/fundoBussola.png");
  ponteiroBussola = loadImage("imagens/ponteiroBussola.png");
  //variometro
  fundoVariometro = loadImage("imagens/fundoVariometro.png");
  ponteiroVariometro = loadImage("imagens/ponteiroVariometro.png");
  //turn_coordinator
  Turn_coordinator = loadImage("imagens/Turn_coordinator.png");
  Aviaozinho = loadImage("imagens/Aviaozinho.png");
  bolinha = loadImage("imagens/bolinha.png");
  //horizonte
  horizonteartificial = loadImage("imagens/horizonteartificial.png");
  marcacao = loadImage("imagens/marcacao.png");
  
  //inicializa as classes, posteriormente passa os valores respectivos para cada classe!
  
  /////////
altimetro = new altimetro();
bussola = new bussola();
variometro = new variometro();
turn = new turn();
horizonte = new horizonte();
grafico = new grafico();
  /////////
  
//==================================
  str = loadStrings("data/FDR.TXT");
  
  String[] arry = new String[str.length];

  for (int i=0; i < arry.length; i++) {
    String[] thisLine = split(str[i] , " " );
    
    ano        [i]   = PApplet.parseFloat (trim(thisLine[0] ));
    mes        [i]   = PApplet.parseFloat (trim(thisLine[1] ));
    dia        [i]   = PApplet.parseFloat (trim(thisLine[2] ));
    hora       [i]   = PApplet.parseFloat (trim(thisLine[3] ));
    minuto     [i]   = PApplet.parseFloat (trim(thisLine[4] ));
    segundo    [i]   = PApplet.parseFloat (trim(thisLine[5] ));
    pressao    [i]   = PApplet.parseFloat (trim(thisLine[6] ));
    alt        [i]   = PApplet.parseFloat (trim(thisLine[7] ));
    pitch      [i]   = PApplet.parseFloat (trim(thisLine[8] ));
    roll       [i]   = PApplet.parseFloat (trim(thisLine[9] ));
    curso      [i]   = PApplet.parseFloat (trim(thisLine[10]));
    velocidade [i]   = PApplet.parseFloat (trim(thisLine[11]));
    alt2       [i]   = PApplet.parseFloat (trim(thisLine[12]));
    lat        [i]   = PApplet.parseFloat (trim(thisLine[13]));
    lng        [i]   = PApplet.parseFloat (trim(thisLine[14]));
    sat        [i]   = PApplet.parseInt   (trim(thisLine[15]));
  }
}

//=====
int p = 0;  //variavel para selecionar linha do arquivo
//=====

public void draw(){
  //println(frameRate);
  //for(int cont = 0;cont<str.length;cont++){
  //cada instrumento possui seu proprio centro de rotação!
  //int cont = 1000;  // pega dados da linha 3000
  
  translate(0,0,-1);
    //função do horizonte
  horizonte.math(pitch[p],roll[p]);
  horizonte.rotacao(roll[p]);
  horizonte.fundo();
  horizonte.marcacao(marcacao, pitch[p]);
  horizonte.img(horizonteartificial);
  horizonte.translate2();
  translate(0,0,1);

  //funções do altimetro
  altimetro.translado();
  altimetro.math(alt[p]);
  altimetro.fundo(fundoAltimetro);
  altimetro.indicador2(indicadorMaior);
  altimetro.indicador1(indicadorMenor);
  texto(dia[p], mes[p], ano[p], hora[p],minuto[p], segundo[p], alt[p],pressao[p], pitch[p], roll[p],curso[p],velocidade[p],sat[p]);              //imprime as informações ao lado do altimetro
  altimetro.translado2();
  
  //funções da bussola
  bussola.translado();
  bussola.math(curso[p]);
  bussola.indicador1(fundoBussola);
  bussola.indicador2(ponteiroBussola);
  bussola.translado2();
  
  //funções do turn_coordinator
  turn.translado();
  turn.fundo(Turn_coordinator);
  turn.aviao(Aviaozinho,roll[p]);
  turn.bolinha(bolinha, roll[p]);
  turn.translado2();
  
  //funções do variometro
  variometro.translado();
  variometro.math(alt[p]);
  variometro.fundoVariometro(fundoVariometro);
  variometro.indicador(ponteiroVariometro);
  //grafico.graf();
  variometro.translado2();
  
  p++;  //contador da variavel

}
public void texto(float dia, float mes, float ano, float hora, float minuto, float segundo, float alt, float pressao,float pitch,float roll, float curso, float velocidade, int sat){
   //imprime dados:
  //translate(4*size+280,300);
 textSize(14);
 fill(255);
 text("dia/mes/ano: ",180,-120);
 text(+PApplet.parseInt(dia)+"/"+PApplet.parseInt(mes)+"/"+PApplet.parseInt(ano),180,-100);
 text("h/min/seg: (ZULU)",180,-80);
 text(PApplet.parseInt(hora)+":"+PApplet.parseInt(minuto)+":"+PApplet.parseInt(segundo),180,-60);
 text("ALT: "+alt,180,-40);
 text("QNH: "+pressao,180,-20);
 text("PITCH: "+pitch,180,0);
 text("ROLL: "+roll,180,20);
 text("Curso: "+curso,180,40 );
 text("Velocidade: "+velocidade,180,60);
 text("Nº de sat: "+ sat,180,80);
 text("FPS: "+PApplet.parseInt(frameRate),180,120);
}
class altimetro{

float dist1,dist2;
float alt, pressao;
//float[] vals;
int size = height/2; //standart

public void translado(){
    translate((3*size)-(size/2),size/2);      //1 linha, 3 coluna
}

//================================================
public void math(float alt){
   dist1 = map(alt,0,12000,0,24*PI);
   dist2 = map(alt,0,12000,0,12*0.6283f);  //até 12mil pés
  }  


//================================================
public void fundo(PImage img1){
  image(img1,-size/2,-size/2,size,size);
}


//================================================
public void indicador2(PImage img3){
 rotate(dist1);
 image(img3,-size/2,-size/2,size,size); 
 
}

//================================================
public void indicador1(PImage img2){
  rotate(-dist1);
  rotate(dist2);
  image(img2,-size/2,-size/2,size,size);
  rotate(-dist2);
  
  
}
public void translado2(){
  translate(-((3*size)-(size/2)),-size/2);  
}
}
class bussola{

float rot; 
int size = height/2; //standart

public void translado(){
  translate(size+size/2,size+size/2);    //2 linha, 2 coluna
}
  
public void math(float heading){
  //float a = dist(0,mouseY,mouseX,mouseY);
  rot = map(heading,0,359,20,380);      //recebe curso de 0 a 360 / traduz em direção de -20 a 349 graus
  //rot = rot + (-20.0);                // deriva magnetica aproximada para SC/PR
  //println(rot);
   
}
public void indicador1(PImage img1){              //fundo com rotação
  rotate(radians(rot));
  image(img1,-size/2,-size/2,size,size);
  rotate(radians(-rot));
}

public void indicador2(PImage img2){
  image(img2,-size/2,-size/2,size,size);
}


public void translado2(){
  translate(-(size+size/2),-(size+size/2));
  
}
}
int cont = 0;
int print=0;


class grafico{
int size = 350;

public void graf(){
    
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

public void mouseClicked(){
  cont += 1;
}


int x=0;
public void graph(float alt){
  float p = map(alt,0,200,0,3500);
  point(x,width/2+p);
  x ++;
}
  
}
class horizonte{
float dist;
float a,b,c,d,e,f,g,h;
int size = height/2;
float x,y,x1,y1;
float mov, p, rot;

//============================================================
public void math(float pitch,float roll){
 
         x = map(pitch,-900,900,0,height);
         y = map(roll,-900,900,0,height);  
         x1 = map(pitch,-900,900,-PI,PI);
         y1 = map(roll,-900,900,-PI,PI);
}    
//============================================================

public void fundo(){
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
    fill(0xff834022);
    arc(0,0,size,size,0,PI);                //chao
    rect(0,-b/2,size,b);
    fill(0xff2D20F5);
    arc(0,-b,size,size,PI,2*PI);          //ceu
  } 
  
  //ceu
  if(a>height/2){
    strokeWeight(0);
    fill(0xff834022);
    arc(0,0,size,size,0,PI);          //chao  - arc(0,0+b,size,size,0,PI); 
    fill(0xff2D20F5);
    arc(0,0,size,size,PI,2*PI);       //ceu
    rect(0,b/2,size,b);
  }
  rotate(-rot);
  strokeWeight(0);
}
//=======================================================

public void img(PImage img){
 image(img, -size/2,-size/2, 350,350); 
}

//=======================================================

public void rotacao(float roll){ 
   rot = map(roll, -90, 90, -PI/2, PI/2);
}

//=============================================
public void marcacao(PImage marca, float pitch){
  
  mov = map(pitch,-90,90,0,size);      //data input
  image(marca,-size/2,-size  + mov, size, 350);
  
  stroke(0);
  strokeWeight(100);
  noFill();
 arc(0,0,size+100,size+100,0,2*PI);      //arco externo preto
}

//=================================================
public void translate2(){
 translate(-(size+size/2),-(size/2)); 
  }
}
class turn{
    
float rot;
int size = height/2; //standart

public void translado(){
  translate(size/2,size+size/2);    //2 linha, 1 coluna
}
  
public void fundo(PImage img1){
  image(img1,-size/2,-size/2,size,size);
}

public void aviao(PImage img2, float roll){
  
  float rot = map(roll,-90,90,-PI/2,PI/2);    //de -90º até +90º
  
  rotate(rot);
  image(img2,-size/2,-size/2,size,size);
  rotate(-rot);
}

public void bolinha(PImage img3,float roll){
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

public void translado2(){
  translate(-(size/2),-(size+size/2));
  //translate(size+size/4,-size/3);
}

}
class variometro{
float dist, calc;
int size = height/2; //standart


//======================================================================
public void translado(){
  translate(2*size+size/2,size+size/2);    //2 linha, 3 coluna
}

//======================================================================
public void math(float alt){
  //recebe a altitude atual, compara com a altitude anterior e calcula a variação em ft/min
  
 float a = dist(alt,0,calc,0);    //diferença entre a alt antiga com a nova
 dist = map(a,-2000,2000,PI,-PI); //variação de até 2000ft/min para mais e menos
  
 //após calcular a variação, salva o dado antigo na variavel
 calc = alt;                            
}

//======================================================================
public void fundoVariometro(PImage img1){
 image(img1,-size/2,-size/2,size,size);
}

//======================================================================
public void indicador(PImage img2){
 rotate(dist);
 image(img2,-size/2,-size/2,size,size);
 rotate(-dist);
}


//======================================================================
public void translado2(){
  translate(-(2*size+size/2),-(size+size/2));
  
}  }
  public void settings() {  size(1200,700,P3D);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "main_3_5" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
