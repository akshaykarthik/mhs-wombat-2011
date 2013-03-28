#include "stdafx.h"
#include "Box2D_v2.2.1\Box2D\Box2D.h"

#include <cstdio>
using namespace std;

int main(){
	//Set gravity vector
	b2Vec2 gravity(0.0f, -9.8f);
	//create world
	b2World world(gravity);

	b2BodyDef groundBodyBase;
	groundBodyBase.position.Set(0.0f, -10.0f);

	b2Body* groundBody = world.CreateBody(&groundBodyBase);

	b2PolygonShape groundBox;
	groundBox.SetAsBox(50.0f, 10.0f);

	groundBody -> CreateFixture(&groundBox, 0.0f);
	//create an object in the world
	b2BodyDef dynObj;
	dynObj.type = b2_dynamicBody;
	dynObj.position.Set(0.0f, 4.0f);
	b2Body* dynamicObj = world.CreateBody(&dynObj);

	b2PolygonShape dynamicBox;
	dynamicBox.SetAsBox(1.0f, 1.0f);

	b2FixtureDef fixtureDef;
	fixtureDef.shape = &dynamicBox;

	fixtureDef.density = 1.0f;
	fixtureDef.friction = 0.3f;

	dynamicObj->CreateFixture(&fixtureDef);

	//**************************
	float32 timeStep = 1.0f/60.0f;
	int32 vIterations = 6;
	int32 pIterations = 2;

	for(int32 i = 0; i < 60; i++){
		world.step(timeStep, vIterations, pIterations);

		b2Vec2 position = dynamicObj->GetPosition();
		float32 angle = dynamicObj->GetAngle();

		printf("%4.2f %4.2f %4.2f\n", position.x, position.y, angle);
	}



	return 0;
}
