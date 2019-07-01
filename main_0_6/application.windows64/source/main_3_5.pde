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



  void setup(){
  //fullScreen();
  size(1200,700,P3D);
  surface.setResizable(true);
  background(0);
  smooth();
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
    
    ano        [i]   = float (trim(thisLine[0] ));
    mes        [i]   = float (trim(thisLine[1] ));
    dia        [i]   = float (trim(thisLine[2] ));
    hora       [i]   = float (trim(thisLine[3] ));
    minuto     [i]   = float (trim(thisLine[4] ));
    segundo    [i]   = float (trim(thisLine[5] ));
    pressao    [i]   = float (trim(thisLine[6] ));
    alt        [i]   = float (trim(thisLine[7] ));
    pitch      [i]   = float (trim(thisLine[8] ));
    roll       [i]   = float (trim(thisLine[9] ));
    curso      [i]   = float (trim(thisLine[10]));
    velocidade [i]   = float (trim(thisLine[11]));
    alt2       [i]   = float (trim(thisLine[12]));
    lat        [i]   = float (trim(thisLine[13]));
    lng        [i]   = float (trim(thisLine[14]));
    sat        [i]   = int   (trim(thisLine[15]));
  }
}

//=====
int p = 0;  //variavel para selecionar linha do arquivo
//=====

void draw(){
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
void texto(float dia, float mes, float ano, float hora, float minuto, float segundo, float alt, float pressao,float pitch,float roll, float curso, float velocidade, int sat){
   //imprime dados:
  //translate(4*size+280,300);
 textSize(14);
 fill(255);
 text("dia/mes/ano: ",180,-120);
 text(+int(dia)+"/"+int(mes)+"/"+int(ano),180,-100);
 text("h/min/seg: (ZULU)",180,-80);
 text(int(hora)+":"+int(minuto)+":"+int(segundo),180,-60);
 text("ALT: "+alt,180,-40);
 text("QNH: "+pressao,180,-20);
 text("PITCH: "+pitch,180,0);
 text("ROLL: "+roll,180,20);
 text("Curso: "+curso,180,40 );
 text("Velocidade: "+velocidade,180,60);
 text("Nº de sat: "+ sat,180,80);
 text("FPS: "+int(frameRate),180,120);
}
