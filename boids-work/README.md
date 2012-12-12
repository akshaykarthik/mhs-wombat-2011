# README.md

## Purpose
This project was simply a way for me to properly learn python,
pygame, setup.py, and py2exe. It was also a way for me to write
a preliminary engine for future projects.

## Introduction
This project is simply a visualization of a `boids` algorithm.

The `boids` algorithm is simple:


    for each boid:
        (alignment) align the velocity with those nearby
        (cohesion) move the boid toward the center of its neighbors
        (separation) move the boid away from those too close to it

        (follow mouse) move toward the mouse


These steps provide very complex mechanisms.

## Overview
Each of the three steps can be turned on or off by pressing an
holding the following keys:

1. Alignment `(a)`
2. Cohesion `(s)`
3. Separation `(d)`
4. Follow Mouse `(g)`


