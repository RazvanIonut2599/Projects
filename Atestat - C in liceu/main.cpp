#include <iostream>
#include <cstdlib>
#include <windows.h>
#include <conio.h>
#include <fstream>
#include<time.h>
using namespace std;



                //GOTOXY muta cursorul pentru suprascriere.
                void gotoxy(int column, int line)
{
    COORD coord;
    coord.X = column;
    coord.Y = line;
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}


                //SHOW_CURSOR face cursorul sa dispara.
                void show_cursor(bool show){ //vizibilitate curosor in consola
    HANDLE out = GetStdHandle(STD_OUTPUT_HANDLE);
    CONSOLE_CURSOR_INFO infCursor;
    GetConsoleCursorInfo(out, &infCursor);
    infCursor.bVisible = show;
    SetConsoleCursorInfo(out, &infCursor);
}


                //Matricea Plan are rolul de a contine tabla de joc, variabila Wall reprezinta linia pe care se afla zidul, variabila K este un contor pentru zid
                //Variabilele Player si Playerline sunt coordonatele jucatorului (X=Player si Y=Playerline);
                //Variabilele Gameover si scor au rolul de a opri jocul si de a tine scorul;

int Plan[50][50],Wall=0,K=0;
int Player=4,Playerline=7;
int Gameover=1;
int scor=0;



                //Functia afisare are rolul de a scrie matricea Plan ;
                void afisare(int v[50][50])
    {
        //3= jucator 2=zid lateral 1=zid in joc
        int i,j;
for(i=0;i<10;i++){cout<<"                  ";
for(j=0;j<10;j++)
if(v[i][j]==3){cout<<"{"<<(char)286<<"_"<<(char)286<<"}";}
else
if(v[i][j]==2){cout<<(char)178<<' '<<(char)178<<' '<<(char)178;}
else
if(v[i][j]==1)
{cout<<(char)220<<(char)220<<(char)220<<(char)220<<(char)220;}
        else  {cout<<"     " ;}
        cout<<endl;
             cout<<"                  ";
                for(j=0;j<10;j++)
        if(v[i][j]==3)
            {cout<<'/'<<(char)16<<(char)4<<(char)17<<(char)92 ;}
        else
        if(v[i][j]==2)
            {cout<<' '<<(char)178<<' '<<(char)178<<' ';}
        else if(v[i][j]==1)
{cout<<(char)203<<"   "<<(char)203;}
        else  {cout<<"     ";}
cout<<endl;
}
                }

//Functia start folosita la pornire pregateste matricea Plan , cu 0 initial sau spatiu, cu 3 daca i-ul este pe linia Jucatorului si cu 2 zidurile laterale;
        void start()
        {
              int i,j;
    for (i=0;i<10;i++)
        for(j=0;j<10;j++)
        {Plan[i][j]=0; if(i==Playerline)Plan[i][Player]=3;
        if(j==0||j==9) Plan[i][j]=2;}
        }

//Functia lineclear completeaza o linie cu '0' dupa interschimbarea pozitiei peretilor in matricea Plan;
void lineclear(int x)
{int i; for(i=1;i<9;i++)Plan[x][i]=0;}


//Functia safety are rolul de a creea spatii libere in cazul in care nu exista vreunul intre 3 puncte diferite ale unei linii;
        void safety()
    {
       int i;
        for(i=2;i<10;i++)if(Plan[0][i]==1&&Plan[0][i--]==1&&Plan[0][i++]==1)
            Plan[0][i]=0;
    }

//Functia random are rolul de a creea pe linia 0 peretele care va fi mutat ulterior si de a asigura ca peretele are cel putin 4 unitati;
        void random()
{srand(time(NULL));
    int i;
for(i=1;i<9;i++) Plan[0][i]=rand()%2;
safety();
}


//Functia mutare se foloseste de interschimbare pentru a muta peretii
//In cazul in care peretele ajunge la ultima linie a matricei resetam existenta unui perete in Plan , modificam pozitia peretelui in Plan si curatam ultima linie cu ajutorul functiei Lineclear;
        void mutare(int v[50][50],int i)
{int j,c;
for(j=0;j<9;j++)if(v[i][j]==1&&v[i+1][j]==3)Gameover=0;else
if(v[i][j]==1){c=v[i+1][j];v[i+1][j]=v[i][j];v[i][j]=c;}
if(Wall!=10)Wall++;else {lineclear(Wall);Wall=0;K=0;}}


//Functia re(refresh) are rolul de a completa spatiul curent al jucatorului dupa o miscare cu 3;
        void re()
{Plan[Playerline][Player]=3;}

//Functia de are rolul de a inlocui fostul loc ocupat de jucator cu 0 in cazul in care se face o interschimbare
        void de()
{Plan[Playerline][Player]=0;}


//Functia score are ca rol cresterea scorului
        void score()
{scor=scor+10;}



//Functia input foloseste un if in care testam daca s-a apasat o tasta cu functia kbhit din conio.h , in cazul in care se apasa o tasta avem 3 cazuri
//Folosim un switch pentru a activa cazurile
// cazul D -miscare spre dreapta, contine un if care nu lasa coordonata Player sa iasa din ecran pe partea dreapta, si pe ramura else miscarea propriu-zisa cu ajutorul functiilor re si de;
// similar cazul A - miscare spre stanga
// cazul X opreste jocul
// pe ramura default (daca se apasa orice alt buton) se repozitioneaza jucatorul pe pozitia curenta ,astfel nemodificand nimic;
        void input()
{
    if(kbhit())
    switch(getch())
    {           case'd':{if(Player>7)Player=8;else {de();Player++;re();}break;}
                case'a':{if(Player<2)Player=1;else{de();Player--;re();}break;}
                case'x':{Gameover=0;break;}
                default :{Player=Player;re(); break;}}}





        int main()
{//folosim functia color din sistem pentru a recolora textul si fundalul
    system("Color 06");

show_cursor(false);
//text inainte de joc cu scop de tutorial

cout<<endl<<endl<<"     -WALLS-"<<endl;
cout<<endl;
cout<<"  'x'=EXIT ";

cout<<endl<<"a=STANGA d=DREAPTA";
cout<<endl<<"   Apasa orice tasta pentru a incepe jocul";

cout<<endl;
// OPT va fi folosit pentru a incepe jocul

char OPT=0; OPT=getch();
            if (OPT!='0')
//Folosim functiile de mai sus . Initiam planul si apoi folosim un while pentru a face jocul sa ruleze continuu pana cand variabila Gameover este nula
            {
         system("cls");
        start();
while(Gameover)
{
    afisare(Plan);

    cout<<endl;
    cout<<"Scor curent  "<<scor<<endl;
    input();
    if(K==0){random();K=1;}
    mutare(Plan,Wall);
    score();

// Folosim Sleep pentru a da jocului o incetinire si system cls pentru a curata ecranul
    Sleep(100);

    gotoxy(0,0);
}
system("cls");
//La finalul jocului se va scrie scorul
cout<<endl;
cout<<"{"<<'x'<<"_"<<'x'<<"}"<<endl<<'/'<<' '<<' '<<(char)3<<(char)92 <<endl<<endl;
cout<<"-Game Over-"<<endl<<"Scorul final="<<scor;
cout<<endl<<"Apasa orice buton pentru a inchide jocul."<<endl;
for(int h=0;h<=3;h++)Sleep(250);
return 0;}
}
