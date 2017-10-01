# Simplex
Investigation into the Simplex algorithm, and uses using programming
I attempt to find a way for a computer to solve Simplex algebraically, for given Variables and Constaints

---UPDATE---
On interpretation of results, I found that the assumptions needed for this to function include:
1) The values of slack variables are irrelevant and can be ignored
2) The pivot is chosen in a fixed order, irrespective of changing values
3) In the first row, all variable coefficients are assumed to be negative or 0
4) The Number of Variables = The Number of Coefficients (Unconfirmed, but possible)
5) We can't know at which point the algorithm has been completed, so we must look at all staged solutions

These assumptions make this method of solving algebraically an unsuitable replacement for the usual case by case Simplex algorithm

As such, if I ever update this project, it will be likely a futher investigation into use of Variables and Algebra in Java, or the usual case by case Simplex, quickened by the use of a computer
