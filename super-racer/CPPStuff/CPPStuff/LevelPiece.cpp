#include "stdafx.h"
#include <iostream>

using namespace std;

class LevelPiece {

double x,y;

public:
	double getX(); {return x;}
	double getY(); {return y;}
	void setX(double);
	void setY(double);
	void setXY();

}ex;

void LevelPiece::setX(double a){
	x = a;
}
void LevelPiece::setY(double a){
	y = a;
}
void LevelPiece::setXY(double a, double b){
	x = a;
	y = b;
}

int main(){
	ex.setX(9001.0);
	cout<<"Harsha is dumb x"<<ex.getX();
	return 0;
}