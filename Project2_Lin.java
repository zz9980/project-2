//// Project 2, 2015/10/21
//// (Assume ball diameter of 30.)

//// GLOBALS:  pool table, 3 colored balls

String title=  "ELASTIC COLLISIONS";
String news=   "Use 'r' key to reset.";
String author=  "Teng Lin";


float left, right, top, bottom;
float middle;

float cueX,  cueY,  cueDX,  cueDY;
float redX,  redY,  redDX,  redDY;
float greenX,  greenY,  greenDX,  greenDY;
float blueX, blueY, blueDX, blueDY;

//// SETUP:  size and table
void setup() {
  size( 600, 400 );
  left=   50;
  right=  width-50;
  top=    100;
  bottom= height-50;
  middle= left + (right-left) / 2;
  reset();
 }
 void reset() {
   cueX=  left + (right-left) / 4;
   cueY=  top + (bottom-top) / 2;
  
   // Random positions.
   redX=   random( middle,right );   redY=   random( top, bottom );
   greenX= random( middle,right );   greenY= random( top, bottom );
   blueX=  random( middle,right );   blueY=  random( top, bottom );
   
   
   // Random speeds
   redDX=   random( 1,3 );   redDY=   random( 1,3 );
   greenDX= random( 1,3 );   greenDY= random( 1,3 );
   blueDX=  random( 1,3 );   blueDY=  random( 1,3 );
 }

//// NEXT FRAME:  table, bounce off walls, collisions, show all
void draw() {
  background( 250,250,200 );
  rectMode( CORNERS );
  table( left, top, right, bottom );
  bounce();
  collisions();
  show();
  messages();
}

//// SCENE:  draw the table with walls
void table( float left, float top, float right, float bottom ) {
  fill( 15, 250, 150 );    // green pool table
  strokeWeight(20);
  stroke( 140, 80, 10 );      // Brown walls
  rect( left-20, top-20, right+20, bottom+20 );
  stroke(0);
  strokeWeight(1);
}

//// ACTION:  bounce off walls, collisions
void bounce() {
 
  //red ball
  redX += redDX;       if ( redX < left || redX > right ) redDX *= -1;
  redY += redDY;       if ( redY < top || redY > bottom ) redDY *= -1;
  //green ball
  greenX += greenDX;   if ( greenX < left || greenX > right ) greenDX *= -1;
  greenY += greenDY;   if ( greenY < top || greenY > bottom ) greenDY *= -1;
  //blue ball
  blueX += blueDX;     if ( blueX < left || blueX > right ) blueDX *= -1;
  blueY += blueDY;     if ( blueY < top || blueY > bottom ) blueDY *= -1;
  //cue ball
  cueX += cueDX;       if ( cueX < left || cueX > right ) cueDX *= -1;
  cueY += cueDY;       if ( cueY < top || cueY > bottom ) cueDY *= -1;

}
void collisions() {
  
  float tmp;
    // Swap velocities! , bounce off each other.
    if ( dist( redX,redY, greenX,greenY ) < 30 ) {
    tmp=greenDX;  greenDX=redDX;  redDX=tmp;
    tmp=greenDY;  greenDY=redDY;  redDY=tmp;
   }
    
    if ( dist( redX,redY, blueX,blueY ) < 30 ) {
    tmp=blueDX;  blueDX=redDX;  redDX=tmp;
    tmp=blueDY;  blueDY=redDY;  redDY=tmp;
   }
    
    if ( dist( greenX,greenY, blueX,blueY ) < 30 ) {
    tmp=blueDX;  blueDX=greenDX;  greenDX=tmp;
    tmp=blueDY;  blueDY=greenDY;  greenDY=tmp;
   }
  
    if ( dist( redX,redY, cueX,cueY ) < 30 ) {
    tmp=cueDX;  cueDX=redDX;  redDX=tmp;
    tmp=cueDY;  cueDY=redDY;  redDY=tmp;
   }
  
    if ( dist( cueX,cueY, blueX,blueY ) < 30 ) {
    tmp=blueDX;  blueDX=cueDX;  cueDX=tmp;
    tmp=blueDY;  blueDY=cueDY;  cueDY=tmp;
   }
  
    if ( dist( greenX,greenY, cueX,cueY ) < 30 ) {
    tmp=cueDX;  cueDX=greenDX;  greenDX=tmp;
    tmp=cueDY;  cueDY=greenDY;  greenDY=tmp;
   }
  
  
}


//// SHOW:  balls, messages
void show() {
  //fill( 255,255,255 );    ellipse( redX,redY, 30,30 );
  
  fill( 255,0,0 );        ellipse( redX,redY, 30,30 );
  fill( 0,255,0 );      ellipse( greenX,greenY, 30,30 );
  fill( 0,0,255 );        ellipse( blueX,blueY, 30,30 );
  fill( 255,255,255 );    ellipse( cueX,cueY, 30,30 );
    
}

void mousePressed() {
  if ( mouseX >= left && mouseX <= right && mouseY >=top && mouseY <=bottom )

  fill(0);
  line( mouseX, mouseY, cueX, cueY);
  cueX = cueX+cueDX;
  //cueY += cueDY;
}


void messages() {
  fill(0);
  text( title, width/3, 20 );
  text( news, width/3, 40 );
  text( author, 10, height-10 );
}


//// HANDLERS:  keys, click
void keyPressed() {
  if (key == 'r') {
    reset();
  }
}
